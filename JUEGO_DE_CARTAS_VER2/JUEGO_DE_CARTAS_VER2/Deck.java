package JUEGO_DE_CARTAS_VER2;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cartas;

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

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Card robarCarta() {

        if (cartas.isEmpty()) {
            return null;
        }

        return cartas.remove(0);
    }
}
