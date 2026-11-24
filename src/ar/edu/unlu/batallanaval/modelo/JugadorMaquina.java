package ar.edu.unlu.batallanaval.modelo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class JugadorMaquina extends Jugador{
    private final Queue<Coordenada> objetivosCercanos;
    private Coordenada ultimoAcierto; //actualmente, no lo uso, lo voy a guardar por si quiero mejorar la ia
    private final Random random;

    public JugadorMaquina(String nombre) {
        super(nombre);
        this.objetivosCercanos = new LinkedList<>();
        this.ultimoAcierto = null;
        this.random = new Random();
    }

    @Override
    public void prepararFlota() {
        int tamanio = getTableroPropio().getTamanio();
        Orientacion[] orientaciones = Orientacion.values();

        for (TipoBarco tipo : TipoBarco.values()) {
            Barco barco = new Barco(tipo);
            boolean colocado = false;

            while (!colocado) {
                int fila = this.random.nextInt(tamanio);
                int col = this.random.nextInt(tamanio);

                Orientacion orientacion = orientaciones[this.random.nextInt(orientaciones.length)];

                colocado = colocarBarco(barco, new Coordenada(fila, col), orientacion);
            }
        }
    }

    public Coordenada elegirCoordenadaDisparo() {
        while (!this.objetivosCercanos.isEmpty()) {
            Coordenada siguiente = this.objetivosCercanos.poll();

            if (!getTableroDisparo().getCasillero(siguiente).fueDisparado()) {
                return siguiente;
            }
        }

        int tamanio = getTableroDisparo().getTamanio();
        Coordenada elegida;

        do {
            int fila = this.random.nextInt(tamanio);
            int col = this.random.nextInt(tamanio);
            elegida = new Coordenada(fila, col);
        } while (getTableroDisparo().getCasillero(elegida).fueDisparado());

        return elegida;
    }

    @Override
    public void registrarDisparo(Coordenada coordenada, ResultadoDisparo resultado) {
        super.registrarDisparo(coordenada, resultado);

        switch (resultado) {
            case TOCADO -> {
                this.ultimoAcierto = coordenada;
                encolarAdyacentes(coordenada);
            }
            case HUNDIDO -> {
                this.objetivosCercanos.clear();
                this.ultimoAcierto = null;
            }
        }
    }

    private void encolarAdyacentes(Coordenada centro) {
        int[][] desplazamientos = {
                {-1, 0}, //arriba
                {1, 0},  //abajo
                {0, -1}, //izquierda
                {0, 1}   //derecha
        };

        for (int[] desp : desplazamientos) {
            Coordenada vecina = new Coordenada(
                    centro.getFila() + desp[0],
                    centro.getColumna() + desp[1]
            );

            if (getTableroDisparo().esCoordenadaValida(vecina)
                && !getTableroDisparo().getCasillero(vecina).fueDisparado()
                && !this.objetivosCercanos.contains(vecina)) {
                    this.objetivosCercanos.add(vecina);
            }
        }
    }
}
