import java.util.Comparator; 
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class PQ 
{ 
   private Comparable[] pq; // exactly as in array-based PQ 
   private int N; 

   PQ()
   {
      pq = new Comparable[100];
   }
   
   PQ(int K) 
   {
      pq = new Comparable[K];
   } 
   public boolean isEmpty() 
   {
      return N == 0;
   } 
   
   public int size() 
   { 
      return N;
   } 
   
   public void insert(Comparable x) 
   {
      pq[++N] = x; 
      swim(N); 
   } // PQ ops 
   
   public Comparable getFirst() 
   {
      if (!isEmpty()) 
      {
          throw new IllegalStateException();
      }
      
      return pq[1];
      
  }
   
   public Comparable removeMin()
   {
      exch(1, N); 
      sink(1, N-1); 
      return pq[N--]; 
   }
   
   private void swim(int k) 
   {
      while (k > 1 && less(k/2, k)) 
      { 
         exch(k, k/2); // parent of 
         k = k/2; // node k is at k/2 
      }
   } // helper method 
   
   private void sink(int k, int N) 
   {
      while (2*k <= N) 
      { 
         int j = 2*k; 
         if (j < N && less(j, j+1)) j++; 
         if (!less(k, j)) break; 
         exch(k, j); 
         k = j; 
      }      
   } // helper method 
   
   private boolean less(int i, int j) 
   {      
      return (pq[i].compareTo(pq[j])) > 0; //>
   } // helper method 
   
   private void exch(int i, int j) 
   {
      Comparable swap = pq[i]; 
      pq[i] = pq[j]; 
      pq[j] = swap; 
   } // helper method 
} 
