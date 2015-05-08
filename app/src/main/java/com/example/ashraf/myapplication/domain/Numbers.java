package com.example.ashraf.myapplication.domain;

import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;

/**
 * Created by ashraf on 4/25/15.
 */
public class Numbers extends BasicNameValuePair implements Serializable {
    private Long id;
    private String username;
    private String phone;
    private Person person;

    public Numbers(String name, String value) {
        super(name, value);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
