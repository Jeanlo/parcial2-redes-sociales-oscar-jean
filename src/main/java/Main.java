import Modelos.Usuario;
import Servicios.ServicioBootstrap;
import Servicios.ServicioUsuario;

/*********************************************************
 *  Parcial #2 - Redes sociales                           *
 *  Realizada por:                                        *
 *      - Oscar Dionisio Núñez Siri - 2014-0056           *
 *      - Jean Louis Tejeda - 2013-1459                   *
 *  Materia: Programación Web - ISC-415-T-001             *
 *********************************************************/

public class Main {
    public static void main(String[] args) {
        // Iniciando el servicio de Base de datos por medio de Hibernate y H2.
        try {
            ServicioBootstrap.iniciarBaseDatos();
            // Insertando el usuario por defecto (administrador).
            if (ServicioUsuario.getInstancia().encontrar(new Long(1)) == null) {
                ServicioUsuario.getInstancia().crear(new Usuario(new Long(1), "admin", "1234", true, null));
            }
            Enrutamiento.crearRutas();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
