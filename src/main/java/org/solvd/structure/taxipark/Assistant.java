package main.java.org.solvd.structure.taxipark;
import main.java.org.solvd.structure.Person;

import java.util.Objects;

public class Assistant extends Person{
    private float salary;

    public Assistant(String name, String surname, float salary) {
        super(name, surname);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{" +
                getName() +
                " " + getSurname() +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assistant assistant = (Assistant) o;
        return Float.compare(salary, assistant.salary) == 0 &&
                Objects.equals(getName(), assistant.getName()) &&
                Objects.equals(getSurname(), assistant.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), salary);
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
