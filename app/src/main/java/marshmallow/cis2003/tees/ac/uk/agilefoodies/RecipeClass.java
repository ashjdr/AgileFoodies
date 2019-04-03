package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.lang.reflect.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class RecipeClass {


    private String name;
    private int time;
    private boolean vegan;
    private boolean vegetarian;
    private String category;

    private int ratingCount;
    private int ratingValue;
    private Timestamp timeCreated;
    private List<String> ingredients;
    private List<String> instructions;

    public RecipeClass() {
    }


    public RecipeClass(String name, int time, boolean vegan, boolean vegetarian, String category, int ratingCount, int ratingValue,  List<String> ingredients, List<String> instructions) {
        // [START_EXCLUDE]
        this.name = name;
        this.time = time;
        this.vegan = vegan;
        this.vegetarian = vegetarian;
        this.category = category;
        this.ratingCount = ratingCount;

        this.ratingValue = ratingValue;
        this.timeCreated = timeCreated;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }
    public int getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegan() {
        return vegan;
    }
    public boolean isVegetarian() {
        return vegetarian;
    }
    public String getCategory() {
        return category;
    }
    public int getRatingCount() {
        return ratingCount;
    }
    public int getRatingValue() {
        return ratingValue;
    }
    public Timestamp getTimeCreated() {
        return timeCreated;
    }
    public List<String> getIngredients() {
        return ingredients;
    }
    public List<String> getInstructions() {
        return instructions;
    }






}
