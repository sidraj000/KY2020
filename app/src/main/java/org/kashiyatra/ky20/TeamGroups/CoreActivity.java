package org.kashiyatra.ky20.TeamGroups;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.TeamGroupsAdapter.CoreTeamAdapter;

public class CoreActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        String[] names = getResources().getStringArray(R.array.team_names_core);
        String[] roles = getResources().getStringArray(R.array.team_roles_core);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls_core);
        String[] emails = getResources().getStringArray(R.array.team_emails_core);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks_core);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls_core);

        RecyclerView mCoreTeamRecyclerView = findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mCoreLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mCoreTeamAdapter = new CoreTeamAdapter(CoreActivity.this, names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mCoreTeamRecyclerView.setLayoutManager(mCoreLayoutManager);
        mCoreTeamRecyclerView.setAdapter(mCoreTeamAdapter);
    }
}
