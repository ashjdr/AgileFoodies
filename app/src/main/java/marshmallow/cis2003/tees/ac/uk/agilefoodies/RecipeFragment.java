package marshmallow.cis2003.tees.ac.uk.agilefoodies;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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


import java.util.List;
import java.util.Timer;

import static android.content.ContentValues.TAG;


public class
RecipeFragment extends Fragment implements SearchRecipeFragment.OnFragmentInteractionListener{

    private TextView recipetext;
    private TextView nameView;
    private TextView timeView;

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
        database = FirebaseFirestore.getInstance();


        CollectionReference recipes = database.collection("recipes");
//        if (mQueryType.equals("ingredientName")) {
//
//           Query query =  recipes.whereEqualTo("ingredientName", mTextEntered);
//
//
//            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    LinearLayout ingredient = getActivity().findViewById(R.id.ingredient);
//                    if (task.isSuccessful()) {
//                        DocumentSnapshot document = task.getResult();
//                        if (document.exists()) {
//                            StringBuilder fields = new StringBuilder ("");
//                            fields.append("\nVegan?: ").append(document.get("Vegan"));
//                            fields.append("\nVegetarian?: ").append(document.get("Vegetarian"));
//                            fields.append("\nCategory: ").append(document.get("category"));
//
//
//                            List<String> group = (List<String>) document.get("ingredients");
//                            TextView tView = new TextView(getContext());
//                            if (group == null){
//                                tView.setText(R.string.no_ingredients);
//                            }
//                            else {
//                                tView.append("Ingredients:\n");
//                                for  (final String element: group){
//                                    tView.append(element + "\n");
//                                    tView.setOnClickListener(new View.OnClickListener() {
//                                        public void onClick(View v) {
//                                            Intent intent = new Intent(RecipeFragment.this.getActivity(), tescoLab.class);
//                                            intent.putExtra("ingredient", element);
//                                            startActivity(intent);
//                                        }
//                                    });
//                                }
//                                ingredient.addView(tView);
//                                //fields.append("\n" + element);
//                            }
//                            final long timing = (long)document.get("time");
//
//                            timeView.setText("Time: " + timing) ;
//                            final String name = (String)document.get("name");
//                            nameView.setText("Recipe Name: " + name) ;
//                            timeView.setOnClickListener(new View.OnClickListener(){
//                                public void onClick(View v){
//                                    mListener.onFragmentInteraction(timing, name);
//                                }
//
//
//                            });
//
//                            fields.append("\n\nInstructions:");
//                            List<String> group2 = (List<String>) document.get("instructions");
//                            if (group2 == null){
//                                fields.append("\n No instructions found");}
//                            else{
//                                int num = 1;
//                                for (String element: group2){
//                                    fields.append("\n" + num + ") " + element + "\n" );
//                                    num++;
//                                }
//                                recipetext.setText(fields);}
//                        } else {
//                            Log.d(TAG, "No such document");
//                        }
//                    } else {
//                        Log.d(TAG, "get failed with ", task.getException());
//                    }
//                }
//            });}
//
//
//        else
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
                        categories.append("\nVegan?: ").append(document.get("Vegan"));
                        categories.append("\nVegetarian?: ").append(document.get("Vegetarian"));
                        categories.append("\nCategory: ").append(document.get("category"));



                        List<String> group = (List<String>) document.get("ingredients");
                        TextView tView = new TextView(getContext());
                        if (group == null){
                            tView.setText("No ingredients");
                        }
                        else {
                            tView.append("Ingredients:\n");
                        for  (final String element: group){
                                tView.append(element + "\n");
                                tView.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        Intent intent = new Intent(RecipeFragment.this.getActivity(), tescoLab.class);
                                        intent.putExtra("ingredient", element);
                                        startActivity(intent);
                                    }
                                });
                            }

                            ingredient.addView(tView);
                            //fields.append("\n" + element);
                        }
                        final long timing = (long)document.get("time");

                        timeView.setText("Time: "+ timing) ;
                        final String name = (String)document.get("name");
                        nameView.setText("Recipe Name: "+ name) ;
                        nameView.append(categories);
                        timeView.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                mListener.onFragmentInteraction(timing, name);
                            }


                        });
                        StringBuilder fields = new StringBuilder ("");
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
                            TextView tView = new TextView(getContext());
                            tView.setTextSize(16);
                            if (group == null){
                                tView.setText("No ingredients");
                            }
                            else {
                                tView.append("Ingredients:\n");
                                for  (final String element: group){

                                    tView.append(element + "\n");
                                    tView.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(RecipeFragment.this.getActivity(), tescoLab.class);
                                            intent.putExtra("ingredient", element);
                                            startActivity(intent);
                                        }
                                    });
                                }

                                ingredient.addView(tView);
                                //fields.append("\n" + element);
                            }
                            final long timing = (long)document.get("time");

                            timeView.setText("Click here to set timer "+ timing) ;
                            timeView.setTextSize(20);
                            timeView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            final String name = (String)document.get("name");
                            nameView.setText("Recipe Name: "+ name) ;
                            nameView.setTextSize(20);
                            nameView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                            nameView.append(categories);
                            timeView.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View v){
                                    mListener.onFragmentInteraction(timing, name);
                                }


                            });
                            StringBuilder fields = new StringBuilder ("");
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
                    }
                }
            });}





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


    public void onFragmentInteraction(String recipeName) {
        recipeNameFragmentInteraction = recipeName;

    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Long time, String name);

    }

}





