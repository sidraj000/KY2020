package org.kashiyatra.ky20.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import org.kashiyatra.ky20.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionFragment extends Fragment {

    private static final String ARG_EVENT_POSITION = "event_position", ARG_SUBEVENT_POSITION = "subevent_position", ARG_COLOR = "color";
    private int mEventPosition;
    private int mSubeventPosition;
    private int mColor;


    public DescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param eventPosition Event Position
     * @param subeventPosition Subevent Position
     * @return A new instance of fragment DescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionFragment newInstance(int eventPosition, int subeventPosition, int color) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EVENT_POSITION, eventPosition);
        args.putInt(ARG_SUBEVENT_POSITION, subeventPosition);
        args.putInt(ARG_COLOR, color);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEventPosition = getArguments().getInt(ARG_EVENT_POSITION);
            mSubeventPosition = getArguments().getInt(ARG_SUBEVENT_POSITION);
            mColor = getArguments().getInt(ARG_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_description, container, false);
        WebView descriptionTextView = rootView.findViewById(R.id.description_text_view);
        descriptionTextView.setBackgroundColor(getResources().getColor(R.color.translucent));

        NestedScrollView nestedScrollView = rootView.findViewById(R.id.description_scroll_view);
        nestedScrollView.setBackgroundColor(mColor);

        String description;
        switch (mEventPosition) {
            case 1:
                description = getResources().getStringArray(R.array.bandish_subevent_abouts)[mSubeventPosition];
                break;
            case 3:
                description = getResources().getStringArray(R.array.enquizta_subevent_abouts)[mSubeventPosition];
                break;
            case 4:
                description = getResources().getStringArray(R.array.mirage_subevent_abouts)[mSubeventPosition];
                break;
            case 6:
                description = getResources().getStringArray(R.array.samwaad_subevent_abouts)[mSubeventPosition];
                break;
            case 0:
                description = getResources().getStringArray(R.array.abhinay_subevent_abouts)[mSubeventPosition];
                break;
            case 7:
                description = getResources().getStringArray(R.array.toolika_subevent_abouts)[mSubeventPosition];
                break;
            case 5:
                description = getResources().getStringArray(R.array.natraj_subevent_abouts)[mSubeventPosition];
                break;
            case 2:
                description = getResources().getStringArray(R.array.crosswindz_subevent_abouts)[mSubeventPosition];
                break;
            default:
                description = getResources().getStringArray(R.array.bandish_subevent_abouts)[mSubeventPosition];
                break;

        }
        descriptionTextView.loadData(description, "text/html", "utf-8");
        return rootView;
    }

}
