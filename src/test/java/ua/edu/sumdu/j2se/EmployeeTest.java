package ua.edu.sumdu.j2se;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести класу {@link Employee}: перевірка вхідних даних,
 * агрегація з класом {@link Department}, конструктор копіювання
 * і статичний лічильник об'єктів.
 */
class EmployeeTest {

    /**
     * Створює працівника з коректними даними.
     *
     * @return коректний працівник
     */
    private Employee validEmployee() {
        return new Employee("Дмитро", Position.DEVELOPER, 19, 30000.0, "test952@gmail.com",
                new Department("Розробка"));
    }

    /**
     * Коректні дані створюють об'єкт з тими самими значеннями.
     */
    @Test
    void shouldCreateEmployeeWithValidData() {
        Employee employee = validEmployee();

        assertEquals("Дмитро", employee.getName());
        assertEquals(19, employee.getAge());
        assertEquals("test952@gmail.com", employee.getEmail());
    }

    /**
     * Некоректний вік у сетері спричиняє виняток.
     */
    @Test
    void shouldThrowExceptionWhenInvalidAgeInSetter() {
        Employee employee = validEmployee();

        assertThrows(IllegalArgumentException.class, () -> employee.setAge(-1));
    }

    /**
     * Після невдалого виклику сетера поле зберігає старе значення.
     */
    @Test
    void shouldKeepOldValueWhenSetterFails() {
        Employee employee = validEmployee();

        assertThrows(IllegalArgumentException.class, () -> employee.setSalary(-100));
        assertEquals(30000.0, employee.getSalary());
    }

    /**
     * Некоректні параметри конструктора спричиняють виняток.
     */
    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee("", Position.DEVELOPER, 25, 30000.0, "ivan@mail.com",
                        new Department("Розробка")));
    }

    /**
     * Посада null у конструкторі спричиняє виняток.
     */
    @Test
    void shouldThrowExceptionWhenPositionIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee("Іван", null, 25, 30000.0, "ivan@mail.com",
                        new Department("Розробка")));
    }

    /**
     * Email без символу @ спричиняє виняток з повідомленням про email.
     */
    @Test
    void shouldThrowExceptionWhenInvalidEmail() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Employee("Іван", Position.DEVELOPER, 25, 30000.0, "ivan.mail.com",
                        new Department("Розробка")));

        assertTrue(e.getMessage().contains("Email"));
    }

    /**
     * Відділ null у конструкторі спричиняє виняток.
     */
    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee("Іван", Position.DEVELOPER, 25, 30000.0, "ivan@mail.com", null));
    }

    /**
     * Два працівники посилаються на той самий відділ,
     * тому зміна відділу видна через обох.
     */
    @Test
    void shouldShareDepartmentBetweenEmployees() {
        Department department = new Department("Розробка");
        Employee first = new Employee("Іван", Position.DEVELOPER, 25, 30000.0, "ivan@mail.com", department);
        Employee second = new Employee("Олена", Position.TESTER, 30, 28000.0, "olena@mail.com", department);

        department.setName("Тестування");

        assertSame(first.getDepartment(), second.getDepartment());
        assertEquals("Тестування", first.getDepartment().getName());
        assertEquals("Тестування", second.getDepartment().getName());
    }

    /**
     * Копія має ті самі дані, але є окремим об'єктом.
     */
    @Test
    void shouldCreateEqualButSeparateCopy() {
        Employee original = validEmployee();
        Employee copy = new Employee(original);

        assertEquals(original, copy);
        assertNotSame(original, copy);
    }

    /**
     * Зміна копії не впливає на оригінал.
     */
    @Test
    void shouldNotChangeOriginalWhenCopyChanges() {
        Employee original = validEmployee();
        Employee copy = new Employee(original);

        copy.setName("Олег");

        assertEquals("Дмитро", original.getName());
    }

    /**
     * Копія посилається на той самий відділ, що й оригінал.
     */
    @Test
    void shouldShareDepartmentWithCopy() {
        Employee original = validEmployee();
        Employee copy = new Employee(original);

        assertSame(original.getDepartment(), copy.getDepartment());
    }

    /**
     * Копіювання null спричиняє виняток.
     */
    @Test
    void shouldThrowExceptionWhenCopyingNull() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(null));
    }

    /**
     * Успішне створення працівника збільшує лічильник на 1.
     */
    @Test
    void shouldIncreaseCountWhenEmployeeCreated() {
        int before = Employee.getCount();

        validEmployee();

        assertEquals(before + 1, Employee.getCount());
    }

    /**
     * Невдале створення працівника не змінює лічильник.
     */
    @Test
    void shouldNotIncreaseCountWhenCreationFails() {
        int before = Employee.getCount();

        assertThrows(IllegalArgumentException.class,
                () -> new Employee("Іван", Position.DEVELOPER, 10, 30000.0, "ivan@mail.com",
                        new Department("Розробка")));

        assertEquals(before, Employee.getCount());
    }

    /**
     * Копіювання створює новий об'єкт, тому лічильник збільшується на 1.
     */
    @Test
    void shouldIncreaseCountWhenEmployeeCopied() {
        Employee original = validEmployee();
        int before = Employee.getCount();

        new Employee(original);

        assertEquals(before + 1, Employee.getCount());
    }
}