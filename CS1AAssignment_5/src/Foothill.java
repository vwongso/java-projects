import java.util.Scanner;

public class Foothill
{

   public static void main (String[]args)
   {
      char keyCharacter = getKeyCharacter();
      String theString;
      theString = new String(getString());
      System.out.print(maskLetter(theString,keyCharacter));
      System.out.print(countKey(theString,keyCharacter));
      System.out.print(removeCharacter(theString,keyCharacter));
//      input.close();
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
      for ( count = 0 ; count < number ; count++ );
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

/*------------------------------ sample run ---------------------------------

Please enter a SINGLE letter to act as key: abc
Please enter a SINGLE letter to act as key: 
Please enter a SINGLE letter to act as key: a
Please enter a phrase or sentence >= 4 letters: cat
Please enter a phrase or sentence >= 4 letters: He who laughs last, laughs fast,
 faster, FASTEST.

String with key character, 'a' masked: 
He who l-ughs l-st, l-ughs f-st, f-ster, FASTEST.

# of occurence, 'a' masked: 5

String with key character, 'a' removed: 
He who lughs lst, lughs fst, fster, FASTEST.

---------------------------------------------------------------------------*/
/*------------------------------ sample run ---------------------------------

Please enter a SINGLE letter to act as key: F
Please enter a phrase or sentence >= 4 letters: He who laughs last, laughs fast,
 faster, FASTEST.

String with key character, 'F' masked: 
He who laughs last, laughs fast, faster, -ASTEST.

# of occurence, 'F' masked: 1

String with key character, 'F' removed: 
He who laughs last, laughs fast, faster, ASTEST.

---------------------------------------------------------------------------*/
/*------------------------------ sample run ---------------------------------

Please enter a SINGLE letter to act as key: Gap
Please enter a SINGLE letter to act as key: 
Please enter a SINGLE letter to act as key: cap
Please enter a SINGLE letter to act as key: g
Please enter a phrase or sentence >= 4 letters: He who laughs last, laughs fast,
 faster, FASTEST.

String with key character, 'g' masked: 
He who lau-hs last, lau-hs fast, faster, FASTEST.

# of occurence, 'g' masked: 2

String with key character, 'g' removed: 
He who lauhs last, lauhs fast, faster, FASTEST.

---------------------------------------------------------------------------*/
/*------------------------------ sample run ---------------------------------

Please enter a SINGLE letter to act as key: 
Please enter a SINGLE letter to act as key: acadas
Please enter a SINGLE letter to act as key: ASCACC
Please enter a SINGLE letter to act as key: T
Please enter a phrase or sentence >= 4 letters: asd
Please enter a phrase or sentence >= 4 letters: ead
Please enter a phrase or sentence >= 4 letters: He who laughs last, laughs fast,
 faster, FASTEST.

String with key character, 'T' masked: 
He who laughs last, laughs fast, faster, FAS-ES-.

# of occurence, 'T' masked: 2

String with key character, 'T' removed: 
He who laughs last, laughs fast, faster, FASES.

---------------------------------------------------------------------------*/