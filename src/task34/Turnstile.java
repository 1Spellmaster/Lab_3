package task34;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class Turnstile {
    private final CardRegistry registry;
    private final int fare;
    private final Statistics totals = new Statistics();
    private final Map<String, Statistics> statisticsByType = new LinkedHashMap<>();

    public Turnstile(CardRegistry registry, int fare) {
        if (fare <= 0) {
            throw new IllegalArgumentException("Вартість проїзду має бути додатною");
        }
        this.registry = registry;
        this.fare = fare;
    }

    public boolean tryPass(TravelCard card, LocalDate date) {
        boolean allowed = registry.contains(card) && card.tryUse(date, fare);
        String type = card == null ? "Картку не зчитано" : card.getTypeName();
        Statistics statistics = statisticsByType.get(type);
        if (statistics == null) {
            statistics = new Statistics();
            statisticsByType.put(type, statistics);
        }
        totals.record(allowed);
        statistics.record(allowed);
        return allowed;
    }

    public void printTotals() {
        System.out.println("\nЗагальна статистика: " + totals);
    }

    public void printByTicketType() {
        System.out.println("Статистика за типами квитків:");
        for (Map.Entry<String, Statistics> entry : statisticsByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static class Statistics {
        private int allowed;
        private int denied;

        void record(boolean passed) {
            if (passed) {
                allowed++;
            } else {
                denied++;
            }
        }

        @Override
        public String toString() {
            return "дозволено=" + allowed + ", відмовлено=" + denied;
        }
    }
}
