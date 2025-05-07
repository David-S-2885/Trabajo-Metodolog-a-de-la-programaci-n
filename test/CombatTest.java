import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CombatTest {
    @Test
    public void testCombatGold() {
        AppSystem system = AppSystem.getInstance();
        Player player1 = new Player("player1", "p1", "12345689", system.getUniqueRegisterCode());

        Hunter characterP1 = new Hunter();
        characterP1.setName("hunter");

        List<Talent> talents = (List<Talent>) system.load2("talentos.xml", new ArrayList<Talent>());
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

        Ghoul ghoul = new Ghoul();
        ghoul.setName("EsbirroP2");
        ghoul.setHealth(3);
        ghoul.setDependency(3);
        characterP1.setFlunkies(List.of(human, ghoul));

        // Resto de atributos

        characterP1.setHealth(4);
        characterP1.setPower(5);
        characterP1.setWill(6);

        Modifier modifier = new Modifier();
        characterP1.setWeaknesses(List.of(modifier.getWeakness("Torpeza")));
        characterP1.setStrenghts(List.of(modifier.getStrenght("Voluntad de Hierro")));
        player1.setCharacter(characterP1);


        Player player2 = new Player("player2", "p2", "12345689", system.getUniqueRegisterCode());

        Lycanthrope characterP2 = new Lycanthrope();
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

        system.registerUser(player1);
        system.registerUser(player2);

        // Crear desafío usando los nicks
        Challenge challenge = new Challenge(player1, player2, 100);  // Oro apostado 100
        challenge.validate();
        player2.addChallenge(challenge);

        player2.getCharacter().setGold(600);

        system.login("player2", "12345689");

        Combat combat = new Combat(challenge, player1, player2);
        combat.startCombat();

        if (combat.getWinner() == player1) {
            assertEquals(600, player1.getCharacter().getGold());
            assertEquals(500, player2.getCharacter().getGold());
        } else if (combat.getWinner() == player2) {
            assertEquals(700, player2.getCharacter().getGold());
            assertEquals(500, player1.getCharacter().getGold());
        } else if (combat.getWinner() == null) {
            assertEquals(500, player1.getCharacter().getGold());
            assertEquals(600, player2.getCharacter().getGold());
        }

        assertFalse(challenge.getDefiant().getNotifications().isEmpty());
        assertTrue(combat.getC1().getHealth() <= 0 || combat.getC2().getHealth() <= 0);  // Uno de los jugadores debería haber caído

    }
}