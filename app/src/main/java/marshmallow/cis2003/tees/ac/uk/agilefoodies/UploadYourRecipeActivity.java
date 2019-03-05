package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UploadYourRecipeActivity extends AppCompatActivity {
    private Button addImage;
    private EditText edittext;
    private ImageView addimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_your_recipe);
        addImage  = findViewById(R.id.button_set);
        edittext  = findViewById(R.id.edit_text1);
        addimage  = findViewById(R.id.imageView);


    }
}
