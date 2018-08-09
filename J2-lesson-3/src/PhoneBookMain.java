import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhoneBookMain {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Петрушин", "12345678764", "1234578");
        phoneBook.add("Васечкин", "12345", "1234578");

        phoneBook.displayAllPhoneBook();

        System.out.println(phoneBook.getPhoneByName("Васечкин"));

    }


}
