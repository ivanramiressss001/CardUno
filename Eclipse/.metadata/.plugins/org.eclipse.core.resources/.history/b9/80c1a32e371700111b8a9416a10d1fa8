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
				System.out.println("\n(⌐■_■) ¡Ganaste FELICIDADES!");
				break;
			}
			
			turnoComputadora();
			if (computadora.estaVacia()) {
				System.out.println("\n💻 La computadora ganó .");
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
		    Card nueva = deck.robarCarta();

		    if (nueva != null) {
		        jugador.agregarCarta(nueva);
		        System.out.println("Robaste: " + nueva);
		    } else {
		        System.out.println("No hay más cartas en el mazo.");
		    }
		    return;
		}

		Card cartaElegida = jugador.jugarCarta(opcion);

		if (cartaElegida == null) {
		    System.out.println("Índice inválido.");
		    return;
		}

		if (cartaElegida.esJugableSobre(cartaMesa)) {
		    cartaMesa = cartaElegida;
		    System.out.println("Jugaste: " + cartaMesa);
		} else {
		    System.out.println("Movimiento inválido.");
		    jugador.agregarCarta(cartaElegida); // solo si NO es null
		}
	}
	
	private void turnoComputadora() {
	    System.out.println("\n--- Turno computadora ---");

	    System.out.println("Cartas de la computadora:");
	    computadora.mostrarMano();  // 👈 aquí se muestran

	    for (int i = 0; i < computadora.size(); i++) {
	        Card carta = computadora.jugarCarta(i);

	        if (carta != null && carta.esJugableSobre(cartaMesa)) {
	            cartaMesa = carta;
	            System.out.println("La computadora jugó: " + cartaMesa);
	            return;
	        } else {
	            computadora.agregarCarta(carta);
	        }
	    }

	    Card nueva = deck.robarCarta();
	    if (nueva != null) {
	        computadora.agregarCarta(nueva);
	        System.out.println("La computadora roba una carta.");
	    }
	}
}
