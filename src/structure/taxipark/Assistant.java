package structure.taxipark;
import structure.Person;

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
                name +
                " " + surname +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assistant assistant = (Assistant) o;
        return Float.compare(salary, assistant.salary) == 0 && Objects.equals(name, assistant.name) && Objects.equals(surname, assistant.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
