
public class Foothill
{
   public static void main(String[] args)
   {
      DateProfile applicant1 = new DateProfile ('M','F',7,4,"Hugh Hefner");
      DateProfile applicant2 = new DateProfile ('F','M',6,9,"Carmen Miranda");
      DateProfile applicant3 = new DateProfile ('M','F',9,3,"Brad Pitt");
      DateProfile applicant4 = new DateProfile ('F','M',8,5,"Mary Lynn "
            + "Rajskub");

      System.out.println(applicant1.getName());
      System.out.println("     " + applicant1.getName() + " fit "
            + applicant1.fitValue(applicant1));
      System.out.println("     " + applicant2.getName() + " fit "
            + applicant1.fitValue(applicant2));
      System.out.println("     " + applicant3.getName() + " fit "
            + applicant1.fitValue(applicant3));
      System.out.println("     " + applicant4.getName() + " fit "
            + applicant1.fitValue(applicant4));
     
      System.out.println("\n" + applicant2.getName());
      System.out.println("     " + applicant1.getName() + " fit "
            + applicant2.fitValue(applicant1));
      System.out.println("     " + applicant2.getName() + " fit "
            + applicant2.fitValue(applicant2));
      System.out.println("     " + applicant3.getName() + " fit "
            + applicant2.fitValue(applicant3));
      System.out.println("     " + applicant4.getName() + " fit "
            + applicant2.fitValue(applicant4));
     
      System.out.println("\n" + applicant3.getName());
      System.out.println("     " + applicant1.getName() + " fit "
            + applicant3.fitValue(applicant1));
      System.out.println("     " + applicant2.getName() + " fit "
            + applicant3.fitValue(applicant2));
      System.out.println("     " + applicant3.getName() + " fit "
            + applicant3.fitValue(applicant3));
      System.out.println("     " + applicant4.getName() + " fit "
            + applicant3.fitValue(applicant4));
     
      System.out.println("\n" + applicant4.getName());
      System.out.println("     " + applicant1.getName() + " fit "
            + applicant4.fitValue(applicant1));
      System.out.println("     " + applicant2.getName() + " fit "
            + applicant4.fitValue(applicant2));
      System.out.println("     " + applicant3.getName() + " fit "
            + applicant4.fitValue(applicant3));
      System.out.println("     " + applicant4.getName() + " fit "
            + applicant4.fitValue(applicant4));
      
   }

}
class DateProfile
{
   private char gender;
   private char searchGender;
   private int romance;
   private int finance;
   private String name;
   
   public static final char DEFAULT_GENDER = 'M';
   public static final char DEFAULT_SEARCHGENDER = 'F';
   public static final int DEFAULT_INT = 0;
   public static final int MIN_VALUE = 1;
   public static final int MAX_VALUE = 10;
   public static final int MIN_LENGTH = 2;
   public static final int MAX_LENGTH = 100;
   public static final String DEFAULT_NAME = "unknown";
   
   DateProfile()
   {
      gender = DEFAULT_GENDER;
      searchGender = DEFAULT_SEARCHGENDER;
      romance = DEFAULT_INT;
      finance = DEFAULT_INT;
      name = DEFAULT_NAME;
   }
   
   DateProfile(char gender, char searchGender,
         int romance, int finance, String name)
   {
      if ( gender == 'M' || gender == 'F' )
      {
         this.gender = gender;
      }
      else
      {
         this.gender = DEFAULT_GENDER;
      }
      
      if ( searchGender == 'F' || searchGender == 'M')
      {
         this.searchGender = searchGender;
      }
      else
      {
         this.searchGender = DEFAULT_SEARCHGENDER;
      }
      
      if ( romance > MIN_VALUE && romance < MAX_VALUE )
      {
         this.romance = romance;
      }
      else
      {
         this.romance = DEFAULT_INT;
      }
      
      if ( finance > MIN_VALUE && finance < MAX_VALUE )
      {
         this.finance = finance;
      }
      else
      {
         this.finance = DEFAULT_INT;
      }
      
      if ( name.length() > MIN_LENGTH && name.length() < MAX_LENGTH )
      {
         this.name = name;
      }
      else
      {
         this.name = DEFAULT_NAME;
      }
   }
   
   public double fitValue(DateProfile partner)
   {
      return determineGenderFit(partner) * determineRomanceFit(partner) *
            determineFinanceFit(partner);
   }
   
   private double determineGenderFit(DateProfile partner)
   {
      if ( searchGender != partner.gender || partner.searchGender != gender)
      {
         return 0;
      }
      else
      {
      return 1;
      }
   }
   
   private double determineRomanceFit(DateProfile partner)
   {
      int myRomance,partnerRomance,difference;
      double romanceFit;

      myRomance = romance;
      partnerRomance = partner.romance;
      difference = Math.abs(myRomance - partnerRomance);
      romanceFit = ((MAX_VALUE - 1) - (difference)) / ((double)(MAX_VALUE - 1));
      return romanceFit;
   }
   
   private double determineFinanceFit(DateProfile partner)
   {
      int difference;
      double financeFit;

      difference = Math.abs(finance - partner.finance);
      financeFit = ((MAX_VALUE - 1) - (difference)) / ((double)(MAX_VALUE - 1));
      return financeFit;
   }
   
   public boolean setGender(char theGender)
   {
      if ( theGender != 'M' && theGender != 'F' )
      {
         return false;
      }
      else
      {
         gender = theGender;
         return true;
      }
   }
   
   public boolean setSearchGender(char theSearchGender)
   {
      if ( theSearchGender != 'F' && theSearchGender != 'M' )
      {
         return false;
      }
      else
      {
         searchGender = theSearchGender;
         return true;
      }
   }
   
   public boolean setRomance(int theRomance)
   {
      if ( theRomance < MIN_VALUE || theRomance > MAX_VALUE )
      {
         return false;
      }
      else
      {
         romance = theRomance;
         return true;
      }
   }
   
   public boolean setFinance(int theFinance)
   {
      if ( theFinance < MIN_VALUE || theFinance > MAX_VALUE )
      {
         return false;
      }
      else
      {
         finance = theFinance;
         return true;
      }
   }
   
   public boolean setName(String theName)
   {
      if ( theName.length() < MIN_LENGTH || theName.length() > MAX_LENGTH)
      {
         return false;
      }
      else
      {
         name = theName;
         return true;
      }
   }
   
   public char getGender()
   {
      return gender;
   }
   
   public char getSearchGender()
   {
      return searchGender;
   }
   
   public int getRomance()
   {
      return romance;
   }
   
   public int getFinance()
   {
      return finance;
   }
   
   public String getName()
   {
      return name;
   }
}

/*------------------------------ paste of run ---------------------------------

Hugh Hefner
     Hugh Hefner fit 0.0
     Carmen Miranda fit 0.3950617283950617
     Brad Pitt fit 0.0
     Mary Lynn Rajskub fit 0.7901234567901234

Carmen Miranda
     Hugh Hefner fit 0.3950617283950617
     Carmen Miranda fit 0.0
     Brad Pitt fit 0.2222222222222222
     Mary Lynn Rajskub fit 0.0

Brad Pitt
     Hugh Hefner fit 0.0
     Carmen Miranda fit 0.2222222222222222
     Brad Pitt fit 0.0
     Mary Lynn Rajskub fit 0.691358024691358

Mary Lynn Rajskub
     Hugh Hefner fit 0.7901234567901234
     Carmen Miranda fit 0.0
     Brad Pitt fit 0.691358024691358
     Mary Lynn Rajskub fit 0.0
     
-----------------------------------------------------------------------------*/