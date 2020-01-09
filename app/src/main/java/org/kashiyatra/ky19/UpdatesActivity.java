package org.kashiyatra.ky19;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kashiyatra.ky19.adapters.ImageAdapter;
import org.kashiyatra.ky19.adapters.UpdateAdapter;
import org.kashiyatra.ky19.fragments.GalleryFragment;
import org.kashiyatra.ky19.services.UploadImage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class UpdatesActivity extends AppCompatActivity {
    RecyclerView updateRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    SharedPreferences prefs;
    SharedPreferences.Editor prefEditor;
    DatabaseReference mDatabaseRef;
    ArrayList<UpdatesModel>updatesModels;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Updates");
        updatesModels=new ArrayList<>();

        prefs = getSharedPreferences(SplashActivity.storeUserDetails, Context.MODE_PRIVATE);
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark);
        updateRecyclerView = findViewById(R.id.update_recycler);
        RecyclerView.LayoutManager updateLinearLayoutManager = new LinearLayoutManager(this);
        updateRecyclerView.setLayoutManager(updateLinearLayoutManager);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UpdatesModel upload = postSnapshot.getValue(UpdatesModel.class);


                    updatesModels.add(upload);

                }


               mSwipeRefreshLayout.setRefreshing(false);

                updateRecyclerView.setAdapter(new UpdateAdapter(updatesModels,getApplicationContext()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

    }

}
