package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario implements Serializable {
    @Id
    private Long id;
    private String usuario;
    private String contrasena;
    private String emocion;
    private boolean administrator;
    private String sesion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Persona> amigos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Notificacion> notificaciones;

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public Usuario(String usuario, String contrasena, boolean administrator, String sesion) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.administrator = administrator;
        this.sesion = sesion;
    }

    public Usuario(Long id, String usuario, String contrasena, boolean administrator, String sesion) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.administrator = administrator;
        this.sesion = sesion;
    }

    public Usuario(Long id, String usuario, String contrasena, String emocion,  boolean administrator, String sesion) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.emocion = emocion;
        this.administrator = administrator;
        this.sesion = sesion;
    }


    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public Usuario() {
    }

    public List<Persona> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Persona> amigos) {
        this.amigos = amigos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }
}