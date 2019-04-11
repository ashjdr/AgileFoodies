package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Spinner;
import android.widget.Switch;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;


import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

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
        View v = inflater.inflate(R.layout.activity_upload_your_recipe, container, false);
        final EditText nametext = v.findViewById(R.id.your_recipe_name);
        uploadedImage = v.findViewById(R.id.uploadedImage);
        ImageButton takePhoto = v.findViewById(R.id.takePhoto);
        ImageButton addImage = v.findViewById(R.id.imageButtonAddImage);
        ImageButton saveUpload = v.findViewById(R.id.save_upload);
        final Switch veganSwitch = v.findViewById(R.id.switch1);
        Switch vegetarianSwitch = v.findViewById(R.id.switch2);
        final EditText ingredientsText = v.findViewById(R.id.your_recipe_ingredients);
        final EditText timeText = v.findViewById(R.id.editText2);
        database = FirebaseFirestore.getInstance();
        Spinner categorySpinner = v.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.category_theme_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(adapter);

                    for (int i = 0; i < ingredientsText.getMaxLines(); i++) {
                        Log.i("Info log", "result is" + i);
                        ingredientsText.setHint(i + ") Type ingredients here");
                    }

        veganSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                pvegan = true;
            } else {
                pvegan = false;
            }
        }
    });

        vegetarianSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pvegetarian = true;
                } else {
                    pvegetarian = false;
                }
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
    }}














