package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Imagen implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String url;
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Persona personaEtiquetada;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comentario> comentarios;

    public Imagen(String url, String descripcion, Persona personaEtiquetada, List<Comentario> comentarios) {
        this.url = url;
        this.descripcion = descripcion;
        this.personaEtiquetada = personaEtiquetada;
        this.comentarios = comentarios;
    }

    public Imagen() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Persona getPersonaEtiquetada() {
        return personaEtiquetada;
    }

    public void setPersonaEtiquetada(Persona personaEtiquetada) {
        this.personaEtiquetada = personaEtiquetada;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
