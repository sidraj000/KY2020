package org.kashiyatra.ky20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class fullImage extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        imageView=findViewById(R.id.image);
        Intent intent=getIntent();
        String url= intent.getStringExtra("url");
        Picasso.get()
                .load(url)
                .fit()
                .into(imageView);
    }

}
