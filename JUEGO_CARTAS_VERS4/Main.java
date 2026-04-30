package JUEGO_CARTAS_VERS4;

/**
 * Clase de entrada para la aplicación.
 * Contiene el método main que lanza la ejecución del juego UNO.
 */
public class Main {
    
    /**
     * Constructor por defecto de la clase Main.
     */
    public Main() {}

    /**
     * Punto de entrada del programa. Crea e inicia una nueva partida.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Game juego = new Game();
        juego.iniciar();
    }
}