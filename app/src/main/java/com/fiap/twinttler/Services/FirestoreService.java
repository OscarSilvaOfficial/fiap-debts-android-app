package com.fiap.twinttler.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirestoreService {
    public List<Object> findAll(String collection) {
        List<Object> data = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean ok = task.isSuccessful();
                        if (ok) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> dbData = document.getData();
                                data.add(dbData);
                            }
                        } else {
                            Log.w("OK", "Error getting documents.", task.getException());
                        }
                    }
                });
        return data;
    }
}
