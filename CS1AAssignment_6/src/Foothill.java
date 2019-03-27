import java.util.*;
import java.lang.Math;

public class Foothill
{
   static Scanner inputStream = new Scanner(System.in);
   
   public static void main (String args[])
   {
      TripleString pullString;
      int betFinal;
      int multiplier, winFinal;
      betFinal = getBet();
    
      while (betFinal != 0)
      {
         pullString = pull();
         multiplier = getPayMultiplier(pullString);
         winFinal = betFinal * multiplier;
         display(pullString, winFinal);
         betFinal = getBet();
      }
      inputStream.close();
      System.out.println("Thank You. Have a nice day!");
         
   }
   
   public static int getBet()
   {
      int betInt = 0;
      String betStr;
      boolean check;
      check = false;

      while ( check == false )
      {
         System.out.print("How much would you like to bet (1-100) or 0 "
               + "to quit? ");
         betStr = inputStream.nextLine();
         betInt = Integer.parseInt(betStr);
         if ( betInt < 0 || betInt > 100 )
         {
            check = false;
         }
         else
            check = true;
      }
      return betInt; 
   }
   
   public static TripleString pull()
   {
      TripleString thePull;
      thePull = new TripleString();
      
      thePull.setString1(randString());
      thePull.setString2(randString());
      thePull.setString3(randString());

      return thePull;
   }

   public static String randString()
   {
      String slot;
      slot = new String ("");
      double hold;

      hold = (int) (Math.random() * 100 + 1);
      if ( hold <= 50 )
      {
         slot = "BAR";
      }
      else if ( hold <= 75 )
      {
         slot = "cherries";
      }
      else if ( hold <= 87.5 )
      {
         slot = "[space]";
      }
      else if ( hold <= 100 )
      {
         slot = "7";
      }
      return slot;
   }
   
   public static int getPayMultiplier (TripleString thePull)
   {
      int prize;
      String string1, string2, string3;
      string1 = thePull.getString1();
      string2 = thePull.getString2();
      string3 = thePull.getString3();


      if ( string1.equals("cherries") &&
            !string2.equals("cherries") )
      {
         prize = 5;
      }
      else if ( string1.equals("cherries") &&
            string2.equals("cherries") && !string3.equals("cherries") )
      {
         prize = 15;
      }
      else if ( string1.equals("cherries") && string2.equals("cherries")
            && string3.equals("cherries") )
      {
      prize = 30;
      }
      else if ( string1.equals("BAR") && string2.equals("BAR") && 
            string3.equals("BAR") )
      {
         prize = 50;
      }
      else if ( string1.equals("7") && string2.equals("7") && 
            string3.equals("7") )
      {
         prize = 100;
      }
      else if ( string1.equals("BAR") && string2.equals("BAR") &&
            !string3.equals("BAR") )
      {
         prize = 0;
      }
      else if ( string1.equals("BAR") && !string2.equals("BAR") )
      {
         prize = 0;
      }
      else if ( string1.equals("7") && string2.equals("7") &&
            !string3.equals("7") )
      {
         prize = 0;
      }
      else if ( string1.equals("7") && !string2.equals("7") )
      {
         prize = 0;
      }
      else if ( string1.equals("[space]") )
      {
         prize = 0;
      }
      else
         prize = 0;

      return prize;
   }
   public static void display(TripleString thePull, int winnings)
   {
      System.out.println("whirrrrrr .... and your pull is ... ");
      System.out.print(thePull.getString1() + "   "); 
      System.out.print(thePull.getString2() + "   ");
      System.out.println(thePull.getString3());
      
      if (winnings > 0)
      {
         System.out.println("congratulations, you win: $" + winnings + "\n\n");
      }
      else
      {
         System.out.println("sorry, you lose.\n");
      }
   }  

}

class TripleString
{
   private String string1;
   private String string2;
   private String string3;
   public static final int MAX_LEN = 20;
   
   TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
   public boolean validString (String str)
   {
      if (str.length() == 0 || str.length() > MAX_LEN || str == null)
         return false;
      
      return true;
   }
   public boolean setString1(String str)
   {
      if (!validString(str))
         return false;
         
      string1 = str;
      return true;
   }
   public boolean setString2(String str)
   {
      if (!validString(str))
         return false;
         
      string2 = str;
      return true;
   }
   public boolean setString3(String str)
   {
      if (!validString(str))
         return false;
         
      string3 = str;
      return true;
   }
   public String getString1()
   {
      return string1;
   }
   public String getString2()
   {
      return string2;
   }
   public String getString3()
   {
      return string3;
   }
}

/*------------------------------ Sample Run ----------------------------------

How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   cherries
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 555
How much would you like to bet (1-100) or 0 to quit? -555
How much would you like to bet (1-100) or 0 to quit? 50
whirrrrrr .... and your pull is ... 
BAR   cherries   BAR
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? -1
How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   BAR
congratulations, you win: $250


How much would you like to bet (1-100) or 0 to quit? 4
whirrrrrr .... and your pull is ... 
7   BAR   cherries
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 3
whirrrrrr .... and your pull is ... 
cherries   BAR   cherries
congratulations, you win: $15


How much would you like to bet (1-100) or 0 to quit? 45
whirrrrrr .... and your pull is ... 
BAR   cherries   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 45
whirrrrrr .... and your pull is ... 
cherries   BAR   BAR
congratulations, you win: $225


How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
7   BAR   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   BAR
congratulations, you win: $250


How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   BAR
congratulations, you win: $250


How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   BAR
congratulations, you win: $250


How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   cherries
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   cherries
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   cherries   BAR
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   BAR   BAR
congratulations, you win: $250


How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   cherries   cherries
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 5
whirrrrrr .... and your pull is ... 
BAR   cherries   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 45
whirrrrrr .... and your pull is ... 
cherries   cherries   [space]
congratulations, you win: $675


How much would you like to bet (1-100) or 0 to quit? 45
whirrrrrr .... and your pull is ... 
BAR   BAR   BAR
congratulations, you win: $2250


How much would you like to bet (1-100) or 0 to quit? 45
whirrrrrr .... and your pull is ... 
BAR   BAR   BAR
congratulations, you win: $2250


How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
cherries   BAR   BAR
congratulations, you win: $450


How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
BAR   cherries   [space]
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
BAR   BAR   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
cherries   BAR   cherries
congratulations, you win: $450


How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
BAR   cherries   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
BAR   BAR   cherries
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
[space]   cherries   BAR
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
cherries   BAR   cherries
congratulations, you win: $450


How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
BAR   cherries   BAR
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
cherries   7   BAR
congratulations, you win: $450


How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
cherries   cherries   BAR
congratulations, you win: $1350


How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
BAR   cherries   BAR
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
BAR   BAR   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
BAR   BAR   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
cherries   cherries   BAR
congratulations, you win: $1350


How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
7   cherries   7
sorry, you lose.

How much would you like to bet (1-100) or 0 to quit? 90
whirrrrrr .... and your pull is ... 
cherries   cherries   BAR
congratulations, you win: $1350


How much would you like to bet (1-100) or 0 to quit? 0
Thank You. Have a nice day!
-----------------------------------------------------------------------------*/