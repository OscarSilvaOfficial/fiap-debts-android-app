package com.fiap.twinttler.Entities;

public class Debt {
    private String id;
    private String title;
    private double amount;

    public Debt(String id, String title, double amount) {
        this.id = id;
        this.title = title;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }
}
