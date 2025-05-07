import java.util.List;

enum State {pending, accepted, denied, notValidated};

public class Challenge {
    private User defiant;
    private User defied;
    private int betGold;
    private State state;
    private List<Strength> activeStrenghtsDefiant;
    private List<Weakness> activeWeaknessesDefiant;
    private List<Strength> activeStrenghtsDefied;
    private List<Weakness> activeWeaknessesDefied;
    private boolean lostDefiedChallenge;
    private boolean validatedChallenge;

    public Challenge() {

    }

    public Challenge(User defiant, User defied, int gold)  {
        state = State.notValidated;
        validatedChallenge = false;
        this.defiant = defiant;
        this.defied = defied;
        betGold = gold;
        Player p1 = (Player) defiant;
        Player p2 = (Player) defied;
        activeStrenghtsDefiant = p1.getCharacter().getStrenghts();
        activeStrenghtsDefied = p2.getCharacter().getStrenghts();
        activeWeaknessesDefiant = p1.getCharacter().getWeaknesses();
        activeWeaknessesDefied = p2.getCharacter().getWeaknesses();

        //Faltan debilidades y fortalezas
    }

    public void challengeInfo() {
        System.out.println("Desafiante: "+defiant.getNick());
        System.out.println("Desafiado: "+defied.getNick());
        System.out.println("Apuesta: "+betGold);
        System.out.print("Fortalezas desafiante: ");
        if(activeStrenghtsDefiant.isEmpty()){
            System.out.print("Ninguna");
        }
        else {
            for (Strength s: activeStrenghtsDefiant) {
                System.out.print(s.getName());
            }
        }
        System.out.println();
        System.out.print("Debilidades desafiante: ");
        if(activeWeaknessesDefiant.isEmpty()){
            System.out.print("Ninguna");
        }
        else {
            for (Weakness s : activeWeaknessesDefiant) {
                System.out.print(s.getName());
            }
        }
        System.out.println();
        System.out.print("Fortalezas desafiado: ");
        if(activeStrenghtsDefied.isEmpty()) {
            System.out.print("Ninguna");
        }
        else {
            for (Strength s : activeStrenghtsDefied) {
                System.out.print(s.getName());
            }
        }
        System.out.println();
        System.out.print("Debilidades desafiado: ");
        if(activeWeaknessesDefied.isEmpty()) {
            System.out.print("Ninguna");
        }
        else {
            for (Weakness s: activeWeaknessesDefied) {
                System.out.print(s.getName());
            }
        }
    }

    public void startCombat() {
        Combat c = new Combat(this,(Player) defiant,(Player) defied);
        c.startCombat();
    }

    public void validate() {
        state = State.pending;
        validatedChallenge = true;
    }

    public void reject() {
        state = State.denied;
    }

    public void accept() {
        state = State.accepted;

        startCombat();
    }

    public void adjustChallenge() {
        //permite cambiar las debilidades y fortalezas del desafio
    }

    public User getDefied() {
        return defied;
    }

    public User getDefiant() {
        return defiant;
    }

    public int getBetGold() {
        return betGold;
    }

    public State getState() {
        return state;
    }

    public List<Strength> getActiveStrenghtsDefiant() {
        return activeStrenghtsDefiant;
    }

    public List<Weakness> getActiveWeaknessesDefiant() {
        return activeWeaknessesDefiant;
    }

    public List<Strength> getActiveStrenghtsDefied() {
        return activeStrenghtsDefied;
    }

    public List<Weakness> getActiveWeaknessesDefied() {
        return activeWeaknessesDefied;
    }

    public boolean isLostDefiedChallenge() {
        return lostDefiedChallenge;
    }

    public boolean isValidatedChallenge() {
        return validatedChallenge;
    }

    public void setDefiant(User defiant) {
        this.defiant = defiant;
    }

    public void setDefied(User defied) {
        this.defied = defied;
    }

    public void setBetGold(int betGold) {
        this.betGold = betGold;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setActiveStrenghtsDefiant(List<Strength> activeStrenghtsDefiant) {
        this.activeStrenghtsDefiant = activeStrenghtsDefiant;
    }

    public void setActiveWeaknessesDefiant(List<Weakness> activeWeaknessesDefiant) {
        this.activeWeaknessesDefiant = activeWeaknessesDefiant;
    }

    public void setActiveStrenghtsDefied(List<Strength> activeStrenghtsDefied) {
        this.activeStrenghtsDefied = activeStrenghtsDefied;
    }

    public void setActiveWeaknessesDefied(List<Weakness> activeWeaknessesDefied) {
        this.activeWeaknessesDefied = activeWeaknessesDefied;
    }

    public void setLostDefiedChallenge(boolean lostDefiedChallenge) {
        this.lostDefiedChallenge = lostDefiedChallenge;
    }

    public void setValidatedChallenge(boolean validatedChallenge) {
        this.validatedChallenge = validatedChallenge;
    }


}
