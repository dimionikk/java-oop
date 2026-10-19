package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Кількість працівників: ");
        int count = Integer.parseInt(scanner.nextLine());

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println("Працівник " + (i + 1) + ":");
            System.out.print("  Ім'я: ");
            String name = scanner.nextLine();
            System.out.print("  Посада: ");
            String position = scanner.nextLine();
            System.out.print("  Вік: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("  Зарплата: ");
            double salary = Double.parseDouble(scanner.nextLine());

            employees.add(new Employee(name, position, age, salary));
        }

        System.out.println("\nСписок працівників:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}