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
     * default constructor for the map, creates pre-determined locations and reads in the npcs from a .txt file
     */
    public Map(){
        int count = 0;
        this.rows = 4;
        this.columns = 4;
        this.locations = new Location[rows][columns];
        int numPpl = 0;

        TalkingDoor talkingDoor = new TalkingDoor("A man who appears to be melted into the metal of a heavy door, or perhaps he was always a part of the structure.", "door", 3, 1);
    
        this.locations[0][0] = new Location("a series of looming cliffs, the bottom is barely, if even visible, staring for too long makes you feel distinctly nauseous.", "cliffs", 0, 0, new ArrayList<NPC>(), true, true, false, false, false);
        this.locations[0][1] = new Location("A dark expanse of the sea, with small waves lapping at your feet, mussels, a few with pearls inside, cling to shoreline rocks and corals. \nYou cannot see the water's farthest edge, but near you, fine strands of plants intertwine into silk.", "sea", 1, 0, new ArrayList<NPC>(), true, true, false, true, false);
        this.locations[0][2] = new Location("A small inlet, with soft embankments of sand and shells scattered along with pieces of driftwood.", "cove", 2, 0, new ArrayList<NPC>(), true, false, false, true, false);
        this.locations[0][3] = new Location("An old and dusty room, shelves look to be filled with yarn? Most of it is rotted with time, but a few surviving skeins are a thin, lace yarn, but some is thicker, nearer to worsted yarn.", "yarn trove", 3, 0, new ArrayList<NPC>(), true, false, false, false, false);
        this.locations[1][0] = new Location("a room filled with old repair equipment, none of which you think you can use.", "repair guild", 0, 1, new ArrayList<NPC>(), true, true, true, false, false);
        this.locations[1][1] = new Location("A traders outpost! You see a booth currently occupied with an expectant merchant.", "trading post", 1, 1, new ArrayList<NPC>(), true, true, true, true, false);
        this.locations[1][2] = new Location("A freezing mountain, mist clouds around its peak. \nSnow crunches on the ground barely covering the gravel and lichen beneath.", "mountain", 2, 1, new ArrayList<NPC>(), true, true, true, true, false);
        this.locations[1][3] = new Location("An old mine, dark and empty, but you can see something glinting just beyond the entrance,\n and... is that door alive?", "mine", 3, 1, new ArrayList<NPC>(Arrays.asList(talkingDoor)), true, false, false, true, false);
        this.locations[2][0] = new Location("A curated garden, lovely fruit trees line a walkway down the central stretch \nThere are apple trees, orange trees, even a few berry bushes in the well-cared-for grove.", "garden", 0, 2, new ArrayList<NPC>(), true, true, true, false, false);
        this.locations[2][1] = new Location("a small and broken down hovel", "home", 1, 2, new ArrayList<NPC>(), true, true, true, true, false);
        this.locations[2][2] = new Location("an empty and dusty road", "road", 2, 1, new ArrayList<NPC>(), true, false, true, true, false); 
        this.locations[2][3] = new Location("It's an old hollowed out room of the mine, but this one hasn't yet been scraped, large purple gemstones glitter on the walls along with gold and silver ore. \na pickaxe lays to the side.", "ore room", 3, 2, new ArrayList<NPC>(), false, false, true, false, false);
        this.locations[3][0] = new Location("A narrow yet violent river, with white water foaming and swirling around jagged rocks. \nSofter, rounded pebbles line its banks, gold is flecked amongst the sand", "river", 0, 3, new ArrayList<NPC>(), false, true, true, false, false);
        this.locations[3][1] = new Location("an empty field, nothing as far as the eye can see.", "field", 1, 3, new ArrayList<NPC>(), false, true, true, true, true);
        this.locations[3][2] = new Location("A dark and eerie forest...", "forest", 2, 3, new ArrayList<NPC>(), false, true, true, true, false);
        this.locations[3][3] = new Location("In the center of an eerie and still clearing, purple mushrooms grow in a perfect ring, The silence feels heavier than before. \nIn the center of the ring, a small potion in a bottle lies motionless.", "mushroom circle", 3, 3, new ArrayList<NPC>(), false, false, false, true, false);
            
        //read in inventory information
        try{
            File file1 = new File("locationInventories.txt");
            Scanner itemsInput = new Scanner(file1);

             for(int i = 0; i < rows; i++){
                for(int j = 0; j<columns; j++){
                    locations[i][j].setInventory(new ArrayList<String>(Arrays.asList(itemsInput.nextLine())));
                }
            } 
            itemsInput.close();
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        //Read in NPC information and put them in their locations
        try{
            File file2 = new File("population.txt");
            Scanner input = new Scanner(file2);

            //read in the total number of npcs
            if(count == 0 && input.hasNextLine()){ 
                try{
                    numPpl += Integer.parseInt(input.nextLine()); //might throw exception
                    count += 1;
                } catch(NumberFormatException e) {
                    System.out.println("Invalid number formatting " + e.getMessage());
                }
            }

            for(int i = 0; i<numPpl; i++){
                String description = input.nextLine();
                String occupation = input.nextLine();
                int position_x = 0; //initialized outside of try-catch
                int position_y = 0;
                int wantsNum = 0;
                try{
                    position_x += Integer.parseInt(input.nextLine());
                    position_y += Integer.parseInt(input.nextLine());
                    wantsNum += Integer.parseInt(input.nextLine());

                } catch(NumberFormatException e){
                    System.out.println("Invalid number formatting " + e.getMessage());
                }
                ArrayList<String> inventory = new ArrayList<String>(Arrays.asList(input.nextLine()));
                String want = input.nextLine();
                NPC npc = new NPC(description, occupation, position_x, position_y, inventory, want, wantsNum);
                this.locations[position_y][position_x].addPerson(npc);
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
        int x = hero.getPosition_x();
        int y = hero.getPosition_y();
        return locations[y][x]; //might throw  arrayIndexOutOfBoundsException 
        //y, x because array is [row][column]
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
            hero.walk(0, 1);
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
     * Method for getting a description of the map, prints out the locations and their indices 
     */
    public String toString(){
        String description = ""; //automatically set to an array of 4x4

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


    public static void main(String[] args) {

        Map map = new Map();
        System.out.println(map.toString());

    }

}
