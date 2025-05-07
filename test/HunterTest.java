import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HunterTest {

    private Hunter hunter;

    @BeforeEach
    void setUp() { hunter = new Hunter(); }

    @AfterEach
    void tearDown() { hunter = null; }

    @Test
    void testAbilityWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n4\n2\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        hunter = hunter.creationMenu();

        assertNotNull(hunter.getSpecialAbility());
    }

    @Test
    void testAbilityWithValidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        hunter = hunter.creationMenu();

        assertNotNull(hunter.getSpecialAbility());
    }

    @Test
    void testWeaponWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n2\n3\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        hunter = hunter.creationMenu();

        assertNotNull(hunter.getActiveWeapons());
    }

    @Test
    void testWeaponWithValidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        hunter = hunter.creationMenu();

        assertNotNull(hunter.getActiveWeapons());
    }

    @Test
    void testArmourWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n5\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        hunter = hunter.creationMenu();

        assertNotNull(hunter.getActiveArmour());
    }

    @Test
    void testArmourWithValidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        hunter = hunter.creationMenu();

        assertNotNull(hunter.getActiveArmour());
    }

    @Test
    void testMinionWithInvalidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\n1\n5\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        hunter = hunter.creationMenu();

        assertNotNull(hunter.getFlunkies());
    }

    @Test
    void testMinionWithValidOption() throws FileNotFoundException {
        String input = "Juan\n2\n1\n1\n1\n1\n1\njuan";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        AppSystem ap = new AppSystem();
        ap.load();

        hunter = hunter.creationMenu();

        assertNotNull(hunter.getFlunkies());
    }

}