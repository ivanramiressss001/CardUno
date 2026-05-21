package unov5;

/**
 * Controla el orden de los turnos del juego.
 * Maneja que jugador juega y en que direccion.
 */
public class TurnManager {

    private int turnoActual;
    private int direccion;

    /**
     * Inicializa el gestor de turnos con el primer jugador y dirección normal.
     */
    public TurnManager() {
        turnoActual = 0;
        direccion = 1;
    }

    /**
     * Obtiene el índice del jugador que tiene el turno actualmente.
     * @return Índice numérico del jugador.
     */
    public int getTurnoActual() {
        return turnoActual;
    }

    /**
     * Obtiene la dirección actual de la ronda (1 para normal, -1 para reversa).
     * @return El valor de la dirección.
     */
    public int getDireccion() {
        return direccion;
    }

    /**
     * Avanza al siguiente jugador segun la direccion.
     * @param totalJugadores Cantidad total de jugadores en la partida para calcular el ciclo.
     */
    public void siguienteTurno(int totalJugadores) {
        turnoActual = (turnoActual + direccion + totalJugadores) % totalJugadores;
    }

    /**
     * Salta el turno de un jugador.
     * Se usa en cartas como SALTO o ROBA.
     * @param totalJugadores Cantidad total de jugadores en la partida.
     */
    public void saltarTurno(int totalJugadores) {
        siguienteTurno(totalJugadores);
        siguienteTurno(totalJugadores);
    }

    /**
     * Invierte la dirección de la rotación de los turnos.
     */
    public void cambiarDireccion() {
        direccion *= -1;
    }
}