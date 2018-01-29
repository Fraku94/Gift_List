package com.example.giftlist.giftlist.Data;

/**
 * Created by Z710 on 2018-01-29.
 */

public class UsersDatabase {


    //Prywatne zmienne
    String _id;
    String _username;
    String _password;
    String _email;

    //Pusty konstruktor
    public UsersDatabase(){

    }

    //Konstruktor



    public UsersDatabase(String id, String username, String password, String email){
        this._id =id;
        this._username = username;
        this._password = password;
        this._email = email;

    }

    //Konstruktor

//    public UsersDatabase(String i, String phone_number){
//        this._name = name;
//        this._phone_number = phone_number;
//
//    }

    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String username) {
        this._username = username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String password) {
        this._password = password;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String email) {
        this._email = email;
    }
}