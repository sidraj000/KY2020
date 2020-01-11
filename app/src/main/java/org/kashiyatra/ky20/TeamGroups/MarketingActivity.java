package org.kashiyatra.ky20.TeamGroups;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.TeamGroupsAdapter.MarketingTeamAdapter;

public class MarketingActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        String[] names = getResources().getStringArray(R.array.team_names_marketing);
        String[] roles = getResources().getStringArray(R.array.team_roles_marketing);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls_marketing);
        String[] emails = getResources().getStringArray(R.array.team_emails_marketing);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks_marketing);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls_markting);

        RecyclerView mMarketingTeamRecyclerView = findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mMarketingLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mMarketingTeamAdapter = new MarketingTeamAdapter(MarketingActivity.this, names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mMarketingTeamRecyclerView.setLayoutManager(mMarketingLayoutManager);
        mMarketingTeamRecyclerView.setAdapter(mMarketingTeamAdapter);
    }
}
