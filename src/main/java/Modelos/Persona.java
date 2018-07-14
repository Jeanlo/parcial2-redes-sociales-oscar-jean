package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Usuario usuario;

    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String sexo;
    private String nacionalidad;

    private String estudio;
    private String trabajo;
    private String creencia;
    private String sitioWeb;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Post> postReaccionados;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Post> postComentados;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Album> albumes;

    private Date fechaRegistro;

    public Persona(Usuario usuario, String nombre, String apellido, Date fechaNacimiento, String sexo, String nacionalidad, Date fechaRegistro) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaRegistro = fechaRegistro;
    }

    public Persona(Usuario usuario, String nombre, String apellido, Date fechaNacimiento, String sexo, String nacionalidad, String estudio, String trabajo, String creencia, String sitioWeb, Date fechaRegistro) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.estudio = estudio;
        this.trabajo = trabajo;
        this.creencia = creencia;
        this.sitioWeb = sitioWeb;
        this.fechaRegistro = fechaRegistro;
    }

    public Persona() {
    }

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public List<Post> getPostReaccionados() {
        return postReaccionados;
    }

    public List<Post> getPostComentados() {
        return postComentados;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setPostReaccionados(List<Post> postReaccionados) {
        this.postReaccionados = postReaccionados;
    }

    public void setPostComentados(List<Post> postComentados) {
        this.postComentados = postComentados;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getCreencia() {
        return creencia;
    }

    public void setCreencia(String creencia) {
        this.creencia = creencia;
    }

    @Override
    public String toString() {
        return this.usuario.getUsuario();
    }
}
