package com.zeomawer.qsams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterSchoolActivity extends AppCompatActivity {
    private EditText mUdise, mSchool;
    private Button mRegisterBtn, mBtnClear;
    boolean valid = true;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private TextInputLayout fieldUdiseM,fieldSchoolM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialise Views
        initViews();

        //Firebase Connection
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //OnClick Listener For Register School
        mRegisterBtn.setOnClickListener(this::registerSchool);

        //OnClick Listener For Clear Button
        mBtnClear.setOnClickListener(this::clearData);



    }

    private void clearData(View view) {
        mUdise.setText("");
        mSchool.setText("");
    }

    private void registerSchool(View view) {

        //Check Validation
        validation(mUdise);
        validation(mSchool);

        if(valid) {
            Toast.makeText(this, "School Details Updated!", Toast.LENGTH_SHORT).show();



            String udise = mUdise.getText().toString().trim();
            String school = mSchool.getText().toString().trim();

            FirebaseUser user = fAuth.getCurrentUser();
            DocumentReference df = fStore.collection("Users").document(user.getUid());
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("Udise", udise);
            userInfo.put("School", school);
            //specify if user is admin
            userInfo.put("isUser", "1");

            df.set(userInfo);
            finish();

        }
    }

    private void initViews() {

        mUdise = findViewById(R.id.registerUdise);
        mSchool = findViewById(R.id.registerSchool);
        fieldSchoolM=findViewById(R.id.fieldSchool);
        fieldUdiseM=findViewById(R.id.fieldUdise);

        mRegisterBtn = findViewById(R.id.registerBtn);
        mBtnClear = findViewById(R.id.btnClear);
    }
    public boolean validation(EditText textField) {


        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }
        return valid;


    }

}




