package com.danu.gehu_erp;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard extends AppCompatActivity {
   TextView username,phone,email;
   FirebaseDatabase db ;
   DatabaseReference db_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        Log.d("uid",uid);
        username = findViewById(R.id.userName_text);
        phone = findViewById(R.id.phone_text);
        email = findViewById(R.id.userEmail_text);

        db = FirebaseDatabase.getInstance();
        db_ref=db.getReference("Users/"+uid);

        db_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User_details user = dataSnapshot.getValue(User_details.class);
                    // Use the user object
                    if (user != null) {
                        Log.d("TAG",uid);
                        Log.d("TAG", "User name: " + user.getName());
                        Log.d("TAG", "User email: " + user.getEmail());
                        Log.d("TAG", "User phone: " + user.getPhone());
                    }
                } else {
                    Log.d("TAG", "User does not exist.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "Error reading data: " + databaseError.getMessage());
            }
        });



    }
}