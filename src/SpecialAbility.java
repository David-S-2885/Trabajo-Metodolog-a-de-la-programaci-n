import java.util.List;

public class SpecialAbility {
    private String name;
    private int attackValue;
    private int defenseValue;
    private static List<Talent> talents;
    private static List<Gift> gifts;
    private static List<Discipline> disciplines;

    public SpecialAbility() {

    }

    public SpecialAbility(String abilityName) {
        name = abilityName;
    }

    public SpecialAbility(String name, int a, int d) {
        this.name = name;
        attackValue = a;
        defenseValue = d;
    }
    public String getName() {
        return name;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }

    public void showInfo() {
        System.out.println("Name: "+name);
        System.out.println("Attack value: "+attackValue);
        System.out.println("Defense value: "+defenseValue);
        System.out.println();
    }

    public static List<Talent> getTalents() {
        return talents;
    }

    public static List<Gift> getGifts() {
        return gifts;
    }

    public static List<Discipline> getDisciplines() {
        return disciplines;
    }

    public static void setTalents(List<Talent> talents) {
        SpecialAbility.talents = talents;
    }

    public static void setGifts(List<Gift> gifts) {
        SpecialAbility.gifts = gifts;
    }

    public static void setDisciplines(List<Discipline> disciplines) {
        SpecialAbility.disciplines = disciplines;
    }

    public static SpecialAbility validAbility(String name, Character c) {
        SpecialAbility specialAbility = new SpecialAbility();

        if (c instanceof Vampire) {
            for (Discipline d: disciplines) {
                if (d.getName().equals(name)) {
                    specialAbility = d;
                }
            }
        }
        else if (c instanceof Lycanthrope) {
            for (Gift g: gifts) {
                if (g.getName().equals(name)) {
                    specialAbility = g;
                }
            }
        }
        else if (c instanceof Hunter) {
            for (Talent t: talents) {
                if (t.getName().equals(name)) {
                    specialAbility = t;
                }
            }
        }

        return specialAbility;
    }


}
