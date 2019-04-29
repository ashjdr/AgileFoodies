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
import android.text.style.RelativeSizeSpan;
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
    private TextView recipeNameView;
    private TextView instructionNameView;

    FirebaseFirestore database;
    RecipeClass recipe;
    Timer timer;

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
        recipetext.setTextSize(20);
        recipetext.setTextColor(getResources().getColor(R.color.dark_green));
        timeView = v.findViewById(R.id.timer);
        timeView.setTextSize(20);
        timeView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        nameView = v.findViewById(R.id.recipe_name);
        nameView.setTextSize(20);
        nameView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView1 = v.findViewById(R.id.ingredients_title);
        recipeNameView = v.findViewById(R.id.recipe_name_view);
        recipeNameView.setTextSize(30);
        database = FirebaseFirestore.getInstance();
        textView1.setTextSize(10);
        textView1.setTextColor(getResources().getColor(R.color.black));
        instructionNameView= v.findViewById(R.id.instruction_name_view);
        instructionNameView.setTextSize(20);
        instructionNameView.setTextColor(getResources().getColor(R.color.dark_green));

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

                                String string= "\nIngredient:";
                                String click="\nclick ingredient to shop";
                                SpannableString ss1=  new SpannableString(string);
                                ss1.setSpan(new RelativeSizeSpan(2f), 0, ss1.length(), 0);
                                textView1.append(ss1);
                                textView1.append(click);

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

                            timeView.setText("Timer: (click to start)" + timing);
                            final String name = (String)document.get("name");
                            recipeNameView.setText(name);
                            nameView.append(categories);
                            timeView.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View v){
                                    mListener.onFragmentInteraction(timing, name);
                                }


                            });
                            StringBuilder fields = new StringBuilder ("");
                            instructionNameView.append("Instructions:");
                            List<String> group2 = (List<String>) document.get("instructions");
                            if (group2 == null){
                                fields.append("\n No instructions found");}
                            else{
                                int num = 1;
                                for (String element: group2){
                                    fields.append(num + ")" + element + "\n\n" );
                                    num++;
                                }recipetext.setText(fields);}
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

                                String string= "\nIngredient:";
                                String click="\nclick ingredient to shop";
                                SpannableString ss1=  new SpannableString(string);
                                ss1.setSpan(new RelativeSizeSpan(2f), 0, ss1.length(), 0);
                                textView1.append(ss1);
                                textView1.append(click);

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

                            timeView.setText("Timer: (click to start)" + timing);
                            final String name = (String)document.get("name");
                            recipeNameView.setText(name);
                            nameView.append(categories);
                            timeView.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View v){
                                    mListener.onFragmentInteraction(timing, name);
                                }


                            });
                            StringBuilder fields = new StringBuilder ("");
                            instructionNameView.append("\nInstructions:");
                            List<String> group2 = (List<String>) document.get("instructions");
                            if (group2 == null){
                                fields.append("\n No instructions found");}
                            else{
                                int num = 1;
                                for (String element: group2){
                                    fields.append(num + ")" + element + "\n\n" );
                                    num++;
                                }recipetext.setText(fields);}
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





