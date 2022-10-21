package bestgymever;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private final ArrayList<Member> members;
    private final Path memberFile = Paths.get("src/bestgymever/customers.txt");
    private final Path attendanceFile = Paths.get("src/bestgymever/attendances.txt");

    Main() {
        members = new ArrayList<>();
    }

    private void run() {
        generateMembersFromFile(memberFile);
        promptUser();
    }

    private void promptUser() {
        String userInput = JOptionPane.showInputDialog("Enter a name or a personal identification number 'YYMMDDXXXX'.");
        if (userInput == null) {
            System.exit(0);
        }
        if (userInput.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Enter a non empty name or personal identification number");
            promptUser();
        }
        if (hasMembership(userInput)) {
            Member member = getMember(userInput);
            assert member != null;
            if (member.hasActiveMembership()) {
                JOptionPane.showMessageDialog(null, member.getName() + " has an active membership. " +
                        "Date of last payment: " + member.getDateLastPayment());

                registerAttendance(attendanceFile, member);

            } else {
                JOptionPane.showMessageDialog(null, member.getName() + " is a former member. " +
                        "Date of last payment: " + member.getDateLastPayment());
            }
        }
        promptUser();
    }

    private void registerAttendance(Path p, Member member) {
        try {
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(p.toString(), true))) {
            writer.println(member.getName() + " entered the gym " + LocalDate.now());
        } catch (FileNotFoundException fe) {
            System.out.println("File not found.");
            fe.printStackTrace();
        }
    }

    private Member getMember(String input) {
        for (Member member : members) {
            if (input.equalsIgnoreCase(member.getName()) || input.equals(member.getPersonalIdentityNumber())) {
                return member;
            }
        }
        return null;
    }

    private boolean hasMembership(String input) {
        for (Member member : members) {
            if (input.equalsIgnoreCase(member.getName()) || input.equals(member.getPersonalIdentityNumber())) {
                return true;
            }
        }
        return false;
    }

    private void generateMembersFromFile(Path path) {
        String personalIdentityNumber;
        String name;
        String dateLastPayment;

        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                personalIdentityNumber = scanner.next().replace(",", "");
                name = (scanner.next() + " " + scanner.next().trim());
                dateLastPayment = scanner.next();

                members.add(new Member(name, personalIdentityNumber, dateLastPayment));
            }
        } catch (NoSuchFileException fe) {
            System.out.println("Couldn't find file from " + fe.getMessage());
            fe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Something went wrong. " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main BestGymEver = new Main();
        BestGymEver.run();
    }

}
