
package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class HomePageFragment extends Fragment  {
    private OnFragmentInteractionListener bListener;
    String recipeName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.homepage_fragment, container, false);

        ImageButton imageB1 =  v.findViewById(R.id.imageButton1);
        imageB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {bListener.onFragmentInteraction("Carbonara");
//                 Intent intent = new Intent(HomePageFragment.this.getActivity(),RecipeFragment.class);
//                 startActivity(intent);
            }
        });

        ImageButton imageB2 =  v.findViewById(R.id.imageButton2);
        imageB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {bListener.onFragmentInteraction("Greek Salad");
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        ImageButton imageB3 =  v.findViewById(R.id.imageButton3);
        imageB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {bListener.onFragmentInteraction("Summer Paella");
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        ImageButton imageB4 =  v.findViewById(R.id.imageButton4);
        imageB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {bListener.onFragmentInteraction("Massaman Curry");
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        ImageButton imageB5 =  v.findViewById(R.id.imageButton5);
        imageB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { bListener.onFragmentInteraction("Steak");
                // Intent intent=new Intent(HomePage.this,?enter location?.class);
                // startActivity(intent);
            }
        });

        ImageButton imageB6 =  v.findViewById(R.id.imageButton6);
        imageB6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    bListener.onFragmentInteraction("Grilled Chicken");
            }
        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomePageFragment.OnFragmentInteractionListener) {
            bListener = (HomePageFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        bListener = null;
    }



    public interface OnFragmentInteractionListener {
    void onFragmentInteraction(String recipeName);

}







}

