import java.util.List;


//No se especifica de que tiene que ser el ranking. Lo suyo sería que ranking fuese una clase abstracta y que haya diferentes clases de ranking (oro, desafios ganados, desafios perdidos...) que implementen los dos metodos
public class Ranking {
    private List<User> ranking;


    public Ranking(List<User> users) {
        ranking = users;
    }

    private void updateRanking(List<User> userList) {

    }

    public void showRanking(){
        updateRanking(ranking);

        System.out.println("TOP 10 JUGADORES (ORO GANADO)");
        for(int i = 0; i<10;i++) {
            System.out.println(i+". "+ranking.get(i).getName()+": "+ranking.get(i).getGainedGold());
        }
        System.out.println("\n");
    }
}
