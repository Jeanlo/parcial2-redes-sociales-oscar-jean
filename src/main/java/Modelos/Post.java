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
    private String texto;
    private Imagen imagen;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comentario> comentarios;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Persona> personasEtiquetadas;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reaccion> reacciones;

    private Date Fecha;

    public Post(String texto, Imagen imagen, Usuario usuario, List<Comentario> comentarios, List<Persona> personasEtiquetadas, List<Reaccion> reacciones, Date fecha) {
        this.texto = texto;
        this.imagen = imagen;
        this.usuario = usuario;
        this.comentarios = comentarios;
        this.personasEtiquetadas = personasEtiquetadas;
        this.reacciones = reacciones;
        Fecha = fecha;
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
