package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.app.Fragment;
import android.content.Intent;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View.OnClickListener;

import static android.app.Activity.RESULT_OK;


public class UploadYourRecipeActivity extends Fragment {
    private ImageButton addImage;
    private ImageButton takePhoto;
    private ImageButton saveUpload;
    private EditText edittext;
    private ImageView uploadedImage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_upload_your_recipe, container, false);


        edittext = v.findViewById(R.id.edit_text1);
        uploadedImage = v.findViewById(R.id.uploadedImage);
        takePhoto = v.findViewById(R.id.takePhoto);
        addImage = v.findViewById(R.id.imageButtonAddImage);

        /*ImageButton B1 = addImage;
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });

        ImageButton B2 = takePhoto;
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });*/

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














