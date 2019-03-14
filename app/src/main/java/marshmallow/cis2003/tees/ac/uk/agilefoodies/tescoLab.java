package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by t7062534 on 26/02/19.
 *
 */

public class tescoLab extends AppCompatActivity {


    //private static final String TAG = "";
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private EditText querys;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tesco_lab);
        Intent intent = getIntent();

        //setContentView(R.layout.activity_main);

        mTextViewResult =findViewById(R.id.text_view_result);

        Button buttonParse = findViewById(R.id.button_parse);

        querys = findViewById(R.id.editText);


        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                try {

//                    Tesco LAb
//                    Recycler layout
//                    Array adapter

                    // Intent intent=new Intent(tescoLab.this,tescoLab.class);
                 //   startActivity(intent);
                    jsonParse(querys);
                  //  finish();
                } catch (AuthFailureError authFailureError) {
                    authFailureError.printStackTrace();
                }
            }
        });

        //START OF AD CODE
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AdFragment())
                    .commit();
        }

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //END OF AD CODE
    }
    private String readMetadata(){

        try {
            final ApplicationInfo ai = getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            return ai.metaData.getString("key_tesco");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }



    private void jsonParse(EditText q) throws AuthFailureError {

        String urlStart = "https://dev.tescolabs.com/grocery/products/?query=";

        String urlEnd ="&offset=0&limit=";
        String limt = "10";
        String query = q.getText().toString();

        String url= urlStart+query+urlEnd+limt;

        //Host: dev.tescolabs.com
        //Ocp-Apim-Subscription-Key: a78d89bd412f49c8b8c9532af8e1d4c2
        // need to edit this !!!

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray result = response
                                    .getJSONObject("uk")
                                    .getJSONObject("ghs")
                                    .getJSONObject("products")
                                    .getJSONArray("results");


                            for (int i = 0; i < result.length(); i++) {
                                JSONObject items = result.getJSONObject(i);



                                String image = items.getString("image");

                                String superDepartment = items.getString("superDepartment");
                                int tpnb = items.getInt("tpnb");
                                String ContentsMeasureType = items.getString("ContentsMeasureType"); // no value
                                String name = items.getString("name");

                                int unitOfSale = items.getInt("UnitOfSale");
                                String des = items.getString("description");
                                int AverageSellingUnitWeight = items.getInt("AverageSellingUnitWeight");
                                String UnitQuantity = items.getString("UnitQuantity");
                                int id = items.getInt("id");
                                int ContentsQuantity = items.getInt("ContentsQuantity");
                                String department = items.getString("department");

                                double price = items.getDouble("price");

                                double unitprice = items.getDouble("unitprice");

                                //ImageView iv = new ImageView;

                                // Picasso.with(Context).load(image).into(iv);


                                mTextViewResult.append(name  + ", £" + String.valueOf(price) + "\n\n");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Ocp-Apim-Subscription-Key", readMetadata());
                return headers;
            }
        };


        mQueue.add(request);
    }
}
