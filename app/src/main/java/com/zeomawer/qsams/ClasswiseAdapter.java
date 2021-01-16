package com.zeomawer.qsams;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class ClasswiseAdapter extends RecyclerView.Adapter<ClasswiseAdapter.studentViewHolder> {
    ArrayList<ModelViewClasswise> classlist;


    public ClasswiseAdapter(ArrayList<ModelViewClasswise> classlist) {

        this.classlist = classlist;


    }

    @NonNull
    @Override
    public ClasswiseAdapter.studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_classwise,parent,false);
        return new ClasswiseAdapter.studentViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull studentViewHolder holder, int position) {
        holder.t1.setText(classlist.get(position).getName());
        holder.t2.setText(classlist.get(position).getClassName());
        holder.t3.setText(classlist.get(position).getRollNumber());

    }







    @Override
    public int getItemCount() {
        return classlist.size();
    }




    class studentViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3;

        public studentViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.nameC);
            t2=itemView.findViewById(R.id.classC);
            t3=itemView.findViewById(R.id.rollNumC);

        }
    }

}
