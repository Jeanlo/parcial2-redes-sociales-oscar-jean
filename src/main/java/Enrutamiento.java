import Modelos.Persona;
import Modelos.Post;
import Modelos.Reaccion;
import Modelos.Usuario;
import Servicios.ServicioPersona;
import Servicios.ServicioPost;
import Servicios.ServicioReaccion;
import Servicios.ServicioUsuario;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.jasypt.util.text.StrongTextEncryptor;
import spark.Session;

import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Enrutamiento {
    static Usuario usuario = null;
    static String nombreUsuario;

    public static void crearRutas() {
        final Configuration configuration = new Configuration(new Version(2, 3, 23));
        configuration.setClassForTemplateLoading(Main.class, "/");

        staticFiles.location("/publico");

        before("/", (req, res) -> {
            if (req.cookie("sesionSemanal") != null) {
                Usuario usuarioRestaurado = restaurarSesion(req.cookie("sesionSemanal"));

                if (usuarioRestaurado != null) {
                    req.session(true);
                    req.session().attribute("sesionUsuario", usuarioRestaurado);
                }
            }

            if (req.session().attribute("sesionUsuario") == null) {
                res.redirect("/login");
            }
        });

        before("/amigos", (req, res) -> {
            if (req.cookie("sesionSemanal") != null) {
                Usuario usuarioRestaurado = restaurarSesion(req.cookie("sesionSemanal"));

                if (usuarioRestaurado != null) {
                    req.session(true);
                    req.session().attribute("sesionUsuario", usuarioRestaurado);
                }
            }

            if (req.session().attribute("sesionUsuario") == null) {
                res.redirect("/login");
            }
        });

        before("/subirPrivilegios", (req, res) -> {
            if (req.cookie("sesionSemanal") != null) {
                Usuario usuarioRestaurado = restaurarSesion(req.cookie("sesionSemanal"));

                if (usuarioRestaurado != null) {
                    req.session(true);
                    req.session().attribute("sesionUsuario", usuarioRestaurado);
                }
            }

            if (req.session().attribute("sesionUsuario") == null) {
                res.redirect("/login");
            }

            if (!usuario.isAdministrator()) {
                res.redirect("/");
            }
        });

        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("plantillas/index.ftl");
            List<Post> listaPost = ServicioPost.getInstancia().buscarPosts();

            for (Post post : listaPost) {
                post.setMeGusta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-gusta"));
                post.setMeEncanta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-encanta"));
                post.setMeh(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "meh"));
                post.setMeDisgusta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-disgusta"));
                post.setMeIndigna(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-indigna"));
            }

            atributos.put("usuario", usuario);
            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            atributos.put("listaPost", listaPost);
            template.process(atributos, writer);

            return writer;
        });

        get("/salir", (req, res) ->
        {
            Session sesion = req.session(true);
            sesion.invalidate();

            res.removeCookie("sesionSemanal");

            res.redirect("/");

            return null;
        });

        get("/login", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("plantillas/login.ftl");

            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            template.process(atributos, writer);

            return writer;
        });

        post("/login", (req, res) -> {
            String username = req.queryParams("usuario");
            nombreUsuario = username;
            String password = req.queryParams("contrasena");
            usuario = (Usuario) ServicioUsuario.getInstancia().encontrarUsuario(username, password);

            try {
                if (usuario != null) {
                    req.session(true);
                    req.session().attribute("sesionUsuario", usuario);

                    if (req.queryParams("guardarSesion") != null) {
                        String sesionID = req.session().id();
                        StrongTextEncryptor encriptador = new StrongTextEncryptor();
                        encriptador.setPassword("bacano");
                        String sesionIDEncriptado = encriptador.encrypt(sesionID);

                        System.out.println("Sesión sin encriptar: " + sesionID);
                        System.out.println("Sesión encriptada: " + sesionIDEncriptado);

                        res.cookie("/", "sesionSemanal", sesionIDEncriptado, 604800, false);
                        ServicioUsuario.getInstancia().editar(new Usuario(usuario.getId(), usuario.getUsuario(), usuario.getContrasena(), usuario.isAdministrator(), req.session().id()));
                    }

                    res.redirect("/");
                } else {
                    res.redirect("/login");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        });

        post("/bacanear", (req, res) -> {
            java.sql.Date tiempoAhora = new Date(System.currentTimeMillis());

            String texto = req.queryParams("texto");
            //String imagen = req.queryParams("imagen");
            //String etiquetar = req.queryParams("etiquetar");

            //Imagen imagenAux = new Imagen(imagen, " ", null, null);

            Post post = new Post(texto, null, usuario, null, null, null, tiempoAhora);
            ServicioPost.getInstancia().crear(post);

            res.redirect("/");
            return null;
        });

        post("/registrar", (req, res) -> {
            String nombre = req.queryParams("nombre");
            String apellido = req.queryParams("apellido");
            String username = req.queryParams("usuario");
            String contrasena = req.queryParams("contrasena");
            String sexo = req.queryParams("sexo");
            String nacionalidad = req.queryParams("nacionalidad");

            long id = ServicioUsuario.getInstancia().listar().get(ServicioUsuario.getInstancia().listar().size() - 1).getId() + 1;

            Usuario userNuevo = new Usuario(id, username, contrasena, false, null);
            ServicioUsuario.getInstancia().crear(userNuevo);
            Persona personaNueva = new Persona(usuario, nombre, apellido, sexo, nacionalidad);
            ServicioPersona.getInstancia().crear(personaNueva);

            res.redirect("/");

            return null;
        });

        get("/subirPrivilegios", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("plantillas/subir-privilegios.ftl");

            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            atributos.put("usuarios", ServicioUsuario.getInstancia().listar());
            atributos.put("usuario", usuario);
            template.process(atributos, writer);

            return writer;
        });

        post("/hacerAdmin/:usuario", (req, res) -> {
            String username = req.params("usuario");
            Usuario usuarioEditado = (Usuario) ServicioUsuario.getInstancia().encontrarUsuarioPorUsername(username);
            usuarioEditado.setAdministrator(true);
            ServicioUsuario.getInstancia().editar(usuarioEditado);

            res.redirect("/subirPrivilegios");

            return null;
        });

        post("/reaccionar", (req, res) -> {
            Long id = Long.parseLong(req.queryParams("id"));
            String tipo = req.queryParams("tipo");

            Post post = ServicioPost.getInstancia().encontrar(id);

            Reaccion reaccion = (Reaccion) ServicioReaccion.getInstancia().encontrarReaccionUsuarioPost(id, usuario.getId());

            if (reaccion == null) {
                ServicioReaccion.getInstancia().crear(
                        new Reaccion(
                                tipo,
                                usuario,
                                post
                        )
                );
            } else {
                reaccion.setTipoReaccionElegida(tipo);
                ServicioReaccion.getInstancia().editar(reaccion);
            }


            ServicioPost.getInstancia().editar(post);

            int[] cantidades = new int[5];
            cantidades[0] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionPost(id, "me-gusta"));
            cantidades[1] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionPost(id, "me-encanta"));
            cantidades[2] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionPost(id, "meh"));
            cantidades[3] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionPost(id, "me-disgusta"));
            cantidades[4] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionPost(id, "me-indigna"));

            String stringCantidades = "";

            for (int i = 0; i < 5; i++) {
                stringCantidades += cantidades[i] + ",";
            }

            return stringCantidades;
        });

        get("/amigos", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("plantillas/amigos.ftl");

            List<Usuario> usuarios = ServicioUsuario.getInstancia().listar();
            List<Usuario> usuariosNoAmigos = new ArrayList<>();


            for(Usuario usu: usuarios){
                Boolean esAmigo = false;
                for(Usuario amigo: usuario.getAmigos()){
                    if(usu.getUsuario() == amigo.getUsuario() || usu.getUsuario() == usuario.getUsuario()) {
                        esAmigo = true;
                        break;
                    }
                }
                if(esAmigo) {
                    continue;
                }
                usuariosNoAmigos.add(usu);
            }

            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            atributos.put("usuarios", usuarios);
            atributos.put("usuariosNoAmigos", usuariosNoAmigos);
            atributos.put("usuario", usuario);
            template.process(atributos, writer);

            return writer;
        });

        post("/agregarAmigo/:usuario", (req, res) -> {
            String username = req.params("usuario");
            Usuario usuarioAmigo = (Usuario) ServicioUsuario.getInstancia().encontrarUsuarioPorUsername(username);
            usuario.getAmigos().add(usuarioAmigo);
            ServicioUsuario.getInstancia().editar(usuario);

            //usuarioAmigo.getAmigos().add(usuario);
            //ServicioUsuario.getInstancia().editar(usuarioAmigo);

            res.redirect("/amigos");

            return null;
        });
    }


    private static Usuario restaurarSesion(String cookie) {
        StrongTextEncryptor encriptador = new StrongTextEncryptor();
        encriptador.setPassword("bacano");
        String sesionSemanal = encriptador.decrypt(cookie);

        Usuario usuarioRestaurado = (Usuario) ServicioUsuario.getInstancia().encontrarUsuarioSesion(sesionSemanal);
        nombreUsuario = usuarioRestaurado.getUsuario();
        usuario = usuarioRestaurado;

        return usuarioRestaurado;
    }
}
