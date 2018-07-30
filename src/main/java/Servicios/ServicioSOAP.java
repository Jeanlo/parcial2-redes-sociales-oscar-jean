package Servicios;

import Modelos.Imagen;
import Modelos.Persona;
import Modelos.Post;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Date;
import java.util.List;

@WebService
public class ServicioSOAP {
    @WebMethod
    public List<String> getListadoPostPorUsuario(String usuario) {
        List<String> posts = ServicioPost.getInstancia().listarPorUsuarioSOAP(usuario);

        return posts;
    }

    @WebMethod
    public String crearPost(String texto, String nombreUsuario, String urlImagen) {
        Persona personaUsuario = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(nombreUsuario);
        Imagen imagen = new Imagen(urlImagen, texto, null, null);
        ServicioImagen.getInstancia().crear(imagen);

        Post post = new Post(texto, imagen, personaUsuario.getUsuario(), null, null, null,  new Date(System.currentTimeMillis()));

        ServicioPost.getInstancia().crear(post);

        return post.getTexto() + "," + post.getImagen().getUrl() + "," + post.getUsuario().getUsuario() + "," + post.getFecha();
    }
}
