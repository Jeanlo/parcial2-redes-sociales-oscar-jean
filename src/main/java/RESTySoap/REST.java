package RESTySoap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class REST {
    public REST() {

    }

    public String listadoPublicacionesUsuario(String usuario) {
        Client cliente = Client.create();
        WebResource recursos = cliente.resource("http://localhost:4567/rest/listadoPost/" + usuario);

        ClientResponse response = recursos.accept("application/json").get(ClientResponse.class);

        String salida = response.getEntity(String.class);
        return salida;
    }
}