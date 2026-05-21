CardUno
Proyecto hecho en Java para la materia de Construcción y Evolución de Software.

Evolución del proyecto
Versión 1: Implementación básica del juego con funcionalidades simples.

Versión 2: Se agregaron cartas especiales como reversa, salto, roba2, roba 4 y comodín.

Versión 3: Se realizó una refactorización del código, agregando:

Soporte para 4 jugadores.

Sistema de turnos dinámicos.

Mejor organización del código.

Base para la inteligencia artificial básica.

Versión 4: * Documentación Técnica Profesional: Implementación integral del estándar Javadoc en todas las clases y métodos del sistema, eliminando las advertencias de compilación previas.

Cero Deuda Técnica: Refactorización de constructores y firmas de métodos para garantizar una arquitectura limpia y comprensible para futuros desarrolladores.

Suite de Pruebas Unitarias Robusta: Documentación y categorización de pruebas en PruebasUno.java, cubriendo casos de Caja Negra, Lógica de Decisión y manejo de Excepciones.

Versión 5 (Actual - GUI): * Interfaz Gráfica de Usuario (GUI): Migración completa de consola a un entorno visual interactivo utilizando Java Swing.

Gestión Visual de Cartas: Implementación de renderizado de cartas mediante imágenes (.png) con un sistema de respaldo de dibujo en 2D (crearImagenTexto()) en caso de falta de recursos visuales.

Efectos y Diseño: Visualización de la mano del jugador y bots mediante un "Efecto Abanico" utilizando márgenes dinámicos.

IA Mejorada y Segura: Los bots interactúan en la interfaz gráfica con retrasos programados (Timer) para simular el tiempo de pensamiento, con bloqueos de seguridad que evitan que el usuario interrumpa los turnos de la computadora.

Consola de Eventos en Tiempo Real: Integración de un área de mensajes (JTextArea) que narra las jugadas, cambios de color y robos de cartas en vivo.

Estructura del repositorio
Eclipse -----> Archivos de entorno de desarrollo

JUEGO_DE_CARTAS_VERS2 --------> Código de la versión 2

JUEGO_DE_CARTAS_VERS3 --------> Código de la versión 3

JUEGO_DE_CARTAS_VERS4 --------> Contiene el código fuente con la lógica del motor de juego por consola y las entidades (Cartas, Jugadores, Reglas).

CONTRUCCION_Y_EVOLUCION (Versión GUI):

src/unov5/ -----> Motor lógico del juego (Reglas, Turnos, Mazo, Jugadores).

src/UnoGUI/ -----> Clases encargadas de la interfaz visual (VentanaInicio, VentanaJuego, Main).

src/img/ -----> Recursos gráficos (imágenes de las cartas y dorso).

Test/TestGUI/ -----> Suite de pruebas unitarias (VentanaInicioTest.java, VentanaJuegoTest.java) con validación de ciclos de vida de las ventanas (JUnit 5).

Doc/ -----> Carpeta autogenerada que contiene la API técnica completa del proyecto (Motor lógico y GUI).

Características
Entorno dual: Código escalable probado en consola y ahora completamente jugable mediante Interfaz Gráfica.

Uso estricto de Programación Orientada a Objetos (POO).

Implementación fiel de las reglas del UNO.

Motor de Reglas Independiente: Separación de la lógica de validación mediante la clase RuleEngine, permitiendo un mantenimiento más sencillo de las reglas especiales.

Gestor de Turnos Bidireccional: Control avanzado de la dirección del juego mediante TurnManager, permitiendo el funcionamiento correcto de la carta Reversa en partidas de 2 a 4 jugadores.

IA de Oponentes: Implementación de lógica automática para 3 bots, permitiendo partidas 100% funcionales contra la computadora.

Manejo de Estados: Gestión visual y lógica del estado de la mesa, el mazo y la pila de descarte (DiscardPile).

Mantenibilidad, Calidad y Ejecución
Pruebas Unitarias: Ejecuta los archivos dentro de la carpeta Test/ como JUnit Test para verificar la integridad de la lógica matemática y la instanciación de la Interfaz Gráfica.

Documentación Javadoc: Abre Doc/index.html en cualquier navegador web para consultar la documentación técnica completa del proyecto.

Ejecución del Juego: El proyecto cuenta con una versión compilada. Simplemente descarga y ejecuta el archivo UNO.jar con doble clic para iniciar la partida sin necesidad de abrir un entorno de desarrollo.
 

Diagrama UML

- v2
<img width="1140" height="1097" alt="image" src="https://github.com/user-attachments/assets/82a681c6-e336-4321-be0c-35d9d40b3bad" />



- v3
![Diagrama](https://github.com/user-attachments/assets/7192b1cb-340f-4a19-96d9-f77266b87ecf)



- v4
<img width="1730" height="1789" alt="UML_VERS4" src="https://github.com/user-attachments/assets/d5d22fff-1d4b-44ff-baf6-86356bf464ba" />




-Diamgrama GUI
<img width="5847" height="9927" alt="DiagramaUMLvGUI" src="https://github.com/user-attachments/assets/9f1aadd4-13a7-499e-ab2b-48e566b2e4cc" />


 
  
  
  
  
  
  Integrantes

   - Aguilar Domínguez Leslie Joseline  
   - Pérez Copado Carmen Andrea  
   - Ramírez Gallardo Iván Enrique
 
   
