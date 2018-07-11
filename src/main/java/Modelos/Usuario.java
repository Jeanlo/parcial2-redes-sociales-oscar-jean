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
    private boolean administrator;
    private String sesion;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Usuario> amigos;

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

    public Usuario() {
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
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