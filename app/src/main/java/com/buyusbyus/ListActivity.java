package com.buyusbyus;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.buyusbyus.Model.List;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.buyusbyus.Model.Data;

public class ListActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private FloatingActionButton fab_btn;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;

    private TextView totalsumResult;

    //Globar variable..

    private String type;
    private String note;
    private String post_key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        toolbar=findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Listes des courses");



        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId=mUser.getUid();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Shopping List").child(uId);

        mDatabase.keepSynced(true);





        recyclerView=findViewById(R.id.recycler_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        //Total sum number




        fab_btn=findViewById(R.id.addlist);

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();
            }
        });

    }


    private void customDialog(){

        AlertDialog.Builder mydialog=new AlertDialog.Builder(ListActivity.this);

        LayoutInflater inflater=LayoutInflater.from(ListActivity.this);
        View myview=inflater.inflate(R.layout.input_list,null);

        final AlertDialog dialog=mydialog.create();

        dialog.setView(myview);

        final EditText type=myview.findViewById(R.id.edt_type);
       // final EditText note=myview.findViewById(R.id.edt_note);
        Button btnSave=myview.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mType=type.getText().toString().trim();



                if (TextUtils.isEmpty(mType)){
                    type.setError("Required Field..");
                    return;
                }



                String id=mDatabase.push().getKey();

                String date= DateFormat.getDateInstance().format(new Date());

                List list=new List(mType,date,id);

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put( mType, id);



                //mDatabase.updateChildren(childUpdates);
                mDatabase.child(mType).setValue(list);


                Toast.makeText(getApplicationContext(),"List Add",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });


        dialog.show();

    }


    @Override
    protected void onStart() {
        super.onStart();



        FirebaseRecyclerAdapter<List,MyViewHolder>adapter=new FirebaseRecyclerAdapter<List, MyViewHolder>
                (

                        List.class,
                        R.layout.item_list,
                        MyViewHolder.class,
                        mDatabase
                )
        {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, final List model, final int position) {

                viewHolder.setDate(model.getDate());
                viewHolder.setType(model.getType());



                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        post_key=getRef(position).getKey();
                        type=model.getType();

                        updateData();
                    }
                });

            }
        };

        recyclerView.setAdapter(adapter);

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public MyViewHolder(View itemView) {
            super(itemView);
            myview=itemView;
        }

        public void setType(String type){
            TextView mType=myview.findViewById(R.id.type);
            mType.setText(type);
        }

        public void setNote(String note){
            TextView mNote=myview.findViewById(R.id.note);
            mNote.setText(note);
        }

        public void setDate(String date){
            TextView mDate=myview.findViewById(R.id.date);
            mDate.setText(date);
        }





    }

    public void updateData(){

        Intent listid = new Intent(this,HomeActivity.class);
        listid.putExtra("type",type);
        startActivity(listid);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.log_out:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
