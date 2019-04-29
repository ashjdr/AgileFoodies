package marshmallow.cis2003.tees.ac.uk.agilefoodies;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class LoginActivity extends AppCompatActivity{
        private static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;




    @Override
    public void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignInButton googleSignInButton = findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            onLoggedIn(alreadyloggedAccount);
        }
        else {
            Log.d(TAG, "Not logged in");
        }
    }


           @Override
 public void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_OK)
         switch (requestCode) {
             case 101:
                 try {
                     // The Task returned from this call is always completed, no need to attach
                     // a listener.
                     Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                     GoogleSignInAccount account = task.getResult(ApiException.class);
                     String idToken = account.getIdToken();
                     onLoggedIn(account);
                 } catch (ApiException e) {
                     // The ApiException status code indicates the detailed failure reason.
                     Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                 }
                 break;
         }
 }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
    }
}

