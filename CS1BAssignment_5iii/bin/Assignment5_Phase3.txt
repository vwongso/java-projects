import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Foothill 
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS];
   static int numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack;
   static Card[] unusedCardsPerPack;
   
   public static void main(String[] args)
   {
      int k;
      
      numPacksPerDeck = 1;
      numJokersPerPack = 0;
      numUnusedCardsPerPack = 0;
      unusedCardsPerPack = null;

      CardGameFramework toyGame = new CardGameFramework( 
            numPacksPerDeck, numJokersPerPack,  
            numUnusedCardsPerPack, unusedCardsPerPack, 
            NUM_PLAYERS, NUM_CARDS_PER_HAND);
      
      toyGame.deal();
      
      // establish main frame in which program will run
      CardTable myCardTable 
         = new CardTable("CS 1B CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      myCardTable.setVisible(true);

      // CREATE LABELS ----------------------------------------------------
      playLabelText[0] = new JLabel( "Computer", JLabel.CENTER );
      playLabelText[1] = new JLabel( "You", JLabel.CENTER );
      
      for ( k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
      }
      
      for ( k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         humanLabels[k] =
            new JLabel(GUICard.getIcon(toyGame.getHand(1).inspectCard(k)));
      }
      
      for ( k = 0; k < 2; k++)
      {
         playedCardLabels[k] =
            new JLabel(GUICard.getIcon(toyGame.getHand(k).playCard()));
      }
 
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
   
   public  static int getValueRank(char val)
   {
      String values = "A23456789TJQKX";
      return values.indexOf(val);
   }
   
   public static int getSuitRank(Suit st)
   {
      if (st == Suit.clubs)
         return 0;

      if (st == Suit.diamonds)
         return 1;

      if (st == Suit.hearts)
         return 2;

      if (st == Suit.clubs)
         return 3;

      // should not happen
      return 0;
   }
   
   public int compareTo(Card other)
   {
      if (this.value != other.value)
      {
         return (getValueRank(this.value) - getValueRank(other.value));
      }
      else
         return (getSuitRank(this.suit) - getSuitRank(other.suit));


   }
   
   public static void arraySort(Card[] array, int arraySize)
   {
      for (int k = 0; k < arraySize; k++)
         if (!floatLargestToTop(array, arraySize - 1 - k))
            return;
   }
   
   private static boolean floatLargestToTop(Card[] array, int top)
   {
      boolean changed = false;
      Card temp;

      for (int k = 0; k < top; k++)
         if (array[k].compareTo(array[k+1]) > 0)
         {
            temp = array[k];
            array[k] = array[k+1];
            array[k+1] = temp;
            changed = true;
         };
         return changed;
   }
}

//class Hand  ----------------------------------------------------------------
class Hand
{
   public static final int MAX_CARDS_PER_HAND = 100;  // should cover any game

   private Card[] myCards;
   private int numCards;

 //constructor
   public Hand()
   {
      // careful - we are only allocating the references
      myCards = new Card[MAX_CARDS_PER_HAND];
      resetHand();
   }

 // mutators
   public void resetHand() { numCards = 0; }

   public boolean takeCard(Card card)
   {
      if (numCards >= MAX_CARDS_PER_HAND)
         return false;

      // be frugal - only allocate when needed
      if (myCards[numCards] == null)
         myCards[numCards] = new Card();

      // don't just assign:  mutator assures active/undeleted      
      myCards[numCards++].set( card.getVal(), card.getSuit() );
      return true;
   }

   public Card playCard()
   {
      // always play  highest card in array.  client will prepare this position.
      // in rare case that client tries to play from a spent hand, return error

      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases

      if (numCards == 0)
         return errorReturn;
      else
         return myCards[--numCards];
   }

 // accessors
   public String toString()
   {
       int k;
       String retVal = "Hand =  ( ";

       for (k = 0; k < numCards; k++)
       {
          retVal += myCards[k].toString();
          if (k < numCards - 1)
             retVal += ", ";
        }
       retVal += " )";
       return retVal;
    }

    int getNumCards()
    {
       return numCards;
    }

    Card inspectCard(int k)
    {
    // return copy of card at position k.
    // if client tries to access out-of-bounds card, return error

       Card errorReturn = new Card('9', Card.Suit.spades); // in rare cases

       if (k < 0 || k >= numCards)
          return errorReturn;
       else
          return myCards[k];
    }
    
    void sort()
    {
    // assumes that Card class has been sent ordering (if default not correct)
       Card.arraySort(myCards, numCards);
    }
}

//class Deck  ----------------------------------------------------------------
class Deck
{
   // six full decks (with jokers) is enough for about any game
   private static final int MAX_CARDS_PER_DECK = 6 * 52;
   private static Card[] masterPack; //one 52-Card master for initializing decks

   private Card[] cards;
   private int topCard;
   private int numPacks;

   private static boolean firstTime = true;  // avoid calling allcMstrPck > once

   public Deck(int numPacks)
   {
      allocateMasterPack();  // do not call from init()
      cards = new Card[MAX_CARDS_PER_DECK];
      init(numPacks);
   }

   static private void allocateMasterPack()
   {
      int j, k;
      Card.Suit st;
      char val;

      // we're in static method; only needed once per program: good for whole class
      if ( !firstTime )
         return;
      firstTime = false;

      // allocate
      masterPack = new Card[52];
      for (k = 0; k < 52; k++)
         masterPack[k] = new Card();

      // next set data
      for (k = 0; k < 4; k++)
      {
         // set the suit for this loop pass
         st = Card.Suit.values()[k];

         // now set all the values for this suit
         masterPack[13*k].set('A', st);
         for (val='2', j = 1; val<='9'; val++, j++)
            masterPack[13*k + j].set(val, st);
         masterPack[13*k+9].set('T', st);
         masterPack[13*k+10].set('J', st);
         masterPack[13*k+11].set('Q', st);
         masterPack[13*k+12].set('K', st);
      }
   }

   public Deck()
   {
      this(1);
   }


   // set deck from 1 to 6 packs, perfecly ordered
   public void init(int numPacks)
   {
      int k, pack;

      if (numPacks < 1 || numPacks > 6)
         numPacks = 1;

      // hand over the masterPack cards to our deck
      for (pack = 0; pack < numPacks; pack++)
         for (k = 0; k < 52; k++)
            cards[pack*52 + k] = masterPack[k];

      // if something modified a card, we would be in trouble.  fortunately,
      // we don't expect a card to ever be modified after instantiated
      // in the context of a deck. e.g. state would be for deck set-up only

      this.numPacks = numPacks;
      topCard = numPacks * 52;
   }

   public void init()
   {
      init(1);
   }

   public int getNumCards()
   {
      return topCard;
   }

   public void shuffle()
   {
      Card tempCard;
      int k, randInt;

      // topCard is size of deck
      for (k = 0; k < topCard; k++)
      {
         randInt = (int)(Math.random() * topCard);

         // swap cards k and randInt (sometimes k == randInt:  okay)
         tempCard = cards[k];
         cards[k] = cards[randInt];
         cards[randInt] = tempCard;
      }
   }

   public Card takeACard()
   {
      return new Card();
   }

   public Card dealCard()
   {
      // always deal the topCard.  
      Card errorReturn = new Card('E', Card.Suit.spades); //  in rare cases

      if (topCard == 0)
         return errorReturn;
      else
         return cards[--topCard];
   }

   public Card inspectCard(int k)
   {
      // return copy of card at position k.
      // if client tries to access out-of-bounds card, return error

      Card errorReturn = new Card('E', Card.Suit.spades); //  in rare cases

      if (k < 0 || k >= topCard)
         return errorReturn;
      else
         return cards[k-1];
   }

   public boolean removeCard(Card card)
   {
      int k;
      boolean foundAtLeastOne;

      foundAtLeastOne = false;
      for (k = 0; k < topCard; k++)
      {
         // care: use while, not if, in case we copy to-be-removed from top to k
         while ( cards[k].equals(card) )
         {
            // overwrite card[k] with top of deck, then decrement topCard
            cards[k] = cards[topCard - 1];
            topCard--;
            foundAtLeastOne = true;
            // test because "while" causes topCard to decrease, possibly below k
            if ( k >= topCard )
               break;
         }
      }
      // did above work if k == topCard-1?  think about it
      return foundAtLeastOne;
   }
   
   public boolean addCard(Card card)
   {
      // don't allow too many copies of this card in the deck
      if (numOccurrences(card) >= numPacks)
         return false;

      cards[topCard++] = card;
      return true;
   }
   
   public int numOccurrences(Card card)
   {
      int retVal, k;

      retVal = 0;

      // assumption:  card is a default item:  not deleted and state=active)
      for (k = 0; k < topCard; k++)
      {
         if (inspectCard(k).equals(card))
            retVal++;
      }
      return retVal;
   }
}

//class CardGameFramework  ----------------------------------------------------
class CardGameFramework
{
   private static final int MAX_PLAYERS = 50;

   private int numPlayers;
   private int numPacks;            // # standard 52-card packs per deck
                                  // ignoring jokers or unused cards
   private int numJokersPerPack;    // if 2 per pack & 3 packs per deck, get 6
   private int numUnusedCardsPerPack;  // # cards removed from each pack
   private int numCardsPerHand;        // # cards to deal each player
   private Deck deck;               // holds the initial full deck and gets
                                  // smaller (usually) during play
   private Hand[] hand;             // one Hand for each player
   private Card[] unusedCardsPerPack;   // an array holding the cards not used
                                      // in the game.  e.g. pinochle does not
                                      // use cards 2-8 of any suit

   public CardGameFramework( int numPacks, int numJokersPerPack,
         int numUnusedCardsPerPack,  Card[] unusedCardsPerPack,
         int numPlayers, int numCardsPerHand)
   {
      int k;

      // filter bad values
      if (numPacks < 1 || numPacks > 6)
         numPacks = 1;
      if (numJokersPerPack < 0 || numJokersPerPack > 4)
         numJokersPerPack = 0;
      if (numUnusedCardsPerPack < 0 || numUnusedCardsPerPack > 50) //  > 1 card
         numUnusedCardsPerPack = 0;
      if (numPlayers < 1 || numPlayers > MAX_PLAYERS)
         numPlayers = 4;
      // one of many ways to assure at least one full deal to all players
      if  (numCardsPerHand < 1 ||
            numCardsPerHand >  numPacks * (52 - numUnusedCardsPerPack)
            / numPlayers )
         numCardsPerHand = numPacks * (52 - numUnusedCardsPerPack) / numPlayers;

      // allocate
      this.unusedCardsPerPack = new Card[numUnusedCardsPerPack];
      this.hand = new Hand[numPlayers];
      for (k = 0; k < numPlayers; k++)
         this.hand[k] = new Hand();
      deck = new Deck(numPacks);

      // assign to members
      this.numPacks = numPacks;
      this.numJokersPerPack = numJokersPerPack;
      this.numUnusedCardsPerPack = numUnusedCardsPerPack;
      this.numPlayers = numPlayers;
      this.numCardsPerHand = numCardsPerHand;
      for (k = 0; k < numUnusedCardsPerPack; k++)
         this.unusedCardsPerPack[k] = unusedCardsPerPack[k];

      // prepare deck and shuffle
      newGame();
   }

   // constructor overload/default for game like bridge
   public CardGameFramework()
   {
      this(1, 0, 0, null, 4, 13);
   }

   public Hand getHand(int k)
   {
      // hands start from 0 like arrays

      // on error return automatic empty hand
      if (k < 0 || k >= numPlayers)
         return new Hand();

      return hand[k];
   }

   public Card getCardFromDeck() { return deck.dealCard(); }

   public int getNumCardsRemainingInDeck() { return deck.getNumCards(); }

   public void newGame()
   {
      int k, j;

      // clear the hands
      for (k = 0; k < numPlayers; k++)
         hand[k].resetHand();

      // restock the deck
      deck.init(numPacks);

      // remove unused cards
      for (k = 0; k < numUnusedCardsPerPack; k++)
         deck.removeCard( unusedCardsPerPack[k] );

      // add jokers
      for (k = 0; k < numPacks; k++)
         for ( j = 0; j < numJokersPerPack; j++)
            deck.addCard( new Card('X', Card.Suit.values()[j]) );

      // shuffle the cards
      deck.shuffle();
   }

   public boolean deal()
   {
      // returns false if not enough cards, but deals what it can
      int k, j;
      boolean enoughCards;

      // clear all hands
      for (j = 0; j < numPlayers; j++)
         hand[j].resetHand();

      enoughCards = true;
      for (k = 0; k < numCardsPerHand && enoughCards ; k++)
      {
         for (j = 0; j < numPlayers; j++)
            if (deck.getNumCards() > 0)
               hand[j].takeCard( deck.dealCard() );
            else
            {
               enoughCards = false;
               break;
            }
      }

      return enoughCards;
   }

   void sortHands()
   {
      int k;
      
      for (k = 0; k < numPlayers; k++)
         hand[k].sort();
   }
}