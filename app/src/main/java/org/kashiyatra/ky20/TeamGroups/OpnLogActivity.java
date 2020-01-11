package org.kashiyatra.ky20.TeamGroups;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.TeamGroupsAdapter.OpnLogTeamAdapter;

public class OpnLogActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        String[] names = getResources().getStringArray(R.array.team_names_opnlog);
        String[] roles = getResources().getStringArray(R.array.team_roles_opnlog);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls_opnlog);
        String[] emails = getResources().getStringArray(R.array.team_emails_opnlog);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks_opnlog);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls_opnlog);

        RecyclerView mOpnLogTeamRecyclerView = findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mOpnLogLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mOpnLogTeamAdapter = new OpnLogTeamAdapter(OpnLogActivity.this, names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mOpnLogTeamRecyclerView.setLayoutManager(mOpnLogLayoutManager);
        mOpnLogTeamRecyclerView.setAdapter(mOpnLogTeamAdapter);
    }
}
