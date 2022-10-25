package bestgymever;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;

public class FileOutputHandler {
    private final Gym gym;

    public FileOutputHandler(Gym gym) {
        this.gym = gym;
    }

    public void writeMemberToFile(Member member) throws NullPointerException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(gym.getOutputPath().toString(), true))) {
            writer.println(member.getName() + " entered the gym " + LocalDate.now());
        } catch (FileNotFoundException fe) {
            System.out.println("File not found.");
            fe.printStackTrace();
        }
    }

    public void createFile() {
        try {
            if (!Files.exists(gym.getOutputPath())) {
                Files.createFile(gym.getOutputPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
