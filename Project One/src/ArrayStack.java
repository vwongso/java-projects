import java.util.EmptyStackException;
import java.util.NoSuchElementException;


public class ArrayStack implements Stack
{
   public static final int CAPACITY = 500;
   private int capacity;
   private int size;
   private Object S[];
   private int top = -1;
   
   public ArrayStack(int cap)
   {
      capacity = cap;
      S = new Object[cap];
   }
   
   public ArrayStack()
   {
      this(CAPACITY);
   }
   
   public boolean empty()
   {
      return (top < 0);
   }
   
   
   public Object peek() throws NoSuchElementException
   { 
      if(empty()) throw new NoSuchElementException("Stack is empty");
      
         return S[top+1];    
   } 
   
   public void push(Object obj) throws IllegalArgumentException
   {  
      if (S.length == size) 
         throw new IllegalArgumentException("Stack is too big");
      top++;
      S[top] = obj; // ++top or top++ 
   } 
   
   public Object pop() throws EmptyStackException
   { 
      Object elem; 
      if(empty()){System.out.println("stack empty");}
      elem = S[top]; 
      S[top] = null; 
      top--;
      return elem; 
   }
   
}