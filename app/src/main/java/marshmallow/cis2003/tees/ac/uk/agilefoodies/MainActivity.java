package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
// Juli



// Jack



// Matthew

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

// Marshall



// Ash



public class MainActivity extends AppCompatActivity {

    // Juli



    // Jack



    // Matthew

    private AdView mAdView;

    // Marshall



    // Ash



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

        // Juli
        public void goToTimerActivity (View view){
            Intent intent = new Intent (this, TimerActivity.class);
            startActivity(intent);
        }


        // Jack



        // Matthew

        //START OF AD CODE
        if (savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AdFragment())
                    .commit();
        }

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //END OF AD CODE

        // Marshall



        // Ash



    }

    // Juli



    // Jack



    // Matthew



    // Marshall



    // Ash




