package task34;

public enum TicketType {
    MONTH("на місяць"),
    TEN_DAYS("на 10 днів"),
    FIVE_TRIPS("на 5 поїздок"),
    TEN_TRIPS("на 10 поїздок"),
    BALANCE("накопичувальна");

    private final String name;

    TicketType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
