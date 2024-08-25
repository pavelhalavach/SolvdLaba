package structure.taxipark;

import java.time.LocalDate;
import java.util.Objects;

public class Car {
    private static int id = 0;
    private String brandName;
    private String plateNumber;
    private LocalDate registrationDateOfPlate;

    public Car(String brandName, String plateNumber, LocalDate registrationDateOfPlate) {
        id++;
        this.brandName = brandName;
        this.plateNumber = plateNumber;
        this.registrationDateOfPlate = registrationDateOfPlate;
    }

    @Override
    public String toString() {
        return "{brandName: " + brandName +
                ", plateNumber: " + plateNumber +
                ", registrationDateOfPlate: " + registrationDateOfPlate + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(brandName, car.brandName) && Objects.equals(plateNumber, car.plateNumber) && Objects.equals(registrationDateOfPlate, car.registrationDateOfPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandName, plateNumber, registrationDateOfPlate);
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
