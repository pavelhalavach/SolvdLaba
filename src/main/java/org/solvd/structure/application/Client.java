package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.AppUser;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Client extends AppUser {

    public Client(String name, String surname) {
        super(name, surname);
    }

    @Override
    public String toString() {
        return "{" + getName() +
                " " + getSurname() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getName(), client.getName()) &&
                Objects.equals(getSurname(), client.getSurname()) &&
                Arrays.equals(getRatings(), client.getRatings());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getSurname());
        result = 31 * result + Arrays.hashCode(getRatings());
        return result;
    }
}
