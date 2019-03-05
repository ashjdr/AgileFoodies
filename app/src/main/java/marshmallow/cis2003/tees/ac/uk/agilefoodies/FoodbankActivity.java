package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class FoodbankActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_bank);

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

    }


    public void goFind(View view) {

        String exitstring;

        switch(view.getId()) {
            case R.id.FBmapbutton:
                exitstring = "https://www.google.com/maps/search/Food+Bank/";

                break;

            case R.id.FBsearchbutton:
                exitstring = "https://www.google.com/search?q=oodbanks+near+me";

                break;

            /*case R.id.:
                exitstring = "";
                break;
            case R.id.:
                exitstring = "";
                break;
            case R.id.:
                exitstring = "";
                break;
            case R.id.:
                exitstring = "";
                break;*/
            default:
                throw new RuntimeException("Unknown button ID");
        }

        Intent searchintent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(exitstring));
        startActivity(searchintent);
    }
}
