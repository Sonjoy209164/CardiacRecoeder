package com.example.gitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText l_phone, l_pass;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    private EditText email;
    private EditText password;
    private Button login;
    private TextView registerUser;
    private Button logInbutton;
    private TextView  forgetPassButtonLogIn;

    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        if( getSharedPreferences("sp",MODE_PRIVATE).getBoolean("loggedIn",false)){
//            startActivity(new Intent(this,MainActivity.class));
//        }

        email = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        registerUser = findViewById(R.id.register);
        forgetPassButtonLogIn=findViewById(R.id.forgotPassword);

        mAuth = FirebaseAuth.getInstance();

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (txt_email.isEmpty() || txt_password.isEmpty()){

                    Toast.makeText(LoginActivity.this, "Empty Credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(LoginActivity.this,txt_email+txt_password,Toast.LENGTH_LONG).show();
                    loginUser(txt_email , txt_password);
                }
            }
        });
        forgetPassButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText phone = findViewById(R.id.phone);
                String mail = email.getText().toString();
                if(mail.isEmpty()) return;

                FirebaseAuth.getInstance().sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(forgetPassButtonLogIn.getContext(),"A mail is sent to the respective email id!",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(forgetPassButtonLogIn.getContext(),"A mail is not sent!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("sp",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("loggedIn",true);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        l_phone=findViewById(R.id.phone);
        l_pass=findViewById(R.id.password);
    }

    public void loginPage(View view) {
        String st_phone=l_phone.getText().toString();
        String st_pass=l_pass.getText().toString();

        if(st_phone.isEmpty()) {
            l_phone.setError("Please Enter The Phone Number.");
        }
        else if(st_pass.isEmpty()) {
            l_pass.setError("Please Enter The Password.");
        }
        else {
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(st_phone)) {
                        String str_pass = (String) snapshot.child(st_phone).child("Password").getValue();

                        if (str_pass.equals(st_pass)) {
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("Phone", st_phone);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Password Is Wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Phone Number Is Not Registered Yet.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void registerPage(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}