package com.zeomawer.qsams;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClasswiseAdapter extends RecyclerView.Adapter<ClasswiseAdapter.studentViewHolder>{
    //Click On View https://www.youtube.com/watch?v=69C1ljfDvl0
    private static final String TAG =ClasswiseAdapter.class.getSimpleName() ;
    private ArrayList<ModelViewClasswise> classlist;
    private ArrayList<ModelViewClasswise> newClassList;
    private OnNoteListener mOnNoteListener;



    public ClasswiseAdapter(ArrayList<ModelViewClasswise> classlist,OnNoteListener onNoteListener) {

        this.classlist = classlist;
        this.mOnNoteListener=onNoteListener;

    }
    public void addnewList(ArrayList<ModelViewClasswise> newClassList){
        this.classlist = newClassList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClasswiseAdapter.studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_classwise,parent,false);
        return new ClasswiseAdapter.studentViewHolder(view,mOnNoteListener);


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





    class studentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView t1,t2,t3;
        OnNoteListener onNoteListener;

        public studentViewHolder(@NonNull View itemView,OnNoteListener onNoteListener)
        {
            super(itemView);

            t1=itemView.findViewById(R.id.nameC);
            t2=itemView.findViewById(R.id.classC);
            t3=itemView.findViewById(R.id.rollNumC);

            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);

    }

}
