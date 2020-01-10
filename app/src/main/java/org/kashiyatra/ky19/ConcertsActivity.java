package org.kashiyatra.ky19;

import android.animation.Animator;
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

public class ConcertsActivity extends AppCompatActivity {

    private View background;
    public String scheduleVersion="0";
    public ImageView photoView;
    public String currentVersion;

    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.activity_concerts);
        background = findViewById(R.id.concerts);
        photoView=findViewById(R.id.photo_view);
        currentVersion=getSharedPreferences("ky2020", this.MODE_PRIVATE).getString("schedulePronite", "0");



       /* if (savedInstanceState == null) {
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
*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        getversion();
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
                scheduleVersion=dataSnapshot.child("versionPronite").getValue().toString();
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
        final File file= new File(this.getFilesDir(),"pronite"+".jpg");
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
            storageRef.child("schedule/"+"pronite"+".jpeg").getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    photoView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                    SharedPreferences.Editor editor =ConcertsActivity.this.getSharedPreferences("ky2020", ConcertsActivity.this.MODE_PRIVATE).edit();
                    editor.putString("schedulePronite",scheduleVersion);
                    editor.commit();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(ConcertsActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                    Log.d("pActivity",""+exception);
                }
            });



        }
    }
}
