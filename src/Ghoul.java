public class Ghoul extends Flunky{
    private int dependency;

    public int getDependency() {
        return dependency;
    }

    public void setDependency(int dependency) {
        this.dependency = dependency;
    }

    public Ghoul() {

    }

    public Ghoul(int dependency) {
        this.dependency = dependency;
    }

    public Ghoul(String n, int h, int dependency) {
        super(n, h);
        this.dependency = dependency;
    }
}
