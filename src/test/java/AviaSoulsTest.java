import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {
    @Test
    public void testTicketCompareTo() {
        Ticket ticket1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket ticket2 = new Ticket("Moscow", "Paris", 150, 12, 14);
        Ticket ticket3 = new Ticket("Moscow", "Berlin", 200, 14, 16);

        assertTrue(ticket2.compareTo(ticket1) < 0);
        assertTrue(ticket1.compareTo(ticket2) > 0);
        assertEquals(0, ticket1.compareTo(ticket3));
    }

    @Test
    public void testSearchAndSortByPrice() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "London", 200, 10, 12));
        manager.add(new Ticket("Moscow", "London", 100, 11, 13));
        manager.add(new Ticket("Moscow", "Berlin", 150, 12, 14));
        manager.add(new Ticket("Moscow", "London", 150, 14, 16));

        Ticket[] result = manager.search("Moscow", "London");
        assertEquals(3, result.length);
        assertEquals(100, result[0].getPrice());
        assertEquals(150, result[1].getPrice());
        assertEquals(200, result[2].getPrice());
    }

    @Test
    public void testTicketTimeComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket ticket1 = new Ticket("Moscow", "London", 200, 10, 12); // 2 часа
        Ticket ticket2 = new Ticket("Moscow", "Paris", 150, 12, 15); // 3 часа
        Ticket ticket3 = new Ticket("Moscow", "Berlin", 200, 14, 17); // 3 часа

        assertTrue(comparator.compare(ticket1, ticket2) < 0);
        assertTrue(comparator.compare(ticket2, ticket1) > 0);
        assertEquals(0, comparator.compare(ticket2, ticket3));
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "London", 200, 10, 13)); // 3 часа
        manager.add(new Ticket("Moscow", "London", 100, 11, 14)); // 3 часа
        manager.add(new Ticket("Moscow", "Berlin", 150, 12, 14)); // 2 часа
        manager.add(new Ticket("Moscow", "London", 150, 14, 17)); // 3 часа
        manager.add(new Ticket("Moscow", "London", 120, 15, 16)); // 1 час

        Ticket[] result = manager.searchAndSortBy("Moscow", "London", new TicketTimeComparator());
        assertEquals(4, result.length);
        assertEquals(120, result[0].getPrice());
        assertEquals(200, result[1].getPrice());
        assertEquals(100, result[2].getPrice());
        assertEquals(150, result[3].getPrice());
    }

    @Test
    public void testSearchWhenNoTicketsFound() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "London", 200, 10, 12));
        manager.add(new Ticket("Moscow", "Paris", 150, 12, 14));

        Ticket[] result = manager.search("Moscow", "Berlin");
        assertEquals(0, result.length);
    }
    @Test
    public void testCompareToWhenFirstTicketIsCheaper() {
        Ticket ticket1 = new Ticket("Moscow", "London", 100, 10, 12);
        Ticket ticket2 = new Ticket("Moscow", "Paris", 200, 12, 14);

        assertEquals(-100, ticket1.compareTo(ticket2));
    }

    @Test
    public void testCompareToWhenTicketsHaveSamePrice() {
        Ticket ticket1 = new Ticket("Moscow", "London", 150, 10, 12);
        Ticket ticket2 = new Ticket("Moscow", "Berlin", 150, 12, 14);

        assertEquals(0, ticket1.compareTo(ticket2));
    }

    @Test
    public void testCompareToWhenFirstTicketIsMoreExpensive() {
        Ticket ticket1 = new Ticket("Moscow", "London", 300, 10, 12);
        Ticket ticket2 = new Ticket("Moscow", "Paris", 200, 12, 14);

        assertEquals(100, ticket1.compareTo(ticket2));
    }

    @Test
    public void testGetFlightTime() {
        Ticket ticket = new Ticket("Moscow", "London", 200, 10, 15);
        assertEquals(5, ticket.getFlightTime());
    }

    @Test
    public void testEqualsWithSameTickets() {
        Ticket ticket1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket ticket2 = new Ticket("Moscow", "London", 200, 10, 12);

        assertEquals(ticket1, ticket2);
    }

    @Test
    public void testEqualsWithDifferentTickets() {
        Ticket ticket1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket ticket2 = new Ticket("Moscow", "Paris", 200, 10, 12);

        assertNotEquals(ticket1, ticket2);
    }

    @Test
    public void testHashCodeForEqualTickets() {
        Ticket ticket1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket ticket2 = new Ticket("Moscow", "London", 200, 10, 12);

        assertEquals(ticket1.hashCode(), ticket2.hashCode());
    }

    @Test
    public void testGetters() {
        Ticket ticket = new Ticket("Moscow", "London", 200, 10, 12);

        assertEquals("Moscow", ticket.getFrom());
        assertEquals("London", ticket.getTo());
        assertEquals(200, ticket.getPrice());
        assertEquals(10, ticket.getTimeFrom());
        assertEquals(12, ticket.getTimeTo());
    }

    @Test
    public void testCompareToWithNull() {
        Ticket ticket = new Ticket("Moscow", "London", 200, 10, 12);

        assertThrows(NullPointerException.class, () -> {
            ticket.compareTo(null);
        });
    }
}