import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;


public class PlayTest {
    Play game = new Play(); 
    Map map = new Map(); //default 2x2 map
    Player hero = new Player(); //auto sets to Dorothy at 0,0

    //getPerson(s) is what throws the exception for lookAtCharacter()
    @Test(expected=MissingNPCException.class)
    public void getPersonFail(){
        hero.walk(0, -1); //in the garden
        map.findLocation(hero).getPerson("goblin");
    }

}
