package marshmallow.cis2003.tees.ac.uk.agilefoodies;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.lang.reflect.Array;


public class LoginFragment extends Fragment {
        private static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.ad_fragment, container, false);

        SignInButton googleSignInButton = v.findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        if (alreadyloggedAccount != null) {
            Toast.makeText(getContext(), "Already Logged In", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        intent.putExtra(ProfileActivity.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
    }
}

