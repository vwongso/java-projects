import java.util.NoSuchElementException;


public class ArrayQueue implements Queue
{
   public static final int CAPACITY = 20;
   private int capacity;
   private Object S[];
   private int front = -1;
   private int rear = -1;
   private int index = -1;
   
   
   public ArrayQueue(int cap)
   {
      capacity = cap;
      S = new Object[capacity];
   }
   
   public ArrayQueue()
   {
      this(CAPACITY);
   }
   
   public boolean isEmpty()
   {
      return( index < 0 );
   }
   
   public Object getFrontElement()
   {
      if(isEmpty())
      {
         System.out.println("Queue empty from getFront");
      }
      return S[0];
   }
   
   public Object getRearElement()
   {
      if(isEmpty())
      {
         System.out.println("");
      }
      return S[rear];
   }
   
   public void put(Object theObject) throws IllegalArgumentException
   {
      if(S.length > capacity)
         throw new IllegalArgumentException("Queue Overflow");
         
      index++;  
      S[index] = theObject;
      
   }
   public int getIndex()
   {
      return index;
   }
   public Object remove() throws NoSuchElementException
   {
      Object frontItem = S[0];
         if(isEmpty())
            throw new NoSuchElementException("Queue empty from remove");   
      //frontItem = S[0];
      index--;
      for(int i = 0; i <= index; i++)
      {
         S[i] = S[i+1];
         
      }
         return frontItem;
   }
   public String toString()
   {
      String debug = new String("");
      for (int i = 0; i <= index; i++)
      {
         debug += S[i] + " ";
      }
      return debug;
   }
}