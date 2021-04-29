/** 
 * TraitMappings.java simulates a social media program in which profiles of individuals are mapped to a collection of the traits that they are
 *  "most likely to" embody (in the spirit of high school superlatives). It uses a TreeMap in order to map individuals to their collection of traits,
 * and includes methods to populate the map based on an input file, add and delete traits of a person, create and delete profiles, find all the profiles
 * that embody an individual trait, and all the traits that apply to a single profile, and retrieve alphabetized lists of all the profiles, and of all
 * the traits
 * @author Ruchi Bhalani
 * Collaborators: None
 * Teacher Name: Ishman
 * Period: 1
 * Due Date: 05/20/20
 */
 
import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;

public class TraitMappings 
{
  
  private TreeMap<Profile, TreeSet<String>> traitMap;
  private ArrayList<Profile> users;
  private ArrayList<String> traits;
  
  /** Constructs a TraitMappings object, instantiating the map that serves as the basis of the
   * profile and trait mappings
   */
  public TraitMappings()
  {
    traitMap = new TreeMap<>();
    users = new ArrayList<>();
    traits = new ArrayList<>();
    try
    {
      File inputFile = new File("input.txt");
      populateTraitMap(inputFile);
    }
    catch(FileNotFoundException f)
    {
      System.out.println("File not found.");
    }
    
    
  }
  
  /** Populates the trait map based on data from an input file, reading in information and creating profiles based on it
   * @param inputFile the file to read the input data from and populate the map with
   * @throws FileNotFoundException if the input file does not exist or cannot be found
   */  
  public void populateTraitMap(File inputFile) throws FileNotFoundException
  {
    if(inputFile.exists())
    {
      Scanner read = new Scanner(inputFile);
      String name = "";
      String age = "";
      String sign = "";
      String likes = "";
      String dislikes = "";
      Profile p = new Profile(name, age, sign, likes, dislikes);
      TreeSet<String> userTraits = new TreeSet<>();
      
      while(read.hasNextLine())
      {
        if(read.hasNextLine())
        {
          name = read.nextLine();
        }
        if(read.hasNextInt())
        {
          age = read.nextInt() + "";
          read.nextLine();
        }
        if(read.hasNextLine())
        {
          sign = read.nextLine();
        }
        if(read.hasNextLine())
        {
          likes = read.nextLine();
        }
        if(read.hasNextLine())
        {
          dislikes = read.nextLine();
        }
          
        
        p = new Profile(name, age, sign, likes, dislikes);
        users.add(p);
        userTraits = new TreeSet<>();
        
        if(read.hasNextInt())
        {
          int numTraits = read.nextInt();
          read.nextLine();
          
          for(int i = 0; i < numTraits; i++)
          {
            if(read.hasNextLine())
            {
              String trait = read.nextLine();
              boolean has = false;
              for(String s : traits)
              {
                if(s.equals(trait))
                  has = true;
              }
              if(!has)
                traits.add(trait);
              userTraits.add(trait);
            }
          }
          
          traitMap.put( p, userTraits);
        }
      }      
    }
    else
    {
      System.out.println("File is nonesistent.");
    }
    
  }
  
  /** Allows adding of a new trait to a single given profile in the map
   * @param person the profile to add the new trait to
   * @param newTrait the new trait to add to the given profile
   */  
  public void addTrait(Profile person, String newTrait)
  {
    
    TreeSet<String> traitSet = traitMap.get(person);
    traitSet.add(newTrait);
    boolean has = false;
    for(String s : traits)
    {
      if(s.equals(newTrait))
        has = true;
    }
    if(!has)
      traits.add(newTrait);
    
    traitMap.put(person, traitSet);
    
    //add the trait to their "profile", and to the mapping
    //if it is their first trait, add them to the profile list
    //if this is a new trait, add it to the list
  }

  /** Deletes the given trait from a specific profile
   * @param person the profile from which to delete the trait from
   * @param number the number of the trait selected by the user for deletion
   * @return whether or not something was deleted from the map
   */
  public boolean deleteTrait(Profile person, int number)
  {
    boolean result = false;
 
    int num = 1;
    TreeSet<String> traitSet = traitMap.get(person);
    String traitToDelete = "";
    Iterator iter = traitSet.iterator();
    while(iter.hasNext())
    {
      String read = (String)iter.next();
      if(number == num)
      {
        traitToDelete = read;
        iter.remove();
        result = true;
        break;
      }
      num++;
    }

    if(!traitMap.containsValue(traitToDelete))
      traits.remove(traitToDelete);
    
    traitMap.put(person, traitSet);
    
    return result;
    
    //delete the trait from the "profile" and from the mapping and from the list (if no one else is using it)
  }
 
   /** Clears all the traits of a given Profile
    * @param person the given profile to clear all traits of
   */ 
  public void clearAllTraits(Profile person)
  {

    TreeSet<String> traitSet = traitMap.get(person);
    
    for(String s : traitSet)
    {
      if(!traitMap.containsValue(s))
        traits.remove(s);
    }
    
    traitMap.put(person, new TreeSet<String>());
    //clear all traits from the "profile" and from the mapping and from the list
  }

  /** Deletes the given profile from the map
   * @param person the profile to delete from the trait map
   */  
  public void deleteProfile(Profile person)
  {
    TreeSet<String> traitSet = traitMap.get(person);
    traitMap.remove(person);
    
    for(String s : traitSet)
    {
      if(!traitMap.containsValue(s))
        traits.remove(s);
    }
    
    users.remove(person);
    //remove this person from the map and from the profile list
  }
  
  /** Adds the given profile to the map
   * @param person the profile to add to the trait map
   */
  public void addProfileToMap(Profile person)
  {
    users.add(person);
    traitMap.put(person, new TreeSet<String>());
  }
  
  /** Retrieves a list of profiles that contain a given trait in the trait collection they map to
   * @param number the number of the trait to search the profiles for
   * @return a list of the profiles that contain the given trait
   */
  public ArrayList<Profile> getProfilesForTrait(int number)
  {
    String traitToSearch = traits.get(number - 1);
    
    ArrayList<Profile> profileList = new ArrayList<>();
    
    for(Profile p : traitMap.keySet())
    {
      TreeSet<String> traitSet = traitMap.get(p);
      if(traitSet.contains(traitToSearch))
        profileList.add(p);
    }
    
    Collections.sort(profileList);
    
    return profileList;
  }

  /** Retrieves the list of traits for a single given Profile
   * @param person the given profile to retrieve the traits of
   * @return a list of the traits for the given profile
   */  
  public ArrayList<String> getTraitsForProfile(Profile person)
  {
    ArrayList<String> allTraitsForProfile = new ArrayList<>();
    for(String s : traitMap.get(person))
      allTraitsForProfile.add(s);
      
    Collections.sort(allTraitsForProfile);
    
    return allTraitsForProfile;
  }
  
  /** Sorts the list of all users in order to retrieve an alphabetized profile list
   * @return a list of all user profiles, organized alphabetically
   */
  public ArrayList<Profile> getAlphabetizedProfileList()
  {
    ArrayList<Profile> organized = new ArrayList<>(users);
    Collections.sort(organized);
    return organized;
  }
  
  /** Sorts the list of all traits in order to retrieve an alphabetized trait list
   * @return a list of all traits, organized alphabetically
   */  
  public ArrayList<String> getAlphabetizedTraitList()
  {
    ArrayList<String> organized = new ArrayList<>(traits);
    Collections.sort(organized);
    return organized;
  }
  
  /** Formats the trait map into a string so that it can be printed and viewed
   * @return the formatted string that organizes the data in the trait map
   */  
  public String toString()
  {
    String output = "";
    int num = 1;
    for(Profile p : traitMap.keySet())
    {
      output += num + ": " + p;
      num++;
      TreeSet<String> traitList = traitMap.get(p);
      if(getTraitsForProfile(p).size() == 0)
        output += "\n\n   No traits for this profile.";
      else
      {
        output += "\n\n   Most likely to...";
        for(String s : traitList)
          output += "\n         " + s;
      }
      
      output += "\n\n";
    }
    
    return output;
  }
  
  



}