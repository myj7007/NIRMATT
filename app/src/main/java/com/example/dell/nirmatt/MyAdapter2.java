package com.example.dell.nirmatt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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


public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private List<ListAttendance> listAttendances;
    ArrayList a1=new ArrayList();
    ArrayList a2=new ArrayList();
    ArrayList a3=new ArrayList();
    ArrayList a4=new ArrayList();
    Long total;

    private Context context;

    public MyAdapter2(Context context) {
        this.context = context;
        FirebaseUser currentUser;
        FirebaseAuth mAuth;
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentUser.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("/user").document(uid).collection("/subject")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                            a1.add(documentSnapshot.getId());
                            a3.add(documentSnapshot.get("Absent"));
                            a4.add(documentSnapshot.get("Present"));
                        }
                        notifyDataSetChanged();
                    }
                });
    }

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
        //ListAttendance listAttendance = listAttendances.get(position);
        holder.a1.setText(a1.get(position).toString());
        holder.a3.setText(a3.get(position).toString());
        holder.a4.setText(a4.get(position).toString());
        total=((long) a3.get(position))+ ((long) a4.get(position));
        total= ((long) a4.get(position))/total;
        holder.a2.setText (total.toString());

    }

    @Override
    public int getItemCount() {
        return listAttendances.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //public TextView textviewsubject;
        TextView a1,a2,a3,a4;

        public ViewHolder(View itemView) {
            super(itemView);
            //textviewsubject=itemView.findViewById(R.id.textview);
            a1=itemView.findViewById(R.id.textView16);
            a2=itemView.findViewById(R.id.textView18);
            a3=itemView.findViewById(R.id.textView20);
            a4=itemView.findViewById(R.id.textView23);


        }
    }
}


