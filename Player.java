import java.util.HashSet;
import java.util.Iterator;

/**
 * This class represents the player in the game Lost Pyramid
 * 
 * @author (Candidate Number: 138123) 
 * @version (1.0 - 7th July 2015)
 */
public class Player
{
    private String name; // player name
    private HashSet bag; // array to store items held by player

    /**
     * Constructor for objects of class Player
     * 
     * @param name in case we want to store a player name in a future iteration
     */
    public Player(String name)
    {
        this.name = name;
        bag = new HashSet();
    }

    /**
     * method to return name
     * 
     * @return returns name
     */
    public String getName()
    {
        return name;
    }

    /**
     * method to set player name
     * 
     * @param is the player name
     */
    public void setName(String playerName)
    {
        if(playerName != null) // check that name exists
        {
            name = playerName;
        }
        else
        {
            System.out.println("Player name could not be added");
        }
    }
    
    /**
     * This method receives the call from the take() method in the Room class
     * and add the item picked up to the HashSet bag
     * 
     * @param Item oneToRemove is the item being added to HashSet bag
     */
    public void addItem(Item oneToRemove)
    {
        if(oneToRemove != null)
        {
            bag.add(oneToRemove);
        }
        else
        {
            System.out.println("Item could not be placed in bag");
        }
    }
    
    /**
     * this method iterates through the HashSet bag and prints out 
     * the list of items held in the player bag.
     * 
     * @return  returns a string listing the items in the players bag
     */
    public String getBagString()
    {
        String returnString = "Bag Items: ";
        for(Iterator iter = bag.iterator(); iter.hasNext(); )
            returnString += " " + ((Item) iter.next()).getDescription();
            System.out.println(returnString);
            return returnString;          
    }
}
