package com.fiap.twinttler;

import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import org.junit.Test;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Mock
    private FirebaseAuth mAuth;

    @Mock
    private EditText emailEditText;

    @Mock
    private EditText passwordEditText;

    @Mock
    private Button loginButton;

    @Mock
    private View loginView;

    @Mock
    private Toast toast;

    @Mock
    private Intent intent;

    @Mock
    private Bundle bundle;

    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = Mockito.spy(MainActivity.class);
        doNothing().when(mainActivity).startActivity(any(Intent.class));
        doReturn(emailEditText).when(mainActivity).findViewById(R.id.editTextEmail);
        doReturn(passwordEditText).when(mainActivity).findViewById(R.id.editTextPassword);
        doReturn(loginButton).when(mainActivity).findViewById(R.id.loginButton);
        doReturn(loginView).when(mainActivity).findViewById(anyInt());
        doReturn(toast).when(mainActivity).makeText(any(Context.class), any(CharSequence.class), anyInt());
        doReturn(intent).when(mainActivity).createIntent(any(Context.class), eq(DebtsActivity.class));
    }

    @Test
    public void testLoginWithValidCredentials() {
        // Set up
        String email = "test@example.com";
        String password = "password123";

        // Mock behavior
        doAnswer(invocation -> {
            Task<AuthResult> task = mock(Task.class);
            doAnswer(innerInvocation -> {
                ((OnCompleteListener<AuthResult>) innerInvocation.getArgument(0)).onComplete(task);
                return task;
            }).when(task).addOnCompleteListener(any(OnCompleteListener.class));
            when(task.isSuccessful()).thenReturn(true);
            return task;
        }).when(mAuth).signInWithEmailAndPassword(email, password);

        // Run test
        emailEditText.setText(email);
        passwordEditText.setText(password);
        loginButton.performClick();

        // Verify behavior
        verify(mainActivity, times(1)).startActivity(intent);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        // Set up
        String email = "test@example.com";
        String password = "wrongpassword";

        // Mock behavior
        doAnswer(invocation -> {
            Task<AuthResult> task = mock(Task.class);
            doAnswer(innerInvocation -> {
                ((OnCompleteListener<AuthResult>) innerInvocation.getArgument(0)).onComplete(task);
                return task;
            }).when(task).addOnCompleteListener(any(OnCompleteListener.class));
            when(task.isSuccessful()).thenReturn(false);
            return task;
        }).when(mAuth).signInWithEmailAndPassword(email, password);

        // Run test
        emailEditText.setText(email);
        passwordEditText.setText(password);
        loginButton.performClick();

        // Verify behavior
        verify(mainActivity, never()).startActivity(intent);
    }

    @Test
    public void testLoginWithEmptyCredentials() {
        // Run test
        emailEditText.setText("");
        passwordEditText.setText("");
        loginButton.performClick();

        // Verify behavior
        verify(mainActivity, never()).startActivity(intent);
        verify(mainActivity, times(1)).makeText(any(Context.class), eq("Invalid email or password"), eq(Toast.LENGTH_SHORT));
        verify(toast, times(1)).show();
    }

}
