public class Discipline extends SpecialAbility{
    private int bloodCost;


    public Discipline(){

    }

    public Discipline(int bloodCost, String name, int a, int d) {
        super(name,a,d);
        this.bloodCost = bloodCost;

    }

    public int getBloodCost() {
        return bloodCost;
    }


    public void setBloodCost(int bloodCost) {
        this.bloodCost = bloodCost;
    }
}
