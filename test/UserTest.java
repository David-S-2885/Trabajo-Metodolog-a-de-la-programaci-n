import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    static class TestUser extends User {
        public TestUser(String name, String nick, String password) {
            super(name, nick, password);
        }
    }

    @BeforeEach
    void setUp() {
        user = new TestUser("TestName", "TestNick", "TestPassword");
    }

    @Test
    void testAddAndDeleteNotifications() {
        Notification n1 = new Notification("Message 1");
        Notification n2 = new Notification("Message 2");

        user.addNotification(n1);
        user.addNotification(n2);

        assertEquals(2, user.getNotifications().size());

        user.deleteNotifications();
        assertEquals(0, user.getNotifications().size());
    }

    @Test
    void testBlockedFlag() {
        user.setBlocked(true);
        assertTrue(user.isBlocked());

        user.setBlocked(false);
        assertFalse(user.isBlocked());
    }

    @Test
    void testSetNotifications() {
        Notification n1 = new Notification("Test1");
        Notification n2 = new Notification("Test2");

        user.setNotifications(List.of(n1, n2));
        assertEquals(2, user.getNotifications().size());
    }

    @Test
    void testGetGainedGoldThrowsIfNotPlayer() {
        assertThrows(RuntimeException.class, user::getGainedGold);
    }

    @Test
    void testGetLostGoldThrowsIfNotPlayer() {
        assertThrows(RuntimeException.class, user::getLostGold);
    }

    @Test
    public void testNotifications() {
        Notification n1 = new Notification("Bienvenido!");
        n1.notify(user);

        assertEquals(1, user.getNotifications().size());
        assertEquals("Bienvenido!", user.getNotifications().get(0).getMessage());
    }

}

