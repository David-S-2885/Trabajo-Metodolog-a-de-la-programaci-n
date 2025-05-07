public abstract class Flunky {
    private String name;
    private int health;

    public Flunky() {

    }

    public Flunky(String n, int h) {
        name = n;
        health = h;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isAlive() {
        return health == 0;
    }

    public void setHealth(int h) {
        health = h;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
