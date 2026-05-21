package unov5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Representa una carta del juego UNO.
 * Una carta puede ser numerica o especial.
 */
public class Card {

    /**
     * Tipos de cartas disponibles en el juego UNO.
     */
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
    private int numero;
    private ImageIcon imagen;

    /**
     * Constructor para carta numerica.
     *
     * @param color color de la carta
     * @param numero valor numerico
     */
    public Card(String color, int numero) {
        this.color = color;
        this.numero = numero;
        this.tipo = Tipo.NUMERO;
        cargarImagen();
    }

    /**
     * Constructor para carta especial.
     *
     * @param color color de la carta
     * @param tipo tipo de carta
     */
    public Card(String color, Tipo tipo) {
        this.color = color;
        this.tipo = tipo;
        this.numero = -1;
        cargarImagen();
    }

    /**
     * Carga automaticamente la imagen correspondiente
     * a la carta adaptada a los nombres exactos de los archivos.
     */
    private void cargarImagen() {
        String ruta = "";

        if (tipo == Tipo.NUMERO) {
            // Carga: rojo_5.png, azul_0.png, etc.
            ruta = "/img/" + color + "_" + numero + ".png";
        } else {
            // Carga las especiales exactamente como están en la carpeta
            switch (tipo) {
                case ROBA2:   ruta = "/img/roba2_" + color + ".png"; break;
                case REVERSA: ruta = "/img/reversa_" + color + ".png"; break;
                case SALTO:   ruta = "/img/salto_" + color + ".png"; break;
                case COMODIN: ruta = "/img/negro_comodin.png"; break;
                case ROBA4:   ruta = "/img/roba4.png"; break;
                default:      ruta = "";
            }
        }

        URL url = getClass().getResource(ruta);
        
        if (url != null) {
            ImageIcon original = new ImageIcon(url);
            Image img = original.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            imagen = new ImageIcon(img);
        } else {
            System.out.println("⚠️ No se encontró la imagen: " + ruta);
            crearImagenTexto();
        }
    }

    // Método para crear imagen de respaldo
    private void crearImagenTexto() {
        BufferedImage img = new BufferedImage(80, 120, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        
        if (color.equals("rojo")) g2d.setColor(Color.RED);
        else if (color.equals("azul")) g2d.setColor(Color.BLUE);
        else if (color.equals("verde")) g2d.setColor(Color.GREEN);
        else if (color.equals("amarillo")) g2d.setColor(Color.YELLOW);
        else g2d.setColor(Color.BLACK);
        
        g2d.fillRect(0, 0, 80, 120);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        
        if (tipo == Tipo.NUMERO) {
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.drawString(String.valueOf(numero), 30, 70);
        } else {
            g2d.drawString(tipo.toString(), 15, 60);
        }
        
        g2d.dispose();
        imagen = new ImageIcon(img);
    }

    /**
     * Obtiene la imagen de la carta.
     *
     * @return imagen
     */
    public ImageIcon getImagen() {
        return imagen;
    }

    /*
     * ====================================================
     * VALIDACION DE REGLAS UNO
     * ====================================================
     */

    /**
     * Verifica si la carta es jugable sobre otra.
     *
     * @param otra carta en mesa
     * @return true si es valida
     */
    public boolean esJugableSobre(Card otra) {

        // COMODIN SIEMPRE VALIDO
        if (this.tipo == Tipo.COMODIN ||
            this.tipo == Tipo.ROBA4) {
            return true;
        }

        // MISMO COLOR
        if (this.color.equals(otra.color)) {
            return true;
        }

        // MISMO TIPO
        if (this.tipo == otra.tipo) {
            return true;
        }

        // MISMO NUMERO
        if (this.numero == otra.numero) {
            return true;
        }

        return false;
    }

    /**
     * Obtiene el color de la carta.
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Cambia el color de la carta.
     *
     * @param color nuevo color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene el tipo de carta.
     *
     * @return tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Obtiene el numero de la carta.
     *
     * @return numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Representacion en texto de la carta.
     *
     * @return texto
     */
    @Override
    public String toString() {
        return tipo == Tipo.NUMERO
                ? color + " " + numero
                : color + " " + tipo;
    }
}