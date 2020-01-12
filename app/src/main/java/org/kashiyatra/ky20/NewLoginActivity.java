package org.kashiyatra.ky20;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewLoginActivity extends AppCompatActivity {
    TextView registerLater;
    public int PERMISSIONS_REQUEST = 100;
    private FirebaseAuth mAuth;
    public static final int RC_SIGN_IN=1;
    public static final int flogin=2;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    DatabaseReference mDatabase;
    public  String uId;
    public ChildEventListener mChildEventListener;
    FirebaseUser currentUser;
    VideoView videoView;
  //  private DisplayMetrics dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);/*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.new_activity_login);
        registerLater=findViewById(R.id.registerLater);
        registerLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewLoginActivity.this, HomeActivity.class));
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        signInButton = findViewById(R.id.sign_in);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setVisibility(View.GONE);

      /*   KenBurnsView kbv = (KenBurnsView) findViewById(R.id.image);


            dm=getResources().getDisplayMetrics();
       final ValueAnimator va=new ValueAnimator();
        va.setDuration(1500);
        va.setInterpolator(new DecelerateInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator p1) {
                RelativeLayout.LayoutParams button_login_lp= (RelativeLayout.LayoutParams) signInButton.getLayoutParams();
                button_login_lp.width=Math.round(p1.getAnimatedFraction());
                signInButton.setLayoutParams(button_login_lp);
              *//*  RelativeLayout.LayoutParams button_login_lp2= (RelativeLayout.LayoutParams) loginButton.getLayoutParams();
                button_login_lp2.width=Math.round(p1.getAnimatedFraction());
                loginButton.setLayoutParams(button_login_lp2);*//*
            }
        });
        signInButton.animate().translationX(dm.widthPixels+signInButton.getMeasuredWidth()).setDuration(0).setStartDelay(0).start();
        signInButton.animate().translationX(0).setStartDelay(3000).setDuration(1500).setInterpolator(new OvershootInterpolator()).start();
*/

       /* videoView=findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.header));
  */  }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
                startActivity(new Intent(NewLoginActivity.this, HomeActivity.class));
                finish();
        }
        else
        {

            signInButton.setVisibility(View.VISIBLE);
            mGoogleSignInClient.signOut();
        }



    }
    @Override
    public void onBackPressed() {
        Log.w("lact","comes");
        final AlertDialog.Builder builder = new AlertDialog.Builder(NewLoginActivity.this);
        builder.setMessage("Confirm to Exit");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Close!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                  Log.e("erroor", "Google sign in failed", e);

            }
        }

    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        final AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in User's information
                            Log.d("erroor", "signInWithCredential:success");
                            currentUser = mAuth.getCurrentUser();
                            uId=currentUser.getUid();

                            updateUI(currentUser);



                        } else {
                            // If sign in fails, display a message to the User.
                             Log.w("erroor", "signInWithCredential:failure", task.getException());
                            // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            currentUser=null;
                            updateUI(currentUser);

                        }



                        // ...
                    }
                });

    }

    private void updateUI(final FirebaseUser user) {
        if(user!=null) {
            writeNewUser(user.getUid(), user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString());
          //  Toast.makeText(this, "hii", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(NewLoginActivity.this, HomeActivity.class));
            finish();
            Toast.makeText(NewLoginActivity.this, "Successfully logged in as "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
            else
            {
                Toast.makeText(this, "Authentication Failed...", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                mGoogleSignInClient.signOut();
            }


    }
    private void writeNewUser(String userId, String name, String email,String pid) {
        User user=new User(name,userId,email,pid);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(userId).setValue(user);

    }





}
