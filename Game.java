/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    March 2000
 * 
 * Modified by: April Schermann
 * Date:        October 2018
 * 
 * Modified by: Amy Feeney
 * Date:        November 2018
 * 
 *  This class is the main class of the "Zork" application. Zork is a very
 *  simple, text based adventure game.  Users can walk around some scenery.
 *  That's all. It should really be extended to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  routine.
 * 
 *  This main class creates and initializes all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates the
 *  commands that the parser returns.
 */
import java.util.*;
class Game 
{
    private Parser parser;
    private Room currentRoom;
        

    /**
     * Create the game and initialize its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room teleporterRoom, redDoor, greenDoor, blueDoor, greenDoorEntrance, blueDoorEntrance, office;
      
        // create the rooms
        office = new Room("This is your favorite place in the entire world. Your office.\nYou have spent countless hours here  "  + 
                             "watching the sunset screensaver\non your computer and marvelling at the beauty of corporate life.\n" +
                             "You're thinking of taking overtime and investing in a\nsleeping bag so you can catch up on those expense reports" );
        teleporterRoom = new Room("You are in the teleporter room");
        redDoor = new Room("You are in front of the red door");
        greenDoor = new Room("You are in front of the green door");
        blueDoor = new Room("You are in front of the blue door");
        
        greenDoorEntrance = new Room("");
        blueDoorEntrance = new Room("");
        
        //imc = new Room("the IMC");
        //office = new Room("the main office");
        
        // initialise room exits
        office.setExits(null, null, null, null);
        teleporterRoom.setExits(greenDoor, blueDoor, null, redDoor);
        redDoor.setExits(null, teleporterRoom, null, null);
        greenDoor.setExits(null, null, teleporterRoom, null);
        blueDoor.setExits(null, null, null, teleporterRoom);
        //imc.setExits(outside, office, null, null);
        //office.setExits(null, null, null, imc);

        currentRoom = office;  // game begins in teleporter room.
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Goodbye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Zork!");
        System.out.println("Zork is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.longDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        if(command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
        {
            if(command.hasSecondWord())
                System.out.println("Quit what?");
            else
                return true;  // signal that we want to quit
        }
        return false;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around Normal Community High School.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.nextRoom(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else 
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.longDescription());
        }
    }
    
     private void examineObject(Command command, ArrayList itemlist) 
    {
        
        if(!command.hasSecondWord()) {
            System.out.println("Examine what?");
            return;
        }
        
        String typed = command.getSecondWord();
        
        for (int i = 0; i < itemlist.length()-1; i++)
        {
            Items o =  itemlist[i];
            String check = o.getName();
            if (typed.equals(check))
            {
                System.out.println("You pick up the " + o.getName() + ". It " +   
                "looks like " + o.getDescription() + ".");
        
            }
        }
    
    }
   
}
