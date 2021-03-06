package marshmallow.cis2003.tees.ac.uk.agilefoodies;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;


public class ProfileActivity extends AppCompatActivity  {
        public static final String GOOGLE_ACCOUNT = "google_account";
        private TextView profileName, profileEmail;
        private ImageView profileImage;
    private GoogleSignInClient googleSignInClient;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_profile);

            profileName = findViewById(R.id.profile_text);
            profileEmail = findViewById(R.id.profile_email);
            profileImage = findViewById(R.id.profile_image);
            Button signOut = findViewById(R.id.sign_out);



            setDataOnView();

            signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(ProfileActivity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
            });

            }
    private void setDataOnView() {
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);

        Context context = this;
        Picasso.with(context).load(googleSignInAccount.getPhotoUrl()).into(profileImage);
        profileName.setText(googleSignInAccount.getDisplayName());
        profileEmail.setText(googleSignInAccount.getEmail());
    }

    @SuppressWarnings("EmptyMethod")
    public void onClick(View view)
    {

    }




}






