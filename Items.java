
/**
 * Write a description of class Items here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Items
{
    String name;
    String description;
    String room;
    
    //constructor.
    public Items(String name, String description, String room)
    {
        this.name = name;
        this.description = description;
        this.room = room;
    }
    
    //accessors.
    public String getDescription() 
    {
        return description;
    }
    public String getName() 
    {
        return name;
    } 
    public String getRoom() 
    {
        return room;
    }
    
    //create items.
    public void createItems ()
    {
        Items[] itemlist = new Items[] {null, null};
        Items stapler = new Items("stapler" , "Your trusty stapler that has seen you through thick and thin.", "office");
        itemlist[0] = stapler;
        Items computer = new Items("computer" , "The sunset screensaver flickers briefly with the soft intensity of the tropical" +
        " sun. Ahh, HR knew what they were doing when they gave you this computer. It brings you such peace." , "office");
        itemlist[1] = computer;
    
    }
    
    
}
