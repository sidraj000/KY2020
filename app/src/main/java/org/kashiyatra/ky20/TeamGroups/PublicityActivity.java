package org.kashiyatra.ky20.TeamGroups;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.TeamGroupsAdapter.PublicityTeamAdapter;

public class PublicityActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        String[] names = getResources().getStringArray(R.array.team_names_publicity);
        String[] roles = getResources().getStringArray(R.array.team_roles_publicity);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls_publicity);
        String[] emails = getResources().getStringArray(R.array.team_emails_publicity);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks_publicity);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls_publicity);

        RecyclerView mPublicityTeamRecyclerView = findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mPublicityLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mPublicityTeamAdapter = new PublicityTeamAdapter(PublicityActivity.this, names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mPublicityTeamRecyclerView.setLayoutManager(mPublicityLayoutManager);
        mPublicityTeamRecyclerView.setAdapter(mPublicityTeamAdapter);
    }
}
