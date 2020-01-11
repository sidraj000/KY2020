package org.kashiyatra.ky20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import org.kashiyatra.ky20.adapters.ImageAdapter;
import org.kashiyatra.ky20.services.UploadImage;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivty extends AppCompatActivity  implements ImageAdapter.onImageClicked{
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<UploadImage> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_activty);



        initializeViews();
        ImageView imageView=findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        getData();

    }
    private void getData() {
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadImage upload = postSnapshot.getValue(UploadImage.class);


                    mUploads.add(upload);

                }


                mAdapter = new ImageAdapter(getApplicationContext(), mUploads, GalleryActivty.this);
                mProgressCircle.setVisibility(View.INVISIBLE);

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }
    private void initializeViews() {

        mUploads = new ArrayList<>();
        mProgressCircle = findViewById(R.id.progress_circle);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        int width = size. x;
        int height = size. y;
        Log. e("Width", "" + width);
        Log. e("height", "" + height);
        int widt=width*7/8;
        final CardSliderLayoutManager cardSliderLayoutManager=new CardSliderLayoutManager(50,widt,12);
        mRecyclerView.setLayoutManager(cardSliderLayoutManager);
        new CardSnapHelper().attachToRecyclerView(mRecyclerView);
        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int ii= cardSliderLayoutManager.getActiveCardPosition();
                int animH[] = new int[] {R.anim.anim, R.anim.anim2};

                final boolean left2right = i3 < i2;
                if (left2right) {
                    animH[0] = R.anim.anim2;
                    animH[1] = R.anim.anim;


                }


            }
        });

    }
    @Override
    public void onImageClicked(int pos) {
        Intent intent=new Intent(getApplicationContext(),fullImage.class);
        intent.putExtra("url",mUploads.get(pos).getImageUrl());
        startActivity(intent);
    }
}
