import java.util.Scanner;

/**
 * Play class, contains all the necessary methods for the text-based game to work with user input.
 */
public class Play {

    /**
     * Constructor for Play(), no necessary attributes.
     */
    public Play(){
    }

    /**
     * Method for checking if two characters or a character and a player are in the same location
     * most location testing is taken care of in the Location class
     * @param a the first character
     * @param b the second character
     * @return true or false they're in the same location
     */
    public boolean positionMatch(NPC a, NPC b){
        return (a.getPositionX() == b.getPositionX() && a.getPositionY() == b.getPositionY());
    }

    /**
     * A method for checking if a npc or player is in the same area as a given location. 
     * the majority of location testing is now done in the Location class
     * @param a the npc or player
     * @param b the location they're checking if they're in
     * @return true or false they're in the same location
     */
    public boolean positionMatch(NPC a, Location b){
        return (a.getPositionX() == b.getPositionX() && a.getPositionY() == b.getPositionY());
    }

    /**
     * Prints out all the options for commands the player is capable of using
     */
    public void showOptions(){
        System.out.println("As a player, you are capable of:");
        System.out.println("+walk [cardinal direction] \n+look around \n+talk to [person] \n+look at [person] \n+barter with [person] \n+what can I knit \n+knit [clothing item] \n+check inventory \n+check outfit \n+grab [item] \n+drop [item] \n+examine[item]");
    }

    /**
     * Method for looking around a given location.
     * prints out a description of their surroundings
     * @param hero the player who is doing the looking around
     * @param map the map used to get the Location of the player
     */
    public void lookAround(Player hero, Map map){
        map.findLocation(hero).lookAround();
    }

    /**
     * Method for removing punctuation from a given string, (also removes whitespace and numbers)
     * @param s the String being edited to have no punctuation
     * @return the same string without punctuation or whitespace or numbers
     */
    public String punctuationRemoval(String s){
        char[] charArray = s.toCharArray(); //changes it to an array of char
        Character[] characterArray = new Character[charArray.length]; 

        //makes it a Character array instead of char
        for (int i = 0; i < charArray.length; i++) {
            characterArray[i] = Character.valueOf(charArray[i]); 
        }

        //builds the new String, leaving behind anything not a letter 
        String result = "";        
        for (int i = 0; i<characterArray.length; i++) { 
            if (Character.isLetter(characterArray[i])) { 
                result += characterArray[i];
            }
        }
        return result;
    }

    /**
    * splits up a string of multiple words based on the white space between them, converting it all to lower case to prevent confusion
    * @param s the string passed into the splitting function to be broken up
    * @return array: each index holds one word from the original string
    */
    public String[] sliceAndDice(String s){
        String[] words = s.toLowerCase().split("\\s+"); //splits along whitespace
        String[] result = new String[words.length];

        //removes punctuation and numbers
        for (int i = 0; i < words.length; i++) { 
            result[i] = punctuationRemoval(words[i]);
        }
        return result;
    }

    /**
     * Method for the player walking around, requires them to have typed in their command correctly as walk [direction] and not be walking off the map
     * alerts the player whether or not a moth is in a location, and if applicable, calls the moth to eat their yarn
     * @param command the command that the player typed in broken up by whitespace
     * @param a the Player
     * @param map the map, used to get t/f the player is able to walk to the next Location
     */
    public void walk(String[] command, Player a, Map map) {
        if (command[1].equals("north")) { //walk north
            if (map.walkNorth(a)) {
                if (map.findLocation(a).hasMoth()) { //if there's a moth
                    System.out.println("Oh no! There's a moth in here!");
                    map.findLocation(a).getMoth().eat(a); //moth eats yarn
                }
                return;
            } else {
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction.");
            }
        } else if(command[1].equals("east")){ //walk east
            if(map.walkEast(a)){
                if(map.findLocation(a).hasMoth()){ //if there's a moth
                    System.out.println("Oh no! There's a moth in here!");
                    map.findLocation(a).getMoth().eat(a); //moth eats yarn
                }
                return;
            } else{
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction.");
            }
        } else if(command[1].equals("south")){ //walk south
            if(map.walkSouth(a)){
                if(map.findLocation(a).hasMoth()){ //if there's a moth
                    System.out.println("Oh no! There's a moth in here!");
                    map.findLocation(a).getMoth().eat(a); //moth eats yarn
                }
                return;
            } else{
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction.");
            }
        } else if(command[1].equals("west")){ //walk west
            if(map.walkWest(a)){
                if(map.findLocation(a).hasMoth()){ //if there's a moth
                    System.out.println("Oh no! There's a moth in here!");
                    map.findLocation(a).getMoth().eat(a); //moth eats yarn
                }
                return;
            } else{
                System.out.println("Unable to walk in this direction. Please try to walk in a new direction.");
            }
        } else{
            System.out.println(command[1] + " is an unknown direction. Please try to walk in a different direction."); //typo
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
        map.findLocation(hero).lookAtCharacter(s); //exception handling in Location
    }

    /**
     * Method for answering the riddle the door sets for the player, 
     * if correctly answered, player is able to go south into the yarn trove
     * @param hero the player answering the riddle
     * @param map for the location of the player and door
     * @param input the Scanner used to get the player's guesses
     */
    public void answerRiddle(Player hero, Map map, Scanner input){
        try{
            TalkingDoor door = (TalkingDoor)map.findLocation(hero).getPerson("door");
    
            door.intro(hero);
            String response = input.nextLine();
            String cleanResponse = punctuationRemoval(response).toLowerCase(); //scrub out punctuation, whitespace, etc.
            if(cleanResponse.equals("yes")){
                System.out.println();
                door.riddle();
                response = input.nextLine();
                String cleanResponse2 = punctuationRemoval(response).toLowerCase();
                if(door.unlockDoor(cleanResponse2, map.findLocation(hero))){
                   return;
                } else{
                    System.out.println("I'm sorry, that is incorrect. You have one guess remaining."); //one more chance
                    System.out.println("What am I?");
                    response = input.nextLine();
                    String cleanResponse3 = punctuationRemoval(response).toLowerCase();
                    if(door.unlockDoor(cleanResponse3, map.findLocation(hero))){ 
                       return;
                    } else {
                        System.out.println("I'm sorry, you may not pass.");
                    }
                }
            } else{
                System.out.println("I understand, fair travels.");
                return; //they don't want to answer his riddle
            }
        } catch (MissingNPCException e){
            System.out.println("There is no door to talk to in this location.");
        } catch (Exception e2){
            System.out.println(e2.getMessage());
        }
    }
    

    /**
     * Method for talking to npcs provided that they're in the same location that the player is
     * The npc's response never changes, it justs says who they are and what they trade for
     * @param hero the player
     * @param npc the name of the npc they're trying to talk to. They're grabbed from the location's cast.
     * @param map the map, used to get the player's current location
     * @param input scanner needed to get user's response if they are talking to the door
     */
    public void talk(Player hero, String npc, Map map, Scanner input) { 
        if (npc.equals("door")) {
            answerRiddle(hero, map, input);
        } else {
            try{
                map.findLocation(hero).getPerson(npc).intro(hero); //MissingNPCException in Location if not found
            } catch (MissingNPCException e) {
                try{
                    System.out.println(map.findLocation(hero).getItem(npc).getResponse()); //if they typed an object name, they'll get a snarky response
                } catch(MissingMaterialException b){
                    System.out.println("There is nobody named " + npc + " in the " + map.findLocation(hero).getName()); 
                }
            }
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
            boolean success = false;
            String commodity = "";

            map.findLocation(hero).getPerson(npc); //checks for matching location
            NPC character = map.findLocation(hero).getPerson(npc); //the npc being bartered with
            if(character.getOccupation().equals("door")){ //YOU CANNOT BARTER WITH DOOR
                character.barter("", "", hero);
                return;
            }

            while(!success){ //until you enter a valid item
                System.out.println("If " + character.getOccupation() + " doesn't have anything you want, type STOP");
                System.out.println("The " + character.getOccupation() + " has " + character.getInventory()); //the bartering options
                System.out.println("What would you like to barter for? "); //which object does the player want?
                String response = input.nextLine();
                if(punctuationRemoval(response).toLowerCase().equals("stop")){ //break out of method
                    System.out.println("Exiting bartering... please enter next command:");
                    return;
                }
                String[] trade = sliceAndDice(response); //item the player wants
                
                for(int i = 0; i<trade.length; i++){ //fixes issue of item being [type] yarn (two words)
                    if(trade[i].equals("yarn")){
                        commodity = trade[i-1] + " " + trade[i];
                    } else{
                        commodity = trade[0];
                    }
                }
                if(character.checkInventory(commodity)){   //does the npc have the item?
                    success = true; //break from loop
                } else {
                    System.out.println("I'm sorry, the " + character.getOccupation() + " does not have " + commodity);
                    System.out.println();
                }
            }
            
            //gets the player's payment
            System.out.println("Remember, they want (" + (character.getWantsNum() - character.getHasNum()) + ") " + character.getWant());
            System.out.println("What is your payment? ");
            String payment = punctuationRemoval(input.nextLine()).toLowerCase();
            if(hero.checkInventory(payment)){
                try{
                    character.barter(commodity, payment, hero);
                } catch (RuntimeException e){
                    System.out.println(e.getMessage()); //the npc doesn't want that item or player doesn't have enough
                }
            } else {
                System.out.println("You do not appear to have " + payment + " in your inventory.");
                System.out.println("Please obtain this item and come back to barter after!");
                return;
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
                if(punctuationRemoval(response.toLowerCase()).equals("yes")){ //removes any punctuation or whitespace and is lowercase
                    try{
                        hero.knitSocks();
                    } catch(MissingMaterialException e){
                        System.out.println("You have insufficient yarn for this action");
                    }
                } else{
                    return; //exit the knitting method if they don't wish to continue
                }
            } else{ //if they don't already have socks there's no need to ask the question
                try{
                    hero.knitSocks();
                } catch(MissingMaterialException e){
                    System.out.println("You have insufficient yarn for this action");
                }
            }
        } else if(command[1].equals("hat")){
            if(hero.hasHat()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(punctuationRemoval(response.toLowerCase()).equals("yes")){ 
                    try{
                        hero.knitHat();
                    } catch(MissingMaterialException e){
                        System.out.println("You have insufficient yarn for this action");
                    }
                } else{
                    return;
                }
            } else{
                try{
                    hero.knitHat();
                } catch(MissingMaterialException e){
                    System.out.println("You have insufficient yarn for this action");
                }
            }
        } else if(command[1].equals("gloves")){
            if(hero.hasGloves()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(punctuationRemoval(response.toLowerCase()).equals("yes")){
                    try{
                        hero.knitGloves();
                    } catch(MissingMaterialException e){
                        System.out.println("You have insufficient yarn for this action");
                    }
                } else{
                    return;
                }
            } else{
                try{
                    hero.knitGloves();
                } catch(MissingMaterialException e){
                    System.out.println("You have insufficient yarn for this action");
                }
            }
        } else if(command[1].equals("sweater")){
            if(hero.hasSweater()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(punctuationRemoval(response.toLowerCase()).equals("yes")){
                    try{
                        hero.knitSweater();
                    } catch(MissingMaterialException e){
                        System.out.println("You have insufficient yarn for this action");
                    }
                } else{
                    return;
                }
            } else{
                try{
                    hero.knitSweater();
                } catch(MissingMaterialException e){
                    System.out.println("You have insufficient yarn for this action");
                }
            }
        } else if(command[1].equals("pants")){
            if(hero.hasPants()){
                System.out.println("You have already knit this item, are you sure you'd like to continue? yes/no");
                response = input.nextLine();
                if(punctuationRemoval(response.toLowerCase()).equals("yes")){
                    try{
                        hero.knitPants();
                    } catch(MissingMaterialException e){
                        System.out.println("You have insufficient yarn for this action");
                    }
                } else{
                    return;
                }
            } else{
                try{
                    hero.knitPants();
                } catch(MissingMaterialException e){
                    System.out.println("You have insufficient yarn for this action");
                }
            }
        } else{
            System.out.println(command[1] + " is an unknown clothing item. Please choose something else to knit.");
        }
    }

    /**
     * Main function for Play(), contains the while loop for the game, ends when win condition is reached, or end is typed out
     * @param args 
     */
    public static void main(String[] args) {
        Play game = new Play();
        Map map = new Map("locations", "population", "locationInventories"); //default from .txt files
        Scanner input = new Scanner(System.in);
        Player hero = new Player(); //auto sets to Dorothy at 0,0
        int counter = 0;
        System.out.println("Hello, welcome to the game! \nWould you like to play? Yes to play end to end");
        String response = input.nextLine();

        //main play loop, it currently ends when the player says end or with win condition
        outerloop:
        while((!((response.toLowerCase()).equals("end"))) && !(hero.hasWon())){ 
            if(counter == 0){ //BEGINNING INFORMATION
                System.out.println("Due to a series of woes, you find yourself tragically unprepared for winter, and in a broken down house.");
                System.out.println("You must travel around the land to collect yarn to knit yourself an outfit in order to survive the cold to come.");
                System.out.println("Good luck! \nYou begin in the house. Type HELP for options.");
            } else if(counter == 50){ //Seasons + passing time
                System.out.println("\nHmm... the weather seems to be getting a bit colder...");
            } else if(counter == 100){
                System.out.println("\nYou're starting to feel a bit nervous, the trees are turning for fall.");
            } else if(counter == 150){
                System.out.println("\nYou only have a few weeks left before the cold front hits!");
            } else if (counter == 200){
                System.out.println("\nYou are out of time. It is already winter.");
                break outerloop; //game ends, time is up
            }
            response = input.nextLine();
            String[] command = game.sliceAndDice(response); //removes punctuation, whitespace, numbers, uppercase, and separates

            //determines which command executes, instead of if, else if, else formatting
            switch(command[0]){
                case("walk"): //walk north, east, south, west
                    try{
                        game.walk(command, hero, map);
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Please enter in the valid form: walk _cardinal direction_");
                    }
                    break;
                case("look"): //look around LOCATION (general) && look at PERSON
                    try{
                        if(command[1].equals("around")){ //location
                            game.lookAround(hero, map);
                        } else if(command[1].equals("at")){ //person
                            game.lookAt(hero, command[2], map);
                        }
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Please enter the valid form: look around or look at _person_");
                    }
                    break;
                case("help"): //show options
                    game.showOptions();
                    break;
                case("talk"): //talk to [person] OR talk to [the] [person]
                    try{
                        if(command[1].equals("to") && command[2].equals("the")){ //talk to the _person_
                            game.talk(hero, command[3], map, input);
                        } else if (command[1].equals("to")){
                            game.talk(hero, command[2], map, input); //player, name of the person they're talking to, map for location, input is for riddles
                        }
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Please enter in the form: talk to _person_");
                    }
                    break;
                case("drop"): //dropping an item, adding it as a string to location inventory and mutable description
                    String thing = "";
                    if(response.contains("yarn")){ //makes it possible to drop [weight] yarn (2 words)
                        thing = command[1] + " " + command[2];
                    } else{
                        thing = command[1];
                    }
                    try{
                        if(hero.checkInventory(thing)){ //if they actually have the thing they claim they do
                            map.findLocation(hero).addItem(hero.getItem(thing)); //it is added to the location's inventory and description
                        }
                        hero.drop(thing); //removed from player's inventory
                        System.out.println(thing + " has been removed from your inventory.");
                    } catch(IndexOutOfBoundsException e){                   //incorrect typing
                        System.out.println("Please enter in the form: drop _item_");
                    } catch(RuntimeException e){                            //not an item they actually have
                        System.out.println(e.getMessage());
                    }
                    break;
                case("grab"): //grab
                    String item = "";
                    try{
                        if(response.contains("yarn")){ //makes it possible to pick up [weight] yarn (2 words)
                            item = command[1] + " " + command[2];
                        } else{
                            item = command[1];
                        }
                        if(map.findLocation(hero).containsItem(item)){ //if the object is actually in the location
                            hero.grab(item, map.findLocation(hero)); //adds it to the player's inventory from the location's inventory
                            map.findLocation(hero).removeItem(item); //removes it from location inventory and description
                        } else{
                            System.out.println(item + " cannot be grabbed."); //the item does not exist in the location
                        }
                    }catch(IndexOutOfBoundsException e){ //incorrect typing
                        System.out.println("Please enter in the form: grab _item_");
                    }
                    break;
                case("knit"): //knit (all)
                    try{
                        game.knit(hero, command, input);
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Please enter in the form: knit _item_");
                    }
                    break;
                case("what"): //see knitting options, "what can I knit" "what can she knit" 
                    try{
                        if(command[1].equals("can") && command[3].equals("knit")){
                            hero.canKnit();
                        }
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Please enter in the form: what can I knit");
                    }
                    break;
                case("barter"): //barter
                    try{
                        if(command[1].equals("with")){
                            if(command[2].equals("the")){
                                game.barter(hero, command[3], map, input);
                            } else {
                                game.barter(hero, command[2], map, input);
                            }
                        } else { 
                            System.out.println("Please enter valid form: Barter with _person_");
                        }
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Please enter in a valid form: barter with _person_");
                    }
                    break;
                case("check"): //check inventory / outfit
                    try{
                        if(command[1].equals("inventory")){
                            hero.checkInventory(); //prints out inventory as String
                        } else if(command[1].equals("outfit")){
                            hero.showOutfit(); //prints out outfit as String
                        } else{ //NECESSARY DON'T REMOVE
                            System.out.println("Please enter in the valid form: check inventory/outfit");
                        }
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Please enter in the valid form: check inventory/outfit");
                    }
                    break;
                case("examine"):
                    try{
                        System.out.println(map.findLocation(hero).getItem(command[1]).getDescription());
                    } catch(MissingMaterialException e){
                        System.out.println("There is no " + command[1] + " in this location.");
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Please enter in the valid form: examine [item]");
                    }
                    break;
                case("end"):
                    break outerloop; //END
                default:
                    System.out.println(response + " is an unknown command.");
            }
            counter += 1; //passing time
        }
        if(hero.hasWon()){
            System.out.println("\n!!!Congratulations!!!\nYou've survived winter! Nice and cozy!");
        } else{
            System.out.println("You're freezing and cold. You haven't knit your outfit. You did not survive! :(");
        }
        input.close();  
    }
}
