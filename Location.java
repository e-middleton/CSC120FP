import java.util.ArrayList;
import java.util.Arrays;

/**
 * Location class, has methods for walking in/out, looking around, looking at specific characters, 
 * for getting a list of everybody within the location, for adding and removing items from the location, 
 * and getters for all attributes
 */
public class Location {
    private String description;
    private ArrayList<String> mutableDescription;
    private String name;
    private ArrayList<String> inventory;
    private int position_x;
    private int position_y;
    private ArrayList<NPC> cast;
    private boolean north;
    private boolean east;
    private boolean south;
    private boolean west;
    private boolean containsMoth;
    private Moth moth;

    /**
     * Shortened/incomplete constructor for Locations, used for testing/unsure locations
     * @param x the x or column index of the location in the map
     * @param y the y or row index of the location in the map
     * @param north t/f they can walk north
     * @param east t/f it is possible to walk east
     * @param south t/f it is possible to walk south
     * @param west t/f it is possible to walk west
     */
    public Location(int x, int y, boolean north, boolean east, boolean south, boolean west){
        this.description = null;
        this.name = null;
        this.inventory = new ArrayList<String>();
        this.position_x = x;
        this.position_y = y;
        this.cast = new ArrayList<NPC>();
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.containsMoth = false;
        this.moth = null; //no moth
    }

    /**
     * Constructor for Location with everything except the inventory of objects, to be read in later
     * @param description  a description of the location
     * @param name the name/id tag of the location
     * @param position_x the x or column index of the Location in the map
     * @param position_y the y or row index of the Location in the map
     * @param cast the arrayList of NPCs in the Location
     * @param north t/f it is possible to go north from this location
     * @param east t/f it is possible to walk east
     * @param south t/f it is possible to walk south
     * @param west t/f it is possible to walk west
     * @param containsMoth t/f there is a moth in this location
     */
    public Location(String description, String[] mutableDescription, String name, int position_x, int position_y, ArrayList<NPC> cast, boolean north, boolean east, boolean south, boolean west, boolean containsMoth){
        this.description = description;
        this.mutableDescription = new ArrayList<String>(Arrays.asList(mutableDescription));
        this.name = name;
        this.position_x = position_x; //getter but no setter. (or is this given by map?)
        this.position_y = position_y;
        this.inventory = new ArrayList<String>();
        this.cast = cast;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.containsMoth = containsMoth;
        if(this.containsMoth){
            this.moth = new Moth(); //if a true is passed in, a moth is created in the Location
        }

        //automatically makes everybody's position correct for the location they're in.
        for(int i = 0; i < cast.size(); i++){
            cast.get(i).setPosition_x(this.position_x);
            cast.get(i).setPosition_y(this.position_y);
        }

    }

    /**
     * Constructor for Location with all the attributes
     * @param description a description of the location
     * @param name the name/id tag of the location
     * @param inventory the arrayList of objects in the location
     * @param position_x the x or column index of the Location in the map
     * @param position_y the y or row index of the Location in the map
     * @param cast the arrayList of Characters in the Location
     * @param north t/f it is possible to go north from this location
     * @param east t/f it is possible to walk east
     * @param south t/f it is possible to walk south
     * @param west t/f it is possible to walk west
     * @param containsMoth t/f there is a moth in this Location
     */
    public Location(String description, String name, ArrayList<String> inventory, int position_x, int position_y, ArrayList<NPC> cast, boolean north, boolean east, boolean south, boolean west, boolean containsMoth){
        this.description = description;
        this.name = name;
        this.inventory = inventory;
        this.position_x = position_x; //getter but no setter. (or is this given by map?)
        this.position_y = position_y;
        this.cast = cast;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.containsMoth = containsMoth;
        if(this.containsMoth){
            this.moth = new Moth(); //if a true is passed in, a moth is created in the Location
        }

        //automatically makes everybody's position correct for the location they're in.
        for(int i = 0; i < cast.size(); i++){
            cast.get(i).setPosition_x(this.position_x);
            cast.get(i).setPosition_y(this.position_y);
        }

    }

    /**
     * getter for the containsMoth attribute
     * @return true/false this location has a moth
     */
    public boolean hasMoth(){
        return this.containsMoth;
    }

    /**
     * method to get the value of the possibility of traveling/walking north
     * @return t/f it is possible to walk north
     */
    public boolean getNorth(){
        return this.north;
    }

    /**
     * method to get the value of the possibility of traveling/walking east
     * @return t/f it is possible to walk east
     */
    public boolean getEast(){
        return this.east;
    }

    /**
     * method to get the value of the possibility of traveling/walking north
     * @return t/f it is possible to walk south
     */
    public boolean getSouth(){
        return this.south;
    }

    /**
     * Only called by Play.answerRiddle() to make going into the yarn room possible
     */
    public void setSouth(){
        this.south = true;
    }

    /**
     * method to get the value of the possibility of traveling/walking west
     * @return t/f it is possible to walk west
     */
    public boolean getWest(){
        return this.west;
    }

    /**
     * grabs the moth from the location if there exists such a moth
     * @return the moth
     */
    public Moth getMoth(){
        if(this.hasMoth()){
            return this.moth;
        } else{
            throw new MissingNPCException();
        }
    }

    /**
     * getter for description
     * @return a string describing the location
     */
    public String getDescription(){
        String result = this.description + "\n";

        for(int i = 0; i < this.mutableDescription.size(); i++){
            result += this.mutableDescription.get(i) + " ";
        }
        return result;
    }

    /**
     * Getter for the name of the location, used like an ID tag
     * @return the name of the location
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the inventory of a location. It just prints the objects in the location
     */
    public String getInventory(){
        return this.inventory.toString();
    }

    /**
     * Getter for the x coordinate of the position, cannot be changed
     * @return the x coordinate of the location's position
     */
    public int getPosition_x(){
        return this.position_x;
    }

    /**
     * Getter for the y-coordinate of the location's position. cannot be changed.
     * @return y-coordinate of the location's position.
     */
    public int getPosition_y(){
        return this.position_y;
    }

    /**
     * Method for adding an item to the inventory for a location, no checks done, usually when a npc drops something
     * @param s the item being added to a location
     */
    public void addItem(String s){
        this.inventory.add(s);
        this.mutableDescription.add("\nIn this location is a(n) " + s);
    }

    /**
     * method for initializaing an inventory of items outside of the constructor, used in Map when locations are read in from .txt files
     * @param s the String[] (list) of items in the location
     */
    public void setInventory(String[] s){
        this.inventory.addAll(Arrays.asList(s));  
    }
    /**
     * Method for removing an item from the inventory of a Location, 
     * first checking if it was in the location originally.
     * @param s the object being taken out of the Location
     */
    public void removeItem(String s){
        if(this.inventory.contains(s)){
            this.inventory.remove(s);
            if(!this.inventory.contains(s)){ //only removes the description bit if there are no more of that object
                for(int i = 0; i < this.mutableDescription.size(); i++){
                    if(this.mutableDescription.get(i).contains(s)){
                        this.mutableDescription.remove(i);
                    }
                }
            }
        } else{
            throw new RuntimeException("This item is not in this location.");
        }
    }

    /**
     * Method for checking if a given item is in the inventory of a location
     * @param s the object being checked for
     * @return true/false the object is in the Location
     */
    public boolean containsItem(String s){
        return this.inventory.contains(s);
    }

    /**
     * Method for printing out the occupations/names of the characters in a location as a long String.
     * @return
     */
    public String getCast(){
        String names = "The "; //needs to be initialized
        if(this.cast.size() == 1){
            return "the " + this.cast.get(0).getOccupation(); //if there's only one person, their name/occupation is the only thing passed
        } else if(!this.cast.isEmpty()){
            for(int i = 0; (i < this.cast.size() -1); i++){ //for if there are multiple people
                names += this.cast.get(i).getOccupation();
                names += ", ";
            }
            names += "and the " + this.cast.get(this.cast.size() - 1).getOccupation(); //returns the name of the last npc in the array list, final index
            return names;
        } else{
            return "There are no people in this location.";
        }
    }

    /**
     * Method for adding a person to a location if they were not initialized within it
     * @param npc the npc being added to the location
     */
    public void addPerson(NPC npc){ 
        this.cast.add(npc);
    }

    /**
     * Method for looking around a location, gets the description of (this) location,
     * and if there are npc's, it gets their occupations as well
     */
    public void lookAround(){
        System.out.printf("You see " + getDescription() +"\n");
        if(this.cast.size()>=1){
            System.out.println("In this location is " + getCast()); //names/occupations of NPC if they exist
        }
    }

    /**
     * Returns a npc based upon their occupation/name by looking through the cast of a given location.
     * @param s the name/occupation of the npc
     * @return the NPC (type) being looked for
     */
    public NPC getPerson(String s){
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
            throw new MissingNPCException();
        }
    }

    /**
     * A method for getting a description of a NPC based on their occupation given that they are in the cast of a location
     * @param s the occupation/name of the NPC being described
     */
    public void lookAtCharacter(String s){ //describes a person if they are in the location being checked.
        try{
            System.out.println("You see...");
            NPC c = getPerson(s); //returns a person if they exist in the cast of a given location
            System.out.println(c.getDescription());
        } catch(MissingNPCException e){
            System.out.println("You don't see any " + s + " in the " + this.name);
        }
    }

    /**
     * getter for the overal description of a Location, used to help with testing in the Map class
     * @return String description of the Location object and all its relevant information
     */
    public String toString(){
       return "name: " + this.name + "\n" + "description: " + getDescription() + "\n" + "cast: " + getCast() + "\n" + "items: " + getInventory() +"\n" + "moth: " + this.containsMoth;
    }

    /**
     * Main method for Location, used for testing
     * @param args empty array of Strings
     */
    public static void main(String[] args) {
        NPC smith = new NPC("A traveling smith looking to shod horses", "smith", 0,0, new ArrayList<String>(), "flour", 1);
        NPC baker = new NPC("A worker in a small northern town", "baker", 0, 0, new ArrayList<String>(), "gold", 2);
        ArrayList<NPC> village = new ArrayList<>();
        village.add(smith);
        village.add(baker);
        String[] descrip = new String[3];
        descrip[0] = "zero and other words,";
        descrip[1] = "oh baby";
        descrip[2] = "now yes";
        Location home = new Location("a small hovel, decrepit and falling apart.", descrip, "home", 1, 1, village, true, true, true, true, false);
    
        System.out.println(home.toString());
    }
    
}
