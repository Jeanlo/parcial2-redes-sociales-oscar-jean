package Servicios;

import Modelos.Album;
import Modelos.Post;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServicioAlbum extends ServicioBaseDatos<Album> {
    private static ServicioAlbum instancia;

    private ServicioAlbum() {
        super(Album.class);
    }

    public static ServicioAlbum getInstancia() {
        if (instancia == null) {
            instancia = new ServicioAlbum();
        }
        return instancia;
    }

    public List<Album> buscarAlbumes() {
        EntityManager em = ServicioPost.getInstancia().getEntityManager();

        Query query = em.createQuery("select a from Album a order by a.id desc");
        List<Album> lista = query.getResultList();

        return lista;
    }
}