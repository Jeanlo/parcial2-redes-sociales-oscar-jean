package Servicios;

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
    public String crearPost(String texto) {
        Post post = new Post(texto, null, null, null, null, null, new Date(System.currentTimeMillis()));
        ServicioPost.getInstancia().crear(post);
        return post.getTexto() + " " + post.getFecha();
    }
}
