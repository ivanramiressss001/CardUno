package JUEGO_CARTAS_VERS4;
/**
 * Representa una carta del juego UNO.
 * Una carta puede ser numerica o especial.
 */
public class Card {

    /**
     * Tipos de cartas disponibles en el juego UNO.
     */
    public enum Tipo {
        /** Carta numerica */
        NUMERO,
        /** Cambia la direccion del juego */
        REVERSA,
        /** Salta turno */
        SALTO,
        /** Obliga a robar 2 cartas */
        ROBA2,
        /** Obliga a robar 4 cartas */
        ROBA4,
        /** Permite cambiar color */
        COMODIN
    }

    private String color;
    private Tipo tipo;
    private int numero;

    /**
     * Constructor para carta numerica.
     * @param color color de la carta
     * @param numero valor numerico
     */
    public Card(String color, int numero) {
        this.color = color;
        this.numero = numero;
        this.tipo = Tipo.NUMERO;
    }

    /**
     * Constructor para carta especial.
     * @param color color de la carta
     * @param tipo tipo de carta
     */
    public Card(String color, Tipo tipo) {
        this.color = color;
        this.tipo = tipo;
        this.numero = -1;
    }

    /**
     * Obtiene el color de la carta.
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Cambia el color de la carta.
     * @param color nuevo color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene el tipo de carta.
     * @return tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Obtiene el numero de la carta.
     * @return numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Verifica si la carta es jugable sobre otra.
     * @param otra carta en mesa
     * @return true si es valida
     */
    public boolean esJugableSobre(Card otra) {
        return this.color.equals(otra.color) ||
               this.tipo == otra.tipo ||
               this.numero == otra.numero ||
               this.tipo == Tipo.COMODIN ||
               this.tipo == Tipo.ROBA4;
    }

    /**
     * Representacion en texto de la carta.
     * @return texto
     */
    @Override
    public String toString() {
        return tipo == Tipo.NUMERO ? color + " " + numero : color + " " + tipo;
    }
}