package com.danu.gehu_erp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signup extends AppCompatActivity {
    TextView login_link;
    EditText E_mail,Pass,Confirm_pass;
    Button signup_Button;;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        login_link = findViewById(R.id.login_link);
        E_mail = findViewById(R.id.editTextPhone);
        Pass = findViewById(R.id.editTextTextPassword);
        Confirm_pass = findViewById(R.id.editTextTextPassword2);
        signup_Button = findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        login_link.setOnClickListener(v -> {
            Intent intent = new Intent(signup.this, login.class);
            startActivity(intent);
        });
        signup_Button.setOnClickListener(v -> {
            String email = E_mail.getText().toString();
            String password = Pass.getText().toString();
            String confirm_password = Confirm_pass.getText().toString();
            if (email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
                Toast.makeText(signup.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }else if (!password.equals(confirm_password)) {
                Toast.makeText(signup.this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
            }else{
                registration(email,password);
                Toast.makeText(signup.this, "Sign up successful", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private void registration(String email,String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(signup.this, login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(signup.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}