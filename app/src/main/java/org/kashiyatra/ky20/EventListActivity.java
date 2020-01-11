package org.kashiyatra.ky20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.kashiyatra.ky20.adapters.SubeventsAdapter;
import org.kashiyatra.ky20.utils.RecyclerItemClickListener;

public class EventListActivity extends AppCompatActivity {
    private int eventPosition;
    private String mEventName;
    private SharedPreferences mPrefs;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eventPosition = getIntent().getIntExtra("POSITION", 0);

        mEventName = getResources().getStringArray(R.array.event_names)[eventPosition];
        getSupportActionBar().setTitle(mEventName);
        TypedArray subeventBackground = getResources().obtainTypedArray(R.array.subevent_backgrounds);
        imageView = findViewById(R.id.background_image_view);

        mPrefs = getSharedPreferences(SplashActivity.storeUserDetails, Context.MODE_PRIVATE);

        String[] subeventNames;
        String[] subeventDescs;
        int placeholderId;
        String scheduleUrl;

        placeholderId = R.drawable.crosswindz_background;
        scheduleUrl = "https://i.ibb.co/dKrLVtk/MOB-CROSSWINDZ-high.jpg";


        int bgImage = getResources().obtainTypedArray(R.array.event_backgrounds).getResourceId(eventPosition, -1);
//        bgImageView.setImageResource(bgImage);
        switch (eventPosition) {
            case 1:
                subeventNames = getResources().getStringArray(R.array.bandish_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.bandish_subevent_short_description);
                placeholderId = R.drawable.bandish_background;
                scheduleUrl = "https://i.ibb.co/MsK1w0D/bandish-background.jpg";
                break;
            case 3:
                subeventNames = getResources().getStringArray(R.array.enquizta_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.enquizta_subevent_short_description);
                placeholderId = R.drawable.enquizta_background;
                scheduleUrl = "https://i.ibb.co/h7F1k7v/enquizta-background.jpg";
                break;
            case 4:
                subeventNames = getResources().getStringArray(R.array.mirage_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.mirage_subevent_short_description);
                placeholderId = R.drawable.mirage_background;
                scheduleUrl = "https://i.ibb.co/gWDrcGH/mirage-background.jpg";
                break;
            case 6:
                subeventNames = getResources().getStringArray(R.array.samwaad_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.samwaad_subevent_short_description);
                placeholderId = R.drawable.samvaad_background;
                scheduleUrl = "https://i.ibb.co/VqQfk9v/samwaad-background.jpg";
                break;
            case 0:
                subeventNames = getResources().getStringArray(R.array.abhinay_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.abhinay_subevent_short_description);
                placeholderId = R.drawable.abhinay_background;
                scheduleUrl = "https://i.ibb.co/dQ059BW/abhinay-background.jpg";
                break;
            case 7:
                subeventNames = getResources().getStringArray(R.array.toolika_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.toolika_subevent_short_description);
                placeholderId = R.drawable.toolika_background;
                scheduleUrl = "https://i.ibb.co/DpqHWPM/toolika-background.jpg";
                break;
            case 5:
                subeventNames = getResources().getStringArray(R.array.natraj_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.natraj_subevent_short_description);
                placeholderId = R.drawable.natraj_background;
                scheduleUrl = "https://i.ibb.co/RSkm6YY/natraj-background.jpg";
                break;
            case 2:
                subeventNames = getResources().getStringArray(R.array.crosswindz_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.crosswindz_subevent_short_description);
                placeholderId = R.drawable.crosswindz_background;
                scheduleUrl = "https://i.ibb.co/0m4hc6v/crosswindz-background.jpg";
                break;
            default:
                subeventNames = getResources().getStringArray(R.array.bandish_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.bandish_subevent_short_description);
        }


        ImageView photoView = findViewById(R.id.background_image_view);

    /*    Glide.with(this)
                .load(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
                        .fitCenter()
                        .dontAnimate()
                        .dontTransform())
                .into(photoView);*/
    Glide.with(this)
            .load(placeholderId)
            .into(imageView);

        RecyclerView mSubeventRecycler = findViewById(R.id.subevent_list_recycler_view);
        RecyclerView.LayoutManager mSubeventLayoutManager = new LinearLayoutManager(this);
        mSubeventRecycler.setLayoutManager(mSubeventLayoutManager);
        RecyclerView.Adapter mSubeventsAdapter = new SubeventsAdapter(getApplicationContext(), subeventNames, subeventDescs);

        mSubeventRecycler.setAdapter(mSubeventsAdapter);
        mSubeventRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                        intent.putExtra("EVENT_POSITION", eventPosition);
                        intent.putExtra("SUBEVENT_POSITION", position);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                    }
                })
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.subevent_list_options, menu);
        if (mPrefs.getBoolean(mEventName, false)) {
            menu.findItem(R.id.action_subscribe).setVisible(false);
            menu.findItem(R.id.action_unsubscribe).setVisible(true);
        } else {
            menu.findItem(R.id.action_subscribe).setVisible(true);
            menu.findItem(R.id.action_unsubscribe).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mPrefs.getBoolean(mEventName, false)) {
            menu.findItem(R.id.action_subscribe).setVisible(false);
            menu.findItem(R.id.action_unsubscribe).setVisible(true);
        } else {
            menu.findItem(R.id.action_subscribe).setVisible(true);
            menu.findItem(R.id.action_unsubscribe).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        int id = item.getItemId();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("hello", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast

                        Log.d("somehting", token);
                    }
                });

        switch (id) {
            case R.id.action_subscribe:
                FirebaseMessaging.getInstance().subscribeToTopic("general").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                mEditor.putBoolean(mEventName, true);
                Toast.makeText(this, "Subscribed to notifications for general" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_unsubscribe:
                FirebaseMessaging.getInstance().unsubscribeFromTopic(mEventName);
                mEditor.putBoolean(mEventName, false);
                Toast.makeText(this, "Unsubscribed from notifications for " + mEventName, Toast.LENGTH_SHORT).show();
                break;
        }
        mEditor.commit();
        supportInvalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }

}
