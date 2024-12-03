import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationTest {
    //uses same characters and inventories as character test
    ArrayList<String> inventoryA = new ArrayList<String>(Arrays.asList("gold", "myrh"));
    ArrayList<String> inventoryB = new ArrayList<String>(Arrays.asList("needles", "wool"));
    Character a = new Character("n/a", "a", 0, 0, inventoryA, "flour", 1);
    Character b = new Character("n/a", "b", 0, 0, inventoryB, "gold", 2);
    ArrayList<Character> village = new ArrayList<>(Arrays.asList(a, b));
    Location homeBase = new Location("a small hovel, decrepit and falling apart.", "home", new ArrayList<String>(), 1, 1, village, true, true, true, true, false);
    
    @Test
    public void testGetPerson(){
        Character c = homeBase.getPerson("a");
        assertEquals(a, c); //it should be the exact same object
    }

    @Test
    public void testGetCast(){
        String test = "The a, and b";
        assertEquals(test, homeBase.getCast());
    }

    @Test(expected=MissingNPCException.class)
    public void testGetPersonFail(){
        homeBase.getPerson("c"); //"c" is not in this location
    }
} 
