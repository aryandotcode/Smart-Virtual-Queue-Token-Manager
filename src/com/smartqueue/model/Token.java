package com.smartqueue.model;

public class Token {
    private int id;
    private String status;

    public Token(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() { return id; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Token #" + id;
    }
}