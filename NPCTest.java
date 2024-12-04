import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class NPCTest{
    //will then have to print out the npc to make sure the test works
    ArrayList<String> inventoryA = new ArrayList<String>(Arrays.asList("gold", "gold", "myrh"));
    ArrayList<String> inventoryB = new ArrayList<String>(Arrays.asList("needles", "wool"));

    //for passing tests
    NPC a = new NPC("n/a", "a", 0, 0, inventoryA, "flour", 1);
    NPC b = new NPC("n/a", "b", 0, 0, inventoryB, "gold", 2);

    //for failing tests
    NPC c = new NPC("n/a", "a", 1, 1, inventoryA, "flour", 1);
    NPC d = new NPC("n/a", "b", 0, 0, inventoryB, "gold", 2);
 
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
        assertFalse(c.positionMatch(d));
    }

   
    @Test
    public void testBarter(){
        b.barter("needles", "gold", a);
        ArrayList<String> test = new ArrayList<String>(Arrays.asList("myrh", "needles"));
        assertEquals(test.toString(), a.getInventory());
    }

    

    //not enough gold to trade, so it should fail
    @Test(expected=RuntimeException.class)
    public void testBarterFail2(){
        b.drop("gold");
        a.barter("needles", "gold", b);
    }

    
    
}
