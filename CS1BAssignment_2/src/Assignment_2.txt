import java.util.Random;
import java.util.Scanner;

public class Foothill
{
   public static void main(String[] args)
   {
      int numHands;
      Scanner inputStream = new Scanner(System.in);
      Deck deck;
      
      System.out.println("------------------------------ 1st Test ---------"
            + "---------------------\n");
      
      deck = new Deck(2); // assign number of decks
      
      while(deck.getTopCard() != 0) // keep printing cards until it reaches 0
      {
         System.out.print(deck.dealCard().toString() + " / ");
      }
      
      deck.init(2); // repopulate deck
      deck.shuffle(); // shuffles deck
      
      System.out.println("\n\n");
      for(int i = 0; i < 104; i++) // keep printing cards until it reaches 0
      {
         System.out.print(deck.dealCard().toString() + " / ");
      }
      
      deck.init(1); // repopulate with 1 deck
      
      System.out.println("\n\n");
      for(int i = 0; i < 52; i++) // keep printing cards until it reaches 0
      {
         System.out.print(deck.dealCard().toString() + " / ");
      }
      
      deck.init(1); // repopulate with 1 deck
      deck.shuffle(); // shuffles deck
      
      System.out.println("\n\n");
      for(int i = 0; i < 52; i++) // prints out cards until it reaches 0
      {
         System.out.print(deck.dealCard().toString() + " / ");
      }
      
      System.out.print("\n\n------------------------------ 2nd Test ---------"
            + "---------------------");
      
      System.out.print("\n\nHow many hands? (1-10 please): ");
      numHands = inputStream.nextInt(); // input from user
      
      deck.init(1); // repopulate 1 deck
      
      Hand myHands[] = new Hand [10];
      
      for (int k = 0; k < numHands; k++)
      {
         myHands[k] = new Hand();
      }
      
      for (int k = 0; k < 52; k++) // distribute cards to hands
      {
         myHands[k % numHands].takeCard(deck.dealCard());
      }
      
      System.out.println("\nHere are our hands, from unshuffled deck: ");
      for (int k = 0; k < numHands; k++) // prints cards in each hand
      {
         System.out.println("Hand = ( " + myHands[k].toString() + " )\n");
      }
      
      deck.init(1); // repopulate with 1 deck
      deck.shuffle(); // shuffle cards in deck
      
      for (int k = 0; k < numHands; k++) // reset hands 
      {
         myHands[k].resetHand();
      }
      
      System.out.println("\n\nHere are our hands, from SHUFFLED deck: ");
      for (int k = 0; k < 52; k++) // distribute shuffled cards to hands
      {
         myHands[k % numHands].takeCard(deck.dealCard());
      }
      
      for (int k = 0; k < numHands; k++) // prints shuffled cards in each hand
      {
         System.out.println("Hand = ( " + myHands[k].toString() + " )\n");
      }
   }
}

class Deck
{
   public final int MAX_DECKCARDS = 6*52;
   
   private static Card[] masterPack;
   
   private Card[] cards = new Card[MAX_DECKCARDS];
   private int topCard;
   private int numPacks;
   
   public Deck(int numPacks)
   {
      allocateMasterPack();
      init(numPacks);
   }
   
   public void init(int numPacks)
   {
      this.topCard = 0;
      this.numPacks = numPacks;
      
      for (int i = 0; i < numPacks*52; i++)
      {
         cards[i] = masterPack[i % 52];
      }
      
      topCard = numPacks*52;
      
   }
   
   public void shuffle()
   {
      Random randIndex = new Random();
      
      for( int first = 0; first < topCard; first++) 
      {
         int second = randIndex.nextInt(topCard);

         Card temp = cards[first];
         cards[first] = cards[second];
         cards[second] = temp;
      }
   }
   
   public Card dealCard()
   {
      Card cardOpened;
     
      if(topCard == 0)
      {
         Card errorCard = new Card('Z', Card.Suit.Clubs);
         return errorCard;
      }
      cardOpened = cards[topCard - 1];
      topCard--;

      return cardOpened;
   }
   
   public int getTopCard()
   {
      return topCard;
   }
   
   public Card inspectCard(int k)
   {
      if ( k < 0 || k >= MAX_DECKCARDS)
      {
         Card errorCard = new Card('Z', Card.Suit.Clubs);
         return errorCard;
      }
      else
         return cards[k];
   }
   
   private static void allocateMasterPack()
   {
      if(masterPack != null)
      {
         return;
      }
      else
      {
         masterPack = new Card[52];
         int k, j;
         Card.Suit st;
         char val;
      
         // instantiate the array elements
         for (k = 0; k < masterPack.length; k++)
         masterPack[k] = new Card();
    
         for (k = 0; k<4; k++)
         {
            switch(k)
            {
            case 0:
               st = Card.Suit.Clubs; break;
            case 1:
               st = Card.Suit.Diamonds; break;
            case 2:
               st = Card.Suit.Hearts; break;
            case 3:
               st = Card.Suit.Spades; break;
            default:
               // should not happen but ...
               st = Card.Suit.Spades; break;
            }
            // set the suit for this loop pass
            masterPack[13*k].set('A', st);
            for (val='2', j = 1; val<='9'; val++, j++)
               masterPack[13*k + j].set(val, st);
            masterPack[13*k+9].set('T', st);
            masterPack[13*k+10].set('J', st);
            masterPack[13*k+11].set('Q', st);
            masterPack[13*k+12].set('K', st);
         }
      }
   }
}

class Card
{   
   enum Suit { Clubs, Diamonds, Hearts, Spades }
   
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   public Card(char value, Suit suit)
   {
      if(!set(value, suit))
         errorFlag = true;
   }
   
   public Card(char value)
   {
      this(value, Suit.Spades);
   }
   
   public Card()
   {
      this('A', Suit.Spades);
   }
   
   public Card(Card card)
   {
      this.suit = card.suit;
      this.value = card.value;
   }
   
   public String toString()
   {
      String retVal, error;
      
      if( errorFlag == true )
      {
         error = "[ invalid ]";
         return error;
      }
      else
      {
         retVal = String.valueOf(value) + " of " + suit;
         return retVal;
      }
   }
   
   public boolean set(char value, Suit suit)
   {
      if(isValid(value, suit))
      {
         this.suit = suit;
         this.value = value;
         errorFlag = false;
         return true;
      }
      else
      {
         errorFlag = true;
         return false;
      }
   }
   public Suit getSuit()
   {
      return suit;
   }
   
   public char getValue()
   {
      return value;
   }
   
   boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   private boolean isValid(char value, Suit suit)
   {
      char upVal;

      upVal = Character.toUpperCase(value);

      if (upVal == 'A' || upVal == 'K' || upVal == 'Q' || upVal == 'J'
            || upVal == 'T' || (upVal >= '2' && upVal <= '9'))
         return true;
      else
         return false;
   }
   
   public boolean equals(Card card)
   {
      if( this.value == card.value && this.suit == card.suit
            && this.errorFlag == card.errorFlag)
            return true;
         else
            return false;
   }
}  

class Hand
{
   public int MAX_CARDS = 52;
   
   private Card[] myCards = new Card[MAX_CARDS];
   private int numCards;
   
   public Hand()
   {
      resetHand();
   }
   
   public void resetHand()
   {
      numCards = 0;
   }
   
   public boolean takeCard(Card card)
   {
      if (numCards >= MAX_CARDS)
         return false;
      else
      {  
         myCards[numCards] = card;
         numCards++;
         return true;
      }     
   }
   
   public Card playCard()
   {
      Card cardOpened;

      cardOpened = myCards[numCards - 1];
      numCards--;

      return cardOpened;
   }
   
   public String toString()
   {
      String allCards = "";

      for(int i = 0; i < numCards; i++)
      {
         allCards = myCards[i].toString() + ", " + allCards;
      }

      return allCards;
   }
   
   public int getNumCards()
   {
      return numCards;
   }
   
   public Card inspectCard(int k)
   {
      if ( k < 0 || k >= MAX_CARDS)
      {
         Card errorCard = new Card('Z', Card.Suit.Clubs);
         return errorCard;
      }
      else
         return myCards[k];       
   }
}

/*-------------------------------- Sample Run --------------------------------
 * ------------------------------ 1st Test ------------------------------

K of Spades / Q of Spades / J of Spades / T of Spades / 9 of Spades / 8 of Spade
s / 7 of Spades / 6 of Spades / 5 of Spades / 4 of Spades / 3 of Spades / 2 of S
pades / A of Spades / K of Hearts / Q of Hearts / J of Hearts / T of Hearts / 9 
of Hearts / 8 of Hearts / 7 of Hearts / 6 of Hearts / 5 of Hearts / 4 of Hearts 
/ 3 of Hearts / 2 of Hearts / A of Hearts / K of Diamonds / Q of Diamonds / J of
 Diamonds / T of Diamonds / 9 of Diamonds / 8 of Diamonds / 7 of Diamonds / 6 of
 Diamonds / 5 of Diamonds / 4 of Diamonds / 3 of Diamonds / 2 of Diamonds / A of
 Diamonds / K of Clubs / Q of Clubs / J of Clubs / T of Clubs / 9 of Clubs / 8 o
f Clubs / 7 of Clubs / 6 of Clubs / 5 of Clubs / 4 of Clubs / 3 of Clubs / 2 of 
Clubs / A of Clubs / K of Spades / Q of Spades / J of Spades / T of Spades / 9 o
f Spades / 8 of Spades / 7 of Spades / 6 of Spades / 5 of Spades / 4 of Spades /
 3 of Spades / 2 of Spades / A of Spades / K of Hearts / Q of Hearts / J of Hear
ts / T of Hearts / 9 of Hearts / 8 of Hearts / 7 of Hearts / 6 of Hearts / 5 of 
Hearts / 4 of Hearts / 3 of Hearts / 2 of Hearts / A of Hearts / K of Diamonds /
 Q of Diamonds / J of Diamonds / T of Diamonds / 9 of Diamonds / 8 of Diamonds /
 7 of Diamonds / 6 of Diamonds / 5 of Diamonds / 4 of Diamonds / 3 of Diamonds /
 2 of Diamonds / A of Diamonds / K of Clubs / Q of Clubs / J of Clubs / T of Clu
bs / 9 of Clubs / 8 of Clubs / 7 of Clubs / 6 of Clubs / 5 of Clubs / 4 of Clubs
 / 3 of Clubs / 2 of Clubs / A of Clubs / 


2 of Clubs / 6 of Diamonds / 9 of Diamonds / 5 of Hearts / 9 of Spades / 6 of Cl
ubs / 4 of Diamonds / Q of Diamonds / 3 of Clubs / A of Hearts / K of Spades / Q
 of Hearts / Q of Diamonds / T of Hearts / T of Hearts / 4 of Spades / K of Club
s / 7 of Hearts / 4 of Clubs / 7 of Diamonds / 5 of Clubs / Q of Hearts / 6 of D
iamonds / 9 of Hearts / 7 of Hearts / 3 of Diamonds / 6 of Spades / 7 of Clubs /
 7 of Spades / A of Spades / Q of Clubs / 2 of Diamonds / 8 of Hearts / 2 of Spa
des / J of Diamonds / 8 of Diamonds / 5 of Spades / 5 of Clubs / 8 of Spades / 9
 of Spades / K of Clubs / J of Clubs / K of Diamonds / 9 of Clubs / 4 of Hearts 
/ T of Diamonds / 5 of Diamonds / 2 of Hearts / J of Hearts / 8 of Clubs / J of 
Diamonds / 9 of Hearts / 7 of Clubs / T of Diamonds / K of Hearts / 2 of Spades 
/ 6 of Hearts / A of Hearts / T of Clubs / 4 of Clubs / J of Spades / 8 of Clubs
 / J of Spades / A of Clubs / 2 of Diamonds / 3 of Hearts / Q of Clubs / T of Cl
ubs / K of Hearts / 7 of Diamonds / 3 of Spades / A of Diamonds / J of Hearts / 
8 of Spades / 2 of Hearts / T of Spades / 5 of Hearts / 6 of Clubs / 2 of Clubs 
/ 7 of Spades / 6 of Spades / 9 of Diamonds / 4 of Spades / A of Clubs / 8 of He
arts / 8 of Diamonds / T of Spades / 4 of Hearts / 6 of Hearts / 5 of Diamonds /
 3 of Spades / 4 of Diamonds / K of Diamonds / K of Spades / A of Diamonds / 9 o
f Clubs / Q of Spades / 3 of Diamonds / Q of Spades / A of Spades / J of Clubs /
 3 of Hearts / 3 of Clubs / 5 of Spades / 


K of Spades / Q of Spades / J of Spades / T of Spades / 9 of Spades / 8 of Spade
s / 7 of Spades / 6 of Spades / 5 of Spades / 4 of Spades / 3 of Spades / 2 of S
pades / A of Spades / K of Hearts / Q of Hearts / J of Hearts / T of Hearts / 9 
of Hearts / 8 of Hearts / 7 of Hearts / 6 of Hearts / 5 of Hearts / 4 of Hearts 
/ 3 of Hearts / 2 of Hearts / A of Hearts / K of Diamonds / Q of Diamonds / J of
 Diamonds / T of Diamonds / 9 of Diamonds / 8 of Diamonds / 7 of Diamonds / 6 of
 Diamonds / 5 of Diamonds / 4 of Diamonds / 3 of Diamonds / 2 of Diamonds / A of
 Diamonds / K of Clubs / Q of Clubs / J of Clubs / T of Clubs / 9 of Clubs / 8 o
f Clubs / 7 of Clubs / 6 of Clubs / 5 of Clubs / 4 of Clubs / 3 of Clubs / 2 of 
Clubs / A of Clubs / 


5 of Diamonds / 8 of Clubs / 9 of Spades / 2 of Diamonds / J of Spades / A of He
arts / Q of Hearts / 6 of Spades / A of Diamonds / 5 of Clubs / K of Clubs / 5 o
f Hearts / 8 of Hearts / T of Spades / T of Hearts / A of Spades / J of Clubs / 
Q of Diamonds / 9 of Hearts / 3 of Spades / 6 of Hearts / A of Clubs / Q of Spad
es / 4 of Spades / 3 of Clubs / T of Diamonds / J of Hearts / 8 of Spades / 3 of
 Diamonds / 2 of Hearts / 3 of Hearts / K of Hearts / 9 of Clubs / 4 of Clubs / 
Q of Clubs / 8 of Diamonds / 6 of Diamonds / K of Diamonds / 9 of Diamonds / K o
f Spades / J of Diamonds / 4 of Hearts / 2 of Clubs / 2 of Spades / 7 of Spades 
/ 5 of Spades / 7 of Clubs / 7 of Hearts / 6 of Clubs / 4 of Diamonds / T of Clu
bs / 7 of Diamonds / 

------------------------------ 2nd Test ------------------------------

How many hands? (1-10 please): 6

Here are our hands, from unshuffled deck: 
Hand = ( 4 of Clubs, T of Clubs, 3 of Diamonds, 9 of Diamonds, 2 of Hearts, 8 of
 Hearts, A of Spades, 7 of Spades, K of Spades,  )

Hand = ( 3 of Clubs, 9 of Clubs, 2 of Diamonds, 8 of Diamonds, A of Hearts, 7 of
 Hearts, K of Hearts, 6 of Spades, Q of Spades,  )

Hand = ( 2 of Clubs, 8 of Clubs, A of Diamonds, 7 of Diamonds, K of Diamonds, 6 
of Hearts, Q of Hearts, 5 of Spades, J of Spades,  )

Hand = ( A of Clubs, 7 of Clubs, K of Clubs, 6 of Diamonds, Q of Diamonds, 5 of 
Hearts, J of Hearts, 4 of Spades, T of Spades,  )

Hand = ( 6 of Clubs, Q of Clubs, 5 of Diamonds, J of Diamonds, 4 of Hearts, T of
 Hearts, 3 of Spades, 9 of Spades,  )

Hand = ( 5 of Clubs, J of Clubs, 4 of Diamonds, T of Diamonds, 3 of Hearts, 9 of
 Hearts, 2 of Spades, 8 of Spades,  )



Here are our hands, from SHUFFLED deck: 
Hand = ( A of Hearts, K of Hearts, T of Clubs, 4 of Spades, J of Clubs, 4 of Clu
bs, 5 of Clubs, A of Diamonds, 7 of Hearts,  )

Hand = ( 8 of Clubs, K of Diamonds, 7 of Diamonds, Q of Clubs, 9 of Spades, 9 of
 Diamonds, 5 of Diamonds, 2 of Hearts, 5 of Hearts,  )

Hand = ( 8 of Hearts, 6 of Clubs, A of Spades, 6 of Diamonds, 6 of Spades, 3 of 
Spades, T of Hearts, K of Clubs, 3 of Clubs,  )

Hand = ( Q of Hearts, 9 of Clubs, J of Hearts, 3 of Diamonds, Q of Spades, J of 
Diamonds, 9 of Hearts, 5 of Spades, 2 of Diamonds,  )

Hand = ( 3 of Hearts, 4 of Diamonds, 2 of Spades, Q of Diamonds, T of Diamonds, 
7 of Clubs, A of Clubs, 6 of Hearts,  )

Hand = ( 8 of Diamonds, K of Spades, 4 of Hearts, 2 of Clubs, 7 of Spades, J of 
Spades, T of Spades, 8 of Spades,  )

----------------------------------------------------------------------------*/