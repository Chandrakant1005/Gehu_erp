package com.danu.gehu_erp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    private ProgressBar progressBar;
    TextView sign_link;
    Button login_button;
    EditText email,password;
    TextView forgot_pass;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference db_ref;
    Bundle uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.warning_toast,null);
        sign_link = findViewById(R.id.signup_link);
        login_button = findViewById(R.id.button);
        email = findViewById(R.id.Email_edit);
        password = findViewById(R.id.Password_edit);
        forgot_pass = findViewById(R.id.textView3);
        progressBar = findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        sign_link.setOnClickListener(view -> {
            startActivity(new Intent(login.this, signup.class));
        });
        forgot_pass.setOnClickListener(view -> {
            startActivity(new Intent(login.this, forgot_password.class));
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();
                if (email_text.isEmpty() || password_text.isEmpty()) {
                   toast_warning(layout,"Please fill required fields !!!");
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    login_button.setVisibility(View.INVISIBLE);
                    auth.signInWithEmailAndPassword(email_text, password_text).addOnCompleteListener(login.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    login_button.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {

                                        toast_sucess(layout);
                                        FirebaseUser user = auth.getCurrentUser();
                                        uid = new Bundle();
                                        uid.putString("uid", user.getUid());
                                        Intent intent = new Intent(login.this, dashboard.class);
                                        intent.putExtras(uid);
                                        startActivity(intent);


                                    }else{
                                        try {
                                            throw task.getException();
                                        }catch (Exception e){
                                            toast_warning(layout,e.getMessage());
                                        }

                                    }
                                }
                            }

                    );
                }
            }
        });
    }

    private void toast_sucess(View layout) {
        TextView warning_msg = layout.findViewById(R.id.warning_msg);
        TextView warning_title = layout.findViewById(R.id.warning_title);
        CardView background = layout.findViewById(R.id.warning_background);
        ImageView warning_icon = layout.findViewById(R.id.imageView3);
        warning_icon.setImageResource(R.drawable.sucess);
        warning_icon.setScaleX(2);
        warning_icon.setScaleY(2);
        warning_title.setText("Success");
        warning_msg.setText("You are sucessfully Logged In !!!");
        warning_title.setTextColor(getResources().getColor(R.color.success));
        background.setCardBackgroundColor(getResources().getColor(R.color.success_back));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0, 80);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    private void toast_warning(View layout,String waring_msg_text) {
        TextView warning_msg = layout.findViewById(R.id.warning_msg);
        TextView warning_title = layout.findViewById(R.id.warning_title);
        CardView background = layout.findViewById(R.id.warning_background);
        ImageView warning_icon = layout.findViewById(R.id.imageView3);
        warning_icon.setImageResource(R.drawable.icon_warning);
        warning_icon.setScaleX(3);
        warning_icon.setScaleY(3);
        warning_title.setText("Warning");
        warning_msg.setText(waring_msg_text);
        warning_title.setTextColor(getResources().getColor(R.color.warning));
        background.setCardBackgroundColor(getResources().getColor(R.color.warning_back));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0, 80);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}