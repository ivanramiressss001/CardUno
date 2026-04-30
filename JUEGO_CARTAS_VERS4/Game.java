package JUEGO_CARTAS_VERS4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal del juego UNO.
 * Se encarga de controlar el flujo del juego, los jugadores y las reglas.
 */
public class Game {

    private Deck deck;
    private ArrayList<Player> players;
    private DiscardPile discardPile;
    private TurnManager turnManager;
    private RuleEngine ruleEngine;
    private Scanner scanner;

    /**
     * Constructor del juego.
     * Inicializa todos los componentes y configura la partida.
     */
    public Game() {
        deck = new Deck();
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
        turnManager = new TurnManager();
        ruleEngine = new RuleEngine();
        configurarJuego();
    }

    private void configurarJuego() {
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        players.add(new Player(nombre, true));
        players.add(new Player("Pepe", false));
        players.add(new Player("Tona", false));
        players.add(new Player("Mari", false));

        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.robarCarta(deck);
            }
        }

        Card primera;
        do {
            primera = deck.robarCarta();
        } while (primera.getTipo() != Card.Tipo.NUMERO);

        discardPile = new DiscardPile(primera);
    }

    /**
     * Inicia el juego y controla los turnos hasta que alguien gane.
     */
    public void iniciar() {
        System.out.println("Bienvenido al UNO Version Software Engineering!");
        while (true) {
            Player jugadorActual = players.get(turnManager.getTurnoActual());
            System.out.println("\n========================================");
            System.out.println("TURNO DE: " + jugadorActual.getNombre());
            System.out.println("CARTA EN MESA: " + discardPile.getCartaMesa());
            System.out.println("========================================");
            jugadorActual.jugarTurno(this, scanner);
            if (jugadorActual.getMano().estaVacia()) {
                anunciarGanador(jugadorActual);
                break;
            }
            turnManager.siguienteTurno(players.size());
        }
    }

    private void anunciarGanador(Player ganador) {
        System.out.println("\n" + ganador.getNombre() + " HA GANADO EL JUEGO!");
    }

    /**
     * Permite cambiar el color de una carta comodin.
     * @param carta La carta a la que se le cambiará el color.
     * @param jugador El jugador que está realizando el cambio.
     */
    public void cambiarColor(Card carta, Player jugador) {
        if (jugador.esHumano()) {
            String color = "";
            while (true) {
                System.out.print("Elige nuevo color (rojo, azul, verde, amarillo): ");
                color = scanner.next().toLowerCase();
                if (color.equals("rojo") || color.equals("azul") ||
                    color.equals("verde") || color.equals("amarillo")) {
                    break;
                }
                System.out.println("Color no valido.");
            }
            carta.setColor(color);
        } else {
            String[] colores = {"rojo", "azul", "verde", "amarillo"};
            String elegido = colores[(int) (Math.random() * 4)];
            carta.setColor(elegido);
            System.out.println("La IA cambio el color a: " + elegido);
        }
    }

    /**
     * Obtiene el siguiente jugador segun el turno actual y direccion.
     * @return La instancia del objeto Player que sigue en el orden de juego.
     */
    public Player obtenerSiguienteJugador() {
        int siguienteIndice = (turnManager.getTurnoActual()
                + turnManager.getDireccion()
                + players.size()) % players.size();
        return players.get(siguienteIndice);
    }

    /**
     * Obtiene el mazo principal del juego.
     * @return El objeto Deck actual.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Obtiene la lista de jugadores participantes.
     * @return ArrayList con los jugadores.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Obtiene el gestor de turnos del juego.
     * @return El objeto TurnManager actual.
     */
    public TurnManager getTurnManager() {
        return turnManager;
    }

    /**
     * Obtiene el motor de reglas aplicado al juego.
     * @return El objeto RuleEngine actual.
     */
    public RuleEngine getRuleEngine() {
        return ruleEngine;
    }

    /**
     * Obtiene la pila de descarte actual.
     * @return El objeto DiscardPile con las cartas en mesa.
     */
    public DiscardPile getDiscardPile() {
        return discardPile;
    }
}