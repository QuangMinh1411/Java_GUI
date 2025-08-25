package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.util.StringConverter;

public class PersonStringConverter extends StringConverter<Person> {

    @Override
    public String toString(Person p) {
        if (p == null) {
            return "";
        }
        String first = p.getFirstName();
        String last = p.getLastName();
        boolean hasFirst = first != null && !first.isBlank();
        boolean hasLast = last != null && !last.isBlank();
        if (hasFirst && hasLast) {
            return last + ", " + first;
        }
        if (hasLast) {
            return last;
        }
        return hasFirst ? first : "";
    }

    @Override
    public Person fromString(String string) {
        if (string == null) {
            return null;
        }
        String s = string.trim();
        if (s.isEmpty()) {
            return null;
        }
        int commaIndex = s.indexOf(',');
        if (commaIndex == -1) {
            // No comma: treat as first name only (keeps prior behavior)
            String firstOnly = s;
            return new Person(firstOnly, null, null);
        } else {
            String lastName = s.substring(0, commaIndex).trim();
            String firstPart = s.substring(commaIndex + 1).trim(); // handles optional space
            String firstName = firstPart.isEmpty() ? null : firstPart;
            String last = lastName.isEmpty() ? null : lastName;
            return new Person(firstName, last, null);
        }
    }
}
