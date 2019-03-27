
public class Foothill
{
   public static void main(String[] args)
   {     
      Point.setRange(-20, 20);

      Point point1 = new Point(5,9);
      Point point2 = new Point(15,8);
      Point point3 = new Point(19,11);
      Point point4 = new Point(-100,21);
      
      System.out.print("Point 1:\n ");
      point1.displayPoint();
      
      System.out.print("Point 2:\n ");
      point2.displayPoint();
      
      System.out.print("Point 3:\n ");
      point3.displayPoint();
      
      System.out.print("Point 4:\n ");
      point4.displayPoint();
      
      Polygon polygon1 = new Polygon();
      
      polygon1.addPoint(3.1f, 4.4f);
      polygon1.addPoint(11.8f, 9.9f);
      polygon1.addPoint(point3);
      polygon1.addPoint(15.4f, 14.3f);
      polygon1.addPoint(9.4f, 1.2f);
      polygon1.addPoint(19.6f, 13.1f);
      polygon1.addPoint(4.5f, 12.1f);
      polygon1.addPoint(2.7f, 2.9f);
      polygon1.addPoint(5.1f, 8.1f);
      polygon1.addPoint(12.4f, 17.f);
      
      System.out.println("\nPolygon 1:");
      polygon1.showPolygon();

      float[] xArray = new float[10];
      xArray[0] = 10;
      xArray[1] = 5;
      xArray[2] = 9;
      xArray[3] = 19;
      xArray[4] = 11;
      xArray[5] = 14;
      xArray[6] = 3;
      xArray[7] = 6;
      xArray[8] = 18;
      xArray[9] = 12;
      
      float[] yArray = new float[10];
      yArray[0] = 13;
      yArray[1] = 2;
      yArray[2] = 4;
      yArray[3] = 17;
      yArray[4] = 8;
      yArray[5] = 5;
      yArray[6] = 16;
      yArray[7] = 14;
      yArray[8] = 1;
      yArray[9] = 9;
      
      Polygon polygon2 = new Polygon(10,xArray,yArray);
      polygon2.addPoint(2,2);
      System.out.println("\nPolygon 2:");
      polygon2.showPolygon();
      
      float[] xValue = new float[10];
      xValue[0] = 6;
      xValue[1] = 10;
      xValue[2] = 14;
      xValue[3] = 12;
      xValue[4] = 8;
      xValue[5] = 12;
      xValue[6] = 5;
      xValue[7] = 7;
      xValue[8] = 11;
      xValue[9] = 17;
      
      float[] yValue = new float[10];
      yValue[0] = 3;
      yValue[1] = 9;
      yValue[2] = 12;
      yValue[3] = 16;
      yValue[4] = 19;
      yValue[5] = 2;
      yValue[6] = 7;
      yValue[7] = 1;
      yValue[8] = 18;
      yValue[9] = 15;
     
      Polygon polygon3 = new Polygon(10,xValue,yValue);
      System.out.println("\nPolygon 3:");
      polygon3.showPolygon();
    
   }
}

class Point
{
   private float x;
   private float y;
   private static float MIN_VAL = -10;
   private static float MAX_VAL = +10;
   
   Point()
   {
      x = (MIN_VAL + MAX_VAL) / 2;
      y = (MIN_VAL + MAX_VAL) / 2;
   }
   
   Point(float x, float y)
   {      
      if (!set(x,y))
      {
         this.x = (MIN_VAL + MAX_VAL) / 2;
         this.y = (MIN_VAL + MAX_VAL) / 2;
      }
   }
   
   public boolean set(float x,float y)
   {
      if ( x < MIN_VAL || x > MAX_VAL || y < MIN_VAL || y > MAX_VAL )
      {
         this.x = (MIN_VAL + MAX_VAL) / 2;
         this.y = (MIN_VAL + MAX_VAL) / 2;
         return false;
      }
      else
      {
         this.x = x;
         this.y = y;
         return true;
      }      
   }
   
   public float getX()
   {
      return x;
   }
   
   public float getY()
   {
      return y;
   }
   
   public void displayPoint()
   {
      System.out.println( "(" + x + "," + y + ")");
   }
   
   public static boolean setRange(float newMinVal, float newMaxVal)
   {
      if ( newMinVal < newMaxVal )
      {
         MIN_VAL = newMinVal;
         MAX_VAL = newMaxVal;
         
         return true;
      }
      else
         return false;

   }
}
class Polygon
{
   static final int MAX_POINTS = 100;
   private int numPoints;
   private Point points[] = new Point[MAX_POINTS];
   
   Polygon()
   {
      numPoints = 0;
   }
   
   Polygon(int numPoints, float xArray[], float yArray[])
   {
      if (!setPoints(numPoints, xArray, yArray))
         numPoints = 0;
   }
   
   public boolean setPoints(int numPoints, float xValues[], float yValues[])
   {
      if ((numPoints < 0) || (numPoints > MAX_POINTS))
         return false;
      if ((xValues.length < numPoints) || (yValues.length < numPoints))
         return false;
      
      this.numPoints = numPoints;
      int i;
      
      for (i = 0; i < numPoints; i++)
      {
         if (points[i] == null)
            points[i] = new Point();
         if (!points[i].set(xValues[i], yValues[i]))
            return false;
      }
      return true;
   }
   
   public void showPolygon()
   {
      int i;
      for (i = 0; i < numPoints; i++)
      {
         System.out.print("");
         points[i].displayPoint();
      }

   }
   
   public boolean addPoint(float x, float y)
   {
      if (numPoints >= MAX_POINTS)
         return false;
  
      if (points[numPoints] == null)
         points[numPoints] = new Point();
      if (!points[numPoints].set(x, y))
         return false;
  
      numPoints++;
      return true;
      
   }
   
   public boolean addPoint(Point p)
   {
      
      if (numPoints >= MAX_POINTS)
         return false;
     
      points[numPoints] = p;
      
      numPoints++;
      
      return true;
   }
}
/* ---------------------------- paste of run ------------------------------
 * 
Point 1:
 (5.0,9.0)
Point 2:
 (15.0,8.0)
Point 3:
 (19.0,11.0)
Point 4:
 (0.0,0.0)

Polygon 1:
(3.1,4.4)
(11.8,9.9)
(19.0,11.0)
(15.4,14.3)
(9.4,1.2)
(19.6,13.1)
(4.5,12.1)
(2.7,2.9)
(5.1,8.1)
(12.4,17.0)

Polygon 2:
(10.0,13.0)
(5.0,2.0)
(9.0,4.0)
(19.0,17.0)
(11.0,8.0)
(14.0,5.0)
(3.0,16.0)
(6.0,14.0)
(18.0,1.0)
(12.0,9.0)
(2.0,2.0)

Polygon 3:
(6.0,3.0)
(10.0,9.0)
(14.0,12.0)
(12.0,16.0)
(8.0,19.0)
(12.0,2.0)
(5.0,7.0)
(7.0,1.0)
(11.0,18.0)
(17.0,15.0)

----------------------------------------------------------------------- */

