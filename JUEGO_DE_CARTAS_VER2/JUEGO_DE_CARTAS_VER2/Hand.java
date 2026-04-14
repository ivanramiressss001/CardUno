package JUEGO_DE_CARTAS_VER2;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cartas;

    public Hand() {
        cartas = new ArrayList<>();
    }

    public void agregarCarta(Card carta) {
        cartas.add(carta);
    }

    public Card jugarCarta(int indice) {

        if (indice >= 0 && indice < cartas.size()) {
            return cartas.remove(indice);
        }

        return null;
    }

    public void mostrarMano() {

        for (int i = 0; i < cartas.size(); i++) {
            System.out.println(i + ": " + cartas.get(i));
        }
    }

    public boolean tieneJugadaValida(Card cartaMesa) {

        for (Card c : cartas) {

            if (c.esJugableSobre(cartaMesa)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return cartas.size();
    }

    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    public Card getCarta(int indice) {
        return cartas.get(indice);
    }
}
