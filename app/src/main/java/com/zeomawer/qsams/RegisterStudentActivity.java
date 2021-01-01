package com.zeomawer.qsams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterStudentActivity extends AppCompatActivity {
EditText t1,t2;
 Button b1;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        t1=findViewById(R.id.adNum);
        t2=findViewById(R.id.sName);
        b1=findViewById(R.id.btnSaveStudent);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user=fAuth.getCurrentUser();
                DocumentReference df=fStore.collection("Users").document(user.getUid())
                        .collection("Students").document(t1.getText().toString());
                Map<String,Object> userInfo=new HashMap<>();
                userInfo.put("udise",t2.getText().toString());


                df.set(userInfo);
                Toast.makeText(RegisterStudentActivity.this, "Student Added", Toast.LENGTH_SHORT).show();

            }
        });


    }
}