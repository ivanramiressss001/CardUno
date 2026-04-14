package JUEGO_DE_CARTAS_VER2;

import java.util.Scanner;

public class Game {

    private Deck deck;
    private Hand jugador;
    private Hand computadora;
    private Card cartaMesa;
    private Scanner scanner;

    private boolean jugadorDijoUNO;

    public Game() {
        deck = new Deck();
        jugador = new Hand();
        computadora = new Hand();
        scanner = new Scanner(System.in);
        jugadorDijoUNO = false;
    }

    public void iniciar() {

        repartirCartas();

        cartaMesa = deck.robarCarta();
        System.out.println("Carta inicial: " + cartaMesa);

        while (true) {

            turnoJugador();

            if (jugador.estaVacia()) {
                System.out.println("Jugador gana!");
                break;
            }

            turnoComputadora();

            if (computadora.estaVacia()) {
                System.out.println("Computadora gana!");
                break;
            }
        }
    }

    private void repartirCartas() {

        for (int i = 0; i < 7; i++) {

            jugador.agregarCarta(deck.robarCarta());
            computadora.agregarCarta(deck.robarCarta());
        }
    }

    private void turnoJugador() {

        System.out.println("\nCarta en mesa: " + cartaMesa);
        jugador.mostrarMano();

        if (!jugador.tieneJugadaValida(cartaMesa)) {

            System.out.println("No tienes jugada válida. Robas carta.");

            Card robada = deck.robarCarta();
            System.out.println("Robaste: " + robada);

            jugador.agregarCarta(robada);

            System.out.println("Tu nueva mano:");
            jugador.mostrarMano();

            if (robada.esJugableSobre(cartaMesa)) {

                System.out.println("La carta robada es jugable y se tira automáticamente.");

                jugador.jugarCarta(jugador.size() - 1);

                cartaMesa = robada;

                aplicarEfecto(robada, true);

                verificarUNO();
                verificarPenalizacionUNO();

            } else {

                System.out.println("La carta robada no se puede jugar.");
            }

            return;
        }

        System.out.print("Selecciona carta: ");

        if (!scanner.hasNextInt()) {

            System.out.println("Entrada inválida.");
            scanner.next();
            return;
        }

        int opcion = scanner.nextInt();

        if (opcion < 0 || opcion >= jugador.size()) {

            System.out.println("Opción inválida.");
            return;
        }

        Card carta = jugador.jugarCarta(opcion);

        if (!carta.esJugableSobre(cartaMesa)) {

            System.out.println("Carta no válida");
            jugador.agregarCarta(carta);
            return;
        }

        System.out.println("Jugador tira: " + carta);

        cartaMesa = carta;

        aplicarEfecto(carta, true);

        verificarUNO();

        verificarPenalizacionUNO();
    }

    private void turnoComputadora() {

        System.out.println("\nTurno de la computadora");

        for (int i = 0; i < computadora.size(); i++) {

            Card carta = computadora.getCarta(i);

            if (carta.esJugableSobre(cartaMesa)) {

                computadora.jugarCarta(i);

                System.out.println("Computadora juega: " + carta);

                cartaMesa = carta;

                aplicarEfecto(carta, false);

                return;
            }
        }

        Card robada = deck.robarCarta();

        System.out.println("Computadora roba carta: " + robada);

        if (robada.esJugableSobre(cartaMesa)) {

            System.out.println("Computadora juega la carta robada.");

            cartaMesa = robada;

            aplicarEfecto(robada, false);

        } else {

            computadora.agregarCarta(robada);
        }
    }

    private void aplicarEfecto(Card carta, boolean esJugador) {

        switch (carta.getTipo()) {

            case SALTO:

                System.out.println("Carta SALTO!");

                if (esJugador) {

                    System.out.println("El jugador juega otra vez.");
                    turnoJugador();

                } else {

                    System.out.println("La computadora juega otra vez.");
                    turnoComputadora();
                }

                break;

            case REVERSA:

                System.out.println("Carta REVERSA (solo 2 jugadores, funciona como salto)");

                if (esJugador) {

                    turnoJugador();

                } else {

                    turnoComputadora();
                }

                break;

            case ROBA2:

                System.out.println("Se roban 2 cartas.");

                for (int i = 0; i < 2; i++) {

                    if (esJugador) {

                        computadora.agregarCarta(deck.robarCarta());

                    } else {

                        jugador.agregarCarta(deck.robarCarta());
                    }
                }

                break;

            case ROBA4:

                System.out.println("Se roban 4 cartas.");

                for (int i = 0; i < 4; i++) {

                    if (esJugador) {

                        computadora.agregarCarta(deck.robarCarta());

                    } else {

                        jugador.agregarCarta(deck.robarCarta());
                    }
                }

                cambiarColor(carta, esJugador);

                break;

            case COMODIN:

                cambiarColor(carta, esJugador);

                break;

            default:
                break;
        }
    }

    private void cambiarColor(Card carta, boolean esJugador) {

        String nuevoColor;

        if (esJugador) {

            System.out.println("Elige nuevo color (rojo, azul, verde, amarillo): ");

            nuevoColor = scanner.next().toLowerCase();

        } else {

            String[] colores = {"rojo", "azul", "verde", "amarillo"};

            int indice = (int) (Math.random() * colores.length);

            nuevoColor = colores[indice];

            System.out.println("La computadora cambia color a: " + nuevoColor);
        }

        carta.setColor(nuevoColor);
    }

    private void verificarUNO() {

        if (jugador.size() == 1) {

            System.out.println("Tienes una carta. ¿Desea decir UNO? (s/n)");

            String respuesta = scanner.next();

            if (respuesta.equalsIgnoreCase("s")) {

                jugadorDijoUNO = true;

                System.out.println("Jugador dice UNO!");

            } else {

                jugadorDijoUNO = false;
            }
        }
    }

    private void verificarPenalizacionUNO() {

        if (jugador.size() == 1 && !jugadorDijoUNO) {

            System.out.println("No dijiste UNO!");
            System.out.println("Penalización: robas dos cartas!");

            jugador.agregarCarta(deck.robarCarta());
            jugador.agregarCarta(deck.robarCarta());
        }

        jugadorDijoUNO = false;
    }
}