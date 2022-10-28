package bestgymever;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GymTest {
    @Test
    void testHasMembership() {
        Gym testGym = new Gym(null, null);
        testGym.add(new Member("Åke Åkesson", "1932123212", "2018-12-03"));

        assertTrue(testGym.hasMembership("Åke Åkesson"));
        assertTrue(testGym.hasMembership("åKe ÅkEsSon"));
        assertTrue(testGym.hasMembership("1932123212"));
        assertTrue(testGym.hasMembership("1932123212"));

        assertFalse(testGym.hasMembership("åke"));
        assertFalse(testGym.hasMembership("1932123211"));

    }

    @Test
    void testGetMember() {
        Gym testGym = new Gym(null, null);
        Member m1 = new Member("Åke Åkesson", "1932123212", "2018-12-03");
        testGym.add(m1);

        Member m2 = testGym.getMember("åke åkesson");
        Member m3 = testGym.getMember("finns ej");

        //Assert that m1 is not m3
        assertNotSame(m2, m3);

        //Assert m3 doesnt exist
        assertNull(m3);

        //Assert that m1 and m2 are the same object
        assertSame(m1, m2);
    }
}
