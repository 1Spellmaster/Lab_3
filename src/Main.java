import task31.*;
import task34.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        runTask31();
        runTask34();
    }

    private static void runTask31() {
        System.out.println("Завдання 3.1");
        Shape[] shapes = {
                new Rectangle("Червоний", 4, 5),
                new Circle("Синій", 3),
                new Triangle("Зелений", 6, 4),
                new Rectangle("Жовтий", 3, 7),
                new Circle("Червоний", 2),
                new Triangle("Синій", 5, 8),
                new Rectangle("Зелений", 2, 6),
                new Circle("Жовтий", 4),
                new Triangle("Червоний", 3, 4),
                new Rectangle("Синій", 5, 5)
        };

        ShapeModel model = new ShapeModel(shapes);
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(model, view);
        controller.run(Rectangle.class);
    }

    private static void runTask34() {
        System.out.println("\nЗавдання 3.4");
        LocalDate today = LocalDate.now();
        CardRegistry registry = new CardRegistry();
        Turnstile turnstile = new Turnstile(registry, 10);

        TravelCard monthlyCard = registry.issueCard(
                CardType.SCHOOL, TicketType.MONTH, today);
        TravelCard tenDayCard = registry.issueCard(
                CardType.STUDENT, TicketType.TEN_DAYS, today);
        TravelCard fiveTripCard = registry.issueCard(
                CardType.REGULAR, TicketType.FIVE_TRIPS, today);
        TravelCard tenTripCard = registry.issueCard(
                CardType.SCHOOL, TicketType.TEN_TRIPS, today);
        TravelCard balanceCard = registry.issueCard(
                CardType.REGULAR, TicketType.BALANCE, today);

        checkPass(turnstile, monthlyCard, today);
        checkPass(turnstile, tenDayCard, today);
        checkPass(turnstile, tenTripCard, today);
        System.out.println("Залишок поїздок: " + tenTripCard.getRemainingTrips());

        for (int i = 0; i < 6; i++) {
            checkPass(turnstile, fiveTripCard, today);
        }
        System.out.println("Залишок поїздок: " + fiveTripCard.getRemainingTrips());

        checkPass(turnstile, tenDayCard, today.plusDays(10));
        checkPass(turnstile, monthlyCard, today.plusMonths(1));
        checkPass(turnstile, null, today);

        balanceCard.topUp(15);
        checkPass(turnstile, balanceCard, today);
        checkPass(turnstile, balanceCard, today);
        balanceCard.topUp(10);
        checkPass(turnstile, balanceCard, today.plusYears(1));
        System.out.println("Залишок коштів: " + balanceCard.getBalance() + " грн");

        turnstile.printTotals();
        turnstile.printByTicketType();
    }

    private static void checkPass(Turnstile turnstile, TravelCard card, LocalDate date) {
        String cardName = card == null ? "Картку не зчитано" : "Картка №" + card.getId();
        boolean allowed = turnstile.tryPass(card, date);
        System.out.println(cardName + ": " + (allowed ? "прохід дозволено" : "прохід заборонено"));
    }
}
