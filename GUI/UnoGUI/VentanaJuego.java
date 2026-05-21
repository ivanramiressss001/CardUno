package UnoGUI;

import unov5.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 * Ventana principal del desarrollo de la partida del juego UNO.
 * <p>
 * Gestiona la interfaz gráfica de usuario (GUI) para un jugador humano interactuando 
 * contra tres bots (Tona, Pepe y Mari). Controla el renderizado de cartas en abanico, 
 * la actualización de turnos, el mazo de robo, la pila de descarte y el log de eventos.
 * </p>
 * * @author TuNombre
 * @version 1.0
 */
public class VentanaJuego extends JFrame {

    /** Instancia del controlador principal de la lógica del juego. */
    private Game game;

    /** Panel superior que representa la mano del Bot 2 (Pepe). */
    private JPanel panelNorte;
    /** Panel inferior que representa la mano del Jugador Humano. */
    private JPanel panelSur;
    /** Panel derecho que contiene la consola de mensajes y los botones de acción del jugador. */
    private JPanel panelEste;
    /** Panel izquierdo que representa la mano del Bot 1 (Tona). */
    private JPanel panelBotIzquierdo;
    /** Panel central-derecho que representa la mano del Bot 3 (Mari). */
    private JPanel panelBotDerecho;

    /** Etiqueta que muestra visualmente la carta actual en la mesa (pila de descarte). */
    private JLabel lblCartaMesa;
    /** Etiqueta interactiva o visual que representa el mazo de cartas para robar. */
    private JLabel lblMazo;
    /** Área de texto donde se imprimen los sucesos de la partida en tiempo real. */
    private JTextArea areaMensajes;
    /** Botón para que el jugador humano declare de forma voluntaria que le queda una carta. */
    private JButton btnUno;
    /** Botón para que el jugador tome una carta del mazo. */
    private JButton btnRobar;
    /** Botón para ceder el turno una vez que se ha robado o no se puede jugar. */
    private JButton btnPasarTurno;

    /**
     * Constructor de la ventana de juego. Inicializa el motor del juego,
     * configura la ventana, construye las regiones de la interfaz y arranca los turnos.
     * * @param nombreJugador Nombre que adoptará el usuario humano en la partida.
     */
    public VentanaJuego(String nombreJugador) {
        game = new Game(nombreJugador);

        setTitle("UNO - PARTIDA");
        setSize(1300, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0, 100, 0));

        crearPanelNorte();
        crearPanelCentral();
        crearPanelSur();
        crearPanelEste();

        setVisible(true);

        actualizarMano();
        actualizarCartaMesa();
        actualizarBotsVisual();

        agregarMensaje("¡Bienvenido al UNO!");
        agregarMensaje("Tu nombre: " + nombreJugador);
        agregarMensaje("Carta inicial: " + game.getCartaMesa());

        anunciarTurno();
        iniciarBots();
    }

    /**
     * Construye y configura el panel norte asignado al Bot 2.
     */
    private void crearPanelNorte() {
        panelNorte = new JPanel();
        panelNorte.setPreferredSize(new Dimension(100, 160));
        panelNorte.setBackground(new Color(0, 80, 0));
        add(panelNorte, BorderLayout.NORTH);
    }

    /**
     * Construye la zona central de la pantalla, la cual aloja las manos de los bots laterales 
     * (izquierdo y derecho) y el centro de la mesa (Mazo de robo y carta actual).
     */
    private void crearPanelCentral() {
        JPanel panelCentralGeneral = new JPanel(new BorderLayout());
        panelCentralGeneral.setBackground(new Color(0, 100, 0));

        panelBotIzquierdo = new JPanel();
        panelBotIzquierdo.setPreferredSize(new Dimension(150, 350));
        panelBotIzquierdo.setBackground(new Color(0, 80, 0));

        panelBotDerecho = new JPanel();
        panelBotDerecho.setPreferredSize(new Dimension(150, 350));
        panelBotDerecho.setBackground(new Color(0, 80, 0));

        JPanel panelMesaCentro = new JPanel();
        panelMesaCentro.setBackground(new Color(0, 120, 0));
        panelMesaCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));

        lblMazo = new JLabel();
        lblMazo.setPreferredSize(new Dimension(90, 130));
        lblMazo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        lblMazo.setHorizontalAlignment(SwingConstants.CENTER);
        
        ImageIcon dorsoMazo = cargarDorso();
        if (dorsoMazo != null) {
            lblMazo.setIcon(dorsoMazo);
            lblMazo.setText("");
        } else {
            lblMazo.setOpaque(true);
            lblMazo.setBackground(Color.BLACK);
            lblMazo.setForeground(Color.WHITE);
            lblMazo.setFont(new Font("Arial", Font.BOLD, 16));
            lblMazo.setText("MAZO");
        }

        lblCartaMesa = new JLabel();
        lblCartaMesa.setPreferredSize(new Dimension(90, 130));
        lblCartaMesa.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

        panelMesaCentro.add(lblMazo);
        panelMesaCentro.add(lblCartaMesa);

        panelCentralGeneral.add(panelBotIzquierdo, BorderLayout.WEST);
        panelCentralGeneral.add(panelMesaCentro, BorderLayout.CENTER);
        panelCentralGeneral.add(panelBotDerecho, BorderLayout.EAST);

        add(panelCentralGeneral, BorderLayout.CENTER);
    }

    /**
     * Construye el panel inferior asignado para mostrar la mano actual del jugador humano.
     */
    private void crearPanelSur() {
        panelSur = new JPanel();
        panelSur.setPreferredSize(new Dimension(100, 220));
        panelSur.setBackground(new Color(0, 70, 0));

        TitledBorder borde = BorderFactory.createTitledBorder("Tus cartas");
        borde.setTitleColor(Color.WHITE);
        panelSur.setBorder(borde);

        add(panelSur, BorderLayout.SOUTH);
    }

    /**
     * Construye el panel lateral derecho que incluye la bitácora de mensajes (JTextArea con Scroll) 
     * y las acciones disponibles (Robar, Pasar turno, Decir UNO y Salir).
     */
    private void crearPanelEste() {
        panelEste = new JPanel();
        panelEste.setPreferredSize(new Dimension(300, 100));
        panelEste.setBackground(new Color(0, 80, 0));
        panelEste.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        areaMensajes = new JTextArea(10, 22);
        areaMensajes.setEditable(false);
        areaMensajes.setBackground(Color.BLACK);
        areaMensajes.setForeground(new Color(0, 255, 0));
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scroll = new JScrollPane(areaMensajes);
        scroll.setPreferredSize(new Dimension(280, 200));

        btnUno = new JButton("¡UNO!");
        btnUno.setFont(new Font("Arial", Font.BOLD, 18));
        btnUno.setBackground(Color.RED);
        btnUno.setForeground(Color.YELLOW);
        btnUno.setPreferredSize(new Dimension(120, 50));
        btnUno.setVisible(false);
        btnUno.addActionListener(e -> {
            agregarMensaje("¡" + game.getJugadorHumano().getNombre() + " dijo UNO!");
            JOptionPane.showMessageDialog(this, "¡UNO!");
            btnUno.setVisible(false);
        });

        btnRobar = new JButton("Tomar carta");
        btnRobar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRobar.setBackground(Color.ORANGE);
        btnRobar.setPreferredSize(new Dimension(120, 40));
        btnRobar.addActionListener(e -> tomarCarta());

        btnPasarTurno = new JButton("Pasar turno");
        btnPasarTurno.setFont(new Font("Arial", Font.BOLD, 14));
        btnPasarTurno.setBackground(Color.GRAY);
        btnPasarTurno.setForeground(Color.WHITE);
        btnPasarTurno.setPreferredSize(new Dimension(120, 40));
        btnPasarTurno.addActionListener(e -> pasarTurno());

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setBackground(Color.RED);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setPreferredSize(new Dimension(120, 40));
        btnSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                dispose();
                new VentanaInicio();
            }
        });

        panelEste.add(scroll);
        panelEste.add(btnRobar);
        panelEste.add(btnPasarTurno);
        panelEste.add(btnUno);
        panelEste.add(btnSalir);

        add(panelEste, BorderLayout.EAST);
    }

    /**
     * Realiza la acción de pasar el turno actual si le corresponde al jugador humano,
     * delegando el control de juego al siguiente participante.
     */
    private void pasarTurno() {
        int turnoActual = game.getTurnManager().getTurnoActual();
        if (!game.getPlayers().get(turnoActual).esHumano()) return;

        Player jugador = game.getJugadorHumano();
        agregarMensaje(jugador.getNombre() + " pasó el turno");
        game.getTurnManager().siguienteTurno(game.getPlayers().size());
        
        actualizarMano();
        actualizarBotsVisual();
        anunciarTurno();
        iniciarBots();
    }

    /**
     * Actualiza el componente gráfico de la carta del centro de la mesa.
     * Si cuenta con una imagen asociada la despliega; en su defecto, muestra el texto descriptivo.
     */
    private void actualizarCartaMesa() {
        Card cartaMesa = game.getCartaMesa();
        if (cartaMesa != null && cartaMesa.getImagen() != null) {
            lblCartaMesa.setIcon(cartaMesa.getImagen());
            lblCartaMesa.setText("");
        } else if (cartaMesa != null) {
            lblCartaMesa.setText(cartaMesa.toString());
            lblCartaMesa.setForeground(Color.WHITE);
            lblCartaMesa.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    /**
     * Re-renderiza las cartas que posee el jugador humano en el panel sur.
     * Utiliza una disposición de FlowLayout encabalgada para simular un efecto de abanico.
     */
    private void actualizarMano() {
        panelSur.removeAll();
        panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, -25, 20)); 
        
        Player jugador = game.getJugadorHumano();
        ArrayList<Card> cartas = jugador.getMano().getCartas();

        for (int i = 0; i < cartas.size(); i++) {
            final Card carta = cartas.get(i);
            final int indice = i;

            JButton btnCarta = new JButton();
            btnCarta.setPreferredSize(new Dimension(80, 120));
            btnCarta.setFocusPainted(false);
            btnCarta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            if (carta.getImagen() != null) {
                btnCarta.setIcon(carta.getImagen());
                btnCarta.setText("");
            } else {
                btnCarta.setText(carta.toString());
                btnCarta.setForeground(Color.BLACK);
                btnCarta.setBackground(Color.WHITE);
            }

            btnCarta.setToolTipText(carta.toString());
            btnCarta.addActionListener(e -> jugarCartaHumano(indice, carta));
            panelSur.add(btnCarta);
        }

        panelSur.revalidate();
        panelSur.repaint();
    }

    /**
     * Procesa el intento del jugador humano por colocar una carta en juego.
     * Valida las reglas y, si es legítima, aplica sus efectos, verifica si ganó o si 
     * le resta una carta para habilitar el botón de ¡UNO!.
     * * @param indice Posición de la carta dentro de la mano del jugador.
     * @param carta Objeto Card seleccionado para ser jugado.
     */
    private void jugarCartaHumano(int indice, Card carta) {
        int turnoActual = game.getTurnManager().getTurnoActual();
        if (!game.getPlayers().get(turnoActual).esHumano()) return;

        Player jugador = game.getJugadorHumano();
        Card cartaMesa = game.getCartaMesa();

        if (!game.getRuleEngine().esJugadaValida(carta, cartaMesa)) {
            JOptionPane.showMessageDialog(this, "No puedes jugar esa carta", "Jugada inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Card cartaJugada = jugador.jugarCarta(indice);
        game.getDiscardPile().agregarCarta(cartaJugada);
        
        if (cartaJugada.getImagen() != null) {
            lblCartaMesa.setIcon(cartaJugada.getImagen());
            lblCartaMesa.setText("");
        } else {
            lblCartaMesa.setText(cartaJugada.toString());
        }

        agregarMensaje(jugador.getNombre() + " jugó: " + cartaJugada);

        if (cartaJugada.getTipo() == Card.Tipo.COMODIN || cartaJugada.getTipo() == Card.Tipo.ROBA4) {
            mostrarDialogoColor(cartaJugada);
            actualizarCartaMesa();
        }

        game.getRuleEngine().aplicarEfecto(cartaJugada, game, jugador);

        if (jugador.getMano().estaVacia()) {
            agregarMensaje("¡" + jugador.getNombre() + " GANÓ el juego!");
            JOptionPane.showMessageDialog(this, "¡Felicidades! Ganaste el juego.");
            int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres jugar otra partida?", "UNO", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                dispose();
                new VentanaInicio();
            } else {
                System.exit(0);
            }
            return;
        }

        btnUno.setVisible(jugador.getMano().getCartas().size() == 1);
        
        actualizarMano();
        actualizarBotsVisual();
        anunciarTurno();
        iniciarBots();
    }

    /**
     * Ejecuta el robo de una carta desde el mazo principal hacia la mano del jugador humano.
     */
    private void tomarCarta() {
        int turnoActual = game.getTurnManager().getTurnoActual();
        if (!game.getPlayers().get(turnoActual).esHumano()) return;

        Player jugador = game.getJugadorHumano();
        verificarMazo();
        Card cartaRobada = game.getDeck().robarCarta();
        jugador.getMano().agregarCarta(cartaRobada);
        
        agregarMensaje(jugador.getNombre() + " robó una carta");
        
        actualizarMano();
    }

    /**
     * Muestra un cuadro de diálogo emergente para que el usuario escoja un color 
     * tras jugar una carta de tipo comodín.
     * * @param cartaComodin La carta comodín modificada con el nuevo color.
     */
    private void mostrarDialogoColor(Card cartaComodin) {
        String[] colores = {"rojo", "azul", "verde", "amarillo"};
        String[] coloresMostrar = {"Rojo", "Azul", "Verde", "Amarillo"};
        
        String colorSeleccionado = (String) JOptionPane.showInputDialog(
            this,
            "Elige un color para el comodín:",
            "Cambiar color",
            JOptionPane.QUESTION_MESSAGE,
            null,
            coloresMostrar,
            coloresMostrar[0]
        );
        
        if (colorSeleccionado != null) {
            String colorIngles = "";
            switch (colorSeleccionado) {
                case "Rojo": colorIngles = "rojo"; break;
                case "Azul": colorIngles = "azul"; break;
                case "Verde": colorIngles = "verde"; break;
                case "Amarillo": colorIngles = "amarillo"; break;
            }
            cartaComodin.setColor(colorIngles);
            agregarMensaje("Color cambiado a " + colorSeleccionado);
        }
    }

    /**
     * Sincroniza y actualiza la representación visual de todos los bots (Norte, Izquierdo, Derecho)
     * e indica en el título de la ventana de quién es el turno de juego.
     */
    private void actualizarBotsVisual() {
        actualizarPanelNorte();
        actualizarPanelIzquierdo();
        actualizarPanelDerecho();
        
        int turnoActual = game.getTurnManager().getTurnoActual();
        Player jugadorTurno = game.getPlayers().get(turnoActual);
        setTitle("UNO - PARTIDA - Turno de: " + jugadorTurno.getNombre());
    }
    
    /**
     * Actualiza gráficamente el panel norte que corresponde a la mano oculta del bot "PEPE".
     */
    private void actualizarPanelNorte() {
        panelNorte.removeAll();
        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, -40, 15)); 
        Player bot = game.getPlayers().get(2);
        
        TitledBorder borde = BorderFactory.createTitledBorder(bot.getNombre());
        borde.setTitleColor(Color.WHITE);
        panelNorte.setBorder(borde);
        
        ImageIcon dorso = cargarDorso();
        for(int i = 0; i < bot.getMano().size(); i++) {
            JLabel lblBot = new JLabel();
            lblBot.setPreferredSize(new Dimension(80, 120));
            lblBot.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            if (dorso != null) {
                lblBot.setIcon(dorso);
            } else {
                lblBot.setOpaque(true);
                lblBot.setBackground(Color.DARK_GRAY);
            }
            panelNorte.add(lblBot);
        }
        
        panelNorte.revalidate();
        panelNorte.repaint();
    }
    
    /**
     * Actualiza gráficamente el panel izquierdo que corresponde a la mano oculta del bot "TONA".
     */
    private void actualizarPanelIzquierdo() {
        panelBotIzquierdo.removeAll();
        panelBotIzquierdo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -90)); 
        Player bot = game.getPlayers().get(1);
        
        TitledBorder bordeIzq = BorderFactory.createTitledBorder(bot.getNombre());
        bordeIzq.setTitleColor(Color.WHITE);
        panelBotIzquierdo.setBorder(bordeIzq);
        
        ImageIcon dorso = cargarDorso();
        for(int i = 0; i < bot.getMano().size(); i++) {
            JLabel lblBot = new JLabel();
            lblBot.setPreferredSize(new Dimension(80, 120));
            lblBot.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            if (dorso != null) {
                lblBot.setIcon(dorso);
            } else {
                lblBot.setOpaque(true);
                lblBot.setBackground(Color.DARK_GRAY);
            }
            panelBotIzquierdo.add(lblBot);
        }
        
        panelBotIzquierdo.revalidate();
        panelBotIzquierdo.repaint();
    }
    
    /**
     * Actualiza gráficamente el panel derecho que corresponde a la mano oculta del bot "MARI".
     */
    private void actualizarPanelDerecho() {
        panelBotDerecho.removeAll();
        panelBotDerecho.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -90)); 
        Player bot = game.getPlayers().get(3);
        
        TitledBorder bordeDer = BorderFactory.createTitledBorder(bot.getNombre());
        bordeDer.setTitleColor(Color.WHITE);
        panelBotDerecho.setBorder(bordeDer);
        
        ImageIcon dorso = cargarDorso();
        for(int i = 0; i < bot.getMano().size(); i++) {
            JLabel lblBot = new JLabel();
            lblBot.setPreferredSize(new Dimension(80, 120));
            lblBot.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            if (dorso != null) {
                lblBot.setIcon(dorso);
            } else {
                lblBot.setOpaque(true);
                lblBot.setBackground(Color.DARK_GRAY);
            }
            panelBotDerecho.add(lblBot);
        }
        
        panelBotDerecho.revalidate();
        panelBotDerecho.repaint();
    }

    /**
     * Controla la inteligencia artificial y el turno automatizado del bot activo actual.
     * <p>
     * Utiliza un objeto {@link javax.swing.Timer} de un solo pulso para retardar la jugada
     * por 1.5 segundos, permitiendo simular el tiempo de decisión del bot. Si posee una jugada
     * válida la efectúa, si no, roba una carta y pasa de turno.
     * </p>
     */
    private void jugarTurnoBot() {
        Timer timer = new Timer(1500, e -> {
            int turnoActual = game.getTurnManager().getTurnoActual();
            Player jugadorActual = game.getPlayers().get(turnoActual);
            
            if (jugadorActual.esHumano()) {
                return;
            }
            
            ArrayList<Card> cartas = jugadorActual.getMano().getCartas();
            for (int i = 0; i < cartas.size(); i++) {
                Card carta = cartas.get(i);
                if (game.getRuleEngine().esJugadaValida(carta, game.getCartaMesa())) {
                    Card jugada = jugadorActual.jugarCarta(i);
                    game.getDiscardPile().agregarCarta(jugada);
                    
                    if (jugada.getImagen() != null) {
                        lblCartaMesa.setIcon(jugada.getImagen());
                        lblCartaMesa.setText("");
                    } else {
                        lblCartaMesa.setText(jugada.toString());
                    }
                    
                    agregarMensaje(jugadorActual.getNombre() + " jugó: " + jugada);
                    
                    if (jugadorActual.getMano().size() == 1) {
                        agregarMensaje("¡" + jugadorActual.getNombre() + " dijo UNO!");
                    }
                    
                    if (jugada.getTipo() == Card.Tipo.COMODIN || jugada.getTipo() == Card.Tipo.ROBA4) {
                        String[] colores = {"rojo", "azul", "verde", "amarillo"};
                        String colorAleatorio = colores[(int)(Math.random() * 4)];
                        jugada.setColor(colorAleatorio);
                        agregarMensaje(jugadorActual.getNombre() + " cambió a " + colorAleatorio);
                        actualizarCartaMesa();
                    }
                    
                    game.getRuleEngine().aplicarEfecto(jugada, game, jugadorActual);
                    
                    if (jugadorActual.getMano().estaVacia()) {
                        agregarMensaje("¡" + jugadorActual.getNombre() + " GANÓ!");
                        JOptionPane.showMessageDialog(this, jugadorActual.getNombre() + " ha ganado!");
                        int opcion = JOptionPane.showConfirmDialog(this, "¿Jugar otra partida?", "UNO", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION) {
                            dispose();
                            new VentanaInicio();
                        } else {
                            System.exit(0);
                        }
                        ((Timer)e.getSource()).stop();
                        return;
                    }
                    
                    actualizarMano();
                    actualizarBotsVisual();
                    anunciarTurno();
                    iniciarBots();
                    return;
                }
            }
            
            verificarMazo();
            Card robada = game.getDeck().robarCarta();
            jugadorActual.getMano().agregarCarta(robada);
            agregarMensaje(jugadorActual.getNombre() + " robó una carta");
            
            game.getTurnManager().siguienteTurno(game.getPlayers().size());
            actualizarMano();
            actualizarBotsVisual();
            anunciarTurno();
            iniciarBots();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Verifica si el turno actual pertenece a un bot y, de ser así, 
     * dispara la ejecución automatizada de la IA.
     */
    private void iniciarBots() {
        if (!game.getPlayers().get(game.getTurnManager().getTurnoActual()).esHumano()) {
            jugarTurnoBot();
        }
    }

    /**
     * Examina el estado del mazo de robo. Si no quedan elementos, procede a recargar las 
     * cartas de la pila de descartes de vuelta al mazo para continuar la partida.
     */
    private void verificarMazo() {
        if (game.getDeck().estaVacio()) {
            agregarMensaje("⚠️ El mazo se vació, se reinicia...");
            game.getDeck().reiniciar();
            agregarMensaje("✅ Mazo reiniciado y barajado");
        }
    }
    
    /**
     * Intenta recuperar y reescalar la imagen del reverso/dorso de las cartas UNO
     * desde el directorio de recursos del proyecto.
     * * @return El objeto ImageIcon escalado si se encuentra el archivo; de lo contrario null.
     */
    private ImageIcon cargarDorso() {
        try {
            java.net.URL url = getClass().getResource("/img/atras.png");
            if (url == null) {
                url = getClass().getResource("/img/dorso.png");
            }
            if (url == null) {
                url = getClass().getResource("/img/back.png");
            }
            if (url != null) {
                ImageIcon original = new ImageIcon(url);
                Image img = original.getImage().getScaledInstance(90, 130, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        } catch (Exception e) {
            System.out.println("Error cargando dorso: " + e.getMessage());
        }
        return null;
    }

    /**
     * Imprime un encabezado en la consola gráfica notificando de quién es el turno en curso.
     */
    private void anunciarTurno() {
        int turnoActual = game.getTurnManager().getTurnoActual();
        Player jugadorTurno = game.getPlayers().get(turnoActual);
        agregarMensaje("\n▶️ --- Turno de " + jugadorTurno.getNombre() + " ---");
    }

    /**
     * Concatena texto al final de la bitácora de mensajes y auto-desplaza el Scroll 
     * para asegurar que el último evento sea siempre visible.
     * * @param mensaje Cadena de caracteres que se añadirá al log de juego.
     */
    private void agregarMensaje(String mensaje) {
        areaMensajes.append(mensaje + "\n");
        areaMensajes.setCaretPosition(areaMensajes.getDocument().getLength());
    }
}