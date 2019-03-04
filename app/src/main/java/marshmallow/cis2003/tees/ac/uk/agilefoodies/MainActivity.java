package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

// Juli



// Jack



// Matthew

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

// Marshall



// Ash
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import android.util.Log;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // Juli


    // Jack


    // Matthew

    private AdView mAdView;

    // Marshall


    // Ash
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    int RC_SIGN_IN = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Juli





    // Jack


        // Matthew

        //START OF AD CODE
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AdFragment())
                    .commit();
        }

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //END OF AD CODE

        // Marshall


        // Ash
        mAuth = FirebaseAuth.getInstance();


    }

    // Juli

    public void goToTimerActivity(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    // Jack


    // Matthew


    // Marshall


    // Ash
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            updateUI(currentUser);
        }
        else {

            Log.d("getInitialLogin", "USER NOT LOGGED IN");
        }
    }



    public void updateUI(FirebaseUser user) {
        Log.d("TEST", "USER LOGGED IN");
    }

}