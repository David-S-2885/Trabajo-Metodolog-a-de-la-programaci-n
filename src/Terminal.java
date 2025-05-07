import java.util.Scanner;

public class Terminal {


    // por comodidad los mensajes de los menús se crean en el constructor y se guardan para que los metodos de mostrar el menú sean más "limpios". Podría perfectamente escribirse directamente dentro del método
    private String pmenu;
    private String amenu;
    private String lmenu;



    public Terminal() {
        StringBuilder s = new StringBuilder();
        s.append("----------MENÚ--------\n\n").append("1. Darse de baja\n").append("2. Configuración personaje\n").append("3. Desafíos\n").append("4. Consultar estadísticas\n").append("5. Salir\n").append("Introduzca una opción: ");
        pmenu = s.toString();

        StringBuilder s1 = new StringBuilder();
        s1.append("----------MENÚ ADMIN--------\n\n").append("1. Darse de baja\n").append("2. Editar personaje\n").append("3. Validar desafíos\n").append("4. Bloquear jugador\n").append("5. Desbloquear jugador\n").append("6. Salir\n").append("Introduzca una opción: ");
        amenu = s1.toString();

        StringBuilder s2 = new StringBuilder();
        s2.append("---------BIENVENIDO-------\n\n").append("1. Iniciar sesión\n").append("2. Registrarse\n").append("3. Cerrar programa\n").append("Introduzca una opción: ");
        lmenu = s2.toString();
    }

    public int principalMenu(){

        int i = 0;
        Scanner s = new Scanner(System.in);
        String string;

        while (i < 1 || i > 5) {
            System.out.print(pmenu);

            if (s.hasNextInt()) {
                i = s.nextInt();
            } else {
                System.out.println("Por favor, introduzca un número válido.");
                s.next();
            }
        }

        return i;

    }

    public int adminMenu(){
        int i = 0;
        Scanner s = new Scanner(System.in);

        while (i < 1 || i > 6) { /// //////////////////
            System.out.print(amenu);

            if (s.hasNextInt()) {
                i = s.nextInt();
            } else {
                System.out.println("Por favor, introduzca un número válido.");
                s.next();
            }
        }

        return i;
    }

    public int login(){
        int i = 0;
        Scanner s = new Scanner(System.in);

        while (i < 1 || i > 3) {
            System.out.print(lmenu);

            if (s.hasNextInt()) {
                i = s.nextInt();
            }
            if (i < 1 || i > 3) {
                System.out.println("Por favor, introduzca un número válido.");
            }
        }
        System.out.println();
        return i;
    }
}
