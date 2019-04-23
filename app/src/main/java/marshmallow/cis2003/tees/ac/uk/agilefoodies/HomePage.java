package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RecipeFragment.OnFragmentInteractionListener, SearchRecipeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_home_page2);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //START OF AD CODE
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new AdFragment())
                    .commit();
        }

        //setting the initial view of the page to the homepage
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFragment, new HomePageFragment())
                .commit();
        //end

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //END OF AD CODE


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_account){
            Intent intent=new Intent(HomePage.this,LoginFragment.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.tesco_lab) {
            // Handle the camera action

            Intent intent=new Intent(HomePage.this,tescoLab.class);
            startActivity(intent);
        }

        else if (id == R.id.recipe_activity) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, new UploadYourRecipeFragment())
                    .commit();
        }

        else if (id == R.id.timer_fragment){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, new TimerFragment())
                    .commit();
        }

        else if (id == R.id.recipe_search_fragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, new SearchRecipeFragment())
                    .commit();
        }

        else if (id == R.id.nav_locate) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, new FindNearestShopFragment())
                    .commit();
        }

        else if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, new HomePageFragment())
                    .commit();
        }

        else if (id == R.id.nav_review) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, new ReviewFragment())
                    .commit();
        }

        else if (id == R.id.nav_foodbank) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, new FoodbankFragment())
                    .commit();
        }

        else if (id == R.id.nav_converter) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, new UnitConverterFragment())
                    .commit();
        }

        else if (id == R.id.nav_share) {
            //action needed
        } else if (id == R.id.nav_send) {
            //action needed
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override

    public void onFragmentInteraction(Long time, String name) {
        TimerFragment fragTwo = new TimerFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFragment, fragTwo)
                .commitNow();
        fragTwo.onFragmentInteraction(time, name);
    }

    public void onFragmentInteraction(String textEntered, String queryType)
    {
        RecipeFragment fragOne = new RecipeFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFragment, fragOne)
                .commit();
        fragOne.onFragmentInteraction(textEntered, queryType );
    }


}


