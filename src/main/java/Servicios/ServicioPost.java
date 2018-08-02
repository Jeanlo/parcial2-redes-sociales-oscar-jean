package Servicios;

import Modelos.Post;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServicioPost extends ServicioBaseDatos<Post> {
    private static ServicioPost instancia;

    private ServicioPost() {
        super(Post.class);
    }

    public static ServicioPost getInstancia() {
        if (instancia == null) {
            instancia = new ServicioPost();
        }
        return instancia;
    }

    public List<Post> buscarPosts() {
        EntityManager em = ServicioPost.getInstancia().getEntityManager();

        Query query = em.createQuery("select a from Post a order by a.id desc");
        List<Post> lista = query.getResultList();

        return lista;
    }

    public List<Post> listarPorUsuario(String usuario) {
        EntityManager em = ServicioPost.getInstancia().getEntityManager();
        Query query = em.createQuery("from Post a where a.usuario.usuario = :usuario order by a.id desc");
        query.setParameter("usuario", usuario);
        List<Post> lista = query.getResultList();

        return lista;
    }
}
