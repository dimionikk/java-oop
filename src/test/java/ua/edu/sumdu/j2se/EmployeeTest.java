package ua.edu.sumdu.j2se;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести перевірки вхідних даних класу {@link Employee}
 * та агрегації з класом {@link Department}.
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
}