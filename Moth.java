/**
 * Villain class moth
 */
public class Moth{

    /**
     * constructor for Moth, no attributes needed
     */
    public Moth(){

    }

    /**
     * Method for eating yarn, checks if the player has any yarn, 
     * and if they do, it is eaten and it prints which type has been eaten
     * @param a the player whos yarn is eaten
     */
    public void eat(Player a){
        if(a.hasYarn()){
            String yarn = a.eatYarn();
            System.out.print("All of your " + yarn + " has been eaten by a moth!");
        }
    }

}