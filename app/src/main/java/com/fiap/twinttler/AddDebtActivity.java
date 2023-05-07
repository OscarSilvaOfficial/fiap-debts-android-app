package com.fiap.twinttler;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fiap.twinttler.Entities.Debt;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddDebtActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextValue;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);

        editTextName = findViewById(R.id.edit_text_name);
        editTextValue = findViewById(R.id.edit_text_value);
        buttonAdd = findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(view -> {
            String name = editTextName.getText().toString();
            String value = editTextValue.getText().toString();

            if (TextUtils.isEmpty(name)) {
                editTextName.setError("Campo obrigatório");
                return;
            }

            if (TextUtils.isEmpty(value)) {
                editTextValue.setError("Campo obrigatório");
                return;
            }

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Debt debt = new Debt("", name, Double.parseDouble(value));
            Map<String, String> doc = debtToDoc(debt);
            db.collection("debits")
                    .add(doc)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(AddDebtActivity.this, "Conta adicionada com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(AddDebtActivity.this, "Erro ao adicionar a conta", Toast.LENGTH_SHORT).show());
        });
    }

    private Map<String, String> debtToDoc(Debt debt) {
        Map<String, String> doc = new HashMap<>();
        doc.put("title", debt.getTitle());
        doc.put("value", Double.toString(debt.getAmount()));
        return doc;
    }
}
