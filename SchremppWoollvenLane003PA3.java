/**
 * @(#)SchremppWoollvenLane003PA2.java
 * @author  Ryan Schrempp, Gavin Woollven-Lane
 * @version 1.00 2023/04/05  11:00 AM
 * 
 * PROGRAM PURPOSE:  To create a program for address books. 
 * Created address books can be categorized as either personal or
 * business address books. The relationship type between the address book owner
 * and the addressee can be family, friends, or work. The addressee information 
 * stored includes name, street address, city, state, and zip code, and phone number.
 */
import java.util.Scanner; //By Ryan Schrempp: ACCESSES Scanner CLASS FOR DATA INPUT.

public class SchremppWoollvenLane003PA3
{
  private static Scanner input = new Scanner(System.in); //By Ryan Schrempp: Object for keyboard input.
  private static StringBuilder phoneFormatted; //By Ryan Schrempp: Builds a string in the format of xxx-xxx-xxxx.
  private static char correct; //By Ryan Schrempp: Prompts and validates bookType if (correct != 'Y')
  private static String addressBook; //By Ryan Schrempp: Stores entries of addressees.
  private static String addressee; //By Ryan Schrempp: Stores contact name.
  private static String family = String.format("%nFAMILY%n"); //By Ryan Schrempp: Stores family entries.
  private static String friends = String.format("%nFRIENDS%n"); //By Ryan Schrempp: Stores friends entries.
  private static int bookType; //By Ryan Schrempp: Stores the type of address entry, Personal or Business.
  private static String street; //By Ryan Schrempp: Stores street info variable.
  private static String cityStateZip; //By Ryan Schrempp: Stores City, State, and ZIP.
  private static String work = ""; //By Ryan Schrempp: Stores work entries.
  private static boolean validInteger; //By Ryan Schrempp: Validates user input for bookType.
  
  /**
   * By Gavin Woollven-Lane
   * Separate address books can be created for family, friends, and work.  User is 
   * prompted for addressees and their relationship to the user who is the owner of 
   * the address book.  The entries are validated then added to the correct address 
   * book.  The address books are then printed.
   */
  public static void main(String[] args)
  { //BEGIN main
    char another = ' ';
    char anotherAddress = ' ';
//Prompt 1: By Ryan Schrempp:
//This primes the sentinel-loop control variable another before entering the while that controls the address books.
    System.out.printf("%nBegin entering addresses?  \'Y\' or \'N\':  ");
    another = input.nextLine().charAt(0);
    
    if(Character.toUpperCase(another) != 'Y')
    {
      System.out.printf("%nExiting program.%n");
    }while(Character.toUpperCase(another) == 'Y') //END if(another) for entering addresses.
    {
      setAddressBook(); //By Ryan Schrempp: Prompt 2
      setOwner(); //By Ryan Schrempp: Prompt 3
      
      do
      {
        setAddressee(); //By Ryan Schrempp: Prompt 4
        setStreet(); //By Ryan Schrempp: Prompt 5
        setCityStateZip(); //By Ryan Schrempp: Prompt 6
        setPhone(); //By Ryan Schrempp: Prompt 7
        setRelationship(); //By Ryan Schrempp: Prompt 8
        //By Ryan Schrempp: Prompt 9: This alters the sentinel-loop control variable anotherAddress
        //to either continue with another address or exit the do-while loop.
        System.out.printf("%nDo you want to enter another address?  "
                            + "\'Y\' or \'N\':  ");
        anotherAddress = input.nextLine().charAt(0); 
      }while(Character.toUpperCase(anotherAddress) == 'Y'); //END prompts 2-9 do-while.
      printAddressBook();
      //By Ryan Schrempp: Prompt 10: This alters the sentinel-loop control variable another to either continue
      //with another address book or exit the while loop.
      System.out.printf("%nDo you want to create another address book?  \'Y\' or \'N\':  ");
      another = input.nextLine().charAt(0);
    }
    System.exit(0);  //By Gavin Woollven-Lane: Exits the program.
  }//END main()
  
  
//========================================================================================  
//========================================================================================  
//BEGIN METHODS  
  /**
   * By Ryan Schrempp: setAddressBook() method
   *setAddressBook() prompts and validates the address book type. Once validated, the method will
   * print the address book type using a switch.
   */
  public static void setAddressBook()
  {
    do  //prompt for and validate bookType as long as correct is not Y
    {
      System.out.printf("%nAddress Book Type:"
                          + "%n%n1.  Personal"
                          + "%n2.  Business"
                          + "%n%nChoose from above the address book type:  ");
      
      validInteger = input.hasNextInt();
      bookType = validateInteger();
      
      input.nextLine();  //clear input buffer
      
      switch(bookType)
      {
        case 1:
          System.out.printf("%nYou entered %d for %s.  Is this correct?  "
                              + "\'Y\' or \'N\':  ", bookType, "Personal");
          correct = input.nextLine().charAt(0);
          break;
        case 2:
          System.out.printf("%nYou entered %d for %s.  Is this correct?  "
                              + "\'Y\' or \'N\':  ", bookType, "Business");
          correct = input.nextLine().charAt(0);
          break;
        default:
          System.out.printf("%nYou entered %d which is the wrong address "
                              + "book type.  Try again.%n", bookType);
          correct = 'n';
      }//END switch on bookType to print the validation prompts for bookType
    }while(Character.toUpperCase(correct) != 'Y' );  //END do-while correct NOT Y
    
    //initialize the address book to its title using a ternary
    addressBook = String.format("%n%n%S ADDRESS BOOK FOR ", 
                                bookType == 1 ? "personal" : "business");
  }//END setAddressBook():  static void
//========================================================================================  
  /**
   * By Ryan Schrempp: setOwner() method
   * setOwner() prompts for and validates the user's name to assign it to the address book.
   */
  public static void setOwner()
  {
    String ownerAddrBk = "";
    do  //prompt for and validate the name of the address book owner as long as correct is not y
    {
      System.out.printf("%nEnter your name:  ");
      ownerAddrBk = input.nextLine();
      System.out.printf("%nYou entered %s.  Is this correct?  \'Y\' or \'N\':  ", ownerAddrBk);
      correct = input.nextLine().charAt(0);
    }while(Character.toUpperCase(correct) != 'Y' );  //END do-while correct NOT Y
    
    
    //add the name of the address book owner to the address book
    addressBook += String.format("%S%n", ownerAddrBk); 
  } //END setOwner():  static void
  //========================================================================================  
  /**
   * By Ryan Schrempp: setAddressee() method
   * setAddressee() prompts and validates the name of each additional addressee added to the
   * address book.
   */
  public static void setAddressee()
  {
    do  //prompt for and validate the name of the addressee as long as correct is not 'Y'
    {
      System.out.printf("%nEnter the name of the addressee:  ");
      addressee = input.nextLine();
      System.out.printf("%nYou entered %s.  Is this correct?  "
                          + "\'Y\' or \'N\':  ", addressee);
      correct = input.nextLine().charAt(0);
    }while(Character.toUpperCase(correct) != 'Y'); //END do-while correct NOT Y
  } //END setAddressee():  static void
//========================================================================================
  /**
   * By Ryan Schrempp: setStreet() method
   * setStreet() prompts and validates the name of the street variable for addressee.
   */
  public static void setStreet()
  {
    do  //prompt for and validate the street address of the addressee as long as correct is not 'Y'
    {
      System.out.printf("%nEnter the street for %s:  ", addressee);
      street = input.nextLine();
      System.out.printf("%nYou entered %s.  Is this correct?  "
                          + "\'Y\' or \'N\':  ", street);
      correct = input.nextLine().charAt(0);
    }while(Character.toUpperCase(correct) != 'Y');  //END do-while correct NOT Y
  } //END setStreet():  static void
  //========================================================================================
  /**
   * By Ryan Schrempp: setCityStateZip() method
   * setCityStateZip() prompts and validates the CityStateZip variable for addressee.
   */
  public static void setCityStateZip()
  {
    do  //prompt for and validate the city, state, and zip of the addressee as long as correct is not 'Y'
    {
      System.out.printf("%nEnter the city, state, and zip code for %s "
                          + "in the correct format:  ", addressee);
      cityStateZip = input.nextLine();
      System.out.printf("%nYou entered %s.  Is this correct?  "
                          + "\'Y\' or \'N\':  ", cityStateZip);
      correct = input.nextLine().charAt(0);
    }while(Character.toUpperCase(correct) != 'Y'); //END do-while correct NOT Y
  } //END setCityStateZip():  static void
  //========================================================================================
  /**
   * By Gavin Woollven-Lane
   * setPhone() uses a do-while to prompt for and validate the phone number of an addressee.
   * The phone number is formatted with dashes using a StringBuilder before being printed back
   * to the user for validation.
   */ 
  public static void setPhone()
  {
    String phone = "";  //By Gavin Woollven-Lane: holds phone number of addressee
    
    //prompt for and validate the phone number for the addressee as long as correct is not 'Y'
    do 
    {
      System.out.printf("%nEnter the 10 digit phone number for %s "
                          + "without dashes or parentheses:  ", addressee);
      phone = input.nextLine();
      //use StringBuilder(phone) to format the entered phone number with dashes
      phoneFormatted = new StringBuilder(phone);  //create new StringBuilder using phone variable
      phoneFormatted.insert(3, '-');
      phoneFormatted.insert(7, '-');
      //validate entered phone number after it is formatted
      System.out.printf("%nYou entered %s.  Is this correct?  "
                          +  "\'Y\' or \'N\':  ", phoneFormatted);
      correct = input.nextLine().charAt(0);
    }while(Character.toUpperCase(correct) != 'Y'); //END do-while correct NOT Y
  }//END setPhone():  static void
//===============================================================================
  /**
   * By Gavin Woollven-Lane
   * setRelationship() uses a do-while to prompt for and validate the relationship of the addressee.
   * A boolean is read into validInteger using input.hasNextInt() and validateInteger()
   * is called to validate and return an integer to relationship. The valid integer is used
   * in a switch on relationship to validate that the chosen relationship was correct.
   * A switch on relationship is then used to addition assign the returned formatted string from 
   * formatAddressee() to family for case 1, friends for case 2, and work for case 3.
   */ 
  public static void setRelationship()
  {
    int relationship = 0; //By Gavin Woollven-Lane: holds an integer corresponding to the relationship type
    
    //validate the relationship of the addressee to the address book owner as long as correct is not 'Y'
    do 
    {
      System.out.printf("%nRelationship of the addressee:"
                          + "%n%n1.  Family"
                          + "%n2.  Friends"
                          + "%n3.  Work"
                          + "%n%nChoose from the above:  ");
      validInteger = input.hasNextInt();
      relationship = validateInteger();
      
      input.nextLine(); //clear input buffer
      
      switch(relationship)
      {
        case 1:
        case 2:
          System.out.printf("%nYou entered %d for %s.  Is this "
                              + "correct?  \'Y\' or \'N\':  ", relationship, relationship == 1 ? 
                              "Family" : "Friends");
        correct = input.nextLine().charAt(0);
        break;
        case 3:
          System.out.printf("%nYou entered %d for %s.  Is this "
                              + "correct?  \'Y\' or \'N\':  ", relationship, "Work");
          correct = input.nextLine().charAt(0);
          break;
        default:
          System.out.printf("%nYou entered %d which is incorrect.  "
                              + "Try again.%n", relationship);
          correct = 'n';
      }//END switch on relationship to validate the relationship of the addressee to the address book owner
    }while(Character.toUpperCase(correct) != 'Y'); //END do-while correct NOT Y
    //switch on relationship to add formatted addressee info by calling formatAddressee()
    switch(relationship)
    {
      case 1:
        family += formatAddressee();
        break;
      case 2:
        friends += formatAddressee();
        break;
      case 3:
        work += formatAddressee();
    }//END switch on relationship to assign addressee information to the String objects family, friends, and work.  
  }//END setRelationship():  static void
//===========================================================================
  /**
   * By Gavin Woollven-Lane 
   * formatAddressee() returns the formatted String containing addresee information including
   * Addressee, Street, City, State, Zip, and Phone
   */ 
  public static String formatAddressee()
  {
    return String.format("%nAddressee:  %s"
                           + "%nStreet:  %s"
                           + "%nCity, State  Zip:  %s"
                           + "%nPhone:  %s%n", addressee, street, cityStateZip,
                         phoneFormatted);
  }//END formatAddressee():  static String
//==========================================================================
  /**
   * By Gavin Woollven-Lane
   * printAddressBook() uses a switch on bookType to print the address books. 
   * If bookType == 1, the personal address book is printed. If bookType == 2,
   * the business address book is printed.
   */ 
  public static void printAddressBook()
  {
    switch(bookType)
    {
      case 1:
        System.out.printf("%s"
                            + "%s"
                            + "%s", addressBook, family, friends);
        break;
      case 2:
        System.out.printf("%s"
                            + "%s", addressBook, work);
    }//END switch on bookType to print address book information for personal or business address book
  }//END printAddressBook():  static void
//====================================================================================
  /**
   * By Gavin Woollven-Lane
   * validateInteger() uses a while loop to test the condition !validInteger so that the user
   * will keep being prompted to enter an integer until a valid integer is read in, causing the 
   * boolean variable validInteger to hold the boolean value "true." The valid integer is returned.
   */ 
  public static int validateInteger()
  {
    //prompts the user for an integer until a valid integer is read in, making validInteger == true
    while(!validInteger)
    {
      input.nextLine(); //clear the input buffer
      System.out.printf("%nNot an integer!  Enter a valid integer:  ");
      validInteger = input.hasNextInt();
    }//END while(!validInteger)
    return input.nextInt();
  }//END validateInteger():  static int
//========================================================================================
}//END APPLICATION CLASS SchremppWoollvenLane003PA2

/*

Begin entering addresses?  'Y' or 'N':  n
 
Exiting program. 
 
Begin entering addresses?  'Y' or 'N':  y
 
Address Book Type: 
 
1.  Personal 
2.  Business 
 
Choose from above the address book type:  3
 
You entered 3 which is the wrong address book type.  Try again. 
 
Address Book Type: 
 
1.  Personal 
2.  Business 
 
Choose from above the address book type:  l
 
Not an integer!  Enter a valid integer:  1
 
You entered 1 for Personal.  Is this correct?  'Y' or 'N':  y
 
Enter your name:  James Kork
 
You entered James Kork.  Is this correct?  'Y' or 'N':  n
 
Enter your name:  James Kirk
 
You entered James Kirk.  Is this correct?  'Y' or 'N':  y
 
Enter the name of the addressee:  Jorge Kirk
 
You entered Jorge Kirk.  Is this correct?  'Y' or 'N':  n
 
Enter the name of the addressee:  George Kirk
 
You entered George Kirk.  Is this correct?  'Y' or 'N':  y
 
Enter the street for George Kirk:  123 Main
 
You entered 123 Main.  Is this correct?  'Y' or 'N':  n
 
Enter the street for George Kirk:  718 Enterprise Cir
 
You entered 718 Enterprise Cir.  Is this correct?  'Y' or 'N':  y
 
Enter the city, state, and zip code for George Kirk in the correct format:  Riverdale,  CA
 
You entered Riverdale,  CA.  Is this correct?  'Y' or 'N':  n
 
Enter the city, state, and zip code for George Kirk in the correct format:  Riverside, IA  52327
 
You entered Riverside, IA  52327.  Is this correct?  'Y' or 'N':  y
 
Enter the 10 digit phone number for George Kirk without dashes or parentheses:  7197890123
 
You entered 719-789-0123.  Is this correct?  'Y' or 'N':  n
 
Enter the 10 digit phone number for George Kirk without dashes or parentheses:  3197890123
 
You entered 319-789-0123.  Is this correct?  'Y' or 'N':  y
 
Relationship of the addressee: 
 
1.  Family 
2.  Friends 
3.  Work 
 
Choose from the above:  4
 
You entered 4 which is incorrect.  Try again. 
 
Relationship of the addressee: 
 
1.  Family 
2.  Friends 
3.  Work 
 
Choose from the above:  2
 
You entered 2 for Friends.  Is this correct?  'Y' or 'N':  n
 
Relationship of the addressee: 
 
1.  Family 
2.  Friends 
3.  Work 
 
Choose from the above:  1
 
You entered 1 for Family.  Is this correct?  'Y' or 'N':  y
 
Do you want to enter another address?  'Y' or 'N':  y
 
Enter the name of the addressee:  Spock
 
You entered Spock.  Is this correct?  'Y' or 'N':  y
 
Enter the street for Spock:  15 Triple Moon
 
You entered 15 Triple Moon.  Is this correct?  'Y' or 'N':  y
 
Enter the city, state, and zip code for Spock in the correct format:  Surak, Vulcan
 
You entered Surak, Vulcan.  Is this correct?  'Y' or 'N':  y
 
Enter the 10 digit phone number for Spock without dashes or parentheses:  4151234567
 
You entered 415-123-4567.  Is this correct?  'Y' or 'N':  y
 
Relationship of the addressee: 
 
1.  Family 
2.  Friends 
3.  Work 
 
Choose from the above:  2
 
You entered 2 for Friends.  Is this correct?  'Y' or 'N':  y
 
Do you want to enter another address?  'Y' or 'N':  n
 
 
PERSONAL ADDRESS BOOK FOR JAMES KIRK 
 
FAMILY 
 
Addressee:  George Kirk 
Street:  718 Enterprise Cir 
City, State  Zip:  Riverside, IA  52327 
Phone:  319-789-0123 
 
FRIENDS 
 
Addressee:  Spock 
Street:  15 Triple Moon 
City, State  Zip:  Surak, Vulcan 
Phone:  415-123-4567 
 
Do you want to create another address book?  'Y' or 'N':  y
 
Address Book Type: 
 
1.  Personal 
2.  Business 
 
Choose from above the address book type:  2
 
You entered 2 for Business.  Is this correct?  'Y' or 'N':  y
 
Enter your name:  James Kirk
 
You entered James Kirk.  Is this correct?  'Y' or 'N':  y
 
Enter the name of the addressee:  Star Fleet Command
 
You entered Star Fleet Command.  Is this correct?  'Y' or 'N':  y
 
Enter the street for Star Fleet Command:  1 Star Fleet
 
You entered 1 Star Fleet.  Is this correct?  'Y' or 'N':  y
 
Enter the city, state, and zip code for Star Fleet Command in the correct format:  Fort Baker, CA  98210
 
You entered Fort Baker, CA  98210.  Is this correct?  'Y' or 'N':  y
 
Enter the 10 digit phone number for Star Fleet Command without dashes or parentheses:  4158921519
 
You entered 415-892-1519.  Is this correct?  'Y' or 'N':  y
 
Relationship of the addressee: 
 
1.  Family 
2.  Friends 
3.  Work 
 
Choose from the above:  3
 
You entered 3 for Work.  Is this correct?  'Y' or 'N':  y
 
Do you want to enter another address?  'Y' or 'N':  y
 
Enter the name of the addressee:  Lt. Commander Montgomery Scott
 
You entered Lt. Commander Montgomery Scott.  Is this correct?  'Y' or 'N':  y
 
Enter the street for Lt. Commander Montgomery Scott:  5 Star Fleet #62
 
You entered 5 Star Fleet #62.  Is this correct?  'Y' or 'N':  y
 
Enter the city, state, and zip code for Lt. Commander Montgomery Scott in the correct format:  Fort Baker, CA  98210
 
You entered Fort Baker, CA  98210.  Is this correct?  'Y' or 'N':  y
 
Enter the 10 digit phone number for Lt. Commander Montgomery Scott without dashes or parentheses:  4152345678
 
You entered 415-234-5678.  Is this correct?  'Y' or 'N':  y
 
Relationship of the addressee: 
 
1.  Family 
2.  Friends 
3.  Work 
 
Choose from the above:  3
 
You entered 3 for Work.  Is this correct?  'Y' or 'N':  y
 
Do you want to enter another address?  'Y' or 'N':  n
 
 
BUSINESS ADDRESS BOOK FOR JAMES KIRK 
 
Addressee:  Star Fleet Command 
Street:  1 Star Fleet 
City, State  Zip:  Fort Baker, CA  98210 
Phone:  415-892-1519 
 
Addressee:  Lt. Commander Montgomery Scott 
Street:  5 Star Fleet #62 
City, State  Zip:  Fort Baker, CA  98210 
Phone:  415-234-5678 
 
Do you want to create another address book?  'Y' or 'N':  n

*/