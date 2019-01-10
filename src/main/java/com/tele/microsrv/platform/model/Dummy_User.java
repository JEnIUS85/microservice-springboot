package com.tele.microsrv.platform.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Document
public class Dummy_User {
    @Id
    private String Id;
    //private String name;

    @NotEmpty(message = "first name must not be empty")
    private String firstName;
    @NotEmpty(message = "last name must not be empty")
    private String lastName;
    @NotEmpty(message = "phone number must not be empty")
    private Long phoneNumber;
    @NotEmpty(message = "Email Id must not be empty")
    private String email;
    @NotEmpty(message = "password must not be empty")
    private String password;
    @NotEmpty(message = "Passcode phrase name must not be empty")
    private String passcodePhrase;
    private Date creationDate = new Date();
    private Boolean activated = new Boolean("False");
    //private Map<String, String> userSettings = new HashMap<>();


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
    public String getEmail() {
        return email;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasscodePhrase() {
        return passcodePhrase;
    }

    public void setPasscodePhrase(String passcodePhrase) {
        this.passcodePhrase = passcodePhrase;
    }



    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

   /* public Map<String, String> getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(Map<String, String> userSettings) {
        this.userSettings = userSettings;
    }*/

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "Dummy_User{" +
                "Id='" + Id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", userName='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passcodePhrase='" + passcodePhrase + '\'' +
                ", creationDate=" + creationDate +
                ", activated=" + activated +
                '}';
    }
}
