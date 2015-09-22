/**
 *  The Game class is the main class of the 'Lost Pyramid" game
 *  
 *  It is a text based adventure game where a player will roam the halls of an 
 *  ancient pyramid in search of the Treasure. It is intended that a player will 
 *  need to pick up various objects as they venture around the maze
 *  in order to open doors and gain extra moves as they find water & food.
 *  
 *  Time depending a monster mummy might be implemented guarding the final room.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns
 * 
 * @author (Candidate Number: 138123) 
 * @version (1.0 - 7th July 2015)
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private GameGUI gui;
        
    /**
     * Create the game and initialise its internal map.
     * 
     * createRooms() initialises the Rooms
     * parser is the object that handles commands from the player
     * player is the object that handles Item objects stored in the players backpack
     * 
     */
    public Game() 
    {
        createRooms(); // calls the createRoom() method below
        parser = new Parser(); // object to monitor commands
        player = new Player("Player1"); //giving a player name at this time
        gui = new GameGUI(); // create the GUI
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {   
        Room desert, entrance, catacombs, chamberOfSand, tomb, antiChamber, treasureRoom, 
        chamberOfJewels, sphinx;
      
        // create rooms for Lost Pyramid
        desert = new Room("lost in the desert");
        entrance = new Room("by an entrance to a small pyramid");
        catacombs = new Room("in the Catacombs");
        chamberOfSand = new Room("in the Chamber of Sand. Not suprisingly, everything is covered in sand");
        tomb = new Room("in a tomb, before you stands a large sarcophagus, there is no way past");
        antiChamber = new Room("in an Anti-Chamber, the air is heavy and lighting poor, \nyou can faintly see steps leading down");
        treasureRoom = new Room("in the Treasure room at last! Your searching is over");
        chamberOfJewels = new Room("in the Chamber of Jewels. The room glistens, \nbut all the jewels are out of reach! 8-(");
        sphinx = new Room("the Sphinx room, guess what? before you stands a large Sphinx.\nIt fills the whole room. There is no way past");
        
        //add items to the room
        chamberOfSand.addItem(new Item("energy-drink", 0.5));
        
        tomb.addItem(new Item("teleporter", 1));
        
        sphinx.addItem(new Item("key", 0.25));
        
        // initialise room exits and neighbor
        desert.setExit("south", entrance);
        
        entrance.setExit("south", catacombs);
        entrance.setExit("north", desert);
        
        catacombs.setExit("north", entrance);
        catacombs.setExit("south", antiChamber);
        catacombs.setExit("east", chamberOfSand);
        catacombs.setExit("west", chamberOfJewels);
        
        chamberOfSand.setExit("west", catacombs);
        chamberOfSand.setExit("down", tomb);
        
        tomb.setExit("up", chamberOfSand);
        
        chamberOfJewels.setExit("east", catacombs);
        chamberOfJewels.setExit("south", sphinx);
        
        sphinx.setExit("north", chamberOfJewels);
        
        antiChamber.setExit("north", catacombs);
        antiChamber.setExit("down", treasureRoom);
        
        treasureRoom.setExit("up", antiChamber);
        
        currentRoom = desert;  // start game in room desert
    }

    /**
     *  Main play routine.  Loops until end of play.
     *  
     *  this method loops until the quit command is called and changes the boolean finished
     *  to true to stop the loop.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player
     * 
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Lost Pyramid");
        System.out.println("You are in a desert searching for hidden treasures.");
        System.out.println("Find your way through the traps and maze to find your reward!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case TAKE:
                currentRoom.take(command, player);
                break;
                
            case LOOK: // look command reports the surroundings to the player
                look();
                break;
                
            case INVENTORY: // will report back on which items a player is carrying
                player.getBagString();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param Command command contains the command message
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction); 

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom; // change currentRoom to the nextRoom
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * method to look around the room and describe surroundings
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
}
