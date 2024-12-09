import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest {
    ArrayList<String> inventoryA = new ArrayList<String>(Arrays.asList("gold", "wool"));
    ArrayList<String> inventoryB = new ArrayList<String>(Arrays.asList("needles", "cedar"));

    Player dorothy = new Player("an old woman", "protagonist", 0, 0, inventoryA);
    NPC b = new NPC("n/a", "b", 0, 0, inventoryB, "gold", 1);

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


    @Test(expected=InvalidMovementException.class)
    public void testWalkJump(){
        dorothy.walk(1, 3);
    }

    @Test(expected=MissingMaterialException.class)
    public void testKnitSocksFail(){
        dorothy.knitSocks();
    }

    @Test(expected=MissingMaterialException.class)
    public void testKnitGlovesFail(){
        dorothy.knitGloves();
    }
    
    @Test(expected=MissingMaterialException.class)
    public void testKnitHatFail(){
        dorothy.knitHat();
    }

    @Test(expected=MissingMaterialException.class)
    public void testKnitSweaterFail(){
        dorothy.knitSweater();
    }

    @Test(expected=MissingMaterialException.class)
    public void testKnitPantsFail(){
        dorothy.knitPants();
    }

    @Test
    public void testKnitSocks(){
        dorothy.grab("lace yarn");
        dorothy.knitSocks();
        assertEquals(true, dorothy.hasSocks());
    }

    @Test
    public void testKnitGloves(){
        dorothy.grab("lace yarn");
        dorothy.knitGloves();
        assertEquals(true, dorothy.hasGloves());
    }

    @Test
    public void testKnitHat(){
        dorothy.grab("dk yarn");
        dorothy.knitHat();
        assertEquals(true, dorothy.hasHat());
    }

    @Test
    public void testKnitSweater(){
        dorothy.grab("bulky yarn");
        dorothy.knitSweater();
        assertEquals(true, dorothy.hasSweater());
    }

    @Test
    public void testKnitPants(){
        dorothy.grab("worsted yarn");
        dorothy.knitPants();
        assertEquals(true, dorothy.hasPants());
    }
}
