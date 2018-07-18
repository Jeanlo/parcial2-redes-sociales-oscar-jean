package Servicios;

import Modelos.Persona;
import Modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServicioUsuario extends ServicioBaseDatos<Usuario> {
    private static ServicioUsuario instancia;

    private ServicioUsuario() {
        super(Usuario.class);
    }

    public static ServicioUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ServicioUsuario();
        }
        return instancia;
    }

    public Object encontrarUsuarioSesion(String sesion) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("from Usuario user where user.sesion = :user_sesion");
            query.setParameter("user_sesion", sesion);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Object encontrarUsuario(String username, String password) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("from Usuario user where user.usuario = :user_username and user.contrasena = :user_password");
            query.setParameter("user_username", username);
            query.setParameter("user_password", password);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Object encontrarUsuarioEmocion(String username, String emocion) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("from Usuario user where user.usuario = :user_username and user.emocion = :user_emocion");
            query.setParameter("user_username", username);
            query.setParameter("user_emocion", emocion);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Object encontrarUsuarioPorUsername(String username) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("from Usuario user where user.usuario = :user_username");
            query.setParameter("user_username", username);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Object encontrarPersonaUsuario(String usuario) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("from Persona persona where persona.usuario.usuario = :usuario_actual");
            query.setParameter("usuario_actual", usuario);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Persona> sugerirUsuario(String nacionalidad, String estudio, String trabajo, String creencia, String sitioWeb) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "from Persona persona " +
                            "where persona.nacionalidad = :usuario_nacionalidad" +
                            " or persona.estudio = :usuario_estudio" +
                            " or persona.trabajo = :usuario_trabajo" +
                            " or persona.creencia = :usuario_creencia" +
                            " or persona.sitioWeb = :usuario_sitioWeb"
            );

            query.setParameter("usuario_nacionalidad", nacionalidad);
            query.setParameter("usuario_estudio", estudio);
            query.setParameter("usuario_trabajo", trabajo);
            query.setParameter("usuario_creencia", creencia);
            query.setParameter("usuario_sitioWeb", sitioWeb);

            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
