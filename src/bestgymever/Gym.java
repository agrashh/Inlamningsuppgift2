package bestgymever;

import java.nio.file.Path;
import java.util.ArrayList;

public class Gym {
    private final ArrayList<Member> members;
    private final Path in;
    private final Path out;

    Gym(Path in, Path out) {
        members = new ArrayList<>();
        this.in = in;
        this.out = out;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public Path getOutputPath() {
        return out;
    }

    public Path getInputPath() {
        return in;
    }

    public Member getMember(String input) {
        for (Member member : members) {
            if (input.equalsIgnoreCase(member.getName()) || input.equals(member.getPersonalIdentityNumber())) {
                return member;
            }
        }
        return null;
    }

    public boolean hasMembership(String input) {
        for (Member member : members) {
            if (input.equalsIgnoreCase(member.getName()) || input.equals(member.getPersonalIdentityNumber())) {
                return true;
            }
        }
        return false;
    }

    public void add(Member member) {
        members.add(member);
    }
}
