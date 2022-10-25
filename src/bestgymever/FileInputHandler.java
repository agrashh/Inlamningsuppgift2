package bestgymever;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class FileInputHandler {
    private final Gym gym;

    public FileInputHandler(Gym gym) {
        this.gym = gym;
    }

    public void generateMembersFromFile() throws NullPointerException {
        String personalIdentityNumber;
        String name;
        String dateLastPayment;

        try (Scanner scanner = new Scanner(gym.getInputPath())) {
            while (scanner.hasNext()) {
                personalIdentityNumber = scanner.next().replace(",", "");
                name = (scanner.next() + " " + scanner.next().trim());
                dateLastPayment = scanner.next();

                gym.getMembers().add(new Member(name, personalIdentityNumber, dateLastPayment));
            }
        } catch (NoSuchFileException fe) {
            System.out.println("Couldn't find file from " + fe.getMessage());
            fe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Something went wrong. " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }
}
