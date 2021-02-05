import java.util.Random;
import java.util.ArrayList;

public class enemy {
    String _name = "";
    int _health = 100;
    int _armour = 0;
    int _damage = 20;
    int _damageMultiplier = 2;

    int _attacksDone = 0;

    ArrayList<String> _actions = new ArrayList<String>(); //Keep track of actions the player has done

    public enemy(String name, int health, int armour, int damage, int damageMultiplier){
        this._name = name;
        this._health = health;
        this._armour = armour;
        this._damage = damage;
        this._damageMultiplier = damageMultiplier;
    }


    public int damageDone(int _percentageChance) { //calculates how much damage the enemy will do
        Random RandomNum = new Random();
        int _randomNumber = RandomNum.nextInt(100);
        if (_randomNumber < _percentageChance) {
            this._damage = this._damage * _damageMultiplier;
        }
        return this._damage;
    }

    public void takeDamage(int _damageDone) { //Makes the enemy receive damage
        if (_damage > this._armour) {
            int _remainingDamage = _damage - this._armour;
            this._armour = this._armour - (_damage - _remainingDamage);
            this._health -= _remainingDamage;
        }
        else {
            this._armour -= _damage;
        }
    }

    public void setHealth(int health) { //Changes enemy's health
        this._health = health;
    }

    public int getHealth() { //Checks the enemy's health
        return this._health;
    }

    public void setArmour(int armour) { //Changes armour ammount.
        this._armour = armour;
    }

    public String getArmour() { //Gets the armour ammount
        return "Enemy has " +  _armour + " armour left";
    }

    public boolean isAlive() { //Checks if the enemy is alive (health over 0)
        if (this.getHealth() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int addAttack() { //Adds to the ammount of times the enemy did an attack
        return _attacksDone++;
    }

    public int getAttacksDone() { //Checks how many time the enemy has attacked
        return _attacksDone;
    }

    public void addAction(String _action) { //Records an action the enemy has done
        if (_actions.contains(_action) == false) {
            _actions.add(_action);
        }
    }

    public boolean doneAction(String _action) { //Check if the enemy has done a certain action
        if (_actions.contains(_action)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void removeAction(String _action) { //Removes an action the enemy has done from their record
        _actions.remove(_action);
    }
}