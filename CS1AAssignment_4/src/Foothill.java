import java.util.Scanner;

public class Foothill
{
   static final int MIN_YOGURT = 1;
   static final int MAX_STAMP = 10;
   
   public static void main(String[] args)
   {
      String yogurtNumStr, useStamp, choice;
      int yogurtStamp;
      int yogurtNumInt;
      Scanner inputStream = new Scanner(System.in);
      boolean correct;
      yogurtStamp = 0;
      
      correct = false;
      while(!correct)
      {
         System.out.print("Menu:");
         System.out.print("\nP (Process Purchase)");
         System.out.print("\nS (Shut Down)");
         System.out.print("\n\nYour Choice: ");
         choice = inputStream.next();
         if ( choice.charAt(0) == 'p' || choice.charAt(0) == 'P')
         {
            if ( yogurtStamp >= MAX_STAMP )
            {
               System.out.print("You qualify for a free yogurt. Would you"
                     + " like to use your credit? (Y or N)");
               useStamp = inputStream.next();
               if ( useStamp.charAt(0) == 'Y' || useStamp.charAt(0) == 'y')
               {
                  yogurtStamp = yogurtStamp - 10;
                  System.out.print("\nYou have just used 10 credits and "
                        + "have " + yogurtStamp + " left." + 
                        "\nEnjoy your free yogurt!\n\n");
                  continue;
               }
               else if ( useStamp.charAt(0) == 'N' || useStamp.charAt(0) 
                        == 'n')
               {
                  System.out.print("How many yogurt(s) would you like to "
                        + "buy? ");
                  yogurtNumStr = inputStream.next();
                  yogurtNumInt = Integer.parseInt(yogurtNumStr);
                  if ( yogurtNumInt >= MIN_YOGURT )
                  {
                     yogurtStamp = yogurtNumInt + yogurtStamp;
                     System.out.print("\nYou just earned " + yogurtNumInt + 
                           " stamps and have a total of " + yogurtStamp + 
                           " to use.\n\n" );
                     continue;
                  }
                  else
                     System.out.print("Error. Could not process "
                           + "transaction.\n\n");
                     continue;
               }
               else
                  System.out.print("Command not understood, please repeat"
                           + " transaction.\n\n");
                  continue;
               }
            else if (yogurtStamp < MAX_STAMP)
            {
               System.out.print("How many yogurt(s) would you like to "
                     + "buy? ");
               yogurtNumStr = inputStream.next();
               yogurtNumInt = Integer.parseInt(yogurtNumStr);
               if ( yogurtNumInt >= MIN_YOGURT )
               {
                  yogurtStamp = yogurtNumInt + yogurtStamp;
                  System.out.print("You just earned " + yogurtNumInt + 
                        " stamps and have a total of " + yogurtStamp + " to"
                        + " use.\n\n" );
               }
               else
                  System.out.print("Error. Could not process "
                        + "transaction.\n\n");
            }
         }
         else if ( choice.charAt(0) == 's' || choice.charAt(0) == 'S' )
         {
            System.out.println("Have a nice day!");
            correct = true;
         }
         else
            System.out.print("*** Use P or S, please. ***\n\n");          
      }
   }
}

/*----------------------------- Sample Run 1 ----------------------------------

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: Process
How many yogurt(s) would you like to buy? 3
You just earned 3 stamps and have a total of 3 to use.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: p
How many yogurt(s) would you like to buy? 9
You just earned 9 stamps and have a total of 12 to use.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: process
You qualify for a free yogurt. Would you like to use your credit? (Y or N)no
How many yogurt(s) would you like to buy? 6

You just earned 6 stamps and have a total of 18 to use.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: P
You qualify for a free yogurt. Would you like to use your credit? (Y or N)yes

You have just used 10 credits and have 8 left.
Enjoy your free yogurt!

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: p
How many yogurt(s) would you like to buy? 2
You just earned 2 stamps and have a total of 10 to use.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: p
You qualify for a free yogurt. Would you like to use your credit? (Y or N)n
How many yogurt(s) would you like to buy? 10

You just earned 10 stamps and have a total of 20 to use.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: shut down
Have a nice day!

-----------------------------------------------------------------------------*/
/*----------------------------- Sample Run 2 ----------------------------------

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: 100
*** Use P or S, please. ***

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: p
How many yogurt(s) would you like to buy? 100
You just earned 100 stamps and have a total of 100 to use.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: p
You qualify for a free yogurt. Would you like to use your credit? (Y or N)i
Command not understood, please repeat transaction.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: p
You qualify for a free yogurt. Would you like to use your credit? (Y or N)n
How many yogurt(s) would you like to buy? -1
Error. Could not process transaction.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: asd
*** Use P or S, please. ***

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: p
You qualify for a free yogurt. Would you like to use your credit? (Y or N)yes

You have just used 10 credits and have 90 left.
Enjoy your free yogurt!

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: p
You qualify for a free yogurt. Would you like to use your credit? (Y or N)a
Command not understood, please repeat transaction.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: Process
You qualify for a free yogurt. Would you like to use your credit? (Y or N)no
How many yogurt(s) would you like to buy? 9

You just earned 9 stamps and have a total of 99 to use.

Menu:
P (Process Purchase)
S (Shut Down)

Your Choice: S
Have a nice day!

-----------------------------------------------------------------------------*/