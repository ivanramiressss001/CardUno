package JUEGO_CARTAS_VERS3;

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

    public void mostrarMano(Card cartaMesa) {

        System.out.println("   (⌐■_■)  TUS CARTAS:\n");

        for (int i = 0; i < cartas.size(); i++) {

            Card c = cartas.get(i);

            if (c.esJugableSobre(cartaMesa)) {
                System.out.println("--> [" + i + "] " + c + " <<< JUGABLE");
            } else {
                System.out.println("   [" + i + "] " + c);
            }
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
