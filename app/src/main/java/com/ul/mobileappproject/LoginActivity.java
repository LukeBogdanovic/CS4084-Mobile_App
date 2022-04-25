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

public class LoginActivity extends AppCompatActivity {

    private EditText emailTV, passwordTV;
    private Button loginBtn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    /**
     * Initializes the user Interface using the xml file.
     * Initializes the firebase authorization.
     * Sets listener on the login button for a click to login the user.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        initializeUI();
        loginBtn.setOnClickListener(view -> loginUserAccount());
    }

    /**
     * Login the user if the email and password provide by the user is correct
     * Otherwise print a message that the login has failed and for the user to try again
     */
    private void loginUserAccount() {
        progressBar.setVisibility(View.VISIBLE);
        String email = emailTV.getText().toString();
        String password = passwordTV.getText().toString();
        // Checking if user has entered email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please Enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please Enter password!", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Initializing the UI of the loginActivity with the fields created in the associated xml file
     */
    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
    }
}
