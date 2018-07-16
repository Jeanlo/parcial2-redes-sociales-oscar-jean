package Servicios;

import Modelos.Imagen;

public class ServicioImagen extends ServicioBaseDatos<Imagen> {
    private static ServicioImagen instancia;

    private ServicioImagen() {
        super(Imagen.class);
    }

    public static ServicioImagen getInstancia() {
        if (instancia == null) {
            instancia = new ServicioImagen();
        }
        return instancia;
    }
}
