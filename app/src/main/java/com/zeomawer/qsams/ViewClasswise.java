package com.zeomawer.qsams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewClasswise extends AppCompatActivity {

    private static final String TAG = ViewClasswise.class.getSimpleName();

    private RecyclerView recyclerViewCW;
    ArrayList<ModelViewClasswise>classlist;
    FirebaseFirestore db;
    FirebaseAuth fAuth;
    ClasswiseAdapter classwiseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classwise);

        recyclerViewCW = findViewById(R.id.recyclerviewCW);
        recyclerViewCW.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerViewCW.setLayoutManager(new LinearLayoutManager(this));
        classlist=new ArrayList<>();
        classwiseAdapter=new ClasswiseAdapter(classlist);
        recyclerViewCW.setAdapter(classwiseAdapter);

        db= FirebaseFirestore.getInstance();
        fAuth= FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        db.collection("Schools").document(user.getUid()).collection("Students").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();

                        for(DocumentSnapshot d:list){

                            ModelViewClasswise obj=d.toObject(ModelViewClasswise.class);
                            classlist.add(obj);
                        }
                        //Update Adapter

                        classwiseAdapter.notifyDataSetChanged();
                    }
                });



    }


}