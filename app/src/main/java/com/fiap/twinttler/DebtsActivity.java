package com.fiap.twinttler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.fiap.twinttler.Entities.Debt;

import java.util.ArrayList;
import java.util.List;

public class DebtsActivity extends AppCompatActivity {

    private List<Debt> debts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debts);

        debts.add(new Debt("Dívida do cartão de crédito", 500.0));
        debts.add(new Debt("Empréstimo pessoal", 2000.0));
        debts.add(new Debt("Dívida do carro", 10000.0));

        DebtAdapter adapter = new DebtAdapter(debts);

        RecyclerView recyclerView = findViewById(R.id.debtsRecyclerView);
        recyclerView.setAdapter(adapter);
    }
}