package JUEGO_CARTAS_VERS4;

import java.util.ArrayList;
/**
 * Representa la mano de un jugador en el juego UNO
 * 
 * Encapsula:
 * - La colección de cartas del jugador
 * - Operaciones como agregar, jugar y consultar cartas
 */
public class Hand {
    /**
     * Lista de cartas en la mano
     */
    private ArrayList<Card> cartas;

    /**
     * Crea una nueva mano vacía para el jugador.
     */
    public Hand() {
        cartas = new ArrayList<>();
    }

    /**
     * Añade una carta a la colección del jugador.
     * @param carta La carta que se desea agregar.
     */
    public void agregarCarta(Card carta) {
        cartas.add(carta);
    }

    /**
     * Remueve y retorna una carta de la mano según su índice
     * 
     * @param indice posición de la carta en la mano
     * @return carta jugada
     * @throws IllegalArgumentException si el índice es inválido
     */
    public Card jugarCarta(int indice) {
        if (indice >= 0 && indice < cartas.size()) {
            return cartas.remove(indice);
        }
        throw new IllegalArgumentException("Índice inválido");
    }

    /**
     * Muestra las cartas del jugador indicando cuáles son jugables
     * 
     * @param cartaMesa carta actual en mesa
     * @param ruleEngine motor de reglas
     */
    public void mostrarMano(Card cartaMesa, RuleEngine ruleEngine) {
        System.out.println("\n(⌐■_■) TUS CARTAS:\n");
        for (int i = 0; i < cartas.size(); i++) {
            Card c = cartas.get(i);
            if (ruleEngine.esJugadaValida(c, cartaMesa)) {
                System.out.println("--> [" + i + "] " + c + " <<< JUGABLE");
            } else {
                System.out.println("   [" + i + "] " + c);
            }
        }
    }

    /**
     * Obtiene el numero de cartas en la mano
     * 
     * @return cantidad de cartas
     */
    public int size() {
        return cartas.size();
    }

    /**
     * Indica si la mano está vacia
     * 
     * @return true si no hay cartas
     */
    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    /**
     * Obtiene una carta sin removerla de la mano
     * 
     * @param indice posición de la carta
     * @return carta en la posición indicada
     */
    public Card getCarta(int indice) {
        return cartas.get(indice);
    }
}