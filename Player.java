import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays; 

public class Player extends Character{
    private Hashtable<String, Boolean> outfit; //change to item class later

    public Player(String description, String occupation, int position_x, int position_y){
        super(description, occupation, position_x, position_y);
        this.outfit = new Hashtable<String, Boolean>(); //winter outfit begins empty, or should it begin full + false?
        this.inventory = new ArrayList<String>(); //empty inventory if not specified
    }

    public Player(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory){
        super(description, occupation, position_x, position_y, inventory);
        this.outfit = new Hashtable<String, Boolean>();
    }

    //mini function for checking position for testing
    public void whereAmI(){
        System.out.println("I am at " + this.position_x + ", " + this.position_y);
    }

    //negative numbers for walking backwards(up) or left
    //checks to make sure they're not walking off the map before they walk
    //the map is a 5x5 grid currently
    //only 1 and -1 will be passed in for x and y because no jumping
    public void walk(int x, int y){
        if((this.position_x + x) >= 0 && ((this.position_x + x) < 5)){
            this.position_x += x;
        } else{
            throw new InvalidLocationException();
        }
        if((this.position_y + y) >= 0 && (this.position_y + y) < 5){
            this.position_y += y;
        } else{
            throw new InvalidLocationException();
        }
    }

    public boolean knitSocks(){
        if(this.inventory.contains("sock yarn")){
            drop("sock yarn");
            drop("sock yarn");
            outfit.put("socks", true);
            return true;

        } else{
            return false;
        }
    }

    public static void main(String[] args){
        ArrayList<String> purse = new ArrayList<String>();
        purse.add("sock yarn");
        purse.add("flour");
        purse.add("sock yarn");
        Player dorothy = new Player("n/a", "player", 0, 0, purse);
        ArrayList<String> bag = new ArrayList<String>();
        bag.add("needles");
        bag.add("sock yarn");
        bag.add("wool");
        Character smith = new Character("A traveling smith looking to shod horses", "blacksmith", 0,0, bag, "flour");
        smith.barter("wool", "flour", dorothy);

        System.out.println();
        System.out.println(dorothy.knitSocks());
    }
    
}
