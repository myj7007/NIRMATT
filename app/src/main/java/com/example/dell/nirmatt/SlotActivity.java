package com.example.dell.nirmatt;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SlotActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListSlot> listSlots;
    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot);

        Intent intent = getIntent();
        final String day = intent.getExtras().getString("day");

        mToolbar=findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Select Slot");

        recyclerView = findViewById(R.id.rcview3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter3(SlotActivity.this , day);
        /*listSlots=new ArrayList<>();
        for(int i=0;i<6;i++){
            ListSlot listSlot = new ListSlot(
                    "Class" + (i+1),
                    "Timeslot" + (i+1),
                    "Subject" + (i+1)

            );
            listSlots.add(listSlot);
        }*/
        //adapter = new MyAdapter3(listSlots,this);
        recyclerView.setAdapter(adapter);

    }
}
