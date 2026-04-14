package JUEGO_DE_CARTAS.NUEVA_VERSION;

public class Card {

    public enum Tipo {
        NUMERO,
        REVERSA,
        SALTO,
        ROBA2,
        ROBA4,
        COMODIN
    }

    private String color;
    private Tipo tipo;
    private int numero; // solo para cartas numéricas

    // Constructor carta numérica
    public Card(String color, int numero) {
        this.color = color;
        this.numero = numero;
        this.tipo = Tipo.NUMERO;
    }

    // Constructor carta especial
    public Card(String color, Tipo tipo) {
        this.color = color;
        this.tipo = tipo;
        this.numero = -1;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getNumero() {
        return numero;
    }

    public boolean esJugableSobre(Card otra) {

        if (this.tipo == Tipo.COMODIN || this.tipo == Tipo.ROBA4) {
            return true;
        }

        if (this.color.equals(otra.color)) {
            return true;
        }

        if (this.tipo == Tipo.NUMERO && otra.tipo == Tipo.NUMERO) {
            return this.numero == otra.numero;
        }

        return this.tipo == otra.tipo;
    }

    @Override
    public String toString() {

        if (tipo == Tipo.NUMERO) {
            return color + " " + numero;
        }

        return color + " " + tipo;
    }
}
