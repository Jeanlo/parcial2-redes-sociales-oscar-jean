package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "TEXT")
    private String texto;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Imagen imagen;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Usuario usuario;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comentario> comentarios;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Persona> personasEtiquetadas;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reaccion> reacciones;

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

    public Post(String texto, Imagen imagen, Usuario usuario, List<Comentario> comentarios, List<Persona> personasEtiquetadas, List<Reaccion> reacciones, Date fecha) {
        this.texto = texto;
        this.imagen = imagen;
        this.usuario = usuario;
        this.comentarios = comentarios;
        this.personasEtiquetadas = personasEtiquetadas;
        this.reacciones = reacciones;
        Fecha = fecha;
    }

    public Post() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Persona> getPersonasEtiquetadas() {
        return personasEtiquetadas;
    }

    public void setPersonasEtiquetadas(List<Persona> personasEtiquetadas) {
        this.personasEtiquetadas = personasEtiquetadas;
    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(List<Reaccion> reacciones) {
        this.reacciones = reacciones;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
}
