public class TalkingDoor extends Character{

    public TalkingDoor(String description, String occupation, int x, int y){
        super(description, occupation, x, y);
    }

    public void intro(Character a){ //overrides the super.intro() 
        System.out.println("I'm almost a String");
        System.out.println("but that isn't quite right");
        System.out.println("for how could a String");
        System.out.println("keep you warm in the night?");
        System.out.println("I'm hard here to find,");
        System.out.println("still harder to use");
        System.out.println("Here's a hint,");
        System.out.println("I make up the socks");
        System.out.println("inside of your shoes.");
        System.out.println();
        System.out.println("What am I?");
    }

    
}
