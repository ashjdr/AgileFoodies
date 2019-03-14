
package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HomePageFragment extends Fragment {

    ImageButton imageB1;
    ImageButton imageB2;
    ImageButton imageB3;
    ImageButton imageB4;
    ImageButton imageB5;
    ImageButton imageB6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.homepage_fragment, container, false);

        imageB1 =  v.findViewById(R.id.imageButton1);
        imageB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        imageB2 =  v.findViewById(R.id.imageButton2);
        imageB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        imageB3 =  v.findViewById(R.id.imageButton3);
        imageB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        imageB4 =  v.findViewById(R.id.imageButton4);
        imageB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        imageB5 =  v.findViewById(R.id.imageButton5);
        imageB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        imageB6 =  v.findViewById(R.id.imageButton6);
        imageB6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        return v;
    }









}

