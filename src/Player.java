import java.util.ArrayList;
import java.util.List;

public class Player extends User {

    private String registerNumber;
    private Character character;
    private int gainedGold;
    private int lostGold;
    private int victories;
    private int defeats;
    private List<Challenge> pendingChallenges;

    public Player() {

    }

    public List<Challenge> getPendingChallenges() {
        return pendingChallenges;
    }

    public void addChallenge(Challenge c) {
        pendingChallenges.add(c);
    }

    public void setPendingChallenges(List<Challenge> pendingChallenges) {
        this.pendingChallenges = pendingChallenges;
    }

    public Player(String username, String nick, String password, String registerNumber) {
        super(username, nick, password);
        this.registerNumber = registerNumber;
        victories = 0;
        defeats = 0;
        pendingChallenges = new ArrayList<Challenge>();
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public int getGainedGold() {
        return gainedGold;
    }

    @Override
    public int getLostGold() {
        return lostGold;
    }

    public int getVictories() {
        return victories;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setGainedGold(int gainedGold) {
        this.gainedGold = gainedGold;
    }

    public void setLostGold(int lostGold) {
        this.lostGold = lostGold;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

}
