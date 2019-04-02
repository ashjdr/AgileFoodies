package marshmallow.cis2003.tees.ac.uk.agilefoodies;


import android.app.VoiceInteractor;
import android.content.Context;
import android.net.Uri;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import static android.content.ContentValues.TAG;


public class RecipeFragment extends Fragment  {
    public TextView recipeText;
    FirebaseFirestore database;
    RecipeClass recipe;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {View v = inflater.inflate(R.layout.fragment_recipe, container, false);
    recipeText = v.findViewById(R.id.recipe_text);
     database = FirebaseFirestore.getInstance();

        CollectionReference recipes = database.collection("recipes");

        final DocumentReference docRef = database.collection(recipes.getId()).document("JGamlzKMjXtoUsAncqcY");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        StringBuilder fields = new StringBuilder ("");
                        fields.append("Name: ").append(document.get("name"));
                        fields.append("\nTime: ").append(document.get("Time"));
                        fields.append("\nVegan?: ").append(document.get("Vegan"));
                        fields.append("\nVegetarian?: ").append(document.get("Vegetarian"));
                        fields.append("\nCategory: ").append(document.get("category"));
                        fields.append("\n\nIngredients:");
                        List<String> group = (List<String>) document.get("ingredients");
                        for (String element: group){
                            fields.append("\n" + element);
                        }
                        fields.append("\n\nInstructions:");
                        List<String> group2 = (List<String>) document.get("instructions");
                        int num = 1;
                        for (String element: group2){
                            fields.append("\n" + num + ") " + element + "\n" );
                            num++;
                        }
                        recipeText.setText(fields);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "Get failed with ", task.getException());
                }
            }
        });


        return v;
    }


}