package com.example.dell.nirmatt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewattendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListAttendance> listItems;
    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewattendance);

        mToolbar=findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Attendance");

        recyclerView = findViewById(R.id.rcview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*listItems = new ArrayList<>();
        for(int i=0;i<6;i++){
            ListAttendance listItem = new ListAttendance(
                    "Subject " + (i+1),0,0
            );
            listItems.add(listItem);
        }*/
        adapter = new MyAdapter2(ViewattendanceActivity.this);
        recyclerView.setAdapter(adapter);

    }
}
