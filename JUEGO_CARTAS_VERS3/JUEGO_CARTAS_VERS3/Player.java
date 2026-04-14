package JUEGO_CARTAS_VERS3;

//import java.util.Scanner;

public class Player {

    private String nombre;
    private Hand mano;
    private boolean esHumano;

    public Player(String nombre, boolean esHumano) {
        this.nombre = nombre;
        this.esHumano = esHumano;
        this.mano = new Hand();
    }

    public String getNombre() {
        return nombre;
    }

    public Hand getMano() {
        return mano;
    }

    public boolean esHumano() {
        return esHumano;
    }

    public void robarCarta(Deck deck) {
        mano.agregarCarta(deck.robarCarta());
    }

    public boolean tieneJugadaValida(Card cartaMesa) {
        return mano.tieneJugadaValida(cartaMesa);
    }

    public Card jugarCarta(int index) {
        return mano.jugarCarta(index);
    }

    public void mostrarMano(Card cartaMesa) {
        mano.mostrarMano(cartaMesa);
    }
    public void jugarTurno(Game game) {

        if (esHumano) {
            game.turnoHumano(this);
        } else {
            game.turnoIA(this);
        }
    }
}