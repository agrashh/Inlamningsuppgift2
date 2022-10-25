package bestgymever;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    @Test
    void testHasActiveMembership() {
        LocalDate now = LocalDate.now();
        String dateLessThanYearAgo = String.valueOf(now.minusMonths(2));
        String dateMoreThanYearAgo = String.valueOf(now.minusYears(2));

        Member activeMember = new Member("Aktiv Kille", "9302012321", dateLessThanYearAgo);
        Member inactiveMember = new Member("Inaktiv Kille", "9403022423", dateMoreThanYearAgo);

        assertTrue(activeMember.hasActiveMembership());
        assertFalse(inactiveMember.hasActiveMembership());
    }

    @Test
    void testEquals() {
        // Test if new Member.equals() is true if the fields are equal, false if not.
        Member m1 = new Member("Rikard Brun", "8212345678", "2015-03-04");
        Member m2 = new Member("Rikard Brun", "8212345678", "2015-03-04");
        Member m3 = new Member("Rikard Gul", "9012345678", "1932-11-22");


        // Field equality
        assertEquals(m1, m2);
        assertEquals(m2, m1);
        assertNotEquals(m1, m3);
        assertNotEquals(m2, m3);

        // Object instance equality
        assertNotSame(m1, m2);

    }
}
