import java.util.Scanner;
import java.text.*;
import java.util.*;

public class Foothill
{
   public static void main(String[] args)
   {
      PizzaOrder pizza = new PizzaOrder();
      String input;
      char initial;
      int initialInt;
      boolean check = true;
      double price;
      Scanner inputStream = new Scanner(System.in);

      while ( true )
      {
         initial = getSizeFromUser();

         if ( initial == 'S' || initial == 's' )
         {
            pizza.setSize(0);
         }
         else if ( initial == 'M' || initial == 'm' )
         {
            pizza.setSize(1);
         }
         else if ( initial == 'L' || initial == 'l' )
         {
            pizza.setSize(2);
         }
         else if ( initial == 'Q' || initial == 'q')
         {
            System.out.println("Thank You for your visit.");
            break;
         }

         while ( true )
         {
            pizza.displayCurrent();
            displayMainMenu();
            System.out.print("Selection: ");
            input = inputStream.nextLine();
            initialInt = Integer.parseInt(input);;

            if ( initialInt == 0)
            {
               check = true;
               System.out.print("Your Order: ");
               pizza.displayPizza();
               price = pizza.getPrice();
               NumberFormat tidy = NumberFormat.getInstance(Locale.US);
               tidy.setMaximumFractionDigits(2);
               tidy.setMinimumFractionDigits(2);
               System.out.println("Total Price: $" + tidy.format(price) + "\n");
               pizza.resetTopping();
               break;
            }
            pizza.addTopping(initialInt);
         }
      }
      }
      
      
      public static void displayMainMenu()
      {
         int i;
         
         System.out.println("Select an item by number (0 when done): ");
         for( i = 0; i < PizzaOrder.toppingsOffered.length; i++)
         {
            int k = i + 1;
            System.out.println(" " + k  + ". "+ PizzaOrder.toppingsOffered[i]);
         }
            
      }
      
      public static char getSizeFromUser()
      {
         char input = 0;
         String choice;
         Scanner inputStream = new Scanner(System.in);
         boolean check = true;

         while( check == true)
         {
            System.out.print("Size of pizza ('s', 'm', 'l') or 'q' to quit: ");
            choice = inputStream.nextLine();
            if ( choice.charAt(0) == 'S' || choice.charAt(0) == 's' )
            {
               input = choice.charAt(0);
               check = false;
            }
            else if ( choice.charAt(0) == 'M' || choice.charAt(0) == 'm' )
            {
               input = choice.charAt(0);
               check = false;
            }
            else if ( choice.charAt(0) == 'L' || choice.charAt(0) == 'l' )
            {
               input = choice.charAt(0);
               check = false;
            }
            else if ( choice.charAt(0) == 'Q' || choice.charAt(0) == 'q' )
            {
               input = choice.charAt(0);
               check = false;
            }
            else
               check = true;
         }
          return input;
      }
   }

class PizzaOrder
{
   public static final String[] toppingsOffered = 
      {"Onion", "Bell Peppers", "Olives", "Pepperoni", "Mushroom", "Tomato"};
   public static final double toppingsBaseCost= 3.00;
   public static final double basePrice = 10.00;
   public static final int LIMIT_CHOICE = 7;
   public static final int MAX_TOPPINGS = 5;
   
   private int size;
   private String[] toppings = new String[MAX_TOPPINGS];
   private int numToppings;
   
   PizzaOrder()
   {
      size = 0;
      numToppings = 0;
   }
   
   public PizzaOrder(int size)
   {
      if ( size == 0 )
      {
         this.size = size;
      }
      else if ( size == 1 )
      {
         this.size = size;
      }
      else if ( size == 2 )
      {
         this.size = size;
      }
      numToppings = 0;
   }
   
   public boolean setSize(int size)
   {
      if ( size < 0 || size > 2 )
      {
         return false;
      }
      else
         this.size = size;
      return true;
   }
   
   public String getToppings()
   {
      String addedtoppings;
      addedtoppings = "";
      int i;
      
      for ( i = 0; i < numToppings; i++ )
         addedtoppings = addedtoppings + " + " + toppings[i];
      return addedtoppings;
   }
   
   public boolean addTopping(String topping)
   {
      if ( numToppings < MAX_TOPPINGS)
      {
         toppings[numToppings] = topping;
         numToppings++;
         return true;
      }
      else
         return false;
   }
   
   public boolean addTopping(int n)
   {
      if (numToppings < MAX_TOPPINGS && n < LIMIT_CHOICE)
      {
         toppings[numToppings] = toppingsOffered[n-1];
         numToppings++;
         return true;
      }
      else
         return false;
   }
   
   public String stringizeSize()
   {
      String sizepizza;

      if ( size == 0 )
      {
         sizepizza = "Small";
      }
      else if ( size == 1 )
      {
         sizepizza = "Medium";
      }
      else
      {
         sizepizza = "Large";
      }

      return sizepizza;
   }
   
   public double getPrice()
   {
      double bill = 0 ;

      if (size == 0)
      {
         bill = basePrice + (numToppings*toppingsBaseCost);
      }
      if (size == 1)
      {
         bill = (basePrice + (numToppings*toppingsBaseCost)) * 1.15;
      }
      if (size == 2)
      {
         bill = (basePrice + (numToppings*toppingsBaseCost)) * 1.25;
      }
      return bill;
   }
   
   public int getSize()
   {
      return size;
   }
   
   public void displayPizza()
   {
      System.out.println(stringizeSize() + getToppings());
   }
   
   public void displayCurrent()
   {
      int i;
      
      System.out.print("\nCurrent size: " + stringizeSize());
      for( i = 0; i < numToppings; i++ )
         System.out.print(" + " + toppings[i]);
      System.out.println();
      
   }
   
   public void resetTopping()
   {
      int i; 
      
      for( i = 0; i < numToppings; i++ )
      {
         toppings[i] = null;
      }
      numToppings = 0;
   }
}

/* ---------------------------- paste of run ------------------------------
 * 
Size of pizza ('s', 'm', 'l') or 'q' to quit: e
Size of pizza ('s', 'm', 'l') or 'q' to quit: 9
Size of pizza ('s', 'm', 'l') or 'q' to quit: jump
Size of pizza ('s', 'm', 'l') or 'q' to quit: M

Current size: Medium
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 2

Current size: Medium + Bell Peppers
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 3

Current size: Medium + Bell Peppers + Olives
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 5

Current size: Medium + Bell Peppers + Olives + Mushroom
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 0
Your Order: Medium + Bell Peppers + Olives + Mushroom
Total Price: $21.85

Size of pizza ('s', 'm', 'l') or 'q' to quit: large

Current size: Large
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 1

Current size: Large + Onion
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 6

Current size: Large + Onion + Tomato
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 2

Current size: Large + Onion + Tomato + Bell Peppers
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 4

Current size: Large + Onion + Tomato + Bell Peppers + Pepperoni
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 0
Your Order: Large + Onion + Tomato + Bell Peppers + Pepperoni
Total Price: $27.50

Size of pizza ('s', 'm', 'l') or 'q' to quit: s

Current size: Small
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 5

Current size: Small + Mushroom
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 2

Current size: Small + Mushroom + Bell Peppers
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 3

Current size: Small + Mushroom + Bell Peppers + Olives
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 4

Current size: Small + Mushroom + Bell Peppers + Olives + Pepperoni
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 1

Current size: Small + Mushroom + Bell Peppers + Olives + Pepperoni + Onion
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 0
Your Order: Small + Mushroom + Bell Peppers + Olives + Pepperoni + Onion
Total Price: $25.00

Size of pizza ('s', 'm', 'l') or 'q' to quit: L

Current size: Large
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 2

Current size: Large + Bell Peppers
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 3

Current size: Large + Bell Peppers + Olives
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 4

Current size: Large + Bell Peppers + Olives + Pepperoni
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 1

Current size: Large + Bell Peppers + Olives + Pepperoni + Onion
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 6

Current size: Large + Bell Peppers + Olives + Pepperoni + Onion + Tomato
Select an item by number (0 when done): 
 1. Onion
 2. Bell Peppers
 3. Olives
 4. Pepperoni
 5. Mushroom
 6. Tomato
Selection: 0
Your Order: Large + Bell Peppers + Olives + Pepperoni + Onion + Tomato
Total Price: $31.25

Size of pizza ('s', 'm', 'l') or 'q' to quit: Q
Thank You for your visit.

----------------------------------------------------------------------- */