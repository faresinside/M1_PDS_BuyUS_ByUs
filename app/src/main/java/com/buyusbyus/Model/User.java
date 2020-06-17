package com.buyusbyus.Model;

public class User {
    public String nom,prenom, email, regime,phone;

    public User(){

    }

    public User(String nom, String prenom, String email, String phone,String regime) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.regime= regime;
    }


}