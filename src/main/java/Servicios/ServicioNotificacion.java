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

    public List<Notificacion> buscarNotificacionesNoLeidas(String usuario) {
        EntityManager em = ServicioPost.getInstancia().getEntityManager();

        Query query = em.createQuery("select a from Notificacion a where a.hasta.usuario =:usuario_username and a.leido = false order by a.id desc");
        query.setParameter("usuario_username", usuario);
        List<Notificacion> lista = query.getResultList();

        return lista;
    }
}