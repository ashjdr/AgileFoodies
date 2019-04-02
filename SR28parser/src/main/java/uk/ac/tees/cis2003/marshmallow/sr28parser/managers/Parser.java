/*
 * File parser for SR28 database
 * Contains methods for iteratively parsing the text files in the SR28 database
 */
package uk.ac.tees.cis2003.marshmallow.sr28parser.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser
{
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            // TODO: proper error/help messages
            System.out.println("Oi, that's not how you use this! Tell me where the thing is!");
            return;
        }
        
        try
        {
            parseFiles(args[0]);
        } catch (FileNotFoundException ex)
        {
            System.out.println("Oi! That's the wrong bloody place!");
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            System.out.println("Oh no! Something went super wrong! It's probably your bloody fault!");
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void parseFiles(String location) throws FileNotFoundException, IOException
    {
        File fd_group = new File(location+"/FD_GROUP.txt");
        File nutr_def = new File(location+"/NUTR_DEF.txt");
        File food_des = new File(location+"/FOOD_DES.txt");
        File nut_data = new File(location+"/NUT_DATA.txt");
        File weight = new File(location+"/WEIGHT.txt");
        
        // BufferedReader used here because these files break Scanner... somehow. 
        // Also, it fails to recognise the micro character (µ) and I have no idea why
        
        BufferedReader br = new BufferedReader(new FileReader(fd_group));
        try 
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                ObjectManager.newFoodGroupFromLine(line);
            }
        } finally
        {
            br.close();
        }
        
        br = new BufferedReader(new FileReader(nutr_def));
        try 
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
                ObjectManager.newNutrientDefFromLine(line);
            }
        } finally
        {
            br.close();
        }
        
        br = new BufferedReader(new FileReader(food_des));
        try 
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                ObjectManager.newFoodFromLine(line);
            }
        } finally
        {
            br.close();
        }
        
        br = new BufferedReader(new FileReader(nut_data));
        try 
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                ObjectManager.newNutrientDatumFromLine(line);
            }
        } finally
        {
            br.close();
        }
        
        br = new BufferedReader(new FileReader(weight));
        try 
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                ObjectManager.newWeightFromLine(line);
            }
        } finally
        {
            br.close();
        }
    }
}