package com.danu.gehu_erp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class profile_page extends AppCompatActivity {
    static final int PICK_IMAGE_REQUEST = 1;
    Uri imageUri;
    ImageView profileImage;
    EditText f_name,l_name,email,phone,course,specialization,year,semester;
    Button save,uploadButton;;
    FirebaseDatabase db;
    DatabaseReference db_ref;
    Bundle uid_bundle;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        uid_bundle = getIntent().getExtras();
        String uid = uid_bundle.getString("uid");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        profileImage = findViewById(R.id.shapeableImageView);
        uploadButton = findViewById(R.id.button4);
        back = findViewById(R.id.imageView7);
        f_name = findViewById(R.id.editTextText);
        l_name = findViewById(R.id.editTextText2);
        email = findViewById(R.id.editTextText3);
        email.setText(currentUser.getEmail());
        phone = findViewById(R.id.editTextText4);
        course = findViewById(R.id.editTextText6);
        specialization = findViewById(R.id.editTextText8);
        year = findViewById(R.id.editTextText7);
        semester = findViewById(R.id.editTextText9);
        save = findViewById(R.id.button3);
        db = FirebaseDatabase.getInstance();
        back.setOnClickListener(view->{
            startActivity(new Intent(profile_page.this, login.class));
        });
        uploadButton.setOnClickListener(view->{
              if(imageUri!= null){
                  AppwriteUploader uploader = new AppwriteUploader();
                  uploader.uploadFileToAppwrite(imageUri, this,uid);
              }else{
                  Toast.makeText(profile_page.this, "No Image is Selected", Toast.LENGTH_SHORT).show();
              }
         });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ImageUrl = "https://cloud.appwrite.io/v1/storage/buckets/677e275f001bb892a579/files/"+uid+"/view?project=677e273b00160e765ea7&project=677e273b00160e765ea7&mode=admin";
                String name = f_name.getText().toString()+" "+l_name.getText().toString();
                profile_data data = new profile_data(
                        ImageUrl,
                        name,
                        email.getText().toString(),
                        phone.getText().toString(),
                        course.getText().toString(),
                        specialization.getText().toString(),
                        year.getText().toString(),
                        semester.getText().toString());
                db_ref = db.getReference();
                db_ref.child("Users").child(uid).setValue(data);
                startActivity(new Intent(profile_page.this,login.class));
            }
        });


        profileImage.setOnClickListener(v -> openFileChooser());
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);}}

}