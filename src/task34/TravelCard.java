package task34;

import java.time.LocalDate;

public class TravelCard {
    private final int id;
    private final CardType cardType;
    private final TicketType ticketType;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private int remainingTrips;
    private int balance;

    TravelCard(int id, CardType cardType, TicketType ticketType, LocalDate startDate) {
        if (ticketType == TicketType.BALANCE && cardType != CardType.REGULAR) {
            throw new IllegalArgumentException("Накопичувальна картка може бути тільки звичайною");
        }
        this.id = id;
        this.cardType = cardType;
        this.ticketType = ticketType;

        switch (ticketType) {
            case MONTH:
                validFrom = startDate;
                validUntil = startDate.plusMonths(1);
                break;
            case TEN_DAYS:
                validFrom = startDate;
                validUntil = startDate.plusDays(10);
                break;
            case FIVE_TRIPS:
                remainingTrips = 5;
                break;
            case TEN_TRIPS:
                remainingTrips = 10;
                break;
            case BALANCE:
                balance = 0;
                break;
        }
    }

    public int getId() {
        return id;
    }

    public int getRemainingTrips() {
        return remainingTrips;
    }

    public int getBalance() {
        return balance;
    }

    public String getTypeName() {
        return cardType + ", " + ticketType;
    }

    public void topUp(int amount) {
        if (ticketType != TicketType.BALANCE || amount <= 0) {
            throw new IllegalArgumentException("Поповнювати можна лише накопичувальну картку на додатну суму");
        }
        balance += amount;
    }

    boolean tryUse(LocalDate date, int fare) {
        switch (ticketType) {
            case MONTH:
            case TEN_DAYS:
                return !date.isBefore(validFrom) && date.isBefore(validUntil);
            case FIVE_TRIPS:
            case TEN_TRIPS:
                if (remainingTrips == 0) {
                    return false;
                }
                remainingTrips--;
                return true;
            case BALANCE:
                if (balance < fare) {
                    return false;
                }
                balance -= fare;
                return true;
            default:
                return false;
        }
    }
}
