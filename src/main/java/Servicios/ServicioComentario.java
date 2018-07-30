package Servicios;

import Modelos.Comentario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServicioComentario extends ServicioBaseDatos<Comentario> {
    private static ServicioComentario instancia;

    private ServicioComentario() {
        super(Comentario.class);
    }

    public static ServicioComentario getInstancia() {
        if (instancia == null) {
            instancia = new ServicioComentario();
        }
        return instancia;
    }

    public List<Comentario> buscarComentarios() {
        EntityManager em = ServicioPost.getInstancia().getEntityManager();

        Query query = em.createQuery("select a from Comentario a order by a.id desc");
        List<Comentario> lista = query.getResultList();

        return lista;
    }
}
