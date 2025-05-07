import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ModifierTest {

    @Test
    void testGetWeaknessByName() {
        AppSystem system = AppSystem.getInstance();
        List<Weakness> weaknesses = (List<Weakness>) system.load2("weaknesses.xml", new ArrayList<Weakness>());
        Modifier m = new Modifier();
        m.setWeaknesses(weaknesses);
        Weakness result = m.getWeakness("Torpeza");
        assertNotNull(result);
        assertEquals("Torpeza", result.getName());
        assertEquals(3, result.getValue());
    }

    @Test
    void testGetStrengthByName() {
        AppSystem system = AppSystem.getInstance();
        List<Strength> strengths =(List<Strength>) system.load2("strengths.xml", new ArrayList<Strength>());
        Modifier m = new Modifier();
        Strength result = m.getStrenght("Velocidad");
        assertNotNull(result);
        assertEquals("Velocidad", result.getName());
        assertEquals(4, result.getValue());
    }

    @Test
    void testGetWeaknessNotFound() {
        AppSystem system = AppSystem.getInstance();
        List<Weakness> weaknesses = (List<Weakness>) system.load2("weaknesses.xml", new ArrayList<Weakness>());
        Modifier m = new Modifier();
        m.setWeaknesses(weaknesses);
        Weakness resultado = m.getWeakness("Veneno");
        assertNull(resultado);
    }

    @Test
    void testGetStrengthNotFound() {
        Modifier m = new Modifier();
        AppSystem system = AppSystem.getInstance();
        List<Strength> strengths = (List<Strength>) system.load2("strenghts.xml", new ArrayList<Strength>());
        m.setStrengths(strengths);
        Strength resultado = m.getStrenght("Agilidad");
        assertNull(resultado);

    }

    @Test
    void testGetWeaknessEmptyList() {
        Modifier m = new Modifier();

        ArrayList<Weakness> weaknesses = new ArrayList<>();
        m.setWeaknesses(weaknesses);

        Weakness result = m.getWeakness("Veneno");
        assertNull(result);
    }

    @Test
    void testGetStrenghtEmptyList() {
        Modifier m = new Modifier();
        ArrayList<Strength> strengths = new ArrayList<>();
        m.setStrengths(strengths);

        Strength result = m.getStrenght("Velocidad");
        assertNull(result);
    }
}