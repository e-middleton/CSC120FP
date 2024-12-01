import java.util.ArrayList;
import java.util.Scanner;

public class Play {

    public Play(){

    }

    /**
     * Method for checking if two characters or a character and a player are in the same location
     * @param a the first character
     * @param b the second character
     * @return true or false they're in the same location
     */
    public boolean positionMatch(Character a, Character b){
        if(a.getPosition_x() == b.getPosition_x() && a.getPosition_y() == b.getPosition_y()){
            return true;
        } else{
            return false;
        }
    }

    /**
     * A method for checking if a character or player is in the same area as a given location. 
     * @param a the character or player
     * @param b the location they're checking if they're in
     * @return true or false they're in the same location
     */
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
        System.out.println("+walk (north, south, east, west) \n+lookAround \n+talk");
    }

    /**
     * Method for looking around a given location. NEED TO CHANGE TO BE THE PLAYER'S CURRENT LOCATION, NOT PASSING IN A SET LOCATION
     */
    public void lookAround(Player hero, Map map){
        map.findLocation(hero).lookAround();
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
    public void walk(String[] command, Player a, Map map){

        if(command[1].equals("north")){ //walk north
            if(map.walkNorth(a)){
                return;
            } else{
                System.out.println("Unable to walk in this direction. Please to try walk in a new direction.");
            }
        } else if(command[1].equals("east")){ //walk east
            if(map.walkEast(a)){
                return;
            } else{
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction.");
            }
        } else if(command[1].equals("south")){ //walk south
            if(map.walkSouth(a)){
                return;
            } else{
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction.");
            }
        } else if(command[1].equals("west")){ //walk west
            if(map.walkWest(a)){
                return;
            } else{
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
     * @param map finds the location the hero is currently in
     */
    public void lookAt(Player hero, String s, Map map){ 
        System.out.println("You see...");
        try{
            map.findLocation(hero).lookAtCharacter(s);
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for talking to npc's provided that they're in the same location that the player is
     * The npc's response never changes, it justs says who they are and what they trade for
     * @param hero the player
     * @param npc the name of the npc they're trying to talk to. They're grabbed from the location's cast.
     * @param map the map, used to get the player's current location
     */
    public void talk(Player hero, String npc, Map map){ 
        try{
            map.findLocation(hero).getPerson(npc).intro(hero); //
        } catch(PositionMismatchException e){
            System.out.println("You do not see any " + npc + " around. They are not in this location.");
        }
    }

    /**
     * Method for bartering with a character, checks if you are in the same location
     * then it asks what you would like to barter for, string for string, 
     * if something is mistyped, it requires the person to begin with 
     * "barter with character" all over again
     * @param hero the player who is doing the bartering
     * @param npc the name/occupation of the npc being looked for
     * @param map the map to location match
     * @param input the scanner used to get the players input/trade info
     */
    public void barter(Player hero, String npc, Map map, Scanner input){
        try{
            if(positionMatch(hero, map.findLocation(hero).getPerson(npc))){ //might not be a necessary check, CHECK
                System.out.println("What would you like to barter for? "); //barter __ for __
                String response = input.nextLine();
                String[] command = sliceAndDice(response);
                try{
                    map.findLocation(hero).getPerson(npc).barter(command[3], command[1], hero);
                } catch(IndexOutOfBoundsException b){                          //handles incorrect typing
                    System.out.println("Please enter a valid for of: barter _payment_ for _commodity_");
                } catch(RuntimeException e){                                   //if one or more object is missing
                    System.out.println(e.getMessage());
                }
            }
        } catch(MissingNPCException b){ //if the person is in the wrong location
            System.out.println("You are not in the same location as " + npc);
        }
    }
    

    /**
     * Method for knitting clothing items, checks if the item has already been knit, if it has, it does not reknit
     * @param hero the player who is knitting their clothing
     * @param command the command typed in, parsed for different options, split by white space
     * @param input the scanner used to give the player a chance to say no
     */
    public void knit(Player hero, String[] command, Scanner input){
        String response;
        if(command[1].equals("socks")){
            if(hero.hasSocks()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(response.equals("yes")){
                    try{
                        hero.knitSocks();
                    } catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                } else{
                    return; //exit the knitting method if they don't wish to continue
                }
            } else{
                try{
                    hero.knitSocks();
                } catch(RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        } else if(command[1].equals("hat")){
            if(hero.hasHat()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(response.equals("yes")){
                    try{
                        hero.knitHat();
                    } catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                } else{
                    return;
                }
            } else{
                try{
                    hero.knitHat();
                } catch(RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        } else if(command[1].equals("gloves")){
            if(hero.hasGloves()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(response.equals("yes")){
                    try{
                        hero.knitGloves();
                    } catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                } else{
                    return;
                }
            } else{
                try{
                    hero.knitGloves();
                } catch(RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        } else if(command[1].equals("sweater")){
            if(hero.hasSweater()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(response.equals("yes")){
                    try{
                        hero.knitSweater();
                    } catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                } else{
                    return;
                }
            } else{
                try{
                    hero.knitSweater();
                } catch(RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        } else if(command[1].equals("pants")){
            if(hero.hasPants()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(response.equals("yes")){
                    try{
                        hero.knitPants();
                    } catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                } else{
                    return;
                }
            } else{
                try{
                    hero.knitPants();
                } catch(RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        } else{
            System.out.println(command[1] + " is an unknown clothing item. Please choose something else to knit.");
        }
    }


    

    public static void main(String[] args) {
        Play game = new Play();
        Map map = new Map(); //default 2x2 map
        Scanner input = new Scanner(System.in);
        Player hero = new Player(); //auto sets to Dorothy at 0,0

        hero.grab("apple");
    
        System.out.println("Hello, welcome to the game!");
        System.out.println("Would you like to play? Yes to play end to end");
        String response = input.nextLine();

        //main play loop, it currently ends when the player says end.
        //also checks to see if the win condition has been met
        while(!((response.toLowerCase()).equals("end")) && !(hero.hasWon())){ 
            response = input.nextLine();
            String[] command = game.sliceAndDice(response);
            if(command[0].equals("walk")){ //walk north, east, south, west
                game.walk(command, hero, map);
            }
            else if(command[0].equals("look") && command[1].equals("around")){ //look around LOCATION (general)
                 game.lookAround(hero, map); 
            }
            else if((command[0]).equals("look") && command[1].equals("at")){ //look at PERSON
                try{
                    game.lookAt(hero, command[2], map);
                } catch(PositionMismatchException e){
                    System.out.println(command[2] + " cannot be seen, you are not in the same location.");
                }
            }
            else if(command[0].equals("show") && command[1].equals("options")){ //show options
                game.showOptions();
            }
            else if(command[0].equals("talk") && command[1].equals("to")){ //talk
                game.talk(hero, command[2], map); //player, name of the person they're talking to, map for location
            } else if(command[0].equals("drop")){ //dropping an item, adding it as a string to location inventory
                try{
                    hero.drop(command[1]);
                    map.findLocation(hero).addItem(command[1]);
                } catch(RuntimeException e){
                    System.out.println(e.getMessage());
                }
            } else if(command[0].equals("grab")){ //grabbing an item, removing it from location inventory
                if(map.findLocation(hero).containsItem(command[1])){
                    hero.grab(command[1]);
                    map.findLocation(hero).removeItem(command[1]);
                } else{
                    System.out.println("This item is not in this location. It cannot be grabbed.");
                }
            } else if(command[0].equals("knit")){ //knit (all)
                game.knit(hero, command, input);
            } else if(command[0].equals("what") && command[1].equals("can") && command[3].equals("knit")){ //see knitting options, "what can I knit" "what can she knit"
                hero.canKnit();
            } else if(command[0].equals("barter") && command[1].equals("with")){ //barter
                game.barter(hero, command[2], map, input);
            }
            else{
                System.out.println(command[0] + " is an unknown command.");
            }
        }
        
            
            //barter
            //check inventory(print) //Look at inventory?

        if(hero.hasWon()){
            System.out.println("Congratulations, you've survived winter! Nice and cozy!");
        } else{
            System.out.println("You're freezing and cold. You haven't knit your outfit. You might not survive! :(");
        }
        input.close();  
    }
        
}
