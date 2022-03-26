package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView registerWord, registerUser;
    private EditText username, password, name, email;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        TextView haveAnAccount = (TextView) findViewById(R.id.alreadyhaveAccount);
        haveAnAccount.setOnClickListener(this);

        Button registerUser = (Button) findViewById(R.id.button);
        registerUser.setOnClickListener(this);

        password = (EditText) findViewById(R.id.inputPassword);
        name = (EditText) findViewById(R.id.inputName);
        email = (EditText) findViewById(R.id.inputEmail);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alreadyhaveAccount:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.button:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String enteredEmail = email.getText().toString();
        String enteredPassword = password.getText().toString();
        String enteredName = name.getText().toString();

        if (enteredPassword.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
        }
        else if (enteredName.isEmpty()){
            name.setError("Name is required");
            name.requestFocus();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
        }


    }


}