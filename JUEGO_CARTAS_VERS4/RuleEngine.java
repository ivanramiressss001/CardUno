package JUEGO_CARTAS_VERS4;
/**
 * Motor de reglas del juego UNO
 * 
 * Responsable de:
 * - Validar jugadas
 * - Aplicar efectos de cartas especiales
 * 
 * Separa la lógica del juego de la interfaz y del flujo principal
 */
public class RuleEngine {

    /**
     * Constructor por defecto de RuleEngine.
     * Inicializa el motor de reglas para la validación de jugadas y efectos.
     */
    public RuleEngine() {
        // Constructor vacío explícito para documentación Javadoc
    }

    /**
     * Determina si una carta puede jugarse sobre otra
     * 
     * Reglas:
     * - Mismo color
     * - Mismo número
     * - Mismo tipo
     * - Comodín y ROBA4 siempre válidos
     * 
     * @param jugada carta a evaluar
     * @param cartaMesa carta actual en mesa
     * @return true si la jugada es válida
     */
    public boolean esJugadaValida(Card jugada, Card cartaMesa) {

        if (jugada.getTipo() == Card.Tipo.COMODIN || jugada.getTipo() == Card.Tipo.ROBA4) {
            return true;
        }

        if (jugada.getColor().equals(cartaMesa.getColor())) {
            return true;
        }

        if (jugada.getTipo() == Card.Tipo.NUMERO && cartaMesa.getTipo() == Card.Tipo.NUMERO) {
            return jugada.getNumero() == cartaMesa.getNumero();
        }

        return jugada.getTipo() == cartaMesa.getTipo();
    }

    /**
     * Aplica el efecto de una carta especial
     * 
     * Ejemplos:
     * - SALTO → omite turno
     * - REVERSA → cambia dirección
     * - ROBA2 / ROBA4 → roba cartas y pierde turno
     * 
     * @param carta carta jugada
     * @param game instancia del juego
     * @param jugadorActual jugador que jugó la carta
     */
    public void aplicarEfecto(Card carta, Game game, Player jugadorActual) {

        switch (carta.getTipo()) {

            case SALTO:
                game.getTurnManager().saltarTurno(game.getPlayers().size());
                break;

            case REVERSA:
                if (game.getPlayers().size() == 2) {
                    game.getTurnManager().saltarTurno(game.getPlayers().size());
                } else {
                    game.getTurnManager().cambiarDireccion();
                }
                break;

            case ROBA2:
                Player siguiente = game.obtenerSiguienteJugador();
                for (int i = 0; i < 2; i++) {
                    siguiente.robarCarta(game.getDeck());
                }
                game.getTurnManager().saltarTurno(game.getPlayers().size());
                break;

            case ROBA4:
                Player siguiente4 = game.obtenerSiguienteJugador();
                for (int i = 0; i < 4; i++) {
                    siguiente4.robarCarta(game.getDeck());
                }
                game.cambiarColor(carta, jugadorActual);
                game.getTurnManager().saltarTurno(game.getPlayers().size());
                break;

            case COMODIN:
                game.cambiarColor(carta, jugadorActual);
                break;

            default:
                break;
        }
    }
}