package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Comentario implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String texto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Usuario usuario;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reaccion> reacciones;

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

    private Date fecha;

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

    public Comentario(String texto, Post post, Usuario usuario, List<Reaccion> reacciones, Date fecha) {
        this.texto = texto;
        this.post = post;
        this.usuario = usuario;
        this.reacciones = reacciones;
        this.fecha = fecha;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comentario() {
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

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(List<Reaccion> reacciones) {
        this.reacciones = reacciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

