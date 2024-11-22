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
    
}
