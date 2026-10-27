package ua.edu.sumdu.j2se;

/**
 * Посада працівника з фіксованого набору значень.
 */
public enum Position {
    /** Розробник. */
    DEVELOPER("Розробник"),
    /** Тестувальник. */
    TESTER("Тестувальник"),
    /** Менеджер. */
    MANAGER("Менеджер"),
    /** Дизайнер. */
    DESIGNER("Дизайнер"),
    /** Аналітик. */
    ANALYST("Аналітик");

    private final String title;

    /**
     * Створює посаду з назвою для виведення.
     *
     * @param title назва посади українською
     */
    Position(String title) {
        this.title = title;
    }

    /**
     * Повертає назву посади українською.
     *
     * @return назва посади
     */
    public String getTitle() {
        return title;
    }
}