package bestgymever;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Member {
    private final String name;
    private final String personalIdentityNumber;
    private final LocalDate dateLastPayment;

    public Member(String name, String personalIdentityNumber, String dateLastPayment) throws DateTimeParseException {
        this.name = name;
        this.personalIdentityNumber = personalIdentityNumber;
        this.dateLastPayment = LocalDate.parse(dateLastPayment);
    }

    public String getName() {
        return name;
    }

    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }

    public LocalDate getDateLastPayment() {
        return dateLastPayment;
    }

    public boolean hasActiveMembership() {
        return dateLastPayment.isAfter(LocalDate.now().minusYears(1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return name.equals(member.name) && personalIdentityNumber.equals(member.personalIdentityNumber) && dateLastPayment.equals(member.dateLastPayment);
    }
}
