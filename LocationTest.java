import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationTest {
    //uses same characters and inventories as character test
    ArrayList<String> inventoryA = new ArrayList<String>(Arrays.asList("gold", "myrh"));
    ArrayList<String> inventoryB = new ArrayList<String>(Arrays.asList("needles", "wool"));
    Character a = new Character("n/a", "a", 0, 0, inventoryA, "flour");
    Character b = new Character("n/a", "b", 0, 0, inventoryB, "gold");
    ArrayList<Character> village = new ArrayList<>(Arrays.asList(a, b));
    Location homeBase = new Location("a small hovel, decrepit and falling apart.", "home", new ArrayList<String>(), 1, 1, village);
    
    @Test
    public void testGetPerson(){
        Character c = homeBase.getPerson("a");
        assertEquals(a, c); //it should be the exact same object
    }
} 
