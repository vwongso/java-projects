import java.awt.*;

import javax.swing.*;

public class Foothill 
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   
   public static void main(String[] args)
   {
      int k;
      
      Icon tempIcon;
      Card playerCard;
      
      // establish main frame in which program will run
      CardTable myCardTable 
         = new CardTable("CS 1B CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      myCardTable.setVisible(true);

      // CREATE LABELS ----------------------------------------------------
      for(k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
      }
      
      for(k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         playerCard = new Card(Card.generateRandomCard());
         tempIcon = GUICard.getIcon(playerCard);
         humanLabels[k] = new JLabel(tempIcon);
      }
      
      Card middleCard = new Card(Card.generateRandomCard());
      tempIcon = GUICard.getIcon(middleCard);
      
      playedCardLabels[0] = new JLabel(tempIcon);
      playedCardLabels[1] = new JLabel(tempIcon);
      
      playLabelText[0] = new JLabel( "Computer", JLabel.CENTER );
      playLabelText[1] = new JLabel( "You", JLabel.CENTER );
  
      // ADD LABELS TO PANELS -----------------------------------------
      for (k = 0; k < NUM_PLAYERS; k++)
      {
         myCardTable.midPanel.add(playedCardLabels[k]);
      }
      for (k = 0; k < NUM_PLAYERS; k++)
      {
         myCardTable.midPanel.add(playLabelText[k]);
      }
      for (k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         myCardTable.compPanel.add(computerLabels[k]);
      }
      for (k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         myCardTable.playPanel.add(humanLabels[k]);
      }

      // show everything to the user
      myCardTable.setVisible(true);
   }
}

@SuppressWarnings("serial")
class CardTable extends JFrame
{
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;
   
   private int numCardsPerHand;
   private int numPlayers;
   
   public JPanel compPanel, midPanel, playPanel;
   
   CardTable(String title, int numCardsPerHand, int numPlayers)
   {      
      if ((numPlayers >= 1) && (numPlayers <= MAX_PLAYERS))
      {
         this.numPlayers = numPlayers;
      }
      else
         numPlayers = 2;
      
      if ((numCardsPerHand >= 1) && (numCardsPerHand <= MAX_PLAYERS))
      {
         this.numCardsPerHand = numCardsPerHand;
      }
      else
         numCardsPerHand = 5;
      
      compPanel = new JPanel(new GridLayout(1, MAX_CARDS_PER_HAND));
      midPanel = new JPanel(new GridLayout(2, 2));
      playPanel = new JPanel(new GridLayout(1, MAX_CARDS_PER_HAND));
      
      setLayout(new GridLayout(MAX_PLAYERS + 1, MAX_CARDS_PER_HAND));
      
      add(compPanel);
      add(midPanel);
      add(playPanel);
   }
   
   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }
   
   public int getNumPlayers()
   {
      return numPlayers;
   }
}

class GUICard
{
   private static Icon[][] iconCards = new ImageIcon[14][4];
   // 14 = A thru K + joker
   
   private static Icon iconBack;
   static boolean iconsLoaded = false;
   
   static void loadCardIcons()
   {
      if(iconsLoaded)
         return;
      
      iconBack = new ImageIcon("images/BK.gif");
      
      for (int k = 0; k < 14; k++)
         for (int j = 0; j < 4; j ++)
            iconCards[k][j] = new ImageIcon("images/" + turnIntIntoCardValue(k)
               + turnIntIntoCardSuit(j) + ".gif");
      
      iconsLoaded = true;
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
   }
   
   static int turnCardValueIntoInt(int i)
   {
      if (i <= '0' || i >= '9')
      {           
         switch (i)
         {
            case 'A':
               return 0;
            case 'T':
               return 9;
            case 'J':
               return 10;
            case 'Q':
               return 11;
            case 'K':
               return 12;
            case 'X': 
               return 13;
            default:
               return 0;
         }
      }
      return Character.getNumericValue(i+1);
   }
   
   static int turnCardSuitIntoInt(Card.Suit suit)
   {
      switch (suit)
      {
         case clubs:
            return 0;
         case diamonds:
            return 1;
         case hearts:
            return 2;
         case spades: 
            return 3;
         default:
            return 3;
      }
   }
   
   static public Icon getIcon(Card card)
   {
      int cardValueAsInt = turnCardValueIntoInt(card.getVal());
      int cardSuitAsInt = turnCardSuitIntoInt(card.getSuit());
     
      loadCardIcons(); // will not load twice, so no worries.
      return iconCards[cardValueAsInt][cardSuitAsInt];
   }
   
   static public Icon getBackCardIcon()
   {
      loadCardIcons();
      
      return iconBack;
   }
}

class Card
{   
   // type and constants
   public enum Suit { clubs, diamonds, hearts, spades }

   // private data
   private char value;
   private Suit suit;
   boolean errorFlag;

   // 4 overloaded constructors
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   public Card(char value)
   {
      this(value, Suit.spades);
   }
   public Card()
   {
      this('A', Suit.spades);
   }
   // copy constructor
   public Card(Card card)
   {
      this(card.value, card.suit);
   }

   // mutators
   public boolean set(char value, Suit suit)
   {
      char upVal;            // for upcasing char

      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);

      if ( !isValid(upVal, suit))
      {
         errorFlag = true;
         return false;
      }

      // else implied
      errorFlag = false;
      this.value = upVal;
      this.suit = suit;
      return true;
   }

   // accessors
   public char getVal()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getErrorFlag()
   {
      return errorFlag;
   }

   public boolean equals(Card card)
   {
      if (this.value != card.value)
         return false;
      if (this.suit != card.suit)
         return false;
      if (this.errorFlag != card.errorFlag)
         return false;
      return true;
   }

   // stringizer
   public String toString()
   {
      String retVal;

      if (errorFlag)
         return "** illegal **";

      // else implied

      retVal =  String.valueOf(value);
      retVal += " of ";
      retVal += String.valueOf(suit);

      return retVal;
   }

   // helper
   private boolean isValid(char value, Suit suit)
   {
      char upVal;

      // convert to uppercase to simplify (need #include <cctype>)
      upVal = Character.toUpperCase(value);

      // check for validity
      if (
            upVal == 'A' || upVal == 'K'
            || upVal == 'Q' || upVal == 'J'
            || upVal == 'T'
            || (upVal >= '2' && upVal <= '9')
            )
         return true;
      else
         return false;
   }
   
   static Card generateRandomCard()
   {
      Card.Suit suit;
      char val;

      int suitSelector, valSelector;

      // get random suit and value
      suitSelector = (int) (Math.random() * 4);
      valSelector = (int) (Math.random() * 14);

      suit = Card.Suit.values()[suitSelector];
      val = GUICard.turnIntIntoCardValue(valSelector).charAt(0);

      return new Card(val, suit);
   }
}