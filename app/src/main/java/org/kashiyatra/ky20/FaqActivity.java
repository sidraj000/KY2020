package org.kashiyatra.ky20;


import android.animation.Animator;
import android.content.res.Resources;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FaqActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;

    private View background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.activity_faq);

        background = findViewById(R.id.faq);

        if (savedInstanceState == null) {
            background.setVisibility(View.INVISIBLE);

            final ViewTreeObserver viewTreeObserver = background.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                });
            }

        }

        listView = (ExpandableListView) findViewById(R.id.exp1);
        initData();
        listAdapter = new org.kashiyatra.ky20.adapters.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView.setAdapter(listAdapter);

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("When is the next edition of Kashiyatra?");
        listDataHeader.add("How can I participate in Kashiyatra'19");
        listDataHeader.add("What is the significance of the KY ID?");
        listDataHeader.add("Where should I report after reaching IIT (BHU) Varanasi?");
        listDataHeader.add("Are on-spot registrations allowed during the fest?");
        listDataHeader.add("How can I be allowed to participate at a given event venue?");
        listDataHeader.add("What if I do not have a Registration ID before coming to the festival?");
        listDataHeader.add("Does every member of the team need to be present at the Registration Desk during Event Registration?");
        listDataHeader.add("Do I have to register for Prelims and Finals separately?");
        listDataHeader.add("Do I have to register at the Registration Desk if I already registered online?");
        listDataHeader.add("What type of accommodation will be provided?");
        listDataHeader.add("Few members of my team will be arriving late, what to do about their accommodation?");
        listDataHeader.add("Does accommodation fee include the food facility as well?");
        listDataHeader.add("How can the official Kashiyatra app help in improving KY experience?");
        listDataHeader.add("What is the artist line-up?");
        listDataHeader.add("Can I take photos during Pronites?");
        listDataHeader.add("Are there ATMs available inside?");


        List<String> a1 = new ArrayList<>();
        a1.add("Kashiyatra 2019 will be held from 18th to 20st January. Gear up for the 3 most awesome days of 2019!");
        List<String> a2 = new ArrayList<>();
        a2.add("To participate in Kashiyatra'19, you need to register first. For each event you want to participate you need to register separately.");
        List<String> a3 = new ArrayList<>();
        a3.add("The first step which you need to do to participate in Kashiyatra is to generate your unique “KY ID”. This “KY ID” allows you to register yourself and your team for the different events of Kashiyatra. Also, during your participation at IIT (BHU) Varanasi, you will be asked to show your KY ID.");
        List<String> a4 = new ArrayList<>();
        a4.add("Our Registration Desk will be set up at Limbdi Hostel. You need to come there and go through the necessary procedure to avail accommodation at IIT (BHU) Varanasi.");
        List<String> a5 = new ArrayList<>();
        a5.add("On-spot registrations may or may not be allowed depending on the number of already registered participants. We suggest you register online as soon as possible to prevent any unforeseen circumstances");
        List<String> a6 = new ArrayList<>();
        a6.add("Each participant must show his/her “Identity Card” which you have received from the “Registration Desk”. This ID card has a unique number which is required before you finally participate in your event. Also, the event you want to take part in should be mentioned on your ID card by the coordinator at the Registration Desk.");
        List<String> a7 = new ArrayList<>();
        a7.add("You can go to the “Registration Desk” to get your Registration ID well before the start of your interested event. But doing this ensures that you are opting for “On-Spot Registration” which does not guarantee whether you will be getting a slot to participate or not. It is strongly recommended that you register only before coming to the festival to fix your participation.");
        List<String> a8 = new ArrayList<>();
        a8.add("The Team Leader can register the events on behalf of all members of his/her team. In fact, we appreciate you not overcrowding in front of the registration desk.");
        List<String> a9 = new ArrayList<>();
        a9.add("No. You need to register for each event only once.");
        List<String> a10 = new ArrayList<>();
        a10.add("Yes. Registration at the Registration Desk is mandatory for each participant. Without registering at the Registration Desk or without KY ID Card, a participant will not be allowed to participate in any event.");
        List<String> a11 = new ArrayList<>();
        a11.add("Accommodation will be provided to boys and girls in well secured separate residential complexes on the campus of IIT(BHU) Varanasi.");
        List<String> a12 = new ArrayList<>();
        a12.add("No issues. You can take accommodation for those members as and when they arrive, or you can book their accommodation beforehand.");
        List<String> a13 = new ArrayList<>();
        a13.add("No. The accommodation charges don\\'t include food. However, you can take food in the hostel mess @₹100/- for all the 3 meals of the day. Also, there will be food courts operational at the events venue during Kashiyatra to cater to the food requirements.");
        List<String> a14 = new ArrayList<>();
        a14.add("After downloading the app, it will show you the detailed locations of the allotted hostels and the respective event venues, with the google maps integration facility you have the chance to explore IIT (BHU) Varanasi and Kashiyatra like never before.");
        List<String> a15 = new ArrayList<>();
        a15.add("Stay tuned to our Official Facebook page.");
        List<String> a16 = new ArrayList<>();
        a16.add("Preferably not. You just stood in a huge queue to see it live, why ruin it just to show it to others!");
        List<String> a17 = new ArrayList<>();
        a17.add("Yes, ATMs are available.");


        listHashMap.put(listDataHeader.get(0),a1);
        listHashMap.put(listDataHeader.get(1),a2);
        listHashMap.put(listDataHeader.get(2),a3);
        listHashMap.put(listDataHeader.get(3),a4);
        listHashMap.put(listDataHeader.get(4),a5);
        listHashMap.put(listDataHeader.get(5),a6);
        listHashMap.put(listDataHeader.get(6),a7);
        listHashMap.put(listDataHeader.get(7),a8);
        listHashMap.put(listDataHeader.get(8),a9);
        listHashMap.put(listDataHeader.get(9),a10);
        listHashMap.put(listDataHeader.get(10),a11);
        listHashMap.put(listDataHeader.get(11),a12);
        listHashMap.put(listDataHeader.get(12),a13);
        listHashMap.put(listDataHeader.get(13),a14);
        listHashMap.put(listDataHeader.get(14),a15);
        listHashMap.put(listDataHeader.get(15),a16);
        listHashMap.put(listDataHeader.get(16),a17);




    }

    private void circularRevealActivity() {
        int cx = background.getRight() - getDips(44);
        int cy = background.getBottom() - getDips(250);

        float finalRadius = Math.max(background.getWidth(), background.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                background,
                cx,
                cy,
                0,
                finalRadius);

        circularReveal.setDuration(400);
        background.setVisibility(View.VISIBLE);
        circularReveal.start();

    }

    private int getDips(int dps) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                resources.getDisplayMetrics());
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = background.getRight() - getDips(44);
            int cy = background.getBottom() - getDips(250);

            float finalRadius = Math.max(background.getWidth(), background.getHeight());
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(background, cx, cy, finalRadius, 0);

            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    background.setVisibility(View.INVISIBLE);
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            circularReveal.setDuration(400);
            circularReveal.start();
        }
        else {
            super.onBackPressed();
        }
    }
}

