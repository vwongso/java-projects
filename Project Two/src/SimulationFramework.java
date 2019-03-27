
public class SimulationFramework 
{ 
   public void scheduleEvent (Event newEvent) 
   {       
      eventQueue.insert(newEvent);
   } 
   
   public void run () 
   { 
      while (!eventQueue.isEmpty()) 
      { 
         // remove min element from priority queue (Min Heap) 
         Event nextEvent = (Event) eventQueue.removeMin(); 
         currentTime = nextEvent.time; 
         nextEvent.processEvent(); // what do you see here??? 
      } 
   }
   
   public int time () { return currentTime; } 
   
   private int currentTime = 0; 
   
   private PQ eventQueue = new PQ(); 
   
   public int weightedProbability()
   {
      double hold;

      hold = 1 + (int) ((100 - 1 + 1) * Math.random());
      
      if ( hold < 15 )
      {
         return 0;
      }
      else if ( hold <= 75 )
      {
         return 1;
      }
      else
      {
         return 2;
      }
   }
}
