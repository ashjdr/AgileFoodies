package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class SearchRecipeFragment extends Fragment {
EditText searchText;
public static String textEntered;
FirebaseFirestore database;
Button searchButton;
EditText ingredientSearchText;
Button ingredientSearchButton;
RecipeFragment recipe;
private OnFragmentInteractionListener sListener;


    public SearchRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     View v =  inflater.inflate(R.layout.fragment_search_recipe, container, false);
        searchText = v.findViewById(R.id.search_term_recipe_name);
        searchButton = v.findViewById(R.id.buttonSearch_recipe_name);
        ingredientSearchText = v.findViewById(R.id.search_term_ingredients);
        ingredientSearchButton = v.findViewById(R.id.buttonSearch_ingredients);
        database = FirebaseFirestore.getInstance();




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { textEntered = searchText.getText().toString();

                sListener.onFragmentInteraction(textEntered,"recipeName");
            }
            });

        ingredientSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { textEntered = ingredientSearchText.getText().toString();

                sListener.onFragmentInteraction(textEntered,"ingredientName");
            }
        });

        return v;



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle(getString(R.string.recipe_search));
        if (context instanceof SearchRecipeFragment.OnFragmentInteractionListener) {
            sListener = (SearchRecipeFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sListener = null;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String textEntered, String queryType);

    }


   }


