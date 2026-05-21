package unov5;

import java.util.ArrayList;

/**
 * Clase principal del juego UNO.
 * Controla jugadores, reglas y flujo de la partida.
 */
public class Game {

    private Deck deck;
    private ArrayList<Player> players;
    private DiscardPile discardPile;
    private TurnManager turnManager;
    private RuleEngine ruleEngine;

    /**
     * Constructor del juego.
     * 
     * @param nombreJugador nombre del jugador humano
     */
    public Game(String nombreJugador) {

        deck = new Deck();

        players = new ArrayList<>();

        turnManager = new TurnManager();

        ruleEngine = new RuleEngine();

        configurarJuego(nombreJugador);
    }
    
    /**
     * Verifica y reinicia el mazo si está vacío
     */
    public void verificarMazo() {
        if (deck.estaVacio()) {
            deck.reiniciar();
        }
    }

    /**
     * Configura jugadores y cartas iniciales.
     */
    private void configurarJuego(String nombreJugador) {
        // JUGADOR HUMANO
        players.add(new Player(nombreJugador, true)); // Índice 0

        // BOTS EN ORDEN HORARIO
        players.add(new Player("Tona", false));       // Índice 1 (Izquierda)
        players.add(new Player("Pepe", false));       // Índice 2 (Arriba)
        players.add(new Player("Mari", false));       // Índice 3 (Derecha)

        // REPARTIR CARTAS
        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.robarCarta(deck);
            }
        }

        // PRIMERA CARTA NUMERICA
        Card primera;
        do {
            primera = deck.robarCarta();
        } while (primera.getTipo() != Card.Tipo.NUMERO);
        discardPile = new DiscardPile(primera);
    }

    /**
     * Obtiene siguiente jugador.
     */
    public Player obtenerSiguienteJugador() {

        int siguienteIndice = (

                turnManager.getTurnoActual()
                        + turnManager.getDireccion()
                        + players.size()

        ) % players.size();

        return players.get(siguienteIndice);
    }

    /**
     * Cambiar color comodin.
     */
    public void cambiarColor(Card carta, String color) {

        carta.setColor(color);
    }

    /**
     * Obtener mazo.
     */
    public Deck getDeck() {

        return deck;
    }

    /**
     * Obtener jugadores.
     */
    public ArrayList<Player> getPlayers() {

        return players;
    }

    /**
     * Obtener turn manager.
     */
    public TurnManager getTurnManager() {

        return turnManager;
    }

    /**
     * Obtener rule engine.
     */
    public RuleEngine getRuleEngine() {

        return ruleEngine;
    }

    /**
     * Obtener pila descarte.
     */
    public DiscardPile getDiscardPile() {

        return discardPile;
    }

    /**
     * Obtener carta en mesa.
     */
    public Card getCartaMesa() {

        return discardPile.getCartaMesa();
    }

    /**
     * Obtener jugador humano.
     */
    public Player getJugadorHumano() {

        return players.get(0);
    }
}