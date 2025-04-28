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
        Ticket t1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket t2 = new Ticket("Moscow", "London", 100, 11, 13);
        Ticket t3 = new Ticket("Moscow", "Berlin", 150, 12, 14);
        Ticket t4 = new Ticket("Moscow", "London", 150, 14, 16);

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);

        Ticket[] expected = {t2, t4, t1};
        assertArrayEquals(expected, manager.search("Moscow", "London"));
    }

    @Test
    public void testTicketTimeComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("Moscow", "London", 200, 10, 12); // 2 часа
        Ticket t2 = new Ticket("Moscow", "Paris", 150, 12, 15); // 3 часа
        Ticket t3 = new Ticket("Moscow", "Berlin", 200, 14, 17); // 3 часа

        assertTrue(comparator.compare(t1, t2) < 0);
        assertTrue(comparator.compare(t2, t1) > 0);
        assertEquals(0, comparator.compare(t2, t3));
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Moscow", "London", 200, 10, 13); // 3 часа
        Ticket t2 = new Ticket("Moscow", "London", 100, 11, 14); // 3 часа
        Ticket t3 = new Ticket("Moscow", "London", 150, 14, 17); // 3 часа
        Ticket t4 = new Ticket("Moscow", "London", 120, 15, 16); // 1 час

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);

        Ticket[] expected = {t4, t1, t2, t3};
        assertArrayEquals(expected,
                manager.searchAndSortBy("Moscow", "London", new TicketTimeComparator()));
    }

    @Test
    public void testSearchWhenNoTicketsFound() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "London", 200, 10, 12));
        manager.add(new Ticket("Moscow", "Paris", 150, 12, 14));

        assertArrayEquals(new Ticket[0], manager.search("Moscow", "Berlin"));
    }

    @Test
    public void testFindAllTickets() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket t2 = new Ticket("Moscow", "Paris", 150, 12, 14);

        manager.add(t1);
        manager.add(t2);

        Ticket[] expected = {t1, t2};
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    public void testCompareToWhenFirstTicketIsCheaper() {
        Ticket t1 = new Ticket("Moscow", "London", 100, 10, 12);
        Ticket t2 = new Ticket("Moscow", "Paris", 200, 12, 14);

        assertEquals(-100, t1.compareTo(t2));
    }

    @Test
    public void testCompareToWhenTicketsHaveSamePrice() {
        Ticket t1 = new Ticket("Moscow", "London", 150, 10, 12);
        Ticket t2 = new Ticket("Moscow", "Berlin", 150, 12, 14);

        assertEquals(0, t1.compareTo(t2));
    }

    @Test
    public void testCompareToWhenFirstTicketIsMoreExpensive() {
        Ticket t1 = new Ticket("Moscow", "London", 300, 10, 12);
        Ticket t2 = new Ticket("Moscow", "Paris", 200, 12, 14);

        assertEquals(100, t1.compareTo(t2));
    }

    @Test
    public void testGetFlightTime() {
        Ticket ticket = new Ticket("Moscow", "London", 200, 10, 15);
        assertEquals(5, ticket.getFlightTime());
    }

    @Test
    public void testEqualsWithSameTickets() {
        Ticket t1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket t2 = new Ticket("Moscow", "London", 200, 10, 12);

        assertEquals(t1, t2);
    }

    @Test
    public void testEqualsWithDifferentTickets() {
        Ticket t1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket t2 = new Ticket("Moscow", "Paris", 200, 10, 12);

        assertNotEquals(t1, t2);
    }

    @Test
    public void testHashCodeForEqualTickets() {
        Ticket t1 = new Ticket("Moscow", "London", 200, 10, 12);
        Ticket t2 = new Ticket("Moscow", "London", 200, 10, 12);

        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    public void testGetters() {
        Ticket t = new Ticket("Moscow", "London", 200, 10, 12);

        assertArrayEquals(new Object[]{"Moscow", "London", 200, 10, 12},
                new Object[]{t.getFrom(), t.getTo(), t.getPrice(), t.getTimeFrom(), t.getTimeTo()});
    }

    @Test
    public void testCompareToWithNull() {
        Ticket ticket = new Ticket("Moscow", "London", 200, 10, 12);

        assertThrows(NullPointerException.class, () -> ticket.compareTo(null));
    }
}