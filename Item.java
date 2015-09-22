/**
 * This class exists in order to allow the Room class to create 
 * an item in the game 'Lost Pyramid'.
 * 
 * It constucts an Item object with a description and weight.
 * 
 * The weight will be used in subsequent versions of the game 
 * to limit how much a player can hold
 * 
 * @author (Candidate Number: 138123) 
 * @version (1.0 - 7th July 2015)
 */
public class Item
{
    private String description;
    private double weight;
    
    /**
     * Creates a new item with the given description and weight.
     */
    public Item(String description, double weight)
    {
        this.description = description;
        this.weight = weight;
    }

    /**
     * Weight will be used to determine how many items a player can carry
     * 
     * @return returns the weight of an item
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * The description is effectively the name of the item
     * 
     * @return returns the description/name of an item
     */
    public String getDescription() {
        return description;
    }
}