import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lycanthrope extends Character{
    private int rage;

    public void increaseRage() {
        rage++;
    }

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public Lycanthrope creationMenu() {
        Lycanthrope l = new Lycanthrope();
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        System.out.println("------INFORMACIÓN DEL PERSONAJE------");
        System.out.print("Introduzca el nombre del personaje: ");
        String name = scanner.nextLine();
        l.setName(name);
        System.out.println();

        System.out.println("------HABILIDAD ESPECIAL-------");
        List<Gift> gifts = SpecialAbility.getGifts();
        int i = 1;
        int answer = -1;
        for (Gift d: gifts) {
            System.out.println(i+". "+d.getName()+": "+d.getAttackValue()+" attack, "+d.getDefenseValue()+" defense, "+d.getMinmumRage()+" rabia mínima");
            i++;
        }
        do {
            System.out.print("Elija el don de su personaje: ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println("Introduzca una opción válida");
                scanner.nextLine();
            }
        } while (answer<1 || answer>gifts.size());
        Gift gift = gifts.get(answer-1);
        l.setSpecialAbility(gift);
        System.out.println();


        System.out.println("------ARMA Y ARMADURA------");
        List<Weapon> weapons = Equipment.getWeapons();
        List<Armour> armours = Equipment.getArmours();
        List<Equipment> equipment = new ArrayList<Equipment>();

        int j = 1;
        answer = -1;
        for (Weapon weapon: weapons) {
            System.out.println(j+". "+weapon.getName()+": "+weapon.getAttackModifier()+" attack, "+weapon.getDefenseModifier()+" defense");
            j++;
        }
        do {
            System.out.print("Elija un arma para su personaje: ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println("Introduzca una opción válida");
                scanner.nextLine();
            }
        } while (answer<1 || answer>weapons.size());
        List<Weapon> weapon = new ArrayList<Weapon>();
        weapon.add(weapons.get(answer-1));
        l.setActiveWeapons(weapon);
        equipment.add(weapons.get(answer-1));
        System.out.println();

        answer = -1;
        i = 1;
        for (Armour armour: armours) {
            System.out.println(i+". "+armour.getName()+": "+armour.getAttackModifier()+" attack, "+armour.getDefenseModifier()+" defense");
            i++;
        }
        do {
            System.out.print("Elija una armadura para su personaje: ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println("Introduzca una opción válida");
                scanner.nextLine();
            }
        } while (answer<1 || answer>armours.size());
        Armour activeArmour = armours.get(answer-1);
        l.setActiveArmour(activeArmour);
        equipment.add(activeArmour);
        l.setActiveEquipment(equipment);

        System.out.println();
        System.out.println("------ESBIRRO------");
        List<Flunky> flunkies = new ArrayList<>();
        System.out.println("1. Ghoul");
        System.out.println("2. Demonio");
        System.out.println("3. Humano");
        answer = -1;
        do {
            System.out.print("Elija el tipo de esbirro para su personaje: ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println("Introduzca una opción válida");
                scanner.nextLine();
            }
        } while (answer != 1 && answer != 2 && answer != 3);

        Flunky flunky;

        if (answer == 1) {
            flunky = new Ghoul();
            int dependency = (int) (Math.random()*5+1);
            ((Ghoul)flunky).setDependency(dependency);
            flunky.setHealth(2);
        }
        else if (answer == 2) {
            flunky = new Demon();
            ((Demon) flunky).setPact("Pacto estándard");
            flunky.setHealth(3);
        }
        else {
            flunky = new Human();
            ((Human) flunky).setLoyalty(Human.Loyalty.HIGH);
            flunky.setHealth(2);
        }

        System.out.print("Introduzca el nombre de su esbirro: ");
        String nombre = scanner.nextLine();
        flunky.setName(nombre);
        flunkies.add(flunky);
        l.setFlunkies(flunkies);

        l.setRage(0);
        l.setHealth(5);
        l.setPower(5);

        List<Weakness> weaknesses = new ArrayList<>();
        Modifier m = new Modifier();
        weaknesses.add(m.getWeakness("Torpeza"));
        l.setWeaknesses(weaknesses);
        List<Strength> strengths = new ArrayList<>();
        strengths.add(m.getStrenght("Velocidad"));
        l.setStrenghts(strengths);

        return l;
    }
}
