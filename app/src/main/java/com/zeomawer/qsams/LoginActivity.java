package com.zeomawer.qsams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout mEmailLayout, mPasswordLayout;
    private Button mBtnSignin, mBtnRegisterUser;
    private TextView mOutputText;
   private EditText mEmail,mPassword;

    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fStore=FirebaseFirestore.getInstance();



        initViews();
        fAuth=FirebaseAuth.getInstance();


        mBtnSignin.setOnClickListener(this::singInUser);
        mBtnRegisterUser.setOnClickListener(this::createUser);

        //hideProgressBar();
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                updateUI();
            }
        };

    }

    private void singInUser(View view) {

        if (!validateEmailAddress() | !validatePassword()) {
            // Email or Password not valid,
            return;
        }
        //Email and Password valid, sign in user here
        String email=mEmail.getText().toString().trim();
        String password=mPassword.getText().toString().trim();

        fAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Sign In Successful!", Toast.LENGTH_SHORT).show();
                          AuthResult authResult=task.getResult();
                            Log.d("TAG", "onSuccessAuthResult: " +authResult.getUser().getUid());
                            checkUserAccess(authResult.getUser().getUid());
                            //updateUI();
                            
                        }else{
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){

                                Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                mOutputText.setText("Invalid Password");
                            }else if(task.getException() instanceof FirebaseAuthInvalidUserException){

                                Toast.makeText(LoginActivity.this, "Email not is use", Toast.LENGTH_SHORT).show();
                                mOutputText.setText("Email not in use");
                            }

                        }

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
                //Identify List_Data Access Level
                if(documentSnapshot.getString("isAdmin")!=null){
                    //if admin field is present user is admin
                    startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                    finish();
                }
                if (documentSnapshot.getString("isUser")!= null){
                    //teacher user
                    startActivity(new Intent(getApplicationContext(),ViewStudentActivity.class));
                    finish();
                }
            }
        });
    }
    private void updateUI() {
        FirebaseUser user=fAuth.getCurrentUser();
        //user.reload();
        if(user==null){
            mOutputText.setText("List_Data not Logged In");
            return;
        }else{
            mOutputText.setText(user.getEmail());
        }

    }


    private void createUser(View view) {

        if (!validateEmailAddress() | !validatePassword()) {
            // Email or Password not valid,
            return;
        }
        //Email and Password valid, create user here

        String email=mEmail.getText().toString().trim();
        String password=mPassword.getText().toString().trim();

        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "List_Data created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), RegisterSchoolActivity.class));

                    //hideProgressBar();
//                            updateUI();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(LoginActivity.this, "Email Already Registered", Toast.LENGTH_SHORT).show();
                    mOutputText.setText("Email already in use");
                }

            }
        });
    }
    private void initViews() {
        mEmail = findViewById(R.id.userName_login);
        mEmailLayout=findViewById(R.id.fieldLogin);
        mPasswordLayout=findViewById(R.id.fieldPassword);
        mPassword = findViewById(R.id.password_login);
        mBtnSignin = findViewById(R.id.btn_login);
        mBtnRegisterUser = findViewById(R.id.loginRegisterBtn);
        mOutputText = findViewById(R.id.tv_output);

    }





    private boolean validateEmailAddress() {

        String email = mEmail.getText().toString().trim();

        if (email.isEmpty()) {
            mEmailLayout.setError("Email is required. Can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailLayout.setError("Invalid Email. Enter valid email address.");
            return false;
        } else {
            mEmailLayout.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {

        String password = mPassword.getText().toString().trim();

        if (password.isEmpty()) {
            mPasswordLayout.setError("Password is required. Can't be empty.");
            return false;
        } else if (password.length() < 6) {
            mPasswordLayout.setError("Password short. Minimum 6 characters required.");
            return false;
        } else {
            mPasswordLayout.setError(null);
            return true;
        }

       }


    @Override
    protected void onResume() {
        super.onResume();
        fAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
          super.onPause();
        if(mAuthStateListener != null){

            if (fAuth == null) {
                fAuth.removeAuthStateListener(mAuthStateListener);
            }
        }
    }


}

