package com.example.dell.nirmatt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private EditText nemail,npassword;
    private Button nloginbtn;
    private FirebaseAuth mAuth;
    private ProgressDialog loginprogress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nemail=findViewById(R.id.login_email);
        mAuth = FirebaseAuth.getInstance();
        loginprogress = new ProgressDialog(this);
        npassword=findViewById(R.id.login_password);
        nloginbtn=findViewById(R.id.login_login_btn);
        nloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=nemail.getText().toString();
                String password=npassword.getText().toString();
                char arr[]=new char[1];
                email.getChars(0,1,arr,0);
                int ch=(int)arr[0];

                if((!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password))){
                    loginprogress.setTitle("Logging in");
                    loginprogress.setMessage("Please wait while we check your credentials");
                    loginprogress.setCanceledOnTouchOutside(false);
                    loginprogress.show();
                    login(email,password,ch);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login failed. Please check your id and password",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public void login(String email,String password,int ch)
    {
        final int a=ch;
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && (a>=48 && a<=57)) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            loginprogress.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent login_intent = new Intent(LoginActivity.this,Main3Activity.class);
                            login_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(login_intent);
                            finish();
                            //updateUI(user);
                        }
                        else if(task.isSuccessful() && (a>=97 && a<=122)){
                            loginprogress.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent login2_intent = new Intent(LoginActivity.this,MainActivity.class);
                            login2_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(login2_intent);
                            finish();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            loginprogress.hide();
                            Toast.makeText(LoginActivity.this, "Login failed. Please check your id and password",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
