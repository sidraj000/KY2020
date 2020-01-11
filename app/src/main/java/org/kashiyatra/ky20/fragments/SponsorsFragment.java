package org.kashiyatra.ky20.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.kashiyatra.ky20.Objects.Sponsors;
import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.adapters.SponsorAdapter;

import java.util.Arrays;
import java.util.List;


public class SponsorsFragment extends Fragment {

    public DatabaseReference ref;
    public SponsorsFragment() {
        // Required empty public constructor
    }


    public static SponsorsFragment newInstance() {
        SponsorsFragment fragment = new SponsorsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sponsors, container, false);
       ref=FirebaseDatabase.getInstance().getReference();
      String[] types = getResources().getStringArray(R.array.sponsor_types);
        String[] logoUrls = getResources().getStringArray(R.array.sponsor_logo_urls);

        for(int i=0;i<types.length;i++)
        {
           ref.child("sponsors").child(Integer.toString(i)).setValue(new Sponsors(types[i],logoUrls[i]));
        }
        RecyclerView mSponsorRecyclerView = rootView.findViewById(R.id.sponsor_recycler);
        GridLayoutManager mSponsorLayoutManager = new GridLayoutManager(getActivity(), 2);
        mSponsorLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                List<Integer> bigSponsorIndices = Arrays.asList(0, 1);
                return bigSponsorIndices.contains(position) ? 2 : 1;
            }
        });
        RecyclerView.Adapter mSponsorAdapter = new SponsorAdapter(getContext());

        mSponsorRecyclerView.setLayoutManager(mSponsorLayoutManager);
        mSponsorRecyclerView.setAdapter(mSponsorAdapter);

        return rootView;
    }

}
