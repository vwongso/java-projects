
public class Foothill
{
   public static void main(String[] args)
   {
      int std_id, num_letters, answer;
      double result;
      String lastname;
      lastname = new String ("Wongso");
      
      std_id = 20119854;
      num_letters = 6;
      
      System.out.println("My last (family) name is " + lastname);
      System.out.println("My student ID is " + std_id);
      System.out.println("The number of characters in my last name is " + 
            num_letters);
      
      answer = std_id % 2;
      System.out.println("\nExpression #1 " + std_id + " % 2: " + answer);
      
      answer = std_id % num_letters;
      System.out.println("Expression #2 " + std_id + " % " + num_letters + ": "
            + answer);
      
      answer = std_id / num_letters;
      System.out.println("Expression #3 " + std_id + " / " + num_letters + ": "
            + answer);

      answer = 1 + 2 + 3 + 4 + 5 + num_letters;
      System.out.println("Expression #4 " + "1 + 2 + ... + " + num_letters +
             ": " + answer);
      
      
      result = 1 / ((std_id / num_letters) - (10000000.0 * num_letters * 
            num_letters * num_letters));
      System.out.println("Expression #5 " + "1 / ( ( " + std_id + " / " +
            num_letters + " ) - ( 10,000,000 * " + num_letters + "^3 ) ): "
            + result);
            
   }
}

/* -------------------------- Run ----------------------

Expression #1 20119854 % 2: 0
Expression #2 20119854 % 6: 0
Expression #3 20119854 / 6: 3353309
Expression #4 1 + 2 + ... + 6: 21
Expression #5 1 / ( ( 20119854 / 6 ) - ( 10,000,000 * 6^3 ) ): -4.63682810992243
3E-10

------------------------------------------------------- */