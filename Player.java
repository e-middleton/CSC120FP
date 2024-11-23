import java.util.Hashtable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays; 

public class Player extends Character{
    private Hashtable<String, Boolean> outfit; //change to item class later
    private HashMap<String, Integer> workbag; //separate inventory for yarn because idk how else

    public Player(String description, String occupation, int position_x, int position_y){
        super(description, occupation, position_x, position_y);
        this.outfit = new Hashtable<String, Boolean>(); //winter outfit begins empty, or should it begin full + false?
        this.inventory = new ArrayList<String>(); //empty inventory if not specified
        this.workbag = new HashMap<String, Integer>(6); //empty yarn holder

        //initialize values in workbag (possible yarn types = "fingering weight", "dk weight", "worsted weight" "bulky weight")
        this.workbag.put("fingering weight", 0);
        this.workbag.put("dk weight", 0);
        this.workbag.put("worsted weight", 0);
        this.workbag.put("bulky weight", 0);


    }

    public Player(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory){
        super(description, occupation, position_x, position_y, inventory);
        this.outfit = new Hashtable<String, Boolean>();
        this.workbag = new HashMap<String, Integer>(6); //because initial load capacity is 0.75

        this.workbag.put("fingering weight", 0);
        this.workbag.put("dk weight", 0);
        this.workbag.put("worsted weight", 0);
        this.workbag.put("bulky weight", 0);
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

    //need to add a check later to make sure the class is yarn
    public void grabYarn(String s){
        if(this.workbag.containsKey(s)){
            this.workbag.replace(s, ((this.workbag.get(s)) + 1)); //increments the value by one
        } else{
            throw new RuntimeException("this is not a type of yarn that you know. It cannot be added to your workbag.");
        }
    }

    /**
     * Method for giving options for what a player is able to knit based on the number of balls of yarn they have
     */
    public void canKnit(){
        if(this.workbag.get("fingering weight") >= 2){
            System.out.println("You can knit gloves or socks. You have " + this.workbag.get("fingering weight") + " ball(s) of fingering weight yarn.");
        }
        if(this.workbag.get("dk weight") >= 1){
            System.out.println("You can knit a hat. You have " + this.workbag.get("dk weight") + " ball(s) of dk weight yarn.");
        }
        if(this.workbag.get("worsted weight") >= 7){
            System.out.println("You can knit a pair of pants. You have " + this.workbag.get("worsted weight") + " ball(s) of worsted weight yarn.");
        }
        if(this.workbag.get("bulky weight") >= 5){
            System.out.println("You can knit a sweater. You have " + this.workbag.get("bulky weight") + " ball(s) of bulky weight yarn.");
        }
        //good lord clean this up
        if((this.workbag.get("fingering weight") < 2) && (this.workbag.get("dk weight") < 1) && (this.workbag.get("worsted weight") < 7) && (this.workbag.get("bulky weight") < 5)){
            System.out.println("There is nothing you can currently knit. Your workbag currently contains " + this.workbag.toString() + " ball(s) of yarn.");
        }
    }

    /**
     * Method for knitting socks provided that there is sufficient yarn in their workbag. 
     */
    public void knitSocks(){
        if(this.workbag.get("fingering weight") >= 2){
            workbag.replace("fingering weight", (workbag.get("fingering weight") - 2)); //decrememnt number of balls of yarn by 2
            outfit.put("socks", true);
            System.out.println("You now have a pair of socks!");
            
        } else{
            throw new RuntimeException("You have insufficient materials for this action.");
        }
    }

    // public void knitHat(){
    //     if(this.workbag.size()>= 1){
    //         workbag.remove("yarn");
    //         outfit.put("hat", true);
    //         System.out.println("You now have a hat!");
    //     } else{
    //         throw new RuntimeException("You have insufficient materials for this action.");
    //     }
    // }

    // public void knitGloves(){
    //     if(this.workbag.size()>=2){
    //         workbag.remove("yarn");
    //         workbag.remove("yarn");
    //     }
    // }

    public static void main(String[] args){
        ArrayList<String> purse = new ArrayList<String>();
        purse.add("flour");
        Player dorothy = new Player("n/a", "player", 0, 0, purse);
        dorothy.grabYarn("fingering weight");
        dorothy.grabYarn("fingering weight");
        ArrayList<String> bag = new ArrayList<String>();
        bag.add("needles");
        bag.add("sock yarn");
        bag.add("wool");
        Character smith = new Character("A traveling smith looking to shod horses", "blacksmith", 0,0, bag, "flour");
        //smith.barter("wool", "flour", dorothy);

        System.out.println();
        dorothy.canKnit();
        dorothy.knitSocks();
        dorothy.canKnit();
    }
    
}
