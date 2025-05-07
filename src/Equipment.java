import java.util.List;

public class Equipment {
    private String name;
    private int attackModifier;
    private int defenseModifier;
    private static List<Armour> armours;
    private static  List<Weapon> weapons;

    public String getName() {
        return name;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public void setDefenseModifier(int defenseModifier) {
        this.defenseModifier = defenseModifier;
    }

    public static List<Armour> getArmours() {
        return armours;
    }

    public static List<Weapon> getWeapons() {
        return weapons;
    }

    public static void setArmours(List<Armour> armours) {
        Equipment.armours = armours;
    }

    public static void setWeapons(List<Weapon> weapons) {
        Equipment.weapons = weapons;
    }
}
