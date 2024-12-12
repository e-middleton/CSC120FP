import java.util.ArrayList;
import java.util.Arrays;

/**
 * NPC class, used for NPCs and is the parent of the Player class
 * They're able to talk, barter, and have inventories and check positions
 */
public class NPC{
    protected String description;
    protected String occupation;
    protected int positionX;
    protected int positionY;
    protected ArrayList<Item> inventory; 
    protected String wants; 
    protected int wantsNum;
    protected int hasNum;

    /**
     * Stub constructor with bare minimum information, used for testing
     * @param description a brief description of the npc, called for when the player looksAround()
     * @param occupation the occupation of the NPC
     * @param positionX the horizontal/row index of their position on the map
     * @param positionY vertical/column index of their position on the map
     */
    public NPC(String description, String occupation, int positionX, int positionY){
        this.description = description;
        this.occupation = occupation;
        this.positionX = positionX;
        this.positionY = positionY;
        this.inventory = new ArrayList<Item>(); //empty
        this.wants = null; //doesn't want anything
    }

    /**
     * Full constructor for npc with every possible attribute
     * @param description a written description of the NPC and their appearance
     * @param occupation the name/occupation of the NPC, used as an ID
     * @param positionX the x or column position of the npc
     * @param positionY the y or row position of the npc
     * @param inventory the arrayList of strings that serves as the NPC's inventory
     * @param wants what the NPC wants to trade for, if wants = gold, the NPC will only trade if given gold
     * @param wantsNum how many of the given object the NPC wants. 
     */
    public NPC(String description, String occupation, int positionX, int positionY, ArrayList<Item> inventory, String wants, int wantsNum){
        this.description = description;
        this.occupation = occupation;
        this.positionX = positionX;
        this.positionY = positionY;
        this.inventory = inventory;
        this.wants = wants;
        this.wantsNum = wantsNum;
        this.hasNum = 0;
    }

    /**
     * Constructor for an NPC with everything except the "want" item
     * @param description a brief description of the NPC
     * @param occupation the name/occupation, used as an ID
     * @param positionX the x or column position in the map
     * @param positionY the y or row position in the map
     * @param inventory the arrayList of objects, Strings, the NPC has
     */
    public NPC(String description, String occupation, int positionX, int positionY, ArrayList<Item> inventory){
        this.description = description;
        this.occupation = occupation;
        this.positionX = positionX;
        this.positionY = positionY;
        this.inventory = inventory;
        this.wants = null;
        this.hasNum = 0;
    }

    //      GETTERS
    //********************


    /**
     * Getter for the npc's description
     * @return description as a string
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * getter for the NPC's occupation, used interchangably as their name
     * @return occupation of the NPC
     */
    public String getOccupation(){
        return this.occupation;
    }

    /**
     * getter for the x coordinate of a NPC's position
     * @return index x of their position
     */
    public int getPositionX(){
        return this.positionX;
    }

    /**
     * getter for the y coordinate of a NPC's position
     * @return index y of their position
     */
    public int getPositionY(){
        return this.positionY;
    }


   /**
    * Returns the NPC's inventory as a string, used to see what they have for bartering, or testing
    * @return the inventory of the NPC as a readable String
    */
    public String getInventory(){
        return this.inventory.toString();
    }

    /**
     * Prints out a list of what is in a npc's inventory
     */
    public void checkInventory(){
        System.out.println(this.inventory.toString());
    }

    /**
     * No longer used, but originally was used to make sure characters only interacted if it was situationally appropriate
     * currently position testing is done primarily in Location class, sometimes in Play() main function
     * @param c the other character this character is seeing if their positions match
     * @return t/f they are in the same location
     */
    public boolean positionMatch(NPC c){
        return ((this.positionX == c.getPositionX()) && (this.positionY == c.getPositionY()));
    }

    /**
     * Getter for the wanted object of an npc
     * @return the String item that the npc wants in exchange for bartering
     */
    public String getWant(){
        return this.wants;
    }

    /**
     * getter for the number of the "wanted" object in exchange for bartering
     * @return the number of the item that the npc wants in order to barter
     */
    public int getWantsNum(){
        return this.wantsNum;
    }

    /**
     * getter for the number of objects an npc already has of their "wanted" item
     * before they barter
     * @return the number of "want" the npc already has
     */
    public int getHasNum(){
        return this.hasNum;
    }

    //      SETTERS
    //********************

     /**
     * Setter for the x_position, 
     * called by location class during construction just to ensure continuity
     * @param x the x or column position of the NPC
     */
    public void setPositionX(int x){
        this.positionX = x;
    }

    /**
     * setter for the y_position of the NPC,
     * called by Location constructors to make sure everything matches
     * @param y the y or row position of the NPC
     */
    public void setPositionY(int y){
        this.positionY = y;
    }

    //      METHODS
    //**********************

    /**
     * Method for getting the intro/set speech of an NPC
     * @param player the player who they're talking to
     */
    public void intro(NPC player){ //exception thrown in Location and handled in Play if mismatched
        System.out.println("Hello traveler, I am the " + this.occupation);
        System.out.println("Lets make a trade!");
        System.out.println("I currently have " + getInventory());
        System.out.println("And I am willing to barter for " + "(" + (this.wantsNum - this.hasNum) + ") " + this.wants);
    }

    /**
     * Puts an object in the npc's inventory, inventory is not allowed to hold more than 15 items.
     * grabbing from an npc is typically only done in bartering
     * @param s the object being picked up.
     * @param npc the NPC holding the object
     */
    public void grab(String s, NPC npc){
        if(this.inventory.size() < 16){
            if(npc.checkInventory(s)){
                this.inventory.add(npc.getItem(s));
            } else{
                System.out.println(s + " is nowhere to be found, it cannot be picked up.");
            }
        } else{
            throw new RuntimeException("Your bag is too heavy. This object cannot be picked up.");
        }
    }

    /**
     * method for grabbing an object from a location, used much more often than grabbing from an npc
     * @param s the name/id of the item being grabbed
     * @param location the location where the item is found
     */
    public void grab(String s, Location location){
        if(this.inventory.size() < 16){
            if(location.containsItem(s)){
                this.inventory.add(location.getItem(s));
            } else{
                System.out.println(s + " is nowhere to be found, it cannot be picked up.");
            }
        } else{
            throw new RuntimeException("Your bag is too heavy. This object cannot be picked up.");
        }
    }

    /**
     * Checks if a npc's inventory contains a given object
     * @param s the name of the Item being searched for
     * @return true or false if the inventory contains the Item
     */
    public boolean checkInventory(String s){
        boolean check = false;
        for(int i = 0; i < this.inventory.size(); i++){
            if(this.inventory.get(i).getName().equals(s)){
                check = true;
            }
        }
        return check;
    }

    /**
     * method for getting an Item from an npc
     * used for bartering or looking at the description of items
     * @param s the name/id of the item being looked for
     * @return the Item object being asked for
     */
    public Item getItem(String s){
        boolean check = false;
        int index = 0;
        for(int i = 0; i < this.inventory.size(); i++){
            if(this.inventory.get(i).getName().equals(s)){
                check = true;
                index = i;
            }
        }
        if(check){
            return this.inventory.get(index);
        } else{
            throw new MissingMaterialException();
        }
    }


    /**
     * Removes an item from a npc's inventory, provided that it was in the inventory originally.
     * @param s the item being dropped
     */
    public void drop(String s){
        if(checkInventory(s)){ //if they have the Item
            for(int i = 0; i < this.inventory.size(); i++){ 
                if(this.inventory.get(i).getName().equals(s)){
                    this.inventory.remove(i); //removes the index where the item is
                }
                //break; //prevents the loss of all of an Item, only drops one
            }
        }else{
            throw new RuntimeException("This object cannot be dropped, it is not in the inventory");
        }
    }


    /**
     * method for bartering (swapping objects around different inventories), first checks to see if bartering is possible based on matching locations, 
     * Then it checks if the offered item is something the npc even wants
     * if the npc wants the item, then it checks that the player has the item it is going to give, and that the npc has the item they're going to give
     * if all these things are true, then the swap takes place and the trade is successful
     * @param trade the item the player wants to get
     * @param payment the payment the player is going to give the npc
     * @param player currently another npc, but eventually is going to be the player, the person asking for something.
     */
    public void barter(String trade, String payment, NPC player){ 
        if(this.wants.equals(payment)){ //trading won't happen if NPC doesn't want what is offered
            try{
                if(takePayment(player, payment)){ //ensure that full payment occurs t/f
                    player.grab(trade, this); //player grabs item they want from the NPC
                    drop(trade); //the npc drops the item they're giving away
                } else{
                    throw new MissingMaterialException(); //either the npc doesn't have the trade the player wants, or the player doesn't have the payment they say they do
                }
            } catch(RuntimeException e){ //Exception thrown by player in drop(payment) when there is not any left
                System.out.println("Payment insufficient. Please find more " + payment + "(s) before continuing to barter.");
            }
        } else {
        System.out.println("The " + this.occupation + " does not want " + payment + " please return to barter later with a different payment type.");
        System.out.println("Ending bartering...");
    } 
    }

    /**
     * Method for taking payment, useful for when multiple of something is requested. 
     * The item is dropped by the player and grabbed by the npc, 
     * who then decrements their required number until the full payment has occurred and the trade is complete
     * @param player the player who initiated the bartering
     * @param payment the string of the object the npc wants in exchange for their good
     * @return t/f if the payment has been completed
     */
    private boolean takePayment(NPC player, String payment){ //never going to be called from outside of the npc themselves
        while(this.wantsNum > this.hasNum){ //while the NPC wants more than they have 
            grab(payment, player); //npc grabs payment
            player.drop(payment); //player drops the payment and it is taken by the npc, THROWS EXCEPTION IF SUFFICIENT PAYMENT IS NOT IN INVENTORY
            this.hasNum += 1;
        }
        if(this.wantsNum == this.hasNum){ //has the payment in full been given
            this.hasNum = 0; //resets to zero because trade has been completed
            return true;
        } else{
            return false; //not going to happen, exception is thrown instead by player in drop(payment) if insufficient payment occurs
        }
    }

    /**
     * Main method in NPC, used for testing
     * @param args empty array of String
     */
    public static void main(String[] args) {
        ArrayList<Item> purse = new ArrayList<Item>();
        Item thing = new Item("nails");
        Item thing2 = new Item("gin");
        purse.add(thing);
        purse.add(thing2);
        ArrayList<Item> bag = new ArrayList<Item>();
        Item thing3 = new Item("gold");
        Item thing4 = new Item("gold");
        Item thing5 = new Item("sock yarn");
        Item thing6 = new Item("bobbin");
        bag.add(thing3);
        bag.add(thing4);
        bag.add(thing5);
        bag.add(thing6);
        NPC smith = new NPC("A traveling smith looking to shod horses", "blacksmith", 0,0, bag, "flour", 1);
        NPC baker = new NPC("A worker in a small northern town", "baker", 0, 0, purse, "gold", 1);
        ArrayList<Item> spinnerSupplies = new ArrayList<Item>(Arrays.asList(new Item("roving"), new Item("beeswax"), new Item("flax"), new Item("sock yarn")));
        NPC spinner = new NPC("A young woman, sitting at a spinning wheel gently twisting strands of flax as she feeds them into the flyer", "spinner", 0, 1, spinnerSupplies, "bobbin", 1);

        spinner.barter("sock yarn", "bobbin", smith);

        // smith.checkInventory();
        // baker.checkInventory();
        smith.checkInventory();
        baker.barter("nails", "gold", smith);
        smith.checkInventory();
    }
    

}