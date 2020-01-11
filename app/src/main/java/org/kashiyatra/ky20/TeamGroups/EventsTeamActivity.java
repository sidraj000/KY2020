package org.kashiyatra.ky20.TeamGroups;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.TeamGroupsAdapter.EventsTeamAdapter;

public class EventsTeamActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        String[] names = getResources().getStringArray(R.array.team_names_events);
        String[] roles = getResources().getStringArray(R.array.team_roles_events);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls_events);
        String[] emails = getResources().getStringArray(R.array.team_emails_events);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks_events);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls_events);

        RecyclerView mEventsTeamRecyclerView = findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mEventsTeamLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mEventsTeamAdapter = new EventsTeamAdapter(EventsTeamActivity.this, names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mEventsTeamRecyclerView.setLayoutManager(mEventsTeamLayoutManager);
        mEventsTeamRecyclerView.setAdapter(mEventsTeamAdapter);
    }
}
