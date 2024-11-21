import java.util.ArrayList;

public class Character{
    protected String description;
    protected String occupation;
    protected int position_x;
    protected int position_y;
    protected ArrayList<String> inventory; //change to type Tool when class has been created
    protected ArrayList<String> wants;
    protected String openingPhrase;

    public Character(String description, String occupation, int position_x, int position_y, String openingPhrase){
        this.description = description;
        this.occupation = occupation;
        this.position_x = position_x;
        this.position_y = position_y;
        this.inventory = new ArrayList<String>();
        this.wants = new ArrayList<String>();
        this.openingPhrase = openingPhrase;
    }

    public Character(String description, String occupation, int position_x, int position_y, ArrayList<String> inventory, ArrayList<String> wants, String openingPhrase){
        this.description = description;
        this.occupation = occupation;
        this.position_x = position_x;
        this.position_y = position_y;
        this.inventory = inventory;
        this.wants = wants;
        this.openingPhrase = openingPhrase;
    }

    public String getDescription(){
        return this.description;
    }

    public String getOccupation(){
        return this.occupation;
    }

    public int getPosition_x(){
        return this.position_x;
    }

    public int getPosition_y(){
        return this.position_y;
    }

    public String getOpeningPhrase(){
        return this.openingPhrase;
    }

    public String getWants(){
        return "the " + this.occupation + " dreams of owning \n" + this.wants.toString();
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
    public String talk(Character c){
        if(positionMatch(c)){
            return c.getOpeningPhrase();
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

    //return a character's inventory as a String
    public String getInventory(){
        return this.inventory.toString();
    }

    //remove an item from a Character's inventory
    public void drop(String s){
        if(this.inventory.contains(s)){
            this.inventory.remove(s);
        } else{
            throw new RuntimeException("This object cannot be dropped, it is not in the inventory");
        }
    }

    //bartering objects, one for another, the first is what the player wants, the second is what the character is taking as payment
    public void barter(String trade, String payment, Character player){
        if(positionMatch(player)){
            if(this.inventory.contains(trade) && player.checkInventory(payment)){
                drop(trade); //the npc drops the item they're giving away
                grab(payment); //they grab the payment
                player.grab(trade); //player grabs the item they want
                player.drop(payment); //player gives the payment
                System.out.println("A successful trade of " + payment + " for " + trade + "!");
            } else{
                throw new RuntimeException("Those items cannot be bartered, one or both is not in the inventory.");
            }
        } else{
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
        Character smith = new Character("A traveling smith looking to shod horses", "blacksmith", 0,0, bag, new ArrayList<String>(),  "Hello, are there any horses that need shoing?");
        Character baker = new Character("A worker in a small northern town", "baker", 0, 0, purse, new ArrayList<String>(), "You look new in town, do you need bread?!");

        smith.checkInventory();
        baker.checkInventory();

        baker.barter("nails", "gold", smith);
        
    }
    

}