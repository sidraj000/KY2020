package org.kashiyatra.ky20.TeamGroups;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.TeamGroupsAdapter.TechTeamAdapter;

public class TechActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        String[] names = getResources().getStringArray(R.array.team_names_tech);
        String[] roles = getResources().getStringArray(R.array.team_roles_tech);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls_tech);
        String[] emails = getResources().getStringArray(R.array.team_emails_tech);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks_tech);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls_tech);

        RecyclerView mTechTeamRecyclerView = findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mTechLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mTechTeamAdapter = new TechTeamAdapter(TechActivity.this, names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mTechTeamRecyclerView.setLayoutManager(mTechLayoutManager);
        mTechTeamRecyclerView.setAdapter(mTechTeamAdapter);
    }
}
