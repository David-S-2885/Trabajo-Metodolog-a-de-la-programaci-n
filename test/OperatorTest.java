import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    private Operator operator;
    private Player player;

    @BeforeEach
    void setUp() {
        operator = new Operator("OperatorName", "OpNick", "OpPass");
        player = new Player("PlayerName", "PlayerNick", "PlayerPass", "PlsyerRegNum");
    }

    @Test
    void testBlockUser() {
        assertFalse(player.isBlocked());
        operator.blockUser(player);
        assertTrue(player.isBlocked());

        assertEquals(1, player.getNotifications().size());
        assertEquals(
                "Has sido bloqueado. No podrás desafíar a otros jugadores o aceptar desafíos.",
                player.getNotifications().get(0).getMessage()
        );
    }

    @Test
    void testUnblockUser() {
        player.setBlocked(true);
        operator.unblockUser(player);
        assertFalse(player.isBlocked());
    }
}
