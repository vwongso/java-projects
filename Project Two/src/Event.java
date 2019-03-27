
public abstract class Event implements Comparable 
{ 
   public final int time; 
   public Event (int t) 
   { 
      time = t; 
   } 
   
   abstract void processEvent (); 
   
   public int compareTo (Object o) 
   { 
      Event right = (Event) o; 
      if (time < right.time) return -1; 
      if (time == right.time) return 0; 
      return 1; 
   }  
}