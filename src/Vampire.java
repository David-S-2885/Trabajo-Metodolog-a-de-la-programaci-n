import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vampire extends Character{
    private int bloodPoints;
    private int age;

    public Vampire() {
        super();
    }


    public void recoverBloodPoints(int i) {
        bloodPoints += i;
    }

    public int getAge() {
        return age;
    }

    public int getBloodPoints() {
        return bloodPoints;
    }

    public void setBloodPoints(int bloodPoints) {
        this.bloodPoints = bloodPoints;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Vampire creationMenu() {
        Scanner scanner = new Scanner(System.in);
        Vampire vampire = new Vampire();

        System.out.println();
        System.out.println("------INFORMACIÓN DEL PERSONAJE------");
        System.out.print("Introduzca el nombre del personaje: ");
        String name = scanner.nextLine();
        vampire.setName(name);

        int age = -1;
        do {
            System.out.print("Introduzca la edad del vampiro: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println("Introduzca una edad positiva");
                scanner.nextLine();
            }
        } while(age<0);
        vampire.setAge(age);
        System.out.println();


        System.out.println("------HABILIDAD ESPECIAL-------");
        List<Discipline> disciplines = SpecialAbility.getDisciplines();
        int i = 1;
        int answer = -1;
        for (Discipline d: disciplines) {
            System.out.println(i+". "+d.getName()+": "+d.getAttackValue()+" attack, "+d.getDefenseValue()+" defense, "+d.getBloodCost()+" blood cost");
            i++;
        }
        do {
            System.out.print("Elija la disciplina de su personaje: ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println("Introduzca una opción válida");
                scanner.nextLine();
            }
        } while (answer<1 || answer>disciplines.size());
        Discipline discipline = disciplines.get(answer-1);
        vampire.setSpecialAbility(discipline);
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
        vampire.setActiveWeapons(weapon);
        equipment.add(weapons.get(answer-1));
        System.out.println();

        i = 1;
        answer = -1;
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
        vampire.setActiveArmour(activeArmour);
        equipment.add(activeArmour);
        vampire.setActiveEquipment(equipment);

        System.out.println();
        System.out.println("------ESBIRRO------");
        List<Flunky> flunkies = new ArrayList<>();
        System.out.println("1. Ghoul");
        System.out.println("2. Demonio");
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
        } while (answer != 1 && answer != 2);

        Flunky flunky;

        if (answer == 1) {
             flunky = new Ghoul();
             int dependency = (int) (Math.random()*5+1);
            ((Ghoul)flunky).setDependency(dependency);
            flunky.setHealth(2);
        }
        else {
            flunky = new Demon();
            ((Demon) flunky).setPact("Pacto estándard");
            flunky.setHealth(3);
        }

        System.out.print("Introduzca el nombre de su esbirro: ");
        String nombre = scanner.nextLine();
        flunky.setName(nombre);
        flunkies.add(flunky);
        vampire.setFlunkies(flunkies);

        vampire.setPower(3);
        vampire.setHealth(5);
        vampire.setBloodPoints(10);

        List<Weakness> weaknesses = new ArrayList<>();
        Modifier m = new Modifier();
        weaknesses.add(m.getWeakness("Luz"));
        vampire.setWeaknesses(weaknesses);

        List<Strength> strengths = new ArrayList<>();
        strengths.add(m.getStrenght("Noche"));
        vampire.setStrenghts(strengths);


        return vampire;
    }


}
