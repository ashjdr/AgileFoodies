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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import static com.android.volley.VolleyLog.TAG;

public class SearchRecipeFragment extends Fragment {
EditText searchText;
public static String textEntered;
FirebaseFirestore database;
Button searchButton;
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
        searchText = v.findViewById(R.id.search_term);
        searchButton = v.findViewById(R.id.buttonSearch);
        database = FirebaseFirestore.getInstance();




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { textEntered = searchText.getText().toString();
                sListener.onFragmentInteraction(textEntered);
            }
            });



        return v;



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        void onFragmentInteraction(String textEntered);

    }


   }


