package com.example.ashraf.myapplication.domain;

import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ashraf on 4/25/15.
 */
public class Person extends BasicNameValuePair implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Numbers> numberses = new HashSet<>();

    public Person(String name, String value) {
        super(name, value);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
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

    public Set<Numbers> getNumberses() {
        return numberses;
    }

    public void setNumberses(Set<Numbers> numberses) {
        this.numberses = numberses;
    }
}
