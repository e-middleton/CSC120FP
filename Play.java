import java.util.ArrayList;
import java.util.Scanner;

public class Play {

    public Play(){

    }

    public boolean positionMatch(Character a, Character b){
        if(a.getPosition_x() == b.getPosition_x() && a.getPosition_y() == b.getPosition_y()){
            return true;
        } else{
            return false;
        }
    }

    public boolean positionMatch(Character a, Location b){
        if(a.getPosition_x() == b.getPosition_x() && a.getPosition_y() == b.getPosition_y()){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Prints out all the options for commands the player is capable of using
     */
    public void showOptions(){
        System.out.println("As a player, you are capable of:");
        System.out.println("+walk (forward, backward, left, right) \n+lookAround \n+talk");
    }

    /**
     * Method for looking around a given location. NEED TO CHANGE TO BE THE PLAYER'S CURRENT LOCATION, NOT PASSING IN A SET LOCATION
     */
    public void lookAround(Location a){
        a.lookAround();
    }

    /**
    * splits up a string of multiple words based on the white space between them, converting it all to lower case to prevent confusion
    * @param s the string passed into the splitting function to be broken up
    * @return array: each index holds one word from the original string
    */
    public String[] sliceAndDice(String s){
        String[] words = s.toLowerCase().split("\\s+"); //I tried splitting along " ", but for some reason it gave me grief so I did //s+
        return words;
    }

    /**
     * Method for the player walking around, requires them to have typed in their command correctly as walk *direction* and not be walking off the map
     * @param command the command that the player typed in broken up by whitespace
     * @param a the Player
     */
    public void walk(String[] command, Player a){
        if(command[1].equals("right")){ //walk right
            try{
                a.walk(1, 0);
                System.out.println("You have successfully walked right!");
            } catch(InvalidLocationException e){
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction."); //exception handling
            }
        }
        else if(command[1].equals("left")){ //walk left
            try{
                a.walk(-1, 0);
                System.out.println("You have successfully walked left!");
            } catch(InvalidLocationException e){
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction.");
            }
        } else if(command[1].equals("forward")){ //walk forward
            try{
                a.walk(0, 1);
                System.out.println(("You have successfully walked forward!"));
            } catch(InvalidLocationException e){
                System.out.println("Unable to walk in this direction. Please to try walk in a new direction.");
            }
        }
        else if(command[1].equals("backward")){ //walk backward
            try{
                a.walk(0, -1);
                System.out.println(("You have successfully walked backward!"));
            } catch(InvalidLocationException e){
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction.");
            } 
        } else{
            System.out.println(command[1] + " Is an unknown direction. Please try to walk in a different direction."); //typo
        }
    }

    /**
     * Method for looking at an npc and printing out their description based on their name/occupation.
     * Will only work if player and npc are in the same location/the npc is in the given location.
     * @param hero the player who is looking around
     * @param s the name/occupation of the character they are trying to look at
     * @param b the location the hero is currently in, should be changed when map is constructed
     */
    public void lookAt(Player hero, String s, Location b){ //for now I need to pass in a location, but I should later be able to just use the map indexes?
        if(positionMatch(hero, b)){ //making sure the hero and location are the same position in the map
            try{
                System.out.println("you see...");
                b.lookAtCharacter(s); //goes into the location, checks to see if the person's name is in the cast, then grabs their description
            } catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        } else{
            throw new PositionMismatchException();
        }
    }


    

    public static void main(String[] args) {
        Play game = new Play();
        Scanner input = new Scanner(System.in);
        Player hero = new Player(); //auto sets to Dorothy at 0,0

        Character smith = new Character("A traveling smith looking to shod horses", "smith", 0,0, new ArrayList<String>(), "flour");
        Character baker = new Character("A worker in a small northern town", "baker", 0, 0, new ArrayList<String>(), "gold");
        ArrayList<Character> village = new ArrayList<>();
        village.add(smith);
        village.add(baker);
        Location home = new Location("a small hovel, decrepit and falling apart.", "home", new ArrayList<String>(), 0, 0, village);


        System.out.println("Hello, welcome to the game!");
        System.out.println("Would you like to play? Yes to play end to end");
        game.showOptions();
        String response = input.nextLine();

        while(!((response.toLowerCase()).equals("end"))){
            response = input.nextLine();
            String[] command = game.sliceAndDice(response);
            if(command[0].equals("walk")){ //walk right
                System.out.println(command[0] + command[1]);
                game.walk(command, hero);
            }
            else if(command[0].equals("look") && command[1].equals("around")){ //look around Location (general)
                 game.lookAround(home); //NEED TO PASS IN PLAYER/THEIR CURRENT LOCATION IN MAP, NOT SET LOCATION
            }
            else if((command[0]).equals("look") && command[1].equals("at")){ //look at __PERSON?__
                try{
                    game.lookAt(hero, command[2], home);
                } catch(PositionMismatchException e){
                    System.out.println(command[2] + " cannot be seen, you are not in the same location.");
                }
            }
            else if(command[0].equals("show") && command[1].equals("options")){ //show options
                game.showOptions();
            }

        
            

    
        

        }
        input.close();  
    }
        
}
