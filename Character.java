import java.util.ArrayList;

/**
 * Character class, used for NPC's and is the parent of the Player class
 */
public class Character{
    protected String description;
    protected String occupation;
    protected int position_x;
    protected int position_y;
    protected ArrayList<String> inventory; //change to type ITEM when class has been created
    protected String wants; //change to type ITEM

    /**
     * Constructor with everything except the array lists of wishes, and inventory
     * @param description // a brief description of the character, called for when the player looksAround()
     * @param occupation //the occupation of the NPC
     * @param position_x //the horizontal/row index of their position on the map
     * @param position_y //vertical/column index of their position on the map
     */
    public Character(String description, String occupation, int position_x, int position_y){
        this.description = description;
        this.occupation = occupation;
        this.position_x = position_x;
        this.position_y = position_y;
        this.inventory = new ArrayList<String>(); //empty
        this.wants = null; //doesn't want anything
    }

    public Character(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory, String wants){
        this.description = description;
        this.occupation = occupation;
        this.position_x = position_x;
        this.position_y = position_y;
        this.inventory = inventory;
        this.wants = wants;
    }

    public Character(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory){
        this.description = description;
        this.occupation = occupation;
        this.position_x = position_x;
        this.position_y = position_y;
        this.inventory = inventory;
        this.wants = null;
    }



    /**
     * Getter for the character's description
     * @return description as a string
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * getter for the character's occupation, used interchangably as their name
     * @return occupation of the character
     */
    public String getOccupation(){
        return this.occupation;
    }

    /**
     * getter for the x coordinate of a character's position
     * @return index x of their position
     */
    public int getPosition_x(){
        return this.position_x;
    }

    /**
     * getter for the y coordinate of a character's position
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


    /**
     * prints out the character's wants, aka, what they are willing to trade for
     */
    public void getWants(){
        System.out.println("the " + this.occupation + " dreams of owning \n" + this.wants.toString());
    }

    //return a character's inventory as a String
    public String getInventory(){
        return this.inventory.toString();
    }


    //checks if characters are in the same location
    public boolean positionMatch(Character c){
        if ((this.position_x == c.getPosition_x()) && (this.position_y == c.getPosition_y())){
            return true;
        } else{
            return false; //other methods will throw exception
        }
    }


    //to talk to a person, you must be in the same position in the map
    //for npc conversations, you can do talk, and then it probably shows you what they do, and you either do it or not.
    //eg. Welcome to town! I'm the blacksmith, I'm open to trading if you need weapons? y/n
    //method for an npc to fulfill their role/
    //right now this passes in a character, but I think that later, I will just make it that, if you want to do any of the npc's functions, you must
    //be in the same location by an external test, in the text based game part I think?
    public void intro(Character c){
        if(positionMatch(c)){
            System.out.println("Hello traveler! I am the " + this.occupation + " here.");
            System.out.println("Would you like to barter?");
            System.out.println("Currently I have " + getInventory());
            System.out.println("And I am willing to trade for " + this.wants);
        } else{
            throw new PositionMismatchException();
        }
    }

    //putting something in your inventory
    public void grab(String s){
        this.inventory.add(s);
    }

    public boolean checkInventory(String s){
        if(this.inventory.contains(s)){
            return true;
        } else{
            return false;
        }
    }

    //print out a Character's inventory
    public void checkInventory(){
        System.out.println(this.inventory.toString());
    }

    //remove an item from a Character's inventory
    public void drop(String s){
        if(this.inventory.contains(s)){
            this.inventory.remove(s);
        } else{
            throw new RuntimeException("This object cannot be dropped, it is not in the inventory");
        }
    }

    public void showOptions(){
        System.out.println("\n+ barter()"); //currently only bartering is a function
    }


    //bartering objects, one for another, the first is what the player wants, the second is what the character is taking as payment
    /**
     * method for bartering (swapping objects around different inventories), first checks to see if bartering is possible based on matching locations, 
     * Then it checks if the offered item is something the npc even wants
     * if the npc wants the item, then it checks that the player has the item it is going to give, and that the npc has the item they're going to give
     * if all these things are true, then the swap takes place and the trade is successful
     * @param trade the item the player wants to get
     * @param payment the payment the player is going to give the npc
     * @param player currently another character, but eventually is going to be the player, the person asking for something.
     */
    public void barter(String trade, String payment, Character player){ //LATER CHANGE CHARACTER TO PLAYER, NPC'S SHOULDN"T TRADE
        if(positionMatch(player)){ 
            if(this.wants.equals(payment)){
                if(this.inventory.contains(trade) && player.checkInventory(payment)){ //the npc has the right object, and the player's inventory has the payment
                    drop(trade); //the npc drops the item they're giving away
                    grab(payment); //they grab the payment
                    player.grab(trade); //player grabs the item they want
                    player.drop(payment); //player gives the payment
                    System.out.println("A successful trade of " + payment + " for " + trade + "!");
                } else{
                    throw new RuntimeException("Those items cannot be bartered, one or both is not in the inventory.");
                }
            } else{
                System.out.println("The " + this.occupation + " does not want " + payment + " please choose another thing to trade");
            } 
        }else{
            throw new PositionMismatchException();
        }
    }


    public static void main(String[] args) {
        ArrayList<String> purse = new ArrayList<String>();
        purse.add("nails");
        purse.add("gin");
        ArrayList<String> bag = new ArrayList<String>();
        bag.add("gold");
        bag.add("sock yarn");
        bag.add("wool");
        Character smith = new Character("A traveling smith looking to shod horses", "blacksmith", 0,0, bag, "flour");
        Character baker = new Character("A worker in a small northern town", "baker", 0, 0, purse, "gold");

        // smith.checkInventory();
        // baker.checkInventory();

        baker.barter("nails", "gems", smith);

        smith.intro(baker);
        
    }
    

}