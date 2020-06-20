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

import com.buyusbyus.Model.Data;
import com.indooratlas.android.sdk.examples.ListExamplesActivity;
import com.indooratlas.android.sdk.examples.wayfinding.WayfindingOverlayActivity;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private FloatingActionButton fab_btn;
    private FloatingActionButton go_btn;
    private FloatingActionButton search_btn;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;



    //Globar variable..

    private String type;
    private String note;
    private String post_key;
    private String list;
    private String uId;
    private String msg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Liste des courses");



        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        uId=mUser.getUid();


        Bundle bn=getIntent().getExtras();
        msg = bn.getString("type");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Shopping List").child(uId).child(msg).child("produits");

        mDatabase.keepSynced(true);



        recyclerView=findViewById(R.id.recycler_home);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        //Total sum number

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot snap:dataSnapshot.getChildren()){

                    Data data=snap.getValue(Data.class);


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        fab_btn=findViewById(R.id.fab);
        go_btn=findViewById(R.id.go);
        search_btn=findViewById(R.id.search);

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();
            }
        });

        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), WayfindingOverlayActivity.class));
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Search.class));
            }
        });

    }


    private void customDialog(){

        AlertDialog.Builder mydialog=new AlertDialog.Builder(HomeActivity.this);

        LayoutInflater inflater=LayoutInflater.from(HomeActivity.this);
        View myview=inflater.inflate(R.layout.input_data,null);

        final AlertDialog dialog=mydialog.create();

        dialog.setView(myview);

        final EditText type=myview.findViewById(R.id.edt_type);
        final EditText note=myview.findViewById(R.id.edt_note);
        Button btnSave=myview.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mType=type.getText().toString().trim();
                String mNote=note.getText().toString().trim();
                if (!getProposition(mType).equals("None"))
                     openDialog(getProposition(mType),uId,msg);



                if (TextUtils.isEmpty(mType)){
                    type.setError("Required Field..");
                    return;
                }

                if (TextUtils.isEmpty(mNote)){
                    note.setError("Required Field..");
                    return;
                }


                String id=mDatabase.push().getKey();


                String date= DateFormat.getDateInstance().format(new Date());

                Data data=new Data(mType,mNote,date,id);

                mDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(),"Data Add",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
               // Intent i = new Intent(getApplicationContext(),PopActivity.class);
                //startActivity(i);


            }
        });


        dialog.show();

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,MyViewHolder>adapter=new FirebaseRecyclerAdapter<Data, MyViewHolder>
                (
                        Data.class,
                        R.layout.item_data,
                        MyViewHolder.class,
                        mDatabase
                )
        {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, final Data model, final int position) {

                viewHolder.setDate(model.getDate());
                viewHolder.setType(model.getType());
                viewHolder.setNote(model.getNote());


                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        post_key=getRef(position).getKey();
                        type=model.getType();
                        note=model.getNote();



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

        AlertDialog.Builder mydialog=new AlertDialog.Builder(HomeActivity.this);

        LayoutInflater inflater=LayoutInflater.from(HomeActivity.this);

        View mView=inflater.inflate(R.layout.update_inputfield,null);

        final AlertDialog dialog=mydialog.create();

        dialog.setView(mView);

        final EditText edt_Type=mView.findViewById(R.id.edt_type_upd);
        final EditText edt_Note=mView.findViewById(R.id.edt_note_upd);

        edt_Type.setText(type);
        edt_Type.setSelection(type.length());



        edt_Note.setText(note);
        edt_Note.setSelection(note.length());



        Button btnUpdate=mView.findViewById(R.id.btn_SAVE_upd);
        Button btnDelete=mView.findViewById(R.id.btn_delete_upd);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type=edt_Type.getText().toString().trim();



                note=edt_Note.getText().toString().trim();


                String date=DateFormat.getDateInstance().format(new Date());

                Data data=new Data(type,note,date,post_key);

                mDatabase.child(post_key).setValue(data);


                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase.child(post_key).removeValue();
                dialog.dismiss();

            }
        });



        dialog.show();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    Apriori rules = new Apriori();

    public String getProposition(String type){

        int i = 0;
        while (!rules.getRules().get(i).getItemA().equals(type) & i<=(rules.getRules().size()/2)){
            i++;
        }

        if(i<(rules.getRules().size()/2))
                return rules.getRules().get(i).getItemB();
        else{
            return "None";}
    }


    public void openDialog(String type,String uId,String msg) {

        Dialog_Proposition dialog = new Dialog_Proposition (type,uId,msg);
        dialog.show(getSupportFragmentManager(),"example dialog");

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
