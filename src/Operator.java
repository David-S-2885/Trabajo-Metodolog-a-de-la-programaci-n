public class Operator extends User{


    public Operator() {

    }

    public Operator(String name, String nick, String password) {
        super(name, nick, password);
    }

    public void blockUser(User player) {
        player.setBlocked(true);
        System.out.println("El usuario "+player.getNick()+" ha sido bloqueado");

        //Ahora hay que enviar una notificación al usuario

        String s = "Has sido bloqueado. No podrás desafíar a otros jugadores o aceptar desafíos.";
        Notification notification = new Notification(s);
        notification.notify(player);

    }

    public void unblockUser(Player player) {
        player.setBlocked(false);
    }

}
