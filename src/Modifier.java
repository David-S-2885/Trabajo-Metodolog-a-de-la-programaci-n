import java.util.List;

public class Modifier {
    private String name;
    private int value;
    private static List<Weakness> weaknesses;
    private static List<Strength> strengths;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Modifier() {

    }

    public Modifier(String n, int i) {
        name = n;
        value = i;
    }

    public void showInfo() {
        System.out.println("Nombre: "+name);
        System.out.println("Valor: "+value);
    }

    public static List<Weakness> getWeaknesses() {
        return weaknesses;
    }

    public static List<Strength> getStrengths() {
        return strengths;
    }

    public static void setStrengths(List<Strength> strengths) {
        Modifier.strengths = strengths;
    }

    public static void setWeaknesses(List<Weakness> weaknesses) {
        Modifier.weaknesses = weaknesses;
    }

    public Weakness getWeakness(String name) {
        for (Weakness weakness : weaknesses) {
            if (weakness.getName().equals(name)) {
                return weakness;
            }
        }
        // Si no se encuentra la debilidad, devolvemos null
        return null;
    }

    public Strength getStrenght(String name) {
        for (Strength strength : strengths) {
            if (strength.getName().equals(name)) {
                return strength;
            }
        }
        // Si no se encuentra la fortaleza, devolvemos null
        return null;
    }
}