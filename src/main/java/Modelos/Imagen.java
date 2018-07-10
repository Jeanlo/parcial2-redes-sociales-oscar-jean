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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Persona> personasEtiquetadas;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comentario> comentarios;

    public Imagen(String url, String descripcion, List<Persona> personasEtiquetadas, List<Comentario> comentarios) {
        this.url = url;
        this.descripcion = descripcion;
        this.personasEtiquetadas = personasEtiquetadas;
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

    public List<Persona> getPersonasEtiquetadas() {
        return personasEtiquetadas;
    }

    public void setPersonasEtiquetadas(List<Persona> personasEtiquetadas) {
        this.personasEtiquetadas = personasEtiquetadas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
