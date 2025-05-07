import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialAbilityTest {

    @Test
    void testShowInfo() {
        // Crear habilidad de ejemplo
        SpecialAbility ability = new SpecialAbility("Dominación", 4, 2);

        // Capturar la salida de consola
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Llamar al metodo que imprime
        ability.showInfo();

        // Restaurar System.out (opcional, pero buena práctica)
        System.setOut(System.out);

        // Verificar la salida
        String expectedOutput = """
                Name: Dominación
                Attack value: 4
                Defense value: 2
                
                """;

        assertEquals(expectedOutput.replaceAll("\r", ""), output.toString().replaceAll("\r", ""));
    }
}
