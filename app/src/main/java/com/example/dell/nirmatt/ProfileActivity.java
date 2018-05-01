package com.example.dell.nirmatt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference mdatabase;
    private static final int gallery_pick=1;
    private Button nimagebtn;
    private StorageReference mStorageRef;
    private FirebaseUser ncurrentuser;
    private ProgressDialog nprogress;
    private CircleImageView nimageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        nimagebtn=findViewById(R.id.change_image_btn);
        nimageview=findViewById(R.id.profile_image);
        ncurrentuser = FirebaseAuth.getInstance().getCurrentUser();
        String uid=ncurrentuser.getUid();
        mdatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String image = dataSnapshot.child("image").getValue().toString();
                //String thumbimage = dataSnapshot.child("thumbimage").getValue().toString();
                //Picasso.with(ProfileActivity.this).load(image).error(R.drawable.defaultimage).into(nimageview);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        nimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent gallery_intent = new Intent();
                gallery_intent.setType("image/*");
                gallery_intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery_intent,"Select Image"),gallery_pick);*/
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(ProfileActivity.this);

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                nprogress = new ProgressDialog(ProfileActivity.this);
                nprogress.setTitle("Uploading");
                nprogress.setMessage("Please Wait");
                nprogress.setCanceledOnTouchOutside(false);
                nprogress.show();
                Uri resultUri = result.getUri();
                String current_uid=ncurrentuser.getUid();


                StorageReference filepath = mStorageRef.child("profile_images").child(current_uid +".jpg");

                filepath.putFile(resultUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                String url= taskSnapshot.getDownloadUrl().toString();
                                mdatabase.child("image").setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            nprogress.dismiss();
                                            Toast.makeText(getApplicationContext(),"Profile picture changed",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                //Glide.with(getApplicationContext()).load(downloadUrl).into(nimageview);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                nprogress.dismiss();
                                Toast.makeText(ProfileActivity.this,"Error in uploading",Toast.LENGTH_SHORT).show();
                                // Handle unsuccessful uploads
                                // ...
                            }
                        });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(20);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
