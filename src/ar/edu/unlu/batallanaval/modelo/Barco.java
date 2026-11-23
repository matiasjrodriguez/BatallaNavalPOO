package ar.edu.unlu.batallanaval.modelo;

import java.io.Serializable;

public class Barco implements Serializable {
    private final TipoBarco tipo;
    private int impactos;

    public Barco(TipoBarco tipo) {
        this.tipo = tipo;
        this.impactos = 0;
    }

    public void recibirImpacto() {
        this.impactos++;
    }

    public boolean estaHundido() {
        return (this.impactos >= tipo.getLongitud());
    }

    public TipoBarco getTipo() {
        return this.tipo;
    }

    public int getLongitud() {
        return this.tipo.getLongitud();
    }
}
