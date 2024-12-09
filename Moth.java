/**
 * Villain class moth, currently has limited functionality
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
            System.out.print("All of your " + yarn + " has been eaten by a moth!\n");
            if(yarn.equals("bulky weight yarn")){
                System.out.println("There is no more bulky yarn in this universe... you're doomed. Please restart. :(");
            } else if(yarn.equals("dk weight yarn")){
                System.out.println("There is no more dk yarn in this universe... you're doomed. Please restart. :(");
            }
        }
    }

}