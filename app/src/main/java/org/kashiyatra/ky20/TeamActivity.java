package org.kashiyatra.ky20;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;


import org.kashiyatra.ky20.TeamGroups.CoreActivity;
import org.kashiyatra.ky20.TeamGroups.CreativeActivity;
import org.kashiyatra.ky20.TeamGroups.EventsTeamActivity;
import org.kashiyatra.ky20.TeamGroups.MarketingActivity;
import org.kashiyatra.ky20.TeamGroups.OpnLogActivity;
import org.kashiyatra.ky20.TeamGroups.PubRelActivity;
import org.kashiyatra.ky20.TeamGroups.PublicityActivity;
import org.kashiyatra.ky20.TeamGroups.TechActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamActivity extends AppCompatActivity {
    private View background;
    private CircleImageView circleImageView1;
    private CircleImageView circleImageView2;
    private CircleImageView circleImageView3;
    private CircleImageView circleImageView4;
    private CircleImageView circleImageView5;
    private CircleImageView circleImageView6;
    private CircleImageView circleImageView7;
    private CircleImageView circleImageView8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_group);

        circleImageView1 = (CircleImageView) findViewById(R.id.core_team);
        circleImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), CoreActivity.class);
                startActivity(intent);
            }
        });
        circleImageView2 = (CircleImageView) findViewById(R.id.marketing_team);
        circleImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), MarketingActivity.class);
                startActivity(intent);
            }
        });

        circleImageView3 = (CircleImageView) findViewById(R.id.publicity_team);
        circleImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), PublicityActivity.class);
                startActivity(intent);
            }
        });
        circleImageView4 = (CircleImageView) findViewById(R.id.pubrel_team);
        circleImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), PubRelActivity.class);
                startActivity(intent);
            }
        });
        circleImageView5 = (CircleImageView) findViewById(R.id.events_team);
        circleImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), EventsTeamActivity.class);
                startActivity(intent);
            }
        });
        circleImageView6 = (CircleImageView) findViewById(R.id.opnlog_team);
        circleImageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), OpnLogActivity.class);
                startActivity(intent);
            }
        });
        circleImageView7 = (CircleImageView) findViewById(R.id.technical_team);
        circleImageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), TechActivity.class);
                startActivity(intent);
            }
        });
        circleImageView8 = (CircleImageView) findViewById(R.id.creative_team);
        circleImageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), CreativeActivity.class);
                startActivity(intent);
            }
        });

        background = findViewById(R.id.team_view);

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

    }
    private void circularRevealActivity() {
        int cx = background.getLeft() - getDips(140);
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
            int cx = background.getLeft() - getDips(140);
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
