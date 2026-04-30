package JUEGO_CARTAS_VERS4;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Representa el mazo de cartas del juego.
 * 
 * Responsable de:
 * - Crear la baraja completa
 * - Barajar cartas
 * - Permitir robar cartas
 */
public class Deck {
    /**
     * Lista de cartas del mazo.
     */
    private ArrayList<Card> cartas;
    /**
     * Construye el mazo, crea la baraja y la baraja.
     */
    public Deck() {
        cartas = new ArrayList<>();
        crearBaraja();
        barajar();
    }

    private void crearBaraja() {
        String[] colores = {"rojo", "azul", "verde", "amarillo"};
        for (String color : colores) {
            cartas.add(new Card(color, 0));
            for (int i = 1; i <= 9; i++) {
                cartas.add(new Card(color, i));
                cartas.add(new Card(color, i));
            }
            for (int j = 0; j < 2; j++) {
                cartas.add(new Card(color, Card.Tipo.SALTO));
                cartas.add(new Card(color, Card.Tipo.REVERSA));
                cartas.add(new Card(color, Card.Tipo.ROBA2));
            }
        }
        for (int i = 0; i < 4; i++) {
            cartas.add(new Card("negro", Card.Tipo.COMODIN));
            cartas.add(new Card("negro", Card.Tipo.ROBA4));
        }
    }

    /**
     * Mezcla aleatoriamente las cartas contenidas en el mazo.
     */
    public void barajar() {
        Collections.shuffle(cartas);
    }
    
    /**
     * Permite robar una carta del mazo.
     * 
     * @return carta robada
     * @throws IllegalStateException si el mazo está vacío
     */
    public Card robarCarta() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("El mazo está vacío");
        }
        return cartas.remove(0);
    }
}