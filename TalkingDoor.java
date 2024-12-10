/**
 * Class for TalkingDoor which is used differently from other NPCs, 
 * Currently, only special ability is to pose riddles instead of barter
 */
public class TalkingDoor extends NPC{
    private String introduction;
    private String riddle;
    private String directionProtected; //which path are they protecting (north, south, east, west)
    private String answer;

    /**
     * Constructor for talking door
     * @param description the description of how the door appears
     * @param occupation name/id
     * @param x x or column position
     * @param y y or row position
     */
    public TalkingDoor(String description, String occupation, String intro, String riddle, String directionProtected, String answer, int x, int y){
        super(description, occupation, x, y);
        this.introduction = intro;
        this.riddle = riddle;
        this.directionProtected = directionProtected;
        this.answer = answer;
    }

    @Override
    public void intro(NPC a){
        System.out.printf(this.introduction); //print formatted because of how it was written into file
    }

    @Override
    public void riddle(){ //overrides the super.intro() 
        System.out.printf(this.riddle);
    }

    @Override
    public boolean unlockDoor(String response, Location location){
        if(response.equals(this.answer)){ //if the user enters the correct response
            System.out.println("Very well, the door sighs, you may pass.");
            System.out.println("A grinding sound of old gears turning bounces around the rock cieling");
            System.out.println("The heavy metal door... swings open");
            location.unlockPath(directionProtected);
            return true;
        } else {
            return false; //the riddle was guessed incorrectly
        }
    }

    
    /**
     * Method for bartering, inherited from NPC, but should not be used unless additional functionality
     * is given to the talkingDoor
     * @param trade the object the player wants
     * @param payment the payment the player is giving
     * @param player the person bartering with the npc
     */
    @Override
    public void barter(String trade, String payment, NPC player){ 
        System.out.println("The door is not quite sure what you think he can barter. \nHe is a door.");
    }
    
}
