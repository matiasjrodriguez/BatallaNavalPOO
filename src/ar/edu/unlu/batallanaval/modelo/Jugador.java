package ar.edu.unlu.batallanaval.modelo;

import java.io.Serializable;

public abstract class Jugador implements Serializable {
    private final String nombre;
    private final Tablero tableroPropio;
    private final Tablero tableroDisparo;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tableroPropio = new Tablero();
        this.tableroDisparo = new Tablero();
    }

    public boolean colocarBarco(Barco barco, Coordenada inicio, Orientacion orientacion) {
        return this.tableroPropio.colocarBarco(barco, inicio, orientacion);
    }

    public ResultadoDisparo recibirDisparo(Coordenada coordenada) {
        return this.tableroPropio.recibirDisparo(coordenada);
    }

    public void registrarDisparo(Coordenada coordenada, ResultadoDisparo resultado) {
        this.tableroDisparo.registrarDisparo(coordenada, resultado);
    }

    public boolean perdio() {
        return this.tableroPropio.todosHundidos();
    }

    public abstract void prepararFlota();

    public String getNombre() {
        return this.nombre;
    }

    public Tablero getTableroPropio() {
        return this.tableroPropio;
    }

    public Tablero getTableroDisparo() {
        return this.tableroDisparo;
    }

}
