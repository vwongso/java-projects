
public class Foothill
{
   public static void main(String[] args)
   {
      Writer writer1, writer2, writer3;
      Actor actor1, actor2, actor3;
      Agent agent;
      
      //input writer data
      writer1 = new Writer("James Cameron",175000,5,true,false,true,"producer");
      writer2 = new Writer("George Lucas",125000,7,true,
            false,false,"story editor");
      writer3 = new Writer("Stephen Spielberg",250000,8,true,
            false,true,"executive producer");
      
      //input actor data
      actor1 = new Actor("Finnegan Harries",70000,3,'M',20);
      actor2 = new Actor("Sheldon Cooper",88000,2,'M',33);
      actor3 = new Actor("Ellen DeGeneres",50000,5,'F',24);
        
      //input agent data
      agent = new Agent("Richard Lovett");
      
      //print individual data
      System.out.println("Individual Data:");
      System.out.println("Writer 3: Client name: " + writer3.getName());
      System.out.println("Writer 2: Income this year: $" 
            + writer2.getIncomeThisYear());
      System.out.println("Writer 1: Rank: " + writer1.getRank());
      System.out.println("Actor 1: Gender: " + actor1.getGender());
      System.out.println("Actor 2: Client name: " + actor2.getName());
      System.out.println("Actor 3: Age: " + actor3.getAge() + "\n");

      //add client to agent
      agent.addClient(writer1);
      agent.addClient(writer2);
      agent.addClient(writer3);
      agent.addClient(actor1);
      agent.addClient(actor2);
      agent.addClient(actor3);

      //print client names only
      System.out.println("Clients List: \n" + agent.toStringClientsShort());
      
      //print client names w/ info
      System.out.println("\nClients List with Info: \n" 
            + agent.toStringClientsLong());
      
      //print agent's income
      System.out.println("Agent's income this year: $" + agent.getIncome());     
      
   }
}

class Client
{
   //private members
   private String name;
   private long incomeThisYear;
   private double percentCut;
   
   //limits
   protected static final String DEFAULT_NAME = "unknown";
   protected static final double MIN_NAME_LEN = 3;
   protected static final double MAX_NAME_LEN = 60;
   protected static final long MIN_INCOME = 0;
   protected static final long MAX_INCOME = 1000000000;
   protected static final double MIN_PERCENT = 0;
   protected static final double MAX_PERCENT = 99.99;
   
   public Client() // constructor w/ no parameters
   {
      this(DEFAULT_NAME,MIN_INCOME,MIN_PERCENT);
   }

   //constructor that takes all parameters
   public Client(String name, long incomeThisYear, double percentCut) 
   {
      if(setName(name))
      {
         this.name = name;
      }
      else
         this.name = DEFAULT_NAME;
      
      if(setIncomeThisYear(incomeThisYear))
      {
         this.incomeThisYear = incomeThisYear;
      }
      else
         this.incomeThisYear = MIN_INCOME;
      
      if (setPercentCut(percentCut))
      {
         this.percentCut = percentCut;
      }
      else
         this.percentCut = MIN_PERCENT;
   }
   
   private static boolean isValidName(String name) //validate value
   {
      if(name.length() < MIN_NAME_LEN || name.length() > MAX_NAME_LEN)
      {
         return false;
      }     
      return true;
   }

   private static boolean isValidIncome(long incomeThisYear) //validate value
   {
      if(incomeThisYear < MIN_INCOME || incomeThisYear > MAX_INCOME)
      {
         return false;
      }
        
      return true;
   }
   
   private static boolean isValidPercent(double percentCut) //validate value
   {
      if(percentCut < MIN_PERCENT || percentCut > MAX_PERCENT)
      {
         return false;
      }
      
      return true;
   }
   
   public boolean setName(String name) //set name if validation is true
   {
      if(isValidName(name))
      {
         this.name = name;
         return true;
      }
      else
         return false;

   }
   
   //set income if validation is true
   public boolean setIncomeThisYear(long incomeThisYear)
   {
      if(isValidIncome(incomeThisYear))
      {
         this.incomeThisYear = incomeThisYear;
         return true;
      }
      else
         return false;
   }
   
   //set percent cut if validation is true
   public boolean setPercentCut(double percentCut)
   {
      if(isValidPercent(percentCut))
      {
         this.percentCut = percentCut;
         return true;
      }
      else
         return false;
   }
   
   public String getName() 
   {
       return name;
   }

   public long getIncomeThisYear() 
   {
       return incomeThisYear;
   }

   public double getPercentCut() 
   {
       return percentCut;
   }
   
   public String toString() //display
   {
      String clientData;
      
      clientData = "Client name: " + getName() + "\nIncome this year: $" 
      + getIncomeThisYear() + "\nPercent cut: " + getPercentCut();
      
      return clientData;
   }
}

class Writer extends Client
{
   private boolean technical, government, international;
   private String rank;
   
   private static final String DEFAULT_RANK = "Undefined";
   
   public Writer() //constructor w/ no parameters
   {
      this(DEFAULT_NAME,MIN_INCOME,MIN_PERCENT,false,false,false,DEFAULT_RANK);
   }

   //constructor that takes all parameters
   public Writer(String name, long incomeThisYear, double percentCut, boolean 
         technical,boolean government,boolean international, String rank) 
   {
       super(name, incomeThisYear, percentCut);
       
       setTechnical(technical);
       setGovernment(government);
       setInternational(international);
       if(setRank(rank))
       {
          this.rank = rank;
       }
       else
          this.rank = DEFAULT_RANK;
   }


   public void setTechnical(boolean technical) 
   {
       this.technical = technical;
   }

   public void setGovernment(boolean government) 
   {
       this.government = government;
   }

   public void setInternational(boolean international) 
   {
       this.international = international;
   }
   
   private static boolean isValidRank(String rank) //validate value
   {
      if (rank == "staff writer" || rank == "story editor"
         || rank == "co-producer" || rank == "producer"
         || rank == "co-executive producer" || rank == "executive-producer")
      {
         return true;
      }
      
      return false;
   }
   
   public boolean setRank(String rank) //set value after validated
   {
      if(isValidRank(rank))
      {
         this.rank = rank;
         return true;
      }
      else
         return false;
   }
   
   public boolean getTechnical() 
   {
       return technical;
   }

   public boolean getGovernment() 
   {
       return government;
   }

   public boolean getInternational() 
   {
       return international;
   }

   public String getRank() 
   {
       return rank;
   } 
   
   public String toString() // display
   {
      String technical = new String();
      String government = new String();
      String international = new String();
      String backgroundString = new String();
      
      if(getTechnical() == false)
      {
         technical = "";
      }      
      else
         technical = "\nWriter has background in technology";
      
      if(getGovernment() == false)
      {
         government = "";
      }
      else
         government = "\nWriter has background in government/politics";
      
      if(getInternational() == false)
      {
         international = "";
      }
      else
         international = "\nWriter has a great deal of travel experience";
      
     backgroundString = super.toString() + technical + government 
           + international + "\nRank: " + getRank();
      
      return backgroundString;
   }
}

class Actor extends Client
{
   private char gender;
   private int age;
   
   protected static final char DEFAULT_GENDER = 'X';
   private static final int DEFAULT_AGE = 0;
   private static int MIN_AGE = 0;
   private static int MAX_AGE = 120;

   public Actor() // constructor w/ no parameters
   {
      this(DEFAULT_NAME, MIN_INCOME, MIN_PERCENT, DEFAULT_GENDER, DEFAULT_AGE);
   }
   
   // constructor that takes all parameters
   public Actor(String name, long incomeThisYear, double percentCut, 
         char gender,int age)
   {
       super(name, incomeThisYear, percentCut);
       if(setGender(gender))
          this.gender = gender;
       else
          this.gender = DEFAULT_GENDER;
       
       if(setAge(age))
          this.age = age;
       else
          this.gender = DEFAULT_AGE;
   }
   
   private static boolean isValidGender(char gender) //filter bad values
   {
      if (gender == 'M' || gender == 'F')
      {
         return true;
      }
      
      return false;
   }
   
   private static boolean isValidAge(int age) //filter bad values
   {
      if (age < MIN_AGE || age > MAX_AGE)
      {
         return false;
      }
      return true;
   }
   
   public boolean setGender(char gender) //set gender after validated
   {
      if(isValidGender(gender))
      {
         this.gender = gender;
         return true;
      }
      else
         return false;
   }
   
   public boolean setAge(int age) //set age after validated
   {
      if(isValidAge(age))
      {
         this.age = age;
         return true;
      }
      else
         return false;
   }
   
   public String toString() //display
   {
      String actorData;
      
      actorData = super.toString() + "\nGender: " 
            + getGender() + "\nAge: " + getAge();
      
      return actorData;
   }

   public char getGender() 
   {
       return gender;
   }

   public int getAge() 
   {
       return age;
   }

}

class Agent
{
   private String name;
   private Client myClients[];
   private int numClients;

   private static final int MIN_NAME_LENGTH = 3;
   private static final int MAX_NAME_LENGTH = 60;
   private static final String DEFAULT_NAME = "Undefined";
   private static final int MAX_CLIENTS = 20;

   public Agent(String name) //constructor
   {
      if(!setName(name))
      {
         name = DEFAULT_NAME;
      }     
      numClients = 0;
      myClients = new Client[MAX_CLIENTS];    

   }

   private static boolean isValidName(String name) //filter bad values
   {
      if(name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
      {
         return false;
      }
      return true;
   }
   
   public boolean setName(String name) //set name after validated
   {
      if(isValidName(name))
      {
         this.name = name;
         return true;
      }
      else
         return false;
   }
   
   public boolean addClient(Client client) //adds client
   {
      if(numClients <= MAX_CLIENTS)
      {
      
         myClients[numClients] = client;
         numClients++;
      
         return true;
      }
      return false;
   }

   public boolean removeClient(Client client) //removes client
   {
      if(numClients != 0)
      {     
         for(int k = 0; k < myClients.length; k++)
         {
            if(myClients[k] == client)
            {
               for(int i = k; i < numClients; i++)
               {
                  myClients[i] = myClients[i+1];
               } 
               numClients--;
               return true;
            }
         }     
         return true;
      }
      return false;
   }

   public String toStringClientsShort() //display names
   {
      String stringClientsShort = new String();
      
      for(int k = 0; k < numClients; k++)
      {
         stringClientsShort = stringClientsShort 
               + myClients[k].getName() + "\n";
      }
      
      return stringClientsShort;
   }

   public String toStringClientsLong() // display names w/ info
   {
      String stringClientsLong = new String();
      
      for(int k = 0; k < numClients; k++)
      {
         stringClientsLong = stringClientsLong 
               + myClients[k].toString() + "\n\n";
      }
      
      return stringClientsLong;
   }

   public double getIncome() //agent's income
   {
       double getIncome = 0;

       for(Client client: getMyClient())
       {
           if(client != null)
           {
           getIncome = getIncome + (client.getPercentCut()/100) * 
                 client.getIncomeThisYear();
           }
       }
       return getIncome;
   }

   public String getName()
   {
       return name;
   }

   public void setMyClient(Client myClient[])
   {
       this.myClients = myClient;
   }

   public Client[] getMyClient()
   {
       return myClients;
   }

   public void setNumClients(int numClients)
   {
       this.numClients = numClients;
   }

   public int getNumClients()
   {
       return numClients;
   }

}

/* ---------------------------- paste of run ------------------------------
 * 
Individual Data:
Writer 3: Client name: Stephen Spielberg
Writer 2: Income this year: $125000
Writer 1: Rank: producer
Actor 1: Gender: M
Actor 2: Client name: Sheldon Cooper
Actor 3: Age: 24

Clients List: 
James Cameron
George Lucas
Stephen Spielberg
Finnegan Harries
Sheldon Cooper
Ellen DeGeneres


Clients List with Info: 
Client name: James Cameron
Income this year: $175000
Percent cut: 5.0
Writer has background in technology
Writer has a great deal of travel experience
Rank: producer

Client name: George Lucas
Income this year: $125000
Percent cut: 7.0
Writer has background in technology
Rank: story editor

Client name: Stephen Spielberg
Income this year: $250000
Percent cut: 8.0
Writer has background in technology
Writer has a great deal of travel experience
Rank: Undefined

Client name: Finnegan Harries
Income this year: $70000
Percent cut: 3.0
Gender: M
Age: 20

Client name: Sheldon Cooper
Income this year: $88000
Percent cut: 2.0
Gender: M
Age: 33

Client name: Ellen DeGeneres
Income this year: $50000
Percent cut: 5.0
Gender: F
Age: 24


Agent's income this year: $43860.0

----------------------------------------------------------------------- */