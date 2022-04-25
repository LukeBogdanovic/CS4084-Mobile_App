package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailTV, passwordTV;
    private Button regBtn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        initializeUI();
        regBtn.setOnClickListener(view -> registerNewUser());
    }

    /**
     * Registers the user using the entered email and password.
     * Checks if the email and password fields are empty and returns a toast message if the user
     * has not entered values.
     */
    private void registerNewUser() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        /** Creates the user using the email and password#
         *  Sends the user to the login activity if the registration is successful
         *  Otherwise provides feedback to the user to try again.
         */
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Registration Failed! Please try again later", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Initializes the email and password textview elements from the xml file.
     * Initializes the register and login buttons elements from the xml file.
     */
    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);
        regBtn = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);
    }

}
