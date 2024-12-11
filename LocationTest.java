import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationTest {
    //uses same characters and inventories as npc test
    ArrayList<Item> inventoryA = new ArrayList<Item>(Arrays.asList(new Item("gold"), new Item("myrh")));
    ArrayList<Item> inventoryB = new ArrayList<Item>(Arrays.asList(new Item("needles"), new Item("wool")));
    NPC a = new NPC("n/a", "a", 0, 0, inventoryA, "flour", 1);
    NPC b = new NPC("n/a", "b", 0, 0, inventoryB, "gold", 2);
    ArrayList<NPC> village = new ArrayList<>(Arrays.asList(a, b));
    Location homeBase = new Location("a small hovel, decrepit and falling apart.", "home", new ArrayList<Item>(), 1, 1, village, true, true, true, true, false);
    
    @Test
    public void testGetPerson(){
        NPC c = homeBase.getPerson("a");
        assertEquals(a, c); //it should be the exact same object
    }

    @Test
    public void testGetCast(){
        String test = "The a, and the b";
        assertEquals(test, homeBase.getCast());
    }

    @Test(expected=MissingNPCException.class)
    public void testGetPersonFail(){
        homeBase.getPerson("c"); //"c" is not in this location
    }

    @Test
    public void testContainsItem(){
        homeBase.addItem(new Item("cup", "not a great conversation to be honest", "A regular glass cup with a small crack in the handle"));
        assertEquals(true, homeBase.containsItem("cup"));
    }

    @Test
    public void testRemoveItem(){
        //there is a cup
        homeBase.addItem(new Item("cup", "not a great conversation to be honest", "A regular glass cup with a small crack in the handle"));
        homeBase.removeItem("cup");
    }

} 
