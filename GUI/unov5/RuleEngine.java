package unov5;

/**
 * Motor de reglas del juego UNO
 */
public class RuleEngine {

    public RuleEngine() {
        // Constructor vacío
    }

    public boolean esJugadaValida(Card jugada, Card cartaMesa) {
        if (jugada.getTipo() == Card.Tipo.COMODIN ||
            jugada.getTipo() == Card.Tipo.ROBA4) {
            return true;
        }

        if (jugada.getColor().equals(cartaMesa.getColor())) {
            return true;
        }

        if (jugada.getTipo() == Card.Tipo.NUMERO &&
            cartaMesa.getTipo() == Card.Tipo.NUMERO) {
            return jugada.getNumero() == cartaMesa.getNumero();
        }

        return jugada.getTipo() == cartaMesa.getTipo();
    }

    public void aplicarEfecto(Card carta, Game game, Player jugadorActual) {
        int total = game.getPlayers().size();
        TurnManager tm = game.getTurnManager();

        switch (carta.getTipo()) {
            case SALTO:
                tm.saltarTurno(total);
                break;

            case REVERSA:
                if (total == 2) {
                    tm.saltarTurno(total);
                } else {
                    tm.cambiarDireccion();
                    tm.siguienteTurno(total);
                }
                break;

            case ROBA2:
                Player siguiente = game.obtenerSiguienteJugador();
                for (int i = 0; i < 2; i++) {
                    if (!game.getDeck().estaVacio()) {
                        siguiente.robarCarta(game.getDeck());
                    }
                }
                tm.saltarTurno(total);
                break;

            case ROBA4:
                Player siguiente4 = game.obtenerSiguienteJugador();
                for (int i = 0; i < 4; i++) {
                    if (!game.getDeck().estaVacio()) {
                        siguiente4.robarCarta(game.getDeck());
                    }
                }
                tm.saltarTurno(total);
                break;

            case COMODIN:
            case NUMERO:
                tm.siguienteTurno(total);
                break;
        }
    }
}