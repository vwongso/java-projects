
public class Foothill
{
   public static void main(String[] args)
   {
      String[] sImageIn = 
      { 
            "                                      ",
            "                                      ",
            "                                      ",
            "* * * * * * * * * * * * * * * * *     ",
            "*                                *    ",
            "**** * ****** ** ****** *** ****      ",
            "* ********************************    ",
            "*    *   *  * *  *   *  *   *  *      ",
            "* **    *      *   **    *       *    ",
            "****** ** *** **  ***** * * *         ",
            "* ***  ****    * *  **        ** *    ",
            "* * *   * **   *  *** *   *  * **     ",
            "**********************************    "
      };
      
      String[] sImageIn_2 = 
      { 
            "                                          ",
            "                                          ",
            "* * * * * * * * * * * * * * * * * * *     ",
            "*                                    *    ",
            "**** *** **   ***** ****   *********      ",
            "* ************ ************ **********    ",
            "** *      *    *  * * *         * *       ",
            "***   *  *           * **    *      **    ",
            "* ** * *  *   * * * **  *   ***   ***     ",
            "* *           **    *****  *   **   **    ",
            "****  *  * *  * **  ** *   ** *  * *      ",
            "**************************************    "
      };
      
      BarcodeImage bc = new BarcodeImage(sImageIn);
      DataMatrix dm = new DataMatrix(bc);
     
      // First secret message
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      // second secret message
      bc = new BarcodeImage(sImageIn_2);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      // create your own message
      dm.readText("CS 1B rocks more than Zeppelin");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
   } 
}

interface BarcodeIO
{
   public boolean scan(BarcodeImage bc);
   public boolean readText(String text);
   public boolean generateImageFromText();
   public boolean translateImageToText();
   public void displayTextToConsole();
   public void displayImageToConsole();
}

class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65; 
   
   private boolean[][] image_data;
   
   // default constructor, instantiate 2d array
   BarcodeImage() 
   {  
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      
      for(int row = 0; row < image_data.length; row++)
      {
         for(int col = 0; col < image_data.length; col++ )
         {
            image_data[row][col] = false;
         }
      }
   }
   
   // takes 1d string arrays, convert to 2d array of booleans
   BarcodeImage(String[] str_data) 
   {
      this();
      int row, rowString, col;
      
      if ( !checkSize( str_data ) )
         return;
      
      for( row = MAX_HEIGHT - 1, rowString = (str_data.length - 1);
            rowString > 0; row--, rowString--)
      {
         for( col = 0; col < str_data[rowString].length(); col++ )
         {
            if( str_data[rowString].charAt(col) == '*')
            {
               image_data[row][col] = true;
            }
            else if ( str_data[rowString].charAt(col) == ' ')
               image_data[row][col] = false;
         }
      }
   }
   
   // mutator
   boolean setPixel(int row, int col, boolean value)
   {
      if( row < 0 || row > MAX_HEIGHT || col < 0 || col > MAX_WIDTH )
      {
         return false;
      }
      
         image_data[row][col] = value;
      return true;
   }
   
   // accessor
   boolean getPixel(int row, int col)
   {
      if( row < 0 || row > MAX_HEIGHT || col < 0 || col > MAX_WIDTH )
      {
         return false;
      }
      
      return image_data[row][col];         
   }
   
   public void displayToConsole()
   {     
      // top border
      System.out.println();
      for (int col = 0; col < MAX_WIDTH + 2; col++)
      {
         System.out.print("-");
      }
      System.out.println();
      
      // side borders
      for (int row = 0; row < MAX_HEIGHT; row++)
      {
         System.out.print("|");
         
         for (int col = 0; col < MAX_WIDTH; col++)
         {
            if(image_data[row][col] == true)
               System.out.print('*');
            else
               System.out.print(' ');
         }
         
         System.out.println("|");
      }
      
      // bottom border
      for (int col = 0; col < MAX_WIDTH + 2; col++)
      {
         System.out.print("-");
      }     
      System.out.println();
   }
   
   public Object clone() throws CloneNotSupportedException
   {  
      BarcodeImage newImage = (BarcodeImage)super.clone();
      
      newImage.image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      
      for (int row = 0; row < MAX_HEIGHT; row++)
      {
         for (int col = 0; col < MAX_WIDTH; col++)
         {
            newImage.image_data[row][col] = this.image_data[row][col];
         }
      }
      
      return newImage;
   }
   
   //filters data
   private boolean checkSize(String[] data) 
   {
      if (data == null)
      {
         return false;
      }
      
      if (data.length > MAX_HEIGHT)
      {
         return false;
      }
      
      if (data[0].length() > MAX_WIDTH)
      {
         return false;
      }
      
      return true;
   }
}

class DataMatrix implements BarcodeIO
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;
   
   // default constructor
   DataMatrix()
   {
      image = new BarcodeImage();
      actualWidth = 0;
      actualHeight = 0;
      text = "";
   }
   
   // constructor, sets image
   DataMatrix(BarcodeImage image)
   {
      this();
      scan(image);
   }
   
   // constructor, sets text
   DataMatrix(String text)
   {
      this();
      readText(text);
   }
   
   // mutator for text
   public boolean readText(String text)
   {
      if(text != null)
      {
         this.text = text;
         return true;
      }
      
      else
         return false;
      
   }
   
   // mutator for image
   public boolean scan(BarcodeImage image)
   {
      if(image != null)
      { 
         try
         {
            this.image = (BarcodeImage)image.clone();
         }
         catch (CloneNotSupportedException e)
         {
         }      
            actualWidth = computeSignalWidth();
            actualHeight = computeSignalHeight();
      
         return true;
      }
      
      return false;
   }
   
   // accessors
   public int getActualWidth()
   {
      return actualWidth;
   }
   
   public int getActualHeight()
   {
      return actualHeight;
   }
  
   // determine actual width
   private int computeSignalWidth()
   {
     int col, signalWidth = 0;
      
      for (col = 0; image.getPixel(BarcodeImage.MAX_HEIGHT - 1, col) == true; 
            col++)
      {
         signalWidth++;
      }
      
      return signalWidth;
   }
   
   // determine actual height
   private int computeSignalHeight()
   {
      int row, signalHeight = 0;
      
      for (row = (BarcodeImage.MAX_HEIGHT - 1);
            image.getPixel(row, 0) == true; row--)
      {
         signalHeight++;
      }
      
      return signalHeight;
   }
   private char readCharFromCol(int col)
   {  
      int ASCII = 0;
      char readChar;
      boolean bool[];
      
      bool = new boolean[8];
      
      // read column-by-column
      for(int i = 0; i < 8 ; i++)
         if(image.getPixel(BarcodeImage.MAX_HEIGHT - 2 - i, col) == false)
         {
            bool[i] = false;
         }
         else
            bool[i] = true;
      
      // add values
      for(int i = 0; i < 8; i++)
      {
         if(bool[i] == true)
            ASCII = ASCII + (int) Math.pow(2, i);
      }
      
      readChar = (char) ASCII;
      
      return readChar;
   }
   
   private boolean WriteCharToCol(int col, int code)
   {     
      int bitTest, bitResult;
      boolean bool[];
      
      // checks validity
      if(col < 0 || col > BarcodeImage.MAX_WIDTH )
      {
         return false;
      }
      
      bool = new boolean[8];
      
      for (int i = 0; i < 8; i++)
      {
         bitTest = 1 << i;
         bitResult = bitTest & code;
         if ( bitResult == 0 )
         {
            bool[i] = false;
         }
         else
            bool[i] = true;
      }
      
      for(int i = 0; i < 8; i++)
      {
         if(bool[i] == false)
         {
            image.setPixel(BarcodeImage.MAX_HEIGHT - 2 - i, col, false);
         }
         else
            image.setPixel(BarcodeImage.MAX_HEIGHT - 2 - i, col, true);        
      }
      
      return true;
   }
   
   public boolean generateImageFromText()
   {
      
      if(text != null)
      {
         actualHeight = 10;
         actualWidth = text.length() + 2;
      
         for( int i = (BarcodeImage.MAX_HEIGHT - 1); 
               i <= (BarcodeImage.MAX_HEIGHT - 10); i--)
         {
            image.setPixel(i, 0, true);
         }
      
         for(int col = 0; col < actualWidth; col++)
         {
            image.setPixel(BarcodeImage.MAX_HEIGHT - 1, col, true);
         }
      
         for(int col = 1; col < (actualWidth - 1); col++)
            WriteCharToCol(col , text.charAt(col - 1));
      
         return true;
      }
      
      return false;
   }
   
   public boolean translateImageToText()
   {
      if(image != null)
      {
         for(int col = 1; col < (actualWidth - 1); col++)
         {
            System.out.print(readCharFromCol(col));
         }
      
         return true;
      }
      
      return false;
   }
   
   public void displayTextToConsole()
   {
      System.out.println(text);
   }

   public void displayImageToConsole()
   {      
      // top border
      for (int col = 0; col < actualWidth + 2; col++)
      {
         System.out.print("-");
      }
      System.out.println();
      
      // side borders
      for (int row = (BarcodeImage.MAX_HEIGHT - 10);
            row < BarcodeImage.MAX_HEIGHT; row++)
      {
         System.out.print("|");
         for (int col = 0; col < actualWidth; col++)
            if( image.getPixel(row, col) == true )
               System.out.print(BLACK_CHAR);
            else
               System.out.print(WHITE_CHAR);
         System.out.println("|");
      }
      
      // bottom border
      for (int col = 0; col < actualWidth + 2; col++)
      {
         System.out.print("-");
      }
      System.out.println();
      System.out.println();
   }
}

/*----------------------------- sample run ------------------------------------
 * 
Don't forget to remove the tabs!
------------------------------------
|* * * * * * * * * * * * * * * * * |
|*                                *|
|**** * ****** ** ****** *** ****  |
|* ********************************|
|*    *   *  * *  *   *  *   *  *  |
|* **    *      *   **    *       *|
|****** ** *** **  ***** * * *     |
|* ***  ****    * *  **        ** *|
|* * *   * **   *  *** *   *  * ** |
|**********************************|
------------------------------------

You did it!  Great work.  Celebrate.
----------------------------------------
|* * * * * * * * * * * * * * * * * * * |
|*                                    *|
|**** *** **   ***** ****   *********  |
|* ************ ************ **********|
|** *      *    *  * * *         * *   |
|***   *  *           * **    *      **|
|* ** * *  *   * * * **  *   ***   *** |
|* *           **    *****  *   **   **|
|****  *  * *  * **  ** *   ** *  * *  |
|**************************************|
----------------------------------------

CS 1B rocks more than Zeppelin
----------------------------------
|* * * * * * * * * * * * * * * * |
|*                               |
|***  * ***** **** **** *********|
|*  ** ***************** ********|
|* * *  *   *   *  *    * **     |
|*       * *  **    * * *    *** |
|*       *    ** * *  *  *  ** * |
|***  * *****  **     * *      **|
|*** *   **** ** *   *   *  * *  |
|********************************|
----------------------------------

-----------------------------------------------------------------------------*/