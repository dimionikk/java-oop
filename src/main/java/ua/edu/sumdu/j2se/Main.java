package ua.edu.sumdu.j2se;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Консольний драйвер програми: меню для створення працівників
 * і виведення їх списку.
 */
public class Main {

    /**
     * Точка входу. Показує меню, доки користувач не обере вихід.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        while (true) {
            System.out.println();
            System.out.println("1 - Створити працівника");
            System.out.println("2 - Вивести всіх працівників");
            System.out.println("0 - Вихід");
            int choice = readInt(scanner, "Ваш вибір: ");

            switch (choice) {
                case 1 -> createEmployee(scanner, employees);
                case 2 -> printEmployees(employees);
                case 0 -> {
                    System.out.println("Роботу завершено.");
                    return;
                }
                default -> System.out.println("Немає такого пункту меню.");
            }
        }
    }

    /**
     * Зчитує дані працівника з клавіатури і додає його до списку.
     * Якщо дані не проходять перевірку в {@link Employee},
     * виводить повідомлення про помилку і нічого не додає.
     *
     * @param scanner   джерело введення
     * @param employees список, до якого додається працівник
     */
    private static void createEmployee(Scanner scanner, List<Employee> employees) {
        String name = readText(scanner, "  Ім'я: ");
        Position position = readPosition(scanner);
        int age = readInt(scanner, "  Вік: ");
        double salary = readDouble(scanner, "  Зарплата: ");
        String email = readText(scanner, "  Email: ");

        try {
            employees.add(new Employee(name, position, age, salary, email));
            System.out.println("Працівника додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Виводить усіх працівників зі списку з порядковими номерами.
     *
     * @param employees список працівників
     */
    private static void printEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i));
        }
    }

    /**
     * Зчитує непорожній рядок, повторюючи запит, доки рядок порожній.
     *
     * @param scanner джерело введення
     * @param prompt  текст запиту
     * @return введений рядок без пробілів по краях
     */
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

    /**
     * Виводить список посад і зчитує номер обраної,
     * повторюючи запит, доки номер не потрапить у межі списку.
     *
     * @param scanner джерело введення
     * @return обрана посада
     */
    private static Position readPosition(Scanner scanner) {
        Position[] positions = Position.values();
        System.out.println("  Посада:");
        for (int i = 0; i < positions.length; i++) {
            System.out.println("    " + (i + 1) + " - " + positions[i].getTitle());
        }
        while (true) {
            int choice = readInt(scanner, "  Номер посади: ");
            if (choice >= 1 && choice <= positions.length) {
                return positions[choice - 1];
            }
            System.out.println("  Немає такої посади.");
        }
    }

    /**
     * Зчитує ціле число, повторюючи запит при нечисловому або порожньому введенні.
     *
     * @param scanner джерело введення
     * @param prompt  текст запиту
     * @return введене ціле число
     */
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  Це не ціле число.");
            }
        }
    }

    /**
     * Зчитує дробове число (через кому або крапку),
     * повторюючи запит при нечисловому або порожньому введенні.
     *
     * @param scanner джерело введення
     * @param prompt  текст запиту
     * @return введене число
     */
    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  Це не число.");
            }
        }
    }
}