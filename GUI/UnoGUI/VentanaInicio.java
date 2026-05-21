package UnoGUI;

import unov5.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Ventana de bienvenida e inicio para el juego UNO.
 * <p>
 * Esta clase proporciona una interfaz gráfica simple que permite al usuario
 * ingresar su nombre para comenzar una nueva partida o salir de la aplicación.
 * </p>
 * * @author TuNombre
 * @version 1.0
 * @see VentanaJuego
 */
public class VentanaInicio extends JFrame {

    /**
     * Constructor de la clase VentanaInicio.
     * Configura el tamaño, título, layout y los componentes visuales (botones y etiquetas)
     * del menú de inicio, además de asignar sus respectivos escuchadores de eventos (listeners).
     */
    public VentanaInicio() {
        setTitle("UNO - Juego de Cartas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(null);

        JLabel titulo = new JLabel("UNO");
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(240, 50, 200, 50);

        JButton btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setBounds(200, 150, 200, 50);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 290, 200, 50);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 18));

        // Evento para solicitar el nombre e iniciar la ventana de juego
        btnIniciar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(null, "Ingresar tu nombre:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                new VentanaJuego(nombre);
                dispose();
            }
        });

        // Evento para cerrar la aplicación
        btnSalir.addActionListener(e -> System.exit(0));

        panel.add(titulo);
        panel.add(btnIniciar);
        panel.add(btnSalir);

        add(panel);
        setVisible(true);
    }
}