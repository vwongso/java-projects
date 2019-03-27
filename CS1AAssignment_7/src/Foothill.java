
public class Foothill
{
   public static void main(String[] args)
   {
      iTunes song1 = new iTunes();
      iTunes song2 = new iTunes();
      iTunes song3 = new iTunes();

      iTunes song4 = new iTunes("Nobody Compares","One Direction",240,211000);
      iTunes song5 = new iTunes("Brave","Sara Bareilles",320,221000);
      iTunes song6 = new iTunes("Run","Snow Patrol",128,274000);
   
      System.out.println("Default Info: \n");
      System.out.println("Song 1:");
      song1.display();
      System.out.println("Song 2:");
      song2.display();
      System.out.println("Song 3:");
      song3.display();
   
      System.out.println("Song Info: \n");
      song1.setName("Demons");
      song1.setArtist("Imagine Dragons");
      song1.setBitrate(320);
      song1.setTotalTime(220000);
      System.out.println("Song 1:");
      song1.display();
   
      song2.setName("La Da Dee");
      song2.setArtist("Cody Simpson");
      song2.setBitrate(320);
      song2.setTotalTime(198000);
      System.out.println("Song 2:");
      song2.display();
   
      song3.setName("Counting Stars");
      song3.setArtist("One Republic");
      song3.setBitrate(192);
      song3.setTotalTime(257000);
      System.out.println("Song 3:");
      song3.display();
   
      System.out.println("Song 4:");
      song4.display();
      System.out.println("Song 5:");
      song5.display();
      System.out.println("Song 6:");
      song6.display();
   
      System.out.println("Modified Info of Third Song: ");
      song3.setTotalTime(999);
      song3.display();
   
      System.out.println("Modified Info of Fifth Song: ");
      song5.setArtist("abcde");
      song5.display();
   
      System.out.print("Artist's name set to (): ");
      if (!song1.setArtist(""))
      {
         System.out.print("Invalid \n");
      }
      else
         System.out.print("Validated\n");
   
      System.out.print("BitRate set to (10): ");
      if (!song6.setBitrate(10))
      {
         System.out.print("Bit Rate Error \n");
      }
      else
         System.out.print("Validated\n");
      System.out.print("Title of first song: ");
      System.out.print(song1.getName() + "\n");
      System.out.print("Artist name of second song: ");
      System.out.print(song2.getArtist());
   
   }
}
class iTunes
{
   private String name;
   private String artist;
   private int bitRate;
   private int totalTime;
   
   public static final int MIN_BITRATE = 64;
   public static final int MAX_BITRATE = 705;
   public static final int MIN_STR_LENGTH = 1;
   public static final int MAX_STR_LENGTH = 80;
   public static final int MIN_PLAY_TIME = 1000;
   public static final int MAX_PLAY_TIME = 1000*60*60;
   public static final int DEFAULT_BITRATE = 64;
   public static final int DEFAULT_PLAY_TIME = 1000;
   public static final String DEFAULT_STRING = "(undefined)";
   
   iTunes()
   {
      artist = DEFAULT_STRING;
      name = DEFAULT_STRING;
      bitRate = DEFAULT_BITRATE;
      totalTime = DEFAULT_PLAY_TIME;        
   }
   
   public String toString()
   {    
      String output_string;
      int timeInSec;
      
      timeInSec = getTotalTime()/1000;
      
      output_string = "Title: " + getName() + "\nArtist: " + getArtist() +
            "\nBit Rate: " + getBitrate() + "kbps" + "\nDuration: " +
            timeInSec + "s\n";
      
      return output_string;
   }
   
   public void display()
   {
      System.out.println(toString());
   }
   
   iTunes(String song_name, String artist_name,
         int song_bitrate, int song_totalTime)
   {
      if ( song_name.length() > MIN_STR_LENGTH ||
            song_name.length() < MAX_STR_LENGTH )           
      {
         name = song_name;
      }
      else
      {
         name = DEFAULT_STRING;
      }
      if ( artist_name.length() > MIN_STR_LENGTH ||
            artist_name.length() < MAX_STR_LENGTH )
      {
         artist = artist_name;
      }
      else
      {
         artist = DEFAULT_STRING;
      }
      if ( song_bitrate > MIN_BITRATE || song_bitrate < MAX_BITRATE )
      {
      bitRate = song_bitrate;
      }
      else
      {
         bitRate = DEFAULT_BITRATE;
      }
      if ( song_totalTime > MIN_PLAY_TIME || song_totalTime < MAX_PLAY_TIME )
      {
         totalTime = song_totalTime;
      }
      else
      {
         totalTime = DEFAULT_PLAY_TIME;
      }
   }
   
   public boolean setName (String theName)
   {
      if ( theName.length() < MIN_STR_LENGTH  ||
            theName.length() > MAX_STR_LENGTH )
      {
         return false;
      }
      else
      {
         name = theName;
         return true;
      }
   }
   
   public boolean setArtist(String artistName)
   {
      if ( artistName.length() < MIN_STR_LENGTH ||
            artistName.length() > MAX_STR_LENGTH )
      {
         return false;
      }
      else
      {
         artist = artistName;
         return true;
      }
   }
   
   public boolean setBitrate(int theBitrate)
   {
      if ( theBitrate < MIN_BITRATE || theBitrate > MAX_BITRATE )
      {
         return false;
      }
      else
      {
         bitRate = theBitrate;
         return true;
      }
   }
   
   public boolean setTotalTime(int theTotalTime)
   {
      if ( theTotalTime < MIN_PLAY_TIME || theTotalTime > MAX_PLAY_TIME )
      {
         return false;
      }
      else
      {
         totalTime = theTotalTime;
         return true;
      }
   }
   
   public String getName()
   {
      return name;
   }
   
   public String getArtist()
   {
      return artist;
   }
   
   public int getBitrate()
   {
      return bitRate;
   }
   
   public int getTotalTime()
   {
      return totalTime;
   }
   
}

/*------------------------------ paste of run --------------------------------

Default Info: 

Song 1:
Title: (undefined)
Artist: (undefined)
Bit Rate: 64kbps
Duration: 1s

Song 2:
Title: (undefined)
Artist: (undefined)
Bit Rate: 64kbps
Duration: 1s

Song 3:
Title: (undefined)
Artist: (undefined)
Bit Rate: 64kbps
Duration: 1s

Song Info: 

Song 1:
Title: Demons
Artist: Imagine Dragons
Bit Rate: 320kbps
Duration: 220s

Song 2:
Title: La Da Dee
Artist: Cody Simpson
Bit Rate: 320kbps
Duration: 198s

Song 3:
Title: Counting Stars
Artist: One Republic
Bit Rate: 192kbps
Duration: 257s

Song 4:
Title: Nobody Compares
Artist: One Direction
Bit Rate: 240kbps
Duration: 211s

Song 5:
Title: Brave
Artist: Sara Bareilles
Bit Rate: 320kbps
Duration: 221s

Song 6:
Title: Run
Artist: Snow Patrol
Bit Rate: 128kbps
Duration: 274s

Modified Info of Third Song: 
Title: Counting Stars
Artist: One Republic
Bit Rate: 192kbps
Duration: 257s

Modified Info of Fifth Song: 
Title: Brave
Artist: abcde
Bit Rate: 320kbps
Duration: 221s

Artist's name set to (): Invalid 
BitRate set to (10): Bit Rate Error 
Title of first song: Demons
Artist name of second song: Cody Simpson

--------------------------------------------------------------------------- */