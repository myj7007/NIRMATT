package com.example.dell.nirmatt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 5/1/2018.
 */

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ViewHolder>{
    private List<ListSlot> listSlots;
    private Context context;
    ArrayList timeslot,clas,sub,id;
    String path="";

    public MyAdapter3(Context context,String day) {

        this.context = context;
        timeslot=new ArrayList();
        clas=new ArrayList();
        sub=new ArrayList();
        id=new ArrayList();

        FirebaseUser currentUser;
        FirebaseAuth mAuth;
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentUser.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if(day.equals("Monday")){
            path="mon";
        }
        else if(day.equals("Tuesday")){
            path="tue";
        }
        else if(day.equals("Wednesday")){
            path="wed";
        }
        else if(day.equals("Thursday")){
            path="thu";
        }
        else if(day.equals("Friday")){
            path="fri";
        }
        db.collection("/faculty").document(uid).collection("/time_table").document(path).collection("/slot")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                            timeslot.add(documentSnapshot.get("time"));
                            clas.add(documentSnapshot.get("clas"));
                            sub.add(documentSnapshot.get("subject"));
                            id.add(documentSnapshot.getId());
                        }
                        notifyDataSetChanged();
                    }
                });
    }

    public MyAdapter3(List<ListSlot> listSlots, Context context) {
        this.listSlots = listSlots;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slots,parent,false);
        return new MyAdapter3.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter3.ViewHolder holder, int position) {

        holder.timeslot.setText(timeslot.get(position).toString());
        holder.sub.setText(sub.get(position).toString());
        holder.clas.setText(clas.get(position).toString());
        holder.id=id.get(position).toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(context,TeacherTimetableActivity.class);
                I.putExtra("id", holder.id);
                I.putExtra("path", path);
                context.startActivity(I);



            }
        });


    }

    @Override
    public int getItemCount() {
        return timeslot.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView timeslot;
        public TextView sub;
        public TextView clas;
        String id;


        public ViewHolder(View itemView) {
            super(itemView);
            timeslot=itemView.findViewById(R.id.timeslot);
            sub=itemView.findViewById(R.id.sub);
            clas=itemView.findViewById(R.id.clas);
        }
    }
}
