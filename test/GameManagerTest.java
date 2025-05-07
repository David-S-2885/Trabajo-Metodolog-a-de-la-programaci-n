import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {

    private GameManager gameManager;

    @BeforeEach
    void setup() {
        gameManager = new GameManager();
    }

    @Test
    void testValidPassword_TooShort() {
        assertFalse(invokeValidPassword("abc123")); // < 8 chars
    }

    @Test
    void testValidPassword_TooLong() {
        assertFalse(invokeValidPassword("1234567890123")); // > 12 chars
    }

    @Test
    void testValidPassword_ValidLength() {
        assertTrue(invokeValidPassword("password1")); // 9 chars
    }

    @Test
    void testValidNickname_Valid() {
        String nick = "newNick123";
        assertTrue(invokeValidNickname(nick)); // Should be available
    }

    @Test
    void testValidNickname_Duplicate() {
        AppSystem system = AppSystem.getInstance();
        String nick = "duplicateNick";
        Player player = new Player("user", nick, "password123", system.getUniqueRegisterCode());
        system.registerUser(player);

        assertFalse(invokeValidNickname(nick)); // Already taken
    }

    @Test
    void testLoginScreen_Exit() {
        String simulatedInput = "3\n";  // User selects "Cerrar programa"
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        int result = gameManager.loginScreen();
        assertEquals(1, result); // Because '3' sets k=1
    }

    @Test
    void testLoginScreen_Register() {
        String simulatedInput = "1\nusuario\nnewNick\n12345678\n3";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        AppSystem ap = AppSystem.getInstance();

        gameManager.register();

        assertNotNull(ap.getUser("newNick"));
    }

    @Test
    void testLogin_InvalidCredentialsThenExit() {
        AppSystem system = AppSystem.getInstance();
        String simulatedInput = "invalidUser\nwrongPass\ns\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        boolean result = invokeLogin();
        assertFalse(result);
    }

    @Test
    void testLogin_ValidCredentials() {
        AppSystem system = AppSystem.getInstance();
        Player player = new Player("testuser", "testnick", "securePass", system.getUniqueRegisterCode());
        system.registerUser(player);

        String simulatedInput = "testuser\nsecurePass\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        boolean result = invokeLogin();
        assertTrue(result);
    }

    private boolean invokeValidPassword(String password) {
        return gameManager.validPassword(password);
    }

    private boolean invokeValidNickname(String nick) {
        return gameManager.validNickname(nick);
    }

    private boolean invokeLogin() {
        return gameManager.login();
    }
}