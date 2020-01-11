package org.kashiyatra.ky20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {
ImageView ivMerchandise,ivQrcode,ivFaq,ivTeamMemebers,ivEvents,ivProfile;
FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ivMerchandise=findViewById(R.id.ivMerchandise);
        ivQrcode=findViewById(R.id.ivQrcode);
        user= FirebaseAuth.getInstance().getCurrentUser();
        ivFaq=findViewById(R.id.ivFaq);
        ivTeamMemebers=findViewById(R.id.ivTeamMembers);
        ivEvents=findViewById(R.id.btnEventProfile);
        ivProfile=findViewById(R.id.ivProfile);
        if(user!=null) {
            Glide.with(this)
                    .load(user.getPhotoUrl())
                    .apply(new RequestOptions()
                            .fitCenter()
                            .dontAnimate()
                            .dontTransform())
                    .into(ivProfile);
        }
        ivEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, EventsActivity.class));

            }
        });
        ivMerchandise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, MerchandiseActivity.class));
            }
        });
        ivFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, FaqActivity.class));
            }
        });
        ivTeamMemebers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, TeamActivity.class));
            }
        });
        ivQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,QRcode.class));
            }
        });
    }
}
