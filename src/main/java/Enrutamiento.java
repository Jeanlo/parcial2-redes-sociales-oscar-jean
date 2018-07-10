import Modelos.Imagen;
import Modelos.Persona;
import Modelos.Post;
import Modelos.Usuario;
import Servicios.ServicioPersona;
import Servicios.ServicioPost;
import Servicios.ServicioUsuario;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.jasypt.util.text.StrongTextEncryptor;
import spark.Session;

import java.io.StringWriter;
import java.sql.Date;
import java.util.*;

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


        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("plantillas/index.ftl");

            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            atributos.put("listaPost", ServicioPost.getInstancia().buscarPosts());
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
           String sexo = "";
           String nacionalidad = req.queryParams("nacionalidad");

           if(req.queryParams("sexoM") != null){
               sexo = "M";
           }
            if(req.queryParams("sexoF") != null){
                sexo = "F";
            }

            long id = ServicioUsuario.getInstancia().listar().get(ServicioUsuario.getInstancia().listar().size() - 1).getId() + 1;

            Usuario userNuevo = new Usuario(id, username, contrasena, false, null);
            ServicioUsuario.getInstancia().crear(userNuevo);
            Persona personaNueva = new Persona(usuario, nombre, apellido, sexo, nacionalidad);
            ServicioPersona.getInstancia().crear(personaNueva);

            res.redirect("/");

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
