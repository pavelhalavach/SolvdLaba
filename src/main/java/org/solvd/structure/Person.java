package main.java.org.solvd.structure;

import java.util.Optional;

public abstract class Person {
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getSurname() {
        return Optional.ofNullable(surname);
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
