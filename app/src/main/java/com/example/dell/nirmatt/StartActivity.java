package com.example.dell.nirmatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button nregbtn;
    private Button nstudentloginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        nregbtn=findViewById(R.id.start_reg_btn);
        nstudentloginbtn=findViewById(R.id.start_login_btn);

        nregbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent reg_intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(reg_intent);
            }
        });

        nstudentloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studentlogin_intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(studentlogin_intent);
            }
        });

    }
}
