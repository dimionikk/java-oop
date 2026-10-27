package ua.edu.sumdu.j2se;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести перевірки вхідних даних класу {@link Employee}.
 */
class EmployeeTest {

    /**
     * Створює працівника з коректними даними.
     *
     * @return коректний працівник
     */
    private Employee validEmployee() {
        return new Employee("Дмитро", Position.DEVELOPER, 19, 30000.0, "test952@gmail.com");
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
                () -> new Employee("", Position.DEVELOPER, 25, 30000.0, "ivan@mail.com"));
    }

    /**
     * Посада null у конструкторі спричиняє виняток.
     */
    @Test
    void shouldThrowExceptionWhenPositionIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee("Іван", null, 25, 30000.0, "ivan@mail.com"));
    }

    /**
     * Email без символу @ спричиняє виняток з повідомленням про email.
     */
    @Test
    void shouldThrowExceptionWhenInvalidEmail() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Employee("Іван", Position.DEVELOPER, 25, 30000.0, "ivan.mail.com"));

        assertTrue(e.getMessage().contains("Email"));
    }
}