import java.util.ArrayList;

public class Map {
    private Location[][] locations; //array of locations

    //default map for testing
    public Map(){
        this.locations = new Location[2][2]; //2x2
        this.locations[0][0] = new Location("a small and broken down hovel", "home", new ArrayList<String>(), 0, 0, new ArrayList<Character>(), true, true, false, false);
        this.locations[0][1] = new Location("A dark and eerie forest...", "forest", new ArrayList<String>(), 0, 1, new ArrayList<Character>(), true, false, false, true);
        this.locations[1][0] = new Location("A freezing mountain, mist clouds around it's peak", "mountain", new ArrayList<String>(), 0, 1, new ArrayList<Character>(), false, true, true, false);
        this.locations[1][1] = new Location("An old mine entrance, dark, but you can see something glinting just beyond the entrance.", "mine", new ArrayList<String>(), 1, 1, new ArrayList<Character>(), false, false, true, true);
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
        String description = "";

        for(int i = 0; i<2; i++){
            for(int j = 0; j<2; j++){
                description += i + ", " + j + " " + locations[i][j].getName() + " \n";
            }
        }

        return description;
    }


    public static void main(String[] args) {
       
        Map map = new Map();

        Player hero = new Player(); //auto sets to Dorothy at 0,0
        System.out.println(map.toString());


    }

}
