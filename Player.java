import java.util.Hashtable;

public class Player extends Character{
    private Hashtable<String, Boolean> outfit; //change to item class later

    public Player(String description, String occupation, int position_x, int position_y){
        super(description, occupation, position_x, position_y);
        this.outfit = new Hashtable<String, Boolean>(); //winter outfit begins empty, or should it begin full + false?
    }
    
}
