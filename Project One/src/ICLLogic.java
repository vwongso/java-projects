import java.awt.*;
import java.applet.*;

public class ICLLogic extends Applet
{
  private  int dimension = 2 ;
  /*This is the 2D array where the numbers are going to be saved. You will use
  this array in both labelBreadth and labelDepth*/
  private  int [][] pixels ;

  private final static int PIXELDIM = 18;

  private final static int  CURRENTPIXEL = 1 ;
  private final static int  CHECKPIXEL = 2   ;
  private final static int  ADDDATASTRUCTURE = 3;

  boolean depthFirst ;   //Is it depth first or breadth first?


  private int delay = 250 ;

  private int state = 0 ;
  //This is the queue to be used in labelBreadth
  ArrayQueue q = new ArrayQueue(20);
  //This is the stack to be used in labelDepth
  ArrayStack s = new ArrayStack(255);


  public ICLLogic (boolean depthFirst)
  {
    this.depthFirst = depthFirst ;
  }
  
  private void setDimension ( int dimension )
  {
    this.dimension = dimension ;
  }

  /*This method is responsible of drawing the squares*/
  public void generateImage ( int dimension)
  {
    this.setDimension(dimension);
    pixels = new int [dimension + 2][dimension + 2];
    for ( int i = 0 ; i < dimension + 1 ; i ++ )
    {
      pixels[0][i] = pixels[dimension + 1][i] = 0 ;  // bottom and top
      pixels[i][0] = pixels[i][dimension + 1] - 0 ;  // left and right

    }

    for ( int i = 1 ; i <= dimension ;  i++ )
    {
      for ( int j = 1 ; j <= dimension ; j++ )
      {
        pixels[i][j] = (int) (Math.random() *2) ;
      }
    }
  }



  public void draw (Graphics g )
  {
    if ( pixels != null )
    {
      for ( int i = 1 ; i <= dimension ;  i++ )
      {
        for ( int j = 1 ; j <= dimension ; j++ )
        {

          drawPixel (g, i , j  , pixels[i][j]);

        }
      }
    }

  }

  /*This method is responsible for coloring the square and labelling each one
  with a number. You will need this method also in labelDepth and labelbreadth
  since it also sets the color of each square.*/
  public void drawPixel (Graphics  g , int y , int x , int number)
  {
  /*The color is not fully random..Re-implement it so it becomes fully random*/
    Color current ;
      if(number<=4 && number!=0 &&number!=1)
        current=new Color(40*number,60,100);
      else if (number>4 && number<8)
        current=new Color(20*number,50+20*number,150+number);
      else if(number>=8 && number<13)
        current=new Color(5*number,100,30*(number-4));
      else if(number>13)
        current =new Color(118,10*(number-5),20*(number-10));
      else
        current=Color.gray;

      g.setColor(current);
      g.fillRect(x*PIXELDIM , y*PIXELDIM , PIXELDIM , PIXELDIM);

      current = Color.black;
      g.setColor(current);


      g.drawRect(x*PIXELDIM , y*PIXELDIM , PIXELDIM , PIXELDIM);
      String str= new String(""+number); //writes the number on the square
      g.drawString(str,  x*PIXELDIM + PIXELDIM/4 ,
       y*PIXELDIM + 2*PIXELDIM /3 );

  }

  public void labelDepth(Graphics g,int Dimension)
  {
     Position [] offset = new Position[4];
     offset[0] = new Position(0,1);
     offset[1] = new Position(1,0);
     offset[2] = new Position(0,-1);
     offset[3] = new Position(-1,0);
     
     Position here = new Position(1, 1); 
     int option = 0; 
     int lastOption = 3;
     int increment = 1;
     
     int row1 = 0, col1 = 0;
     for(int r = 0; r <= dimension; r++)
     {
        for(int c = 0; c <= dimension; c++)
        {
           if(pixels[r][c] == 1)
           {
              increment++;
              here = new Position(r,c);
              //Thread.sleep(delay);
              s.push(new Position(r,c));
              drawPixel(g,r,c,increment);
              pixels[r][c] = increment;     
              option = 0;
              
              while(true)
              {
                 System.out.println("4");
                 while(option <= lastOption)
                 {
                    row1 = here.row + offset[option].row;
                    col1 = here.col + offset[option].col;                    
                    if(pixels[row1][col1] == 1)
                    {                      
                       break;
                    }
                    option++;
                 }
                 if(option <= lastOption)
                 {
                    s.push(new Position(row1,col1));
                    //Thread.sleep(delay)
                    drawPixel(g,row1,col1,increment);
                    pixels[row1][col1] = increment;
                    here = new Position(row1,col1);
                    option = 0;

                 }
                 else
                 {
                    if(!s.empty())
                    {
//                       System.out.println("row" + row1 + "col" + col1);
                       Position next = (Position) s.pop();
                       here = next;
                       option = 0;

                    }
                    else
                    {
                       break;
                    }
                    
                 }
              } 
           }
           
        }    
     }
   }




  public void labelBreadth(Graphics g,int Dimension)
  {
     Position [] offset = new Position [4]; 
     offset[0] = new Position(0, 1); // right 
     offset[1] = new Position(1, 0); // down 
     offset[2] = new Position(0, -1); // left 
     offset[3] = new Position(-1, 0);

     Position here = new Position(1,1);
     int option = 0;
     int lastOption = 3;
     int increment = 1;
     int row1 = 0, col1 = 0;

     for (int j = 1; j <= dimension; j++)
     {
        System.out.println("1");
        for (int k = 1; k <= dimension; k++)
        {
           System.out.println("2");
           System.out.println("pixel: " + pixels[j][k]);
           if ( pixels[j][k] == 1 )
           {
              System.out.println("3");
              increment++;
              drawPixel(g,j,k,increment);
              pixels[j][k] = increment;
              q.put(new Position(j,k));
              here = new Position(j,k);

              while(true)
              {
                 System.out.println("A");
                 System.out.println(q.getIndex());
                 option = 0;
                 while(option <= lastOption)
                 {
                      
                    row1 = here.row + offset[option].row;
                    col1 = here.col + offset[option].col;
                    System.out.println("row:" + row1 + " col:" + col1);
                    if (pixels[row1][col1] == 1)
                    {
                       q.put(new Position(row1,col1));
                       drawPixel(g,row1,col1,increment);
                       pixels[row1][col1] = increment;  
                       here = new Position(row1,col1);
                       option = 0;
                    }   
                    option++;
                 }

                 if(q.isEmpty())
                 {
                    break;
                 }
                 else
                 {
                    q.remove();
                    Position next = (Position) q.getFrontElement();
                    here = next;
                    option = 0;
                 }
              }
           }
        }        

     }
  }

}