package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import java.sql.Timestamp;
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
        this.ingredients = ingredients;
        this.instructions = instructions;
    }


    public void addIngredient(String ing)
    {
        ingredients.add(ing);
        //to add an ingredient to the list
    }

    public void addStep(String step, int position)
    {
        //needs to itterate through to the position and then add the step.
    }

    public void addStep(String step)
    {
        instructions.add(step);
        //to add a step onto the end
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
