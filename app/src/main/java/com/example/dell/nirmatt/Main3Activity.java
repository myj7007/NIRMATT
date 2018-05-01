package com.example.dell.nirmatt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private DatabaseReference nuserdatabase;
    private FirebaseUser ncurrentuser;
    private ImageView ntimetable;
    private TextView nname;
    private TextView nrollno;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void onStart() {
        super.onStart();
        //Toast.makeText(Main3Activity.this,"hesdbdfbdb",Toast.LENGTH_LONG).show();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            sendToStart();

        }
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentUser.getUid();
        DocumentReference docref = db.collection("/user").document(uid);
        docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(Main3Activity.this,"hccfcfh",Toast.LENGTH_LONG).show();
                    DocumentSnapshot doc=task.getResult();
                    String type=doc.get("type").toString();
                    //Toast.makeText(Main3Activity.this,type,Toast.LENGTH_LONG).show();
                    if(type.equals("faculty")){
                        Intent i=new Intent(Main3Activity.this,MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }

    public void fetchdata(){


        FirebaseUser currentUser = mAuth.getCurrentUser();
        ncurrentuser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = ncurrentuser.getUid();

        DocumentReference docRef = db.collection("/class/6ceb/student").document(current_uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot document = task.getResult();
                    nrollno.setText(document.get("rollno").toString());
                    nname.setText(document.get("name").toString());
                    String image = document.get("time_table").toString();
                    Glide.with(getApplicationContext()).load(image).into(ntimetable);
                    Toast.makeText(Main3Activity.this, "Hello "+document.get("rollno"), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ntimetable=findViewById(R.id.imageView_timetable);
        nrollno=findViewById(R.id.text_rollno);
        nname=findViewById(R.id.text_name);

        mAuth = FirebaseAuth.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            sendToStart();

        }
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentUser.getUid();
        DocumentReference docref = db.collection("/user").document(uid);
        docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(Main3Activity.this,"hccfcfh",Toast.LENGTH_LONG).show();
                    DocumentSnapshot doc=task.getResult();
                    String type=doc.get("type").toString();
                    //Toast.makeText(Main3Activity.this,type,Toast.LENGTH_LONG).show();
                    if(type.equals("faculty")){
                        Intent i=new Intent(Main3Activity.this,MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    }
                    else{
                        fetchdata();
                    }
                }
            }
        });




        /*FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {

            ncurrentuser = FirebaseAuth.getInstance().getCurrentUser();
            String current_uid = ncurrentuser.getUid();

            nuserdatabase = FirebaseDatabase.getInstance().getReference().child("users").child("student").child(current_uid);
            nuserdatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String mname = dataSnapshot.child("name").getValue().toString();
                    String rollno = dataSnapshot.child("rollno").getValue().toString();
                    String image = dataSnapshot.child("time_table").getValue().toString();

                    nname.setText(mname);
                    nrollno.setText(rollno);
                    Glide.with(getApplicationContext()).load(image).into(ntimetable);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    private void sendToStart(){
        Intent start_Intent = new Intent(Main3Activity.this,StartActivity.class);
        startActivity(start_Intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profile_intent= new Intent(Main3Activity.this,ProfileActivity.class);
            startActivity(profile_intent);

            // Handle the camera action
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            sendToStart();

        } else if (id == R.id.nav_viewattendance) {
            Intent attendance_intent= new Intent(Main3Activity.this,ViewattendanceActivity.class);
            startActivity(attendance_intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
