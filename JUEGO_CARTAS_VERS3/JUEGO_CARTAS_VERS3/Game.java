package JUEGO_CARTAS_VERS3;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Deck deck;
    private ArrayList<Player> players;
    private Card cartaMesa;
    private int turnoActual;
    private int direccion;
    private Scanner scanner;

    public Game() {

        deck = new Deck();
        players = new ArrayList<>();
        scanner = new Scanner(System.in);

        turnoActual = 0;
        direccion = 1;

        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        players.add(new Player(nombre, true));

        players.add(new Player("Pepe", false));
        players.add(new Player("Toña", false));
        players.add(new Player("Mari", false));
    }

    public void iniciar() {

        repartirCartas();

        do {
            cartaMesa = deck.robarCarta();
        } while (cartaMesa.getTipo() != Card.Tipo.NUMERO);

        System.out.println("Carta inicial: " + cartaMesa);

        while (true) {

            Player jugadorEnTurno = players.get(turnoActual);

            turno();

            if (jugadorEnTurno.getMano().estaVacia()) {
                System.out.println("\n  🏆 ¡" + jugadorEnTurno.getNombre() + " ha ganado! 🏆");
                System.out.println("      _______________");
                System.out.println("     |@@@@|     |####|");
                System.out.println("     |@@@@|     |####|");
                System.out.println("     |@@@@|     |####|");
                System.out.println("     \\@@@@|     |####/");
                System.out.println("      \\@@@|     |###/");
                System.out.println("       `@@|_____|##'");
                System.out.println("            (O)");
                System.out.println("         .-''''''-.");
                System.out.println("       .'          `.");
                System.out.println("      :   ★  #1  ★   :");
                System.out.println("      :    WINNER    :");
                System.out.println("       `._        _.'");
                System.out.println("          `-.....-'");
                break;
            }
        }
    }
    
    private void repartirCartas() {

        for (int i = 0; i < 7; i++) {
            for (Player p : players) {
                p.robarCarta(deck);
            }
        }
    }

    private void turno() {

        Player actual = players.get(turnoActual);

        System.out.println("\n====================================");
        System.out.println("Turno de: " + actual.getNombre());
        System.out.println("Carta en mesa: " + cartaMesa);
        System.out.println();

        System.out.println("Tus cartas:");
        for (Player p : players) {
            if (p.esHumano()) {
                System.out.println("- " + p.getNombre() + " (" + p.getMano().size() + " cartas)");
            }
        }

        System.out.println();

        actual.jugarTurno(this);

        validarUNO(actual);

        System.out.println("====================================");

        siguienteTurno();
    }

    public void turnoHumano(Player player) {

    	player.mostrarMano(cartaMesa);

        if (!player.tieneJugadaValida(cartaMesa)) {

            System.out.println("No tienes jugada válida. Robas carta...");

            Card robada = deck.robarCarta();
            player.getMano().agregarCarta(robada);

            System.out.println("Carta robada: " + robada);

            if (robada.esJugableSobre(cartaMesa)) {
                System.out.println("✔ La carta es USABLE, se juega automáticamente");
                cartaMesa = robada;
                aplicarEfecto(robada, player);
            } else {
                System.out.println("❌ La carta NO es usable, pierdes turno");
            }

            return;
        }

        int opcion;

        while (true) {

            System.out.print(">> Selecciona una carta válida: ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Debes ingresar un número.");
                scanner.next();
                continue;
            }

            opcion = scanner.nextInt();

            if (opcion < 0 || opcion >= player.getMano().size()) {
                System.out.println("❌ Índice fuera de rango.");
                continue;
            }

            Card carta = player.getMano().getCarta(opcion);

            if (!carta.esJugableSobre(cartaMesa)) {
                System.out.println("❌ Carta no válida.");
                continue;
            }

            carta = player.jugarCarta(opcion);

            System.out.println(player.getNombre() + " juega: " + carta);

            cartaMesa = carta;
            aplicarEfecto(carta, player);

            break;
        }
    }

    public void turnoIA(Player player) {

        for (int i = 0; i < player.getMano().size(); i++) {

            Card carta = player.getMano().getCarta(i);

            if (carta.esJugableSobre(cartaMesa)) {

                player.jugarCarta(i);

                System.out.println(player.getNombre() + " juega: " + carta);

                cartaMesa = carta;

                aplicarEfecto(carta, player);

                return;
            }
        }

        System.out.println(player.getNombre() + " roba carta...");

        Card robada = deck.robarCarta();
        player.getMano().agregarCarta(robada);

        System.out.println(player.getNombre() + " roba: " + robada);

        if (robada.esJugableSobre(cartaMesa)) {

            System.out.println("✔ " + player.getNombre() + " usa la carta robada");

            cartaMesa = robada;
            aplicarEfecto(robada, player);

        } else {
            System.out.println("❌ No puede usarla");
        }
    }

    private void aplicarEfecto(Card carta, Player jugadorActual) {

        switch (carta.getTipo()) {

            case SALTO:
                System.out.println("SALTO!");
                siguienteTurno();
                break;

            case REVERSA:
                System.out.println("REVERSA!");
                direccion *= -1;
                break;

            case ROBA2:
                System.out.println("ROBA 2!");
                Player siguiente = obtenerSiguienteJugador();
                siguiente.robarCarta(deck);
                siguiente.robarCarta(deck);
                siguienteTurno();
                break;

            case ROBA4:
                System.out.println("ROBA 4!");
                Player siguiente4 = obtenerSiguienteJugador();

                for (int i = 0; i < 4; i++) {
                    siguiente4.robarCarta(deck);
                }

                cambiarColor(carta, jugadorActual);
                siguienteTurno();
                break;

            case COMODIN:
                cambiarColor(carta, jugadorActual);
                break;

            default:
                break;
        }
    }

    private Player obtenerSiguienteJugador() {
        int siguiente = (turnoActual + direccion + players.size()) % players.size();
        return players.get(siguiente);
    }

    private void siguienteTurno() {
        turnoActual = (turnoActual + direccion + players.size()) % players.size();
    }

    private void cambiarColor(Card carta, Player jugador) {

        if (jugador.esHumano()) {

            String color;

            while (true) {
                System.out.println("Elige color (rojo, azul, verde, amarillo): ");
                color = scanner.next().toLowerCase();

                if (color.equals("rojo") || color.equals("azul") ||
                    color.equals("verde") || color.equals("amarillo")) {
                    break;
                }

                System.out.println("Color inválido.");
            }

            carta.setColor(color);

        } else {

            String[] colores = {"rojo", "azul", "verde", "amarillo"};
            String color = colores[(int)(Math.random() * 4)];

            System.out.println(jugador.getNombre() + " cambia color a: " + color);
            carta.setColor(color);
        }
    }
    private void validarUNO(Player player) {

        if (player.getMano().size() == 1) {

            if (player.esHumano()) {

                String respuesta;

                while (true) {
                    System.out.print("¿Quieres decir UNO? (s/n): ");
                    respuesta = scanner.next().toLowerCase();

                    if (respuesta.equals("s") || respuesta.equals("n")) {
                        break;
                    }

                    System.out.println("❌ Entrada inválida. Solo 's' o 'n'.");
                }

                if (respuesta.equals("s")) {
                    System.out.println("✔ Dijiste UNO correctamente");
                } else {
                    System.out.println("❌ No dijiste UNO... Robas 2 cartas!");
                    player.robarCarta(deck);
                    player.robarCarta(deck);
                }

            } else {
               
                System.out.println("ヾ(⌐■_■)ノ " + player.getNombre() + " dice UNO!");
            }
        }
    }
}

