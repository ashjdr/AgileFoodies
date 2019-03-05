package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View.OnClickListener;


public class UploadYourRecipeActivity extends AppCompatActivity {
    private ImageButton addImage;
    private Button saveUplaod;
    private EditText edittext;
    private ImageView addimage;

        private final int SELECT_PHOTO = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_your_recipe);


        edittext  = findViewById(R.id.edit_text1);
        addimage  = findViewById(R.id.uploadedImage);

            addImage = findViewById(R.id.imageButtonAddImage);
            addImage.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                }
            });
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
            }
        }

    }









