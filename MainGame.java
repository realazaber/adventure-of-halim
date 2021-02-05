//The Adventure of Halim

//STORY DESIGNERS:
//Joe Jabbour
//Peter Boundouris

//LEAD PROGRAMMER:
//Alexander Zaborski

import java.util.Scanner;//For user input.
import java.util.Random;//To generate random numbers.
import java.lang.System;//So I can use System.exit(0) to stop the program when you win or die.
import classes.*; //Imports all classes needed.

public class MainGame {

    public static Scanner _MainScanner = new Scanner(System.in); //This gets the input. 
    public static player Halim = new player(80, 0); //Creates the player.
    public static enemy Rat = new enemy("Rat", 100, 20, 20, 2); //Creates the rat.
    public static enemy Spanodoupoulos = new enemy("Spanodoupoulos", 100, 0, 10, 2); //Create the Greek worker/ Spanodoupoulos.
    public static messageManager Messages = new messageManager(); //Creates instance of class that has all different format of messages.
    public static boolean _gameStarted = false; //Checks if the game started.  
    public static Random Randomizer = new Random(); //Main Randomizer.

    public static void main(String[] args) {
        while (_gameStarted == false) {
            // Main title
            System.out.println(Messages.outputMessage("    Welcome to the adventure of \n    Halim Muswashharoom!\n    Remember to wash hands."));
            System.out.println("To start the game type Play or Guide \nto see how to play the game.");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            String _userInput = _MainScanner.next(); //Gets user input
            if (_userInput.equals("Play")) { //Starts the game
                Freezer();
            }
            else if (_userInput.equals("Guide")) { //Shows the guide (basic instructions) for the game
                System.out.println(Messages.sectionMessage());
                System.out.println("Throughout the game you will be\ngiven options. Like this.");
                System.out.println("|| aa. Say hello ||");
                System.out.println("|| bb. Say who are you? ||");
                System.out.println("|| cc. Hide ||");
                System.out.println("|| dd. Quit ||");
                System.out.println(Messages.sectionMessage());
                System.out.println("You will be given minimum of 2 and up to 4 choices.");
                System.out.println("To pick an option, simply press the corresponding letter on your keyboard then press enter.");
                System.out.println("You will also see what is in your inventory/pockets. In order to use an item press the");
                System.out.println("corresponding number key on your keyboard.");
                System.out.println("Type Exit to quit the game.");
                System.out.println(Messages.sectionMessage());
                System.out.println("Type Play to start the game.");
                String _changeRoom = _MainScanner.next(); //Gets user input
                if (_changeRoom.equals("Play")) { //Starts the game
                    Freezer();
                }   
            }

            else if (_userInput.equals("Exit")) { //Quits program
                System.out.println("You have quit the game"); //Shows the user that they quit the game
                System.exit(0); //Quits the game
            }
            else { //Checks if the user made an invalid input
                System.out.println(Messages.outputWrongInput(_userInput));
            }
        }
    }

    public static void Options(String _option1, String _option2, String _option3, String _option4) { //Method for showing options
        
        //Checks if certain options are blank so the amount of options can change.
        if (!_option1.equals("")) {
            System.out.println("|| aa. " + _option1 + " ||");
            
            if (!_option2.equals("")) {
                System.out.println("|| bb. " + _option2 + " ||");

                if (!_option3.equals("")) {
                    System.out.println("|| cc. " + _option3 + " ||");
                    
                    if (!_option4.equals("")) {
                        System.out.println("|| dd. " + _option4 + " ||");
                        
                    }
                }
            }
        }
    }

    public static void Freezer() {
        if (Halim.wasInRoom("Freezer")) { //Checks if the player was already in the freezer so the corresponding message can be displayed.
            System.out.println(Messages.outputStats(Halim)); //Shows player stats
            System.out.println(Messages.showLocation("Freezer")); //Shows the user where they are.
        }
        else {
            System.out.println(Messages.outputMessageWithStats("You wake up and feel very cold. \n It seems that you are in a freezer.", Halim)); //Shows main message and the player's stats
        }         
                
        Options("Check your surroundings", "Give up", "Try to open freezer door.", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        
        switch (_userOption) { //Process input
            case "aa":
                System.out.println(Messages.outputMessageWithStats("You see a body lying on the floor. It appears he has been stabbed with a kebab stick", Halim)); //Shows main message and the player's stats
                Halim.addRoom("Freezer"); //Keeps record that the player was already in the frezzer.
                FreezerBody(); //Goes to the body in the freezer
                break;
            case "bb":
                System.out.println(Messages.outputDeathMessage("freezing to death.")); //Shows how the user died and ends the game.
                System.exit(0); //Quits the game
                break;
            case "cc":
                Halim.addRoom("Freezer"); //Keeps record that the player was already in the frezzer.
                FreezerDoor(); //Goes to the freezer door
                break;

            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                Freezer();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                Freezer();
                break;
        }
    }

    public static void FreezerBody() {

        System.out.println(Messages.showLocation("Freezer")); //Shows the user where they are.
        System.out.println(Messages.outputMessageWithStats("You are looking at the body.", Halim)); //Shows main message and the player's stats
        String _option3 = "";
        
        //Checks if the user has kebab stick, if they do have it don't show the option to get it again (so they can't get it twice).
        if (Halim.hasItem("Kebab Stick") == false) { 
            _option3 = "Pull out the kebab stick";
        }
        else if (Halim.hasItem("Kebab Stick") == true) {
            _option3 = "";
        }
        ///////////////////////////////////////////////////

        Options("Check their pockets.", "Look around the body for dropped items.", _option3, ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        
        switch (_userOption) { //Process input
            //If the player didn't search the pockets yet they will 
            //pick up Lint and String, however if they did search 
            //they will get a message saying that the pockets are
            //empty
            case "aa": 
                if (Halim.hasItem("Lint and String")) {
                    System.out.println(Messages.outputMessageWithStats("His pockets are empty.", Halim)); //Shows main message and the player's stats
                }
                else {
                    Halim.addItem("Lint and String"); //armour
                    System.out.println(Messages.outputMessageWithStats("You find some lint and a piece of string.", Halim)); //Shows main message and the player's stats
                }                
                ChooseDestination();
                break;
            ////////////////////////////////////////////////////////////
            //Checks if you already have a frozen kebab, if you do you
            //won't be able to pickup another one.
            case "bb":
                if (Halim.hasItem("Frozen Kebab")) {
                    System.out.println(Messages.outputMessageWithStats("There is nothing around the body.", Halim)); //Shows main message and the player's stats
                }
                else {
                    Halim.addItem("Frozen Kebab");
                    System.out.println(Messages.outputMessageWithStats("You find a frozen kebab on the\n floor, definetely looks inedible but can be\n used as a weapon. You pick \n it up.", Halim)); //Shows main message and the player's stats
                    Halim.viewInventory();
                }
                ChooseDestination();
                break;
            ////////////////////////////////////////////////////////////
            //Checks if the user already has a kebab stick, if they do
            //they will not be able to pick it up again.
            case "cc":
                if (Halim.hasItem("Kebab Stick") == false) {
                    System.out.println(Halim.addItem("Kebab Stick"));
                    System.out.println(Messages.outputMessageWithStats("You store the kebab stick for later", Halim)); //Shows main message and the player's stats
                    System.out.println(Halim.viewInventory());
                }
                else {
                    System.out.println(Messages.outputMessageWithStats("Wait, there is no kebab stick, you \n are starting to hallucinate.", Halim)); //Shows main message and the player's stats
                }
                ChooseDestination();
                break;
            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                FreezerBody();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                FreezerBody();
                break;
        }
    }

    public static void ChooseDestination() {
        Options("Try to open freezer door.", "Keep searching the freezer.", "Keep searching the body", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        
        switch (_userOption) { //Process input
            case "aa":
                FreezerDoor(); //Go to the freezer door.
                break;
            case "bb":
                Freezer(); //Go back to the freezer.
                break;
            case "cc":
                FreezerBody(); //Search the body in the freezer again.
                break;
            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                ChooseDestination();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                ChooseDestination();
                break;
        }
    }

    public static void FreezerDoor() {

        //Checks if the user has the kebab stick item. If they do they can lock pick the door.
        if (Halim.hasItem("Kebab Stick")) { 
            System.out.println(Messages.outputMessageWithStats("You examine the lock and see that your kebab stick would fit perfectly", Halim)); //Shows main message and the player's stats
            Options("Pick lock the door.", "Examine the lock.", "Search Freezer", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            
            switch (_userOption) { //Process input
                case "aa":
                    System.out.println(Messages.outputMessageWithStats("You put the kebab stick in the freezer door and manage to unlock it!", Halim)); //Shows main message and the player's stats
                    FreezerDoorUnlocked(); //Leave the freezer.
                    break;
                case "bb":
                    FreezerDoor(); //Keep looking at the lock.
                    break;
                case "cc":
                    Freezer(); //Go back to search the freezer.
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it,
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    FreezerDoor();
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    FreezerDoor();
                    break;
            }
            
        }
        else {
            System.out.println(Messages.outputMessageWithStats("You try to open the door but are unable to.", Halim)); //Shows main message and the player's stats
            Options("Keep searching the freezer.", "Smash your way through the door", "", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input  
            
            switch (_userOption) { //Process input
                case "aa":
                    Freezer(); //Go back to search the freezer.
                    break;
                case "bb":
                    System.out.println(Messages.outputDeathMessage("ramming the door with your head and got knocked out.")); //Shows how the user died and ends the game.
                    System.exit(0); //Quits the game
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it,
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    FreezerDoor();
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    FreezerDoor();
                    break;
            }
        }
    }

    public static void FreezerDoorUnlocked() {
        Options("Leave the freezer.", "Keep on searching the freezer.", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                System.out.println(Messages.outputMessageWithStats("You put the kebab stick in the freezer door and manage to unlock it!", Halim)); //Shows main message and the player's stats
                EscapedFreezer(); //Leave the freezer.
                break;
            case "bb":
                FreezerDoor(); //Go back to the freezer door.
                break;
            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                FreezerDoorUnlocked();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                FreezerDoorUnlocked();
                break;
        }
    }

    public static void EscapedFreezer() {
        System.out.println(Messages.outputMessageWithStats("You out of the freezer walk into the main area.\nYou hear noises coming from around the corner and feel weak.", Halim)); //Shows main message and the player's stats
        Options("Hide in the nearest bin.", "Walk Around the corner.", "Escape the store", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                System.out.println(Messages.outputMessageWithStats("You enter the bin but there are rotten\n kebabs that smell so bad you jump out.", Halim)); //Shows main message and the player's stats
                EnterKitchen(); //Go to the kitchen
                break;
            case "bb":
                EnterKitchen(); //Go to the kitchen
                break;
            case "cc":
                System.out.println(Messages.outputEndMessage("You escape the store without the secret formula and your boss fires you."));
                break;
            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                EscapedFreezer();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                EscapedFreezer();
                break;
        }
    }
    
    public static void EnterKitchen() {
        System.out.println(Messages.outputMessageWithStats("You enter the kitchen and see the Greek worker Spanodoupoulos\n cutting onions in the corner of the room.", Halim)); //Shows main message and the player's stats
        Options("Run out of the room.", "Sneak past Spanodoupoulos.", "Say aye g, you aight g? to Spanodoupoulos", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                System.out.println(Messages.outputDeathMessage("escaping the room and getting caught by Kashii (the owner).\n You are fired on the spot and thrown back in the freezer.")); //Shows how the user died and ends the game.
                System.exit(0); //Quits the game
                break;
            case "bb":
                //Sneak past the greek worker but get noticed.
                Halim.setDialogOption(1);
                TalkToGreekWorker();
                //////////////////////////////////////////////
                break;
            case "cc":
                //Go up to the greek worker and talk to him.
                Halim.setDialogOption(2);
                TalkToGreekWorker();
                //////////////////////////////////////////////
                break;
            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                EnterKitchen();
            default:
                //Checks if the input is invalid and shows the player the options again. 
                System.out.println(Messages.outputWrongInput(_userOption));
                EnterKitchen();
                break;
        }
    }

    public static void TalkToGreekWorker() {

        if (Halim.getDialogOption() == 1) { //When the user tries to sneak past the greek worker but get caught.
            System.out.println(Messages.outputMessageWithStats("Spanodoupoulos notices you and says:\n \"Do not worry my friend, I am on your side.\"\n He gives you key to the bathroom.", Halim)); //Shows main message and the player's stats
            Options("Enter Bathroom.", "Talk to Spanodoupoulos.", "", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    Bathroom(); //Go to the bathroom
                    break;
                case "bb":
                    //Talk to the greek worker
                    Halim.setDialogOption(2);
                    TalkToGreekWorker();
                    /////////////////////////
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it,
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    TalkToGreekWorker();
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    EscapedFreezer();
               
            }
        }
        else if (Halim.getDialogOption() == 2) { //When ths user goes up to the greek worker and talks to them.
            System.out.println(Messages.outputMessageWithStats("Spanodoupoulos says: \"Hey Halim, you were locked in the freezer yes?\".\nWhat do you say?", Halim)); //Shows main message and the player's stats
            Options("Yeah, that was fun.", "No, that must have been someone else.", "", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    System.out.println(Messages.outputMessageWithStats("Spanodoupoulos says: \n\"I am sorry i couldn't help,\n I was too busy cutting onions. Here \n you will probably need this bathroom key, it \n is the least I can do.\"", Halim)); //Shows main message and the player's stats
                    Bathroom();
                    break;
                case "bb":
                    System.out.println(Messages.outputMessageWithStats("Spanodoupoulos says: \n\"I know it was you, I saw it myself,\n you can trust me my friend. Here \n Take this key to the bathroom, you will need it. \"", Halim)); //Shows main message and the player's stats
                    Bathroom();
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it,
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    TalkToGreekWorker();
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    TalkToGreekWorker();
               
            }
        }
    }

    public static void Bathroom() {
        if (Halim.wasInRoom("Bathroom") && Halim._washHandsCounter < 5) { //Checks if the player was already in the freezer so the corresponding message can be displayed.
            System.out.println(Messages.outputStats(Halim)); //Shows player stats
            System.out.println(Messages.showLocation("Bathroom")); //Shows the user where they are.
        }

        else if (Halim.wasInRoom("Bathroom") && Halim._washHandsCounter == 5) { //Checks if the player was already in the freezer so the corresponding message can be displayed.
            Halim.setArmour(Halim._armour+20);
            System.out.println(Messages.outputStats(Halim)); //Shows player stats
            System.out.println(Messages.outputMessage("Nice hand wash g! you are now Covid safe")); //Shows the player the easter egg message.
        }
        else {
            Halim.addItem("Herb"); //Picks up the buff (herb)
            Halim.addItem("Hungry Jacks Chicken Royal"); //Picks up the healing item (Hungry Jacks Chicken Royal)
            System.out.println(Messages.outputMessageWithStats("You look around in the store bathroom for something \n to help.\n You see a sign saying that \n say employees must wash their hands \n The greek worker points out a herb \n and Hungry Jacks Chicken Royal on the\n floor you put them into your pockets.\nYou also see a loose toilet seat.", Halim)); //Shows main message and the player's stats
        }         

        //If the player has nto washed their hands 5 times, they are given the option to wash them.
        String _option3 = "Wash hands"; 

        if (Halim._washHandsCounter < 5) { 
            _option3 = "Wash hands";
        }
        else {
            _option3 = "";
        }
        ////////////////////////////////////

        Options("Rip off toilet seat", "Ignore the seat and look around", _option3, ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        
        switch (_userOption) { //Process input
            case "aa":
                if (Halim.hasItem("Lint and String")) {
                    Halim.addItem("Toilet Seat");
                    System.out.println(Messages.outputMessageWithStats("You see that you can combine the toilet seat and string to make armour", Halim)); //Shows main message and the player's stats
                    CraftToiletSeat();
                }
                else {
                    Halim.addItem("Toilet Seat");
                    System.out.println(Messages.outputMessageWithStats("Toilet seat added to inventory", Halim)); //Shows main message and the player's stats
                    Halim.addRoom("Bathroom"); //Keeps record that the player was already in the Bathroom.
                    VentEntrance();
                }
                break;
            case "bb":
                Halim.addRoom("Bathroom"); //Keeps record that the player was already in the Bathroom.
                VentEntrance();
                break;
            case "cc":
                Halim.washHands();
                Halim.addRoom("Bathroom"); //Keeps record that the player was already in the Bathroom.
                Bathroom();
                break;
            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                Freezer();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                Freezer();
                break;
        }
    }
    
    public static void CraftToiletSeat() {
        Options("Craft armour", "Don't craft", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Halim.craftItem("Lint and String", "Toilet Seat", "Toilet Seat Armour"); //Crafts toilet seat armour.
                VentEntrance(); //Goes to the vents.
                break;
            case "bb":
                VentEntrance(); //Goes to the vents.
                break;
            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                CraftToiletSeat();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                CraftToiletSeat();
                break;
        }
    }
    
    public static void VentEntrance() {

        if (Halim.doneAction("Scream in vent")) { //Check if the player already screamed in the vent
            System.out.println(Messages.outputMessageWithStats("You hear a rat squeak, pretty whacky.", Halim)); //Shows main message and the player's stats
            Options("Go in.", "Stare at the vent for no reason.", "", ""); //Shows the user their options.

            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    Vents(); //Enter the vents.
                    break;
                case "bb":
                    VentEntrance(); //Stay in the same scene
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it,
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    VentEntrance();
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    VentEntrance();
                    break;
            }
        }
        else {
            //Shows main message and the player's stats
            System.out.println(Messages.outputMessageWithStats("You look up and see an unlocked vent.\n You ask Spanodoupoulos where \n it leads to and he says \n Dunno why don’t you crawl through and \n check, for some reason it’s big enough \n for you to crawl through. \n You try to reach it but you are too short.", Halim)); 
            

            Options("Ask Spanodoupoulos to give you a boost.", "Yell inside the vent.", "", ""); //Shows the user their options.

            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    Vents(); //Go in the vents
                    break;
                case "bb":
                    Halim.addAction("Scream in vent");
                    System.out.println(Messages.outputMessage("You scream Arghmuswashheroom and hear a rat squeak."));
                    VentEntrance();
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it,
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    VentEntrance();
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    VentEntrance();
                    break;
            }
        }
    }

    public static void Vents() {

        if (Halim.isAlive()) {
            if (Halim.wasInRoom("Vents")) {
                System.out.println(Messages.outputMessageWithStats("You crawl back and see that you can either go left or right.", Halim)); //Shows main message and the player's stats
                Options("Go left.", "Go right.", "", ""); //Shows the user their options.
                String _userOption = _MainScanner.next(); //Get input 
                switch (_userOption) { //Process input
                    case "aa":
                        LeftVent();
                        break;
                    case "bb":
                        RightVent();
                        break;
                    //Checks if the user is using an item from the inventory and lets the user use it,
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        //Lets the user use an item
                        Halim.useItem(_userOption);
                        Vents();
                    default: //Checks if the input is invalid and shows the player the options again.
                        System.out.println(Messages.outputWrongInput(_userOption));
                        Vents();
                        break;
                }
            }
            else { //First time in the vents

                //Shows main message and the player's stats
                System.out.println(Messages.outputMessageWithStats("Spanodoupoulos gives you a boost into \n the vent. You crawl forward in \n this unusually big vent and \n realise that there is a cross \n road. You can either go left or right.", Halim));
                
                Options("Go left.", "Go right.", "", ""); //Shows the user their options.
                String _userOption = _MainScanner.next(); //Get input 
                switch (_userOption) { //Process input
                    case "aa":
                        Halim.addRoom("Vents"); //Keeps record that the player was already in the Vents.
                        LeftVent();
                        break;
                    case "bb":
                        Halim.addRoom("Vents"); //Keeps record that the player was already in the Vents.
                        RightVent();
                        break;
                    //Checks if the user is using an item from the inventory and lets the user use it,
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        //Lets the user use an item
                        Halim.useItem(_userOption);
                        Vents();
                    default: //Checks if the input is invalid and shows the player the options again.
                        System.out.println(Messages.outputWrongInput(_userOption));
                        Vents();
                        break;
                }
            }
        }
        else {
            System.out.println(Messages.outputDeathMessage("beaten up by a rat")); //Shows how the user died and ends the game.
            System.exit(0); //Quits the game
        }
    }

    public static void LeftVent() { //Dead end vent
        System.out.println(Messages.outputMessageWithStats("You crawl to the left vent and come to a dead end and find a half eaten rotten kebab.", Halim)); //Shows main message and the player's stats
        Options("Go back.", "Go to the right vent.", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Vents(); //Go back to the start of the vents.
                break;
            case "bb":
                RightVent(); //Go to the vent with the rat.
                break;
            //Checks if the user is using an item from the inventory and lets the user use it,
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                LeftVent();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                LeftVent();
                break;
        }
    }

    public static void RightVent() { //Vent with the hostile rat.
        if (Halim.isAlive()) {
            //Shows main message and the player's stats
            System.out.println(Messages.outputMessageWithStats("You crawl to the right vent \n and see where all that \n squeaking was coming from, \n there is a cat sized \n rat! \n Behind the rat \n you see a golden key \n and a vial with green fluid.", Halim));
            
            Options("Try to go past the rat.", "Fight the rat.", "Retreat", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    ApproachRat(); //Go to the rat and get hurt.
                    break;
                case "bb":
                    RatFight(); //Start fighting the rat.
                    break;
                case "cc":
                    Vents(); //Go back to start of vents
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it,
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    RightVent();
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    RightVent();
                    break;
            }
        }
        else {
            System.out.println(Messages.outputDeathMessage("beaten up by a rat")); //Shows how the user died and ends the game.
            System.exit(0); //Quits the game
        }
    }

    public static void ApproachRat() {
        Halim.takeDamage(Rat.damageDone(15)); //Player takes damage

        //Shows main message and the player's stats
        System.out.println(Messages.outputMessageWithStats("You try to reach for the shiny \n key and green vial but the \n rat does a spinning side \n kick to your face and breaks \n your nose.", Halim)); //Shows main message and the player's stats
        
        Options("Fight the rat.", "Retreat", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Halim.setDialogOption(2);
                RatFight();
                break;
            case "bb":
                Vents();
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                RatFight();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                RatFight();
            
        }
    }

    public static void RatFight() { //Combat with rat in vents.
        if (Rat.isAlive() && Halim.isAlive()) { //Checks if the user and the rat is alive, if they are the fight continues.
            System.out.println(Messages.outputMessageWithStats("You look in your inventory and think what you can use to attack the rat.", Halim)); //Shows main message and the player's stats
            System.out.println("Rats health: " + Rat.getHealth()); //Shows the Rat's health
            System.out.println(Messages.outputWeapons(Halim)); //Shows the weapons the player has
            
            String _option2 = "Punch rat";
            String _option3 = "";

            //Changes certain options if the user has certain items.
            if (Halim.hasItem("Kebab Stick")) {
                _option2 = "Jab the rat with kebab stick";
            }
            if (Halim.hasItem("Frozen Kebab")) {
                _option3 = "Whack rat with frozen kebab";
            }
            ////////////////////////////////////////////////////////
            Options("Retreat", _option2, _option3, ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    Vents(); //Go back to the vents,
                    break;
                case "bb":
                    //If the user doesn't have a kebab stick
                    //in option 2 they can punch the rat
                    //if they do have kebab stick they can 
                    //try to jab the rat with a chance to 
                    //crit, making the rat run away.
                    if (_option2 == "Punch rat") { 
                        Halim.setPlayerDamage(Halim.playerDamage(5) + 15);
                        Rat.takeDamage(Halim.playerDamage(10));
                        if (Rat.getHealth() <= 0) {
                            Halim.setDialogOption(1);
                            RatBeaten();
                        }
                        else {
                            Halim.setDialogOption(1);
                            RatAttack();
                        }
                    }
                    else if (_option2 == "Jab the rat with kebab stick") {
                        int _chanceToTickle = Randomizer.nextInt(3);
                        if (_chanceToTickle == 1) {
                            Halim.setDialogOption(3); //Rat was tickled
                            RatBeaten();
                        }
                        else {
                            Halim.takeDamage(5);
                            Rat.takeDamage(Halim.playerDamage(20));
                            Halim.setDialogOption(3);
                            RatAttack();
                        }
                    }
                    ////////////////////////////////////////////////////
                    break;
                case "cc":
                    //If the player has the frozen kebab (weapon with highest damage)
                    //they can use it on the rat.
                    if (_option3 == "Whack rat with frozen kebab") {
                        Halim.setPlayerDamage(40);
                        Rat.takeDamage(Halim.playerDamage(40));
                        if (Rat.isAlive() == false) {
                            Halim.setDialogOption(2);
                            RatBeaten();
                        }
                        else {
                            Halim.setDialogOption(2);
                            RatAttack();
                        }  
                    }
                    ///////////////////////////////////////////////////////////////
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it.
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    Halim.setDialogOption(2);
                    RatFight();
                default: //Checks if the input is invalid and shows the player the options again.                         
                    System.out.println(Messages.outputWrongInput(_userOption));
                    RatFight();
                
            }
        }
        else if (Halim.isAlive() == false) { //If the player is dead show the end message
            System.out.println(Messages.outputDeathMessage("being rekt by a rat lol.")); //Shows how the user died and ends the game.
            System.exit(0); //Quits the game
        }     
        else if (Rat.isAlive() == false) { //If the rat got defeated end the fight.
            Halim.setDialogOption(4);
            RatBeaten();
        }
    }
   
    public static void RatAttack() {
        if (Halim.getDialogOption() == 1) { 
            //Shows the death message for this scenario
            if (Halim.isAlive() == false) {
                Messages.outputDeathMessage("shredded by a buff rat."); //Shows how the user died and ends the game.
                System.exit(0); //Quits the game
            }
            ///////////////////////////////////////////
            
            //Shows the winning message for this scenario.
            else if (Halim._health > 0 && Rat.getHealth() > 0) {
                Halim.takeDamage(Rat.damageDone(10));
                System.out.println(Messages.outputMessageWithStats("You punch the rat but \n he just laughs it off \n and does a spinning round \n house kick to your head. ", Halim)); //Shows main message and the player's stats
                Halim.setDialogOption(2);
                RatFight();
            }
            ///////////////////////////////////////////
        }
        else if (Halim.getDialogOption() == 2) {
            Halim.takeDamage(Rat.damageDone(5));
            //Shows the death message for this scenario
            if (Halim.isAlive() == false) {
                Messages.outputDeathMessage("kicked by a buff rat."); //Shows how the user died and ends the game.
                System.exit(0); //Quits the game
            }
            ///////////////////////////////////////////

            //Shows the winning message for this scenario.
            else if (Halim.isAlive()) {
                System.out.println(Messages.outputMessageWithStats("You whack the rat \n into the wall but \n he pushes the kebab stick away \n and round house kicks \n your jaw", Halim)); //Shows main message and the player's stats
                Halim.setDialogOption(2);
                RatFight();
            }     
            ///////////////////////////////////////////  
        }
        else if(Halim.getDialogOption() == 3) {

            System.out.println(Messages.outputDeathMessage("getting beaten to death by a rat")); //Shows how the user died and ends the game.
            System.exit(0); //Quits the game
            //Shows the death message for this scenario
            if (Halim.isAlive() == true) {
                System.out.println(Messages.outputMessageWithStats("You jab the rat with your \n kebab stick but he pulls \n it out and puts his finger \n in your eye.", Halim)); //Shows main message and the player's stats
                Halim.setDialogOption(2);
                RatFight();
            }
            ///////////////////////////////////////////
        } 
    }

    public static void RatBeaten() {
        if (Halim.getDialogOption() == 1) { //Beaten with punch
            System.out.println(Messages.outputMessageWithStats("You have hooked punched \n the rat and he \n is now unconcious", Halim)); //Shows main message and the player's stats
        }
        else if (Halim.getDialogOption() == 2) { //Beaten with frozen kebab
            System.out.println(Messages.outputMessageWithStats("You have whacked the \n rat with you frozen kebab \n with all your might! \n He went flying to the \n wall and passed out.", Halim)); //Shows main message and the player's stats
        }
        else if (Halim.getDialogOption() == 3) { //Beaten with kebab stick
            System.out.println(Messages.outputMessageWithStats("You try to jab the rat \n but accidentally tickle him \n with the kebab stick. \n He giggles and runs away. \n Looks like he hates tickles.", Halim)); //Shows main message and the player's stats
        }
        else if (Halim.getDialogOption() == 4) { //Other type of defeat
            System.out.println(Messages.outputMessageWithStats("You have rekt the rat.", Halim));
        }
        Halim.pickUpKey(); //user takes the key from behind the rat,
        System.out.println(Messages.outputMessageWithStats("You see the golden key \n in front of you and pick\n it up. There is \n also a green vial.", Halim)); //Shows main message and the player's stats
        Options("Pick up the vial.", "Ignore it.", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Halim.addItem("Antidote");
                Kitchen();
                break;
            case "bb":
                Kitchen();
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                RatBeaten();
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                RatBeaten();
                break;
        }
    }

    public static void Kitchen() {
        //If the player does not have 2 keys they will enter kitchen. 
        //If the player has two keys they will start the fight with 
        //the greek worker.
        if (Halim.hasEnoughKeys() == false) { 
            Halim.addRoom("Kitchen"); //Keeps record that the player was already in the Kitchen.

            //Shows main message and the player's stats
            System.out.println(Messages.outputMessageWithStats("You keep crawling through the vents \n and accidentally fall down \n one of the openings and \n land on the main stove \n in the kitchen, thank god \n it is off.", Halim));
            
            String _option2 = "Look around.";
            if (Halim.hasItem("Kebab") && Halim.wasInRoom("Kitchen")) {
                _option2 = "";
            }
            Options("Go to main area (Bistro).", _option2, "", "");
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    Bistro();
                    break;
                case "bb":
                    Halim.addItem("Kebab");
                    System.out.println(Messages.outputMessage("You found a kebab and added it to inventory."));
                    Kitchen();
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it.
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    Kitchen();
                    break;
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    Kitchen();
                    break;
            }
        }
        else {
            FightStageOne();
        }
    }

    public static void Bistro() {
        //If the player has two keys
        //the fight with the greek 
        //worker will start.
        //If the player does not
        //have two keys they will 
        //just enter the bistro and
        //be able to enter other 
        //rooms.
        if (Halim._keys == 2) {
            FightStageOne();
        }
        else {
            Halim.addRoom("Bistro"); //Keeps record that the player was already in the Bistro.
            System.out.println(Messages.outputMessageWithStats("You look around the \n bistro but see no \n trace of Spanodoupoulos.\n You see doors to other rooms.", Halim)); //Shows main message and the player's stats
            Options("Staff Room.", "Stock Room", "Office", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    StaffRoom(); //Go to the staff room
                    break;
                case "bb":
                    StockRoom(); //Go to the stock room
                    break;
                case "cc":
                    Office(); //Go to the office.
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it.
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    Bistro();
                    break;
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    Bistro();
                    break;
            }
        }
    }

    public static void StockRoom() {
        String _option2 = "Look through the pile of items.";
        String _message = "You enter the stock room \n and see a pile of loose items \n , next to them you see \n a desk with a drawer.";
        if (Halim.doneAction("Looked through pile")) { //Changes the message if the player searched the pile of stuff.
            _option2 = "";
            _message = "You looked through the \n pile but found nothing.";
        }

       // Halim.addRoom("Stock Room");
        System.out.println(Messages.outputMessageWithStats(_message, Halim)); //Shows main message and the player's stats        
        Options("Open desk drawer", _option2, "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Drawer(); //Open the drawer
                break;
            case "bb":
                //Searching through the pile finds nothing.
                Halim.addAction("Looked through pile");
                StockRoom();
                ////////////////////////////////////////
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                StockRoom();
                break;
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                StockRoom();
                break;
        }
    }

    public static void Drawer() {
        String _option3 = "";
        //If the player has already taken the
        //playstation vita don't let them take
        //it again
        if (Halim.doneAction("Take PSV")) {
            _option3 = "";
        }
        else {
            _option3 = "Snatch Playstation Vita.";
        }
        ////////////////////////////////////////////

        //Shows main message and the player's stats
        System.out.println(Messages.outputMessageWithStats("You open the drawer and \n see a hammer, bunch of papers, \n  locked box and a Playstation \n Vita.", Halim));        
        
        Options("Epic ignore.", "Take the hammer", _option3, ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Halim.addRoom("Drawer");
                StockRoom(); //Go back to the stock room.
                break;
            case "bb":
                //Take the hammer
                Halim.addRoom("Drawer");
                Halim.addAction("Taken Hammer");
                Hammer();
                break;
            case "cc":
                //If the playstation vita wasn't already taken
                //take it.
                if (Halim.doneAction("Take PSV") == false) { 
                    Halim.addRoom("Drawer");    
                    Halim.addAction("Take PSV");
                    Drawer();    
                }
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                Drawer();
                break;
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                Drawer();
                break;
        }
    }

    public static void Hammer() {

        String _option2 = "Bash the lock on the box with your hammer.";
        String _message = "You pick up the hammer \n and see the lock on \n the box isn't indescrutable.";

        //If the user has broke the box 
        //and got the key they can exit to bistro.
        if (Halim.doneAction("Bashed Box")) { 
            _option2 = "Exit back to Bistro";
            _message = "You broke the lock and managed to get another key";
        }

        System.out.println(Messages.outputMessageWithStats(_message, Halim)); //Shows main message and the player's stats        
        Options("Put the hammer back.", _option2, "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Drawer(); //Open the drawer
                break;
            case "bb":
                if (Halim.doneAction("Bashed Box")) {
                    Bistro();
                }
                else {
                    Halim.addAction("Bashed Box");
                    Halim.pickUpKey();
                    Hammer();
                }
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                Hammer();
                break;
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                Hammer();
                break;
        }
    }

    public static void StaffRoom() {
        Halim.addRoom("Staff Room");

        //Shows main message and the player's stats
        System.out.println(Messages.outputMessageWithStats("You have now entered the \n staff room and see \n Kashii's Origin gaming PC unlocked \n with Outlook open.", Halim));

        Options("Have a closer look.", "Leave", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Laptop(); //Look at the email.
                break;
            case "bb":
                engageInCombat(); //Fight the greek worker.                
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                StaffRoom();
                break;
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                StaffRoom();
                break;
        }
    }

    public static void Laptop() {
        //Shows main message and the player's stats
        System.out.println(Messages.outputMessageWithStats("You read the email and \n see that Spanodoupoulos \n is plotting against you. \n It seems that he \n might have a key!", Halim));

        Options("Leave.", "Reread the email", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Bistro(); //Go back to the bistro.
                break;
            case "bb":
                Laptop(); //Stay in this scene.               
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                Laptop();
                break;
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                Laptop();
                break;
        }
    }

    public static void engageInCombat() {//Checks if the player has 2 keys, if they do then start combat with greek worker.
        if (Halim._keys != 2) { //Greek worker will not attack
            Bistro();
        }
        else { //If you have 2 keys that means the greek worker will fight you
            FightStageOne();
        }
    }

    public static void Office() { 
        //If the player has all three keys they
        //can get the recipe and escape.
        String _message = "You try to enter but \n don't have enough keys, \n you need 3 to enter.";

        if (Halim.hasEnoughKeys()) {
            _message = "You enter the office and see \n some golden paper on \n the desk, you already \n know what it is for. \n You made it.";
            if (Halim.doneAction("Read Recipe")) {
                _message = "You try to read the \n recipe but you are \n too excited to be \n able to understand it.";
            }
            //Shows main message and the player's stats
            System.out.println(Messages.outputMessageWithStats(_message, Halim));

            Options("Escape.", "Read the recipe.", "", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    Exit();
                    break;
                case "bb":
                    Halim.addAction("Read Recipe");
                    Office();                
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it.
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    Office();
                    break;
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    Office();
                    break;
            }
        }
        
        else {
            System.out.println("Keys: " + Halim._keys);
            System.out.println(Messages.outputMessageWithStats(_message, Halim)); //Shows main message and the player's stats

            Options("Back to Bistro.", "Try again.", "", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    Bistro();
                    break;
                case "bb":
                    Office();                
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it.
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    Office();
                    break;
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    Office();
                    break;
            }
        }
    }

    public static void FightStageOne() {
        //Random number generator
        Random _dodgeChance = new Random(); 
        int _dodged = _dodgeChance.nextInt(100);
        /////////////////////////////////////////
        String _message = "";
        if (Spanodoupoulos.getAttacksDone() < 5) { //If the greek worker threw a pan less than 5 times he will throw another.

            if (Spanodoupoulos.getAttacksDone() == 0) { //When you first enter
                _message = "You enter the kitchen and hear \n footsteps behind you, your \n instincts kick in and you \n spin around and narrowly dodge \n a pan and see it flying \n past your head. It was \n thrown by Spanodoupoulos! He \n says: \"Ha! Did you really \n think I would help you \n get that special recipe Halimi \n boy!? I would never! \n General Alladeen will be \n most grateful when i \n hand him that recipe.\" He grabs another pan \n and throws it at you.";
            }
            else if (Spanodoupoulos.getAttacksDone() > 0) { //if Spanodoupoulos already threw a pan but no more than 5 he will throw another
                if (Halim.doneAction("Hit by pan")) { //If player gets hit by pan they lose health.
                    Halim.setHealth(Halim._health - 10);
                    _message = "You got hit by the \n pan and see that \n Spanodoupoulos is \n throwing another at you.";
                    Halim.removeAction("Hit by pan");
                }
                else if (Halim.doneAction("Dodged")) { //If player dodges the pan.
                    _message = "You dodged the pan \n but see another flying \n at you!";
                    Halim.removeAction("Dodged");
                } 
            }

            System.out.println(Messages.outputMessageWithStats(_message, Halim)); //Shows main message and the player's stats

            Options("Dodge right.", "Dodge left.", "", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input 
            switch (_userOption) { //Process input
                case "aa":
                    Spanodoupoulos.addAttack();
                    
                    Halim.addAction("Dodged Right");

                    if (_dodged <= 70) { //70% chance to successfully dodge.
                        Halim.addAction("Dodged");
                    }
                    else if (_dodged > 70) { //30% chance to get hit by the pan.
                        Halim.addAction("Hit by pan");
                    }

                    if (Halim.doneAction("Dodged Left")) {
                        Halim.removeAction("Dodged Left");
                    }

                    FightStageOne();
                    
                    break;
                case "bb":
                    Spanodoupoulos.addAttack();

                    Halim.addAction("Dodged Left");

                    if (_dodged <= 70) { //70% chance to successfully dodge.
                        Halim.addAction("Dodged");
                    }
                    else if (_dodged > 70) { //30% chance to get hit by the pan.
                        Halim.addAction("Hit by pan");
                    }
                    
                    if (Halim.doneAction("Dodged Right")) {
                        Halim.removeAction("Dodged Right");
                    }
                    
                    FightStageOne();
                                
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it.
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    FightStageOne();
                    break;
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    FightStageOne();
                    break;
            }
        }

        else if (Spanodoupoulos.getAttacksDone() >= 5) { // if Spanodoupoulos threw a pan 5 times, stage 2 will start
            FightStageTwo(); 
        }
    }


    public static void FightStageTwo() {

        if (Spanodoupoulos.getHealth() > 50 && Halim.isAlive()){

            //Messages displayed
            String _message = "Spanodoupoulos stops throwing pans \n and says: \"Impressive Halimi, \n it is obvious this contest \n can not be decided by \n my throwing and your dodging skills, \n but by our skills with the \n broom stick\". He throws \n you a broom stick and \n you catch it. He dive \n roles to you and is \n in front of you.";
            
            //Changes the message shown depending on what happened in combat.

            if (Halim.doneAction("Crited")) {
                _message = "You whacked Spanodoupoulos \n on the head giving him \n a mild concussion.";
                Halim.removeAction("Crited");
            }
            else if (Halim.doneAction("Hit")) {
                _message = "You whacked \n Spanodoupoulos's torso.";
                Halim.removeAction("Hit");
            }
            else if (Halim.doneAction("Blocked")) {
                _message = "Spanodoupoulos tried to \n strike you but you \n successfully blocked him.";
                Halim.removeAction("Blocked");
            }
            else if (Halim.doneAction("Miss Block")) {
                _message = "You tried to block \n Spanodoupoulos's attack but \n he saw it coming and \n still managed to hit you.";
                Halim.removeAction("Miss Block");
            }
            else if (Halim.doneAction("Deflect strike")) {
                _message = "You blocked Spanodoupoulos's \n strike and managed to land \n a critical blow on him \n while he was off balance.";
                Halim.removeAction("Deflect strike");
            }

            if (Spanodoupoulos.doneAction("Dodged")) {
                _message = "You swang your broom \n to hit Spanodoupoulos \n but he dodged it.";
                Spanodoupoulos.removeAction("Dodged");
            }
            else if (Spanodoupoulos.doneAction("Blocked")) {
                _message = "You tried to strike \n Spanodoupoulos but he \n was too quick for \n you and blocked your \n strike";
                Spanodoupoulos.removeAction("Blocked");
            }
            else if (Spanodoupoulos.doneAction("Block and Strike")) {
                _message = "You tried to strike \n Spanodoupoulos but he \n redirected your attack \n and whacked you in \n the ribs.";
                Spanodoupoulos.removeAction("Block and Strike");
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Random _randomizer = new Random();
            System.out.println(Messages.outputMessageWithStats(_message, Halim)); //Shows main message and the player's stats
            System.out.println("Greek worker health: " + Spanodoupoulos.getHealth());
            Options("Attack.", "Block.", "", ""); //Shows the user their options.
            String _userOption = _MainScanner.next(); //Get input
            int _randomNum = _randomizer.nextInt(100);
            System.out.println("Random number is: " + _randomNum);
            switch (_userOption) {
                case "aa":
                    if (_randomNum < 50) { //50% chance you hit the greek worker
                        if (_randomNum < 40) { //40% chance to crit
                            Halim.addAction("Crited");
                            Spanodoupoulos.takeDamage(30); //Crit damage
                            FightStageTwo();
                        }
                        else {
                            Halim.addAction("Hit");
                            Spanodoupoulos.takeDamage(20); //Normal damage
                            FightStageTwo();
                        }
                    }

                    else if (_randomNum > 50 && _randomNum < 80) {
                        //15 percentage chance for your strike to miss the greek worker
                        if (_randomNum <= 65) { 
                            Spanodoupoulos.addAction("Dodged");
                            FightStageTwo();
                        }
                        //15 percentage chance for the greek worker to block your strike
                        else {
                            Spanodoupoulos.addAction("Blocked");
                            FightStageTwo();
                        }
                    }

                    else if (_randomNum > 80) { //20 percentage chance he blocks and hits you
                        Spanodoupoulos.addAction("Block and Strike");
                        Halim.takeDamage(20);
                        FightStageTwo();
                    }
                    break;
                case "bb":
                    if (_randomNum <= 60) { //60% chance you block successfully
                        Halim.addAction("Blocked");
                        FightStageTwo();
                    }
                    else if (_randomNum > 60 && _randomNum <= 80) { //20% chance you try to block but he still hits you
                        Halim.addAction("Miss Block");
                        Halim.takeDamage(15);
                        FightStageTwo();
                    }
                    else if (_randomNum > 80) { //20% chance to block and do damage to the greek worker
                        Halim.addAction("Deflect strike");
                        Spanodoupoulos.takeDamage(30);
                        FightStageTwo();          
                    }
                    break;
                //Checks if the user is using an item from the inventory and lets the user use it.
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    //Lets the user use an item
                    Halim.useItem(_userOption);
                    FightStageTwo();
                    break;
                default: //Checks if the input is invalid and shows the player the options again.
                    System.out.println(Messages.outputWrongInput(_userOption));
                    FightStageTwo();
                    break;
            } 
        }
    
        else if (Spanodoupoulos.getHealth() <= 50) { //Go to next stage of fight when he greek worker has 50 or less health left.
            FightStageThree();
        }
        else if (Halim._health <= 0) {
            System.out.println(Messages.outputDeathMessage("being whacked with a broom stick")); //Shows how the user died and ends the game.
            System.exit(0); //Quits the game
        }
    }

    public static void FightStageThree() {

        //Shows main message and the player's stats
        System.out.println(Messages.outputMessageWithStats("Spanodoupoulos does a \n critical spinning side \n kick to you, making you \n collide to the kitchen wall. \n Spanodoupoulos laughs and says \"You fool Halim! I am the grand \n master! Surrender yourself \n to me and I will make \n your end be quick and \n painless, do not resist boy.\"", Halim));
        
        Options("Surrender.", "Keep fighting", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                System.out.println(Messages.outputDeathMessage("surrendering to Spanodoupoulos.")); //Shows how the user died and ends the game.
                System.exit(0); //Quits the game
                break;
            case "bb":
                FightStageFour(); //Defeat the greek worker.
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                FightStageThree();
                break;
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                FightStageThree();
                break;
        }
    }

    public static void FightStageFour() {
        String _message = "";
        //Checks if the player already defeated the greek worker
        //if they did then a different message shows up
        if (Halim.wasInRoom("FightStageFour")) { 
            _message = "You see the final \n key in Spanodoupoulos's \n pocket";
        }
        else {
            _message = "You harness your inner rage \n and launch yourself at \n Spanodoupoulos. You both \n exchange blows with your broom \n sticks, almost breaking them \n until you trip Spanodoupoulos \n off balance and do a \n spinning thrust strike, stabbing \n Spanodoupoulos in the heart. \n Spanodoupoulos says his final words: \n \"For...Abu Youse-\". \n He collapses onto the \n ground and you see the final \n key in his shirt pocket.";
        }
        ////////////////////////////////////////////////////////////
        Halim.addRoom("FightStageFour");
        System.out.println(Messages.outputMessageWithStats(_message, Halim)); //Shows main message and the player's stats
        Options("Look at Spanodoupoulos.", "Take the key and go to office", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                FightStageFour(); //Stays in this scene.
                break;
            case "bb":
                //User gets the final key and goes to 
                //office to get the recipe
                Halim.pickUpKey();
                Office();
                /////////////////////////////////////
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                FightStageFour();
                break;
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                FightStageFour();
                break;
        }
    }

    public static void Exit() {
        //Shows main message and the player's stats
        System.out.println(Messages.outputMessageWithStats("You walk to the exit \n of the store and see the \n rat again in your way. He \n is still very tired from \n the fight before. He says: \n \"Not, this, time, Halim. \n I will, make sure, you \n do not leave, here, alive. \"", Halim));
        
        Options("Kick the rat out of the way.", "Jump over the rat and escape.", "", ""); //Shows the user their options.
        String _userOption = _MainScanner.next(); //Get input 
        switch (_userOption) { //Process input
            case "aa":
                Halim.addAction("Kick Rat"); //Keeps track of the player attacking the rat.
                Ending(); //Goes to the ending of the game.
                break;
            case "bb":
                Ending(); //Goes to the ending of the game.
                break;
            //Checks if the user is using an item from the inventory and lets the user use it.
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                //Lets the user use an item
                Halim.useItem(_userOption);
                Exit();
                break;
            default: //Checks if the input is invalid and shows the player the options again.
                System.out.println(Messages.outputWrongInput(_userOption));
                Exit();
                break;
        }
    }

    public static void Ending() { //Shows the ending of the game. If you get here it means that you have won.

        //The end message
        String _message = "You enter your V8 Holden Commodore \n with the recipe excited to take \n it to General Alladeen of Wadiya. \n You zoom past everyone, going to \n the airport but out of \n nowhere your car gets hit \n from the side by a big truck. \n Your car goes spinning over \n and you are stuck, paralysed \n and losing conciousness. You \n see a man walk towards you, \n it is your nemesis, Abu Yousef. \n He approaches you and squats down, \n he sees the recipe in your \n hand and snatches it. \n He says \"Thank you boy, Alladeen \n will reward me well for \n this.\" He walks off as \n you try to reach out to \n stop him but it is useless, \n you slowly give up hope \n and eventually pass out. \n TO BE CONTINUED";
        
        //If the player has attacked the rat at the end some text is added to the front.
        if (Halim.doneAction("Kick Rat")) { 
            _message = "You kicked the rat out \n of the way, knocking him \n out cold again and escape. \n" + _message;
        }
        System.out.println(Messages.outputEndMessage(_message)); //Shows the end message.
        System.exit(0); //Quits the game
    }
}