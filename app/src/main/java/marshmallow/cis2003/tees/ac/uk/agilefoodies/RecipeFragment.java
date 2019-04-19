package marshmallow.cis2003.tees.ac.uk.agilefoodies;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;
import java.util.Timer;
import static android.content.ContentValues.TAG;

public class RecipeFragment extends Fragment implements SearchRecipeFragment.OnFragmentInteractionListener  {
    public TextView recipetext;
    public TextView nameView;
    public TextView timeView;
    FirebaseFirestore database;
    RecipeClass recipe;
    Timer timer;
//    SearchRecipeFragment searchRecipe;
    String mTextEntered;
    String mQueryType;
    DocumentReference docRef;

    private OnFragmentInteractionListener mListener;

    public RecipeFragment(){


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {View v = inflater.inflate(R.layout.fragment_recipe, container, false);
        recipetext = v.findViewById(R.id.recipe_text);
        timeView = v.findViewById(R.id.timer);
        nameView = v.findViewById(R.id.recipe_name);
        database = FirebaseFirestore.getInstance();
        CollectionReference recipes = database.collection("recipes");
        if (mQueryType == "recipeName"){
        docRef = database.collection(recipes.getId()).document("" + mTextEntered);}
        else if (mQueryType == "ingredientName"){Log.d(TAG, "ingredient search");}
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                LinearLayout ingredient = getActivity().findViewById(R.id.ingredient);
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        StringBuilder fields = new StringBuilder ("");
                        fields.append("\nVegan?: ").append(document.get("Vegan"));
                        fields.append("\nVegetarian?: ").append(document.get("Vegetarian"));
                        fields.append("\nCategory: ").append(document.get("category"));


                        List<String> group = (List<String>) document.get("ingredients");
                        for (final String element: group){

                            //TO LOOK AT ??
                            TextView tView = new TextView(getContext());
                            if (group == null){
                                tView.setText("No ingredients");
                            }
                            else {
                                tView.setText(element);
                                ingredient.addView(tView);
                                tView.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        Intent intent = new Intent(RecipeFragment.this.getActivity(), tescoLab.class);
                                        intent.putExtra("ingredient", element);
                                        startActivity(intent);
                                    }
                                });
                            }
                            //fields.append("\n" + element);
                        }
                        final long timing = (long)document.get("Time");

                        timeView.setText("Time: "+ timing) ;
                        final String name = (String)document.get("name");
                        nameView.setText("Recipe Name: "+ name) ;
                        timeView.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                mListener.onFragmentInteraction(timing, name);
                            }


                        });

                        fields.append("\n\nInstructions:");
                        List<String> group2 = (List<String>) document.get("instructions");
                        if (group2 == null){
                        fields.append("\n No instructions found");}
                        else{
                        int num = 1;
                        for (String element: group2){
                            fields.append("\n" + num + ") " + element + "\n" );
                            num++;
                        }
                        recipetext.setText(fields);}
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void onFragmentInteraction(String textEntered, String queryType) {
        Log.d("SearchFragment", "onFragmentInteraction()");
        mTextEntered = textEntered;
        mQueryType = queryType;


    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Long time, String name);

    }

}

