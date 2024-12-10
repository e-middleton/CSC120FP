import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * Map class, a 2d array of Locations, has methods for walking in the cardinal directions
 * and a method for finding the current location of a npc
 */
public class Map {
    private Location[][] locations; //array of locations
    private int rows;
    private int columns;

    /**
     * default constructor for the map, creates a 2D array of Locations based on ints from a text file and then
     * reads in location info from the same text file, and reads in the npcs from a .txt file
     * reads in inventories from a text file as well
     * FILES MUST BE FORMATTED CORRECTLY
     */
    public Map(String locationFile, String npcFile, String itemsFile){
        try{
            File file = new File(locationFile + ".txt");
            Scanner locationInfo = new Scanner(file);

            //read in map dimensions
            this.rows += Integer.parseInt(locationInfo.nextLine()); //MIGHT THROW EXCEPTION
            this.columns += Integer.parseInt(locationInfo.nextLine());
            this.locations = new Location[rows][columns];

            
            // Read in locations base information
            for(int i = 0; i < this.rows; i++){
                for(int j = 0; j < this.columns; j++){
                    locationInfo.nextLine(); //get rid of break line in file which was added for readability
                    String description = locationInfo.nextLine();
                    String[] mutableDescription = locationInfo.nextLine().split(",");
                    String name = locationInfo.nextLine(); 
                    boolean n = Boolean.parseBoolean(locationInfo.nextLine()); //MAKE SURE FORMATTED CORRECT
                    boolean e = Boolean.parseBoolean(locationInfo.nextLine());
                    boolean s = Boolean.parseBoolean(locationInfo.nextLine());
                    boolean w = Boolean.parseBoolean(locationInfo.nextLine());
                    boolean moth = Boolean.parseBoolean(locationInfo.nextLine());
                    locations[i][j] = new Location(description, mutableDescription, name, j, i, new ArrayList<NPC>(), n, e, s, w, moth); 
                }
            }
            locationInfo.close();
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        // Read in location inventory information
        try{
            File file1 = new File(itemsFile + ".txt");
            Scanner itemsInput = new Scanner(file1);

            //all locations have an inventory line in the file, even if it is blank
            for(int i = 0; i < this.rows; i++){
                for(int j = 0; j< this.columns; j++){
                    String inventory = itemsInput.nextLine();
                    String[] individualItems = inventory.split(",");
                    String[] finalInventory = new String[individualItems.length];

                    // words are comma separated
                    // Removes commas and whitespace
                    for(int a = 0; a < individualItems.length; a++){
                        finalInventory[a] = individualItems[a].replace(",", "").stripTrailing().stripLeading();
                    }
                    locations[i][j].setInventory(finalInventory); 
                }
            } 
            itemsInput.close();
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        // Read in NPC information and put them in their locations
        try{
            File file2 = new File(npcFile + ".txt");
            Scanner input = new Scanner(file2);

            //read in total number of npcs, MUST BE ACCURATE
            int numPpl = Integer.parseInt(input.nextLine()); //might throw exception

            for(int i = 0; i<numPpl; i++){
                String occupation = input.nextLine();
                if(occupation.equals("door")){ //talkingDoor information
                    String description = input.nextLine();
                    String intro = input.nextLine();
                    String riddle = input.nextLine();
                    String direction = input.nextLine();
                    String answer = input.nextLine();
                    int positionX = Integer.parseInt(input.nextLine());
                    int positionY = Integer.parseInt(input.nextLine());
                    TalkingDoor door = new TalkingDoor(description, occupation, intro, riddle, direction, answer, positionX, positionY);
                    this.locations[positionY][positionX].addPerson(door);
                } else {                                 //all other npcs
                    String description = input.nextLine();
                    int positionX = Integer.parseInt(input.nextLine());
                    int positionY = Integer.parseInt(input.nextLine());
                    int wantsNum = Integer.parseInt(input.nextLine());

                    // GRABS WHOLE INVENTORY as one object, then breaks it up into individual items/words
                    String inventory = input.nextLine();
                    String[] individualItems = inventory.split(",");
                    String[] finalInventory = new String[individualItems.length];

                    // words are comma separated
                    // Removes commas and whitespace
                    for(int a = 0; a < individualItems.length; a++){
                        finalInventory[a] = individualItems[a].replace(",", "").stripTrailing().stripLeading();
                    }

                    ArrayList<String> npcInventory = new ArrayList<String>(Arrays.asList(finalInventory));
                    String want = input.nextLine();
                    NPC npc = new NPC(description, occupation, positionX, positionY, npcInventory, want, wantsNum);
                    this.locations[positionY][positionX].addPerson(npc);
                }
            }
            input.close();
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for finding the current location of the player
     * uses the player's x and y positions and matches them to a Location in the array
     * @param hero the player whos position is being used
     * @return the Location that the player is currently within
     */
    public Location findLocation(Player hero){
        int x = hero.getPositionX();
        int y = hero.getPositionY();
        //y, x because array is [row][column]
        return locations[y][x]; //might throw arrayIndexOutOfBoundsException, but incredibly unlikely?
    }

    /**
     * Method for walking north, 
     * first checks if it's possible based on the current location
     * @param hero the player whos position is changing
     * @return true/false they walked north
     */
    public boolean walkNorth(Player hero){
        Location square = findLocation(hero);
        if(square.getNorth()){
            hero.walk(1, 0);
            System.out.println("You have entered the " + findLocation(hero).getName());
            return true;
        } else{
            return false;
        }
    }

    /**
     * Method for walking east
     * checks if it's possible based on the current location first
     * @param hero the player whos position is changing
     * @return true/false they walked east
     */
    public boolean walkEast(Player hero){
        Location square = findLocation(hero);
        if(square.getEast()){
            hero.walk(0, 1); //updates player's internal position
            System.out.println("You have entered the " + findLocation(hero).getName());
            return true;
        } else{
            return false;
        }
    }

    /**
     * Method for walking south
     * first checks if it's possible based on Location
     * @param hero the player whos position is being changed
     * @return true/false they walked south
     */
    public boolean walkSouth(Player hero){
        Location square = findLocation(hero);
        if(square.getSouth()){
            hero.walk(-1, 0);
            System.out.println("You have entered the " + findLocation(hero).getName());
            return true;
        } else{
            return false;
        }
    }

    /**
     * Method for walking west, 
     * checks if it's possible to traverse that edge based on the Location
     * @param hero the player whos position is being changed
     * @return true/false they walked west
     */
    public boolean walkWest(Player hero){
        Location square = findLocation(hero);
        if(square.getWest()){
            hero.walk(0, -1);
            System.out.println("You have entered the " + findLocation(hero).getName());
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * Getter for a specific location based on indexing, used for testing
     * @param x the x or column position of the Location
     * @param y the y or row position of the Location
     * @return the Location at the index specifiec
     */
    public Location getLocation(int x, int y){
        return this.locations[y][x];
    }

    /**
     * Method for getting a description of the map, prints out the locations and their indices 
     */
    public String toString(){
        String description = ""; 

        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<this.columns; j++){
                description += "index [" + i + "][" + j + "]: \n"; //ex index[0][0]: 
                description += locations[i][j].toString();
                description += "\n";
                description += "\n";
            }
        }
        return description;
    }

    /**
     * Main method for Map(), used for testing
     * @param args empty array of Strings
     */
    public static void main(String[] args) {

        Map map = new Map("locations", "population", "locationInventories");
        System.out.printf(map.toString());

    }
}
