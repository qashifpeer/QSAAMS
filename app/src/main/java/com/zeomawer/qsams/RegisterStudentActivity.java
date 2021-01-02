package com.zeomawer.qsams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
EditText adNum,name,fatherName,motherName,residence,aadhar;
 Button b1;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        adNum=findViewById(R.id.adNum);
        name=findViewById(R.id.sName);
        fatherName=findViewById(R.id.fatherName);
        motherName=findViewById(R.id.motherName);
        residence=findViewById(R.id.residence);
        aadhar=findViewById(R.id.uid);
        b1=findViewById(R.id.btnSaveStudent);

        String admNum=adNum.getText().toString().trim();
        String sName=name.getText().toString().trim();
        String fName=fatherName.getText().toString().trim();
        String mName=motherName.getText().toString().trim();
        String res=residence.getText().toString().trim();
        String uid=aadhar.getText().toString().trim();




        b1.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {




                if(TextUtils.isEmpty(admNum)){
                    Toast.makeText(RegisterStudentActivity.this, "Please Enter Admission Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sName)){
                    Toast.makeText(RegisterStudentActivity.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(fName)){
                    Toast.makeText(RegisterStudentActivity.this, "Please Enter Father's Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mName)){
                    Toast.makeText(RegisterStudentActivity.this, "Please Enter Mother's", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(res)){
                    Toast.makeText(RegisterStudentActivity.this, "Please Enter Residence", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(uid)){
                    Toast.makeText(RegisterStudentActivity.this, "Please Enter Your Aadhar Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(uid.length()!=12){
                    Toast.makeText(RegisterStudentActivity.this, "Aadhar Number Should be of 12 Digits", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(valid){


                }

                FirebaseUser user=fAuth.getCurrentUser();
                DocumentReference df=fStore.collection("Users").document(user.getUid())
                        .collection("Students").document(admNum);
                Map<String,Object> userInfo=new HashMap<>();
                userInfo.put("Name",name);
                userInfo.put("fatherName",fName);
                userInfo.put("motherName",mName);
                userInfo.put("residence",res);
                userInfo.put("uid",uid);


                df.set(userInfo);
                Toast.makeText(RegisterStudentActivity.this, "Student Added", Toast.LENGTH_SHORT).show();

                adNum.setText("");
                name.setText("");
                fatherName.setText("");
                motherName.setText("");
                residence.setText("");
                aadhar.setText("");


            }
        });


    }

   public void valid(){

   }
}