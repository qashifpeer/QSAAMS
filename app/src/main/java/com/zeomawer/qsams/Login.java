package com.zeomawer.qsams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button goToRegister,loginBtn;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

         goToRegister = findViewById(R.id.loginRegisterBtn);
        email = findViewById(R.id.userName_login);
        password = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkField(email);
                checkField(password);

                if(valid){
                    fAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            checkUserAccess(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }

            }
        });


        goToRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 startActivity(new Intent(getApplicationContext(),Register.class));
             }
         });
    }

    private void checkUserAccess(String uid) {
        DocumentReference df=fStore.collection("Users").document(uid);
        //Extract Data from the document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: " +documentSnapshot.getData());
                //Identify User Access Level
                if(documentSnapshot.getString("isAdmin")!=null){
                    //if admin field is present user is admin
                    startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                    finish();
                }
                if (documentSnapshot.getString("isUser")!= null){
                    //teacher user
                    startActivity(new Intent(getApplicationContext(),UserActivity.class));
                    finish();
                }
            }
        });
    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }
    }
}