package uk.ac.tees.cis2003.marshmallow.sr28parser.managers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.Lists;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author s6115598
 */
public class Firebaser 
{
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException
    {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("auth.json"))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setProjectId("agilefoodies")
                .build();
        FirebaseApp.initializeApp(options);
        
        Firestore db = FirestoreClient.getFirestore();
        
        /*
        ApiFuture<QuerySnapshot> query = db.collection("testColl").get();
        
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents)
        {
            System.out.println(document.getId());
            System.out.println(document.getString("test"));
            System.out.println(document.getLong("test1"));
            System.out.println(document.getString("test2"));
        }
        //*/
        
        //*
        DocumentReference docRef = db.collection("testColl").document("testDoc");
        Map<String, Object> data = new HashMap<>();
        data.put("test", "Test data weee");
        data.put("test1", 345);
        data.put("test2", "woah");
        
        Map<String, Object> nestedData = new HashMap<>();
        data.put("nestedTest", nestedData);
        nestedData.put("test1.1", "nested test datum 1");
        nestedData.put("test1.2", "nested test datum 2");
        
        ArrayList<Object> testArray = new ArrayList<>();
        Collections.addAll(testArray, "test array datum 1", "test array datum 2", "combo breaker");
        data.put("testArray", testArray);
        
        ApiFuture<WriteResult> result = docRef.set(data);
        
        System.out.println("Update time : " + result.get().getUpdateTime());
        //*/
    }
}
