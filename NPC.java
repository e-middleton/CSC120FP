import java.util.ArrayList;
import java.util.Arrays;

/**
 * NPC class, used for NPC's and is the parent of the Player class
 */
public class NPC{
    protected String description;
    protected String occupation;
    protected int position_x;
    protected int position_y;
    protected ArrayList<String> inventory; //change to type ITEM when class has been created
    protected String wants; //change to type ITEM
    protected int wantsNum;
    protected int hasNum;

    /**
     * Constructor with everything except the array lists of wishes, and inventory
     * @param description a brief description of the npc, called for when the player looksAround()
     * @param occupation the occupation of the NPC
     * @param position_x the horizontal/row index of their position on the map
     * @param position_y vertical/column index of their position on the map
     */
    public NPC(String description, String occupation, int position_x, int position_y){
        this.description = description;
        this.occupation = occupation;
        this.position_x = position_x;
        this.position_y = position_y;
        this.inventory = new ArrayList<String>(); //empty
        this.wants = null; //doesn't want anything
    }

    /**
     * Full constructor for npc with every possible attribute
     * @param description a written description of the NPC and their appearance
     * @param occupation the name/occupation of the NPC, used as an ID
     * @param position_x the x or column position of the npc
     * @param position_y the y or row position of the npc
     * @param inventory the arrayList of strings that serves as the NPC's inventory
     * @param wants what the NPC wants to trade for, if wants = gold, the NPC will only trade if given gold
     * @param wantsNum how many of the given object the NPC wants. 
     * @param hasNum how many of the item they have, used to keep track of payment
     */
    public NPC(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory, String wants, int wantsNum){
        this.description = description;
        this.occupation = occupation;
        this.position_x = position_x;
        this.position_y = position_y;
        this.inventory = inventory;
        this.wants = wants;
        this.wantsNum = wantsNum;
        this.hasNum = 0;
    }

    public NPC(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory){
        this.description = description;
        this.occupation = occupation;
        this.position_x = position_x;
        this.position_y = position_y;
        this.inventory = inventory;
        this.wants = null;
    }



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
    public int getPosition_x(){
        return this.position_x;
    }

    /**
     * getter for the y coordinate of a NPC's position
     * @return index y of their position
     */
    public int getPosition_y(){
        return this.position_y;
    }

    public void setPosition_x(int x){
        this.position_x = x;
    }

    public void setPosition_y(int y){
        this.position_y = y;
    }


    //return a NPC's inventory as a String
    public String getInventory(){
        return this.inventory.toString();
    }


    //checks if characters are in the same location
    public boolean positionMatch(NPC c){
        if ((this.position_x == c.getPosition_x()) && (this.position_y == c.getPosition_y())){
            return true;
        } else{
            return false; //other methods will throw exception
        }
    }


    /**
     * Method for getting the intro/set speech of an NPC
     * @param player the player who they're talking to
     */
    public void intro(NPC player){ //exception thrown in Location and handled in Play if mismatched
        System.out.println("Hello traveler, I am the " + this.occupation);
        System.out.println("Would you like to barter?");
        System.out.println("Currently I have " + getInventory());
        System.out.println("And I am willing to barter for " + "(" + this.wantsNum + ") " + this.wants);
    }

    /**
     * Puts an object in the npc's inventory, inventory is not allowed to hold more than 15 items.
     * @param s the object being picked up.
     */
    public void grab(String s){
        if(this.inventory.size() < 16){
            this.inventory.add(s);
        } else{
            throw new RuntimeException("Your bag is too heavy. This object cannot be picked up.");
        }
    }

    /**
     * Checks if a npc's inventory contains a given object
     * @param s the object being searched for
     * @return true or false if the inventory contains the object
     */
    public boolean checkInventory(String s){
        if(this.inventory.contains(s)){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Prints out a list of what is in a npc's inventory
     */
    public void checkInventory(){
        System.out.println(this.inventory.toString());
    }

    /**
     * Removes an item from a npc's inventory, provided that it was in the inventory originally.
     * @param s the item being dropped
     */
    public void drop(String s){
        if(this.inventory.contains(s)){
            this.inventory.remove(s);
        } else{
            throw new RuntimeException("This object cannot be dropped, it is not in the inventory");
        }
    }


    //bartering objects, one for another, the first is what the player wants, the second is what the npc is taking as payment
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
        if(this.wants.equals(payment)){
            if(this.inventory.contains(trade) && player.checkInventory(payment)){ //the npc has the right object, and the player's inventory has the payment
                try{
                    if(takePayment(player, payment)){ //ensure that full payment occurs
                        drop(trade); //the npc drops the item they're giving away
                        player.grab(trade); //player grabs item they want
                        System.out.println("A successful trade of " + payment +"(s) for " + trade + "!");
                    }
                } catch(RuntimeException e){
                    System.out.println("Payment insufficient. Please find more " + payment + "(s) before continuing to barter.");
                }
            } else{
                throw new RuntimeException("Those items cannot be bartered, one or both is not in the inventory.");
            }
        } else{
            System.out.println("The " + this.occupation + " does not want " + payment + " please choose another thing to trade");
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
        while(this.wantsNum > this.hasNum){ //while they want more than they have 
            player.drop(payment); //player drops the payment and it is taken by the npc
            grab(payment); //grabs payment
            this.hasNum += 1;
        }
        if(this.wantsNum == this.hasNum){ //has the payment in full been given
            this.hasNum = 0; //resets to zero because trade has been completed
            return true;
        } else{
            return false;
        }
    }


    /**
     * Main method in NPC, used for testing
     * @param args empty array of String
     */
    public static void main(String[] args) {
        ArrayList<String> purse = new ArrayList<String>();
        purse.add("nails");
        purse.add("gin");
        ArrayList<String> bag = new ArrayList<String>();
        bag.add("gold");
        bag.add("gold");
        bag.add("sock yarn");
        bag.add("bobbin");
        NPC smith = new NPC("A traveling smith looking to shod horses", "blacksmith", 0,0, bag, "flour", 1);
        NPC baker = new NPC("A worker in a small northern town", "baker", 0, 0, purse, "gold", 2);
        ArrayList<String> spinnerSupplies = new ArrayList<String>(Arrays.asList("roving", "beeswax", "flax", "sock yarn"));
        NPC spinner = new NPC("A young woman, sitting at a spinning wheel gently twisting strands of flax as she feeds them into the flyer", "spinner", 0, 1, spinnerSupplies, "bobbin", 1);

        spinner.barter("sock yarn", "bobbin", smith);

        // smith.checkInventory();
        // baker.checkInventory();

        baker.barter("nails", "gold", smith);
        smith.checkInventory();
    
        
    }
    

}