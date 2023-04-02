package com.example.lab4memopadusingsqlite;

public class Memo {

    private int id;
    private String memo;

    public Memo(int id, String name) {
        this.id = id;
        this.memo = name;
    }

    public Memo(String name) {
        this.memo = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(getId()).append(". ").append(memo).append("\n");
        return s.toString();
    }

}
