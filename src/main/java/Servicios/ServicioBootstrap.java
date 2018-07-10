/*********************************************************
 *  Práctica #3 - HTTP - JDBC (Creación de un blog)       *
 *  Realizada por:                                        *
 *      - Oscar Dionisio Núñez Siri - 2014-0056           *
 *      - Jean Louis Tejeda - 2013-1459                   *
 *  Materia: Programación Web - ISC-415-T-001             *
 *********************************************************/

package Servicios;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ServicioBootstrap {
    private static ServicioBootstrap instancia;

    public static ServicioBootstrap getInstancia() {
        if (instancia == null) {
            instancia = new ServicioBootstrap();
        }
        return instancia;
    }

    /**
     * Se encarga de iniciar la base de datos para poder hacer transacciones y demás acciones.
     *
     * @throws SQLException
     */
    public static void iniciarBaseDatos() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    /**
     * Se encarga de detener la base de datos, en el caso de que sea necesario.
     *
     * @throws SQLException
     */
    public static void detenerBaseDatos() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }
}
