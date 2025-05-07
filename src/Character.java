import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Character {
    private String name;
    private SpecialAbility specialAbility;
    private List<Weapon> weapons;
    private List<Weapon> activeWeapons;
    private Armour activeArmour;
    private List<Armour> armours;
    private List<Flunky> flunkies;
    private int gold;
    private int health;
    private List<Weakness> weaknesses;
    private List<Strength> strenghts;
    private int power;
    private List<Equipment> activeEquipment;

    public Character() {
        gold = 500;
        weapons = Equipment.getWeapons();
        armours = Equipment.getArmours();
    }

    public Character(String char1, String type1, int i, int i1) {
    }

    public String getName() {
        return name;
    }

    public SpecialAbility getSpecialAbility() {
        return specialAbility;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Weapon> getActiveWeapons() {
        return activeWeapons;
    }

    public List<Flunky> getFlunkies() {
        return flunkies;
    }

    public int getGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }

    public List<Weakness> getWeaknesses() {
        return weaknesses;
    }

    public List<Strength> getStrenghts() {
        return strenghts;
    }

    public int getPower() {
        return power;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setSpecialAbility(SpecialAbility specialAbility) {
        this.specialAbility = specialAbility;
    }

    public void setActiveWeapons(List<Weapon> activeWeapons) {
        this.activeWeapons = activeWeapons;
    }


    public void setFlunkies(List<Flunky> flunkies) {
        this.flunkies = flunkies;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWeaknesses(List<Weakness> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public void setStrenghts(List<Strength> strenghts) {
        this.strenghts = strenghts;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public List<Armour> getArmours() {
        return armours;
    }

    public List<Equipment> getActiveEquipment() {
        return activeEquipment;
    }

    public void setArmours(List<Armour> armours) {
        this.armours = armours;
    }

    public void setActiveEquipment(List<Equipment> activeEquipment) {
        this.activeEquipment = activeEquipment;
    }

    public Armour getActiveArmour() {
        return activeArmour;
    }

    public void setActiveArmour(Armour activeArmour) {
        this.activeArmour = activeArmour;
    }


}
