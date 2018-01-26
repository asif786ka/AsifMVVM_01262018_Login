package com.asiflogin.loginmvvm0126.trade.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class User extends RealmObject {
    @PrimaryKey
    private String email;
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
