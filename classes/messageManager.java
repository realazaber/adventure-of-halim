import java.util.ArrayList;
import player;

public class messageManager {

    public messageManager() {
        
    }
    
    public String _sectioner = "=================================="; //The sectioner betweeen each section.

    
    public String sectionMessage() { //Shows the divider.
        return _sectioner;
    }

    public String outputMessage(String _message) { //Just shows a normal message with the divider around it. s
        return "\n" + _sectioner + "\n" + _message + "\n" +  _sectioner + "\n";   
    }

    public String outputMessageWithStats(String _message, player Player) { //Shows a message and all of the player's stats/
        return "\n" + _sectioner + "\n" + //Shows the divider and creates a new line.
        "Player health: "+ Player.getHealth() + "\n" + //Shows the player's health.
         "Player armour: " + Player.getArmour() + "\n" + //Shows the player's armour.
         "Player base damage: " + Player.getPlayerDamage() + "\n" + //Shows the player's damage rating.
         "Pockets: " + Player.viewInventory() + "\n" + //Shows the player's inventory/pockets.
          _message + "\n" +  _sectioner + "\n"; //Shows the divider and creates a new line.  
    }

    public String outputStats(player Player) { //Just shows the player's stats.
        return "\n" + "Player health: "+ Player.getHealth() + //Shows the player's health.
               "\n" + "Player armour: " + Player.getArmour() + //Shows the player's armour.
               "\n" + "Pockets: \n" + Player.viewInventory(); //Shows the player's inventory/pockets.
    }

    public String showLocation(String _location) { //Shows the player's current location. s
    return "You are in the " + _location + "\n" + _sectioner;
    }

    public String outputWeapons(player Player) { //Shows the weapons that the player currently has.
        return "Weapons in pockets: " + Player._weapons;
    }

    public String outputWrongInput(String _message) { //Shows the user's input and a message saying that it is invalid/
        return "\n" + _sectioner + _message + " is not a valid input" + _sectioner + "\n";
    }

    public String outputDeathMessage(String _message) { //Shows how the player died.
        return "\n" + _sectioner + "\nYou died from " + _message + "\n           Game over \n" + _sectioner + "\n";
    }

    public String outputEndMessage(String _message) { //Shows the player an end message and that they finished the game
        return "\n" + _sectioner + "\n" + _message + "\n           Game over \n" + _sectioner + "\n";
    }
}

