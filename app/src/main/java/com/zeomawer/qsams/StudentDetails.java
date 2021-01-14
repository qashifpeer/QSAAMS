package com.zeomawer.qsams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.Document;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class StudentDetails extends AppCompatActivity {
    private EditText t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    private Spinner s1;
    private Button b1;
    private FloatingActionButton f1,f2;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private EditText mDob;
    private DatePickerDialog.OnDateSetListener onDateSetListener ;
    private EditText mDisplayAdmDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener2 ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        initializeWidgets();
        initializeListeners();

        //Admission Date Listener
        admDate();
        //DOB Date Listener
        dob();

        fetchData();

        f1.setOnClickListener(this::editData);
        f2.setOnClickListener(this::updateData);

    }

    private void dob() {

        //************** code for DOB of Student****** Begins*****
        mDob= (EditText) findViewById(R.id.txtDob);
        mDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        StudentDetails.this, android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        onDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+ "/" + month + "/" +year;
                mDob.setText(date);

            }
        };
        //************** code for DOB of Student****** End hre*****
    }

    private void admDate() {
        //************** code for Date of Admission****** Begins*****
        mDisplayAdmDate= (EditText) findViewById(R.id.txtAdDate);
        mDisplayAdmDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        StudentDetails.this, android.R.style.Theme,
                        onDateSetListener2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        onDateSetListener2=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+ "/" + month + "/" +year;
                mDisplayAdmDate.setText(date);

            }
        };
        //************** code for Admission Date****** End hre*****
    }

    private void updateData(View view) {
        update();

    }


    private void update(){


        FirebaseUser user = fAuth.getCurrentUser();
        DocumentReference df = fStore.collection("Schools").document(user.getUid())
                .collection("Students").document(t7.getText().toString());




        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name",t1.getText().toString());
        userInfo.put("fathername",t2.getText().toString());
        userInfo.put("mothername",t3.getText().toString());
        userInfo.put("uid",t4.getText().toString());
        userInfo.put("residence",t5.getText().toString());

        userInfo.put("phone",t6.getText().toString());
        userInfo.put("admdate",t8.getText().toString());
        //userInfo.put("className",t9.getText().toString());
        userInfo.put("dob",t10.getText().toString());
        userInfo.put("RollNumber",t11.getText().toString());
        userInfo.put("className",s1.getSelectedItem().toString());


        df.update(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(StudentDetails.this, "Success", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(StudentDetails.this, t7.getText().toString(), Toast.LENGTH_SHORT).show();


            }
        });




    }

    private void fetchData() {
        t1.setText(getIntent().getStringExtra("name").toString());
        t2.setText(getIntent().getStringExtra("fathername").toString());
        t3.setText(getIntent().getStringExtra("mothername").toString());
        t4.setText(getIntent().getStringExtra("uid").toString());
        t5.setText(getIntent().getStringExtra("residence").toString());
        t6.setText(getIntent().getStringExtra("phone").toString());
        t7.setText(getIntent().getStringExtra("AdNum").toString());
        t8.setText(getIntent().getStringExtra("admdate").toString());
        t9.setText(getIntent().getStringExtra("className").toString());
        t10.setText(getIntent().getStringExtra("dob").toString());
        t11.setText(getIntent().getStringExtra("RollNumber").toString());
        //s1.setSelected(Boolean.parseBoolean(getIntent().getStringExtra("className")));





    }

    private void editData(View view) {
        f2.setVisibility(View.VISIBLE);
        s1.setVisibility(View.VISIBLE);
        f1.setVisibility(View.INVISIBLE);
        t9.setVisibility(View.INVISIBLE);


        t1.setFocusable(true);
        t1.setFocusableInTouchMode(true);
        t1.setCursorVisible(true);
        t1.setClickable(true);

        t2.setFocusable(true);
        t2.setFocusableInTouchMode(true);
        t2.setCursorVisible(true);
        t2.setClickable(true);

        t3.setFocusable(true);
        t3.setFocusableInTouchMode(true);
        t3.setCursorVisible(true);
        t3.setClickable(true);

        t4.setFocusable(true);
        t4.setFocusableInTouchMode(true);
        t4.setCursorVisible(true);
        t4.setClickable(true);

        t5.setFocusable(true);
        t5.setFocusableInTouchMode(true);
        t5.setCursorVisible(true);
        t5.setClickable(true);


















    }

    private void initializeListeners() {

    }

    private void initializeWidgets() {
        t1=findViewById(R.id.txtName);
        t2=findViewById(R.id.txtFName);
        t3=findViewById(R.id.txtMName);
        t4=findViewById(R.id.txtUid);
        t5=findViewById(R.id.txtRes);
        t6=findViewById(R.id.txtPhone);
        t7=findViewById(R.id.txtAdNum);
        t8=findViewById(R.id.txtAdDate);
        t9=findViewById(R.id.txtClass);
        t10=findViewById(R.id.txtDob);
        t11=findViewById(R.id.txtRollN);
        s1=findViewById(R.id.spinnerClass);

        f1=findViewById(R.id.faEdit);
        f2=findViewById(R.id.faUpdate);


    }
}