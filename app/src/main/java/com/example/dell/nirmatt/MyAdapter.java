package com.example.dell.nirmatt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 4/30/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> listitems;
    private Context context;
    public ArrayList i_rollno = new ArrayList();
    public ArrayList check = new ArrayList();

    public interface Adder{
        void Add(ArrayList i_rollno, ArrayList check);
    }
    private Adder adder=new TeacherTimetableActivity();


    public MyAdapter(List<ListItem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_att,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ListItem listItem = listitems.get(position);

        holder.textviewrollno.setText(listItem.getRollno());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ViewHolder)holder).mcheckbox.toggle();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textviewrollno;
        public CheckBox mcheckbox;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewrollno=itemView.findViewById(R.id.textviewrollno);
            mcheckbox=itemView.findViewById(R.id.checkbox1);
        }
    }

}
