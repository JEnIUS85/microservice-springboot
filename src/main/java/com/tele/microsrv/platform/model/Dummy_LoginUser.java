package com.tele.microsrv.platform.model;

import java.io.Serializable;

public class Dummy_LoginUser implements Serializable {
    private static final long serialVersionUID = -8445943548965154778L;

    private String email;
    private String password;

    public Dummy_LoginUser() {
        super();
    }

    public Dummy_LoginUser(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
