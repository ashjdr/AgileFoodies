package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
                        fields.append("\nIngredients: ").append(document.get("ingredients"));
                        fields.append("\n\nInstructions?: ").append(document.get("instructions"));
                        recipeText.setText(fields.toString());

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