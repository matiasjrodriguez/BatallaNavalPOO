package ar.edu.unlu.batallanaval.modelo;

public enum TipoBarco {
    DESTRUCTOR(2),
    SUBMARINO(3),
    CRUCERO(3),
    ACORAZADO(4),
    PORTAAVIONES(5);

    private final int longitud;

    private TipoBarco(int longitud) {
        this.longitud = longitud;
    }

    public int getLongitud() {
        return this.longitud;
    }
}
