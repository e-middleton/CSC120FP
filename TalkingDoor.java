/**
 * Class for TalkingDoor which is used differently from other NPCs, 
 * only special ability is to pose riddles instead of barter
 */
public class TalkingDoor extends NPC{

    /**
     * Constructor for talking door
     * @param description the description of how the door appears
     * @param occupation name/id
     * @param x x or column position
     * @param y y or row position
     */
    public TalkingDoor(String description, String occupation, int x, int y){
        super(description, occupation, x, y);
    }

    /**
     * method for posing a riddle, overrides the regular intro speech that other npc's have
     * @param a NPC the intro is being given to
     */
    @Override
    public void intro(NPC a){ //overrides the super.intro() 
        System.out.println("I'm almost a String");
        System.out.println("but that isn't quite right");
        System.out.println("for how could a String");
        System.out.println("keep you warm in the night?");
        System.out.println("I'm hard here to find,");
        System.out.println("still harder to use");
        System.out.println("Here's a hint,");
        System.out.println("I make up socks");
        System.out.println("inside of your shoes.");
        System.out.println();
        System.out.println("What am I?");
    }

    @Override
    public void barter(String trade, String payment, NPC player){ 
        System.out.println("The door is not quite sure what you think he can barter. \nHe is a door.");
    }
    
    

    
}
