package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class UploadYourRecipeFragment extends Fragment {
    private ImageView uploadedImage;
    public RecipeClass recipe = new RecipeClass();
    boolean pvegan;
    boolean pvegetarian;


    FirebaseFirestore database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_upload_your_recipe, container, false);
        final EditText nametext = v.findViewById(R.id.your_recipe_name);
        
        uploadedImage = v.findViewById(R.id.uploadedImage);
        ImageButton takePhoto = v.findViewById(R.id.takePhoto);
        ImageButton addImage = v.findViewById(R.id.imageButtonAddImage);
        ImageButton saveUpload = v.findViewById(R.id.save_upload);
        final Switch veganSwitch = v.findViewById(R.id.switch1);
        Switch vegetarianSwitch = v.findViewById(R.id.switch2);
        final EditText timeText = v.findViewById(R.id.editText2);
        database = FirebaseFirestore.getInstance();
        final LinearLayout ingredient = getActivity().findViewById(R.id.ingredient);
        final ArrayList<String> ingredients = new ArrayList<>();
        final EditText ingredientsText = new EditText(getContext());
            ingredientsText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event){
                        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            String mvalue;
                            EditText mingredientsText = ingredientsText;
                            mvalue = mingredientsText.getText().toString();
                            if(mvalue.isEmpty()){
                                return false;
                            }
                            else {
                                ingredients.add(mvalue);
                                ingredient.addView(mingredientsText);
                            }
                        }




                    return false;
                }


            });



        Spinner categorySpinner = v.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.category_theme_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(adapter);



        veganSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            pvegan = isChecked;
        }
    });

        vegetarianSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pvegetarian = isChecked;
            }
        });


        addImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });

        takePhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });

        saveUpload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String pname  = nametext.getText().toString();
                int ptime = Integer.parseInt(timeText.getText().toString());
                RecipeClass recipe = new RecipeClass();
                recipe.setName(pname);
                recipe.setIngredients(ingredients);
                recipe.setVegan(pvegan);
                recipe.setVegetarian(pvegetarian);
                recipe.setTime(ptime);
                database.collection("recipes").document(""+pname).set(recipe);


                Log.i("Info log","Button Clicked" + recipe.isVegan());
                Log.i("Info log","Button Clicked" + recipe.getName());
                Log.i("Info log","Button Clicked" + recipe.isVegetarian());
                Log.i("Info log","Button Clicked" + recipe.getTime());

                }
        }); 


        return v;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    uploadedImage.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    uploadedImage.setImageURI(selectedImage);
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle(getString(R.string.upload_a_recipe));

    }}














