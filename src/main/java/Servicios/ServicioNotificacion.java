package Servicios;

import Modelos.Notificacion;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServicioNotificacion extends ServicioBaseDatos<Notificacion> {
    private static ServicioNotificacion instancia;

    private ServicioNotificacion() {
        super(Notificacion.class);
    }

    public static ServicioNotificacion getInstancia() {
        if (instancia == null) {
            instancia = new ServicioNotificacion();
        }
        return instancia;
    }
}