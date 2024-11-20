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


    //to talk to a person, you must be in the same position in the map
    public void talk(Character c){
        if((c.getPosition_x() == this.position_x) && (c.getPosition_y() == this.position_y)){
            System.out.println(c.getOpeningPhrase());
        } else{
            throw new RuntimeException(c.getOccupation() + "is nowhere to be found, they cannot be talked to.");
        }
    }

    public static void main(String[] args) {
        ArrayList<String> dreams = new ArrayList<String>();
        dreams.add("fine milled flour");
        dreams.add("a rolling pin");
        Character smith = new Character("A traveling smith looking to shod horses", "blacksmith", 0, 0, "Hello, are there any horses that need shoing?");
        Character baker = new Character("A worker in a small northern town", "baker", 0, 0, new ArrayList<String>(), dreams, "You look new in town, do you need bread?!");

        System.out.println(baker.getWants());
        
    }
    

}