public class Notification {
    private String message;

    public Notification() {

    }

    public Notification(String s) {
        message = s;
    }

    public void notify(User user) {
        user.addNotification(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
