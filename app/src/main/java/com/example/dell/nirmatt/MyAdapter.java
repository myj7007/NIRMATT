package com.example.dell.nirmatt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
 * Created by DELL on 4/30/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> listitems;
    private Context context;
    String timeslot,clas,sub;
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

    public MyAdapter(Context context,String id,String path) {
        this.context = context;
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser;
        FirebaseAuth mAuth;
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentUser.getUid();
        db.collection("/faculty").document(uid).collection("/time_table").document(path).collection("/slot").document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        timeslot=documentSnapshot.get("time").toString();
                        sub=documentSnapshot.get("subject").toString();
                        clas=documentSnapshot.get("clas").toString();
                        if(clas.equals("6-CE-A")){
                            clas="6cea";
                        }
                        else if (clas.equals("6-CE-B")){
                            clas="6ceb";
                        }

                        db.collection("/class").document(clas).collection("/student")
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot documentSnapshots) {
                                        for (DocumentSnapshot snapshot : documentSnapshots) {
                                            i_rollno.add(snapshot.get("rollno"));
                                        }
                                        notifyDataSetChanged();
                                        check=new ArrayList(getItemCount());
                                        for (int i=0;i<getItemCount();i++){
                                            check.add(true);
                                        }
                                    }
                                });


                    }
                });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_att,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //ListItem listItem = listitems.get(position);

        holder.textviewrollno.setText(i_rollno.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ViewHolder)holder).mcheckbox.toggle();
                check.set(position,((ViewHolder)holder).mcheckbox.isChecked());
                adder.Add(i_rollno,check);
            }
        });


    }

    @Override
    public int getItemCount() {
        return i_rollno.size();
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
