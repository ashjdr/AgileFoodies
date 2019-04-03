package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Arrays;

import static android.app.Activity.RESULT_OK;


public class UploadYourRecipeFragment extends Fragment {
    private ImageView uploadedImage;
    public RecipeClass recipe;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_upload_your_recipe, container, false);
        final EditText nametext = v.findViewById(R.id.your_recipe_name);
        uploadedImage = v.findViewById(R.id.uploadedImage);
        ImageButton takePhoto = v.findViewById(R.id.takePhoto);
        ImageButton addImage = v.findViewById(R.id.imageButtonAddImage);
        ImageButton saveUpload = v.findViewById(R.id.save_upload);



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
                Log.i("Info log","Button Clicked" + pname );
                recipe.setName(pname);

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














