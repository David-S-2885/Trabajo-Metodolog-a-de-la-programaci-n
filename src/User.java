import java.util.List;
import java.util.ArrayList;

public abstract class User {
    private String name;
    private String nick;
    private String password;
    private boolean blocked;
    private List<Notification> notifications;

    public User() {

    }

    public User(String name, String nick, String password) {
        this.name = name;
        this.nick = nick;
        this.password = password;
        this.notifications = new ArrayList<>();
        this.blocked = false;
    }

    public void deleteNotifications(){
        notifications = new ArrayList<Notification>();
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }


    public void getNotificationsText() {

        if (!notifications.isEmpty())
        {
            System.out.println("------NOTIFICACIONES-------\n");
            for (Notification notification: notifications) {
                System.out.println(notification.getMessage());
            }

            deleteNotifications(); // una vez mostradas las notificaciones, se borran
        }
        else {
            System.out.println("No tienes notificaciones pendientes");
        }

    }

    public int getLostGold() {
        if (this instanceof Player) {
            return ((Player) this).getGainedGold();
        }
        else {
            throw new RuntimeException("Error");
        }
    }

    public int getGainedGold() {
        if (this instanceof Player) {
            return ((Player) this).getGainedGold();
        }
        else {
            throw new RuntimeException("Error");
        }
    }


    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void userInfo() {
        System.out.println("Name: "+name);
        System.out.println("Nick: "+nick);
        System.out.println("Password: "+password);
        if(this instanceof Player) {
            System.out.println("RegisterNumber: "+((Player) this).getRegisterNumber());
        }
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }




}
