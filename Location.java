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
    private ArrayList<Item> inventory;
    private int positionX;
    private int positionY;
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
        this.mutableDescription = new ArrayList<String>();
        this.name = null;
        this.inventory = new ArrayList<Item>();
        this.positionX = x;
        this.positionY = y;
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
     * @param mutableDescription the description which is able to change, passed in as an array of Strings
     * @param name the name/id tag of the location
     * @param positionX the x or column index of the Location in the map
     * @param positionY the y or row index of the Location in the map
     * @param cast the arrayList of NPCs in the Location
     * @param north t/f it is possible to go north from this location
     * @param east t/f it is possible to walk east
     * @param south t/f it is possible to walk south
     * @param west t/f it is possible to walk west
     * @param containsMoth t/f there is a moth in this location
     */
    public Location(String description, String[] mutableDescription, String name, int positionX, int positionY, ArrayList<NPC> cast, boolean north, boolean east, boolean south, boolean west, boolean containsMoth){
        this.description = description;
        this.mutableDescription = new ArrayList<String>(Arrays.asList(mutableDescription));
        this.name = name;
        this.positionX = positionX; //getter but no setter. (or is this given by map?)
        this.positionY = positionY;
        this.inventory = new ArrayList<Item>();
        this.cast = cast;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.containsMoth = containsMoth;
        if(this.containsMoth){
            this.moth = new Moth(); //if a true is passed in, a moth is created in the Location
        } else{
            this.moth = null; //no moth
        }

        //automatically makes all npc's positions are correct for the location they're in, double check
        for(int i = 0; i < cast.size(); i++){
            cast.get(i).setPositionX(this.positionX);
            cast.get(i).setPositionY(this.positionY);
        }
    }

    /**
     * Constructor for Location with all the attributes except mutableDescription
     * @param description a description of the location
     * @param name the name/id tag of the location
     * @param inventory the arrayList of objects in the location
     * @param positionX the x or column index of the Location in the map
     * @param positionY the y or row index of the Location in the map
     * @param cast the arrayList of Characters in the Location
     * @param north t/f it is possible to go north from this location
     * @param east t/f it is possible to walk east
     * @param south t/f it is possible to walk south
     * @param west t/f it is possible to walk west
     * @param containsMoth t/f there is a moth in this Location
     */
    public Location(String description, String name, ArrayList<Item> inventory, int positionX, int positionY, ArrayList<NPC> cast, boolean north, boolean east, boolean south, boolean west, boolean containsMoth){
        this.description = description;
        this.mutableDescription = new ArrayList<String>();
        this.name = name;
        this.inventory = inventory;
        this.positionX = positionX; //getter but no setter. Set when read into map() and initialized
        this.positionY = positionY;
        this.cast = cast;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.containsMoth = containsMoth;
        if(this.containsMoth){
            this.moth = new Moth(); //if a true is passed in, a moth is created in the Location
        } else{
            this.moth = null; //no moth
        }

        //automatically makes everybody's position correct for the location they're in.
        for(int i = 0; i < cast.size(); i++){
            cast.get(i).setPositionX(this.positionX);
            cast.get(i).setPositionY(this.positionY);
        }
    }

    //      GETTERS
    // ****************

    /**
     * Getter for the name of the location, used like an ID tag
     * @return the name of the location
     */
    public String getName(){
        return this.name;
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
     * called by Map class to check if walking is possible
     * @return t/f it is possible to walk north
     */
    public boolean getNorth(){
        return this.north;
    }

    /**
     * method to get the value of the possibility of traveling/walking east
     * called by Map class
     * @return t/f it is possible to walk east
     */
    public boolean getEast(){
        return this.east;
    }

    /**
     * method to get the value of the possibility of traveling/walking north
     * called by Map class
     * @return t/f it is possible to walk south
     */
    public boolean getSouth(){
        return this.south;
    }

    /**
     * method to get the value of the possibility of traveling/walking west
     * called by Map class
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
     * Getter for the x coordinate of the position, cannot be changed
     * @return the x coordinate of the location's position
     */
    public int getPositionX(){
        return this.positionX;
    }

    /**
     * Getter for the y-coordinate of the location's position. cannot be changed.
     * @return y-coordinate of the location's position.
     */
    public int getPositionY(){
        return this.positionY;
    }

    /**
     * getter for description of the location
     * @return a string describing the location
     */
    public String getDescription(){
        String result = this.description + "\n";

        //adds the mutable description to the static one
        for(int i = 0; i < this.mutableDescription.size(); i++){
            result += this.mutableDescription.get(i) + " ";
        }
        return result;
    }

    /**
     * Method for printing out the occupations/names of the characters in a location as a long String.
     * @return a String with all the NPCs in a given Location
     */
    public String getCast(){
        String names = "The "; //needs to be initialized
        if(this.cast.size() == 1){
            return "the " + this.cast.get(0).getOccupation(); //if there's only one person, their name/occupation is the only thing passed
        } else if(this.cast.size() > 1){ 
            for(int i = 0; (i < this.cast.size() -1); i++){ //for if there are multiple people
                names += this.cast.get(i).getOccupation();
                names += ", ";
            }
            names += "and the " + this.cast.get(this.cast.size() - 1).getOccupation(); //returns the name of the last npc in the array list, final index
            return names;
        } else{ //if this.cast() is empty
            return "There are no people in this location.";
        }
    }


    /**
     * Getter for the inventory of a location. It just prints the objects in the location
     * used for testing and the toString method
     * @return the inventory of the Location as a String
     */
    public String getInventory(){ 
       return this.inventory.toString();
    }


    /**
     * Method for getting an item from a location's inventory
     * @param s the name of the item being searched for
     * @return the item, if it exists in the inventory
     */
    public Item getItem(String s){
        boolean check = false;
        int index = 0;
        for(int i = 0; i < this.inventory.size(); i ++){
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
     * Method for checking if a given item is in the inventory of a location
     * @param s the name of the item being checked for
     * @return true/false the object is in the Location
     */
    public boolean containsItem(String s){
        boolean check = false;
        for(int i = 0; i < this.inventory.size(); i ++){
            if(this.inventory.get(i).getName().equals(s)){
                check = true;
            }
        }
        return check;
    }

    /**
     * Returns a npc based upon their occupation/name by looking through the cast of a given location.
     * @param s the name/occupation of the npc
     * @return the NPC (object) being looked for
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


    //          SETTERS
    //*************************** 

    /**
     * method to change the value of south attribute, 
     * Only called by Play.answerRiddle() to make going into the yarn room possible
     */
    public void setSouth(){
        this.south = true;
    }

    /**
     * Method for unlocking a path
     * called by door if a riddle is answered correctly
     * @param direction the path being unlocked n/e/s/w
     */
    public void unlockPath(String direction){
        if(direction.equals("north")){
            this.north = true;
        } else if (direction.equals("east")){
            this.east = true;
        } else if (direction.equals("south")){
            this.south = true;
        } else if (direction.equals("west")){
            this.west = true;
        }

    }

    /**
     * Method for adding an item to the inventory for a location, usually when a npc drops something
     * adds the object to the mutable description to make sure it's visible
     * before this happens, Play class CHECKS to make sure the player actually has the object they say they're dropping
     * @param s the item being added to a location
     */
    public void addItem(Item s){
        this.inventory.add(s);
        this.mutableDescription.add("\nIn this location is a(n) " + s.getName());
    }

    /**
     * method for initializaing an inventory of items outside of the constructor, used in Map when locations are read in from .txt files
     * @param s the String[] (list) of items in the location
     */
    public void setInventory(Item[] s){
        this.inventory.addAll(Arrays.asList(s));  //adding a collection of type String
    }

    /**
     * Method for removing an item from the inventory of a Location, 
     * first checking if it was in the location originally.
     * @param s the object being taken out of the Location
     */
    public void removeItem(String s){
        if(containsItem(s)){ //if the item is in the location 
            for(int i = 0; i < this.inventory.size(); i ++){ //it is removed
                if(this.inventory.get(i).getName().equals(s)){
                    this.inventory.remove(i); //removes the index where the item is
                } 
            }
            if(!containsItem(s)){ //if there is no more remaining of that item, it is removed from the description
                for(int i = 0; i < this.mutableDescription.size(); i++){
                    if(this.mutableDescription.get(i).contains(s)){ //if part of the mutable description includes the name of the object being removed,
                        this.mutableDescription.remove(i); //it is taken out of the description
                    }
                }
            }
        } else{ //item cannot be removed, it was never in the location
            throw new RuntimeException("This item is not in this location.");
        }
    }



    /**
     * Method for adding a person to a location if they were not initialized within it
     * for when npcs are read in from the .txt file in Map constructor
     * @param npc the npc being added to the location
     */
    public void addPerson(NPC npc){ 
        this.cast.add(npc);
    }



    //      METHODS
    //********************



    /**
     * Method for looking around a location, gets the description of (this) location,
     * and if there are npcs, it gets their occupations/name as well
     */
    public void lookAround(){
        System.out.printf("You see " + getDescription() +"\n");
        if(this.cast.size()>=1){
            System.out.println("In this location is " + getCast()); //names/occupations of NPC if they exist
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
     * Method for looking at an Item in a location
     * @param s the name of the item being looked at
     */
    public void lookAtItem(String s){
        try{
            Item a = getItem(s);
            System.out.println(a.getDescription());
        } catch(MissingMaterialException e){
            System.out.println("You do not see any " + s + " in this location.");
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
        ArrayList<Item> inventory = new ArrayList<Item>();

        Item thing = new Item("cup", "The cup is a poor conversation partner", "A simple glass cup, with a crack in the handle");
        inventory.add(thing);
        Item thing2 = new Item("dk yarn", "conversation with yarn is annoying and mostly fuzz words", "It is a thin undyed yarn, perfect for hats");
        inventory.add(thing2);

        Location home = new Location("a small hovel, decrepit and falling apart.", descrip, "home", 1, 1, village, true, true, true, true, false);
        home.addItem(thing);
        home.addItem(thing2);
        //home.lookAround();
        home.lookAtItem("dk yarn");
        System.out.println(home.containsItem("cup"));
    
        //System.out.println(home.toString());
    }
    
}
