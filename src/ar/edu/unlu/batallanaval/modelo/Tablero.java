package ar.edu.unlu.batallanaval.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tablero implements Serializable {
    private static final int TAMANIO_DEFAULT = 10;

    private final int tamanio;
    private final Casillero[][] grilla;
    private final List<Barco> barcos;

    public Tablero() {
        this.tamanio = TAMANIO_DEFAULT;
        this.grilla = new Casillero[this.tamanio][this.tamanio];
        this.barcos = new ArrayList<>();
        inicializarGrilla();
    }

    private void inicializarGrilla() {
        for (int fila = 0; fila < this.tamanio; fila++) {
            for (int col = 0; col < this.tamanio; col++) {
                this.grilla[fila][col] = new Casillero(new Coordenada(fila, col));
            }
        }
    }

    public boolean colocarBarco(Barco barco, Coordenada inicio, Orientacion orientacion) {
        if (barco == null || inicio == null || orientacion == null) {
            return false;
        }

        int filaInicial = inicio.getFila();
        int colInicial = inicio.getColumna();
        int longitud = barco.getLongitud();


        for (int i = 0; i < longitud; i++) {
            int fila = filaInicial;
            int col = colInicial;

            if (orientacion == Orientacion.VERTICAL) {
                fila = filaInicial + 1;
            } else {
                col = colInicial + 1;
            }

            if (!esCoordenadaValida(fila, col) || this.grilla[fila][col].tieneBarco()) {
                return false;
            }
        }

        for (int i = 0; i < longitud; i++) {
            int fila = filaInicial;
            int col = colInicial;

            if (orientacion == Orientacion.VERTICAL) {
                fila = filaInicial + i;
            } else {
                col = colInicial + i;
            }

            this.grilla[fila][col].asignarBarco(barco);
        }

        this.barcos.add(barco);
        return true;
    }

    public ResultadoDisparo recibirDisparo(Coordenada coordenada) {
        validarCoordenada(coordenada);
        return this.grilla[coordenada.getFila()][coordenada.getColumna()].recibirDisparo();
    }

    public void registrarDisparo(Coordenada coordenada, ResultadoDisparo resultado) {
        validarCoordenada(coordenada);
        this.grilla[coordenada.getFila()][coordenada.getColumna()].recibirDisparo();
    }

    public boolean todosHundidos() {
        if (this.barcos.isEmpty()) {
            return false;
        }

        for (Barco barco : this.barcos) {
            if (!barco.estaHundido()) {
                return false;
            }
        }

        return true;
    }

    public Casillero getCasillero(Coordenada coordenada) {
        validarCoordenada(coordenada);
        return this.grilla[coordenada.getFila()][coordenada.getColumna()];
    }

    public int getTamanio() {
        return this.tamanio;
    }

    public boolean esCoordenadaValida(Coordenada coordenada) {
        return coordenada != null
                && esCoordenadaValida(coordenada.getFila(), coordenada.getColumna());
    }

    private boolean esCoordenadaValida(int fila, int col) {
        return fila >= 0
                && fila < this.tamanio
                && col >= 0
                && col < this.tamanio;
    }

    private void validarCoordenada(Coordenada coordenada) {
        if (!esCoordenadaValida(coordenada)) {
            throw new IllegalArgumentException("Coordenada fuera de los límites del tablero.");
        }
    }
}
