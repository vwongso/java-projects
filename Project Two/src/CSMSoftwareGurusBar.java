
public class CSMSoftwareGurusBar 
{ 
   private int freeChairs = 50; 
   private double profit = 0.0; 
   private SimulationFramework simulation = new SimulationFramework(); 
   
   public static void main (String [ ] args) 
   { 
      CSMSoftwareGurusBar world = new CSMSoftwareGurusBar (); 
   } 
   
   CSMSoftwareGurusBar ()
   { 
      int t = 0; 
      while (t < 240) 
      { // simulate 4 hours of bar operation 
         t += randBetween(2, 5); // new group every 2-5 minutes 
         simulation.scheduleEvent(new ArriveEvent(t, 
               randBetween(1,5))); 
      } // group size ranges from 1 to 5 
      simulation.run(); 
      System.out.println("Total profits " + profit); 
   } 
   
   private int randBetween (int low, int high) 
   { 
      return low + (int) ((high - low + 1) * Math.random());
   }
   
   public boolean canSeat (int numberOfPeople) 
   { 
      System.out.println("Group of " + numberOfPeople + 
            " customers arrives at time " + simulation.time()); 
      if (numberOfPeople < freeChairs) 
      { 
         System.out.println("Group is seated"); 
         freeChairs -= numberOfPeople; 
         return true; 
      } 
      else 
         System.out.println("No Room, Group Leaves"); 
         return false; 
      }
   
   private void order (int beerType) 
   { 

      System.out.println("Serviced order for beer type " + beerType + 
            " at time " + simulation.time());    
      
      if( beerType == 1 )
      {
         profit += 2.00;
      }
      else if ( beerType == 2 )
      {
         profit += 3.00;
      }
      else if ( beerType == 3 )
      {
         profit += 4.00;
      }
   }
   
   private void leave (int numberOfPeople) 
   { 
      System.out.println("Group of size " + numberOfPeople + 
            " leaves at time " + simulation.time()); 
      freeChairs += numberOfPeople; 
   } 
   
   private class ArriveEvent extends Event 
   { 
      private int groupSize; 
      ArriveEvent (int time, int gs) { super(time); groupSize = gs; } 
      
      public void processEvent () 
      { 
         if (canSeat(groupSize)) 
         { 
            // place an order within 2 & 10 minutes 
            simulation.scheduleEvent ( 
                  new OrderEvent(time + randBetween(2,10), 
                        groupSize)); 
         } 
      } 
   }
   
   private class OrderEvent extends Event 
   { 
      private int groupSize; 
      OrderEvent (int time, int gs) { super(time); groupSize = gs; } 
      public void processEvent () 
      { 
         // each member of the group orders a beer (type 1,2,3) 
         for (int i = 0; i < groupSize; i++) 
            order(1 + simulation.weightedProbability()); 
         simulation.scheduleEvent (
               new LeaveEvent(time + randBetween(30,60),
                     groupSize));
         // schedule a leaveEvent for this group 
      } 
   } 
   
   private class LeaveEvent extends Event 
   { 
      LeaveEvent (int time, int gs) { super(time); groupSize = gs; } 
      private int groupSize; 
      public void processEvent () { leave(groupSize); } 
   } 
} 