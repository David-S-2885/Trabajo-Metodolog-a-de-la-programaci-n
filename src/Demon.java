import java.util.ArrayList;
import java.util.List;

public class Demon extends Flunky{
    private String pact;
    private List<Flunky> flunkies;

    public Demon() {

    }

    public Demon(String p) {
        pact = p;
        flunkies = new ArrayList<>();
    }

    public Demon(String n, int h, String pact) {
        super(n, h);
        this.pact = pact;
    }

    public List<Flunky> getFunkies() {
        return flunkies;
    }

    public String getPact() {
        return pact;
    }

    public void setPact(String pact) {
        this.pact = pact;
    }

    public void addFunky(Flunky flunky) {
        flunkies.add(flunky);
    }

    public void removeFlunky(String name) {
        boolean found = false;
        int i = 0;
        while(!found && i<flunkies.size()) {
            if (flunkies.get(i).getName().equals(name)) {
                flunkies.remove(i);
                found = true;
            }
            else {
                i++;
            }
        }

        if (found) {
            System.out.println("El esbirro "+name+" ha sido eliminado");
        }
        else {
            System.out.println("No tienes un esbirro con nombre "+name);
        }
    }

    public List<Flunky> getFlunkies() {
        return flunkies;
    }

    public void setFlunkies(List<Flunky> flunkies) {
        this.flunkies = flunkies;
    }
}
