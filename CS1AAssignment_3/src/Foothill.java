import java.util.Scanner;

public class Foothill
{
   static final String FOOD_1_NAME = "flour";
   static final int FOOD_1_CALORIES_P100G = 364;
   static final double FOOD_1_FAT_P100G = 0.98;
   static final double FOOD_1_CARBS_P100G = 76.31;
   static final double FOOD_1_PROTEIN_P1OOG = 10.33;

   static final String FOOD_2_NAME = "sugar";
   static final int FOOD_2_CALORIES_P100G = 387;
   static final double FOOD_2_FAT_P100G = 0.0;
   static final double FOOD_2_CARBS_P100G = 99.98;
   static final double FOOD_2_PROTEIN_P100G = 0.0;

   static final String FOOD_3_NAME = "brown sugar";
   static final int FOOD_3_CALORIES_P100G = 377;
   static final double FOOD_3_FAT_P100G = 0.0;
   static final double FOOD_3_CARBS_P100G = 97.33;
   static final double FOOD_3_PROTEIN_P100G = 0;

   static final String FOOD_4_NAME = "cocoa powder";
   static final int FOOD_4_CALORIES_P100G = 229;
   static final double FOOD_4_FAT_P100G = 13.7;
   static final double FOOD_4_CARBS_P100G = 54.3;
   static final double FOOD_4_PROTEIN_P100G = 19.6;

   static final String INDENT = "   ";
   static final String SEPARATOR = "\n";

   public static void main(String[] args)
   {
      String recipeName, userInputStr, servingNumStr;
      int userInputInt, servingNumInt;
      double totalFat, totalCals, totalCarbs, totalProtein;
      Scanner inputStream = new Scanner(System.in);

      totalFat = 0.0;
      totalCals = 0.0;
      totalCarbs = 0.0;
      totalProtein = 0.0;

      System.out.println("---------- List of Possible Ingredients ---------");
      System.out.println(INDENT + "Food #1: " + FOOD_1_NAME);
      System.out.println(INDENT + "Food #2: " + FOOD_2_NAME);
      System.out.println(INDENT + "Food #3: " + FOOD_3_NAME);
      System.out.println(INDENT + "Food #4: " + FOOD_4_NAME + SEPARATOR);

      System.out.print("What are you calling this recipe ? ");
      recipeName = inputStream.nextLine();

      System.out.print("Number of servings? ");
      servingNumStr = inputStream.nextLine();
      servingNumInt = Integer.parseInt(servingNumStr);

      if (servingNumInt < 1 || servingNumInt > 10)
      {
         System.out.println("Error");
         System.exit(0);
      }

      System.out.print("How many grams of " + FOOD_1_NAME + "? ");
      userInputStr = inputStream.nextLine();
      userInputInt = Integer.parseInt(userInputStr);

      if (userInputInt < 0 || userInputInt > 1000)
      {
         System.out.println("Error");
         System.exit(0);
      }

      totalCals += userInputInt * (FOOD_1_CALORIES_P100G / 100.);
      totalFat += userInputInt * (FOOD_1_FAT_P100G / 100.);
      totalCarbs += userInputInt * (FOOD_1_CARBS_P100G / 100.);
      totalProtein += userInputInt * (FOOD_1_CARBS_P100G / 100.);

      System.out.print("How many grams of " + FOOD_2_NAME + "? ");
      userInputStr = inputStream.nextLine();
      userInputInt = Integer.parseInt(userInputStr);

      if (userInputInt < 0 || userInputInt > 1000)
      {
         System.out.println("Error");
         System.exit(0);
      }

      totalCals += userInputInt * (FOOD_2_CALORIES_P100G / 100.);
      totalFat += userInputInt * (FOOD_2_FAT_P100G / 100.);
      totalCarbs += userInputInt * (FOOD_2_CARBS_P100G / 100.);
      totalProtein += userInputInt * (FOOD_2_PROTEIN_P100G / 100.);

      System.out.print("How many grams of " + FOOD_3_NAME + "? ");
      userInputStr = inputStream.nextLine();
      userInputInt = Integer.parseInt(userInputStr);

      if (userInputInt < 0 || userInputInt > 1000)
      {
         System.out.println("Error");
         return;
      }

      totalCals += userInputInt * (FOOD_3_CALORIES_P100G / 100.);
      totalFat += userInputInt * (FOOD_3_FAT_P100G / 100.);
      totalCarbs += userInputInt * (FOOD_3_CARBS_P100G / 100.);
      totalProtein += userInputInt * (FOOD_3_PROTEIN_P100G / 100.);

      System.out.print("How many grams of " + FOOD_4_NAME + "? ");
      userInputStr = inputStream.nextLine();
      userInputInt = Integer.parseInt(userInputStr);

      if (userInputInt < 0 || userInputInt > 1000)
      {
         System.out.println("Error");
         return;
      }

      totalCals += userInputInt * (FOOD_4_CALORIES_P100G / 100.);
      totalFat += userInputInt * (FOOD_4_FAT_P100G / 100.);
      totalCarbs += userInputInt * (FOOD_4_CARBS_P100G / 100.);
      totalProtein += userInputInt * (FOOD_4_PROTEIN_P100G / 100.);

      totalCals = totalCals / servingNumInt;
      totalFat = totalFat / servingNumInt;
      totalCarbs = totalCarbs / servingNumInt;
      totalProtein = totalProtein / servingNumInt;
      inputStream.close();
      
      System.out.println("\nNutrition per Serving for " + recipeName
            + "------------");
      System.out.println(INDENT + "Calories: " + totalCals);
      System.out.println(INDENT + "Fat: " + totalFat);
      System.out.println(INDENT + "Carbs: " + totalCarbs);
      System.out.println(INDENT + "Protein: " + totalProtein);
   }
}

/* -------------------- Sample Run #1  ------------------

---------- List of Possible Ingredients ---------
Food #1: flour
Food #2: sugar
Food #3: brown sugar
Food #4: cocoa powder

What are you calling this recipe ? Apple Pie
Number of servings? 8
How many grams of flour? 800
How many grams of sugar? 650
How many grams of brown sugar? 400
How many grams of cocoa powder? 550

Nutrition per Serving for Apple Pie------------
Calories: 1024.375
Fat: 10.39875
Carbs: 243.53999999999996
Protein: 89.785

-------------------- Sample Run #2  ------------------

---------- List of Possible Ingredients ---------
   Food #1: flour
   Food #2: sugar
   Food #3: brown sugar
   Food #4: cocoa powder

What are you calling this recipe ? Pumpkin Cupcake
Number of servings? 9
How many grams of flour? 950
How many grams of sugar? 450
How many grams of brown sugar? 600
How many grams of cocoa powder? 250

Nutrition per Serving for Pumpkin Cupcake------------
   Calories: 892.6666666666666
   Fat: 4.84
   Carbs: 210.50944444444445
   Protein: 85.99388888888889

-------------------- Sample Run #3  ------------------

---------- List of Possible Ingredients ---------
   Food #1: flour
   Food #2: sugar
   Food #3: brown sugar
   Food #4: cocoa powder

What are you calling this recipe ? Pumpkin Pie
Number of servings? 5
How many grams of flour? 888
How many grams of sugar? 590
How many grams of brown sugar? 930
How many grams of cocoa powder? 432

Nutrition per Serving for Pumpkin Pie------------
   Calories: 2002.2000000000003
   Fat: 13.577279999999998
   Carbs: 481.45196
   Protein: 152.46096

-------------------- Sample Run #4  ------------------

---------- List of Possible Ingredients ---------
   Food #1: flour
   Food #2: sugar
   Food #3: brown sugar
   Food #4: cocoa powder

What are you calling this recipe ? Black Forest
Number of servings? 6
How many grams of flour? 850
How many grams of sugar? 789
How many grams of brown sugar? 2000
Error

-------------------- Sample Run #5  ------------------

---------- List of Possible Ingredients ---------
   Food #1: flour
   Food #2: sugar
   Food #3: brown sugar
   Food #4: cocoa powder

What are you calling this recipe ? Croissant
Number of servings? 20
Error

----------------------------------------------------- */