package ua.edu.sumdu.j2se;

import java.util.Objects;

public class Employee {
    private String name;
    private String position;
    private int age;
    private double salary;
    private String email;

    public Employee(String name, String position, int age, double salary, String email) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.salary = salary;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age
                && Double.compare(salary, employee.salary) == 0
                && Objects.equals(name, employee.name)
                && Objects.equals(position, employee.position)
                && Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, age, salary, email);
    }
}