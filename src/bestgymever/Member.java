package bestgymever;

import java.time.LocalDate;

public class Member {
    private final String name;
    private final String personalIdentityNumber;
    private final LocalDate dateLastPayment;

    public Member(String name, String personalIdentityNumber, String dateLastPayment) {
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
}
