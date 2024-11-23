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

    //JANKY
    public String getCast(){
        String names = ""; //needs to be initialized
        for(int i = 0; i < this.cast.size(); i++){
            names += this.cast.get(i).getOccupation(); //gets a npc's name/occupation
            names += ", ";
        }
        return names;
    }

    public void lookAround(){
        System.out.println("You see " + getDescription());
        if(!(getCast().equals(null))){
            System.out.println("A few people catch your eye. They are ");
            System.out.println(getCast());
        }
    }

    public Character getPerson(String s){
        int index = 0; //only will get passed if the bool is changed
        boolean check = false;
        for(int i = 0; i < this.cast.size(); i++){
            if(cast.get(i).getOccupation().equals(s)){
                index = i;
                check = true;
            }
        }
        if(check){
            return cast.get(index);
        } else{
            throw new RuntimeException("This person is not in this location.");
        }
    }

    //do I need an enter/exit method?

    public static void main(String[] args) {
        Character smith = new Character("A traveling smith looking to shod horses", "smith", 0,0, new ArrayList<String>(), "flour");
        System.out.println(smith.getPosition_x() + "\n" + smith.getPosition_y());
        Character baker = new Character("A worker in a small northern town", "baker", 0, 0, new ArrayList<String>(), "gold");
        ArrayList<Character> village = new ArrayList<>();
        village.add(smith);
        village.add(baker);
        Location home = new Location("a small hovel, decrepit and falling apart.", "home", new ArrayList<String>(), 1, 1, village);
        System.out.println(home.getPerson("smith").getPosition_x());
        System.out.println(home.getPerson("smith").getPosition_y());
    }
    
}
