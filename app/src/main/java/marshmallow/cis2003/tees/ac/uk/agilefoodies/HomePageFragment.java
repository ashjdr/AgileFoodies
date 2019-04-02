
package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HomePageFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.homepage_fragment, container, false);

        ImageButton imageB1 =  v.findViewById(R.id.imageButton1);
        imageB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 Intent intent = new Intent(HomePageFragment.this.getActivity(),RecipeFragment.class);
//                 startActivity(intent);
            }
        });

        ImageButton imageB2 =  v.findViewById(R.id.imageButton2);
        imageB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        ImageButton imageB3 =  v.findViewById(R.id.imageButton3);
        imageB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        ImageButton imageB4 =  v.findViewById(R.id.imageButton4);
        imageB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        ImageButton imageB5 =  v.findViewById(R.id.imageButton5);
        imageB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        ImageButton imageB6 =  v.findViewById(R.id.imageButton6);
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

