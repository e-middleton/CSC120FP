import java.util.ArrayList;
import java.util.Arrays;

/**
 * Map class, a 2d array of Locations, has methods for walking in the cardinal directions
 * and a method for finding the current location of a character
 */
public class Map {
    private Location[][] locations; //array of locations

    /**
     * Default constructor for map, creates pre-determined locations in a 4x4 grid
     */
    public Map(){
        this.locations = new Location[4][4]; //4x4
        //Gardener, in map[2][0] "garden", objects for garden
        ArrayList<String> gardenerSupplies = new ArrayList<String>(Arrays.asList("worsted yarn", "hoe", "seedlings"));
        ArrayList<String> gardenStuff = new ArrayList<String>(Arrays.asList("apple", "moldyOrange", "compost"));
        Character gardener = new Character("An old man, sitting at the bottom of a tree, gently weeding around the base of its roots.", "gardener", 0, 2, gardenerSupplies, "apple", 1);

        //Merchant, in map[1][1] "trading post"
        ArrayList<String> merchantSupplies = new ArrayList<String>(Arrays.asList("dye", "scissors", "needles", "bulky yarn", "bobbin"));
        Character merchant = new Character("A man, perhaps in his mid thirties in richly dyed clothing who eyes you with curiosity.", "merchant", 1, 1, merchantSupplies, "gold", 1);
       
        //Spinner, in map[1][0] "repair guild"
        ArrayList<String> spinnerSupplies = new ArrayList<String>(Arrays.asList("roving", "beeswax", "flax", "sock yarn"));
        Character spinner = new Character("A young woman, sitting at a spinning wheel gently twisting strands of flax as she feeds them into the flyer", "spinner", 0, 1, spinnerSupplies, "bobbin", 1);

        //Goblin, in map[1][3] "mine"
        ArrayList<String> goblinSupplies = new ArrayList<String>(Arrays.asList("breadcrusts", "knuckles?", "teeth", "needles"));
        Character goblin = new Character("A short and hungry looking creature, eyeing your ankles with a disturbing gaze.", "goblin", 3, 1, goblinSupplies, "lichen", 1);

        //objects for map[2][3] "ore room"
        ArrayList<String> oreRoomStuff = new ArrayList<String>(Arrays.asList("gold", "gold", "amethyst", "pickaxe", "silver"));

        //objects for map[3][3] "magic circle"
        ArrayList<String> magicCircleStuff = new ArrayList<String>(Arrays.asList("purple-mushroom", "potion"));

        //objects for map[0][3] "yarn trove"
        ArrayList<String> yarnTroveStuff = new ArrayList<String>(Arrays.asList("sock yarn", "sock yarn", "sock yarn"));

        //objects for map[1][2] "mountain"
        ArrayList<String> mountainStuff = new ArrayList<String>(Arrays.asList("snow", "gravel", "lichen"));

        //Mermaid for Map[0][1] "sea"
        ArrayList<String> mermaidSupplies = new ArrayList<String>(Arrays.asList("dk yarn", "snail", "guppy"));
        Character mermaid = new Character("An old woman, with strands of kelp instead of hair and barnacles clinging to her cheeks.", "mermaid", 1, 0, mermaidSupplies, "shell", 2);

        //objects for map[0][2] "cove"
        ArrayList<String> coveStuff = new ArrayList<String>(Arrays.asList("shell", "shell", "shell", "driftwood", "bone"));

        this.locations[0][0] = new Location(0, 0, true, true, false, false);
        this.locations[0][1] = new Location("A dark expanse of the sea, with small waves lapping at your feet. You cannot see its farthest edge.", "sea", new ArrayList<String>(), 1, 0, new ArrayList<Character>(Arrays.asList(mermaid)), true, true, false, true, false);
        this.locations[0][2] = new Location("A small inlet, with soft embankments of sand and shells scattered along with pieces of driftwood.", "cove", coveStuff, 2, 0, new ArrayList<Character>(), true, false, false, true, false);
        this.locations[0][3] = new Location("An old and dusty room, shelves look to be filled with yarn?", "yarn trove", yarnTroveStuff, 3, 0, new ArrayList<Character>(), true, false, false, false, false);
        this.locations[1][0] = new Location("a room filled with old repair equipment, none of which you think you can use.", "repair guild", new ArrayList<String>(), 0, 1, new ArrayList<Character>(Arrays.asList(spinner)), true, true, true, false, false);
        this.locations[1][1] = new Location("A traders outpost! You see a booth currently occupied with an expectant man.", "trading post", new ArrayList<String>(), 1, 1, new ArrayList<Character>(Arrays.asList(merchant)), false, true, true, false, false);
        this.locations[1][2] = new Location("A freezing mountain, mist clouds around it's peak", "mountain", mountainStuff, 2, 1, new ArrayList<Character>(), true, true, true, true, false);
        this.locations[1][3] = new Location("An old mine, dark, but you can see something glinting just beyond the entrance.", "mine", new ArrayList<String>(), 3, 1, new ArrayList<Character>(Arrays.asList(goblin)), true, false, true, true, false);
        this.locations[2][0] = new Location("A curated garden, lovely trees line a walkway down the central stretch.", "garden", gardenStuff, 0, 2, new ArrayList<Character>(Arrays.asList(gardener)), true, true, true, false, false);
        this.locations[2][1] = new Location("a small and broken down hovel", "home", new ArrayList<String>(), 1, 2, new ArrayList<Character>(), true, true, true, true, false);
        this.locations[2][2] = new Location("an empty and dusty road", "road", new ArrayList<String>(), 2, 1, new ArrayList<Character>(), true, false, true, true, false); 
        this.locations[2][3] = new Location("It's an old hollowed out room of the mine, but this one hasn't yet been scraped, large gemstones glitter on the walls.", "ore room", oreRoomStuff, 3, 2, new ArrayList<Character>(), false, false, true, false, false);
        this.locations[3][0] = new Location(0, 3, false, true, true, false);
        this.locations[3][1] = new Location("an empty field, nothing as far as the eye can see.", "field", new ArrayList<String>(), 1, 3, new ArrayList<Character>(), false, true, true, true, true);
        this.locations[3][2] = new Location("A dark and eerie forest...", "forest", new ArrayList<String>(), 2, 3, new ArrayList<Character>(), false, true, true, true, false);
        this.locations[3][3] = new Location("A magic mushroom circle, eat the mushrooms if you dare...", "magic circle", magicCircleStuff, 3, 3, new ArrayList<Character>(), false, false, false, true, false);
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

        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                description += i + ", " + j + " " + locations[i][j].getName() + " \n";
            }
        }
        return description;
    }


    public static void main(String[] args) {

        Map map = new Map();
        System.out.println(map.toString());


    }

}
