package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Album implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    public Album() {
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Usuario usuario;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Imagen imagen1;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Imagen imagen2;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Imagen imagen3;

    private String descripcion;
    private Date Fecha;

    public Album(Usuario usuario, Imagen imagen1, Imagen imagen2, Imagen imagen3, String descripcion, Date fecha) {
        this.usuario = usuario;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.descripcion = descripcion;
        Fecha = fecha;
    }

    public Imagen getImagen1() {
        return imagen1;
    }

    public void setImagen1(Imagen imagen1) {
        this.imagen1 = imagen1;
    }

    public Imagen getImagen2() {
        return imagen2;
    }

    public void setImagen2(Imagen imagen2) {
        this.imagen2 = imagen2;
    }

    public Imagen getImagen3() {
        return imagen3;
    }

    public void setImagen3(Imagen imagen3) {
        this.imagen3 = imagen3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desripcion) {
        this.descripcion = desripcion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
}
