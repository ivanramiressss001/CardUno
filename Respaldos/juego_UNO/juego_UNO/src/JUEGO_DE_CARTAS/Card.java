package JUEGO_DE_CARTAS;

public class Card {
 
	private String color;
	private int numero;
	
	public Card(String color, int numero) {
		this.color = color;
		this.numero = numero;
	}
	
	public boolean esJugableSobre(Card otra) {
		if (otra == null) return true;
		return this.color.equals(otra.color) || this.numero == otra.numero;
	}
	
	@Override
	public String toString() {
		return color + " " + numero;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getNumero() {
		return numero;
	}
}