import java.util.*;

public class Foothill
{
   public static void main(String[] args)
   {
      // test of part 1
      LinkedList<Card> myList = new LinkedList<Card>();

      Card card1 = Card.generateRandomCard();
      Card card2 = Card.generateRandomCard();
      Card card3 = Card.generateRandomCard();
      Card card4 = Card.generateRandomCard();
      Card card5 = Card.generateRandomCard();

      // duplicates every card in list
      for (int k = 0; k < 2; k++)
      {
         insert(myList, card1);
         insert(myList, card2);
         insert(myList, card3);
         insert(myList, card4);
         insert(myList, card5);
      }

      // shows 10 cards
      showList(myList);

      // test of remove method
      while(remove(myList, card1));
      ;
      while(remove(myList, card5));
      ;

      // shows 6 cards
      showList(myList);

      // test of removeAll method
      removeAll(myList, card2);
      removeAll(myList, card3);

      // shows 2 cards
      showList(myList);


      if(!removeAll(myList, card3))
         System.out.println("**instances have been emptied**\n\n");

      // test of part 2
      LinkedList<Card> list1 = new LinkedList<Card>();      
      LinkedList<Card> list2 = new LinkedList<Card>();

      char[] valueOrder = {'X', 'A', 'K', 'Q', 'J', 'T', '9', '8', '7',
         '6', '5', '4', '3', '2'};

      Card.Suit[] suitOrder = {Card.Suit.spades, Card.Suit.hearts, 
         Card.Suit.diamonds, Card.Suit.clubs};

      for(int k = 0; k < 2; k++)
      {
         // insert card1 to list1
         insert(list1, card1);
         CardWithOrderStack.pushOrdering();

         // insert card2 to list2
         Card.setRankingOrder(valueOrder, suitOrder, valueOrder.length);
         insert(list2, card1);
         CardWithOrderStack.popOrdering();

         // insert card2 to list1
         insert(list1, card2);
         CardWithOrderStack.pushOrdering();

         // insert card2 to list2
         Card.setRankingOrder(valueOrder, suitOrder, valueOrder.length);
         insert(list2, card2);
         CardWithOrderStack.popOrdering();

         // insert card3 to list1
         insert(list1, card3);
         CardWithOrderStack.pushOrdering();

         // insert card3 to list2
         Card.setRankingOrder(valueOrder, suitOrder, valueOrder.length);
         insert(list2, card3);
         CardWithOrderStack.popOrdering();

         // insert card4 to list1
         insert(list1, card4);
         CardWithOrderStack.pushOrdering();

         // insert card4 to list2
         Card.setRankingOrder(valueOrder, suitOrder, valueOrder.length);
         insert(list2, card4);
         CardWithOrderStack.popOrdering();

         // insert card5 to list1
         insert(list1, card5);
         CardWithOrderStack.pushOrdering();

         // insert card5 to list2
         Card.setRankingOrder(valueOrder, suitOrder, valueOrder.length);
         insert(list2, card5);
         CardWithOrderStack.popOrdering();
      }

      // print both lists
      System.out.println("List 1:");
      showList(list1);

      System.out.println("List 2 (reversed order of list 1):");
      showList(list2);   
   }  
  
   static boolean removeAll(LinkedList<Card> myList, Card x)
   {
      ListIterator<Card> iter;

      for (iter = myList.listIterator(); iter.hasNext(); )
         if (iter.next() == x)
         {
            while ( remove(myList, x) )
               ;
            return true;
         }

      return false;
   }
   
   static void insert(LinkedList<Card> myList, Card x)
   {
      ListIterator<Card> iter;
      Card listCard;

      for (iter = myList.listIterator(); iter.hasNext();)
      {
         listCard = iter.next();
         if( x.compareTo(listCard) < 0 )
         {
            iter.previous();
            break;
         }
      }

      iter.add(x);
   }
   
   static boolean remove(LinkedList<Card> myList, Card x)
   {
      ListIterator<Card> iter;

      for (iter = myList.listIterator(); iter.hasNext(); )
         if (iter.next() == x)
         {
            iter.remove();

            return true;
         }

      return false;
   }
   
   static void showList(LinkedList<Card> myList)
   {
      ListIterator<Card> iter;

      System.out.println("_____Here's the List_______\n");
      for (iter = myList.listIterator(); iter.hasNext();)
         System.out.println(iter.next());
      System.out.println("\n_____That's all!_______\n\n");
   }
}

class Card implements Comparable<Card>
{   
   // type and constants
   public enum Suit { clubs, diamonds, hearts, spades }
   
   // for ordering
   public static char[] valueRanks = { '2', '3', '4', '5', '6', '7', '8', '9', 
      'T', 'J', 'Q', 'K', 'A', 'X'};
   static Suit[] suitRanks = {Suit.clubs, Suit.diamonds, Suit.hearts, 
      Suit.spades};
   static int numValsInOrderingArray = 14;

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
   
   // sort member methods
   public int compareTo(Card otherCard)
   {
      if (this.value == otherCard.value)
         return ( getSuitRank(this.suit) - getSuitRank(otherCard.suit) );

      return ( 
            getValueRank(this.value) 
               - getValueRank(otherCard.value) 
            );
   }

   // helpers for compareTo()
   public static int getSuitRank(Suit st)
   {
      int k;

      for (k = 0; k < 4; k++) 
         if (suitRanks[k] == st)
            return k;

      // should not happen
      return 0;
   }

   public static int getValueRank(char val)
   {
      int k;

      for (k = 0; k < numValsInOrderingArray; k++) 
         if (valueRanks[k] == val)
            return k;

      // should not happen
      return 0;
   }
   
   // "global" static Foothill methods
   static Card generateRandomCard()
   {
      Card.Suit suit;
      char val;

      int suitSelector, valSelector;

      // get random suit and value
      suitSelector = (int) (Math.random() * 4);
      valSelector = (int) (Math.random() * 14);

      // pick suit
      suit = Card.Suit.values()[suitSelector];;
      val = turnIntIntoVal(valSelector);

      return new Card(val, suit);
   }

   static char turnIntIntoVal(int k)
   {
      k++; // put in range 1-13
      switch(k)
      {
      case 1:
         return 'A';
      case 10:
         return 'T';
      case 11:
         return 'J';
      case 12:
         return 'Q';
      case 13:
         return 'K';
      default:
         return (char)('0' + k);   // simple way to turn n into 'n'
      }
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
   
   public static void setRankingOrder( char[] valueOrder,
         Suit[] suitOrder, int numValsInOrder )
      {
         int k;

         if (numValsInOrder < 0 || numValsInOrder > 14)
            return;

         Card.numValsInOrderingArray = numValsInOrder;

         for (k = 0; k < numValsInOrder; k++)
         {
            Card.valueRanks[k] = valueOrder[k];
         }

         for (k = 0; k < 4; k++)
         {
            Card.suitRanks[k] = suitOrder[k];
         }
      }
}

class CardWithOrderStack extends Card
{
   // stack
   static Stack<OrderObject> objectStack = new Stack<OrderObject>();

   public static void pushOrdering()
   {
      OrderObject orderObject = new OrderObject();

      // copies current ordering
      for(int k = 0; k < Card.valueRanks.length; k++)
      {
         orderObject.valueRanks[k] = valueRanks[k];
      }
      
      for(int k = 0; k < Card.suitRanks.length; k++)
      {
         orderObject.suitRanks[k] = suitRanks[k];
      }
      
      objectStack.push(orderObject);
   }

   public static void popOrdering()
   {
      OrderObject orderObject = new OrderObject();
      orderObject = objectStack.pop();

      Card.setRankingOrder(orderObject.valueRanks, orderObject.suitRanks,
         orderObject.valueRanks.length); 
   }

   static private class OrderObject
   {
      public char[] valueRanks = new char[Card.valueRanks.length];
      public Suit[] suitRanks = new Suit[4];
   }
}

/*----------------------------- sample run ------------------------------------
 * 
_____Here's the List_______

2 of spades
2 of spades
6 of diamonds
6 of diamonds
J of hearts
J of hearts
Q of diamonds
Q of diamonds
A of diamonds
A of diamonds

_____That's all!_______


_____Here's the List_______

2 of spades
2 of spades
6 of diamonds
6 of diamonds
Q of diamonds
Q of diamonds

_____That's all!_______


_____Here's the List_______

2 of spades
2 of spades

_____That's all!_______


instances have been emptied


List 1:
_____Here's the List_______

2 of spades
2 of spades
6 of diamonds
6 of diamonds
J of hearts
J of hearts
Q of diamonds
Q of diamonds
A of diamonds
A of diamonds

_____That's all!_______


List 2 (reversed order of list 1):
_____Here's the List_______

A of diamonds
A of diamonds
Q of diamonds
Q of diamonds
J of hearts
J of hearts
6 of diamonds
6 of diamonds
2 of spades
2 of spades

_____That's all!_______

-----------------------------------------------------------------------------*/