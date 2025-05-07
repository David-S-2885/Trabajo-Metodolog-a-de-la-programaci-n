import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;

public class RegisterScreenTest {

    @Test
    void testRegisterScreenOpcionIncorrecta3() {

        String input = "5\n3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        GameManager gameManager = new GameManager();
        gameManager.register();

        String expectedOutput = """
                1. Crear cuenta de jugador
                2. Crear cuenta de operador
                3. Volver al menú de inicio
                Introduzca una opción: Introduzca una opción válida
                1. Crear cuenta de jugador
                2. Crear cuenta de operador
                3. Volver al menú de inicio
                Introduzca una opción:
                """;

        assertEquals(expectedOutput.trim().replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\r", ""));
    }

    @Test
    void testRegisterScreenOpcionIncorrecta2() {
        String input = "5\n2\nnombre\nnick\n12345678\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        GameManager gameManager = new GameManager();
        gameManager.register();

        String expectedOutput = """
            1. Crear cuenta de jugador
            2. Crear cuenta de operador
            3. Volver al menú de inicio
            Introduzca una opción: Introduzca una opción válida
            1. Crear cuenta de jugador
            2. Crear cuenta de operador
            3. Volver al menú de inicio
            Introduzca una opción:\s
            Introduzca un nombre de usuario: Introduzca un nick: Introduzca una constraseña que contenga entre 8 y 12 caracteres:\s
            Cuenta creada
            """;

        assertEquals(expectedOutput.trim().replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\r", ""));
    }

    @Test
    void testRegisterScreenOpcionCorrecta() {

        String input = "3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Capturamos salida
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        GameManager gameManager = new GameManager();
        gameManager.register();

        String expectedOutput = """
                1. Crear cuenta de jugador
                2. Crear cuenta de operador
                3. Volver al menú de inicio
                Introduzca una opción:
                """;

        assertEquals(expectedOutput.trim().replaceAll("\r", ""), outputStream.toString().trim().replaceAll("\r",""));
    }

}