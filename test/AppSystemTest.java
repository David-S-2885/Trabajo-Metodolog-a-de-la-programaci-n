import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppSystemTest {

    private User user;

    // Creamos una clase concreta mínima para poder testear User
    static class TestUser extends User {
        public TestUser(String name, String nick, String password) {
            super(name, nick, password);
        }
    }

    @BeforeEach
    void setUp() {
        user = new TestUser("TestName", "TestNick", "TestPassword");
    }

    @Test
    public void testNotificationsAppSystem() {
        AppSystem app = new AppSystem();

        app.registerUser(user);

        Notification notification = new Notification("Mensaje de prueba");
        notification.notify(user);

        assertEquals(1, user.getNotifications().size());
        assertEquals("Mensaje de prueba", user.getNotifications().get(0).getMessage());

        app.login("TestName", "TestPassword");

        String input = "\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        app.notifications();

        assertEquals(0, user.getNotifications().size());
    }

    @Test
    public void testRegister() {

        AppSystem a = new AppSystem();

        Player p = new Player("Jose María", "Pepe","123", a.getUniqueRegisterCode());

        a.registerUser(p);

        assertNotNull(a.getUser("Pepe"));
    }

    @Test
    void testCargarListaDeWeaknessDesdeFichero() {

        AppSystem app= new  AppSystem();

        Object cargado = app.load2("weaknesses.xml", new ArrayList<Weakness>());

        assertNotNull(cargado);
        assertTrue( cargado instanceof List<?>);

        List<Weakness> lista = (List<Weakness>) cargado;

        assertFalse(lista.isEmpty());
        assertTrue(lista.get(0) instanceof Weakness);


        List<Weakness> debilidades = (List<Weakness>) lista;


        assertEquals(2, debilidades.size());

        Weakness w1 = debilidades.get(0);
        assertEquals("Luz", w1.getName());
        assertEquals(4, w1.getValue());

        Weakness w2 = debilidades.get(1);
        assertEquals("Torpeza", w2.getName());
        assertEquals(3, w2.getValue());
    }

    @Test
    void testCargarListaDeArmourDesdeFichero() {

        AppSystem app = new AppSystem();


        Object cargado = app.load2("armours.xml", new ArrayList<Armour>());

        assertNotNull(cargado);
        assertTrue(cargado instanceof List<?>);

        List<Armour> lista = (List<Armour>) cargado;

        assertFalse(lista.isEmpty());

        assertTrue(lista.get(0) instanceof Armour);
        assertEquals(4, lista.size());

        // propiedades de cada armadura
        Armour a1 = lista.get(0);
        assertEquals("Armadura de cuero", a1.getName());
        assertEquals(1, a1.getAttackModifier());
        assertEquals(2, a1.getDefenseModifier());

        Armour a2 = lista.get(1);
        assertEquals("Armadura de bronce", a2.getName());
        assertEquals(2, a2.getAttackModifier());
        assertEquals(2, a2.getDefenseModifier());

        Armour a3 = lista.get(2);
        assertEquals("Armadura de oro", a3.getName());
        assertEquals(2, a3.getAttackModifier());
        assertEquals(1, a3.getDefenseModifier());

        Armour a4 = lista.get(3);
        assertEquals("Armadura de acero", a4.getName());
        assertEquals(3, a4.getAttackModifier());
        assertEquals(2, a4.getDefenseModifier());
    }

    @Test
    void testCargarListaDeWeaponsDesdeFichero() {

        AppSystem app = new AppSystem();


        Object cargado = app.load2("weapons.xml", new ArrayList<Weapon>());


        assertNotNull(cargado);
        assertTrue(cargado instanceof List<?>);


        List<Weapon> lista = (List<Weapon>) cargado;

        assertFalse(lista.isEmpty());

        assertTrue(lista.get(0) instanceof Weapon);

        // tamaño de la lista
        assertEquals(2, lista.size());


        Weapon w1 = lista.get(0);
        assertEquals("Espada Larga", w1.getName());
        assertEquals(3, w1.getAttackModifier());
        assertEquals(1, w1.getDefenseModifier());
        assertTrue(w1.isBothHands());

        Weapon w2 = lista.get(1);
        assertEquals("Daga", w2.getName());
        assertEquals(2, w2.getAttackModifier());
        assertEquals(2, w2.getDefenseModifier());
        assertFalse(w2.isBothHands());
    }
    @Test
    void testCargarListaDeTalentosDesdeFichero() {

        AppSystem app = new AppSystem();

        Object cargado = app.load2("talentos.xml", new ArrayList<Talent>());

        // Verificamos que el objeto no sea nulo y que sea una lista
        assertNotNull(cargado);
        assertTrue(cargado instanceof List<?>);

        List<Talent> lista = (List<Talent>) cargado;

        assertFalse(lista.isEmpty());


        assertTrue(lista.get(0) instanceof Talent);

        // tamaño de la lista
        assertEquals(3, lista.size());


        Talent t1 = lista.get(0);
        assertEquals("Tiro preciso", t1.getName());
        assertEquals(3, t1.getAttackValue());
        assertEquals(1, t1.getDefenseValue());

        Talent t2 = lista.get(1);
        assertEquals("Escudo humano", t2.getName());
        assertEquals(2, t2.getAttackValue());
        assertEquals(2, t2.getDefenseValue());

        Talent t3 = lista.get(2);
        assertEquals("Visión cazadora", t3.getName());
        assertEquals(1, t3.getAttackValue());
        assertEquals(3, t3.getDefenseValue());
    }

    @Test
    void testCargarListaDeStrengthsDesdeFichero() {
        AppSystem app = new AppSystem();

        // Cargar objeto desde el archivo
        Object cargado = app.load2("strenghts.xml", new ArrayList<Strength>());

        assertNotNull(cargado);
        assertTrue(cargado instanceof List<?>);

        List<Strength> lista = (List<Strength>) cargado;

        //lista no vacía
        assertFalse(lista.isEmpty());

        assertTrue(lista.get(0) instanceof Strength);

        assertEquals(3, lista.size());


        Strength s1 = lista.get(0);
        assertEquals("Voluntad de Hierro", s1.getName());
        assertEquals(5, s1.getValue());

        Strength s2 = lista.get(1);
        assertEquals("Velocidad", s2.getName());
        assertEquals(4, s2.getValue());

        Strength s3 = lista.get(2);
        assertEquals("Noche", s3.getName());
        assertEquals(3, s3.getValue());
    }

    @Test
    void testCargarListaDeDonesDesdeFichero() {
        AppSystem app = new AppSystem();

        Object cargado = app.load2("dones.xml", new ArrayList<Gift>());

        assertNotNull(cargado);
        assertTrue(cargado instanceof List<?>);

        List<Gift> lista = (List<Gift>) cargado;


        assertFalse(lista.isEmpty());

        //primer elemento sea una instancia de Gift
        assertTrue(lista.get(0) instanceof Gift);

        // tamaño de la lista
        assertEquals(3, lista.size());

        // cada don
        Gift g1 = lista.get(0);
        assertEquals("Furia salvaje", g1.getName());
        assertEquals(3, g1.getAttackValue());
        assertEquals(1, g1.getDefenseValue());
        assertEquals(2, g1.getMinmumRage());

        Gift g2 = lista.get(1);
        assertEquals("Regeneración", g2.getName());
        assertEquals(2, g2.getAttackValue());
        assertEquals(2, g2.getDefenseValue());
        assertEquals(1, g2.getMinmumRage());

        Gift g3 = lista.get(2);
        assertEquals("Instinto de manada", g3.getName());
        assertEquals(1, g3.getAttackValue());
        assertEquals(3, g3.getDefenseValue());
        assertEquals(3, g3.getMinmumRage());
    }

    @Test
    void testCargarListaDeDisciplinasDesdeFichero(){
        AppSystem app = new AppSystem();
        Object cargado = app.load2("disciplinas.xml", new ArrayList<Discipline>());

        assertNotNull(cargado);
        assertTrue(cargado instanceof List<?>);


        List<Discipline> lista = (List<Discipline>) cargado;


        assertFalse(lista.isEmpty());

        assertTrue(lista.get(0) instanceof Discipline);

        assertEquals(3, lista.size());


        Discipline d1 = lista.get(0);
        assertEquals("Dominación", d1.getName());
        assertEquals(2, d1.getAttackValue());
        assertEquals(2, d1.getDefenseValue());
        assertEquals(2, d1.getBloodCost());

        Discipline d2 = lista.get(1);
        assertEquals("Rapidez", d2.getName());
        assertEquals(3, d2.getAttackValue());
        assertEquals(1, d2.getDefenseValue());
        assertEquals(1, d2.getBloodCost());

        Discipline d3 = lista.get(2);
        assertEquals("Resistencia", d3.getName());
        assertEquals(1, d3.getAttackValue());
        assertEquals(3, d3.getDefenseValue());
        assertEquals(3, d3.getBloodCost());
    }

}