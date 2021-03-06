package com.zeomawer.qsams;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterStudentActivity extends AppCompatActivity {
    private static final String TAG=RegisterStudentActivity.class.getSimpleName();
    private EditText rollNumD,adNumD, nameD, fatherNameD, motherNameD, residenceD, uidD,dobD,phoneD,admDateD;
    private TextInputLayout rollNumField,admField, nameField, fNameField, mNameField, resField, uidField,dobField,phoneField;
    private Button saveBtn;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private Spinner classSpinner;
    private Spinner c1;
    private String gender="";
    private RadioButton r1,r2;
    private RadioGroup radioGroupD;
    private EditText mDisplayDate;
    private EditText mDisplayDate2;
    private DatePickerDialog.OnDateSetListener onDateSetListener2 ;
    private DatePickerDialog.OnDateSetListener onDateSetListener ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        initializeWidgets();

        initializeListeners();

//************** code for Date of Admission****** Begins*****
        mDisplayDate2= (EditText) findViewById(R.id.admDate);
        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        RegisterStudentActivity.this, android.R.style.Theme,
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
                mDisplayDate2.setText(date);

            }
        };
        //************** code for Admission Date****** End hre*****

        //************** code for DOB of Student****** Begins*****
        mDisplayDate= (EditText) findViewById(R.id.dob);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        RegisterStudentActivity.this, android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
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
                mDisplayDate.setText(date);

            }
        };
        //************** code for DOB of Student****** End hre*****
        //************** code for Spinner Class of Student****** Begins hre*****
        classSpinner = (Spinner) findViewById(R.id.classSpinner);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // do something upon option selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });



        //************** code for Spinner Class of Student****** End hre*****


    }

    private void initializeWidgets() {



        rollNumD=findViewById(R.id.rollNum);
        adNumD = findViewById(R.id.adNum);
        nameD = findViewById(R.id.sName);
        fatherNameD = findViewById(R.id.fatherName);
        motherNameD = findViewById(R.id.motherName);
        residenceD = findViewById(R.id.residence);
        uidD = findViewById(R.id.uid);
        dobD=findViewById(R.id.dob);
        phoneD=findViewById(R.id.phone);
        c1=findViewById(R.id.classSpinner);
        r1=findViewById(R.id.radio_male);
        r2=findViewById(R.id.radio_female);
        admDateD=findViewById(R.id.admDate);

        radioGroupD=findViewById(R.id.radioGroup);
        saveBtn = findViewById(R.id.btnSaveStudent);



    }

    private void initializeListeners() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();






            }
        });


    }

    private void signUp() {

        boolean isValid = true;

        if (r1.isChecked()){
            gender="Male";
        }
        if (r2.isChecked()){
            gender="Female";
        }

        //Validation Starts Here
        //Roll Number
        if(TextUtils.isEmpty(rollNumD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter Roll Number", Toast.LENGTH_SHORT).show();
            return;
        }
        //Adm Number
        if(TextUtils.isEmpty(adNumD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter Admission Number", Toast.LENGTH_SHORT).show();
            return;
        }
        //Name
        if(TextUtils.isEmpty(nameD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
            return;
        }
        //FatherName
        if(TextUtils.isEmpty(fatherNameD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter Father's Name", Toast.LENGTH_SHORT).show();
            return;
        }
        //Mother Name
        if(TextUtils.isEmpty(motherNameD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter Mother's", Toast.LENGTH_SHORT).show();
            return;
        }
        //Residence
        if(TextUtils.isEmpty(residenceD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter Residence", Toast.LENGTH_SHORT).show();
            return;
        }
        //Admission Date
        if(TextUtils.isEmpty(admDateD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter DOb", Toast.LENGTH_SHORT).show();
            return;
        }
        //Aadhar
        if(TextUtils.isEmpty(uidD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter Aadhar Number", Toast.LENGTH_SHORT).show();
            return;
        }
        //Aadhar Length
        if(uidD.getText().toString().length()!=12){
            Toast.makeText(RegisterStudentActivity.this, "Aadhar Must be 12 Digit", Toast.LENGTH_SHORT).show();
            return;
        }

        //DOB
        if(TextUtils.isEmpty(dobD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter DOb", Toast.LENGTH_SHORT).show();
            return;
        }
        //Residence
        if(TextUtils.isEmpty(phoneD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Please Enter Residence", Toast.LENGTH_SHORT).show();
            return;
        }
        //Phone
        if(TextUtils.isEmpty(phoneD.getText().toString().trim())){
            Toast.makeText(RegisterStudentActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        //Phone Length
        if(phoneD.getText().toString().length()!=10){
            Toast.makeText(RegisterStudentActivity.this, "Phone Number must be 10 Digits", Toast.LENGTH_SHORT).show();
            return;
        }

        if (radioGroupD.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(RegisterStudentActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return;
        }
            //Validation Ends:  adNumD.getText().toString()

        if (isValid) {


            Toast.makeText(this, "Data Submitted Successfully", Toast.LENGTH_SHORT).show();
        //Saving Data to Firebase
            FirebaseUser user = fAuth.getCurrentUser();

            DocumentReference df = fStore.collection("Schools").document(user.getUid())
                    .collection("Students").document(adNumD.getText().toString());

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("RollNumber", rollNumD.getText().toString());
            userInfo.put("name", nameD.getText().toString());
            userInfo.put("fathername", fatherNameD.getText().toString());
            userInfo.put("mothername", motherNameD.getText().toString());
            userInfo.put("residence", residenceD.getText().toString());
            userInfo.put("admdate", dobD.getText().toString());
            userInfo.put("uid", uidD.getText().toString());
            userInfo.put("dob", dobD.getText().toString());
            userInfo.put("phone", phoneD.getText().toString());
            userInfo.put("className", c1.getSelectedItem().toString());
            userInfo.put("gender", gender);
            userInfo.put("AdNum", adNumD.getText().toString());


            df.set(userInfo);
            rollNumD.setText("");
            adNumD.setText("");
            nameD.setText("");
            fatherNameD.setText("");
            motherNameD.setText("");
            residenceD.setText("");
            admDateD.setText("");
            uidD.setText("");
            dobD.setText("");
            phoneD.setText("");
            c1.setSelected(false);
            r1.setChecked(false);
            r2.setChecked(false);


            Log.d(TAG,"Success");



        } else{
            Log.d(TAG,"Failed to Submit");
            //Toast.makeText(this, "Not Done", Toast.LENGTH_SHORT).show();
        }


    }


    public void btnClear(View view) {
        rollNumD.setText("");
        adNumD.setText("");
        nameD.setText("");
        fatherNameD.setText("");
        motherNameD.setText("");
        residenceD.setText("");
        uidD.setText("");
        dobD.setText("");
        phoneD.setText("");
        c1.setSelected(false);
        r1.setChecked(false);
        r2.setChecked(false);
    }
}






















