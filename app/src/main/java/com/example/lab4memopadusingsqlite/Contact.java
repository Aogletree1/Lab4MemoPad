package com.example.lab4memopadusingsqlite;

public class Contact {

    private int id;
    private String name;

    public Contact(int id, String name, String address) {
        this.id = id;
        this.name = name;
    }

    public Contact(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Name: ").append(name).append("\n");
        return s.toString();
    }

}
