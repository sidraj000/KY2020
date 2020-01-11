package org.kashiyatra.ky20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.clans.fab.FloatingActionButton;

public class ProfileActivity extends AppCompatActivity {
    private boolean isLoggedIn;
    SharedPreferences prefs;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton5, floatingActionButton6,floatingActionButton7,floatingActionButton8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefs = getSharedPreferences(SplashActivity.storeUserDetails, Context.MODE_PRIVATE);
        isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        ImageView dpView = findViewById(R.id.profile_picture);
        TextView nameView = findViewById(R.id.username);
        TextView kyIdView = findViewById(R.id.ky_id);
        TextView collegeView = findViewById(R.id.college_name);

        if (isLoggedIn) {
            Glide.with(getApplicationContext())
                    .load(prefs.getString("profilePic", ""))
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.person)
                            .error(R.drawable.person)
                            .centerCrop()
                            .dontAnimate()
                            .dontTransform())
                    .into(dpView);
            nameView.setText(prefs.getString("fullName", "Guest user"));
            kyIdView.setText(prefs.getString("ky_id", "guestuser@kashiyatra.org"));
            collegeView.setText(prefs.getString("college", "IIT(BHU) Varanasi"));

        }

        floatingActionButton1 = findViewById(R.id.floating_team);
        floatingActionButton2 = findViewById(R.id.floating_merchandise);
        floatingActionButton5 = findViewById(R.id.floating_faq);
        floatingActionButton7=findViewById(R.id.floating_qrCode);
        floatingActionButton8=findViewById(R.id.floating_location);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), TeamActivity.class);
                startActivity(intent);
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MerchandiseActivity.class);
                startActivity(intent);
            }
        });
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FaqActivity.class);
                startActivity(intent);
            }
        });
        floatingActionButton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRcode.class);
                startActivity(intent);
            }
        });
        floatingActionButton8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        if (!isLoggedIn) {
            MenuItem logout = menu.findItem(R.id.action_logout);
            logout.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
         //   Logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    void Logout() {
//        LoginManager.getInstance().logOut();
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_left, R.anim.push_right);
        finish();
    }*/
}
