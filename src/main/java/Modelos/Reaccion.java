package Modelos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reaccion implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String TipoReaccionElegida;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    public Reaccion(String tipoReaccionElegida, Usuario usuario, Post post) {
        TipoReaccionElegida = tipoReaccionElegida;
        this.usuario = usuario;
        this.post = post;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Reaccion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoReaccionElegida() {
        return TipoReaccionElegida;
    }

    public void setTipoReaccionElegida(String tipoReaccionElegida) {
        TipoReaccionElegida = tipoReaccionElegida;
    }
}
