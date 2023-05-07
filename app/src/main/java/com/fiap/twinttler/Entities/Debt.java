package com.fiap.twinttler.Entities;

public class Debt {
    private String title;
    private double amount;

    public Debt(String title, double amount) {
        this.title = title;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }
}
