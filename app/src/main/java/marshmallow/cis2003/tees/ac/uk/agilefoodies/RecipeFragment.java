package marshmallow.cis2003.tees.ac.uk.agilefoodies;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.List;
import java.util.Objects;
import java.util.Timer;

import static android.content.ContentValues.TAG;


public class
RecipeFragment extends Fragment implements SearchRecipeFragment.OnFragmentInteractionListener{

    private TextView recipetext;
    private TextView nameView;
    private TextView timeView;
    private TextView textView1;

    FirebaseFirestore database;
    RecipeClass recipe;
    Timer timer;
//    SearchRecipeFragment searchRecipe;
    String mTextEntered;
    String mQueryType;
    DocumentReference docRef;
    String recipeNameFragmentInteraction;


    private OnFragmentInteractionListener mListener;
    private OnFragmentInteractionListener bListener;


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
        textView1 = v.findViewById(R.id.ingredients_title);
        database = FirebaseFirestore.getInstance();



        CollectionReference recipes = database.collection("recipes");



        if ((mQueryType != null) && mQueryType.equals("recipeName")){
        docRef = database.collection(recipes.getId()).document("" + mTextEntered);

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    LinearLayout ingredient = getActivity().findViewById(R.id.ingredient);
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            StringBuilder categories = new StringBuilder ("");
                            categories.append("\nVegan?: ").append(document.get("vegan"));
                            categories.append("\nVegetarian?: ").append(document.get("vegetarian"));
                            categories.append("\nCategory: ").append(document.get("category"));



                            List<String> group = (List<String>) document.get("ingredients");

                            if (group == null){
                                textView1.setText("No ingredients");
                            }
                            else {
                                textView1.append("Ingredients:\n");
                                for  (final String element: group){
                                    TextView tView = new TextView(getContext());
                                    tView.setTextSize(16);
                                    tView.setTextColor(getResources().getColor(R.color.black));
                                    tView.setPadding(5,5,5,5);
                                    final String value = element;
                                    tView.append(element + "\n");
                                    tView.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(RecipeFragment.this.getActivity(), tescoLab.class);
                                            intent.putExtra("ingredient", value);
                                            startActivity(intent);
                                        }
                                    });
                                    ingredient.addView(tView);
                                }


                                //fields.append("\n" + element);
                            }
                            final long timing = (long)document.get("time");

                            timeView.setText("Click here to set timer "+ timing) ;
                            timeView.setTextSize(20);
                            timeView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            final String name = (String)document.get("name");
                            nameView.setTextSize(16);
                            nameView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            nameView.setText(name);
                            nameView.append(categories);
                            timeView.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View v){
                                    mListener.onFragmentInteraction(timing, name);
                                }


                            });
                            StringBuilder fields = new StringBuilder ("");
                            fields.append("Instructions:");
                            List<String> group2 = (List<String>) document.get("instructions");
                            if (group2 == null){
                                fields.append("\n No instructions found");}
                            else{
                                int num = 1;
                                for (String element: group2){
                                    fields.append("\n" + num + ") " + element + "\n" );
                                    num++;
                                }recipetext.setText(fields);}
                            recipetext.setTextSize(20);
                            recipetext.setTextColor(getResources().getColor(R.color.dark_green));




                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                }
            });}

        else { docRef = database.collection(recipes.getId()).document("" +recipeNameFragmentInteraction);

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    LinearLayout ingredient = getActivity().findViewById(R.id.ingredient);
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            StringBuilder categories = new StringBuilder ("");
                            categories.append("\nVegan?: ").append(document.get("vegan"));
                            categories.append("\nVegetarian?: ").append(document.get("vegetarian"));
                            categories.append("\nCategory: ").append(document.get("category"));



                            List<String> group = (List<String>) document.get("ingredients");

                            if (group == null){
                                textView1.setText("No ingredients");
                            }
                            else {
                                textView1.append("Ingredients:\n");
                                for  (final String element: group){
                                    TextView tView = new TextView(getContext());
                                    tView.setTextSize(16);
                                    tView.setTextColor(getResources().getColor(R.color.black));
                                    tView.setPadding(5,5,5,5);
                                    final String value = element;
                                    tView.append(element + "\n");
                                    tView.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(RecipeFragment.this.getActivity(), tescoLab.class);
                                            intent.putExtra("ingredient", value);
                                            startActivity(intent);
                                        }
                                    });
                                    ingredient.addView(tView);
                                }


                                //fields.append("\n" + element);
                            }
                            final long timing = (long)document.get("time");

                            timeView.setText("Click here to set timer "+ timing) ;
                            timeView.setTextSize(20);
                            timeView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            final String name = (String)document.get("name");
                            nameView.setTextSize(16);
                            nameView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            nameView.setText(name);
                            nameView.append(categories);
                            timeView.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View v){
                                    mListener.onFragmentInteraction(timing, name);
                                }


                            });
                            StringBuilder fields = new StringBuilder ("");
                            fields.append("Instructions:");
                            List<String> group2 = (List<String>) document.get("instructions");
                            if (group2 == null){
                                fields.append("\n No instructions found");}
                            else{
                                int num = 1;
                                for (String element: group2){
                                    fields.append("\n" + num + ") " + element + "\n" );
                                    num++;
                                }recipetext.setText(fields);}
                            recipetext.setTextSize(20);
                            recipetext.setTextColor(getResources().getColor(R.color.dark_green));




                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                }
            });}





        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle(getString(R.string.recipe_details));
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


    public void onFragmentInteraction(String recipeName) {
        recipeNameFragmentInteraction = recipeName;

    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Long time, String name);

    }

}





