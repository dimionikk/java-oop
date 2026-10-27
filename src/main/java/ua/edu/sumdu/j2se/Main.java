package ua.edu.sumdu.j2se;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Консольний драйвер програми: меню для створення відділів і працівників,
 * копіювання працівників, виведення списку працівників
 * і кількості створених об'єктів.
 */
public class Main {

    /**
     * Точка входу. Виводить інформаційну шапку
     * і показує меню, доки користувач не обере вихід.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        printHeader();

        Scanner scanner = new Scanner(System.in);
        List<Department> departments = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        while (true) {
            System.out.println();
            System.out.println("1 - Створити відділ");
            System.out.println("2 - Створити працівника");
            System.out.println("3 - Копіювати працівника");
            System.out.println("4 - Вивести всіх працівників");
            System.out.println("5 - Кількість створених працівників");
            System.out.println("0 - Вихід");
            int choice = readInt(scanner, "Ваш вибір: ");

            switch (choice) {
                case 1 -> createDepartment(scanner, departments);
                case 2 -> createEmployee(scanner, departments, employees);
                case 3 -> copyEmployee(scanner, employees);
                case 4 -> printEmployees(employees);
                case 5 -> System.out.println("Створено працівників: " + Employee.getCount());
                case 0 -> {
                    System.out.println("Роботу завершено.");
                    return;
                }
                default -> System.out.println("Немає такого пункту меню.");
            }
        }
    }

    /**
     * Виводить інформаційну шапку програми: номер і тему роботи,
     * варіант і виконавця.
     */
    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("Практична робота №6");
        System.out.println("Тема: Класи, статичні члени, агрегація, перерахування (enum)");
        System.out.println("Варіант: 4 (облік працівників)");
        System.out.println("Виконав: студент гр. ІН-41/2 Образцов Д.Є.");
        System.out.println("==================================================");
    }

    /**
     * Зчитує назву відділу з клавіатури і додає відділ до списку.
     * Якщо назва не проходить перевірку в {@link Department},
     * виводить повідомлення про помилку і нічого не додає.
     *
     * @param scanner     джерело введення
     * @param departments список, до якого додається відділ
     */
    private static void createDepartment(Scanner scanner, List<Department> departments) {
        String name = readText(scanner, "  Назва відділу: ");

        try {
            departments.add(new Department(name));
            System.out.println("Відділ додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Зчитує дані працівника з клавіатури і додає його до списку.
     * Працівнику призначається один з уже створених відділів.
     * Якщо відділів немає або дані не проходять перевірку в {@link Employee},
     * виводить повідомлення і нічого не додає.
     *
     * @param scanner     джерело введення
     * @param departments список доступних відділів
     * @param employees   список, до якого додається працівник
     */
    private static void createEmployee(Scanner scanner, List<Department> departments,
                                       List<Employee> employees) {
        if (departments.isEmpty()) {
            System.out.println("Спочатку створіть хоча б один відділ.");
            return;
        }

        String name = readText(scanner, "  Ім'я: ");
        Position position = readPosition(scanner);
        int age = readInt(scanner, "  Вік: ");
        double salary = readDouble(scanner, "  Зарплата: ");
        String email = readText(scanner, "  Email: ");
        Department department = readDepartment(scanner, departments);

        try {
            employees.add(new Employee(name, position, age, salary, email, department));
            System.out.println("Працівника додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Показує список працівників, зчитує номер обраного
     * і додає до списку його копію, створену конструктором копіювання.
     * Номер запитується повторно, доки він не потрапить у межі списку.
     *
     * @param scanner   джерело введення
     * @param employees список працівників, до якого додається копія
     */
    private static void copyEmployee(Scanner scanner, List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("Немає працівників для копіювання.");
            return;
        }
        printEmployees(employees);
        while (true) {
            int choice = readInt(scanner, "  Номер працівника: ");
            if (choice >= 1 && choice <= employees.size()) {
                Employee copy = new Employee(employees.get(choice - 1));
                employees.add(copy);
                System.out.println("Копію додано.");
                return;
            }
            System.out.println("  Немає такого працівника.");
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
     * Виводить список відділів і зчитує номер обраного,
     * повторюючи запит, доки номер не потрапить у межі списку.
     *
     * @param scanner     джерело введення
     * @param departments непорожній список відділів
     * @return обраний відділ
     */
    private static Department readDepartment(Scanner scanner, List<Department> departments) {
        System.out.println("  Відділ:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println("    " + (i + 1) + " - " + departments.get(i).getName());
        }
        while (true) {
            int choice = readInt(scanner, "  Номер відділу: ");
            if (choice >= 1 && choice <= departments.size()) {
                return departments.get(choice - 1);
            }
            System.out.println("  Немає такого відділу.");
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