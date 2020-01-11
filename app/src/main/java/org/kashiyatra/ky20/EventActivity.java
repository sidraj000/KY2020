package org.kashiyatra.ky20;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.appcompat.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import org.kashiyatra.ky20.fragments.DescriptionFragment;
import org.kashiyatra.ky20.fragments.RulesFragment;

import static java.lang.Math.min;


public class  EventActivity extends AppCompatActivity {

    private TabsPagerAdapter mTabsPagerAdapter;
    private ViewPager mViewPager;
    private AppBarLayout appBarLayout;
    private TabLayout mTabLayout;
    private int eventPosition;
    private int subeventPosition;
    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        eventPosition = getIntent().getIntExtra("EVENT_POSITION", 0);
        subeventPosition = getIntent().getIntExtra("SUBEVENT_POSITION", 0);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        final int screenWidth = displaymetrics.widthPixels;

        appBarLayout = findViewById(R.id.app_bar);
        final TabLayout tabLayout = findViewById(R.id.event_tabs);
        appBarLayout.post(new Runnable() {
            @Override
            public void run() {
                int heightPx = screenWidth * 1 / 3;
                setAppBarOffset(heightPx);
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float range = appBarLayout.getTotalScrollRange();
                float initial = range - screenWidth * 1 / 3;
                float alpha = min((range + verticalOffset) / initial, 1);
                (findViewById(R.id.event_banner)).setAlpha(alpha);
            }
        });

        String subeventName;
        int eventBanner;

        switch (eventPosition) {
            case 1:
                subeventName = getResources().getStringArray(R.array.bandish_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.bandish_subevent_images).getResourceId(subeventPosition, -1);
                break;
            case 3:
                subeventName = getResources().getStringArray(R.array.enquizta_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.enquizta_subevent_images).getResourceId(subeventPosition, -1);
                break;
            case 4:
                subeventName = getResources().getStringArray(R.array.mirage_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.mirage_subevent_images).getResourceId(subeventPosition, -1);
                break;
            case 6:
                subeventName = getResources().getStringArray(R.array.samwaad_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.samwaad_subevent_images).getResourceId(subeventPosition, -1);
                break;
            case 0:
                subeventName = getResources().getStringArray(R.array.abhinay_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.abhinay_subevent_images).getResourceId(subeventPosition, -1);
                break;
            case 7:
                subeventName = getResources().getStringArray(R.array.toolika_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.toolika_subevent_images).getResourceId(subeventPosition, -1);
                break;
            case 5:
                subeventName = getResources().getStringArray(R.array.natraj_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.natraj_subevent_images).getResourceId(subeventPosition, -1);
                break;
            case 2:
                subeventName = getResources().getStringArray(R.array.crosswindz_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.crosswindz_subevent_images).getResourceId(subeventPosition, -1);
                break;
            default:
                subeventName = getResources().getStringArray(R.array.bandish_subevent_names)[subeventPosition];
                eventBanner = getResources().obtainTypedArray(R.array.bandish_subevent_images).getResourceId(subeventPosition, -1);
                break;
        }

        getSupportActionBar().setTitle(subeventName);
        Bitmap img = BitmapFactory.decodeResource(getResources(), eventBanner);

        ImageView eventImageView = findViewById(R.id.event_banner);
        eventImageView.setImageBitmap(img);

//        Palette.from(img).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                int darkMutedColor = palette.getDarkMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSecondary));
//                int darkVibrantColor = palette.getDarkVibrantColor(darkMutedColor);
//                mColor = darkVibrantColor;
//                Toast.makeText(getApplicationContext(),String.valueOf(mColor),Toast.LENGTH_SHORT).show();
//                appBarLayout.setBackgroundColor(darkVibrantColor);
//                tabLayout.setBackgroundColor(darkVibrantColor);
//
//            }
//        });

        Palette palette = Palette.from(img).generate();
        int darkMutedColor = palette.getDarkMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSecondary));
        int darkVibrantColor = palette.getDarkVibrantColor(darkMutedColor);
        mColor = darkVibrantColor;
//        Toast.makeText(getApplicationContext(),String.valueOf(mColor),Toast.LENGTH_SHORT).show();
        appBarLayout.setBackgroundColor(darkVibrantColor);
        tabLayout.setBackgroundColor(darkVibrantColor);

        mTabsPagerAdapter = new EventActivity.TabsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.event_viewpager);
        mViewPager.setAdapter(mTabsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setAppBarOffset(int offsetPx) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.onNestedPreScroll((CoordinatorLayout) findViewById(R.id.event_coordinator_layout), appBarLayout, null, 0, offsetPx, new int[]{0, 0}, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_register:
                Uri uri = Uri.parse("http://kashiyatra.org/dashboard/events");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event, menu);
        return true;
    }

    private class TabsPagerAdapter extends FragmentPagerAdapter {

        private TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    return DescriptionFragment.newInstance(eventPosition, subeventPosition, mColor);
                case 1:
                    return RulesFragment.newInstance(eventPosition, subeventPosition, mColor);
                default:
                    return RulesFragment.newInstance(eventPosition, subeventPosition, mColor);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DESCRIPTION";
                case 1:
                    return "RULES";
                default:
                    return "DESCRIPTION";
            }
        }

    }
}

