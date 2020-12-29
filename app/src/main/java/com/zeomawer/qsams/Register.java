package com.zeomawer.qsams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText udise,email,password,phone;
    Button registerBtn,goToLogin;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();




        udise = findViewById(R.id.registerUdise);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        registerBtn = findViewById(R.id.registerBtn);
        goToLogin=findViewById(R.id.goToLogin);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkField(udise);
                checkField(email);
                checkField(password);
                boolean b = checkField(phone);




                if(valid){
                    //start user Registration process
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user=fAuth.getCurrentUser();
                            Toast.makeText(Register.this,"Account Created",Toast.LENGTH_SHORT).show();
                            DocumentReference df=fStore.collection("Users").document(user.getUid());
                            Map<String,Object>userInfo=new HashMap<>();
                            userInfo.put("udise",udise.getText().toString());
                            userInfo.put("email",email.getText().toString());
                            userInfo.put("phone",phone.getText().toString());
                            //specify if user is admin
                            userInfo.put("isUser","1");

                            df.set(userInfo);
                            // toast successful massage can be put

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Failed To Register", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));

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




}
