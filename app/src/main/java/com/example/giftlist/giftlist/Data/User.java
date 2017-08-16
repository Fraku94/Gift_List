package com.example.giftlist.giftlist.Data;

import java.io.Serializable;

public class User implements Serializable{

    private Long id;

    private String usename;

    public User(Long id, String usename) {
        this.id = id;
        this.usename = usename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }
}
