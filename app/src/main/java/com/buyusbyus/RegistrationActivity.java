package com.buyusbyus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.buyusbyus.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private EditText Enom;
    private EditText Eprenom;
    private EditText Ephone;
    private TextView signin;
    private Button btnReg;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth=FirebaseAuth.getInstance();

        mDialog=new ProgressDialog(this);

        Enom=findViewById(R.id.nom);
        Eprenom=findViewById(R.id.prenom);
        email=findViewById(R.id.email_reg);
        Ephone=findViewById(R.id.phone);
        pass=findViewById(R.id.password_reg);


        btnReg=findViewById(R.id.btn_reg);
        signin=findViewById(R.id.signin_txt);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nom=Enom.getText().toString().trim();
                final String prenom=Eprenom.getText().toString().trim();
                final String mEmail=email.getText().toString().trim();
                final String phone=Ephone.getText().toString().trim();
                String mPass=pass.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field..");
                    return;
                }
                if (TextUtils.isEmpty(mPass)){
                    pass.setError("Required Field..");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(
                                    nom,
                                    prenom,
                                    mEmail,
                                    phone
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user);


                            startActivity(new Intent(getApplicationContext(),ListActivity.class));
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();

                        }else {
                            Toast.makeText(getApplicationContext(),"Failed..",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }

                    }
                });



            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }
}
