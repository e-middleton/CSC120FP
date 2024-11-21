import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CharacterTest {
    //array for different attributes/randomize them
    //will then have to print out the character to make sure the test works
    ArrayList<String> inventoryA = new ArrayList<String>(Arrays.asList("gold", "myrh"));
    ArrayList<String> inventoryB = new ArrayList<String>(Arrays.asList("needles", "wool"));

    Character a = new Character("n/a", "a", 0, 0, inventoryA, "flour");
    Character b = new Character("n/a", "b", 0, 0, inventoryB, "gold");

 
    @Test
    public void testPositionMatch(){
        try{
            assertTrue(a.positionMatch(b));
        } catch(PositionMismatchException e){
            System.out.println(e);
        }
    }

    @Test
    public void testPositionMatchFalse(){
        Character c = new Character("n/a", "a", 1, 1);
        Character d = new Character("n/a", "b", 0, 0);
        assertFalse(c.positionMatch(d));
    }

    //horrendous test, way too complicated
    @Test
    public void testBarter(){
        b.barter("needles", "gold", a);

        ArrayList<String> test = new ArrayList<String>();
        test.add("myrh");
        test.add("needles");
    
        assertEquals(test.toString(), a.getInventory());
    }
    
}
