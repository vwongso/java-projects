import javax.swing.*;
import java.awt.*;
   
public class Foothill 
{
   // static for the 57 icons and their corresponding labels
   // normally we would not have a separate label for each card, but
   // if we want to display all at once using labels, we need to.
   
   static int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];
   static JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
   
   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop.  For each file name, read it in and use it to
      // instantiate each of the 57 Icons in the icon[] array.
      icon[56] = new ImageIcon("images/BK.gif");
      
      for (int k = 0; k < 14; k++)
      {
         for (int j = 0; j < 4; j ++)
         {
            icon[(14*j) + k] = new ImageIcon("images/" + turnIntIntoCardValue(k)
               + turnIntIntoCardSuit(j) + ".gif");
         }
      }
   }
   
   // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
   static String turnIntIntoCardValue(int k)
   {
      // an idea for a helper method (do it differently if you wish)
      if ( k <= 0 || k >= 9 )
      {
         switch (k)
         {
            case 0:
               return "A";
            case 9:
               return "T";
            case 10:
               return "J";
            case 11:
               return "Q";
            case 12:
               return "K";
            case 13: 
               return "X";
            default: 
               return "A";
         }
      }
      else
         return Integer.toString(k + 1);
   }
   
   // turns 0 - 3 into "C", "D", "H", "S"
   static String turnIntIntoCardSuit(int j)
   {
      switch (j)
      {
         case 0: 
            return "C";
         case 1:
            return "D";
         case 2: 
            return "H";
         case 3: 
            return "S";
         default:
            return "S";
      }
      // an idea for another helper method (do it differently if you wish)
   }
   
   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
      int k;
      
      // prepare the image icon array
      loadCardIcons();
      
      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Transporter Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);   
      frmMyWindow.setLayout(layout);
      
      // prepare the image label array
      JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         labels[k] = new JLabel(icon[k]);
      
      // place your 3 controls into frame
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         frmMyWindow.add(labels[k]);

      // show everything to the user
      frmMyWindow.setVisible(true);
   }
}

