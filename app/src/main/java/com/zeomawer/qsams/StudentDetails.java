package com.zeomawer.qsams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudentDetails extends AppCompatActivity {
    EditText t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        initializeWidgets();
        initializeListeners();




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





        b1=findViewById(R.id.btnBack);


    }
}