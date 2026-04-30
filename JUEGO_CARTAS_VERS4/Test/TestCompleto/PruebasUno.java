package TestCompleto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

import JUEGO_CARTAS_VERS4.*;

public class PruebasUno {

    // ===============================
    // AUXILIAR
    // ===============================
    private Game crearJuego(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        return new Game();
    }

    // ===============================
    // CAJA NEGRA (REGLAS)
    // ===============================

    @Test
    void testComodinReal() {
        Game g = crearJuego("Bruno\nazul\n");
        RuleEngine r = new RuleEngine();

        Player p = g.getPlayers().get(0);
        Card c = new Card("negro", Card.Tipo.COMODIN);

        r.aplicarEfecto(c, g, p);

        assertNotEquals("negro", c.getColor());
    }

    @Test
    void testRoba4Real() {
        Game g = crearJuego("Mayte\nrojo\n");
        RuleEngine r = new RuleEngine();

        Player actual = g.getPlayers().get(0);
        Player siguiente = g.obtenerSiguienteJugador();

        int antes = siguiente.getMano().size();

        Card c = new Card("negro", Card.Tipo.ROBA4);

        r.aplicarEfecto(c, g, actual);

        assertEquals(antes + 4, siguiente.getMano().size());
    }

    @Test
    void testReversaReal() {
        Game g = crearJuego("alejandro\n");
        RuleEngine r = new RuleEngine();

        TurnManager t = g.getTurnManager();

        Card c = new Card("azul", Card.Tipo.REVERSA);

        r.aplicarEfecto(c, g, g.getPlayers().get(0));

        assertEquals(-1, t.getDireccion());
    }

    @Test
    void testMismoColorReal() {
        RuleEngine r = new RuleEngine();
        assertTrue(r.esJugadaValida(
            new Card("rojo", 5),
            new Card("rojo", 9)
        ));
    }

    @Test
    void testMismoNumeroReal() {
        RuleEngine r = new RuleEngine();
        assertTrue(r.esJugadaValida(
            new Card("verde", 7),
            new Card("azul", 7)
        ));
    }

    // ===============================
    // TURNOS (HUMANO / BOT)
    // ===============================

    @Test
    void testTurnoHumanoReal() {
        Game g = crearJuego("juan\n");

        Player p = g.getPlayers().get(0);

        assertTrue(p.esHumano());
    }

    @Test
    void testTurnoBotReal() {
        Game g = crearJuego("leon\n");

        Player bot = g.getPlayers().get(1);

        assertFalse(bot.esHumano());
    }

    @Test
    void testCambioTurnoReal() {
        Game g = crearJuego("Ian\n");

        TurnManager t = g.getTurnManager();

        int antes = t.getTurnoActual();

        t.siguienteTurno(4);

        assertNotEquals(antes, t.getTurnoActual());
    }

    // ===============================
    // DECISION
    // ===============================

    @Test
    void testIfComodinReal() {
        RuleEngine r = new RuleEngine();

        assertTrue(r.esJugadaValida(
            new Card("negro", Card.Tipo.COMODIN),
            new Card("rojo", 3)
        ));
    }

    @Test
    void testIfColorReal() {
        RuleEngine r = new RuleEngine();

        assertTrue(r.esJugadaValida(
            new Card("azul", 2),
            new Card("azul", 9)
        ));
    }

    @Test
    void testIfNumeroReal() {
        RuleEngine r = new RuleEngine();

        assertTrue(r.esJugadaValida(
            new Card("verde", 8),
            new Card("rojo", 8)
        ));
    }

    @Test
    void testElseReal() {
        RuleEngine r = new RuleEngine();

        assertFalse(r.esJugadaValida(
            new Card("verde", 1),
            new Card("rojo", 9)
        ));
    }

    // ===============================
    // EXCEPCIONES
    // ===============================

    @Test
    void testIndiceInvalidoReal() {
        Hand h = new Hand();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            h.getCarta(10);
        });
    }

    @Test
    void testJugarCartaInvalidaReal() {
        Hand h = new Hand();

        assertThrows(Exception.class, () -> {
            h.jugarCarta(5);
        });
    }

    @Test
    void testManoVaciaReal() {
        Hand h = new Hand();

        assertTrue(h.size() == 0);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            h.getCarta(0);
        });
    }

    @Test
    void testJugadorNoNullReal() {
        Player p = new Player("pedro", true);

        assertNotNull(p.getNombre());
    }

   
    @Test
    void testDeckRobarCarta() {
        Deck d = new Deck();
        assertNotNull(d.robarCarta());
    }

    @Test
    void testPlayerRobarCarta() {
        Deck d = new Deck();
        Player p = new Player("A", false);

        int antes = p.getMano().size();
        p.robarCarta(d);

        assertEquals(antes + 1, p.getMano().size());
    }

    @Test
    void testTurnManagerSalto() {
        TurnManager t = new TurnManager();

        t.saltarTurno(4);

        assertEquals(2, t.getTurnoActual());
    }

    @Test
    void testCambioDireccion() {
        TurnManager t = new TurnManager();

        int dir = t.getDireccion();

        t.cambiarDireccion();

        assertEquals(dir * -1, t.getDireccion());
    }

    @Test
    void testCartaColor() {
        Card c = new Card("rojo", 5);
        c.setColor("verde");

        assertEquals("verde", c.getColor());
    }
}