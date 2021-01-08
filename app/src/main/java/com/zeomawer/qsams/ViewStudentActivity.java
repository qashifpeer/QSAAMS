package com.zeomawer.qsams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {
    private List<ProductsModel>listData;
    private FirestoreRecyclerAdapter<ProductsModel, ProductsViewHolder> adapter;
    FirebaseFirestore fStore;
    private RecyclerView rv;
    //private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        fStore = FirebaseFirestore.getInstance();
        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //Query
        com.google.firebase.firestore.Query query=fStore.collection("Users");

        //Recycler OPtions
        FirestoreRecyclerOptions<ProductsModel>options=new FirestoreRecyclerOptions.Builder<ProductsModel>()
                .setQuery(query,ProductsModel.class)
                .build();
         adapter= new FirestoreRecyclerAdapter<ProductsModel, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single,parent,false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ProductsViewHolder productsViewHolder, int i, ProductsModel productsModel) {
                productsViewHolder.list_school.setText(productsModel.getSchool());
                productsViewHolder.list_udise.setText(productsModel.getUdise());
                productsViewHolder.list_user.setText(productsModel.getIsUser());
            }
        };
        //View holdeer Class
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

    private static class ProductsViewHolder extends RecyclerView.ViewHolder{
        private final TextView list_school;
        private final TextView list_udise;
        private final TextView list_user;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            list_school=itemView.findViewById(R.id.list_school);
            list_udise=itemView.findViewById(R.id.list_udise);
            list_user=itemView.findViewById(R.id.list_user);


        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
