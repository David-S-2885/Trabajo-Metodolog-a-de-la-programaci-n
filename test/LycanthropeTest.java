import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.List;

public class LycanthropeTest {

    private Lycanthrope lycanthrope;

    @BeforeEach
    void setUp() { lycanthrope = new Lycanthrope(); }

    @AfterEach
    void tearDown() {
        lycanthrope = null;
    }

    @Test
    void testAbilityWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n4\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        lycanthrope = lycanthrope.creationMenu();

        assertNotNull(lycanthrope.getSpecialAbility());
    }

    @Test
    void testAbilityWithValidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        lycanthrope = lycanthrope.creationMenu();

        assertNotNull(lycanthrope.getSpecialAbility());
    }

    @Test
    void testWeaponWithInvalidOption() throws FileNotFoundException {
        // Simulates the user choosing an invalid option for the weapon
        String input = "Juan\n2\n3\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        lycanthrope = lycanthrope.creationMenu();

        assertNotNull(lycanthrope.getActiveWeapons());
    }

    @Test
    void testWeaponWithValidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        lycanthrope = lycanthrope.creationMenu();

        assertNotNull(lycanthrope.getActiveWeapons());
    }

    @Test
    void testArmourWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n5\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        lycanthrope = lycanthrope.creationMenu();

        assertNotNull(lycanthrope.getActiveArmour());
    }

    @Test
    void testArmourWithValidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        lycanthrope = lycanthrope.creationMenu();

        assertNotNull(lycanthrope.getActiveArmour());
    }

    @Test
    void testMinionWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n5\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        lycanthrope = lycanthrope.creationMenu();

        assertNotNull(lycanthrope.getFlunkies());
    }

    @Test
    void testMinionWithValidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        lycanthrope = lycanthrope.creationMenu();

        assertNotNull(lycanthrope.getFlunkies());
    }
}