import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    void createChallengeP1Test() {
        AppSystem system = AppSystem.getInstance();

        // Crear jugador 1
        Player player1 = new Player("player1", "p1", "12345689", system.getUniqueRegisterCode());
        Lycanthrope characterP1 = new Lycanthrope();
        characterP1.setName("LicantropoP1");

        List<Gift> gifts = (List<Gift>) system.load2("dones.xml", new ArrayList<Gift>());
        SpecialAbility.setGifts(gifts);
        characterP1.setSpecialAbility(gifts.getFirst());

        // Cargar armas y armaduras desde XML
        List<Weapon> weapons = (List<Weapon>) system.load2("weapons.xml", new ArrayList<Weapon>());
        Equipment.setWeapons(weapons);

        List<Armour> armours = (List<Armour>) system.load2("armours.xml", new ArrayList<Armour>());
        Equipment.setArmours(armours);

        List<Equipment> equipment = new ArrayList<>();

        // Selección de arma y armadura (simulado)
        List<Weapon> activeWeaponsP1 = new ArrayList<>();
        activeWeaponsP1.add(weapons.get(0));
        characterP1.setActiveWeapons(activeWeaponsP1);
        characterP1.setWeapons(weapons);
        equipment.add(weapons.get(0));

        Armour armourP1 = armours.get(0);
        characterP1.setActiveArmour(armourP1);
        characterP1.setArmours(armours);
        equipment.add(armourP1);
        characterP1.setActiveEquipment(equipment);

        // Esbirro
        Flunky flunkyP1 = new Ghoul();
        ((Ghoul) flunkyP1).setDependency(2);
        flunkyP1.setName("EsbirroP1");
        flunkyP1.setHealth(2);
        List<Flunky> flunkiesP1 = new ArrayList<>();
        flunkiesP1.add(flunkyP1);
        characterP1.setFlunkies(flunkiesP1);

        // Resto de atributos
        characterP1.setRage(0);
        characterP1.setHealth(5);
        characterP1.setPower(5);

        Modifier modifier = new Modifier();
        characterP1.setWeaknesses(List.of(modifier.getWeakness("Torpeza")));
        characterP1.setStrenghts(List.of(modifier.getStrenght("Velocidad")));

        player1.setCharacter(characterP1);

        // Crear jugador 2
        Player player2 = new Player("player2", "p2", "12345689", system.getUniqueRegisterCode());
        Lycanthrope characterP2 = new Lycanthrope();
        characterP2.setName("LicantropoP2");
        characterP2.setSpecialAbility(gifts.get(1));

        List<Weapon> activeWeaponsP2 = new ArrayList<>();
        activeWeaponsP2.add(weapons.get(1));
        characterP2.setActiveWeapons(activeWeaponsP2);
        characterP2.setWeapons(weapons);

        Armour armourP2 = armours.get(1);
        characterP2.setActiveArmour(armourP2);
        characterP2.setArmours(armours);
        characterP2.setActiveEquipment(List.of(weapons.get(1), armourP2));

        Flunky flunkyP2 = new Human();
        ((Human) flunkyP2).setLoyalty(Human.Loyalty.HIGH);
        flunkyP2.setName("EsbirroP2");
        flunkyP2.setHealth(2);
        characterP2.setFlunkies(List.of(flunkyP2));

        characterP2.setRage(0);
        characterP2.setHealth(5);
        characterP2.setPower(5);
        characterP2.setWeaknesses(List.of(modifier.getWeakness("Torpeza")));
        characterP2.setStrenghts(List.of(modifier.getStrenght("Velocidad")));

        player2.setCharacter(characterP2);

        // Crear desafío
        Challenge challenge = new Challenge(player1, player2, 100);
        player1.addChallenge(challenge);

        // Verificaciones
        List<Challenge> challengesP1 = player1.getPendingChallenges();
        List<Challenge> challengesP2 = player2.getPendingChallenges();

        assertEquals(1, challengesP1.size());
        assertEquals(0, challengesP2.size());
    }


    @Test //
    void createChallengeP2Test() {

        AppSystem system = AppSystem.getInstance();
        Player player1 = new Player("player1", "p1", "12345689", system.getUniqueRegisterCode());

        Vampire characterP1 = new Vampire();
        characterP1.setName("vampiro");
        characterP1.setAge(300);
        List<Discipline> disciplines = (List<Discipline>)system.load2("disciplinas.xml", new ArrayList<Discipline>());
        SpecialAbility.setDisciplines(disciplines);
        characterP1.setSpecialAbility(disciplines.getFirst());

        // Cargar armas y armaduras desde XML
        List<Weapon> weapons = (List<Weapon>) system.load2("weapons.xml", new ArrayList<Weapon>());
        Equipment.setWeapons(weapons);

        List<Armour> armours = (List<Armour>) system.load2("armours.xml", new ArrayList<Armour>());
        Equipment.setArmours(armours);

        List<Equipment> equipment = new ArrayList<>();

        // Selección de arma y armadura (simulado)
        List<Weapon> activeWeaponsP1 = new ArrayList<>();
        activeWeaponsP1.add(weapons.get(0));
        characterP1.setActiveWeapons(activeWeaponsP1);
        characterP1.setWeapons(weapons);
        equipment.add(weapons.get(0));

        Armour armourP1 = armours.get(1);
        characterP1.setActiveArmour(armourP1);
        characterP1.setArmours(armours);
        equipment.add(armourP1);
        characterP1.setActiveEquipment(equipment);

        // Esbirro
        Flunky flunkyP1 = new Demon();
        ((Demon) flunkyP1).setPact("Pacto de sangre");
        flunkyP1.setName("EsbirroP1");
        flunkyP1.setHealth(3);
        List<Flunky> flunkiesP1 = new ArrayList<>();
        flunkiesP1.add(flunkyP1);
        characterP1.setFlunkies(flunkiesP1);

        // Resto de atributos

        characterP1.setHealth(5);
        characterP1.setPower(3);
        characterP1.setBloodPoints(10);

        Modifier modifier = new Modifier();
        characterP1.setWeaknesses(List.of(modifier.getWeakness("Luz")));
        characterP1.setStrenghts(List.of(modifier.getStrenght("Velocidad")));
        player1.setCharacter(characterP1);


        Player player2 = new Player("player2", "p2", "12345689", system.getUniqueRegisterCode());

        Lycanthrope characterP2 = new Lycanthrope();
        characterP2.setName("licantropo2");
        List<Gift> gifts = (List<Gift>) system.load2("dones.xml", new ArrayList<Gift>());
        SpecialAbility.setGifts(gifts);

        SpecialAbility.setGifts(gifts);
        characterP2.setSpecialAbility(gifts.get(1));


        List<Weapon> activeWeaponsP2 = new ArrayList<>();
        activeWeaponsP2.add(weapons.get(1));
        characterP2.setActiveWeapons(activeWeaponsP2);
        characterP2.setWeapons(weapons);

        Armour armourP2 = armours.get(2);
        characterP2.setActiveArmour(armourP2);
        characterP2.setArmours(armours);
        characterP2.setActiveEquipment(List.of(weapons.get(1), armourP2));

        Ghoul  flunkyP2 = new Ghoul();
        flunkyP2.setName("EsbirroP2");
        flunkyP2.setHealth(3);
        flunkyP2.setDependency(3);

        Human human = new Human();
        human.setName("Esirro2P2");
        human.setHealth(1);
        human.setLoyalty(Human.Loyalty.LOW);

        characterP2.setFlunkies(List.of(flunkyP2,human ));

        characterP2.setRage(8);
        characterP2.setHealth(5);
        characterP2.setPower(4);
        characterP2.setWeaknesses(List.of(modifier.getWeakness("Torpeza")));
        characterP2.setStrenghts(List.of(modifier.getStrenght("Voluntad de Hierro")));

        player2.setCharacter(characterP2);

        Challenge challenge = new Challenge(player2, player1, 270);
        player2.addChallenge(challenge);

        List<Challenge> challengesP1 = player1.getPendingChallenges();
        List<Challenge> challengesP2 = player2.getPendingChallenges();

        assertEquals(0, challengesP1.size());
        assertEquals(1, challengesP2.size());
    }

    @Test //
    void createChallengeP3Test() {

        AppSystem system = AppSystem.getInstance();
        Player player1 = new Player("player1", "p1", "12345689", system.getUniqueRegisterCode());

        Hunter characterP1 = new Hunter();
        characterP1.setName("hunter");

        List<Talent> talents = (List<Talent>)system.load2("talentos.xml", new ArrayList<Talent>());
        SpecialAbility.setTalents(talents);
        characterP1.setSpecialAbility(talents.get(1));

        // Cargar armas y armaduras desde XML
        List<Weapon> weapons = (List<Weapon>) system.load2("weapons.xml", new ArrayList<Weapon>());
        Equipment.setWeapons(weapons);

        List<Armour> armours = (List<Armour>) system.load2("armours.xml", new ArrayList<Armour>());
        Equipment.setArmours(armours);

        List<Equipment> equipment = new ArrayList<>();

        // Selección de arma y armadura (simulado)
        List<Weapon> activeWeaponsP1 = new ArrayList<>();
        activeWeaponsP1.add(weapons.get(0));
        characterP1.setActiveWeapons(activeWeaponsP1);
        characterP1.setWeapons(weapons);
        equipment.add(weapons.get(0));

        Armour armourP1 = armours.get(1);
        characterP1.setActiveArmour(armourP1);
        characterP1.setArmours(armours);
        equipment.add(armourP1);
        characterP1.setActiveEquipment(equipment);

        // Esbirro
        Human human = new Human();
        human.setName("EsirroP1");
        human.setHealth(5);
        human.setLoyalty(Human.Loyalty.LOW);

        Ghoul  ghoul = new Ghoul();
        ghoul.setName("EsbirroP2");
        ghoul.setHealth(3);
        ghoul.setDependency(3);
        characterP1.setFlunkies(List.of(human,ghoul ));

        // Resto de atributos

        characterP1.setHealth(4);
        characterP1.setPower(5);
        characterP1.setWill(6);

        Modifier modifier = new Modifier();
        characterP1.setWeaknesses(List.of(modifier.getWeakness("Torpeza")));
        characterP1.setStrenghts(List.of(modifier.getStrenght("Voluntad de Hierro")));
        player1.setCharacter(characterP1);


        Player player2 = new Player("player2", "p2", "12345689", system.getUniqueRegisterCode());

        Lycanthrope  characterP2 = new Lycanthrope ();
        characterP2.setName("lycanthrope");
        List<Gift> gifts = (List<Gift>) system.load2("dones.xml", new ArrayList<Gift>());
        SpecialAbility.setGifts(gifts);

        SpecialAbility.setGifts(gifts);
        characterP2.setSpecialAbility(gifts.get(0));


        List<Weapon> activeWeaponsP2 = new ArrayList<>();
        activeWeaponsP2.add(weapons.get(1));
        characterP2.setActiveWeapons(activeWeaponsP2);
        characterP2.setWeapons(weapons);

        Armour armourP2 = armours.get(1);
        characterP2.setActiveArmour(armourP2);
        characterP2.setArmours(armours);
        characterP2.setActiveEquipment(List.of(weapons.get(0), armourP2));



        Demon demon = new Demon();
        demon.setName("EsirroP2");
        demon.setHealth(5);
        demon.setPact("Pacto de luz");

        characterP2.setFlunkies(List.of(demon));


        characterP2.setHealth(4);
        characterP2.setPower(4);
        characterP2.setRage(3);
        characterP2.setWeaknesses(List.of(modifier.getWeakness("Luz")));
        characterP2.setStrenghts(List.of(modifier.getStrenght("Noche")));

        player2.setCharacter(characterP2);

        Challenge challenge = new Challenge(player2, player1, 150);
        player1.addChallenge(challenge);

        List<Challenge> challengesP1 = player1.getPendingChallenges();
        List<Challenge> challengesP2 = player2.getPendingChallenges();

        assertEquals(1, challengesP1.size());
        assertEquals(0, challengesP2.size());
    }

    @Test
    void testAddMultipleChallenges() {
        AppSystem system = AppSystem.getInstance();

        // Crear jugadores
        Player p1 = new Player("A", "user1", "pass1234", system.getUniqueRegisterCode());
        Player p2 = new Player("B", "user2", "pass2346", system.getUniqueRegisterCode());

        // Asignar personajes a los jugadores
        Character char1 = new Character("Char1", "Type1", 100, 50); // puedes ajustar los valores según tu clase
        Character char2 = new Character("Char2", "Type2", 90, 40);

        p1.setCharacter(char1);
        p2.setCharacter(char2);

        // Crear desafíos
        Challenge c1 = new Challenge(p2, p1, 50);
        Challenge c2 = new Challenge(p2, p1, 75);

        // Agregar desafíos a p2 (como atacante)
        p2.addChallenge(c1);
        p2.addChallenge(c2);

        // Verificar que se agregaron correctamente
        assertEquals(2, p2.getPendingChallenges().size(), "Debe haber dos desafíos pendientes.");
    }

    @Test
    void testPlayerCharacterAssignment() {
        AppSystem system = AppSystem.getInstance();
        Player p = new Player("Nombre", "username", "12345689", system.getUniqueRegisterCode());

        Lycanthrope lycan = new Lycanthrope();
        lycan.setName("Lobo");

        p.setCharacter(lycan);

        assertNotNull(p.getCharacter());
        assertEquals("Lobo", p.getCharacter().getName());
    }
}