import static org.junit.Assert.*;

import org.junit.Test;

public class YarnTests {

    @Test
    public void testYarn() {
        Yarn ball = new Yarn();
    }

    @Test
    public void testIsEmpty() {
        Yarn ball = new Yarn();
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());
        //My Tests!
        Yarn ball2 = new Yarn();
        ball2.insert("");
        assertFalse(ball.isEmpty());
    }

    @Test
    public void testGetSize() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        //My Tests!
        Yarn ball2 = new Yarn();
        assertEquals(ball2.getSize(), 0);
    }

    @Test
    public void testGetUniqueSize() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getUniqueSize(), 1);
        ball.insert("unique");
        assertEquals(ball.getUniqueSize(), 2);
        //My Tests!
        Yarn ball2 = new Yarn();
        assertEquals(ball2.getUniqueSize(), 0);
    }

    @Test
    public void testInsert() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        //My Tests!
        Yarn ball2 = new Yarn();
        assertFalse(ball2.contains("pud"));
        ball2.insert("pud");
        assertTrue(ball2.contains("pud"));
    }

    @Test
    public void testRemove() { //This unit test revealed an bug in my code
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 1);
        ball.remove("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        //My Tests!
        Yarn ball2 = new Yarn();
        ball2.insert("what!?");
        ball2.insert("pud");
        ball2.insert("pud");
        assertEquals(ball2.getSize(), 3);
        assertEquals(ball2.getUniqueSize(), 2);
        ball2.remove("what!?");
        assertEquals(ball2.getSize(), 2);
        assertEquals(ball2.getUniqueSize(), 1);

    }

    @Test
    public void testRemoveAll() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
        ball.removeAll("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        //My Tests!
        Yarn ball2 = new Yarn();
        ball2.insert("pud");
        ball2.insert("pud");
        assertEquals(ball2.getSize(), 2);
        assertEquals(ball2.getUniqueSize(), 1);
        ball2.removeAll("pud");
        assertEquals(ball2.getSize(), 0);
        assertEquals(ball2.getUniqueSize(), 0);
    }

    @Test
    public void testCount() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.count("dup"), 2);
        assertEquals(ball.count("unique"), 1);
        assertEquals(ball.count("forneymon"), 0);
        //My Tests!
        Yarn ball2 = new Yarn();
        ball2.insert("pud");
        ball2.insert("pud");
        ball2.insert("pud");
        assertEquals(ball2.count("pud"), 3);
        assertEquals(ball2.count(""), 0);
        assertEquals(ball2.count("monforney"), 0);
    }

    @Test
    public void testContains() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));
        //My Tests!
        Yarn ball2 = new Yarn();
        ball2.insert("pud");
        ball2.insert("pud");
        ball2.insert("pud");
        assertTrue(ball2.contains("pud"));
        assertFalse(ball2.contains(""));
        assertFalse(ball2.contains("monforney"));
    }

    @Test
    public void testGetNth() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        Yarn comparison = ball.clone();
        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(comparison.contains(gotten));
            comparison.remove(gotten);
        }
        //My Tests!
        Yarn ball2 = new Yarn();
        ball2.insert("pud");
        ball2.insert("pud");
        ball2.insert("");
        assertEquals(ball2.getNth(0), "pud");
        assertEquals(ball2.getNth(2), "");
    }

    @Test
    public void testGetMostCommon() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals(ball.getMostCommon(), "dup");
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("dup") || mc.equals("cool"));
        //My Tests!
        Yarn ball2 = new Yarn();
        ball2.insert("");
        ball2.insert("");
        ball2.insert("");
        ball2.insert("pud");
        ball2.insert("pud");
        assertEquals(ball2.getMostCommon(), "");
    }

    @Test
    public void testClone() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        Yarn dolly = ball.clone();
        assertEquals(dolly.count("dup"), 2);
        assertEquals(dolly.count("unique"), 1);
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
        //My Tests!
        Yarn ball2 = new Yarn();
        ball2.insert("pud");
        ball2.insert("pud");
        Yarn dolly2 = ball2.clone();
        assertEquals(dolly2.count("pud"), 2);
        assertEquals(dolly2.count(""), 0);
    }

    @Test
    public void testSwap() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));
        //My Tests!
        Yarn ball1 = new Yarn();
        ball1.insert("pud");
        ball1.insert("pud");
        Yarn ball2 = new Yarn();
        ball2.insert("pud");
        ball2.insert("");
        ball2.insert("");
        assertTrue(ball1.contains("pud"));
        assertTrue(ball2.contains(""));
        assertFalse(ball1.contains(""));
    }

    @Test
    public void testKnit() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.knit(y1, y2);
        assertEquals(y3.count("dup"), 3);
        assertEquals(y3.count("unique"), 1);
        assertEquals(y3.count("cool"), 1);
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        //My Tests!
        Yarn ball1 = new Yarn();
        ball1.insert("pud");
        ball1.insert("pud");
        Yarn ball2 = new Yarn();
        ball2.insert("pud");
        ball2.insert("");
        ball2.insert("");
        Yarn ball3 = Yarn.knit(ball1, ball2);
        assertEquals(ball3.count("pud"), 3);
        assertEquals(ball3.count(""), 2);
        assertEquals(ball3.count("what!?"), 0);
        assertFalse(ball1.contains(""));
    }

    @Test
    public void testTear() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.tear(y1, y2);
        assertEquals(y3.count("dup"), 1);
        assertEquals(y3.count("unique"), 1);
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        //My Tests!
        Yarn ball1 = new Yarn();
        ball1.insert("pud");
        ball1.insert("pud");
        Yarn ball2 = new Yarn();
        ball2.insert("pud");
        ball2.insert("");
        ball2.insert("");
        Yarn ball3 = Yarn.tear(ball1, ball2);
        assertEquals(ball3.count("pud"), 1);
        assertEquals(ball3.count(""), 0);
        assertEquals(ball3.count("what!?"), 0);
        assertFalse(ball1.contains(""));
    }

    @Test
    public void testSameYarn() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(Yarn.sameYarn(y1, y2));
        assertTrue(Yarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(Yarn.sameYarn(y1, y2));
        //My Tests!
        Yarn ball1 = new Yarn();
        ball1.insert("pud");
        ball1.insert("");
        Yarn ball2 = new Yarn();
        ball2.insert("");
        ball2.insert("pud");
        Yarn ball3 = new Yarn();
        Yarn ball4 = new Yarn();
        assertTrue(Yarn.sameYarn(ball1, ball2));
        assertTrue(Yarn.sameYarn(ball2, ball1));
        assertTrue(Yarn.sameYarn(ball3, ball4));
        assertTrue(Yarn.sameYarn(ball4, ball3));
    }

}
