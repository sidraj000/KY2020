package org.kashiyatra.ky20.TeamGroups;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.TeamGroupsAdapter.CreativeTeamAdapter;

public class CreativeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        String[] names = getResources().getStringArray(R.array.team_names_creative);
        String[] roles = getResources().getStringArray(R.array.team_roles_creative);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls_creative);
        String[] emails = getResources().getStringArray(R.array.team_emails_creative);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks_creative);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls_creative);

        RecyclerView mCreativeTeamRecyclerView = findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mCreativeLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mCreativeTeamAdapter = new CreativeTeamAdapter(CreativeActivity.this, names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mCreativeTeamRecyclerView.setLayoutManager(mCreativeLayoutManager);
        mCreativeTeamRecyclerView.setAdapter(mCreativeTeamAdapter);
    }
}
