import org.junit.Test;
import static org.junit.Assert.*;


public class PlayTest {
    Play game = new Play(); 
    Map map = new Map("locations", "population", "locationInventory"); //default 4x4
    Player hero = new Player(); //auto sets to Dorothy 

    //getPerson(s) is what throws the exception for lookAtCharacter()
    @Test(expected=MissingNPCException.class)
    public void getPersonFail(){
        hero.walk(0, -1); //in the garden
        map.findLocation(hero).getPerson("goblin");
    }

    @Test
    public void testMothEat(){
        map.getLocation(1, 2).addItem(new Item("dk yarn"));
        hero.grab("dk yarn", map.getLocation(1, 2));
        assertEquals(true, hero.hasYarn()); //player has yarn before walking in
        String[] command = game.sliceAndDice("walk north");
        game.walk(command, hero, map); //walking up one into the empty field where a Moth is
        assertEquals(false, hero.hasYarn()); //hero no longer has the yarn after encountering Moth
    }
   
}
