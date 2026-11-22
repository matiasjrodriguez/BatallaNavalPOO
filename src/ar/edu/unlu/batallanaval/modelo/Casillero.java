package ar.edu.unlu.batallanaval.modelo;

import java.io.Serializable;

public class Casillero implements Serializable {
    private final Coordenada coordenada;
    private Barco barco;
    private boolean disparo;

    public Casillero(Coordenada coordenada) {
        this.coordenada = coordenada;
        this.barco = null;
        this.disparo = false;
    }

    public Coordenada getCoordenada() {
        return this.coordenada;
    }

    public boolean tieneBarco() {
        return this.barco != null;
    }

    public boolean fueDisparado() {
        return this.disparo;
    }

    public void asignarBarco(Barco barco) {
        this.barco = barco;
    }

    public ResultadoDisparo recibirDisparo() {
        if (this.disparo) {
            return ResultadoDisparo.REPETIDO;
        }

        this.disparo = true;

        if (!tieneBarco()) {
            return ResultadoDisparo.AGUA;
        }

        this.barco.recibirImpacto();


        if (barco.estaHundido()) {
            return ResultadoDisparo.HUNDIDO;
        } else {
            return ResultadoDisparo.TOCADO;
        }
    }

    public EstadoCasillero getEstadoVisible(boolean ocultarBarcos) {
        if (!this.disparo) {
            if (tieneBarco() && !ocultarBarcos) {
                return EstadoCasillero.BARCO;
            }
            return EstadoCasillero.SIN_EXPLORAR;
        }

        if (!tieneBarco()) {
            return EstadoCasillero.AGUA;
        }

        return this.barco.estaHundido() ? EstadoCasillero.HUNDIDO : EstadoCasillero.TOCADO;
    }
}
