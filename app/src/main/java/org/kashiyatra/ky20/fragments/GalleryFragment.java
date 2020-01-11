package org.kashiyatra.ky20.fragments;


import android.graphics.Point;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;


import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.adapters.ImageAdapter;
import org.kashiyatra.ky20.services.UploadImage;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment implements ImageAdapter.onImageClicked{
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    View v;
    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<UploadImage> mUploads;


    public GalleryFragment() {
        // Required empty public constructor
    }


    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_gallery, container, false);

        initializeViews();


        getData();

        return v;







    }
    private void getData() {
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadImage upload = postSnapshot.getValue(UploadImage.class);


                    mUploads.add(upload);

                }


                mAdapter = new ImageAdapter(getActivity().getApplicationContext(), mUploads,GalleryFragment.this);
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
        mProgressCircle = v.findViewById(R.id.progress_circle);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");




        mRecyclerView = v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        Display display = getActivity().getWindowManager(). getDefaultDisplay();
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

    }
}
