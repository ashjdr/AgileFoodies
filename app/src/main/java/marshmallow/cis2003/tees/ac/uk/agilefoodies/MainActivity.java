package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
    GoogleSignInClient mGoogleSignInClient;


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


    public void goToSignOut(View signOutView)
    {switch (signOutView.getId()) {
        // ...
        case R.id.action_sign_out:
            googleSignOut();
            break;
        // ...
    }
    }


    private void googleSignOut() {
   mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }




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




