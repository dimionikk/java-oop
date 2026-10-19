package ua.edu.sumdu.j2se;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = readInt(scanner, "Кількість працівників: ", 1, 100);

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println("Працівник " + (i + 1) + ":");
            String name = readText(scanner, "  Ім'я: ");
            String position = readText(scanner, "  Посада: ");
            int age = readInt(scanner, "  Вік: ", 16, 100);
            double salary = readDouble(scanner, "  Зарплата: ");

            employees.add(new Employee(name, position, age, salary));
        }

        System.out.println("\nСписок працівників:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static String readText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("  Поле не може бути порожнім.");
        }
    }

    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("  Введіть число від " + min + " до " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  Це не ціле число.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().replace(',', '.');
            try {
                double value = Double.parseDouble(line);
                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }
                System.out.println("  Значення не може бути від'ємним.");
            } catch (NumberFormatException e) {
                System.out.println("  Це не число.");
            }
        }
    }
}