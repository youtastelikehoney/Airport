import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketTest {

    Ticket expensive = new Ticket("Калининград", "Казань", 10960, 15, 19);
    Ticket cheap = new Ticket("Калининград", "Казань", 8526, 15, 21);
    Ticket middle = new Ticket("Калининград", "Казань", 9874, 14, 18);
    Ticket otherDirection = new Ticket("Калининград", "Сочи", 13765, 15, 17);


    @Test
    void shouldCompareTicketsByPriceWithAnotherTicket() {
        Ticket newTicket = new Ticket("Калининград", "Казань", 9874, 1500, 1920);
        Assertions.assertEquals(0, middle.compareTo(newTicket));
        Assertions.assertEquals(-1, cheap.compareTo(newTicket));
        Assertions.assertEquals(1, expensive.compareTo(newTicket));
    }

    @Test
    void shouldCompareTicketsWithEachOtherByPrice() {
        Assertions.assertTrue(expensive.compareTo(cheap) > 0);
        Assertions.assertTrue(cheap.compareTo(expensive) < 0);
    }


    @Test
    void shouldSortTicketsByPrice() {
        AviaSouls souls = new AviaSouls();

        souls.add(expensive);
        souls.add(cheap);
        souls.add(middle);
        souls.add(otherDirection);

        Ticket[] expected = {cheap, middle, expensive};
        Assertions.assertArrayEquals(expected, souls.search("Калининград", "Казань"));
    }

    @Test
    void ticketTimeComparatorShouldCompareByFlightDuration() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        AviaSouls souls = new AviaSouls();

        souls.add(expensive);
        souls.add(cheap);
        souls.add(middle);

        Assertions.assertTrue(comparator.compare(expensive, cheap) < 0);
        Assertions.assertTrue(comparator.compare(cheap, middle) > 0);
        Assertions.assertEquals(0, comparator.compare(expensive, middle));
    }

    @Test
    void searchAndSortByShouldSortTicketsByFlightDuration() {
        AviaSouls souls = new AviaSouls();

        souls.add(expensive);
        souls.add(cheap);
        souls.add(middle);
        souls.add(otherDirection);

        Ticket[] expected = {expensive, middle, cheap};
        Assertions.assertArrayEquals(expected, souls.searchAndSortBy("Калининград", "Казань", new TicketTimeComparator()));


    }
}
