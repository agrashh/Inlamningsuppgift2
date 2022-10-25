package bestgymever;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileOutputHandlerTest {

    @Test
    void testCreateFile() {
        // Check if file exists
        String filePath = "test/bestgymever/testFile.txt";
        File f = new File(filePath);
        assertFalse(f.exists());

        Gym testGym = new Gym(null, Path.of(filePath));
        FileOutputHandler testOutputHandler = new FileOutputHandler(testGym);

        testOutputHandler.createFile();

        // Check if createFile() created the file
        assertTrue(f.exists());

        // delete file
        f.delete();

    }

    @Test
    void testWriteMemberToFile() throws IOException {
        Path out = Paths.get("test/bestgymever/testFile.txt");
        Gym testGym = new Gym(null, out);
        FileOutputHandler testOutputHandler = new FileOutputHandler(testGym);

        // Create the file
        testOutputHandler.createFile();
        // Put file in variable to be able to delete at end of program
        File testFile = new File("test/bestgymever/testFile.txt");

        // Write some members with writeMemberToFile
        testOutputHandler.writeMemberToFile(new Member("Eva Adolfsson", "9102034332",
                "2020-09-20"));

        testOutputHandler.writeMemberToFile(new Member("Adolf Edvardsson", "9203041234",
                "2020-10-25"));
        testOutputHandler.writeMemberToFile(new Member("Apan Bengt", "1234567891", "2021-10-25"));

        // Put contents of the file into a List of strings. The actual method output.
        List<String> actualStringList = Files.readAllLines(out);


        // Create the expected output
        List<String> expectedStringList = new ArrayList<>();
        expectedStringList.add("Eva Adolfsson entered the gym " + LocalDate.now());
        expectedStringList.add("Adolf Edvardsson entered the gym " + LocalDate.now());
        expectedStringList.add("Apan Bengt entered the gym " + LocalDate.now());

        // Check if list of strings are of equal size
        assertEquals(expectedStringList.size(), actualStringList.size());

        Iterator<String> expectedIterator = expectedStringList.iterator();
        Iterator<String> actualIterator = actualStringList.iterator();

        String expected;
        String actual;

        // Check if each string in expectedStringList and actualStringList are the same
        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            expected = expectedIterator.next();
            actual = actualIterator.next();
            assertEquals(expected, actual);

        }
        // delete file
        testFile.delete();
    }

    @Test
    void exceptionThrownTest() {
        // Assert throws NullPointerException if output path of gym is null;
        Gym nullGym = new Gym(null, null);
        FileOutputHandler outputHandler = new FileOutputHandler(nullGym);
        Throwable exception =
                assertThrows(NullPointerException.class,
                        outputHandler::createFile);

    }

}
