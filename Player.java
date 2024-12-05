import java.util.Hashtable;
import java.util.ArrayList;

/**
 * Class for the player/protagonist of the game. Inherits from NPC and is able to walk around, knit, talk to people, look around
 */
public class Player extends NPC{
    private Hashtable<String, Boolean> outfit; 
    private Hashtable<String, Integer> workbag; //separate inventory for yarn because their quantity is relevant

    /**
     * Default constructor for player, sets their location to home, their name as Dorothy, and their inventory/workbag begins empty
     */
    public Player(){
        super("n/a", "Dorothy", 1, 2);
        this.outfit = new Hashtable<String, Boolean>(); //winter outfit (goal)
        this.workbag = new Hashtable<String, Integer>(); //empty yarn holder

        this.outfit.put("hat", false);
        this.outfit.put("gloves", false);
        this.outfit.put("pants", false);
        this.outfit.put("sweater", false);
        this.outfit.put("socks", false);

        //initialize values in workbag (possible yarn types = "lace weight", "dk weight", "worsted weight" "bulky weight")
        this.workbag.put("lace weight", 0);
        this.workbag.put("dk weight", 0);
        this.workbag.put("worsted weight", 0);
        this.workbag.put("bulky weight", 0);
    }

    /**
     * Constructor for player with specific attributes
     * @param description the description of the player
     * @param occupation the name/occupation of the player
     * @param position_x the starting x or column position in the map
     * @param position_y the starting y or row position in the map, their inventory begins empty
     */
    public Player(String description, String occupation, int position_x, int position_y){
        super(description, occupation, position_x, position_y);
        this.inventory = new ArrayList<String>(); //empty inventory if not specified
        this.outfit = new Hashtable<String, Boolean>(); //winter outfit (goal)
        this.workbag = new Hashtable<String, Integer>(); //empty yarn holder
         

        //winter outfit begins with all clothing items (only possible ones) as false
        this.outfit.put("hat", false);
        this.outfit.put("gloves", false);
        this.outfit.put("pants", false);
        this.outfit.put("sweater", false);
        this.outfit.put("socks", false);

        //initialize values in workbag (possible yarn types = "lace weight", "dk weight", "worsted weight" "bulky weight")
        this.workbag.put("lace weight", 0);
        this.workbag.put("dk weight", 0);
        this.workbag.put("worsted weight", 0);
        this.workbag.put("bulky weight", 0);


    }

    /**
     * Constructor for player with a predetermined inventory created at initialization
     * @param description a description of the player, not super important but inherited from NPC
     * @param occupation typically the player's name
     * @param position_x the x or column index of the player's position
     * @param position_y the y or row index of the player's position
     * @param inventory an arrayList of things the player has
     * initializes an empty outfit and workbag
     */
    public Player(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory){
        super(description, occupation, position_x, position_y, inventory);
        this.outfit = new Hashtable<String, Boolean>();
        this.workbag = new Hashtable<String, Integer>(); 

        this.outfit.put("hat", false);
        this.outfit.put("gloves", false);
        this.outfit.put("pants", false);
        this.outfit.put("sweater", false);
        this.outfit.put("socks", false);

        this.workbag.put("lace weight", 0);
        this.workbag.put("dk weight", 0);
        this.workbag.put("worsted weight", 0);
        this.workbag.put("bulky weight", 0);
    }

    /**
     * Mini method for testing where a player is
     */
    public void whereAmI(){
        System.out.println("I am at column " + this.position_x + ", and row " + this.position_y);
    }

    /**
     * Method for returning whether or not the player has any yarn at all
     * @return t/f the player has yarn
     */
    public boolean hasYarn(){
        boolean check = false;
        if(this.workbag.get("lace weight") >= 1){
            check = true;
        } else if(this.workbag.get("dk weight") >= 1){
            check = true;
        } else if(this.workbag.get("worsted weight") >= 1){
            check = true;
        } else if(this.workbag.get("bulky weight") >= 1){
            check = true;
        }
       return check;
    }

    /**
     * getter for outfit
     * @return string version of outfit
     */
    public String getOutfit(){
        return this.outfit.toString();
    }

    /**
     * Only able to be called by the moth
     * it finds a yarn category that contins yarn, "eats" the yarn
     * and tells you which type of yarn was eaten
     * @return String name of the yarn that was eaten
     */
    public String eatYarn(){
        String yarn = "";
        if(this.workbag.get("lace weight") >= 1){
            yarn += "lace weight yarn";
            this.workbag.replace("lace weight", 0); //yarn is eaten up
        } else if(this.workbag.get("dk weight") >= 1){
            yarn += "dk weight yarn";
            this.workbag.replace("dk weight", 0);
        } else if(this.workbag.get("worsted weight") >= 1){
            yarn += "worsted weight yarn";
            this.workbag.replace("worsted weight", 0);
        } else if(this.workbag.get("bulky weight") >= 1){
            yarn += "bulky weight yarn";
            this.workbag.replace("bulky weight", 0);
        }
        return yarn;
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

    
    /**
     * Method for walking around, called by walk methods in the Map class if permitted,
     * negative numbers for south or west movements
     * only 1 or -1 may be passed in for walking because no jumping is permitted
     * @param y the row up or down they walk
     * @param x the column left or right they are walking
     */
    public void walk(int y, int x){
        if(x > 1 || x < -1){
            throw new InvalidMovementException();
        }
        if(y > 1 || y < -1){
            throw new InvalidMovementException();
        }
        this.position_x += x; 
        this.position_y += y;
    }

    /**
     * Puts an object in the npc's inventory, inventory is not allowed to hold more than 15 items.
     * @param s the object being picked up.
     */
    public void grab(String s){
        if(!s.contains("yarn")){
            super.grab(s);
            System.out.println(s + " has been added to your inventory.");
        } else{
            grabYarn(s);
        }
    }

    /**
     * Method for adding yarn to the yarn inventory (workbag), checks to make sure that it's a recognized type of yarn
     * then increments the number of balls of yarn in that category however many is required for the garment (idk why I did this)
     * @param s the type of yarn being added
     */
    public void grabYarn(String s){
        String yarnWeight = s.substring(0, s.lastIndexOf("yarn")) + "weight";
        if(s.substring(0, s.lastIndexOf("yarn")).equals("lace ")){
            if(this.workbag.containsKey(yarnWeight)){
                this.workbag.replace(yarnWeight, ((this.workbag.get(yarnWeight)) + 2)); //increments the value by two for a pair of socks/gloves
                System.out.println(s + " is now in your workbag.");
            } 
        } else if(s.substring(0, s.lastIndexOf("yarn")).equals("worsted ")){
            if(this.workbag.containsKey(yarnWeight)){
                this.workbag.replace(yarnWeight, ((this.workbag.get(yarnWeight)) + 7)); //increments the value by seven for pants
                System.out.println(s + " is now in your workbag.");
            } 
        } else if(s.substring(0, s.lastIndexOf("yarn")).equals("dk ")){ //increments by one for hat
            if(this.workbag.containsKey(yarnWeight)){
                this.workbag.replace(yarnWeight, ((this.workbag.get(yarnWeight)) + 1)); //increments the value by seven for pants
                System.out.println(s + " is now in your workbag.");
            } 
        } else if(s.substring(0, s.lastIndexOf("yarn")).equals("bulky ")){
            if(this.workbag.containsKey(yarnWeight)){
                this.workbag.replace(yarnWeight, ((this.workbag.get(yarnWeight)) + 5)); //increments the value by five for a sweater
                System.out.println(s + " is now in your workbag.");
            } 
        } else{
            throw new RuntimeException("this is not a type of yarn that you know. It cannot be added to your workbag.");
        }
    }

    /**
     * Method for giving options for what a player is able to knit based on the number of balls of yarn they have
     */
    public void canKnit(){
        if(this.workbag.get("lace weight") >= 2){
            System.out.println("You can knit gloves or socks. You have " + this.workbag.get("lace weight") + " ball(s) of lace weight yarn.");
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
        if((this.workbag.get("lace weight") < 2) && (this.workbag.get("dk weight") < 1) && (this.workbag.get("worsted weight") < 7) && (this.workbag.get("bulky weight") < 5)){
            System.out.println("There is nothing you can currently knit. Your workbag currently contains " + this.workbag.toString() + " ball(s) of yarn.");
        }
    }

    /**
     * Method for knitting socks provided that there is sufficient yarn in their workbag. 
     */
    public void knitSocks(){
        if(this.workbag.get("lace weight") >= 2){
            this.workbag.replace("lace weight", (workbag.get("lace weight") - 2)); //decrememnt number of balls of yarn by 2
            this.outfit.replace("socks", true);
            System.out.println("A pair of socks has been added to your outfit!");
            
        } else{
            throw new MissingMaterialException();
        }
    }

    /**
     * Method for knitting a hat and adding it to the outfit provided there is sufficient yarn
     */
    public void knitHat(){
        if(this.workbag.get("dk weight")>= 1){
            this.workbag.replace("dk weight", this.workbag.get("dk weight") - 1); //decrement by 1 for hat
            this.outfit.replace("hat", true);
            System.out.println("A hat has been added to your outfit!");
        } else{
            throw new MissingMaterialException();
        }
    }

    /**
     * Method for knitting gloves and adding it to the outfit, provided there is sufficient yarn.
     */
    public void knitGloves(){
        if(this.workbag.get("lace weight")>=2){
            this.workbag.replace("lace weight", this.workbag.get("lace weight") - 2); //decrement by 2 for gloves
            this.outfit.replace("gloves", true);
            System.out.println("A pair of gloves has been added to your outfit!");
        } else{
            throw new MissingMaterialException();
        }
    }

    /**
     * Method for knitting a sweater and adding it to the outfit, provided there is sufficient yarn
     */
    public void knitSweater(){
        if(this.workbag.get("bulky weight") >= 5){
            this.workbag.replace("bulky weight", this.workbag.get("bulky weight") - 5); //decrement by 5
            this.outfit.replace("sweater", true);
            System.out.println("A sweater has been added to your outfit!");
        } else{
            throw new MissingMaterialException();
        }
    }

    /**
     * Method for knitting pants and adding it to the outfit, provided there is sufficient yarn.
     */
    public void knitPants(){
        if(this.workbag.get("worsted weight") >= 7){
            this.workbag.replace("worsted weight", this.workbag.get("worsted weight") - 7);
            this.outfit.replace("pants", true);
            System.out.println("A pair of pants has been added to your outfit!");
        } else{
            throw new MissingMaterialException();
        }
    }

    /**
     * Method for checking the win condition of the game, if all the items of clothing have been knit and added to outfit.
     * @return true/false, all the clothing has been knit.
     */
    public boolean hasWon(){
        if(this.outfit.get("hat") && this.outfit.get("gloves") && this.outfit.get("pants") && this.outfit.get("sweater") && this.outfit.get("socks")){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Whether or not socks have already been knitted
     * @return t/f the player already has socks
     */
    public boolean hasSocks(){
        return this.outfit.get("socks");
    }

    /**
     * Whether or not gloves have already been knitted
     * @return t/f the player has gloves
     */
    public boolean hasGloves(){
        return this.outfit.get("gloves");
    }

    /**
     * whether or not a sweater has already been knitted
     * @return t/f the player has a sweater
     */
    public boolean hasSweater(){
        return this.outfit.get("sweater");
    }

    /**
     * Whether or not pants have already been knitted
     * @return t/f the player has pants
     */
    public boolean hasPants(){
        return this.outfit.get("pants");
    }

    /**
     * Whether or not a hat has already been knitten
     * @return t/f the player has a hat
     */
    public boolean hasHat(){
        return this.outfit.get("hat");
    }

    /**
     * Main method, used for testing
     * @param args empty array of Strings
     */
    public static void main(String[] args){
        ArrayList<String> purse = new ArrayList<String>();
        purse.add("flour");
        Player dorothy = new Player("n/a", "Dorothy", 0, 0, purse);
        dorothy.grabYarn("lace weight");
        dorothy.grabYarn("lace weight");
        // ArrayList<String> bag = new ArrayList<String>();
        // bag.add("needles");
        // bag.add("sock yarn");
        // bag.add("wool");
        //NPC smith = new NPC("A traveling smith looking to shod horses", "blacksmith", 0,0, bag, "flour");
        //smith.barter("wool", "flour", dorothy);

        System.out.println();
        dorothy.canKnit();
        dorothy.knitGloves();
        dorothy.showOutfit();

    }
    
}
