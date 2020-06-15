package com.buyusbyus;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.buyusbyus.Model.Data;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Dialog_Proposition extends AppCompatDialogFragment {
    private final String product;
    private final String uId;
    private final String msg;
    private DatabaseReference mDatabase;

    public Dialog_Proposition(String type,String uId,String msg) {
        this.product = type;
        this.uId = uId;
        this.msg = msg;
    }

    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Fréquemment achetés ensemble :")
                .setMessage("Voulez-vous aussi: "+product+ "?")
                .setNegativeButton("Ignorer", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Shopping List").child(uId).child(msg).child("produits");
                        String id=mDatabase.push().getKey();
                        String date= DateFormat.getDateInstance().format(new Date());
                        Data data=new Data(product,"0",date,id);
                        mDatabase.child(id).setValue(data);

                    }
                });
        return builder.create();
    }


}
