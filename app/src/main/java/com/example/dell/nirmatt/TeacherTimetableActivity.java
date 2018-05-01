package com.example.dell.nirmatt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dell.nirmatt.DatePickerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TeacherTimetableActivity extends AppCompatActivity implements MyAdapter.Adder {


    static ArrayList s_rollno;
    static ArrayList s_check;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar mToolbar;
    FirebaseUser currentUser;
    String path;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_timetable);

        FloatingActionButton Next = findViewById(R.id.Next);
        mToolbar=findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Attendance Sheet");

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent I = new Intent(TeacherTimetableActivity.this,DatePickerActivity.class);
                startActivity(I);
                finish();
            }
        });

        recyclerView = findViewById(R.id.rcview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        for(int i=0;i<15;i++){
            ListItem listItem = new ListItem(
                    "Roll No." + (i+1)
            );
            listItems.add(listItem);
        }
        adapter = new MyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        Intent intent = getIntent();
        String day = intent.getExtras().getString("day");
        Toast.makeText(TeacherTimetableActivity.this,day,Toast.LENGTH_LONG).show();
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
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentUser.getUid();
        DocumentReference docref = db.collection("/faculty").document(uid).collection("/time_table").document(path);
        docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    DocumentSnapshot doc=task.getResult();

                    Object obj = doc.getData().get("1");
                    String slot=obj.toString();

                    //String[] stringArray = Arrays.copyOf(obj, 3, String[].class);
                    //timetable obj1=(timetable)obj;
                    /*timetable obj1=doc.toObject(timetable.class);
                    String slot=obj1.getClas();*/
                    Toast.makeText(TeacherTimetableActivity.this,slot,Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public void Add(ArrayList i_rollno, ArrayList check) {

        s_rollno=i_rollno;
        s_check=check;
    }
}
