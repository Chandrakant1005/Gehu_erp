package com.danu.gehu_erp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile_page extends AppCompatActivity {
    EditText f_name,l_name,email,phone,course,specialization,year,semester;
    Button save;
    FirebaseDatabase db;
    DatabaseReference db_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        f_name = findViewById(R.id.editTextText);
        l_name = findViewById(R.id.editTextText2);
        email = findViewById(R.id.editTextText3);
        phone = findViewById(R.id.editTextText4);
        course = findViewById(R.id.editTextText6);
        specialization = findViewById(R.id.editTextText8);
        year = findViewById(R.id.editTextText7);
        semester = findViewById(R.id.editTextText9);
        save = findViewById(R.id.button3);
        db = FirebaseDatabase.getInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_data data = new profile_data(
                        f_name.getText().toString(),
                        l_name.getText().toString(),
                        email.getText().toString(),
                        phone.getText().toString(),
                        course.getText().toString(),
                        specialization.getText().toString(),
                        year.getText().toString(),
                        semester.getText().toString(),
                        true);
                        db_ref = db.getReference();
                        db_ref.child("Users").child("owr69pHdAbTL5N15ajsG2qFdFt02").setValue(data);
            }
        });

    }
}