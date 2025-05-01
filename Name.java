public class Name {

    private String firstName;
    private String lastName;

    Name(String first, String last) {
        firstName = first;
        lastName = last;
    }

    Name() {
        firstName = "______";
        lastName = "______";
    }

    public void setFirstName(String first) {
        firstName = first;
    }

    public void setLastName(String last) {
        lastName = last;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName;
    }

    public char lastNameFirstLetter() {
        return lastName.charAt(0);
    }
}
