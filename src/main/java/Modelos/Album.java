package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reaccion> reacciones;

    private String descripcion;
    private Date Fecha;

    @Transient
    private List<Reaccion> meGusta;

    @Transient
    private List<Reaccion> meEncanta;

    @Transient
    private List<Reaccion> meh;

    @Transient
    private List<Reaccion> meDisgusta;

    @Transient
    private List<Reaccion> meIndigna;

    public Album(Usuario usuario, Imagen imagen1, Imagen imagen2, Imagen imagen3, String descripcion, Date fecha) {
        this.usuario = usuario;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.descripcion = descripcion;
        Fecha = fecha;
    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(List<Reaccion> reacciones) {
        this.reacciones = reacciones;
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

    public List<Reaccion> getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(List<Reaccion> meGusta) {
        this.meGusta = meGusta;
    }

    public List<Reaccion> getMeEncanta() {
        return meEncanta;
    }

    public void setMeEncanta(List<Reaccion> meEncanta) {
        this.meEncanta = meEncanta;
    }

    public List<Reaccion> getMeh() {
        return meh;
    }

    public void setMeh(List<Reaccion> meh) {
        this.meh = meh;
    }

    public List<Reaccion> getMeDisgusta() {
        return meDisgusta;
    }

    public void setMeDisgusta(List<Reaccion> meDisgusta) {
        this.meDisgusta = meDisgusta;
    }

    public List<Reaccion> getMeIndigna() {
        return meIndigna;
    }

    public void setMeIndigna(List<Reaccion> meIndigna) {
        this.meIndigna = meIndigna;
    }

    public long getCantidadMeGusta() {
        return this.meGusta == null ? 0 : this.meGusta.size();
    }

    public long getCantidadMeEncanta() {
        return this.meEncanta == null ? 0 : this.meEncanta.size();
    }

    public long getCantidadMeh() {
        return this.meh == null ? 0 : this.meh.size();
    }

    public long getCantidadMeDisgusta() {
        return this.meDisgusta == null ? 0 : this.meDisgusta.size();
    }

    public long getCantidadMeIndigna() {
        return this.meIndigna == null ? 0 : this.meIndigna.size();
    }
}
