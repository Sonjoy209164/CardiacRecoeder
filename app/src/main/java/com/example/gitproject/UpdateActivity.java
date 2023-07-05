

//sonjoy
package com.example.gitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


//import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {
    EditText sys, dias, rate, comment;
    String str_phone, st_time;

    Button delete, submit;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        sys=findViewById(R.id.Update_Enter_Systolic_pressure);
        dias=findViewById(R.id.Update_Enter_Diastolic_pressure);
        rate=findViewById(R.id.Update_Enter_Heart_Rate);
        comment=findViewById(R.id.Update_Enter_Comment);
        submit=findViewById(R.id.Update_UpdateButton);
        delete=findViewById(R.id.Update_DeleteButton);

        Intent intent=getIntent();

        st_time =intent.getStringExtra("Time");

        databaseReference.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(st_time)

                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String st_sys= (String) snapshot.child("Systolic").getValue();
                        sys.setText(st_sys);
                        String st_dias= (String) snapshot.child("Diastolic").getValue();


                        dias.setText(st_dias);

                        String st_rate= (String) snapshot.child("Heart_Rate").getValue();
                        rate.setText(st_rate);
                        String st_comment= (String) snapshot.child("Comment").getValue();
                        comment.setText(st_comment);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st_sys=sys.getText().toString();
                String st_dias=dias.getText().toString();
                String st_rate=rate.getText().toString();

                String st_comment=comment.getText().toString();

                String uid = FirebaseAuth.getInstance().getUid();
                databaseReference.child("users").child(uid).child(st_time).child("Systolic").setValue(st_sys);
                databaseReference.child("users").child(uid).child(st_time).child("Diastolic").setValue(st_dias);
                databaseReference.child("users").child(uid).child(st_time).child("Heart_Rate").setValue(st_rate);
                databaseReference.child("users").child(uid).child(st_time).child("Comment").setValue(st_comment);
                databaseReference.child("users").child(uid).child(st_time).child("Time").setValue(st_time);

                finish();
            }
        });

        delete.setOnClickListener(v -> {
            databaseReference.child("users").child(FirebaseAuth.getInstance().getUid()).child(st_time).removeValue();
            finish();

        });
    }
}