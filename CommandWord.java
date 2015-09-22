/**
 * Representations for all the valid command words for the game "Lost Pyramid"
 * along with a string in a particular language(English).
 * 
 * @author (Candidate Number: 138123) 
 * @version (1.0 - 7th July 2015)
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), TAKE("take"), LOOK("look"), INVENTORY("inventory");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command word.
     * @param commandWord The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
