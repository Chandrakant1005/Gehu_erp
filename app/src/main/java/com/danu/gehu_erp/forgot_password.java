package com.danu.gehu_erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
    EditText email;
    Button button;
    TextView login;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.warning_toast,null);
        login = findViewById(R.id.textView10);
        email = findViewById(R.id.editTextTextEmailAddress);
        button = findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();
        login.setOnClickListener(view ->{
            startActivity(new Intent(forgot_password.this, login.class));
        } );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = email.getText().toString();
                if (email_text.isEmpty()) {
                   toast_warning(layout,"Please fill required fields !!!");
                }else{
                    auth.sendPasswordResetEmail(email_text).addOnCompleteListener(forgot_password.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            toast_sucess(layout);
                            startActivity(new Intent(forgot_password.this, login.class));
                        }
                    });
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
        warning_msg.setText("Email with reset link has been sent to registered email.");
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