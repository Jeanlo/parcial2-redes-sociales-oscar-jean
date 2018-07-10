package Modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private Usuario usuario;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String sexo;
    private String nacionalidad;
    private String religion;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private TipoOrientacionSexual orientacionSexual;

    private Boolean tienePareja;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Persona> amigos;

    private String educación;
    private String profesion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Post> postReaccionados;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Post> postComentados;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Album> albumes;

    private Date fechaRegistro;
    private String sitioWeb;

    public Persona(Usuario usuario, String nombre, String apellido, String sexo, String nacionalidad) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
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

    public String getReligion() {
        return religion;
    }

    public TipoOrientacionSexual getOrientacionSexual() {
        return orientacionSexual;
    }

    public Boolean getTienePareja() {
        return tienePareja;
    }

    public List<Persona> getAmigos() {
        return amigos;
    }

    public String getEducación() {
        return educación;
    }

    public String getProfesion() {
        return profesion;
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

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setOrientacionSexual(TipoOrientacionSexual orientacionSexual) {
        this.orientacionSexual = orientacionSexual;
    }

    public void setTienePareja(Boolean tienePareja) {
        this.tienePareja = tienePareja;
    }

    public void setAmigos(List<Persona> amigos) {
        this.amigos = amigos;
    }

    public void setEducación(String educación) {
        this.educación = educación;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
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
}
