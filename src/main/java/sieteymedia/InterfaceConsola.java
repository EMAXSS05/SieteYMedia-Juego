package sieteymedia;

import java.util.Scanner;

public class InterfaceConsola {
    private Scanner sc = new Scanner(System.in);
    private SieteYMedia juego;

    public InterfaceConsola() {
        this.juego = new SieteYMedia(this);
    }


    public void iniciarJuego() {
        juego.iniciar();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public char pedirOpcion() {
        System.out.println("\n¿Pides [C]arta o te [P]lantas?");
        return sc.next().trim().toUpperCase().charAt(0);
    }

    public void mostrarCartasJugador(String cartas) {
        System.out.println("Estas son tus cartas " + cartas);
    }

    public void mostrarCartasBanca(String cartas) {
        System.out.println("Estas son las cartas de la banca:\n " + cartas);
    }

    public void mostrarResultado(String resultado) {
        System.out.println(resultado);
    }
    public static void main(String[] args) {
        InterfaceConsola consola = new InterfaceConsola();
        consola.iniciarJuego();
    }
}
