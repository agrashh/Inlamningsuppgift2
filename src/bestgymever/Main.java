package bestgymever;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    Main() {
        Path out = Paths.get("src/bestgymever/attendances.txt");
        Path in = Paths.get("src/bestgymever/customers.txt");
        Gym BestGymEver = new Gym(in, out);
        FileInputHandler inputHandler = new FileInputHandler(BestGymEver);
        FileOutputHandler outputHandler = new FileOutputHandler(BestGymEver);

        inputHandler.generateMembersFromFile();
        promptUser(BestGymEver, outputHandler);

    }

    public static void main(String[] args) {
        new Main();
    }

    private void promptUser(Gym gym, FileOutputHandler out) {
        String userInput = JOptionPane.showInputDialog("Enter a name or a personal identification number 'YYMMDDXXXX'.");
        if (userInput == null) {
            System.exit(0);
        }
        if (userInput.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Enter a non empty name or personal identification number");
            promptUser(gym, out);
        }
        if (gym.hasMembership(userInput)) {
            Member member = gym.getMember(userInput);
            assert member != null;
            if (member.hasActiveMembership()) {
                JOptionPane.showMessageDialog(null, member.getName() + " has an active membership. " +
                        "Date of last payment: " + member.getDateLastPayment());

                registerAttendance(member, out);

            } else {
                JOptionPane.showMessageDialog(null, member.getName() + " is a former member. " +
                        "Date of last payment: " + member.getDateLastPayment());
            }
        }
        promptUser(gym, out);
    }

    private void registerAttendance(Member member, FileOutputHandler out) {
        out.writeMemberToFile(member);
    }

}
