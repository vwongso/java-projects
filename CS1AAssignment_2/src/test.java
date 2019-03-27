import java.util.*;

public class test
{

   public static void main (String[]args) throws Exception
   {
      int k, count =
      0;
      
      for ( k = 0;  k < 200;  k++);
      count++;
      
      System.out.println(count);
//      char keyCharacter = getKeyCharacter();
//      String theString;
//      theString = new String(getString());
//      System.out.print(maskLetter(theString,keyCharacter));
//      System.out.print(countKey(theString,keyCharacter));
//      System.out.print(removeCharacter(theString,keyCharacter));
   }
   public static char getKeyCharacter()
   {
      String userInput;
      Scanner input = new Scanner(System.in);
      char keyCharacter = ' ';
      boolean check;
      check = false;
      
      while (check == false)
      {
         System.out.print("Please enter a SINGLE letter to act as key: ");
         userInput = input.nextLine();
         if ( userInput.length() == 1 )
         {
            keyCharacter = userInput.charAt(0);
            check = true;
         }
         else
            check = false;
      }
      return keyCharacter; 
   }
   public static String getString()
   {
      String theString;
      theString = new String ("");
      Scanner input = new Scanner(System.in);
      boolean check;
      check = false;
      
      while (check == false)
      {
         System.out.print("Please enter a phrase or sentence >= 4 letters: ");
         theString = input.nextLine();
         if (theString.length() >= 4)
         {
            check = true;
         }
      }
      return theString;
   }
   public static String maskLetter(String theString, char keyCharacter)
   {
      int count;
      int number = theString.length();
      String userInput = new String();

      System.out.print("\nString with key character, '" + keyCharacter 
            + "' masked: \n");
      for ( count = 0 ; count < number ; count++ )
      {
         if ( theString.charAt(count) == keyCharacter )
         {
            userInput += '-';
         }
         else
            userInput += theString.charAt(count);
      }
      return userInput;
   }
   public static int countKey(String theString, char keyCharacter)
   {
      int i;
      int number = theString.length();
      int sum = 0;

      System.out.print("\n\n# of occurence, '" + keyCharacter + "' masked: ");
      for ( i = 0 ; i < number ; i++ )
      {
         if ( theString.charAt(i) == keyCharacter)
            sum = sum + 1;
      }
      return sum;
   }
   public static String removeCharacter(String theString, char keyCharacter)
   {
      int count;
      String sentence = "";
      int number = theString.length();

      System.out.print("\n\nString with key character, '" + keyCharacter 
            + "' removed: \n");
      for ( count = 0 ; count < number ; count++ )
      {
         if ( theString.charAt(count) != keyCharacter)
            sentence = sentence + theString.charAt(count);
      }
      return sentence;
   }
}

