package org.kashiyatra.ky20.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import org.kashiyatra.ky20.ConcertsActivity;
import org.kashiyatra.ky20.PhotoActivity;
import org.kashiyatra.ky20.R;

public class ScheduleFragment extends Fragment {

    String[] array_name = {"facebook","twitter","youtube","windows","drive"};
    int selectedIndex;


    public ScheduleFragment() {
        // Required empty public constructor
    }


    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        CircleMenu circlemenu = (CircleMenu)rootView.findViewById(R.id.circle_menu);
        circlemenu.setMainMenu(getResources().getColor(R.color.colorPrimary),R.drawable.ic_launcher_menu,R.drawable.day)
                .addSubMenu(Color.parseColor("#355c7d"),R.drawable.circle_menu)
                .addSubMenu(Color.parseColor("#6c5b7b"),R.drawable.day0_icon)
                .addSubMenu(Color.parseColor("#c06c84"),R.drawable.day1_icon)
                .addSubMenu(Color.parseColor("#f67280"),R.drawable.day2_icon)
                .addSubMenu(Color.parseColor("#c69176"),R.drawable.day3_icon)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                       selectedIndex = i+1;
                    }
                    }
                );

        circlemenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {
                switch (selectedIndex) {
                    case 1: Intent intent = new Intent(getActivity(), ConcertsActivity.class);
                        startActivity(intent);
                        selectedIndex=0;
                    break;
                    case 2: Intent intent1 = new Intent(getActivity(), PhotoActivity.class);
                        intent1.putExtra("day", 0);
                        startActivity(intent1);
                        selectedIndex=0;
                    break;
                    case 3:Intent intent2 = new Intent(getActivity(), PhotoActivity.class);
                        intent2.putExtra("day", 1);
                        startActivity(intent2);
                        selectedIndex=0;
                    break;
                    case 4: Intent intent3 = new Intent(getActivity(), PhotoActivity.class);
                        intent3.putExtra("day", 2);
                        startActivity(intent3);
                        selectedIndex=0;
                    break;
                    case 5: Intent intent4 = new Intent(getActivity(), PhotoActivity.class);
                        intent4.putExtra("day", 3);
                        startActivity(intent4);
                        selectedIndex=0;
                    break;
                }
            }
        });


        return rootView;
    }
}
