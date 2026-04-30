package JUEGO_CARTAS_VERS4;

import java.util.ArrayList;

/**
 * Representa la pila de cartas descartadas o "mesa".
 * Almacena las cartas que los jugadores van lanzando durante la partida.
 */
public class DiscardPile {
    
    private ArrayList<Card> cartasDescartadas;
    
    /**
     * Inicializa la pila de descarte con una carta inicial.
     * @param cartaInicial La primera carta que se coloca en la mesa al empezar.
     */
    public DiscardPile(Card cartaInicial) {
        cartasDescartadas = new ArrayList<>();
        cartasDescartadas.add(cartaInicial);
    }
    
    /**
     * Obtiene la carta que se encuentra actualmente en la parte superior de la mesa.
     * @return La última carta agregada a la pila de descarte.
     */
    public Card getCartaMesa() {
        return cartasDescartadas.get(cartasDescartadas.size() - 1);
    }
    
    /**
     * Agrega una nueva carta a la pila de descarte.
     * @param carta La carta jugada por un jugador.
     */
    public void agregarCarta(Card carta) {
        cartasDescartadas.add(carta);
    }
}