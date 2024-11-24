import java.util.ArrayList;

public class Location {
    private String description;
    private String name;
    private ArrayList<String> inventory;
    private int position_x;
    private int position_y;
    private ArrayList<Character> cast;

    public Location(String description, String name, ArrayList<String> inventory, int position_x, int position_y, ArrayList<Character> cast){
        this.description = description;
        this.name = name;
        this.inventory = inventory;
        this.position_x = position_x; //getter but no setter. (or is this given by map?)
        this.position_y = position_y;
        this.cast = cast;

        //automatically makes everybody's position correct for the location they're in.
        for(int i = 0; i < cast.size(); i++){
            cast.get(i).setPosition_x(this.position_x);
            cast.get(i).setPosition_y(this.position_y);
        }

    }

    public String getDescription(){
        return this.description;
    }

    public String getName(){
        return this.name;
    }

    public void getInventory(){
        System.out.println(this.inventory.toString());
    }

    public int getPosition_x(){
        return this.position_x;
    }

    public int getPosition_y(){
        return this.position_y;
    }


    /**
     * Method for printing out the occupations/names of the characters in a location as a long String.
     * @return
     */
    public String getCast(){
        String names = "The "; //needs to be initialized
        if(this.cast.size() == 1){
            return this.cast.get(0).getOccupation(); //if there's only one person, their name/occupation is the only thing passed
        } else if(!this.cast.isEmpty()){
            for(int i = 0; (i < this.cast.size() -1); i++){ //for if there are multiple people
                names += this.cast.get(i).getOccupation();
                names += ", ";
            }
            names += "and " + this.cast.get(this.cast.size() - 1).getOccupation(); //returns the name of the last npc in the array list, final index
            return names;
        } else{
            return "There are no people in this location.";
        }
    }

    /**
     * Method for looking around a location, gets the description of (this) location,
     * and if there are npc's, it gets their occupations as well
     */
    public void lookAround(){
        System.out.println("You see " + getDescription());
        System.out.println("You also see...");
        System.out.println(getCast()); //either the names/occupation of the people, or the lack of them
    }

    /**
     * Returns a character based upon their occupation/name by looking through the cast of a given location.
     * @param s the name/occupation of the character
     * @return the Character (type) being looked for
     */
    public Character getPerson(String s){
        int index = 0; //only will get passed if the bool is changed
        boolean check = false;
        for(int i = 0; i < this.cast.size(); i++){ //if anybody in the cast has an occupation matching the name given, the index is saved
            if(cast.get(i).getOccupation().equals(s)){
                index = i;
                check = true;
            }
        }
        if(check){
            return cast.get(index); //index of the person matching the occupation, then that index in the cast is passed
        } else{
            throw new RuntimeException("This person is not in this location.");
        }
    }

    /**
     * A method for getting a description of a Character based on their occupation given that they are in the cast of a location
     * @param s the occupation/name of the character being described
     */
    public void lookAtCharacter(String s){ //describes a person if they are in the location being checked.
        try{
            Character c = getPerson(s); //returns a person if they exist in the cast of a given location
            System.out.println(c.getDescription());
        } catch(RuntimeException e){
            System.out.println(e.getMessage() + " They cannot be looked at.");
        }
    }

    //do I need an enter/exit method?

    public static void main(String[] args) {
        Character smith = new Character("A traveling smith looking to shod horses", "smith", 0,0, new ArrayList<String>(), "flour");
        Character baker = new Character("A worker in a small northern town", "baker", 0, 0, new ArrayList<String>(), "gold");
        ArrayList<Character> village = new ArrayList<>();
        village.add(smith);
        village.add(baker);
        Location home = new Location("a small hovel, decrepit and falling apart.", "home", new ArrayList<String>(), 1, 1, village);
        home.lookAround();
    }
    
}
