package Servicios;

import Modelos.Imagen;
import Modelos.Persona;
import Modelos.Post;
import Modelos.Usuario;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebService
public class ServicioSOAP {
    @WebMethod
    public List<String> getListadoPostPorUsuario(String usuario) {
        Usuario usuarioExiste = (Usuario)ServicioUsuario.getInstancia().encontrarUsuarioPorUsername(usuario);

        if (usuarioExiste != null) {
            List<Post> posts = ServicioPost.getInstancia().listarPorUsuario(usuario);

            List<String> salidas = new ArrayList<>();

            for (Post post : posts) {
                if (post.getPersonaEtiquetada() != null && post.getImagen() != null) {
                    salidas.add(post.getTexto() + "," + post.getImagen().getUrl() + "," + post.getUsuario().getUsuario() + "," + post.getFecha() + "," + post.getPersonaEtiquetada().getNombre() + " " + post.getPersonaEtiquetada().getApellido() + "," + post.getComentarios().size() + "," + post.getImagen().getCreado());
                } else if (post.getPersonaEtiquetada() != null && post.getImagen() == null) {
                    salidas.add(post.getTexto() + "," + " " + "," + post.getUsuario().getUsuario() + "," + post.getFecha() + "," + post.getPersonaEtiquetada().getNombre() + " " + post.getPersonaEtiquetada().getApellido() + "," + post.getComentarios().size() + "," + " ");
                } else if (post.getImagen() != null && post.getPersonaEtiquetada() == null) {
                    salidas.add(post.getTexto() + "," + post.getImagen().getUrl() + "," + post.getUsuario().getUsuario() + "," + post.getFecha() + "," + " " + "," + post.getComentarios().size() + "," + post.getImagen().getCreado());
                } else {
                    salidas.add(post.getTexto() + "," + " " + "," + post.getUsuario().getUsuario() + "," + post.getFecha() + "," + " " + "," + post.getComentarios().size() + "," + " ");
                }
            }

            return salidas;
        } else {
            return new ArrayList<>();
        }
    }

    @WebMethod
    public String crearPost(String texto, String nombreUsuario, String urlImagen) {
        Usuario usuarioExiste = (Usuario)ServicioUsuario.getInstancia().encontrarUsuarioPorUsername(nombreUsuario);

        if (usuarioExiste != null) {
            Persona personaUsuario = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(nombreUsuario);
            Imagen imagen = new Imagen(urlImagen, texto, null, null, "cliente");
            ServicioImagen.getInstancia().crear(imagen);

            Post post = new Post(texto, imagen, personaUsuario.getUsuario(), null, null, null,  new Date(System.currentTimeMillis()));

            ServicioPost.getInstancia().crear(post);

            return post.getTexto() + "," + post.getImagen().getUrl() + "," + post.getUsuario().getUsuario() + "," + post.getFecha();
        } else {
            return "";
        }
    }
}
