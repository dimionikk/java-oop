package ua.edu.sumdu.j2se;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Працівник з ім'ям, посадою, віком, зарплатою, email і відділом.
 * Усі значення перевіряються в конструкторі та сетерах,
 * тому об'єкт не може містити некоректних даних.
 * Відділ передається ззовні і може бути спільним для кількох
 * працівників (агрегація).
 * Клас рахує кількість успішно створених об'єктів.
 */
public class Employee {
    private static final int MIN_AGE = 16;
    private static final int MAX_AGE = 100;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[^@\\s]+@[^@\\s]+\\.[^@\\s]+");

    private static int count = 0;

    private String name;
    private Position position;
    private int age;
    private double salary;
    private String email;
    private Department department;

    /**
     * Створює працівника з перевіркою всіх параметрів.
     * Лічильник об'єктів збільшується лише після успішної перевірки.
     *
     * @param name       ім'я, не порожнє
     * @param position   посада, не null
     * @param age        вік від 16 до 100
     * @param salary     зарплата, невід'ємне скінченне число
     * @param email      email у форматі name@domain.com
     * @param department відділ, не null
     * @throws IllegalArgumentException якщо хоча б один параметр некоректний
     */
    public Employee(String name, Position position, int age, double salary, String email,
                    Department department) {
        this.name = requireText(name, "Ім'я");
        this.position = requirePosition(position);
        this.age = requireAge(age);
        this.salary = requireSalary(salary);
        this.email = requireEmail(email);
        this.department = requireDepartment(department);
        count++;
    }

    /**
     * Повертає кількість успішно створених об'єктів класу.
     *
     * @return кількість створених працівників
     */
    public static int getCount() {
        return count;
    }

    /**
     * Повертає ім'я працівника.
     *
     * @return ім'я
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює ім'я працівника.
     *
     * @param name нове ім'я, не порожнє
     * @throws IllegalArgumentException якщо ім'я null або порожнє
     */
    public void setName(String name) {
        this.name = requireText(name, "Ім'я");
    }

    /**
     * Повертає посаду працівника.
     *
     * @return посада
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Встановлює посаду працівника.
     *
     * @param position нова посада, не null
     * @throws IllegalArgumentException якщо посада null
     */
    public void setPosition(Position position) {
        this.position = requirePosition(position);
    }

    /**
     * Повертає вік працівника.
     *
     * @return вік
     */
    public int getAge() {
        return age;
    }

    /**
     * Встановлює вік працівника.
     *
     * @param age новий вік від 16 до 100
     * @throws IllegalArgumentException якщо вік поза межами
     */
    public void setAge(int age) {
        this.age = requireAge(age);
    }

    /**
     * Повертає зарплату працівника.
     *
     * @return зарплата
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Встановлює зарплату працівника.
     *
     * @param salary нова зарплата, невід'ємне скінченне число
     * @throws IllegalArgumentException якщо зарплата від'ємна, NaN або нескінченна
     */
    public void setSalary(double salary) {
        this.salary = requireSalary(salary);
    }

    /**
     * Повертає email працівника.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Встановлює email працівника.
     *
     * @param email новий email у форматі name@domain.com
     * @throws IllegalArgumentException якщо email порожній або має неправильний формат
     */
    public void setEmail(String email) {
        this.email = requireEmail(email);
    }

    /**
     * Повертає відділ працівника.
     *
     * @return відділ
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Встановлює відділ працівника.
     *
     * @param department новий відділ, не null
     * @throws IllegalArgumentException якщо відділ null
     */
    public void setDepartment(Department department) {
        this.department = requireDepartment(department);
    }

    /**
     * Повертає текстове представлення працівника з усіма полями.
     *
     * @return рядок з даними працівника
     */
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", position=" + position.getTitle() +
                ", age=" + age +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", department=" + department.getName() +
                '}';
    }

    /**
     * Порівнює працівників за всіма полями.
     *
     * @param o об'єкт для порівняння
     * @return true, якщо об'єкт є працівником з такими самими даними
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age
                && Double.compare(salary, employee.salary) == 0
                && Objects.equals(name, employee.name)
                && Objects.equals(position, employee.position)
                && Objects.equals(email, employee.email)
                && Objects.equals(department, employee.department);
    }

    /**
     * Обчислює хеш-код за всіма полями, узгоджено з {@link #equals(Object)}.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, position, age, salary, email, department);
    }

    /**
     * Перевіряє, що рядок не null і не порожній.
     *
     * @param value     рядок для перевірки
     * @param fieldName назва поля для повідомлення про помилку
     * @return рядок без пробілів по краях
     * @throws IllegalArgumentException якщо рядок null або порожній
     */
    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Поле \"" + fieldName + "\" не може бути порожнім.");
        }
        return value.trim();
    }

    /**
     * Перевіряє, що посада задана.
     *
     * @param position посада для перевірки
     * @return та сама посада
     * @throws IllegalArgumentException якщо посада null
     */
    private static Position requirePosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Посада має бути задана.");
        }
        return position;
    }

    /**
     * Перевіряє, що вік у допустимих межах.
     *
     * @param age вік для перевірки
     * @return той самий вік
     * @throws IllegalArgumentException якщо вік менший за 16 або більший за 100
     */
    private static int requireAge(int age) {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException("Вік має бути від " + MIN_AGE + " до " + MAX_AGE + ".");
        }
        return age;
    }

    /**
     * Перевіряє, що зарплата є невід'ємним скінченним числом.
     *
     * @param salary зарплата для перевірки
     * @return та сама зарплата
     * @throws IllegalArgumentException якщо зарплата від'ємна, NaN або нескінченна
     */
    private static double requireSalary(double salary) {
        if (!Double.isFinite(salary) || salary < 0) {
            throw new IllegalArgumentException("Зарплата має бути невід'ємним числом.");
        }
        return salary;
    }

    /**
     * Перевіряє, що email не порожній і має формат name@domain.com.
     *
     * @param email email для перевірки
     * @return email без пробілів по краях
     * @throws IllegalArgumentException якщо email порожній або має неправильний формат
     */
    private static String requireEmail(String email) {
        String value = requireText(email, "Email");
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Email має бути у форматі name@domain.com.");
        }
        return value;
    }

    /**
     * Перевіряє, що відділ заданий.
     *
     * @param department відділ для перевірки
     * @return той самий відділ
     * @throws IllegalArgumentException якщо відділ null
     */
    private static Department requireDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Відділ має бути заданий.");
        }
        return department;
    }
}