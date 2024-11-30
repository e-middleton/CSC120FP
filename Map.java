import java.util.ArrayList;

public class Map {
    private Location[][] locations; //array of locations

    //default map for testing
    public Map(){
        this.locations = new Location[4][4]; //4x4
        this.locations[0][0] = new Location(0, 0, true, true, false, false);
        this.locations[0][1] = new Location(1, 0, true, true, false, true);
        this.locations[0][2] = new Location(2, 0, true, false, false, true);
        this.locations[0][3] = new Location("An old and dusty room, shelves look to be filled with yarn?", "yarn trove", new ArrayList<String>(), 3, 0, new ArrayList<Character>(), true, false, false, false);
        this.locations[1][0] = new Location("a room filled with old repair equipment, none of which you think you can use.", "repair guild", new ArrayList<String>(), 0, 1, new ArrayList<Character>(), true, true, true, false);
        this.locations[1][1] = new Location("A traders outpost! You see a booth currently occupied with an expectant man.", "trading post", new ArrayList<String>(), 1, 1, new ArrayList<Character>(), false, true, true, false);
        this.locations[1][2] = new Location("A freezing mountain, mist clouds around it's peak", "mountain", new ArrayList<String>(), 2, 1, new ArrayList<Character>(), true, true, true, true);
        this.locations[1][3] = new Location("An old mine, dark, but you can see something glinting just beyond the entrance.", "mine", new ArrayList<String>(), 3, 1, new ArrayList<Character>(), true, false, true, true);
        this.locations[2][0] = new Location("A curated garden, lovely trees line a walkway down the central stretch.", "garden", new ArrayList<String>(), 0, 2, new ArrayList<Character>(), true, true, true, false);
        this.locations[2][1] = new Location("a small and broken down hovel", "home", new ArrayList<String>(), 1, 2, new ArrayList<Character>(), true, true, true, true);
        this.locations[2][2] = new Location("an empty and dusty road", "road", new ArrayList<String>(), 2, 1, new ArrayList<Character>(), true, false, true, true); 
        this.locations[2][3] = new Location("It's an old hollowed out room of the mine, but this one hasn't yet been scraped, large gemstones glitter on the walls.", "ore room", new ArrayList<String>(), 3, 2, new ArrayList<Character>(), false, false, true, false);
        this.locations[3][0] = new Location(0, 3, false, true, true, false);
        this.locations[3][1] = new Location("an empty field, nothing as far as the eye can see.", "field", new ArrayList<String>(), 1, 3, new ArrayList<Character>(), false, true, true, true);
        this.locations[3][2] = new Location("A dark and eerie forest...", "forest", new ArrayList<String>(), 2, 3, new ArrayList<Character>(), false, true, true, true);
        this.locations[3][3] = new Location("A magic mushroom circle, eat the mushrooms if you dare...", "magic circle", new ArrayList<String>(), 3, 3, new ArrayList<Character>(), false, false, false, true);
    }

    // public Map(Location a, Location b, Location c, Location d){
    //     this.locations = new Location[2][2]; //2x2
    //     this.locations[0][0] = a;
    //     this.locations[1][0] = b;
    //     this.locations[0][1] = c;
    //     this.locations[1][1] = d;
    // }

    //find where the player is in the map
    public Location findLocation(Player hero){
        int x = hero.getPosition_x();
        int y = hero.getPosition_y();
        return locations[y][x]; //might throw  arrayIndexOutOfBoundsException 
        //y, x because array is [row][column]
    }

    public boolean walkNorth(Player hero){
        Location square = findLocation(hero);
        if(square.getNorth()){
            hero.walk(1, 0);
            return true;
        } else{
            return false;
        }
    }

    public boolean walkEast(Player hero){
        Location square = findLocation(hero);
        if(square.getEast()){
            hero.walk(0, 1);
            return true;
        } else{
            return false;
        }
    }

    public boolean walkSouth(Player hero){
        Location square = findLocation(hero);
        if(square.getSouth()){
            hero.walk(-1, 0);
            return true;
        } else{
            return false;
        }
    }

    public boolean walkWest(Player hero){
        Location square = findLocation(hero);
        if(square.getWest()){
            hero.walk(0, -1);
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
