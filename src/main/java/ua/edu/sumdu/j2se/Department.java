package ua.edu.sumdu.j2se;

import java.util.Objects;

/**
 * Відділ компанії. Існує незалежно від працівників:
 * створюється окремо і може бути спільним для кількох працівників.
 */
public class Department {
    private String name;

    /**
     * Створює відділ з перевіркою назви.
     *
     * @param name назва відділу, не порожня
     * @throws IllegalArgumentException якщо назва null або порожня
     */
    public Department(String name) {
        this.name = requireName(name);
    }

    /**
     * Повертає назву відділу.
     *
     * @return назва відділу
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву відділу.
     *
     * @param name нова назва, не порожня
     * @throws IllegalArgumentException якщо назва null або порожня
     */
    public void setName(String name) {
        this.name = requireName(name);
    }

    /**
     * Повертає текстове представлення відділу.
     *
     * @return рядок з назвою відділу
     */
    @Override
    public String toString() {
        return "Department{name='" + name + "'}";
    }

    /**
     * Порівнює відділи за назвою.
     *
     * @param o об'єкт для порівняння
     * @return true, якщо об'єкт є відділом з такою самою назвою
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Обчислює хеш-код за назвою, узгоджено з {@link #equals(Object)}.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Перевіряє, що назва не null і не порожня.
     *
     * @param name назва для перевірки
     * @return назва без пробілів по краях
     * @throws IllegalArgumentException якщо назва null або порожня
     */
    private static String requireName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Назва відділу не може бути порожньою.");
        }
        return name.trim();
    }
}