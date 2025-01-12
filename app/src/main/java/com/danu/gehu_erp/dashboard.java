package com.danu.gehu_erp;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard extends AppCompatActivity {
    CardView timeTable;
   TextView username,phone,email;
   ShapeableImageView profileImage;
   FirebaseDatabase db ;
   DatabaseReference db_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       // String uid = getIntent().getStringExtra("uid");
       String uid = "HfoV2MIJzLhArxN4Er9JimzGSpz2";
        timeTable = findViewById(R.id.timeTable);
        profileImage = findViewById(R.id.shapeableImageView);
        username = findViewById(R.id.userName_text);
        phone = findViewById(R.id.phone_text);
        email = findViewById(R.id.userEmail_text);
        db = FirebaseDatabase.getInstance();
        db_ref=db.getReference("Users/"+uid);
        timeTable.setOnClickListener(view->{
            startActivity(new Intent(dashboard.this, TimeTable.class));
        });
        db_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    profile_data user = dataSnapshot.getValue(profile_data.class);
                    if (user != null) {
                        Glide.with(dashboard.this)
                                .load(user.getProfileImageUrl())
                                .into(profileImage);
                        String fname = user.getFname();
                        if (fname != null && !fname.isEmpty()) {
                            username.setText(fname);
                        } else {
                            username.setText("Unknown User");  // Or some default text
                        }
                       phone.setText(user.get_phone());
                       email.setText(user.get_email());

                    }
                } else {
                    Toast.makeText(dashboard.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(dashboard.this, "Error Reading Database !!!", Toast.LENGTH_SHORT).show();
            }
        });



    }
}