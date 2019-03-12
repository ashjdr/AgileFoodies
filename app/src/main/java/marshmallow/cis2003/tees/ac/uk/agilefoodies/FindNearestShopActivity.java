package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class FindNearestShopActivity extends Fragment {
    Button B1;
    Button B2;
    Button B3;
    Button B4;
    Button B5;
    Button B6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_find_nearest_shop, container, false);

        B1 = v.findViewById(R.id.tescoButton);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/Tesco/"));
                startActivity(intent);
            }
        });

        B2 = v.findViewById(R.id.sainsButton);
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/Sainsbury's/"));
                startActivity(intent);
            }
        });

        B3 = v.findViewById(R.id.asdaButton);
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/Asda/"));
                startActivity(intent);
            }
        });

        B4 = v.findViewById(R.id.lidlButton);
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/Lidl/"));
                startActivity(intent);
            }
        });

        B5 = v.findViewById(R.id.aldiButton);
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/Aldi/"));
                startActivity(intent);
            }
        });

        B6 = v.findViewById(R.id.waitroseButton);
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/Waitrose/"));
                startActivity(intent);
            }
        });

        return v;
    }
}