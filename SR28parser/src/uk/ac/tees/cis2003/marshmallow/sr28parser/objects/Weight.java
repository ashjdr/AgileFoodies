/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2003.marshmallow.sr28parser.objects;

import uk.ac.tees.cis2003.marshmallow.sr28parser.managers.ObjectManager;
import static uk.ac.tees.cis2003.marshmallow.sr28parser.util.SR28Helper.*;

public class Weight 
{
    /*
    from WEIGHT.txt
        food id
        sequence number
        amount
        description
        gram weight
    
    (currently) omitted fields
        data points
        standard deviation
    */
    
    private Food food;
    private int seq;
    private double amount;
    private String description;
    private double gramWeight;

    public Food getFood()
    {
        return food;
    }

    public int getSeq()
    {
        return seq;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getDescription()
    {
        return description;
    }

    public double getGramWeight()
    {
        return gramWeight;
    }
    
    public Weight(String inLine)
    {
        String[] splitLine = inLine.split(FIELD_DELIMITER);
        int foodId = Integer.parseInt(trimEnds(splitLine[0]));
        food = ObjectManager.getObjectManager().getFoodById(foodId);
        seq = Integer.parseInt(splitLine[1]);
        amount = Double.parseDouble(splitLine[2]);
        description = trimEnds(splitLine[3]);
        gramWeight = Double.parseDouble(splitLine[4]);
    }
}
