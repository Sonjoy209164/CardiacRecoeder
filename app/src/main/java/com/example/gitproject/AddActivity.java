package com.example.gitproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText sys, dias, rate, comment;
    String uid;
    Button add;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sys=findViewById(R.id.Add_Enter_Systolic_pressure);
        dias=findViewById(R.id.Add_Enter_Diastolic_pressure);
        rate=findViewById(R.id.Add_Enter_Heart_Rate);
        comment=findViewById(R.id.ADD_Enter_Comment);
        add=findViewById(R.id.ADD_ADDButton);


        uid  = FirebaseAuth.getInstance().getUid();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st_sys=sys.getText().toString();
                String st_dias=dias.getText().toString();
                String st_rate=rate.getText().toString();
                String st_time=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                String st_comment=comment.getText().toString();

                if(st_sys.isEmpty()) {
                    sys.setError("Systolic pressure can't be empty.");
                }

                else if(st_dias.isEmpty()) {
                    dias.setError("Diastolic pressure can't be empty.");
                }

                else if(st_rate.isEmpty()) {
                    rate.setError("Heart Rate can't be empty.");
                }

                else if(Float.parseFloat(st_sys)<0 || Float.parseFloat(st_sys)>300) {
                    sys.setError("Systolic pressure is invalid.");
                }

                else if(Float.parseFloat(st_dias)<0 || Float.parseFloat(st_dias)>300) {
                    dias.setError("Diastolic pressure is invalid.");
                }

                else if(Float.parseFloat(st_rate)<0 || Float.parseFloat(st_rate)>200) {
                    rate.setError("Heart Rate is invalid.");
                }

                else {
                    databaseReference.child("users").child(uid).child(st_time).child("Systolic").setValue(st_sys);
                    databaseReference.child("users").child(uid).child(st_time).child("Diastolic").setValue(st_dias);
                    databaseReference.child("users").child(uid).child(st_time).child("Heart_Rate").setValue(st_rate);
                    databaseReference.child("users").child(uid).child(st_time).child("Comment").setValue(st_comment);
                    databaseReference.child("users").child(uid).child(st_time).child("Time").setValue(st_time);

                    sys.setText("");
                    dias.setText("");
                    rate.setText("");
                    comment.setText("");

                    finish();
                }
            }
        });
    }
}
