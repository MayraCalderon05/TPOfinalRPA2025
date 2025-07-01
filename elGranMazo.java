import java.util.Scanner;

public class elGranMazo {
    public static void main(String[] args) {
        // Declaro variables globales
        float puntajeJugadorUno, puntajeJugadorDos, maxPuntajeJ1, maxPuntajeJ2, puntajeCarta;
        int rondaMaxJ1, rondaMaxJ2, cantidadRondas;
        boolean finalizaJuego;
        String jugadorUno, jugadorDos;

        Scanner sc = new Scanner(System.in);
        // Inicializo variables globales
        puntajeJugadorUno = 0;
        puntajeJugadorDos = 0;
        maxPuntajeJ1 = 0;
        maxPuntajeJ2 = 0;
        rondaMaxJ1 = 0;
        rondaMaxJ2 = 0;
        cantidadRondas = 1;
        finalizaJuego = false;

        System.out.println("Ingrese el nombre del jugador 1:");
        jugadorUno = sc.next();

        System.out.println("Ingrese el nombre del jugador 2:");
        jugadorDos = sc.next();
        // minimamente una ronda debe jugarse
        do {
            // * Muestro el número de ronda */
            System.out.println("Ronda Nº" + cantidadRondas);
            // solo hay dos jugadores en juego
            for (int jugador = 1; jugador < 3; jugador++) {
                // * muestro el jugador que está jugando */
                System.out.println(" Turno del jugador " + jugador + ": " + (jugador == 1 ? jugadorUno : jugadorDos));
                puntajeCarta = 0;
                boolean continuaTurno = true;
                // minimamente un turno debe jugarse
                do {
                    System.out.println(" Ingrese el valor de la carta");
                    float valorCarta = sc.nextFloat();
                    boolean cartaValida = validarCarta(valorCarta);
                    if (cartaValida) {
                        if ((valorCarta == 1 || valorCarta == 12)) {
                            System.out.println("Ha terminado su turno");
                            continuaTurno = false;
                        } else {
                            boolean esPar = cartaPar(valorCarta);
                            puntajeCarta = calcularPuntaje(valorCarta, esPar);
                            System.out.println(" El puntaje de la carta es: " + puntajeCarta);
                            if (jugador == 1) {
                                puntajeJugadorUno += puntajeCarta;
                                if (puntajeCarta > maxPuntajeJ1) {
                                    maxPuntajeJ1 = puntajeCarta;
                                    rondaMaxJ1 = cantidadRondas;
                                }
                                if (puntajeJugadorUno >= 60) {
                                    System.out.println("El juego ha finalizado");
                                    continuaTurno = false;
                                }
                            } else {
                                puntajeJugadorDos += puntajeCarta;
                                if (puntajeCarta > maxPuntajeJ2) {
                                    maxPuntajeJ2 = puntajeCarta;
                                    rondaMaxJ2 = cantidadRondas;
                                }
                                if (puntajeJugadorDos >= 60) {
                                    System.out.println("El juego ha finalizado");
                                    continuaTurno = false;
                                }

                            }
                        }
                        System.out.println(" El puntaje de " + (jugador == 1 ? jugadorUno : jugadorDos) + " es: "
                                + (jugador == 1 ? puntajeJugadorUno : puntajeJugadorDos));
                    } else {
                        System.out.println(
                                "Carta no válida, debe ser un número entre 1 y 12, el puntaje de la carta es cero.");
                        System.out.println("El puntaje de " + (jugador == 1 ? jugadorUno : jugadorDos) + " es: "
                                + (jugador == 1 ? puntajeJugadorUno : puntajeJugadorDos));
                    }
                } while (continuaTurno);
            }
            // ? corroboro si finaliza el juego o no
            if ((puntajeJugadorUno >= 60 || puntajeJugadorDos >= 60)) {
                System.out.println("El juego ha finalizado");
                finalizaJuego = true;
            } else {
                cantidadRondas++;
            }
        } while (!finalizaJuego);
        // evaluo quien es el ganador
        if (puntajeJugadorUno >= 60 && puntajeJugadorDos >= 60) {
            System.out.println("El juego ha finalizado en empate, ambos jugadores han alcanzado 60 puntos o más.");
        } else if (puntajeJugadorUno >= 60) {
            System.out.println("El ganador es " + jugadorUno + " con un puntaje de: " + puntajeJugadorUno);
        } else {
            System.out.println("El ganador es " + jugadorDos + " con un puntaje de: " + puntajeJugadorDos);
        }
        System.out.println(
                "El puntaje máximo de " + jugadorUno + " fue: " + maxPuntajeJ1 + " en la ronda Nº" + rondaMaxJ1);
        System.out.println(
                "El puntaje máximo de " + jugadorDos + " fue: " + maxPuntajeJ2 + " en la ronda Nº" + rondaMaxJ2);
        System.out.println("Cantidad de rondas jugadas: " + cantidadRondas);

        sc.close();
    }

    // ? este modulo valida si la carta esta dentro del rango permitido para poder
    // calcular el puntaje
    public static boolean validarCarta(float valorCarta) {
        boolean cartaValida;
        if (valorCarta >= 1 && valorCarta <= 12) {
            cartaValida = true;
        } else {
            cartaValida = false;
        }
        return cartaValida;
    }

    // ? este modulo valida si la carta es par o impar para despues determinar la
    // forma de calculat el puntaje
    public static boolean cartaPar(float valorCarta) {
        boolean esPar;
        if ((valorCarta % 2) == 0) {
            esPar = true;
        } else {
            esPar = false;
        }
        return esPar;
    }

    // ? este modulo calcula el puntaje de la carta
    public static float calcularPuntaje(float valorCarta, boolean esPar) {
        float puntajeCarta;
        if (esPar) {
            puntajeCarta = valorCarta * 2;
        } else {
            puntajeCarta = valorCarta / 2;
        }
        return puntajeCarta;
    }
}