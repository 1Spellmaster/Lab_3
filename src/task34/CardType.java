package task34;

public enum CardType {
    SCHOOL("Учнівська"),
    STUDENT("Студентська"),
    REGULAR("Звичайна");

    private final String name;

    CardType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
