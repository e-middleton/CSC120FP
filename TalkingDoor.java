/**
 * Class for TalkingDoor which is used differently from other NPCs, 
 * Currently, only special ability is to pose riddles and unlock doors
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
     * @param intro the intro spiel given by the door when talked to
     * @param riddle the riddle posed by this specific door, may be changed for other doors
     * @param directionProtected the path currently locked, will be unlocked if the riddle is guessed
     * @param answer the correct answer to the riddle
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

    /**
     * method for intro spiel given by door when talked to
     * overrides typical NPC intro method
     * prints out an introduction and asks if you want to answer the riddle
     * @param a the necessary parameter from the intro() method in NPC, the player being talked to
     */
    @Override
    public void intro(NPC a){
        System.out.printf(this.introduction); //print formatted because of how it was written into file
    }

    /**
     * method for posing a riddle
     * prints out the doors specific riddle
     */
    public void riddle(){ 
        System.out.printf(this.riddle);
    }

    /**
     * if the user guesses correctly, the locked path is unlocked and they may continue
     * if they guess incorrectly, the path remains locked
     * @param response the user's response/guess for the riddle
     * @param location the location in the map where the door is, and where the protected path needs to be unlocked
     * @return true/false the door was unlocked/the user entered the correct response
     */
    public boolean unlockDoor(String response, Location location){
        if(response.equals(this.answer)){ //if the user enters the correct response
            System.out.println("Very well, the door sighs, you may pass.");
            System.out.println("A grinding sound of old gears turning bounces around the rock ceiling");
            System.out.println("The heavy metal door... swings open");
            location.unlockPath(directionProtected); // if directionProtected.equals("south") this.south = true in the location
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
