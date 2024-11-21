import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class CharacterTest {

    @Test //they must be in the same location to talk
    public void testTalk(){
        Character a = new Character("n/a", "a", 0, 0, "question");
        Character b = new Character("n/a", "b", 0, 0, "response");

        assertEquals("response", (a.talk(b)));
    }

    @Test
    public void testPositionMatch(){
        Character a = new Character("n/a", "a", 0, 0, "question");
        Character b = new Character("n/a", "b", 0, 0, "response");

        assertTrue(a.positionMatch(b));
    }

    @Test
    public void testPositionMatchFalse(){
        Character a = new Character("n/a", "a", 1, 1, "question");
        Character b = new Character("n/a", "b", 0, 0, "response");
        assertFalse(a.positionMatch(b));
    }

    //horrendous test, way too complicated
    @Test
    public void testBarter(){
        ArrayList<String> inventoryA = new ArrayList<String>();
        inventoryA.add("gold");
        inventoryA.add("wool");
        
        ArrayList<String> inventoryB = new ArrayList<String>();
        inventoryB.add("nails");
        inventoryB.add("needles");

        Character a = new Character("n/a", "a", 0, 0, inventoryA, new ArrayList<String>(), "n/a");
        Character b = new Character("n/a", "b", 0, 0, inventoryB, new ArrayList<String>(), "n/a");

        b.barter("needles", "gold", a);

        ArrayList<String> test = new ArrayList<String>();
        test.add("wool");
        test.add("needles");
    
        assertEquals(test.toString(), a.getInventory());
    }
    
}
