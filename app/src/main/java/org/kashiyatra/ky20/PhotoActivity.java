package org.kashiyatra.ky20;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class PhotoActivity extends AppCompatActivity {
    private View background;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    public String scheduleVersion="0";
    public ImageView photoView;
    public String currentVersion;
    public  int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        day = intent.getIntExtra("day", 1);
        int placeholderId;
        String scheduleUrl;

        photoView = findViewById(R.id.photo_view);
        switch (day) {

            case 0:
                placeholderId = R.drawable.day0;
                scheduleUrl = "https://i.ibb.co/qdQyVXr/Day-0.jpg";
                getSupportActionBar().setTitle("Day 0");
                break;
            case 1:
                placeholderId = R.drawable.day1;
                scheduleUrl ="https://i.ibb.co/27C28JJ/Day-1.jpg";
                getSupportActionBar().setTitle("Day 1");
                break;
            case 2:
                placeholderId = R.drawable.day2;
                scheduleUrl = "https://i.ibb.co/jhTw3Kc/Day-2.jpg";
                getSupportActionBar().setTitle("Day 2");
                break;
            case 3:
                placeholderId = R.drawable.day3;
                scheduleUrl = "https://i.ibb.co/DMmGWKF/Day-3.jpg";
                getSupportActionBar().setTitle("Day 3");
                break;
            default:
                placeholderId = R.drawable.day0;
                scheduleUrl = "https://i.ibb.co/qdQyVXr/Day-0.jpg";
                getSupportActionBar().setTitle("Day 0");
        }

        currentVersion=getSharedPreferences("ky2020", this.MODE_PRIVATE).getString("scheduleVersionDay"+day, "0");




/*
        Glide.with(this)
                .load(scheduleUrl)
                .apply(new RequestOptions()
                        .fitCenter()
                        .dontAnimate()
                        .dontTransform())
                .into(photoView);*/

        background = findViewById(R.id.photo_id);

        if (savedInstanceState == null) {
            background.setVisibility(View.INVISIBLE);

            final ViewTreeObserver viewTreeObserver = background.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                });
            }

        }
    }

    private void circularRevealActivity() {
        int cx = background.getWidth()/2;
        int cy = background.getBottom() - getDips(250);

        float finalRadius = Math.max(background.getWidth(), background.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                background,
                cx,
                cy,
                0,
                finalRadius);

        circularReveal.setDuration(400);
        background.setVisibility(View.VISIBLE);
        circularReveal.start();

    }

    private int getDips(int dps) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                resources.getDisplayMetrics());
    }

    @Override
    protected void onStart() {
        super.onStart();
        getversion();
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = background.getWidth()/2;
            int cy = background.getBottom() - getDips(250);

            float finalRadius = Math.max(background.getWidth(), background.getHeight());
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(background, cx, cy, finalRadius, 0);

            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    background.setVisibility(View.INVISIBLE);
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            circularReveal.setDuration(400);
            circularReveal.start();
        }
        else {
            super.onBackPressed();
        }
    }
    public void getversion()
    {
        FirebaseDatabase.getInstance().getReference().child("schedule_version").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scheduleVersion=dataSnapshot.child("versionDay"+Integer.toString(day)).getValue().toString();
                Log.d("pActivity",scheduleVersion);
                loadImage();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void loadImage()
    {
        final File file= new File(this.getFilesDir(),"Day-"+day +".jpg");
        Log.d("pActivity",currentVersion+"curr");
        Log.d("pActivity",scheduleVersion+"schedule");
        Log.d("pActivity",""+file.exists());
        if(file.exists()&&currentVersion.equals(scheduleVersion))
        {

            photoView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
            Log.d("pActivity","exists");
        }
        else {

            Log.w("pActivity","doesnt_exist");
            storageRef.child("schedule/" +"Day-"+day+".jpg").getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    photoView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                    SharedPreferences.Editor editor =PhotoActivity.this.getSharedPreferences("ky2020", PhotoActivity.this.MODE_PRIVATE).edit();
                    editor.putString("scheduleVersionDay"+day,scheduleVersion);
                    editor.commit();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(PhotoActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                    Log.d("pActivity",""+exception);
                }
            });



        }
    }
}
