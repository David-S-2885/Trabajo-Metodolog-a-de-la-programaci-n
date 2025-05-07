public class Gift extends SpecialAbility{
    private int minmumRage;

    public Gift() {

    }

    public Gift(int minmumRage, String name, int a, int d) {
        super(name,a,d);
        this.minmumRage = minmumRage;
    }

    public int getMinmumRage() {
        return minmumRage;
    }

    public void setMinmumRage(int minmumRage) {
        this.minmumRage = minmumRage;
    }
}
