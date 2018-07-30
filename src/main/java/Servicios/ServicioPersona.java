package Servicios;

import Modelos.Persona;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServicioPersona extends ServicioBaseDatos<Persona> {
    private static ServicioPersona instancia;

    private ServicioPersona() {
        super(Persona.class);
    }

    public static ServicioPersona getInstancia() {
        if (instancia == null) {
            instancia = new ServicioPersona();
        }
        return instancia;
    }


}