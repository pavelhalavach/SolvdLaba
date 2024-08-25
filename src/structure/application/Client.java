package structure.application;

import structure.AppUser;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Client extends AppUser {

    public Client(String name, String surname) {
        super(name, surname);
    }

    @Override
    public Rating giveFeedback(AppUser user){
        System.out.println("Hello, client " + name + " " + surname);
        System.out.println("Please give us a feedback about your driver " +
                user.getName() + " " + user.getSurname());
        System.out.println("First put your mark, then add some comments");
        Scanner input = new Scanner(System.in);
        int mark = input.nextInt();
        input.nextLine();
        String comment = input.nextLine();

        return new Rating(comment, mark);
    }

    @Override
    public String toString() {
        return "{" + name +
                " " + surname +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Arrays.equals(ratings, client.ratings);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname);
        result = 31 * result + Arrays.hashCode(ratings);
        return result;
    }
}
