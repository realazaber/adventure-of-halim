import java.util.Random;
import java.util.ArrayList;

public class player {
    int _health = 69;
    int _armour = 0;
    int _dialogOption = 0; //Keeps track of which dialog option the player chose.

    //List of all items and their types//

    String[] _heals = {"Kebab", "Hungry Jacks Chicken Royal", "Antidote"}; //Items that heal the player.

    String[] _poisons = {"Passion Fruit", "Red Rooster"}; //Items that poison the player.

    String[] _armours = {"Toilet Seat Armour"}; //Items that give the player armour.

    String[] _buffs = {"Herb"}; //Items that increase player damage.

    String[] _useLess = {"Lint and String", "Kebab Stick", "Frozen Kebab"}; //Useless items.

    String[] _craftingMaterials = {"Lint and String", "Toilet Seat"}; //Materials that can be used for crafting.

    String[] _weaponItems = {"Frozen Kebab", "Kebab Stick"};

    ArrayList<String> _weapons = new ArrayList<String>(); //Weapons that can be used by the player.

    String[] _questItems = {"Kebab Stick"}; //Items needed to go through the story.

    ArrayList<String> _actions = new ArrayList<String>(); //Keep track of actions the player has done.

    int _armourGain = 40; //Ammount of armour the player gains when equiping armour.
    int _healthGain = 30; //Ammount of health regenerated when eating a health item.
    int _poisonDamage = 10; //Ammount of health lost when eating a poison.

    int _weaponDamage = 20; //Default weapon damage the player does.

    int _washHandsCounter = 0; //Keeps track of how many times the player has washed their hands, if they wash their hands 5 times in the bathroom they get a special message.

    int _keys = 0; //Keeps track of how many keys the player has, the player needs 3 keys in order to go to the final room.

    ////////////////////////////////////////

    ArrayList<String> _inventory = new ArrayList<String>(); //Player inventory.
    int _bagLimit = 5; //Maxiumum ammount of items a player can store.

    ArrayList<String> _roomTracker = new ArrayList<String>(); //Keeps track of the rooms the player was in.
    
    public player(int health, int armour) { //Constructor for the player, you can set the health and armour.
        this._health = health;
        this._armour = armour;
    }

    public void addAction(String _action) { //Records an action the player has done.
        if (_actions.contains(_action) == false) {
            _actions.add(_action);
        }
    }

    public boolean doneAction(String _action) { //Checks if the player already done something.
        if (_actions.contains(_action)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void removeAction(String _action) { //Removes action from record.
        _actions.remove(_action);
    }

    public void setPlayerDamage(int _damage) { //Changes the damage the player does.
        this._weaponDamage = _damage;
        
    }

    public int playerDamage(int _damageMultiplier) { //Calculates the player's damage and chance to crit.
        Random RandomNum = new Random();
        int _upperBound = 4;
        int _randomNumber = RandomNum.nextInt(_upperBound);
        if (_randomNumber > 2) {
            this._weaponDamage = this._weaponDamage + _damageMultiplier;
        }
        return this._weaponDamage;
    }

    public int getPlayerDamage() { //Checks how much damage the player does.
        return this._weaponDamage;
    }

    public void takeDamage(int _damage) { //Makes the player take damage (lose health and/or armour).
        
        if (_damage > this._armour) {
            int _remainingDamage = _damage - this._armour;
            this._armour = this._armour - (_damage - _remainingDamage);
            this._health -= _remainingDamage;
        }
        else {
            this._armour -= _damage;
        }
    }

    public void addRoom(String _room) { //Records the room the player was in.
        _roomTracker.add(_room);
    }

    public boolean wasInRoom(String _room) { //Checks if the player was in a certain room.
        if (this._roomTracker.indexOf(_room) > -1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void useItem(String _chosenItem) { //Lets the player use an item.
        int _indexOfItem = Integer.parseInt(_chosenItem); //Gets the number the user chose.
        _indexOfItem = _indexOfItem - 1; //Subtracts 1 so that the user's number choice can be used to search the index of inventory,
        int _counter = 0; //used to keep track of items.
        int _itemsUsed = 0; //keeps track of how many items the player used and makes sure it doesn't go over 1 (because the user can only use one item at a time).
        boolean _itemUseless = false; //Checks if the item is useless (cannot be used)
        

        if (_indexOfItem < _inventory.size() && _itemsUsed == 0) { //Goes through the inventory and checks if the item name match any of the itmes in the item categories.
            for (int i = 0; i < _inventory.size(); i++) {
                
                for (int h = 0; h < _heals.length; h++) { //Checks if the item is a healing item, if it is used and health is added.
                    if (this.hasItem(_heals[h]) && _heals[h] == _inventory.get(_indexOfItem) && _itemsUsed == 0) {
                        _counter++;
                        _itemsUsed++;
                        this._health = this._health + _healthGain;
                        System.out.println("You now have eaten " + _heals[h] + " and now have " + this._health + " health!");                              
                    }
                }
    
                for (int a = 0; a < _armours.length; a++) { //Checks if the item is an armour item, if it is used and armour is added.
                    if (this.hasItem(_armours[a]) && _armours[a] == _inventory.get(_indexOfItem) && _itemsUsed == 0) {
                        _counter++;
                        _itemsUsed++;
                        this._armour = this._armour + _armourGain;
                        System.out.println("You have equipped " + _armours[a] + " now have " + this._armour + " armour!");              
                    }
                }
    
                for (int p = 0; p < _poisons.length; p++) { //Checks if the item is a poison item, if it is used and health is decreased.
                    if (this.hasItem(_poisons[p]) && _poisons[p] == _inventory.get(_indexOfItem) && _itemsUsed == 0) {
                        _counter++;
                        _itemsUsed++;
                        this._health = this._health - _poisonDamage;
                        System.out.println("You have ate " + _poisons[p] + " and lost " + _poisonDamage + " health!");
                    }
                }

                for (int b = 0; b < _buffs.length; b++) { //Checks if the item is a buff item, if it is used and weapon damaged is increased.
                    if (this.hasItem(_buffs[b]) && _buffs[b] == _inventory.get(_indexOfItem) && _itemsUsed == 0) {
                        _counter++;
                        _itemsUsed++;
                        this._weaponDamage += 10;
                        System.out.println("You ate " + _buffs[b] + " and your weapon damage is now " + _weaponDamage + "!");
                    }
                }

                for (int u = 0; u < _useLess.length; u++) { //Checks if the item is useless and if so tells the user they can not use it in the current scenario.
                    if (this.hasItem(_useLess[u]) && _useLess[u] == _inventory.get(_indexOfItem) && _itemsUsed == 0) {
                        _itemUseless = true;
                        _counter++;
                        System.out.println(_useLess[u] + " is useless, you can not use it this way.");
                    }
                }
            }  

            if (_itemUseless == false && _itemsUsed == 1) { //If item can be used the item is taken away from inventory.
                _inventory.remove(_indexOfItem);
            }
            
        } 
        else { //If the item slot is empty tell the user the user is notified they can use anything.
            System.out.println("Pockets are empty or item can not be used now.");
        }
    }

    public void craftItem(String _item1, String _item2, String _newItem) { //Lets the user craft an item.
        //Remove crafting materials.
        this._inventory.remove(_item1); 
        this._inventory.remove(_item2);
        ////////////////////////////////

        //Adds crafted item to inventory.
        this._inventory.add(_newItem); 
    }

    public String viewInventory() { //Show the inventory
        return "Pockets: " + _inventory;
    }

    public String addItem(String _item) { //Adds item to inventory
        
        //If the item is a weapon, add it to the weapons array list.
        for (int i = 0; i < _weaponItems.length; i++) {
            if (_weaponItems[i] == _item) { //Checks if item is a weapon.
                _weapons.add(_item); //Adds the item to the weapons array list.
            }
        }
        //Adds item to inventory.
        if (this._inventory.size() < 5) { //Checks if bag is not full.
            _inventory.add(_item); //adds item.  
            return _item + " added to inventory"; //Shows the used that the item has been added.
        }
        else {
            return "Pockets full"; ///Lets the user know if their inventory is full.
        }
        
    }

    public boolean hasItem(String _item) { //Checks if the player has a certain item.
        if (_inventory.indexOf(_item) > -1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void pickUpKey() { //Adds key to inventory
        if (_keys < 3) {
            _keys++;
        }
        else {
            System.out.println("ERROR MAXIMUM KEYS ALREADY STORED");
        }
    }

    public boolean hasEnoughKeys() { //Checks if the player has enough keys (3)
        if (_keys == 3) {
            return true;
        }
        else {
            return false;
        }
    }

    public void washHands() { //Add to the counter that keeps track of how many times the player washed their hands.
        this._washHandsCounter++;
    }

    public int showWashHands() { //Gets the ammount of times the player has washed their hands.
        return this._washHandsCounter;
    }

    public String getHealth() { //Gets the player's health
        return "You have " + _health + " health left.";
    }

    //Changes health ammount.
    public void setHealth(int health) {
        this._health = health;
    }

    //Changes armour ammount.
    public void setArmour(int armour) {
        this._armour = armour;
    }

    public String getArmour() { //Gets the player's armour
        return "You have " +  _armour + " armour left";
    }

    //Checks if the player is alive.
    public boolean isAlive() {
        return _health > 0;
    }
    
    //Changes the dialog option the player chose 
    //(this is mainly used to see which scenario the player chose in dialog or combat).
    public void setDialogOption(int dialogOption) { 
        this._dialogOption = dialogOption;
    }

    //Gets the chosen dialog option.
    public int getDialogOption() {
        return _dialogOption;
    }
}