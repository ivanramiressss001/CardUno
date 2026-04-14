package JUEGO_DE_CARTAS;

import java.util.ArrayList;
import java.util.Collections;


/** 
 * Representa la baraja basica de UNO (sin cartas especiales)
 * */

public class Deck{
	
	private ArrayList<Card>cartas;
	

	public Deck() {
		cartas = new ArrayList<>();
		crearBaraja();
		barajar();
	
	}
/**
 * Crea baraja básica:
 * 4 colores, números 0-9, dos de cada número
 */
	private void crearBaraja() {
    
		String[] colores = {"rojo", "azul", "verde", "amarillo"};
    
		for (String color : colores) {
			for (int i = 0; i <= 9; i++) {
				cartas.add(new Card(color, i));
				cartas.add(new Card(color, i)); // dos de cada

			}
		}
	}

	public void barajar() {
    Collections.shuffle(cartas);
	}

	public Card robarCarta() {
    if (cartas.isEmpty()) return null;
    return cartas.remove(0);
	}

	public int cartasRestantes() {
    return cartas.size();
	}
	
	
}
