import static org.junit.Assert.*;
import org.junit.Test;

/**
* Example unit tests for a couple Forneymon methods;
* actual unit tests should be much more complete
*/
public class ForneymonTests {

    @Test
    public void testTakeDamage() {
        Burnymon burny = new Burnymon("Dave");
        assertEquals(burny.getHealth(), 15);
        burny.takeDamage(5, "dampy");
        assertEquals(burny.getHealth(), 10);
    }

    @Test
    public void testToString() {
        Burnymon burny = new Burnymon("Dave");
        assertEquals(burny.toString(), "Dave Dave");
    }

}
