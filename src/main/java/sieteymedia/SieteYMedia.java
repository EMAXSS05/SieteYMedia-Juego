package sieteymedia;

import recursos.Baraja;
import recursos.Carta;

public class SieteYMedia {
    private Baraja baraja;
    private Carta[] cartasJugador;
    private Carta[] cartasBanca;
    private InterfaceConsola consola;

    public SieteYMedia(InterfaceConsola consola) {
        this.consola = consola;
        this.baraja = new Baraja();
        this.baraja.barajar();
        this.cartasJugador = new Carta[15];
        this.cartasBanca = new Carta[15];
    }

    public void iniciar(){
        consola.mostrarMensaje("- El usuario es el jugador y el ordenador la  banca.\n"
                + "- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.\n"
                + "- Las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.\n"
                + "- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.\n"
                + "- El jugador va pidiendo cartas a la banca de una en una.\n"
                + "- El jugador puede plantarse en cualquier momento.\n"
                + "- Si la suma de los valores de las cartas sacadas es superior "
                + "a 7 y medio, el jugador 'se pasa de siete y medio' y pierde.\n"
                + "- Si el jugador no se pasa, comienza a sacar cartas la banca y ésta está obligada a sacar cartas hasta empatar o superar al jugador.\n"
                + "- Si la banca consigue empatar o superar la puntuación del jugador 'sin pasarse de siete y medio', gana la banca.\n"
                + "- La banca no se puede plantar y tiene que empatar o superar la puntuación del jugador sin pasarse.\n"
                + "- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador.\n"
                + "\nEmpecemos!!!\n");
        turnoJugador();
        turnoBanca();
        consola.mostrarMensaje("Adios!");
    }

    private void turnoJugador() {
        char opcion = 'C';
        consola.mostrarMensaje("Como minimo recibes una carta, luego puedes decidir si seguir o plantarte");

        int indiceJugador = 0;
        while (calcularValor(cartasJugador) < 7.5 && opcion == 'C' && indiceJugador < 15) {
            Carta carta = baraja.darCartas(1)[0];
            cartasJugador[indiceJugador++] = carta;
            consola.mostrarCartasJugador(getCartasString(cartasJugador));
            double valor = calcularValor(cartasJugador);
            consola.mostrarMensaje("\n\tValor de cartas: " + valor);
            if (calcularValor(cartasJugador) < 7.5) {
                opcion = consola.pedirOpcion();
            }
        }
    }

    private void turnoBanca() {
        if (calcularValor(cartasJugador) > 7.5) {
            consola.mostrarResultado("El jugador se paso, gana la banca.");
            return;
        }

        consola.mostrarMensaje("Turno de la banca...");

        int indiceBanca = 0;
        while (calcularValor(cartasBanca) < calcularValor(cartasJugador) && indiceBanca < 15) {
            Carta carta = baraja.darCartas(1)[0];
            cartasBanca[indiceBanca++] = carta;
        }

        consola.mostrarCartasBanca(getCartasString(cartasBanca));

        if (calcularValor(cartasBanca) > 7.5) {
            consola.mostrarResultado("La banca se paso, gana el jugador.");
        } else if (calcularValor(cartasBanca) > calcularValor(cartasJugador)) {
            consola.mostrarResultado("La banca gana.");
        } else {
            consola.mostrarResultado("El jugador gana.");
        }
    }

    private double calcularValor(Carta[] cartas) {
        double total = 0.0;
        for (Carta carta : cartas) {
            if (carta != null) {
                total += (carta.getNumero() > 7) ? 0.5 : carta.getNumero();
            }
        }
        return total;
    }

    private String getCartasString(Carta[] cartas) {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : cartas) {
            if (carta != null) {
                sb.append(carta.toString()).append(" ");
            }
        }
        return sb.toString();
    }
}
