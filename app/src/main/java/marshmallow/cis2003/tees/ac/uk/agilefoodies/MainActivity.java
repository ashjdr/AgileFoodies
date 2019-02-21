package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
// Juli



// Jack



// Matthew



// Marshall



// Ash



public class MainActivity extends AppCompatActivity {

    // Juli



    // Jack



    // Matthew



    // Marshall



    // Ash



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

        // Juli
        public void goToTimerActivity (View view){
            Intent intent = new Intent (this, TimerActivity.class);
            startActivity(intent);
        }


//    public void goToSignOut(View signOutView)
//    {switch (signOutView.getId()) {
//        // ...
//        case R.id.action_sign_out:
//            googleSignOut();
//            break;
//        // ...
//    }
//    }
//
////    GoogleSignInClient mGoogleSignInClient =getIntent().getParcelableExtra();
//
////need to pass the sign in client from login in to main
//            private void googleSignOut() {
//                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getParent());
//
//                acct.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                    }
//                });
//    }




    // Jack



        // Matthew



        // Marshall



        // Ash



    }

    // Juli



    // Jack



    // Matthew



    // Marshall



    // Ash




