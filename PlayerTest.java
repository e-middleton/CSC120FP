import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest {
    ArrayList<String> inventoryA = new ArrayList<String>(Arrays.asList("gold", "wool"));
    ArrayList<String> inventoryB = new ArrayList<String>(Arrays.asList("needles", "cedar"));

    Player dorothy = new Player("an old woman", "protagonist", 0, 0, inventoryA);
    Character b = new Character("n/a", "b", 0, 0, inventoryB, "gold");

    @Test
    public void testBarter(){
        b.barter("needles", "gold", dorothy);
        ArrayList<String> test = new ArrayList<String>(Arrays.asList("wool", "needles"));
        //it's checking them both as String objects, not arrayLists
        assertEquals(test.toString(), dorothy.getInventory());
    }

    @Test
    public void testWalk(){
        dorothy.walk(1, 1);
        assertEquals(dorothy.getPosition_x(), 1); //assuming she starts at position 0, 0 (should probably always initialize her in the same spot tbh)
        assertEquals(dorothy.getPosition_y(), 1);
    }

    @Test(expected=InvalidLocationException.class)
    public void testWalkFail(){
        dorothy.walk(-1, 0);
    }

    @Test(expected=InvalidMovementException.class)
    public void testWalkJump(){
        dorothy.walk(1, 3);
    }

    
}
