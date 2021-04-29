/** 
 * Profile.java provides a profile for each person, and keeps track
 *  of attributes such as their name, age, star sign, likes, dislikes,
 *  and the traits other associate with them, including methods to
 *  retrieve and edit each of these attributes
 * 
 * @author Ruchi Bhalani
 * Collaborators: None
 * Teacher Name: Ishman
 * Period: 1
 * Due Date: 05/20/2020
 */

import java.util.List;
import java.util.ArrayList;

public class Profile implements Comparable<Profile>
{
  private String name;
  private String age;
  private String sign;
  private String likes;
  private String dislikes;

  /** Constructs a Profile object, instantiating attributes like name, age, sign, likes, and dislikes of the user
   */  
  public Profile(String userName, String userAge, String userSign,
    String userLikes, String userDislikes)
  {
    name = userName;
    age = userAge;
    sign = userSign;
    likes = userLikes;
    dislikes = userDislikes;
  }
  
  /** Retrieves the profile name
   * @return the profile name
   */  
  public String getName()
  {
    return name;
  }
  
  /** Sets a new name for the profile
   * @param newName the new name to set the profile to
   */
  public void setName(String newName)
  {
    name = newName;
  }
  
  /** Retrieves the age on the user's profile
   * @return the age of the profile user
   */
  public String getAge()
  {
    return age;
  }
  
  /** Updates the age on the profile
   * @param newAge the new age to update the profile with
   */
  public void setAge(String newAge)
  {
    age = newAge;
  }
  
  /** Retrieves the sun sign associated with the user's profile
   * @return the sun sign of the profile's user
   */
  public String getSign()
  {
    return sign;
  }

  /** Updates the sun sign on the profile
   * @param newSign the new sun sign to update the profile with
   */
  public void setSign(String newSign)
  {
    sign = newSign;
  }

  /** Retrieves the likes/preferences associated with the user's profile
   * @return the likes and preferences of the profile's user
   */  
  public String getLikes()
  {
    return likes;
  }

  /** Update's the profile's likes/preferences
   * @param newLikes the new preferences to update the profile with
   */
  public void setLikes(String newLikes)
  {
    likes = newLikes;
  }
  
  /** Retrieves the dislikes associated with the user's profile
   * @return the dislikes of the profile's user
   */  
  public String getDisklikes()
  {
    return dislikes;
  }
 
   /** Update's the profile's dislikes
   * @param newDislikes the new dislikes to update the profile with
   */ 
  public void setDislikes(String newDislikes)
  {
    dislikes = newDislikes;
  }
  
  /** The format in which to display a printed profile
   * @return the formatted string representing a profile
   */  
  public String toString()
  {
    String output = "";
    output += name + " -- " + age + " -- " + sign + "\n   Likes: " +
      likes + "\n   Dislikes: " + dislikes;
    
    return output;
  }
  
  /** Determines if the objects are equal to one another based on their attributes
   * @other the other profile to compare this one with
   * @return whether or not the profiles share the same data
   */  
  public boolean equals(Profile other)
  {
    if(name.equals(other.getName()) && age.equals(other.getAge()) && sign.equals(other.getSign()) && likes.equals(other.getLikes()) && dislikes.equals(other.getDisklikes()))
      return true;
    else
      return false;
  }

	/** Compare this Student to other based on their name
	 *  @param other the other profile for comparison
	 *  @return negative integer if this < other
	 *          0 if this.equals(other)
	 *          positive integer if this > other
	 */
  public int compareTo(Profile other)
  {
    return name.compareTo(other.getName());
  }



}