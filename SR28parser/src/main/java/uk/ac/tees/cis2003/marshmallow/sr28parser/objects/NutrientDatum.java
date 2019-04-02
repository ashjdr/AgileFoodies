package uk.ac.tees.cis2003.marshmallow.sr28parser.objects;

import uk.ac.tees.cis2003.marshmallow.sr28parser.managers.ObjectManager;
import static uk.ac.tees.cis2003.marshmallow.sr28parser.util.SR28Helper.*;

public class NutrientDatum
{
    /*
    from NUT_DATA.txt
        food id
        nutrient def id
        value
        
    (currently) omitted fields
        number of data points
        standard error
        source code
        derivation code
        reference food id
        number of studies
        minimum value
        maximum value
        degrees of freedom
        lower error bound
        upper error bound
        statistical comments
        added/modified date
        confidence code
    */
    
    private Food food;
    private NutrientDef nutrient;
    private double value;

    public Food getFood()
    {
        return food;
    }

    public NutrientDef getNutrient()
    {
        return nutrient;
    }

    public double getValue()
    {
        return value;
    }
    
    public NutrientDatum(String inLine)
    {
        String[] splitLine = inLine.split(FIELD_DELIMITER);
        
        int foodId = Integer.parseInt(trimEnds(splitLine[0]));
        food = ObjectManager.getObjectManager().getFoodById(foodId);
        
        int nutrientId = Integer.parseInt(trimEnds(splitLine[1]));
        nutrient = ObjectManager.getObjectManager().getNutrientDefById(nutrientId);
        
        value = Double.parseDouble(splitLine[2]);
    }
}