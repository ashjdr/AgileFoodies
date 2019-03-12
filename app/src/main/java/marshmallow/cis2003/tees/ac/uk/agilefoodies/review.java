package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class review extends Fragment {

    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        if(getView().findViewById(R.id.ratingBar) != null) {
            RatingBar ratingBar = getView().findViewById(R.id.ratingBar);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    openReview();
                }
            });


        }
        else
            System.out.println("\n\n\n\nreview's find view by id returned false!\n\n\n\n");
        return inflater.inflate(R.layout.activity_review, container, false);
    }


    public void openReview() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(
                "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName()));
        intent.setPackage("com.android.vending");
        startActivity(intent);
    }




}
