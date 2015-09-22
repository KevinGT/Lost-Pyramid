import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;

/**
 * Class Room -  This class creates the Rooms where the player and items can exist within the
 * game 'Lost Pyramid'
 *
 * A "Room" represents one location in the scenery of the game -  It is 
 * connected to other rooms via exits
 * 
 * The Room class stores a HashMap of exits, with Rooms as a key value pairing within the HashMap.
 * This HashMap exits allowed for loose coupling between the Game and Room classes - It also
 * allows for easier instantiation of Room objects and we can change the Rooms and their
 * exits through the createRoom() method in the game class
 * 
 * @author (Candidate Number: 138123) 
 * @version (1.0 - 7th July 2015)
 */

public class Room 
{
    private String description;                 // stores description of the room
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashSet items;                      // array of items instantiated from createRoom()

    /**
     * This is the constructor for the Room class - Instantiated with a description,
     * a Room can be easily added with exits in the createRoom() method
     * 
     * @param description The room's description
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashSet();
    }

    /**
     * setExit() allows us to create a room with exits. by setting the neighbour we can
     * link rooms together
     * 
     * Define an exit from this room to its neighbour
     * 
     * @param direction  The direction of the exit
     * @param neighbor  The room to which the exit leads
     */
    public void setExit(String direction, Room neighbor) 
    {
        // error prevention through if statement
        if(direction != null && neighbor != null)
        {
            exits.put(direction, neighbor);
        }
        else
        {
            System.out.println("Unable to create room");
        }
    }

    /**
     * This is a short description of the room
     * 
     * @return The short description of the room
     * (the one that was defined in the constructor)
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     Items: beer sword
     *     
     * This is displayed upon entering a new room and when the 'look' command is called
     *     
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getItemString();
    }

    /**
     * Returns a string containing the items located within the room
     * 
     * @return This returns items located in the currentRoom
     */
    private String getItemString()
    {
        // Iterate through HashSet items and return a list of items contained in the room
        String returnString = "Items: ";
        for(Iterator iter = items.iterator(); iter.hasNext(); )
            returnString += " " + ((Item) iter.next()).getDescription(); // this returns the name
            return returnString;
    }
    
    /**
     * Return a string listing all the room's exits, for example
     * "Exits: north west"
     * 
     * @return Details of the room's exits
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        // use keySet() method to loop through keys and return the list of exits
        Set<String> keys = exits.keySet(); 
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Called by the goRoom() method in the Game class this method looks
     * into the HashMap exits collection and gets the exits
     * 
     * If there is no room in that direction, return null
     * 
     * @param direction The exit's direction
     * @return The room in the given direction
     */
   public Room getExit(String direction) 
   {
       return exits.get(direction);
   }
    
    /**
     * Places an Item in the Room and into the items HashSet 
     * 
     * @param item is the item
     */
    public void addItem(Item item)
    { 
    if(item != null) // add in error prevention
        {
            items.add(item);
        }
        else
        {
            System.out.println("Unable to create item"); // message if room fails to instantiate
        }
    }

    /**
    * Take the item from the room and add it to the Player bag
    * 
    * @param Command command is the a string containing both command words
    * @param Player p is the player object where we store objects from the HashSet items
    */
    public void take(Command command, Player p)
    {
        
    if(!command.hasSecondWord()) {
        // if there is no second word, we don't know what to take...
        System.out.println("Take what?");
        return;
        }
    
    String item = command.getSecondWord();
    // For loop to iterate through HashSet items
    for (Iterator iter = items.iterator(); iter.hasNext();) {
     Item oneToRemove = (Item) iter.next();
    
     if(oneToRemove.getDescription().contains(item))
     {
        p.addItem(oneToRemove); // call to add item to player backpack in Player class
        iter.remove(); // removes item from the array
        System.out.println("You have taken the " + item);
     }
     else
     {
        System.out.println("Failed to take the item");
     }
    }

    /* SECOND ATTEMPT ****************
    Iterator iter = items.iterator();
    while (iter.hasNext()) {
        Item oneToRemove = (Item) iter.next();
        if(oneToRemove.getDescription().contains(item)){
            items.remove(oneToRemove);
        }
    }

    /* FIRST ATTEMPT ****************
    String item = command.getSecondWord();
    Item oneToRemove;
      
    //Iterating over HashSet using Iterator in Java
    Iterator iter = items.iterator();
    while(iter.hasNext()){
        oneToRemove = ((Item) iter.next());
        if(oneToRemove.getDescription().contains(item)){
            items.remove(oneToRemove);
        }
    }
    
    for(Iterator iter = items.iterator(); iter.hasNext(); )
    oneToRemove = ((Item) iter.next());

    if(oneToRemove.getDescription() == item){
    items.remove(oneToRemove);
    }
    else 
    {
    System.out.println("There is no item to take");
    }

    //System.out.println(item);
    //System.out.println(oneToRemove);
    //System.out.println(oneToRemove.description);
    //System.out.println(getItemString());
    */
   }
}

