import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GameManager {

    private AppSystem system;
    private Terminal terminal;


    public GameManager() {
        system = AppSystem.getInstance();
        terminal = new Terminal();
    }

    /**
     * Pregunta al usuario las credenciales
     * @return true si el usuario inicia sesión o false si dedice volver al menú de inicio.
     */

    boolean login() {

        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        String c = "a";
        boolean success;

        do {
            System.out.print("Introduzca el nombre de usuario: ");
            username = scanner.nextLine();

            System.out.print("Introduzca la constraseña: ");
            password = scanner.nextLine();

            success = system.login(username,password);

            if (!success) {
                if (system.getUser(username) == null || (system.getUser(username) != null && !system.getUser(username).getPassword().equals(password))) {
                    System.out.print("Credenciales incorrectos. Pulse 's' si quiere volver al menú de inicio o cualquier otra tecla para volver a introducir las credenciales: ");
                }
                else {
                    System.out.print("Tu cuenta está bloqueada. Pulse 's' si quiere volver al menú de inicio o cualquier otra tecla para volver a introducir las credenciales: ");
                }
                c = scanner.nextLine();
            }
            System.out.println();
        } while (!success && !c.equals("s"));

        return success;
    }

    /**
     * Comprueba que la constraseña tenga entre 8 y 12 caracteres
     * @param password
     * @return true si es válida, false si no es válida
     */

    boolean validPassword(String password) {
        return (password.length()>7) && (password.length()<13);
    }

    /**
     * Comprueba que el nickname sea válido. Pues este es único.
     */
    boolean validNickname(String nick) {
        return system.getUsers().getOrDefault(nick,null) == null;
    }

    void register() {
        Scanner scanner = new Scanner(System.in);
        int s = 0;

        do {
            System.out.println("1. Crear cuenta de jugador");
            System.out.println("2. Crear cuenta de operador");
            System.out.println("3. Volver al menú de inicio");
            System.out.print("Introduzca una opción: ");

            if (scanner.hasNextInt()) {
                s = scanner.nextInt();
                scanner.nextLine();
            }
            if (s != 1 && s != 2 && s != 3) {
                System.out.println("Introduzca una opción válida");
                //scanner.nextLine();
            }
        } while (s != 1 && s != 2 && s != 3);

        if (s == 1 || s == 2) {
            String username;
            String nick;
            String password;

            System.out.println();
            System.out.print("Introduzca un nombre de usuario: ");
            username = scanner.nextLine();

            boolean nickOk;
            do {
                System.out.print("Introduzca un nick: ");
                nick = scanner.nextLine();

                nickOk = validNickname(nick);

                if (!nickOk) {
                    System.out.println("Nick ya en uso. Introduzca uno diferente.\n");
                }
            } while (!nickOk);

            do {
                System.out.print("Introduzca una constraseña que contenga entre 8 y 12 caracteres: ");
                password = scanner.nextLine();
                if (!validPassword(password)) {
                    System.out.println("Contraseña no válida. Introduzca una nueva contraseña");
                }
            } while (!validPassword(password));

            if (s == 1) {
                Player player = new Player(username, nick, password, system.getUniqueRegisterCode());
                system.registerUser(player);
            } else {
                Operator operator = new Operator(username, nick, password);
                system.registerUser(operator);
            }
            System.out.println();
            System.out.println("Cuenta creada");
            System.out.println();
        }
    }

    /**
     * Carga los ficheros xml y maneja el inicio de sesión
     */

    public int  loginScreen(){
            boolean s = false;
            int i = 0;
            int k = 0;
            do {
                i = terminal.login();

                if (i == 1) {
                    s = login();
                    k = 0;
                }
                if (i == 2){
                    register();
                    k = 0;
                }
                if (i == 3) {
                    s = true;
                    k = 1;
                }
            } while (!s);
            return k;
    }

    private boolean playerLoop() {
        int i = terminal.principalMenu();
        System.out.println();
        switch (i) {
            case 1: {
                boolean deregistered = false;
                deregistered = system.deregisterUser();

                if(deregistered) {
                    system.exit();
                    i = 6;
                }
                break;
            }
            case 2: {
                system.manageCharacter();
                break;
            }
            case 3: {
                system.challenges();
                break;
            }
            case 4: {
                system.consultStatistics();
                break;
            }
            case 5: {
                system.exit();
                System.out.println();
                break;
            }
        }

        return i == 5;
    }

    private boolean adminLoop() {
        int i = terminal.adminMenu();
        System.out.println();
        switch (i) {
            case 1: {
                boolean deregistered = false;
                deregistered = system.deregisterUser();

                if (deregistered) {
                    system.exit();
                    i = 6;
                }
                break;
            }
            case 2: {
                system.characterEditing();
                break;
            }
            case 3: {
                system.validateChallenges();
                break;
            }
            case 4: {
                system.blockUser();
                break;
            }
            case 5: {
                system.unblockUser();
                break;
            }
            case 6: {
                system.exit();
                System.out.println();
                break;
            }
        }
        return i == 6;
    }

    public void start()  {

        boolean end = false;

        while (!end) {
            boolean exit = false;
            int answer = loginScreen();
            if (answer == 1) {
                end = true;
                continue;
            }

            system.notifications();
            User user = system.getActiveUser();

            while (!exit) {
                if (user instanceof Player) {
                    exit = playerLoop();
                } else {
                    exit = adminLoop();
                }
            }
        }
    }
}
