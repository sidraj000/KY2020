package org.kashiyatra.ky20;


import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

public class HelplineActivity extends AppCompatActivity {

    FloatingActionButton materialDesignFAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.activity_helpline);

        materialDesignFAM = findViewById(R.id.mail_button);
        materialDesignFAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"kashiyatra@iitbhu.ac.in"});
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        ""); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "" + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Mail to Kashiyatra'19"));
            }
        });

    }
    public void call(View v) {
        String uri = "tel:" + ((TextView) v).getText();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void mailToKy(View view) {
        String[] email = new String[]{"kashiyatra@iitbhu.ac.in"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding Kashiyatra");
        startActivity(intent);
    }
}

