import java.util.Hashtable;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class for the player/protagonist of the game. Inherits from Character and is able to walk around, knit, talk to people, look around
 */
public class Player extends Character{
    private Hashtable<String, Boolean> outfit; //change to item class later
    private HashMap<String, Integer> workbag; //separate inventory for yarn because idk how else

    /**
     * Auto constructor 
     */
    public Player(){
        super("n/a", "Dorothy", 0, 0);
        this.outfit = new Hashtable<String, Boolean>(); //winter outfit (goal)
        this.workbag = new HashMap<String, Integer>(6); //empty yarn holder

        this.outfit.put("hat", false);
        this.outfit.put("gloves", false);
        this.outfit.put("pants", false);
        this.outfit.put("sweater", false);
        this.outfit.put("socks", false);

        //initialize values in workbag (possible yarn types = "fingering weight", "dk weight", "worsted weight" "bulky weight")
        this.workbag.put("fingering weight", 0);
        this.workbag.put("dk weight", 0);
        this.workbag.put("worsted weight", 0);
        this.workbag.put("bulky weight", 0);
    }

    /**
     * Constructor for Player that takes the usual attributes as well as initializes an empty outfit, inventory, and workbag
     */
    public Player(String description, String occupation, int position_x, int position_y){
        super(description, occupation, position_x, position_y);
        this.inventory = new ArrayList<String>(); //empty inventory if not specified
        this.outfit = new Hashtable<String, Boolean>(); //winter outfit (goal)
        this.workbag = new HashMap<String, Integer>(6); //empty yarn holder
         

        //winter outfit begins with all clothing items (only possible ones) as false
        this.outfit.put("hat", false);
        this.outfit.put("gloves", false);
        this.outfit.put("pants", false);
        this.outfit.put("sweater", false);
        this.outfit.put("socks", false);

        //initialize values in workbag (possible yarn types = "fingering weight", "dk weight", "worsted weight" "bulky weight")
        this.workbag.put("fingering weight", 0);
        this.workbag.put("dk weight", 0);
        this.workbag.put("worsted weight", 0);
        this.workbag.put("bulky weight", 0);


    }

    /**
     * Constructor for player with a predetermined inventory created at initialization
     * @param description a description of the player, not super important but inherited from Character
     * @param occupation typically the player's name
     * @param position_x the x index of the player's position
     * @param position_y the y index of the player's position
     * @param inventory an arrayList of things the player has
     * initializes an empty outfit and workbag
     */
    public Player(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory){
        super(description, occupation, position_x, position_y, inventory);
        this.outfit = new Hashtable<String, Boolean>();
        this.workbag = new HashMap<String, Integer>(6); //because initial load capacity is 0.75

        this.outfit.put("hat", false);
        this.outfit.put("gloves", false);
        this.outfit.put("pants", false);
        this.outfit.put("sweater", false);
        this.outfit.put("socks", false);

        this.workbag.put("fingering weight", 0);
        this.workbag.put("dk weight", 0);
        this.workbag.put("worsted weight", 0);
        this.workbag.put("bulky weight", 0);
    }

    //mini function for checking position for testing
    public void whereAmI(){
        System.out.println("I am at " + this.position_x + ", " + this.position_y);
    }

    /**
     * Method for printing out which of the items of winter clothing a player has, based upon the value t/f in the key value pair of outfit
     */
    public void showOutfit(){
        System.out.println(this.occupation + " currently has the following items of winter clothing: ");
        if(this.outfit.get("hat")){
            System.out.println("+hat");
        }
        if(this.outfit.get("gloves")){
            System.out.println("+gloves");
        }
        if(this.outfit.get("pants")){
            System.out.println("+pants");
        }
        if(this.outfit.get("sweater")){
            System.out.println("+sweater");
        }
        if(this.outfit.get("socks")){
            System.out.println("+socks");
        }
        
    }

    //negative numbers for walking backwards(up) or left
    //checks to make sure they're not walking off the map before they walk
    //the map is a 5x5 grid currently
    //only 1 and -1 will be passed in for x and y because no jumping around the map is allowed. Only sequential movements.
    public void walk(int y, int x){
        if(x > 1 || x < -1){
            throw new InvalidMovementException();
        }
        if(y > 1 || y < -1){
            throw new InvalidMovementException();
        }
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

    /**
     * Method for adding yarn to the yarn inventory (workbag), checks to make sure that it's a recognized type of yarn
     * then increments the number of balls of yarn in that category by one
     * @param s the type of yarn being added
     */
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
            this.workbag.replace("fingering weight", (workbag.get("fingering weight") - 2)); //decrememnt number of balls of yarn by 2
            this.outfit.replace("socks", true);
            System.out.println("You now have a pair of socks!");
            
        } else{
            throw new RuntimeException("You have insufficient materials for this action.");
        }
    }

    /**
     * Method for knitting a hat and adding it to the outfit provided there is sufficient yarn
     */
    public void knitHat(){
        if(this.workbag.get("dk weight")>= 1){
            this.workbag.replace("dk weight", this.workbag.get("dk weight") - 1); //decrement by 1 for hat
            this.outfit.replace("hat", true);
            System.out.println("You now have a hat!");
        } else{
            throw new RuntimeException("You have insufficient materials for this action.");
        }
    }

    /**
     * Method for knitting gloves and adding it to the outfit, provided there is sufficient yarn.
     */
    public void knitGloves(){
        if(this.workbag.get("fingering weight")>=2){
            this.workbag.replace("fingering weight", this.workbag.get("fingering weight") - 2); //decrement by 2 for gloves
            this.outfit.replace("gloves", true);
            System.out.println("You now have gloves");
        } else{
            throw new RuntimeException("You have insufficient materials for this action.");
        }
    }

    /**
     * Method for knitting a sweater and adding it to the outfit, provided there is sufficient yarn
     */
    public void knitSweater(){
        if(this.workbag.get("bulky weight") >= 5){
            this.workbag.replace("bulky weight", this.workbag.get("bulky weight") - 5); //decrement by 5
            this.outfit.replace("sweater", true);
            System.out.println("You now have a sweater");
        } else{
            throw new RuntimeException("You have insufficient materials for this action.");
        }
    }

    /**
     * Method for knitting pants and adding it to the outfit, provided there is sufficient yarn.
     */
    public void knitPants(){
        if(this.workbag.get("worsted weight") >= 7){
            this.workbag.replace("worsted weight", this.workbag.get("worsted weight") - 7);
            this.outfit.replace("pants", true);
            System.out.println("You now have a pair of pants.");
        } else{
            throw new RuntimeException("You have insufficient materials for this action.");
        }
    }

    public static void main(String[] args){
        ArrayList<String> purse = new ArrayList<String>();
        purse.add("flour");
        Player dorothy = new Player("n/a", "Dorothy", 0, 0, purse);
        dorothy.grabYarn("fingering weight");
        dorothy.grabYarn("fingering weight");
        // ArrayList<String> bag = new ArrayList<String>();
        // bag.add("needles");
        // bag.add("sock yarn");
        // bag.add("wool");
        //Character smith = new Character("A traveling smith looking to shod horses", "blacksmith", 0,0, bag, "flour");
        //smith.barter("wool", "flour", dorothy);

        System.out.println();
        dorothy.canKnit();
        dorothy.knitGloves();
        dorothy.showOutfit();

    }
    
}
