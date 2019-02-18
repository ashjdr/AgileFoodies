package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindNearestShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_nearest_shop);
    }


    public void toGMaps(View view) {

        String shop = null;

        switch(view.getId()) {
            case R.id.tescoButton:
                shop = "tesco";
                break;
            case R.id.sainsButton:
                shop = "sainsbury's";
                break;
            case R.id.asdaButton:
                shop = "asda";
                break;
            case R.id.lidlButton:
                shop = "lidl";
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/" + shop + "/"));
        startActivity(intent);
    }
}
