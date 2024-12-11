import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest {
    ArrayList<Item> inventoryA = new ArrayList<Item>(Arrays.asList(new Item("gold"), new Item("wool")));
    ArrayList<Item> inventoryB = new ArrayList<Item>(Arrays.asList(new Item("needles"), new Item("cedar")));
    Location home = new Location("", "home", new ArrayList<Item>(Arrays.asList(new Item("lace yarn"), new Item("worsted yarn"), new Item("dk yarn"), new Item("bulky yarn"))), 0, 0, new ArrayList<NPC>(), true, true, true, true, false);
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
        assertEquals(dorothy.getPositionX(), 1); //assuming she starts at position 0, 0 (should probably always initialize her in the same spot tbh)
        assertEquals(dorothy.getPositionY(), 1);
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
        dorothy.grab("lace yarn", home);
        dorothy.knitSocks();
        assertEquals(true, dorothy.hasSocks());
    }

    @Test
    public void testKnitGloves(){
        dorothy.grab("lace yarn", home);
        dorothy.knitGloves();
        assertEquals(true, dorothy.hasGloves());
    }

    @Test
    public void testKnitHat(){
        dorothy.grab("dk yarn", home);
        dorothy.knitHat();
        assertEquals(true, dorothy.hasHat());
    }

    @Test
    public void testKnitSweater(){
        dorothy.grab("bulky yarn", home);
        dorothy.knitSweater();
        assertEquals(true, dorothy.hasSweater());
    }

    @Test
    public void testKnitPants(){
        dorothy.grab("worsted yarn", home);
        dorothy.knitPants();
        assertEquals(true, dorothy.hasPants());
    }
}
