import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.beans.XMLDecoder;

public class AppSystem {
    private  Map<String,User> users;
    private Map<String,User> usersUsername;
    private List<Challenge> challenges;
    private Map<String,Combat> combats;
    private static AppSystem instance;
    private List<Observer> observers;
    private User activeUser;
    private Ranking ranking;

    /**
     * Carga los datos del sistema
     * @throws FileNotFoundException
     */

    private void waitAction() {
        Scanner s = new Scanner(System.in);
        System.out.println("Pulsa ENTER para ir al menú principal");
        s.nextLine();
    }

    Object load2(String filename, Object empty) {
        File file = new File(filename);

        if (!file.exists() || file.length() == 0) {
            try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
                encoder.writeObject(empty);
                encoder.flush();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + filename);
                return empty;
            }
        }

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)))) {
            Object object = decoder.readObject();
            if (object != null) {
                return object;
            }
            else {
                return empty;
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo " + filename + ": " + e.getMessage());
            return empty;
        }
    }

    public void load() throws FileNotFoundException {
        users = (Map<String, User>) load2("users.xml", new HashMap<String, User>());
        usersUsername = new HashMap<>();
        for (User user : users.values()) {
            usersUsername.put(user.getName(), user); // Mismas instancias
        }

        challenges = (List<Challenge>) load2("challenges.xml", new ArrayList());
        combats = (Map<String, Combat>) load2("combats.xml", new HashMap<String, Combat>());
        List<Talent> talents= (List<Talent>) load2("talentos.xml", new ArrayList<Talent>());
        List<Discipline> disciplines = (List<Discipline>) load2("disciplinas.xml", new ArrayList<Talent>());
        List<Gift> gifts = (List<Gift>) load2("dones.xml", new ArrayList<Gift>());
        List<Armour> armours = (List<Armour>) load2("armours.xml", new ArrayList<Armour>());
        List<Weapon> weapons = (List<Weapon>) load2("weapons.xml", new ArrayList<Weapon>());
        List<Weakness> weaknesses = (List<Weakness>) load2("weaknesses.xml", new ArrayList<Weakness>());
        List<Strength> strengths = (List<Strength>) load2("strenghts.xml", new ArrayList<Strength>());

        SpecialAbility.setTalents(talents);
        SpecialAbility.setDisciplines(disciplines);
        SpecialAbility.setGifts(gifts);
        Equipment.setArmours(armours);
        Equipment.setWeapons(weapons);
        Modifier.setStrengths(strengths);
        Modifier.setWeaknesses(weaknesses);
    }



    AppSystem() {
        try {
            load();
            ranking = new Ranking(new ArrayList<User>(users.values()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error al inicializar el sistema: " + e.getMessage());
        }
    }

    public static AppSystem getInstance() {
        if (instance == null) {
            instance = new AppSystem();
        }

        return instance;
    }

    public void registerUser(User user) {
        users.put(user.getNick(),user);
        usersUsername.put(user.getName(), user);
    }

    public boolean deregisterUser() {

        Scanner scanner = new Scanner(System.in);
        boolean validOption = false;
        String answer;

        do {
            System.out.print("Escribe CONFIRMAR para eliminar tu cuenta o CANCELAR para volver al menú principal: ");
            answer = scanner.nextLine();
            validOption = answer.equals("CONFIRMAR") || answer.equals("CANCELAR");

            if (!validOption) {
                System.out.println("Opción no válida\n");
            }
        } while (!validOption);

        if (answer.equals("CONFIRMAR")) {
            users.remove(activeUser.getNick());
            usersUsername.remove(activeUser.getName());
            System.out.println("Tu cuenta ha sido eliminada.");
            System.out.println();
            return true;
        }
        else{
            return false;
        }
    }

    public User getUser(String nick) {
        return users.getOrDefault(nick,null);
    }

    private User getUserLogin(String username) {
        return usersUsername.getOrDefault(username, null);
    }


    public boolean login(String username, String password) {
        User user = getUserLogin(username);
        if (user == null) {
            return false;
        }
        else if (user.isBlocked()) {
            return false;
        }
        else {
            if (user.getPassword() == null || !user.getPassword().equals(password)) {
                return false;
            }
            else {
                System.out.println("Bienvenido "+user.getNick());
                activeUser = user;
                return true;
            }
        }
    }

    public void consultStatistics() {
        Player p = (Player) activeUser;
        System.out.println("------ESTADÍSTICAS GENERALES------");
        System.out.println("Cantidad total de oro ganada: "+p.getGainedGold());
        System.out.println("Cantidad total de oro perdida: "+ p.getLostGold());
        System.out.println("Desafíos ganados: "+p.getVictories());
        System.out.println("Desafíos perdidos: "+p.getDefeats()+"\n");
        if (((Player) activeUser).getCharacter() != null) {
            Character c = ((Player) activeUser).getCharacter();
            System.out.println("------ESTADÍSTICAS PERSONAJE------");
            System.out.println("Nombre: "+c.getName());
            System.out.println("Habilidad especial: "+c.getSpecialAbility().getName());
            System.out.println("Oro: "+c.getGold());
            System.out.println("Poder: "+c.getPower());
            System.out.print("Armas equipadas: ");
            for (Weapon w: c.getActiveWeapons()) {
                System.out.print(w.getName());
            }
            System.out.println();
            System.out.println("Armadura equipada: "+c.getActiveArmour().getName());
            System.out.print("Debilidades: ");
            if (c.getWeaknesses().isEmpty()){
                System.out.println("ninguna");
            }
            else {
                for (Weakness w: c.getWeaknesses()) {
                    System.out.print(w.getName());
                }
                System.out.println();
            }
            System.out.print("Fortalezas: ");
            if (c.getStrenghts().isEmpty()){
                System.out.println("ninguna");
            }
            else {
                for (Strength s: c.getStrenghts()) {
                    System.out.print(s.getName());
                }
                System.out.println();
            }
            System.out.println("Número de esbirros: "+c.getFlunkies().size());
            System.out.println();

        }
        waitAction();
    }


    private  String registerCodeGenerator() {

        Random rand = new Random();

        char l1 = (char) ('A' + rand.nextInt(26));
        char l2 = (char) ('A' + rand.nextInt(26));
        char l3 = (char) ('A' + rand.nextInt(26));

        int n1 = rand.nextInt(10);
        int n2 = rand.nextInt(10);

        return  ""+l1 + n1 + n2 + l2 + l3;
    }

    private  boolean isCodeUnique(String code) {

        boolean unique = true;

        ArrayList<User> userList = new ArrayList<User>(users.values());

        int i = 0;

        while (unique && i < userList.size()) {
            if (userList.get(i) instanceof Player) {
                Player p = (Player) userList.get(i);
                if (p.getRegisterNumber().equals(code)) {
                    unique = false;
                }
                else {
                    i++;
                }
            }
            else {
                i++;
            }
        }

        return unique;
    }

    public String getUniqueRegisterCode() {

        String code;

        do {
            code = registerCodeGenerator();
        } while (!isCodeUnique(code));

        return code;
    }

    public void blockUser(User user) {
        Operator op = (Operator) activeUser;
        op.blockUser(user);
    }

    private void guardarObjetoEnXML(String nombreArchivo, Object objeto) throws IOException {
        File archivo = new File(nombreArchivo);

        if (archivo.exists() && !archivo.delete()) {
            throw new IOException("No se pudo eliminar el archivo: " + nombreArchivo);
        }

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(archivo));
             XMLEncoder encoder = new XMLEncoder(bos)) {
            encoder.writeObject(objeto);
            encoder.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error en la creación del nuevo archivo");
            throw e;
        }
    }

    public void exit() {
        try {
            guardarObjetoEnXML("users.xml", users);
            guardarObjetoEnXML("challenges.xml", challenges);
            guardarObjetoEnXML("combats.xml", combats);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos.");
        }
    }

    private List<Challenge> getPendingChallenges() {
        List<Challenge> pc = new ArrayList<Challenge>();
        for (Challenge challenge: challenges) {
            if (!challenge.isValidatedChallenge()) {
                pc.add(challenge);
            }
        }
        return  pc;
    }

    public void validateChallenges() {
        List<Challenge> pc = getPendingChallenges();

        if (pc.isEmpty()) {
            System.out.println("No hay desafíos pendientes");
        }
        else {
            boolean exit = false;
            Scanner s = new Scanner(System.in);
            while (!exit && !pc.isEmpty()) {
                Challenge c = pc.getFirst();
                Player defied = (Player) c.getDefied();
                Player defiant = (Player) c.getDefiant();

                int opt = 0;

                System.out.println("------DESAFÍO------");
                System.out.println();

                c.challengeInfo();
                System.out.println("\n\n1. Validar desafío");
                System.out.println("2. Rechazar desafío");
                System.out.println("3. Volver al menú principal");


                do {
                    System.out.print("Introduzca una opción: ");
                    if (s.hasNextInt()) {
                        opt = s.nextInt();
                        s.nextLine();
                        if (opt < 1 || opt > 3) {
                            System.out.println("Opción no válida");
                        }
                    } else {
                        System.out.println("Opción no válida");
                        s.nextLine();
                        opt = -1;
                    }
                } while (opt < 1 || opt > 3);

                switch (opt) {
                    case 1: {
                        c.validate();
                        Notification n = new Notification("El usuario "+defiant.getNick()+" te ha desafiado.");
                        n.notify(defied);
                        Notification n2 = new Notification("Tu desafío al jugador "+defied.getNick()+" ha sido validado.");
                        n2.notify(defiant);
                        defied.addChallenge(c);
                        System.out.println("Desafío validado");
                        pc.removeFirst();
                        if (pc.isEmpty()) {
                            exit = true;
                        }
                        break;
                    }
                    case 2: {
                        c.reject();
                        System.out.println("Desafío rechazado");
                        System.out.println();
                        Notification n2 = new Notification("Tu desafío al jugador "+defied.getNick()+" ha sido rechazado por un operador.");
                        n2.notify(defiant);
                        pc.removeFirst();
                        if (pc.isEmpty()) {
                            exit = true;
                        }
                        break;
                    }
                    case 3: {
                        exit = true;
                        break;
                    }
                    default: {
                        System.out.println("Opción no válida");
                        break;
                    }
                }

                if (pc.isEmpty()) {
                    System.out.println("No quedan desafíos pendientes. ");
                    System.out.println();
                }
            }
        }
    }

    private void characterDeleting() {
        if (((Player) activeUser).getCharacter() == null) {
            System.out.println("No tienes un personaje.");
        }
        else {
            Scanner scanner = new Scanner(System.in);
            String s;
            System.out.print("¿Seguro que quieres eliminar tu personaje? Introduce CONFIRMAR para eliminarlo o pulse cualquier otra tecla para volver cancelar: ");
            s = scanner.nextLine();

            if (s.equals("CONFIRMAR")) {
                ((Player) activeUser).setCharacter(null);
                System.out.println("Tu personaje ha sido eliminado");
            }
        }
        System.out.println();
    }

    private void characterCreation() {
        if (((Player) activeUser).getCharacter() != null) {
            System.out.println("Ya tienes un personaje");
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int i = -1;

            System.out.println("---------TIPO DE PERSONAJE--------");
            System.out.println("1. Vampiro");
            System.out.println("2. Cazador");
            System.out.println("3. Licántropo");

            do {
                System.out.print("Introduzca el tipo de personaje que quieres crear: ");
                if (scanner.hasNextInt()) {
                    i = scanner.nextInt();
                    scanner.nextLine();
                }
                else {
                    System.out.println("Introduzca una opción válida");
                    scanner.nextLine();
                }
            } while (i<1 || i>3);

            switch (i) {
                case 1: {
                    Vampire vampire= new Vampire();
                    vampire = vampire.creationMenu();
                    ((Player) activeUser).setCharacter(vampire);
                    break;
                }
                case 2: {
                    Hunter hunter = new Hunter();
                    hunter = hunter.creationMenu();
                    ((Player) activeUser).setCharacter(hunter);
                    break;
                }
                case 3: {
                    Lycanthrope lycanthrope = new Lycanthrope();
                    lycanthrope = lycanthrope.creationMenu();
                    ((Player) activeUser).setCharacter(lycanthrope);
                    break;
                }
            }
            System.out.println("Tu personaje ha sido creado.");
        }
        System.out.println();
    }

    private void switchEquipment() {

        if (((Player) activeUser).getCharacter() != null) {
            Scanner scanner = new Scanner(System.in);
            Character c = ((Player) activeUser).getCharacter();
            System.out.println("------OPCIONES------");
            System.out.println("1. Elegir armadura");
            System.out.println("2. Elegir arma");
            System.out.println("3. Volver atrás");
            int opt = -1;

            do {
                System.out.print("Introduzca una opción: ");
                if (scanner.hasNextInt()) {
                    opt = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Introduzca una opción válida");
                    scanner.nextLine();
                }
            } while (opt < 1 || opt > 3);

            System.out.println();

            switch (opt) {
                case 1: {
                    List<Armour> armours = Equipment.getArmours();
                    int i = 1;
                    int answer = -1;
                    for (Armour armour : armours) {
                        System.out.println(i + ". " + armour.getName() + ": " + armour.getAttackModifier() + " attack, " + armour.getDefenseModifier() + " defense");
                        i++;
                    }
                    do {
                        System.out.print("Elija una armadura para su personaje: ");
                        if (scanner.hasNextInt()) {
                            answer = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Introduzca una opción válida");
                            scanner.nextLine();
                        }
                    } while (answer < 1 || answer > armours.size());

                    c.getActiveEquipment().removeIf(e -> e instanceof Armour);

                    c.getActiveEquipment().add(armours.get(answer - 1));
                    c.setActiveArmour(armours.get(answer - 1));

                    break;
                }
                case 2: {
                    List<Weapon> weapons = Equipment.getWeapons();
                    c.getActiveEquipment().removeIf(e -> e instanceof Weapon);
                    c.setActiveWeapons(new ArrayList<>());

                    int i = 1;
                    int answer = -1;
                    for (Weapon weapon : weapons) {
                        System.out.println(i + ". " + weapon.getName() + ": " + weapon.getAttackModifier() + " attack, " + weapon.getDefenseModifier() + " defense");
                        i++;
                    }
                    do {
                        System.out.print("Elija un arma para su personaje: ");
                        if (scanner.hasNextInt()) {
                            answer = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Introduzca una opción válida");
                            scanner.nextLine();
                        }
                    } while (answer < 1 || answer > weapons.size());

                    Weapon weapon1 = weapons.get(answer - 1);
                    if (!weapon1.isBothHands()) {
                        String input;
                        int answer2 = -1;
                        do {
                            System.out.print("Elija otro arma de una mano para su personaje o 's' si no quiere: ");
                            input = scanner.nextLine();

                            if (input.equalsIgnoreCase("s")) {
                                break;
                            }

                            try {
                                answer2 = Integer.parseInt(input);
                                if (answer2 < 1 || answer2 > weapons.size() || weapons.get(answer2 - 1).isBothHands()) {
                                    System.out.println("Introduzca una opción válida");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Introduzca una opción válida");
                            }

                        } while (answer2 < 1 || answer2 > weapons.size() || weapons.get(answer2 - 1).isBothHands());

                        if (!input.equals("s")) {
                            Weapon weapon2 = weapons.get(answer2 - 1);
                            c.getActiveEquipment().add(weapon2);
                            c.getActiveWeapons().add(weapon2);
                        }
                    }

                    c.getActiveWeapons().add(weapon1);
                    c.getActiveEquipment().add(weapon1);
                    break;
                }
                case 3: {
                    break;
                }
            }
        }
        else {
            System.out.println("No tienes un personaje.");
        }
        System.out.println();
    }

    public void manageCharacter() {
        Scanner scanner = new Scanner(System.in);
        int opt = -1;
        boolean exit = false;

        while (!exit) {
            System.out.println("------OPCIONES------");
            System.out.println("1. Eliminar personaje");
            System.out.println("2. Crear personaje");
            System.out.println("3. Elegir equipamiento activo");
            System.out.println("4. Volver al menú principal");

            do {
                System.out.print("Introduzca una opción: ");
                if (scanner.hasNextInt()) {
                    opt = scanner.nextInt();
                    scanner.nextLine();
                }
                else {
                    System.out.println("Introduzca una opción válida.");
                    scanner.nextLine();
                }
            } while (opt<1 || opt > 4);

            System.out.println();

            switch (opt) {
                case 1: {
                    characterDeleting();
                    break;
                }
                case 2: {
                    characterCreation();
                    break;
                }
                case 3: {
                    switchEquipment();
                    break;
                }
                case 4: {
                    exit = true;
                    break;
                }
            }
        }
        waitAction();
    }

    private boolean validGoldBet(int i, String defied) {
        Player p = (Player) activeUser;
        Player d = (Player) getUser(defied);
        return i>0 && p.getCharacter().getGold()>=i && d.getCharacter().getGold()>=i;
    }

    private void defy() {

        if (((Player) activeUser).getCharacter() != null) {
            Scanner scanner = new Scanner(System.in);
            String defied;
            do {
                System.out.print("Introduzca el nick del usuario al que desea desafíar, o pulse 's' para volver: ");
                defied = scanner.nextLine();
                if (defied.equals("s")) {
                    break;
                } else if (getUser(defied) == null || getUser(defied) instanceof Operator) {
                    System.out.println("Usuario no válido");
                } else if (((Player) getUser(defied)).getCharacter() == null) {
                    System.out.println("El usuario al que intentas desafiar no tiene un personaje.");
                } else if (getUser(defied) == activeUser) {
                    System.out.println("No te puedes desafíar a ti mismo. ");
                }
            } while (getUser(defied) == null || (getUser(defied) instanceof Operator) || ((Player) getUser(defied)).getCharacter() == null || getUser(defied) == activeUser);
            if (!defied.equals("s")) {
                int gold = 0;
                do {
                    System.out.print("Introduzca la cantidad de oro que desea apostar: ");
                    if (scanner.hasNextInt()) {
                        gold = scanner.nextInt();
                        if (gold > 0 && ((Player) getUser(defied)).getCharacter().getGold() < gold) {
                            System.out.println("El usuario " + ((Player) getUser(defied)).getNick() + " dispone de " + ((Player) getUser(defied)).getCharacter().getGold() + " de oro");
                        } else if (gold > 0 && ((Player) activeUser).getCharacter().getGold() < gold) {
                            System.out.println("Dispones de " + ((Player) activeUser).getCharacter().getGold() + " de oro");
                        }
                        scanner.nextLine();
                    } else {
                        System.out.println("Introduzca una opción válida");
                        scanner.nextLine();
                    }
                } while (!validGoldBet(gold, defied));

                Challenge challenge = new Challenge(activeUser, getUser(defied), gold);
                challenges.add(challenge);
                System.out.println("El desafío ha sido enviado para ser validado.");
            }
            System.out.println();
        } else {
            System.out.println("No tienes un personaje");
        }
    }

    private void managePendingChallenges() {
        List<Challenge> pc = ((Player) activeUser).getPendingChallenges();

        if (pc.isEmpty()) {
            System.out.println("No tienes desafíos pendientes");
        }
        else {
            boolean exit = false;
            int i = 0;
            int opt = 0;
            Scanner scanner = new Scanner(System.in);
            Challenge c;
            while(!exit && !pc.isEmpty()) {
                System.out.println("------DESAFÍO------");
                c = pc.get(i);
                c.challengeInfo();
                System.out.println("\n1. Aceptar");
                System.out.println("2. Rechazar");
                System.out.println("3. Volver al menú principal");
                do {
                    System.out.print("Introduzca una opción: ");
                    if (scanner.hasNextInt()) {
                        opt = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Introduzca una opción válida");
                        scanner.nextLine();
                    }
                } while (opt != 1 && opt != 2 && opt != 3);

                System.out.println();

                switch (opt) {
                    case 1: {
                        c.accept();
                        pc.remove(i);
                        break;
                    }
                    case 2: {
                        c.reject();
                        Player defiant = (Player) c.getDefiant();
                        double pay = c.getBetGold()*0.1;
                        defiant.getCharacter().setGold((int) (defiant.getCharacter().getGold() + pay));
                        defiant.setGainedGold((int) (defiant.getGainedGold()+pay));
                        if (((Player) activeUser).getCharacter().getGold() - pay < 500) {
                            ((Player) activeUser).getCharacter().setGold(500);
                        }
                        else {
                            ((Player) activeUser).getCharacter().setGold( (int) (((Player) activeUser).getCharacter().getGold() - pay ));
                        }
                        ((Player) activeUser).setLostGold( (int) (((Player) activeUser).getLostGold() + pay ));
                        pc.get(i).reject();
                        pc.remove(i);
                        System.out.println("Has rechazado el desafío. Se te ha cobrado un 10% de la apuesta.");
                        Notification n = new Notification("El usuario "+activeUser.getNick()+" ha rechazado tu desafío. Has ganado "+ (int) (c.getBetGold()*0.1)+ " de oro.");
                        n.notify(c.getDefiant());
                        break;
                    }
                    case 3: {
                        exit = true;
                        break;
                    }
                }
            }
        }
    }

    public void challenges() {
        Scanner scanner = new Scanner(System.in);
        int s = 0;
        System.out.println("------OPCIONES------");
        System.out.println("1. Desafiar a otro usuario");
        System.out.println("2. Consultar desafíos pendientes");

        do {
            System.out.print("Introduzca una opción: ");
            if (scanner.hasNextInt()) {
                s = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Introduzca una opción válida");
                scanner.nextLine();
            }
        } while (s != 1 && s != 2);

        System.out.println();

        switch (s) {
            case 1: {
                defy();
                break;
            }
            case 2: {
                managePendingChallenges();
                break;
            }
        }

        waitAction();
    }

    public void characterEditing() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el nick del jugador cuyo personaje quieres editar: ");
        String nick = scanner.nextLine();

        User user = users.get(nick);
        if (user == null || !(user instanceof Player)) {
            System.out.println("No se ha encontrado un jugador con ese nick.");
        }
        else {
            Player player = (Player) user;
            Character character = player.getCharacter();
            if (character == null) {
                System.out.println("El jugador no tiene un personaje asignado.");
            } else {
                boolean exit = false;
                while (!exit) {
                    System.out.println("\n--- EDITAR PERSONAJE de " + player.getNick() + " ---");
                    System.out.println("1. Nombre (" + character.getName() + ")");
                    System.out.println("2. Habilidad especial (" +
                            (character.getSpecialAbility() != null
                                    ? character.getSpecialAbility().getName()
                                    : "Ninguna") + ")");
                    System.out.println("3. Oro (" + character.getGold() + ")");
                    System.out.println("4. Salud (" + character.getHealth() + ")");
                    System.out.println("5. Poder (" + character.getPower() + ")");
                    System.out.println("6. Salir");

                    int option = -1;
                    do {
                        System.out.print("Elige una opción: ");
                        if (scanner.hasNextInt()) {
                            option = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Introduzca una opción válida");
                            scanner.nextLine();
                        }
                    } while (option < 1 || option > 6);

                    switch (option) {
                        case 1: {
                            System.out.print("Nuevo nombre: ");
                            character.setName(scanner.nextLine());
                            break;
                        }

                        case 2: {
                            String abilityName;
                            do {
                                System.out.print("Nueva habilidad especial (nombre): ");
                                abilityName = scanner.nextLine();

                                if (SpecialAbility.validAbility(abilityName, player.getCharacter()) == null) {
                                    System.out.println("Habilidad no válida.");
                                }
                            } while (SpecialAbility.validAbility(abilityName, player.getCharacter()) == null);

                            character.setSpecialAbility(SpecialAbility.validAbility(abilityName, character));
                            System.out.println("Se ha cambiado la habilidad especial del personaje.");
                            break;
                        }

                        case 3: {

                            int answer = -1;
                            do {
                                System.out.print("Nuevo oro: ");
                                if (scanner.hasNextInt()) {
                                    answer = scanner.nextInt();
                                    scanner.nextLine();
                                } else {
                                    System.out.println("Introduzca una opción válida");
                                    scanner.nextLine();
                                }
                            } while (answer < 0);

                            character.setGold(answer);
                            break;
                        }

                        case 4: {
                            int answer = -1;
                            do {
                                System.out.print("Nueva salud: ");
                                if (scanner.hasNextInt()) {
                                    answer = scanner.nextInt();
                                    scanner.nextLine();
                                } else {
                                    System.out.println("Introduzca un valor entre 0 y 5");
                                    scanner.nextLine();
                                }
                            } while (answer < 0 || answer > 5);
                            character.setHealth(answer);
                            break;
                        }

                        case 5: {
                            int answer = -1;
                            do {
                                System.out.print("Nuevo poder: ");
                                if (scanner.hasNextInt()) {
                                    answer = scanner.nextInt();
                                    scanner.nextLine();
                                } else {
                                    System.out.println("Introduzca un valor entre 0 y 10");
                                    scanner.nextLine();
                                }
                            } while (answer < 1 || answer > 5);
                            character.setPower(answer);
                            break;
                        }
                        case 6: {
                            System.out.println("Saliendo de edición de personaje.");
                            exit = true;
                            break;
                        }

                        default: {
                            System.out.println("Opción inválida.");
                        }
                    }
                }
            }
        }
        waitAction();
    }


    public void blockUser() {

        Scanner scanner = new Scanner(System.in);
        String nick;
        System.out.print("Introduzca el nick del usuario que quiere bloquear: ");
        nick = scanner.nextLine();

        User user = getUser(nick);

        if (user != null) {
            if (user.isBlocked()) {
                System.out.println("Este usuario ya está bloqueado");
            }
            else {
                user.setBlocked(true);
                System.out.println("El usuario "+user.getNick()+ " ha sido bloqueado");
            }
        }
        else {
            System.out.println("No existe ningún usuario con el nick proporcionado");
        }

        waitAction();

    }

    public void unblockUser() {
        Scanner scanner = new Scanner(System.in);
        String nick;
        System.out.print("Introduzca el nick del usuario que quiere desbloquear: ");
        nick = scanner.nextLine();

        User user = users.getOrDefault(nick,null);

        if (user != null) {
            if (!user.isBlocked()) {
                System.out.println("Este usuario no está bloqueado");
            }
            else {
                user.setBlocked(false);
                System.out.println("El usuario "+user.getNick()+ " ha sido desbloqueado");
            }
        }
        else {
            System.out.println("No existe ningún usuario con el nick proporcionado");
        }

        waitAction();
    }

    public void notifications() {
        activeUser.getNotificationsText();
        waitAction();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public List<User> getUsersList() {
        return new ArrayList<>(users.values());
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public Map<String, Combat> getCombats() {
        return combats;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public Ranking getRanking() {
        return ranking;
    }
}