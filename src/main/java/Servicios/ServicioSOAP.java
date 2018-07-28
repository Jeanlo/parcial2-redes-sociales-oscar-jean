package Servicios;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ServicioSOAP {
    @WebMethod
    public List<String> getListadoPostPorUsuario(String usuario) {
        List<String> posts = ServicioPost.getInstancia().listarPorUsuarioSOAP(usuario);

        return posts;
    }

    @WebMethod
    public String holaMundo() {
        return "hola";
    }
}
