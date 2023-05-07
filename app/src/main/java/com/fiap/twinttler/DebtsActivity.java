package com.fiap.twinttler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.fiap.twinttler.Adapters.DebtAdapter;
import com.fiap.twinttler.Entities.Debt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DebtsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private final List<Debt> debts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debts);

        db = FirebaseFirestore.getInstance();
        db.collection("debits")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> data = document.getData();
                            String title = (String) data.get("title");
                            String amount = (String) data.get("value");
                            debts.add(new Debt(title, Double.parseDouble(amount)));
                        }

                        RecyclerView recyclerViewDebits = findViewById(R.id.debtsRecyclerView);
                        DebtAdapter adapter = new DebtAdapter(debts);
                        recyclerViewDebits.setAdapter(adapter);
                    } else {
                        Log.e("DebitsActivity", "Error getting documents: ", task.getException());
                    }
                }
            })
            .addOnFailureListener(e -> Toast.makeText(DebtsActivity.this, "Erro ao obter a lista de débitos", Toast.LENGTH_SHORT).show());;
    }
}