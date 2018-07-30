package Servicios;

import Modelos.Reaccion;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServicioReaccion  extends ServicioBaseDatos<Reaccion> {
    private static ServicioReaccion instancia;

    private ServicioReaccion() {
        super(Reaccion.class);
    }

    public static ServicioReaccion getInstancia() {
        if (instancia == null) {
            instancia = new ServicioReaccion();
        }
        return instancia;
    }

    public Object encontrarReaccionUsuarioPost(long postID, long usuarioID) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "from Reaccion reaccion where reaccion.post.id = :reaccion_postID and reaccion.usuario.id = :reaccion_usuarioID"
            );
            query.setParameter("reaccion_postID", postID);
            query.setParameter("reaccion_usuarioID", usuarioID);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Object encontrarReaccionUsuarioAlbum(long albumID, long usuarioID) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "from Reaccion reaccion where reaccion.album.id = :reaccion_albumID and reaccion.usuario.id = :reaccion_usuarioID"
            );
            query.setParameter("reaccion_albumID", albumID);
            query.setParameter("reaccion_usuarioID", usuarioID);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Object encontrarReaccionUsuarioComentario(long postID, long usuarioID) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "from Reaccion reaccion where reaccion.comentario.id = :reaccion_comentarioID and reaccion.usuario.id = :reaccion_usuarioID"
            );
            query.setParameter("reaccion_comentarioID", postID);
            query.setParameter("reaccion_usuarioID", usuarioID);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public int conseguirCantidadReaccionPost(long postID, String tipo) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "select count(*) from Reaccion reaccion where reaccion.post.id = :reaccion_postID and reaccion.TipoReaccionElegida = :reaccion_tipo"
            );
            query.setParameter("reaccion_postID", postID);
            query.setParameter("reaccion_tipo", tipo);

            double cantidadReaccion = Double.parseDouble(query.getSingleResult().toString());

            return (int)cantidadReaccion;
        } catch (Exception ex) {
            return 0;
        } finally {
            em.close();
        }
    }

    public int conseguirCantidadReaccionAlbum(long albumID, String tipo) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "select count(*) from Reaccion reaccion where reaccion.album.id = :reaccion_albumID and reaccion.TipoReaccionElegida = :reaccion_tipo"
            );

            query.setParameter("reaccion_albumID", albumID);
            query.setParameter("reaccion_tipo", tipo);

            double cantidadReaccion = Double.parseDouble(query.getSingleResult().toString());

            return (int)cantidadReaccion;
        } catch (Exception ex) {
            return 0;
        } finally {
            em.close();
        }
    }

    public int conseguirCantidadReaccionComentario(long comentarioID, String tipo) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "select count(*) from Reaccion reaccion where reaccion.comentario.id = :reaccion_comentarioID and reaccion.TipoReaccionElegida = :reaccion_tipo"
            );
            query.setParameter("reaccion_comentarioID", comentarioID);
            query.setParameter("reaccion_tipo", tipo);

            double cantidadReaccion = Double.parseDouble(query.getSingleResult().toString());

            return (int)cantidadReaccion;
        } catch (Exception ex) {
            return 0;
        } finally {
            em.close();
        }
    }

    public List<Reaccion> encontrarReaccionPorPost(long postID, String tipo) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
            "from Reaccion reaccion where reaccion.post.id = :reaccion_postID and reaccion.TipoReaccionElegida = :reaccion_tipo"
            );

            query.setParameter("reaccion_postID", postID);
            query.setParameter("reaccion_tipo", tipo);

            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Reaccion> encontrarReaccionPorAlbum(long albumID, String tipo) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "from Reaccion reaccion where reaccion.album.id = :reaccion_albumID and reaccion.TipoReaccionElegida = :reaccion_tipo"
            );

            query.setParameter("reaccion_albumID", albumID);
            query.setParameter("reaccion_tipo", tipo);

            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Reaccion> encontrarReaccionPorComentario(long comentarioID, String tipo) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "from Reaccion reaccion where reaccion.comentario.id = :reaccion_comentarioID and reaccion.TipoReaccionElegida = :reaccion_tipo"
            );

            query.setParameter("reaccion_comentarioID", comentarioID);
            query.setParameter("reaccion_tipo", tipo);

            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
