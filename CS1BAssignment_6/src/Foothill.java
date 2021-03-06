import java.lang.Exception;

public class Foothill
{
   public static void main(String[] args)
   {
      Card card1, card2, card3, card4, card5;

      // initialize cards
      card1 = new Card('8', Card.Suit.Clubs);
      card2 = new Card('4', Card.Suit.Hearts);
      card3 = new Card('X');
      card4 = new Card();
      card5 = new Card('9', Card.Suit.Diamonds);

      // initialize queue
      CardQueue cardQueue = new CardQueue();
      // add cards to queue
      cardQueue.addCard(card1);
      cardQueue.addCard(card2);
      cardQueue.addCard(card3);
      cardQueue.addCard(card4);
      cardQueue.addCard(card5);

      // print queue tostring() test
      System.out.println("Queue's toString() Test: ");
      System.out.println(cardQueue.toString());

      // print QueueEmptyException test
      System.out.println("\nQueueEmptyException Test: ");
      for(int k = 0; k < 10; k++)
      {
         try
         {
            System.out.print(" " + cardQueue.removeCard().toString() + " ");
         }
         catch (QueueEmptyException e)
         {
            System.out.print(" Queue Empty ");
         }
      }
   }
}

class Node
{
   // protected member
   protected Node top;

   // constructor
   public Node()
   {
      top = null;
   }

   public String toString()
   {
      String node;

      node = " (generic node) ";

      return node;
   }
}

@SuppressWarnings("serial")
class QueueEmptyException extends Exception
{
}

class Queue
{
   // pointer
   private Node front, back;

   // constructor
   public Queue()
   {
      front = new Node();
      back = new Node();
   }

   // Add and Remove method
   public void add(Node newNode)
   {   
      if (newNode == null)
      {
         return; // Emergency return
      }

      if(front.top == null) // First Case
      {
         front.top = newNode;
         back.top = newNode;
      }
      else // Second Case
      {
         back.top.top = newNode;
         back.top = newNode;
      }
   }

   public Node remove()
      throws QueueEmptyException // Throws Exception
   {
      Node temp;

      if(front.top == null)
         throw new QueueEmptyException();

      temp = front.top;
      if(temp.top != null)
      {
         front.top = temp.top;
         temp.top = null;     // don't give client access to queue
      }
      else
      {
         front.top = null;
         back.top = null;
      }

      return temp;
   }

   public String toString() 
   {
      String queue = new String();
      Node p;

      for( p = front.top; p != null; p = p.top )
         queue += p.toString();

      return queue;
   } 
}

class CardNode extends Node
{
   // additional data for subclass
   private Card card;

   // constructor
   public CardNode(Card card)
   {
      super();  // constructor call to base class
      this.card = card;
   }

   // accessor
   public Card getCard()
   {
      return card;
   }

   // overriding toString()
   public String toString()
   {
      return " " + card.toString() + " ";
   }
}

class CardQueue extends Queue
{
   public void addCard(Card card)
   {
      // adds node
      CardNode cardNode = new CardNode(card);
      super.add(cardNode);
   }

   public Card removeCard()
      throws QueueEmptyException
   {
      // removes node
      CardNode cardNode = (CardNode) remove();
      return cardNode.getCard();
   }
}

class Card
{   
   public enum Status { off, on }
   public enum Suit { Clubs, Diamonds, Hearts, Spades }
   public static int cardArrayVal = 13; 
   public static Suit[] suitRank = {Suit.Clubs, Suit.Diamonds, Suit.Hearts,
      Suit.Spades};
   public static char[] valueRank = { '2', '3', '4', '5', '6', '7', '8', '9',
      'T', 'J', 'Q', 'K', 'A', 'X'};

   // private data
   private char value;
   private Suit suit;
   Status status;
   boolean errorFlag;

   // 4 overloaded constructors
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   public Card(char value)
   {
      this(value, Suit.Spades);
   }
   public Card()
   {
      this('A', Suit.Spades);
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

      // can't really have an error here
      this.suit = suit;  

      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);

      // check for validity
      if (
         upVal == 'A' || upVal == 'K'
         || upVal == 'Q' || upVal == 'J'
         || upVal == 'T' || upVal == 'X'
         || (upVal >= '2' && upVal <= '9')
         )
      {
         errorFlag = false;
         status = Status.on;
         this.value = upVal;
      }
      else
      {
         errorFlag = true;
         return false;
      }

      return !errorFlag;
   }

   public void setState(Status status)
   {
      this.status = status;
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

   public Status getState()
   {
      return status;
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
      if (this.status != card.status)
         return false;
      return true;
   }
   
   //stringizer
   public String toString()
   {
      String retVal;

      if (errorFlag)
         return "** illegal **";
      if (status == Status.off)
         return "(deleted)";

      // else implied

      if (value != 'X')
      {
         retVal =  String.valueOf(value);
         retVal += " of ";
         retVal += String.valueOf(suit);
      }
      else
      {
         retVal = "JOKER";
      }
      return retVal;
   }

   // sort member methods
   public int compareTo(Card other)
   {
      if (this.value != other.value)
      {
         return (getValueRank(this.value) - getValueRank(other.value));
      }
      else
         return (getSuitRank(this.suit) - getSuitRank(other.suit));
   }

   public static void setRankingOrder(
      char[] valueArr, Suit[] suitArr,
      int cardArrayVal )
   {
      int k;

      if (cardArrayVal < 0 || cardArrayVal > 13)
      {
         return;
      }

      Card.cardArrayVal = cardArrayVal;
      for (k = 0; k < cardArrayVal; k++)
         Card.valueRank[k] = valueArr[k];

      for (k = 0; k < 4; k++)
         Card.suitRank[k] = suitArr[k];
   }

   public static int getSuitRank(Suit st)
   {
      if (st == Suit.Clubs)
         return 0;

      if (st == Suit.Diamonds)
         return 1;

      if (st == Suit.Hearts)
         return 2;

      if (st == Suit.Clubs)
         return 3;

      // should not happen
      return 0;
   }

   public  static int getValueRank(char val)
   {
      int k;

      for (k = 0; k < cardArrayVal; k++)
         if (valueRank[k] == val)
            return k;

      // should not happen
      return 0;
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

/*----------------------------- sample run ------------------------------------

Queue's toString() Test: 
 8 of Clubs  4 of Hearts  JOKER  A of Spades  9 of Diamonds 

QueueEmptyException Test: 
 8 of Clubs  4 of Hearts  JOKER  A of Spades  9 of Diamonds  Queue Empty  Queue 
Empty  Queue Empty  Queue Empty  Queue Empty 

-----------------------------------------------------------------------------*/
