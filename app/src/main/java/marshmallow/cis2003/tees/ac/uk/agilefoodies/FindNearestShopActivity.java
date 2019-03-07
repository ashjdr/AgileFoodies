package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class FindNearestShopActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_nearest_shop);


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


    public void toGMaps(View view) {

        String shop;

        switch(view.getId()) {
            case R.id.tescoButton:
                shop = "Tesco";
                break;
            case R.id.sainsButton:
                shop = "Sainsbury's";
                break;
            case R.id.asdaButton:
                shop = "Asda";
                break;
            case R.id.lidlButton:
                shop = "Lidl";
                break;
            case R.id.aldiButton:
                shop = "Aldi";
                break;
            case R.id.waitroseButton:
                shop = "Waitrose";
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/" + shop + "/"));
        startActivity(intent);
    }
}
