package bestgymever;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileInputHandlerTest {
    @Test
    void testGenerateMembersFromFile() {
        Path testFile = Paths.get("test/bestgymever/writeTest.txt");
        Gym testGym = new Gym(testFile, null);
        FileInputHandler testInputHandler = new FileInputHandler(testGym);

        // Expected members
        ArrayList<Member> expectedMembers = new ArrayList<>();
        expectedMembers.add(new Member("Bo Hansson", "9102032324", "2021-01-11"));
        expectedMembers.add(new Member("Erik Malmgren", "8103022342", "2013-11-12"));

        // Actual members from method
        testInputHandler.generateMembersFromFile();
        ArrayList<Member> actualMembers = testGym.getMembers();

        // Check if both lists are of equal size
        assertEquals(expectedMembers.size(), actualMembers.size());

        Iterator<Member> expectedIterator = expectedMembers.iterator();
        Iterator<Member> actualIterator = actualMembers.iterator();

        Member expectedMember;

        Member actualMember;

        // Check if each string in lists are equals
        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            expectedMember = expectedIterator.next();
            actualMember = actualIterator.next();
            // Member overrides equals(), checks if fields name, personalIdentityNumber and dateLastJoined are equal
            // instead of checking if they are the same object.
            assertEquals(expectedMember, actualMember);
        }

        // Check if generateMembersFromFile with null as input in Gym throws NullPointerException
        Gym nullInputGym = new Gym(null, null);
        FileInputHandler fh = new FileInputHandler(nullInputGym);

        assertThrows(NullPointerException.class,
                fh::generateMembersFromFile);
    }

    @Test
    void exceptionThrownTest() {
        // Assert throws NullPointerException if input path of gym is null;
        Gym nullGym = new Gym(null, null);
        FileInputHandler inputHandler = new FileInputHandler(nullGym);
        Throwable exception =
                assertThrows(NullPointerException.class,
                        inputHandler::generateMembersFromFile);

    }
}
