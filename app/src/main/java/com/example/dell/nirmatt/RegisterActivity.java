package com.example.dell.nirmatt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText nemail , npassword;
    private Button ncreatebtn;
    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        nemail=findViewById(R.id.reg_email);
        npassword=findViewById(R.id.reg_password);
        ncreatebtn=findViewById(R.id.reg_create_btn);
        ncreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=nemail.getText().toString();
                String password=npassword.getText().toString();
                create(email,password);
            }
        });
    }

   public void create(String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                         //   Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid=user.getUid();
                            mdatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                            HashMap<String, String> usermap=new HashMap<>();
                            usermap.put("image","default");
                            usermap.put("thumbimage","default");

                            mdatabase.setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent create_intent=new Intent(RegisterActivity.this,Main3Activity.class);
                                        create_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(create_intent);
                                        finish();
                                    }
                                }
                            });


                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // ...
                    }
                });

    }
}
