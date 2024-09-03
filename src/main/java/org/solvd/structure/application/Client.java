package org.solvd.structure.application;

import org.solvd.structure.AppUser;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Client extends AppUser {

    public Client(String name, String surname) {
        super(name, surname);
    }

//    @Override
//    public Rating giveFeedback(AppUser user){
//        System.out.println("Hello, client " + name + " " + surname);
//        System.out.println("Please give us a feedback about your driver " +
//                user.getName() + " " + user.getSurname());
//        System.out.println("First put your mark, then add some comments");
//        Scanner input = new Scanner(System.in);
//        int mark = input.nextInt();
//        input.nextLine();
//        String comment = input.nextLine();
//
//        return new Rating(comment, mark);
//    }

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
