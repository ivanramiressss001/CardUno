CardUno
Proyecto echo en Java para la materia de Construccion y Evolucion de Software

Evolucion del proyecto

- Version 1
  Implementación básica del juego con funcionalidades simples

- Version 2
  Se agregaron cartas especiales como reversa, salto, roba2, roba 4 y comodin.

- Version 3
  Se realizo una refactorizacion del codigo del codigo, agregando:
   - Soporte para 4 jugadore
   - Sistema de turnos dinamicos
   - Mejor organizacion del codigo
   - Base para la inteligencia artificial basica

- Version 4
  - Documentación Técnica Profesional: Implementación integral del estándar Javadoc en todas las clases y métodos del sistema, eliminando las 34 advertencias de compilación previas
  - Cero Deuda Técnica: Refactorización de constructores y firmas de métodos para garantizar una arquitectura limpia y comprensible para futuros desarrolladores
  - Suite de Pruebas Unitarias Robusta: Documentación y categorización de pruebas en PruebasUno.java, cubriendo casos de Caja Negra, Lógica de Decisión y manejo de Excepciones
 

Estructura del repositorio

- Eclipse -----> Archivos de entorno de desarrollo
- JUEGO_DE_CARTAS_VERS2 --------> Codigo de la version 2
- JUEGO_DE_CARTAS_VERS3 --------> Codigo de la version 3
- JUEGO_DE_CARTAS_VERS4 --------> src/JUEGO_CARTAS_VERS4: Contiene el código fuente con la lógica del motor de juego y las entidades (Cartas, Jugadores, Reglas).
       JUEGO_DE_CARTAS_VERS4-----------------> Test/TestCompleto: Incluye PruebasUno.java con las validaciones de JUnit que aseguran que el juego sea estable y libre de errores lógicos
             JUEGO_DE_CARTAS_VERS4-------------> doc/: Carpeta autogenerada que contiene la API técnica del proyecto. Para visualizarla, abre el archivo index.html en cualquier navegador

  Caracteristicas
  - Juego de consola
  - Uso de programacion orientada a objetos
  - Implementacion de reglas del UNO
  - Codigo estructurado y mantenible
  Version4
  - Motor de Reglas Independiente: Separación de la lógica de validación mediante la clase RuleEngine, permitiendo un mantenimiento más sencillo de las reglas especiales
  - Gestor de Turnos Bidireccional: Control avanzado de la dirección del juego mediante TurnManager, permitiendo el funcionamiento correcto de la carta Reversa en partidas de 2 a 4 jugadores
  IA de Oponentes: Implementación de lógica automática para bots, permitiendo partidas 100% funcionales contra la computadora
  - Manejo de Estados: Gestión visual y lógica del estado de la mesa, el mazo y la pila de descarte (DiscardPile)
  - Mantenibilidad y Calidad
  - Pruebas: Ejecuta PruebasUno.java como JUnit Test para verificar la integridad del código
  - Documentación: Abre /doc/index.html para consultar la documentación técnica completa del proyecto
 

Diagrama UML

- v2
<img width="1140" height="1097" alt="image" src="https://github.com/user-attachments/assets/82a681c6-e336-4321-be0c-35d9d40b3bad" />



- v3
![Diagrama](https://github.com/user-attachments/assets/7192b1cb-340f-4a19-96d9-f77266b87ecf)



- v4
<img width="1730" height="1789" alt="UML_VERS4" src="https://github.com/user-attachments/assets/d5d22fff-1d4b-44ff-baf6-86356bf464ba" />




 
  
  
  
  
  
  Integrantes

   - Aguilar Domínguez Leslie Joseline  
   - García Quintero Ángel Antonio  
   - Pérez Copado Carmen Andrea  
   - Ramírez Gallardo Iván Enrique
