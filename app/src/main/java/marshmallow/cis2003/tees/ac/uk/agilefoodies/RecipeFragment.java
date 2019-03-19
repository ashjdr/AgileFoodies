package marshmallow.cis2003.tees.ac.uk.agilefoodies;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import static android.content.ContentValues.TAG;


public class RecipeFragment extends Fragment {
    public TextView recipetext;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {View v = inflater.inflate(R.layout.fragment_recipe, container, false);
    recipetext = v.findViewById(R.id.recipe_text);
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference("jack/0/ingredients/0/name");


    ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String value = (String) dataSnapshot.getValue();
            recipetext.append(value);


        }


        @Override
        public void onCancelled(DatabaseError databaseError) {

        }


    });

                // do your stuff here with value



//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference ref = database.getReference("https://console.firebase.google.com/project/agilefoodies/database/agilefoodies/data");
//
//        ValueEventListener recipeListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                RecipeFragment recipe = dataSnapshot(database.getReference("0").child(getString()));
//                recipetext.append(recipe.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//            }
//
//        };
//        ref.addValueEventListener(recipeListener);
//

        return v;
    }


}