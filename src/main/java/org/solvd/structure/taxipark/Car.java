package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.enums.CarModel;

import java.time.LocalDate;
import java.util.Objects;

public class Car {
    private static int counter;
    private CarModel carModel;
    private String plateNumber;
    private LocalDate registrationDateOfPlate;


    public Car(CarModel carModel, String plateNumber, LocalDate registrationDateOfPlate) {
        counter++;
        this.carModel = carModel;
        this.plateNumber = plateNumber;
        this.registrationDateOfPlate = registrationDateOfPlate;
    }

    @Override
    public String toString() {
        return "{brandName: " + carModel +
                ", plateNumber: " + plateNumber +
                ", registrationDateOfPlate: " + registrationDateOfPlate + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carModel, car.carModel) && Objects.equals(plateNumber, car.plateNumber) && Objects.equals(registrationDateOfPlate, car.registrationDateOfPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carModel, plateNumber, registrationDateOfPlate);
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getRegistrationDateOfPlate() {
        return registrationDateOfPlate;
    }

    public void setRegistrationDateOfPlate(LocalDate registrationDateOfPlate) {
        this.registrationDateOfPlate = registrationDateOfPlate;
    }
}
