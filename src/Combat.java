import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combat {

    private Challenge challenge;
    private List<Round> rounds;
    private User winner;
    private LocalDateTime date;
    private Character c1;
    private Character c2;
    private int gainedGold;
    private int c1Flunky;
    private int c2Flunky;
    private String combatSummaryDefiant;
    private String combatSummaryDefied;

    public Combat() {

    }

    private boolean finishCombat() {
        return !(c1.getHealth()>0 && c2.getHealth()>0);
    }


    public Combat(Challenge c, Player p1, Player p2) {
        challenge = c;
        c1 = p1.getCharacter();
        c2 = p2.getCharacter();
        date = LocalDateTime.now();
        rounds = new ArrayList<Round>();
        gainedGold = c.getBetGold();
        c1Flunky = 0;
        c2Flunky = 0;

        if (!c1.getFlunkies().isEmpty()) {
            for(Flunky f: c1.getFlunkies()) {
                c1Flunky += f.getHealth();
            }
        }

        if (!c2.getFlunkies().isEmpty()) {
            for(Flunky f: c2.getFlunkies()) {
                c2Flunky += f.getHealth();
            }
        }

        c1.setHealth(5);
        c2.setHealth(5);

        if (c1 instanceof Vampire) {
            ((Vampire) c1).setBloodPoints(10);
        }
        else if (c1 instanceof Hunter) {
            ((Hunter) c1).setWill(3);
        }
        else if(c1 instanceof Lycanthrope) {
            ((Lycanthrope) c1).setRage(0);
        }

        if (c2 instanceof Vampire) {
            ((Vampire) c2).setBloodPoints(10);
        }
        else if (c2 instanceof Hunter) {
            ((Hunter) c2).setWill(3);
        }
        else if(c2 instanceof Lycanthrope) {
            ((Lycanthrope) c2).setRage(0);
        }

    }

    public String getCombatSummaryDefiant() {
        return combatSummaryDefiant;
    }

    public String getCombatSummaryDefied() {
        return combatSummaryDefied;
    }

    public void setCombatSummaryDefiant(String combatSummaryDefiant) {
        this.combatSummaryDefiant = combatSummaryDefiant;
    }

    public void setCombatSummaryDefied(String combatSummaryDefied) {
        this.combatSummaryDefied = combatSummaryDefied;
    }

    public void startCombat() {

        StringBuilder defiantText = new StringBuilder();
        StringBuilder defiedText = new StringBuilder();
        int i = 1;
        while (!finishCombat()) {
            Round r = new Round();
            rounds.add(r);
            r.executeRound(this);
            defiedText.append("Ronda ").append(i).append(" : ").append(r.getSummaryDefied()).append("\n");
            defiantText.append("Ronda ").append(i).append(" : ").append(r.getSummaryDefiant()).append("\n");
            i++;
        }

        setCombatSummaryDefiant(defiantText.toString());
        setCombatSummaryDefied(defiedText.toString());

        if (c1.getHealth()<= 0 && c2.getHealth()<=0) { //empate
            System.out.println("El combate ha resultado en un empate. No has ganado ni perdido oro. Resumen del combate: \n"+ combatSummaryDefied);
            Notification n = new Notification("El usuario "+challenge.getDefied().getNick()+ " ha aceptado tu desafío y ha finalizado en un empate. No has perdido ni ganado oro. Resumen del combate: \n"+combatSummaryDefiant);
            n.notify(challenge.getDefiant());
        }
        else if(c1.getHealth()<=0) {
            System.out.println("Has ganado el combate. Has ganado "+challenge.getBetGold()+ " de oro. Resumen del combate: \n"+combatSummaryDefied);
            Player defiant = (Player) challenge.getDefiant();
            Player defied = (Player) challenge.getDefied();
            c2.setGold(c2.getGold()+challenge.getBetGold());
            defied.setGainedGold(defied.getGainedGold()+challenge.getBetGold());
            if (c1.getGold()-challenge.getBetGold()>=500) {
                c1.setGold(c1.getGold()-challenge.getBetGold());
            }
            else {
                c1.setGold(500);
            }
            defiant.setLostGold(defiant.getLostGold()+challenge.getBetGold());
            Notification n = new Notification("El usuario "+challenge.getDefied().getNick()+ " ha aceptado tu desafío y ha finalizado en una derrota. Has perdido +"+challenge.getBetGold()+" de oro. Resumen del combate: \n"+ combatSummaryDefiant);
            n.notify(defiant);
            setWinner(challenge.getDefied());
            defied.setVictories(defied.getVictories()+1);
            defiant.setDefeats(defiant.getDefeats()+1);
        }
        else if(c2.getHealth()<= 0){
            System.out.println("Has perdido el combate. Has perdido "+challenge.getBetGold()+ " de oro. Resumen del combate: \n"+combatSummaryDefied);
            Player defiant = (Player) challenge.getDefiant();
            Player defied = (Player) challenge.getDefied();
            defiant.getCharacter().setGold(defiant.getCharacter().getGold()+challenge.getBetGold());
            defiant.setGainedGold(defiant.getGainedGold()+challenge.getBetGold());
            if (c2.getGold()-challenge.getBetGold()>=500) {
                c2.setGold(c2.getGold()-challenge.getBetGold());
            }
            else {
                c2.setGold(500);
            }
            defied.setLostGold(defied.getLostGold()+challenge.getBetGold());
            Notification n = new Notification("El usuario "+challenge.getDefied().getNick()+ " ha aceptado tu desafío y ha finalizado en una victoria. Has ganado +"+challenge.getBetGold()+" de oro. Resumen del combate: \n"+combatSummaryDefiant);
            n.notify(defiant);
            setWinner(challenge.getDefiant());
            defied.setDefeats(defied.getDefeats()+1);
            defiant.setVictories(defiant.getVictories()+1);
        }

    }

    public Challenge getChallenge() {
        return challenge;
    }

    public User getWinner() {
        return winner;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Character getC2() {
        return c2;
    }

    public Character getC1() {
        return c1;
    }

    public int getGainedGold() {
        return gainedGold;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setC1(Character c1) {
        this.c1 = c1;
    }

    public void setC2(Character c2) {
        this.c2 = c2;
    }

    public void setGainedGold(int gainedGold) {
        this.gainedGold = gainedGold;
    }

    public int getC2Flunky() {
        return c2Flunky;
    }

    public int getC1Flunky() {
        return c1Flunky;
    }

    public void setC1Flunky(int c1Flunky) {
        this.c1Flunky = c1Flunky;
    }

    public void setC2Flunky(int c2Flunky) {
        this.c2Flunky = c2Flunky;
    }
}
