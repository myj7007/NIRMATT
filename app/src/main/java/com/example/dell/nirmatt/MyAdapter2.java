package com.example.dell.nirmatt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL on 5/1/2018.
 */

/*public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>{

    private List<ListAttendance> listAttendances;
    private Context context;

    public MyAdapter2(List<ListAttendance> listAttendances, Context context) {
        this.listAttendances = listAttendances;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_subjects,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textviewsubject;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewsubject=itemView.findViewById(R.id.textviewsubject);
        }
    }
}*/

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private List<ListAttendance> listAttendances;
    private Context context;

    public MyAdapter2(List<ListAttendance> listAttendances, Context context) {
        this.listAttendances = listAttendances;
        this.context = context;
    }

    @NonNull
    @Override


    public MyAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_subjects,parent,false);
        return new MyAdapter2.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.ViewHolder holder, int position) {
        ListAttendance listAttendance = listAttendances.get(position);
        holder.textviewsubject.setText(listAttendance.getSubject());

    }

    @Override
    public int getItemCount() {
        return listAttendances.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textviewsubject;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewsubject=itemView.findViewById(R.id.textviewsubject);
        }
    }
}


