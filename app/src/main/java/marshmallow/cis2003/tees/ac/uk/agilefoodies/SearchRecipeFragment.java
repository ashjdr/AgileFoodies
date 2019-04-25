package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SearchRecipeFragment extends Fragment {
EditText searchText;
public static String textEntered;
FirebaseFirestore database;
Button searchButton;
EditText ingredientSearchText;
Button ingredientSearchButton;
RecipeFragment recipe;
String mQueryType;
TextView rView;
List RecipeList;
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
        rView = v.findViewById(R.id.view_results);




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { textEntered = searchText.getText().toString();

                sListener.onFragmentInteraction(textEntered,"recipeName");


            }
            });

        final CollectionReference recipes = database.collection("recipes");
        ingredientSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textEntered = ingredientSearchText.getText().toString().toLowerCase();
                    recipes.whereEqualTo("ingredient", textEntered)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {

                                        List<String> RecipeList = new ArrayList<>();
                                        for (DocumentSnapshot doc : task.getResult()) {
                                            String e = (doc.getId());
                                            RecipeList.add(e);
                                            rView.append(e);
                                        }


                                    }

                                    else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

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

    }}


