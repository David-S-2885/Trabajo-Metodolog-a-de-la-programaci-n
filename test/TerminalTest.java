import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TerminalTest {

    @Test
    void testLoginMenuValidInput() {
        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Terminal terminal = new Terminal();
        int option = terminal.login();

        assertEquals(2, option);
    }

    @Test
    void testPrincipalMenuValidInput() {
        String simulatedInput = "4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Terminal terminal = new Terminal();
        int option = terminal.principalMenu();

        assertEquals(4, option);
    }

    @Test
    void testPrincipalMenuInvalidThenValidInput() {
        String simulatedInput = "abc\n6\n3\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Terminal terminal = new Terminal();
        int option = terminal.principalMenu();

        String output = out.toString();

        assertTrue(output.contains("Por favor, introduzca un número válido."));
        assertEquals(3, option);
    }

    @Test
    void testAdminMenuValidInput() {
        String simulatedInput = "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Terminal terminal = new Terminal();
        int option = terminal.adminMenu();

        assertEquals(5, option);
    }

    @Test
    void testAdminMenuInvalidThenValidInput() {
        String simulatedInput = "xyz\n0\n2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Terminal terminal = new Terminal();
        int option = terminal.adminMenu();

        String output = out.toString();

        assertTrue(output.contains("Por favor, introduzca un número válido."));
        assertEquals(2, option);
    }

    @Test
    void testLoginInvalidTwiceThenValid() {
        String input = "5\n0\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Terminal terminal = new Terminal();
        int result = terminal.login();

        assertEquals(1, result, "Después de opciones inválidas, se espera login exitoso.");
    }

    @Test
    void testLoginExitProgram() {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Terminal terminal = new Terminal();
        int result = terminal.login();

        assertEquals(3, result, "Seleccionar la opción 3 debe devolver 3 como salida.");
    }

}
