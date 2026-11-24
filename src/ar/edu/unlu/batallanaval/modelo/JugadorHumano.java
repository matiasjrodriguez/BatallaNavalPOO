package ar.edu.unlu.batallanaval.modelo;

public class JugadorHumano extends Jugador{
    public JugadorHumano(String nombre) {
        super(nombre);
    }

    @Override
    public void prepararFlota() {
        // si hay tiempo armar lógica para que el jugador
        // pueda llenar de forma random su tablero
    }
}
