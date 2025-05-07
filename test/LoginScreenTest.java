import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginScreenTest {

    @Test
    void testLoginScreenOpcionIncorrecta() {
        String input = "4\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Capturar la salida del programa
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Terminal terminal = new Terminal();
        int returnedValue = terminal.login();

        Assertions.assertEquals(1, returnedValue);

        String expectedOutput = """
                ---------BIENVENIDO-------
                
                1. Iniciar sesión
                2. Registrarse
                3. Cerrar programa
                Introduzca una opción: Por favor, introduzca un número válido.
                ---------BIENVENIDO-------
                
                1. Iniciar sesión
                2. Registrarse
                3. Cerrar programa
                Introduzca una opción:
                """;

        assertEquals(expectedOutput.trim().replaceAll("\r",""), outContent.toString().trim().replaceAll("\r",""));

    }

    @Test
    void testLoginScreenOpcionCorrecta() {
        String input = "1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Capturar la salida del programa
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Terminal terminal = new Terminal();
        int returnedValue = terminal.login();

        Assertions.assertEquals(1, returnedValue);

        String expectedOutput = """
                ---------BIENVENIDO-------
                
                1. Iniciar sesión
                2. Registrarse
                3. Cerrar programa
                Introduzca una opción:
                """;

        assertEquals(expectedOutput.trim().replaceAll("\r",""), outContent.toString().trim().replaceAll("\r",""));

    }


}
