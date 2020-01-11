package org.kashiyatra.ky20;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import org.kashiyatra.ky20.inner.InnerData;
import org.kashiyatra.ky20.outer.OuterAdapter;
import org.kashiyatra.ky20.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class newEventsFragment extends Fragment {

    String[] subeventName;
    String[] eventName;
    String[] eventDetails;
    String[] subEventDetails;
    String[] subEventDescriptions;
    TypedArray eventBanner;
    TypedArray backgrounds;
View view;
    TypedArray icons;

    private final static int OUTER_COUNT = 8;
    public newEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view= inflater.inflate(R.layout.fragment_events2, container, false);
        final List<List<InnerData>> outerData = new ArrayList<>();
        icons = getResources().obtainTypedArray(R.array.event_icons);

        eventName= getResources().getStringArray(R.array.event_names);
        eventDetails=getResources().getStringArray(R.array.event_descriptions);
        backgrounds = getResources().obtainTypedArray(R.array.event_backgrounds);

        for (int i = 0; i < OUTER_COUNT ; i++) {
            final List<InnerData> innerData = new ArrayList<>();
            innerData.add(new InnerData(
                    eventName[i],
                    eventDetails[i],
                    "varanasi " + "UP",
                    getContext().getDrawable(icons.getResourceId(i, -1)),
                    getResources().getColor(R.color.no1)


            ));
            switch (i) {
                case 1:
                    subeventName = getResources().getStringArray(R.array.bandish_subevent_names);
                    eventBanner = getResources().obtainTypedArray(R.array.bandish_subevent_images);
                    subEventDescriptions= getResources().getStringArray(R.array.bandish_subevent_short_description);
                    break;
                case 3:
                    subeventName = getResources().getStringArray(R.array.enquizta_subevent_names);
                    eventBanner = getResources().obtainTypedArray(R.array.enquizta_subevent_images);
                    subEventDescriptions= getResources().getStringArray(R.array.enquizta_subevent_short_description);
                    break;
                case 4:
                    subeventName = getResources().getStringArray(R.array.mirage_subevent_names);
                    eventBanner = getResources().obtainTypedArray(R.array.mirage_subevent_images);

                    subEventDescriptions= getResources().getStringArray(R.array.mirage_subevent_short_description);
                    break;
                case 6:
                    subeventName = getResources().getStringArray(R.array.samwaad_subevent_names);
                    eventBanner = getResources().obtainTypedArray(R.array.samwaad_subevent_images);
                    subEventDescriptions= getResources().getStringArray(R.array.samwaad_subevent_short_description);
                    break;
                case 0:
                    subeventName = getResources().getStringArray(R.array.abhinay_subevent_names);
                    eventBanner = getResources().obtainTypedArray(R.array.abhinay_subevent_images);
                    subEventDescriptions= getResources().getStringArray(R.array.abhinay_subevent_short_description);
                    break;
                case 7:
                    subeventName = getResources().getStringArray(R.array.toolika_subevent_names);
                    eventBanner = getResources().obtainTypedArray(R.array.toolika_subevent_images);
                    subEventDescriptions= getResources().getStringArray(R.array.toolika_subevent_short_description);
                    break;
                case 5:
                    subeventName = getResources().getStringArray(R.array.natraj_subevent_names);
                    eventBanner = getResources().obtainTypedArray(R.array.natraj_subevent_images);
                    subEventDescriptions= getResources().getStringArray(R.array.natraj_subevent_short_description);
                    break;
                case 2:
                    subeventName = getResources().getStringArray(R.array.crosswindz_subevent_names);
                    eventBanner = getResources().obtainTypedArray(R.array.crosswindz_subevent_images);
                    subEventDescriptions= getResources().getStringArray(R.array.crosswindz_subevent_short_description);
                    break;
            }
            for (int j = 0; j < subeventName.length ; j++) {
                innerData.add(new InnerData(
                        subeventName[j],
                        subEventDescriptions[j],
                        "varanasi " + "UP",
                        getContext().getDrawable(eventBanner.getResourceId(j, -1)),
                        j
                ));
            }
            outerData.add(innerData);
        }
       initRecyclerView(outerData);
        return view;
    }
    private void initRecyclerView(List<List<InnerData>> data) {
       view. findViewById(R.id.progressBar).setVisibility(View.GONE);

        final TailRecyclerView rv = (TailRecyclerView)view. findViewById(R.id.recycler_view);
        ((TailLayoutManager)rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setAdapter(new OuterAdapter(data));
        rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), EventListActivity.class);
                        intent.putExtra("POSITION", position);
                        startActivity(intent);

                    }
                })
        );
        new TailSnapHelper().attachToRecyclerView(rv);
    }


}
