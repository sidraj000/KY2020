package org.kashiyatra.ky20.TeamGroups;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.TeamGroupsAdapter.PubRelTeamAdapter;

public class PubRelActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        String[] names = getResources().getStringArray(R.array.team_names_pubrel);
        String[] roles = getResources().getStringArray(R.array.team_roles_pubrel);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls_pubrel);
        String[] emails = getResources().getStringArray(R.array.team_emails_pubrel);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks_pubrel);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls_pubrel);

        RecyclerView mPubRelTeamRecyclerView = findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mPubRelLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mPubRelTeamAdapter = new PubRelTeamAdapter(PubRelActivity.this, names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mPubRelTeamRecyclerView.setLayoutManager(mPubRelLayoutManager);
        mPubRelTeamRecyclerView.setAdapter(mPubRelTeamAdapter);
    }
}
