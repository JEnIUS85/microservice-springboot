package com.tele.microsrv.platform.model;

public class LoginResponse {
    private Boolean loggedInSuccessfully;
    private String entitlement;
    private String firstName;
    private String lastName;
    private String uname;

    public Boolean getLoggedInSuccessfully() {
        return loggedInSuccessfully;
    }

    public void setLoggedInSuccessfully(Boolean loggedInSuccessfully) {
        this.loggedInSuccessfully = loggedInSuccessfully;
    }

    public String getEntitlement() {
        return entitlement;
    }

    public void setEntitlement(String entitlement) {
        this.entitlement = entitlement;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "loggedInSuccessfully=" + loggedInSuccessfully +
                ", entitlement='" + entitlement + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }
}
