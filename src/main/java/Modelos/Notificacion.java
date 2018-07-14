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
    private Usuario hasta;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Usuario desde;

    private Date fecha;

    private String tipoNotificacion;

    public Notificacion() {
    }

    public Notificacion(String texto, Usuario desde, Usuario hasta, Date fecha, String tipoNotificacion) {
        this.texto = texto;
        this.desde = desde;
        this.hasta = hasta;
        this.fecha = fecha;
        this.tipoNotificacion = tipoNotificacion;
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

    public Usuario getDesde() {
        return desde;
    }

    public void setDesde(Usuario desde) {
        this.desde = desde;
    }

    public Usuario getHasta() {
        return hasta;
    }

    public void setHasta(Usuario usuario) {
        this.hasta = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }
}

