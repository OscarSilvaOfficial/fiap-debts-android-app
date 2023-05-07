package com.fiap.twinttler.Services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirestoreAuth {
    public Task<AuthResult> verifyLogin(String email, String password) {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(Task::isSuccessful);
    }
}
