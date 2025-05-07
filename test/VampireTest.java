import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class VampireTest {

    private Vampire vampire;

    @BeforeEach
    void setUp() {
        vampire = new Vampire();
    }

    @AfterEach
    void tearDown() {
        vampire = null;
    }


    @Test
    void testAgeWithNegativeAge() throws FileNotFoundException {
        // Simulates the user entering a String instead of an integer for the age
        String input = "Juan\n-2\n123\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertTrue(vampire.getAge() >= 0);
    }

    @Test
    void testAge() throws FileNotFoundException {
        String input = "Juan\n123\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertTrue(vampire.getAge() >= 0);
    }

    @Test
    void testAbilityWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n123\n4\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertNotNull(vampire.getSpecialAbility());
    }

    @Test
    void testAbilityWithValidOption() throws FileNotFoundException {
        String input = "Juan\n123\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertNotNull(vampire.getSpecialAbility());
    }

    @Test
    void testWeaponWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n123\n2\n3\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertNotNull(vampire.getActiveWeapons());
    }

    @Test
    void testWeaponWithValidOption() throws FileNotFoundException {
        String input = "Juan\n123\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertNotNull(vampire.getActiveWeapons());
    }

    @Test
    void testArmourWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n123\n2\n1\n5\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertNotNull(vampire.getActiveArmour());
    }

    @Test
    void testArmourWithValidOption() throws FileNotFoundException {
        String input = "Juan\n123\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertNotNull(vampire.getActiveArmour());
    }

    @Test
    void testMinionWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n123\n2\n1\n1\n5\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertNotNull(vampire.getFlunkies());
    }

    @Test
    void testMinionWithValidOption() throws FileNotFoundException {
        String input = "Juan\n123\n2\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        vampire = vampire.creationMenu();

        assertNotNull(vampire.getFlunkies());
    }
}