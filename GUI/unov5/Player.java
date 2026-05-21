package unov5;

import java.util.Scanner;

/**
 * Representa a un jugador del juego UNO.
 * Puede ser humano o IA.
 * 
 * Se encarga de:
 * - Guardar su mano de cartas
 * - Robar cartas
 * - Jugar cartas
 * - Ejecutar su turno
 */
public class Player {

    private String nombre;
    private Hand mano;
    private boolean esHumano;

    /**
     * Crea un nuevo jugador con un nombre y define si es humano o bot.
     * @param nombre El nombre identificador del jugador.
     * @param esHumano true si el jugador es controlado por una persona, false si es IA.
     */
    public Player(String nombre, boolean esHumano) {
        this.nombre = nombre;
        this.esHumano = esHumano;
        this.mano = new Hand();
    }

    /**
     * Obtiene el nombre del jugador.
     * @return nombre del jugador.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene la mano de cartas actual del jugador.
     * @return El objeto Hand asociado al jugador.
     */
    public Hand getMano() { return mano; }

    /**
     * Verifica si el jugador es humano.
     * @return true si es humano.
     */
    public boolean esHumano() { return esHumano; }

    /**
     * Permite al jugador robar una carta del mazo y agregarla a su mano.
     * @param deck El mazo del que se extraerá la carta.
     */
    public void robarCarta(Deck deck) {
        mano.agregarCarta(deck.robarCarta());
    }

    /**
     * Permite jugar una carta de la mano según su índice de posición.
     * @param index El índice de la carta en la mano.
     * @return La carta seleccionada para jugar.
     */
    public Card jugarCarta(int index) {
        return mano.jugarCarta(index);
    }

    /**
     * Ejecuta el turno del jugador.
     * @param game Instancia del juego para acceder a la lógica y componentes.
     * @param scanner Objeto Scanner para recibir la entrada del usuario si es humano.
     */
    public void jugarTurno(Game game, Scanner scanner) {
        if (esHumano) {
            ejecutarTurnoHumano(game, scanner);
        } else {
            ejecutarTurnoIA(game);
        }
    }

    private void ejecutarTurnoHumano(Game game, Scanner scanner) {
        RuleEngine ruleEngine = game.getRuleEngine();
        DiscardPile discardPile = game.getDiscardPile();
        Deck deck = game.getDeck();

        while (true) {
            mano.mostrarMano(discardPile.getCartaMesa(), ruleEngine);
            System.out.println("\n- Escribe numero para jugar");
            System.out.println("- Escribe -1 para robar carta: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Ingresa un numero valido.");
                scanner.next();
                continue;
            }

            int opcion = scanner.nextInt();

            if (opcion == -1) {
                Card robada = deck.robarCarta();
                System.out.println("Has robado: " + robada);
                if (ruleEngine.esJugadaValida(robada, discardPile.getCartaMesa())) {
                    System.out.print("Quieres jugar la carta robada? (s/n): ");
                    String r = scanner.next();
                    if (r.equalsIgnoreCase("s")) {
                        discardPile.agregarCarta(robada);
                        ruleEngine.aplicarEfecto(robada, game, this);
                        return;
                    }
                }
                mano.agregarCarta(robada);
                return;
            }

            if (opcion < 0 || opcion >= mano.size()) {
                System.out.println("Numero fuera de rango.");
                continue;
            }

            Card cartaParaVer = mano.getCarta(opcion);
            if (!ruleEngine.esJugadaValida(cartaParaVer, discardPile.getCartaMesa())) {
                System.out.println("Esa carta no se puede jugar.");
                continue;
            }

            Card cartaJugada = mano.jugarCarta(opcion);
            System.out.println(nombre + " juega: " + cartaJugada);
            discardPile.agregarCarta(cartaJugada);
            ruleEngine.aplicarEfecto(cartaJugada, game, this);
            return;
        }
    }

    private void ejecutarTurnoIA(Game game) {
        RuleEngine ruleEngine = game.getRuleEngine();
        DiscardPile discardPile = game.getDiscardPile();
        Deck deck = game.getDeck();

        for (int i = 0; i < mano.size(); i++) {
            Card c = mano.getCarta(i);
            if (ruleEngine.esJugadaValida(c, discardPile.getCartaMesa())) {
                Card jugada = mano.jugarCarta(i);
                discardPile.agregarCarta(jugada);
                System.out.println(nombre + " juega: " + jugada);
                ruleEngine.aplicarEfecto(jugada, game, this);
                if (mano.size() == 1) {
                    System.out.println(nombre + " dice UNO!");
                }
                return;
            }
        }

        Card robada = deck.robarCarta();
        System.out.println(nombre + " roba una carta...");
        if (ruleEngine.esJugadaValida(robada, discardPile.getCartaMesa())) {
            System.out.println(nombre + " juega la robada: " + robada);
            discardPile.agregarCarta(robada);
            ruleEngine.aplicarEfecto(robada, game, this);
            if (mano.size() == 1) {
                System.out.println(nombre + " dice UNO!");
            }
        } else {
            mano.agregarCarta(robada);
        }
    }
}