package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


public class UploadYourRecipeActivity extends AppCompatActivity {
    private ImageButton addImage;
    private ImageButton takePhoto;
    private ImageButton saveUplaod;
    private EditText edittext;
    private ImageView addimage;
    private String currentPhotoPath;
    private ContactsContract.Data data;


    private final int SELECT_PHOTO = 1;
        private final int REQUEST_TAKE_PHOTO = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_your_recipe);


        edittext  = findViewById(R.id.edit_text1);
        addimage  = findViewById(R.id.uploadedImage);
        takePhoto = findViewById(R.id.takePhoto);

            addImage = findViewById(R.id.imageButtonAddImage);
            addImage.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                }
            });

            takePhoto = findViewById(R.id.takePhoto);
            takePhoto.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {

                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI;
                    photoURI = FileProvider.getUriForFile(this,
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }

    });
}


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
            super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

            switch(requestCode) {
                case SELECT_PHOTO:
                    if(resultCode == RESULT_OK){
                        try {
                            final Uri imageUri = imageReturnedIntent.getData();
                            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            addimage.setImageBitmap(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();

                        }
                    }
                case REQUEST_TAKE_PHOTO:{
                    if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                        Bundle extras = data.();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        addimage.setImageBitmap(imageBitmap);
                    }
                }


                }

            }
        }

    }









