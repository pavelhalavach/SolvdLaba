package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.application.Client;
import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.application.Request;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Driver extends AppUser {
    private static float percentage = 0.7f;
    private Car car;

    public Driver(String name,
                  String surname,
                  Car car) {
        super(name, surname);
        this.car = car;
    }

//    @Override
//    public Rating giveFeedback(AppUser user){
//        System.out.println("Hello, driver " + name + " " + surname);
//        System.out.println("Please give us a feedback about your client " +
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
                ", car: " + car +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(getName(), driver.getName()) &&
                Objects.equals(getSurname(), driver.getSurname()) &&
                Objects.equals(car, driver.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), car);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static float getPercentage() {
        return percentage;
    }

    public static void setPercentage(float percentage) {
        Driver.percentage = percentage;
    }
}
