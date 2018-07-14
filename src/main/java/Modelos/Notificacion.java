package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Notificacion implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String texto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Usuario usuario;

    private Date fecha;

    private Boolean leida;

    public Notificacion() {
    }

    public Notificacion(String texto, Usuario usuario, Date fecha, Boolean leida) {
        this.texto = texto;
        this.usuario = usuario;
        this.fecha = fecha;
        this.leida = leida;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }
}

