package ar.edu.unlu.batallanaval.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Coordenada implements Serializable {
    public final int fila;
    public final int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return this.columna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordenada that = (Coordenada) o;

        return this.fila == that.fila && this.columna == that.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.fila, this.columna);
    }
}
