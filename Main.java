/** 
 * Main.java allows the testing of TraitMappings.java, and allows the user to input data and select options that allow them to modify a map of
 *  traits by adding and deleting traits from profiles, adding and deleting profiles, and seeing which profiles have the same traits, as well as
 * an overview of all the profiles and traits stored on the program.
 * 
 * @author Ruchi Bhalani
 * Collaborators: None
 */
 
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Main 
{
  public static void main(String[] args) 
  {
    //File inputFile = new File("input.txt");
    TraitMappings map = new TraitMappings();
    menuProcess(map);

    
  }

  /** Allows the menu process to run constantly until the user chooses to exit the program; takes into account user input and selections to test
   * the functionality of TraitMappings.java
   * @param map the TraitMappings object that is being used to store the profiles and traits
   */    
  public static void menuProcess(TraitMappings map)
  {
    boolean keepGoing = true;
    
    while(keepGoing)
    {
      Scanner read = new Scanner(System.in);
      
      System.out.print("\n\nWould you like to: \n1 - VIEW TRAITS FOR EACH PROFILE\n2 - VIEW PROFILES FOR EACH TRAIT\n3 - EXIT\n\n SELECTION: ");
    
      int option = 0;
      if(read.hasNextInt())
        option = read.nextInt();
      if(option == 1) //view traits for each profile
      {
        boolean keepGoing2 = true;
        while(keepGoing2)
        {
          ArrayList<Profile> allUsers = map.getAlphabetizedProfileList();
          System.out.print("\n--------ALL PROFILES AND TRAITS--------\n\n");
          System.out.print(map);
          System.out.print("\n" + (map.getAlphabetizedProfileList().size() + 1) + ": CREATE A NEW PROFILE");
          System.out.print("\n\n\nChoose a profile you would like to edit or enter '0' to exit back to the main menu.\n SELECTION: ");
          int sel = 0;
          if(read.hasNextInt())
            sel = read.nextInt();
          
          if(sel == 0) //Exit
          {
            keepGoing2 = false;
          }
          else if(sel >= 0 && sel < allUsers.size() + 1) //one of the existing profiles
          {
            Profile profileToEdit = allUsers.get(sel - 1);
            System.out.print("Would you like to: \n1 - ADD A TRAIT\n2 - DELETE A TRAIT\n3 - CLEAR ALL TRAITS\n4 - DELETE PROFILE\n\n SELECTION: ");
            int selection2 = 0;
            if(read.hasNextInt())
              selection2 = read.nextInt();
            if(selection2 == 1) //Add trait
            {
              String newTrait = "";
              System.out.print("\nAdd a trait! " + profileToEdit.getName() + " is most likely to... ");
              if(read.hasNext())
              {
                read.nextLine();
                newTrait = read.nextLine();
                map.addTrait(profileToEdit, newTrait);
                System.out.print("\nTrait successfully added!\n\n");
              }
                
            }
            else if(selection2 == 2) //delete a trait
            {
              ArrayList<String> personTraits = map.getTraitsForProfile(profileToEdit);
              System.out.print("\nMost likely to...");
              int count = 1;
              for(String s : personTraits)
              {
                System.out.print("\n     " + count + ": " + s);
                count++;
              }
              System.out.print("\nSelect one of " + profileToEdit.getName() + "'s traits to delete: ");
              int delete = 0;
              if(read.hasNextInt())
              {
                delete = read.nextInt();
                map.deleteTrait(profileToEdit, delete);
                System.out.print("\nTrait successfully deleted!\n\n");
              }
            }
            else if(selection2 == 3) //clear all traits
            {
              map.clearAllTraits(profileToEdit);
              System.out.print("\nAll traits successfully cleared!\n\n");
            }
            else if(selection2 == 4) //delete profile
            {
              map.deleteProfile(profileToEdit);
              System.out.print("\nProfile successfully deleted!\n\n");
            }
            else
            {
              System.out.print("\nInvalid choice. Please choose from one of the options below.\n");
            }
            
          }
          else if(sel == allUsers.size() + 1) //create a new profile
          {
            String name = "";
            String age = "";
            String sign = "";
            String likes = "";
            String dislikes = "";
            
            System.out.print("Create a new profile!\nName: ");
            if(read.hasNext())
            {
              read.nextLine();
              name = read.nextLine();
            }
            System.out.print("Age: ");
            if(read.hasNext())
            {
              //read.nextLine();
              age = read.nextLine();
            }
            System.out.print("Sign: ");
            if(read.hasNext())
            {
              //read.nextLine();
              sign = read.nextLine();
            }
            System.out.print("Likes (separated by commas): ");
            if(read.hasNext())
            {
              //read.nextLine();
              likes = read.nextLine();
            }
            System.out.print("Dislikes (separated by commas): ");
            if(read.hasNext())
            {
              //read.nextLine();
              dislikes = read.nextLine();
            }            
            
            Profile addPerson = new Profile(name, age, sign, likes, dislikes);
            map.addProfileToMap(addPerson);
            
            System.out.print("\nNew profile successfully created!\n\n");
            
          }
          else //Invalid
          {
            System.out.print("\nInvalid choice. Please choose from one of the options below.\n");
          }
        }
        
      }
      else if(option == 2) //view profiles for each trait
      {
        boolean keepGoing3 = true;
        while(keepGoing3)
        {
          System.out.print("\n--------ALL TRAITS--------\n\n");
          ArrayList<String> allTraits = map.getAlphabetizedTraitList();
          int num = 1;
          for(String s : allTraits)
          {
            System.out.print("\n" + num + ": " + s);
            num++;
          }
        
          System.out.print("\n\n\nChoose a trait you would like to see which profiles relate with or enter '0' to exit back to the main menu.\n SELECTION: ");
          
          int selection3 = 0;
          if(read.hasNextInt())
            selection3 = read.nextInt();
          if(selection3 == 0)
          {
            keepGoing3 = false;
          }
          else if(selection3 >= 0 && selection3 <= allTraits.size()) //selecting existing traits
          {
            String selectedTrait = allTraits.get(selection3 - 1);
            System.out.print("\n\n\nPROFILES WHO ARE MOST LIKELY TO " + selectedTrait.toUpperCase() + ": \n");
            ArrayList<Profile> mostLikelyProfileList = map.getProfilesForTrait(selection3);
            for(Profile p : mostLikelyProfileList)
            {
              System.out.print("\n" + p + "\n");
            }
            if(mostLikelyProfileList.size() == 0)
              System.out.print("No profiles match this trait.");
            
            System.out.print("\n");
          }
          else //invalid
          {
            System.out.print("\nInvalid choice. Please choose from one of the options below.\n");
          }
        }

      }
      else if(option == 3)
      {
        keepGoing = false;
        break;
      }
      else
      {
        System.out.print("\nInvalid choice. Please choose from one of the options below.\n");
      }
    }

    
  }
}