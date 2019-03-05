/*
 * File parser for SR28 database
 * Contains methods for iteratively parsing the text files in the SR28 database
 */
package uk.ac.tees.cis2003.marshmallow.sr28parser.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser 
{
    public static void parseFiles(String location) throws FileNotFoundException
    {
        File fd_group = new File(location+"/FD_GROUP.txt");
        File nutr_def = new File(location+"/NUTR_DEF.txt");
        File food_des = new File(location+"/FOOD_DES.txt");
        File nut_data = new File(location+"/NUT_DATA.txt");
        File weight = new File(location+"/WEIGHT.txt");
        
        Scanner scanner = new Scanner(fd_group);
        while (scanner.hasNextLine())
        {
            ObjectManager.getObjectManager().newFoodGroupFromLine(scanner.nextLine());
        }
        scanner = new Scanner(nutr_def);
        while (scanner.hasNextLine())
        {
            ObjectManager.getObjectManager().newNutrientDefFromLine(scanner.nextLine());
        }
        scanner = new Scanner(food_des);
        while (scanner.hasNextLine())
        {
            ObjectManager.getObjectManager().newFoodFromLine(scanner.nextLine());
        }
        scanner = new Scanner(nut_data);
        while (scanner.hasNextLine())
        {
            ObjectManager.getObjectManager().newNutrientDatumFromLine(scanner.nextLine());
        }
        scanner = new Scanner(weight);
        while (scanner.hasNextLine())
        {
            ObjectManager.getObjectManager().newWeightFromLine(scanner.nextLine());
        }
    }
}
