package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.util.StringConverter;

public class PersonStringConverter extends StringConverter<Person> {

    @Override
    public String toString(Person p) {
        return p==null ? null : p.getLastName() + ", " + p.getFirstName();
    }

    @Override
    public Person fromString(String string) {
        Person p = null;
        if(string==null){
            return p;
        }
        int commaIndex = string.indexOf(',');
        if(commaIndex==-1){
            p = new Person(string,null,null);
        }else {
            String firstName = string.substring(commaIndex+2);
            String lastName = string.substring(0,commaIndex);
            p = new Person(firstName,lastName,null);
        }
        return p;
    }
}
