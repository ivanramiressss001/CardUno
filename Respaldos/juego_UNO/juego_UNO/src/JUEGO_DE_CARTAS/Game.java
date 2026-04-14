package JUEGO_DE_CARTAS;

import java.util.Scanner;

public class Game {
	
	private Deck deck;
	private Hand jugador;
	private Hand computadora;
	private Card cartaMesa;
	private Scanner scanner; 
	
	public Game() {
		deck = new Deck();
		jugador = new Hand();
		computadora = new Hand();
		scanner = new Scanner(System.in);
	}
	
	public void iniciar() {
		
		repartirCartas();
		
		// Colocar primera carta en la mesa
		cartaMesa = deck.robarCarta();
		System.out.println("Carta inicial en la mesa: " + cartaMesa);

		// Ciclo del juego
		while (true) {
			
			turnoJugador();
			if (jugador.estaVacia()) {
				System.out.println("🎉 ¡Ganaste!");
				break;
			}
			
			turnoComputadora();
			if (computadora.estaVacia()) {
				System.out.println("💻 La computadora ganó.");
				break;
			}
		}
	}
	
	private void repartirCartas() {
		for (int i = 0; i < 7; i++) {
			jugador.agregarCarta(deck.robarCarta());
			computadora.agregarCarta(deck.robarCarta());
		}
	}
	
	private void turnoJugador() {
		System.out.println("\n--- Tu turno ---");
		System.out.println("Carta en mesa: " + cartaMesa);
		
		jugador.mostrarMano();

		if (!jugador.tieneJugadaValida(cartaMesa)) {
			System.out.println("No tienes jugadas válidas. Robas una carta.");
			jugador.agregarCarta(deck.robarCarta());
			return;
		}

		System.out.print("Elige índice de carta para jugar o -1 para robar: ");
		int opcion = scanner.nextInt();

		if (opcion == -1) {
			jugador.agregarCarta(deck.robarCarta());
			System.out.println("Robaste una carta.");
			return;
		}

		Card cartaElegida = jugador.jugarCarta(opcion);

		if (cartaElegida != null && cartaElegida.esJugableSobre(cartaMesa)) {
			cartaMesa = cartaElegida;
			System.out.println("Jugaste: " + cartaMesa);
		} else {
			System.out.println("Movimiento inválido.");
			// regresamos la carta a la mano
			jugador.agregarCarta(cartaElegida);
		}
	}
	
	private void turnoComputadora() {
		System.out.println("\n--- Turno computadora ---");

		for (int i = 0; i < computadora.size(); i++) {
			
			Card carta = computadora.jugarCarta(i);
			
			if (carta != null && carta.esJugableSobre(cartaMesa)) {
				cartaMesa = carta;
				System.out.println("La computadora jugó: " + cartaMesa);
				return;
			} else {
				// si no sirve, la regresamos
				computadora.agregarCarta(carta);
			}
		}

		// Si no puede jugar, roba
		computadora.agregarCarta(deck.robarCarta());
		System.out.println("La computadora roba una carta.");
	}
}
