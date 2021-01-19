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

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.studentViewHolder>implements Filterable
{
    private static final String TAG = StudentAdapter.class.getSimpleName();

    ArrayList<ModelViewStudent> datalist;
    ArrayList<ModelViewStudent> backup;

    public StudentAdapter(ArrayList<ModelViewStudent> datalist) {

        this.datalist = datalist;
        backup=new ArrayList<>(datalist);
        //backup.addAll(datalist);

    }

    @NonNull
    @Override
    public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
         return new studentViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull studentViewHolder holder, int position) {
        holder.t1.setText(datalist.get(position).getName());
        holder.t2.setText(datalist.get(position).getClassName());
        holder.t3.setText(datalist.get(position).getAdNum());

        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.t1.getContext(),StudentDetails.class);
                intent.putExtra("uid",datalist.get(position).getUid());
                intent.putExtra("residence",datalist.get(position).getResidence());
                intent.putExtra("phone",datalist.get(position).getPhone());
                intent.putExtra("AdNum",datalist.get(position).getAdNum());
                intent.putExtra("name",datalist.get(position).getName());
                intent.putExtra("mothername",datalist.get(position).getMothername());
                intent.putExtra("fathername",datalist.get(position).getFathername());
                intent.putExtra("dob",datalist.get(position).getDob());
                intent.putExtra("admdate",datalist.get(position).getAdmdate());
                intent.putExtra("RollNumber",datalist.get(position).getRollNumber());
                intent.putExtra("AdNum",datalist.get(position).getAdNum());

                intent.putExtra("className",datalist.get(position).getClassName());






                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.t1.getContext().startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }
    Filter filter=new Filter() {
        @Override
        //background thread
        protected FilterResults performFiltering(CharSequence keyword)
        {
           ArrayList<ModelViewStudent>filtereddata=new ArrayList<>();

           Log.d(TAG, keyword.toString());

            if(keyword.toString().isEmpty())
               filtereddata.addAll(backup);
           else
               {
               for(ModelViewStudent obj:backup)
               {
                   if(obj.getName().toLowerCase().contains(keyword.toString().toLowerCase())){
                       filtereddata.add(obj);

                   }

               }
           }
                FilterResults results=new FilterResults();
            Log.d(TAG, results.toString());
           results.values=filtereddata;

            results.count = filtereddata.size();
           return results;
        }

        @Override
        //manin thread
        protected void publishResults(CharSequence constraint, FilterResults results) {
            datalist.clear();
            datalist.addAll((List) results.values);
            //datalist.addAll((ArrayList<ModelViewStudent>) results.values);
            notifyDataSetChanged();

        }
    };

    static class studentViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3;

        public studentViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.nameV);
            t2=itemView.findViewById(R.id.classV);
            t3=itemView.findViewById(R.id.adNumV);

        }
    }

}

