package com.zeomawer.qsams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.api.LogDescriptor;
import com.zeomawer.qsams.databinding.ActivityRegisterStudentBinding;
import com.zeomawer.qsams.databinding.ActivityViewStudentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ViewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_student);
       // RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ActivityViewStudentBinding activityViewStudentBinding=ActivityViewStudentBinding.inflate(getLayoutInflater());
        setContentView(activityViewStudentBinding.getRoot());

        UserRecyclerAdapter userRecyclerAdapter = new UserRecyclerAdapter(getUserList());
        activityViewStudentBinding.recyclerView.setAdapter(userRecyclerAdapter);
        activityViewStudentBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Jhon Doe", 70, true, "https://picsum.photos/id/237/200"));
        userList.add(new User("Charles Dickens", 70, true, "https://picsum.photos/id/238/200"));
        userList.add(new User("Harry Potter", 70, false, "https://picsum.photos/id/239/200"));
        userList.add(new User("Jessica Simpson", 70, true, "https://picsum.photos/id/240/200"));
        userList.add(new User("Paul Addams", 70, false, "https://picsum.photos/id/241/200"));
        return userList;
    }
}