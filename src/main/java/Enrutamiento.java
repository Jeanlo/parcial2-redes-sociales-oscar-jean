import Modelos.*;
import Servicios.*;
import Utilidades.JSON;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.jasypt.util.text.StrongTextEncryptor;
import spark.Request;
import spark.Session;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Enrutamiento {
    static Usuario usuario = null;
    static String nombreUsuario;
    private static final String FLASH_MESSAGE_KEY = "flash_message";

    public static void crearRutas() {
        final Configuration configuration = new Configuration(new Version(2, 3, 23));
        configuration.setClassForTemplateLoading(Main.class, "/");

        File uploadDir = new File("upload");
        uploadDir.mkdir();

        staticFiles.externalLocation("upload");
        staticFiles.location("/publico");

        enableDebugScreen();

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

        before("/perfil/:usuario", (req, res) -> {
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

        before("/registrar", (req, res) -> {
            String nombre = req.queryParams("nombre");
            String apellido = req.queryParams("apellido");
            String username = req.queryParams("usuario");
            String contrasena = req.queryParams("contrasena");
            String sexo = req.queryParams("sexo");
            String nacionalidad = req.queryParams("nacionalidad");
            String fechaNacimiento = req.queryParams("fecha-nacimiento");

            if (nombre == null || apellido == null || username == null || contrasena == null || sexo == null || nacionalidad == null || fechaNacimiento == null) {
                setFlashMessage(req, "Error de validación. Faltaron campos requeridos.");
                res.redirect("/");
                halt();
            }
        });

        before("/crear-album", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            String descripcion = req.queryParams("descripcion");

            if (descripcion == null) {
                setFlashMessage(req, "Error de validación. Faltaron campos requeridos.");
                res.redirect("/");
                halt();
            }
        });

        before("/bacanear", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            String texto = req.queryParams("texto");

            if (texto == null) {
                setFlashMessage(req, "Error de validación. Faltaron campos requeridos.");
                res.redirect("/");
                halt();
            }
        });

        path("/rest", () -> {
            afterAfter("/*", (request, response) -> {
                response.header("Content-Type", "application/json");
            });

            get("/listadoPost/:usuario", (req, res) -> {
                String usuario = req.params("usuario");
                return ServicioPost.getInstancia().listarPorUsuarioREST(usuario);
            }, JSON.json());
        });

        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("plantillas/index.ftl");
            List<Post> posts = ServicioPost.getInstancia().buscarPosts();
            List<Post> listaPost = new ArrayList<>();
            List<Album> albumes = ServicioAlbum.getInstancia().buscarAlbumes();
            List<Album> listaAlbum = new ArrayList<>();

            for (Post post : posts) {
                for (Persona amigo : usuario.getAmigos()) {
                    if (post.getUsuario().getUsuario().equals(amigo.getUsuario().getUsuario())) {
                        listaPost.add(post);
                    }
                }

                if (post.getUsuario().getUsuario().equals(usuario.getUsuario())) {
                    listaPost.add(post);
                }
            }

            for (Album album : albumes) {
                for (Persona amigo : usuario.getAmigos()) {
                    if (album.getUsuario().getUsuario().equals(amigo.getUsuario().getUsuario())) {
                        listaAlbum.add(album);
                    }
                }

                if (album.getUsuario().getUsuario().equals(usuario.getUsuario())) {
                    listaAlbum.add(album);
                }
            }

            for (Post post : listaPost) {
                post.setMeGusta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-gusta"));
                post.setMeEncanta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-encanta"));
                post.setMeh(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "meh"));
                post.setMeDisgusta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-disgusta"));
                post.setMeIndigna(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-indigna"));

                for (Comentario comentario : post.getComentarios()) {
                    comentario.setMeGusta(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "me-gusta"));
                    comentario.setMeEncanta(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "me-encanta"));
                    comentario.setMeh(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "meh"));
                    comentario.setMeDisgusta(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "me-disgusta"));
                    comentario.setMeIndigna(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "me-indigna"));
                }
            }

            for (Album album : listaAlbum) {
                album.setMeGusta(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "me-gusta"));
                album.setMeEncanta(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "me-encanta"));
                album.setMeh(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "meh"));
                album.setMeDisgusta(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "me-disgusta"));
                album.setMeIndigna(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "me-indigna"));
            }

            List<Persona> amigos = new ArrayList<>();

            for (Persona persona : usuario.getAmigos()) {
                amigos.add((Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(persona.getUsuario().getUsuario()));
            }

            usuario.setNotificaciones(ServicioNotificacion.getInstancia().buscarNotificacionesNoLeidas(usuario.getUsuario()));

            atributos.put("usuario", usuario);
            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            atributos.put("listaPost", listaPost);
            atributos.put("listaAlbum", listaAlbum);
            atributos.put("amigos", amigos);
            atributos.put("flashMessage", captureFlashMessage(req));
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
            atributos.put("flashMessage", captureFlashMessage(req));

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

                        usuario.setSesion(req.session().id());
                        ServicioUsuario.getInstancia().editar(usuario);
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

        post("/login-affectiva", (req, res) -> {
            System.out.println("LOGUEANDO CON AFFECTIVA");

            String username = req.queryParams("usuario");
            nombreUsuario = username;
            String emocion = req.queryParams("emocion");
            usuario = (Usuario) ServicioUsuario.getInstancia().encontrarUsuarioEmocion(username, emocion);

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

                        usuario.setSesion(req.session().id());
                        ServicioUsuario.getInstancia().editar(usuario);
                    }

                    System.out.println("ENTRANDO");
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

            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            String texto = req.queryParams("texto");
            String etiquetado = req.queryParams("etiquetado");
            Imagen imagen;

            try (InputStream input = req.raw().getPart("imagen").getInputStream()) {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            if (!req.raw().getPart("imagen").getSubmittedFileName().isEmpty()) {
                imagen = new Imagen(tempFile.getFileName().toString(), texto, null, null);
                ServicioImagen.getInstancia().crear(imagen);
            } else {
                imagen = null;
            }

            if (!etiquetado.isEmpty()) {
                Persona persona = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(etiquetado);

                Persona personaUsuario = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(usuario.getUsuario());

                Notificacion notificacion = new Notificacion(
                        personaUsuario.getNombre() + " " + personaUsuario.getApellido() + " te ha etiquetado en un post.",
                        usuario, persona.getUsuario(), new java.util.Date(System.currentTimeMillis()),
                        "Etiquetacion", false);

                ServicioNotificacion.getInstancia().crear(notificacion);

                Usuario user = persona.getUsuario();

                user.getNotificaciones().add(notificacion);
                ServicioUsuario.getInstancia().editar(user);
                Post post = new Post(texto, imagen, usuario, null, persona, null, tiempoAhora);
                ServicioPost.getInstancia().crear(post);
            } else {
                Post post = new Post(texto, imagen, usuario, null, null, null, tiempoAhora);
                ServicioPost.getInstancia().crear(post);
            }

            res.redirect("/");

            return null;
        });

        post("/crear-album", (req, res) -> {
            java.sql.Date tiempoAhora = new Date(System.currentTimeMillis());

            Path tempFile1 = Files.createTempFile(uploadDir.toPath(), "", "");
            Path tempFile2 = Files.createTempFile(uploadDir.toPath(), "", "");
            Path tempFile3 = Files.createTempFile(uploadDir.toPath(), "", "");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            String descripcion = req.queryParams("descripcion");
            String descripcion1 = req.queryParams("descripcion1");
            String descripcion2 = req.queryParams("descripcion2");
            String descripcion3 = req.queryParams("descripcion3");

            String etiquetado1 = req.queryParams("etiquetado1");
            String etiquetado2 = req.queryParams("etiquetado2");
            String etiquetado3 = req.queryParams("etiquetado3");

            Imagen imagen1;
            Imagen imagen2;
            Imagen imagen3;

            try (InputStream input = req.raw().getPart("imagen1").getInputStream()) {
                Files.copy(input, tempFile1, StandardCopyOption.REPLACE_EXISTING);
            }

            if (!req.raw().getPart("imagen1").getSubmittedFileName().isEmpty()) {
                imagen1 = new Imagen(tempFile1.getFileName().toString(), descripcion1, null, null);
            } else {
                imagen1 = null;
            }

            try (InputStream input = req.raw().getPart("imagen2").getInputStream()) {
                Files.copy(input, tempFile2, StandardCopyOption.REPLACE_EXISTING);
            }

            if (!req.raw().getPart("imagen2").getSubmittedFileName().isEmpty()) {
                imagen2 = new Imagen(tempFile2.getFileName().toString(), descripcion2, null, null);
            } else {
                imagen2 = null;
            }

            try (InputStream input = req.raw().getPart("imagen3").getInputStream()) {
                Files.copy(input, tempFile3, StandardCopyOption.REPLACE_EXISTING);
            }

            if (!req.raw().getPart("imagen3").getSubmittedFileName().isEmpty()) {
                imagen3 = new Imagen(tempFile3.getFileName().toString(), descripcion3, null, null);
            } else {
                imagen3 = null;
            }

            Persona personaUsuario = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(usuario.getUsuario());

            if (!etiquetado1.isEmpty()) {
                Persona persona1 = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(etiquetado1);
                imagen1.setPersonaEtiquetada(persona1);
                ServicioImagen.getInstancia().crear(imagen1);

                Notificacion notificacion1 = new Notificacion(
                        personaUsuario.getNombre() + " " + personaUsuario.getApellido() + " te ha etiquetado en un album.",
                        usuario, persona1.getUsuario(), new java.util.Date(System.currentTimeMillis()),
                        "Etiquetacion", false);

                ServicioNotificacion.getInstancia().crear(notificacion1);

                Usuario user1 = persona1.getUsuario();
                user1.getNotificaciones().add(notificacion1);
                ServicioUsuario.getInstancia().editar(user1);
            }

            if (!etiquetado2.isEmpty()) {
                Persona persona2 = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(etiquetado2);
                imagen2.setPersonaEtiquetada(persona2);
                ServicioImagen.getInstancia().crear(imagen2);

                Notificacion notificacion2 = new Notificacion(
                        personaUsuario.getNombre() + " " + personaUsuario.getApellido() + " te ha etiquetado en un album.",
                        usuario, persona2.getUsuario(), new java.util.Date(System.currentTimeMillis()),
                        "Etiquetacion", false);

                ServicioNotificacion.getInstancia().crear(notificacion2);

                Usuario user2 = persona2.getUsuario();
                user2.getNotificaciones().add(notificacion2);
                ServicioUsuario.getInstancia().editar(user2);
            }

            if (!etiquetado3.isEmpty()) {
                Persona persona3 = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(etiquetado3);
                imagen3.setPersonaEtiquetada(persona3);
                ServicioImagen.getInstancia().crear(imagen3);

                Notificacion notificacion3 = new Notificacion(
                        personaUsuario.getNombre() + " " + personaUsuario.getApellido() + " te ha etiquetado en un album.",
                        usuario, persona3.getUsuario(), new java.util.Date(System.currentTimeMillis()),
                        "Etiquetacion", false);

                ServicioNotificacion.getInstancia().crear(notificacion3);

                Usuario user3 = persona3.getUsuario();
                user3.getNotificaciones().add(notificacion3);
                ServicioUsuario.getInstancia().editar(user3);
            }

            Album album = new Album();
            album.setUsuario(usuario);
            album.setDescripcion(descripcion);
            album.setFecha(tiempoAhora);

            boolean bimagen1 = imagen1 != null;
            boolean bimagen2 = imagen2 != null;
            boolean bimagen3 = imagen3 != null;

            if (bimagen1) {
                ServicioImagen.getInstancia().crear(imagen1);
                album.setImagen1(imagen1);
            }

            if (bimagen2) {
                ServicioImagen.getInstancia().crear(imagen2);
                album.setImagen2(imagen2);
            }

            if (bimagen3) {
                ServicioImagen.getInstancia().crear(imagen3);
                album.setImagen3(imagen3);
            }

            ServicioAlbum.getInstancia().crear(album);

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

            String estudio = req.queryParams("estudio");
            String trabajo = req.queryParams("trabajo");
            String creencia = req.queryParams("creencia");
            String sitioWeb = req.queryParams("sitio-web");
            String emocion = req.queryParams("emocion");

            String sfechaNacimiento = req.queryParams("fecha-nacimiento");
            System.out.println(sfechaNacimiento);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            java.util.Date fechaNacimiento = df.parse(sfechaNacimiento);

            long id = ServicioUsuario.getInstancia().listar().get(ServicioUsuario.getInstancia().listar().size() - 1).getId() + 1;

            Usuario userNuevo = new Usuario(id, username, contrasena, emocion, false, null);
            ServicioUsuario.getInstancia().crear(userNuevo);

            Persona personaNueva = new Persona(userNuevo, nombre, apellido, fechaNacimiento, sexo, nacionalidad, estudio, trabajo, creencia, sitioWeb, new Date(System.currentTimeMillis()));
            ServicioPersona.getInstancia().crear(personaNueva);

            res.redirect("/");

            return null;
        });

        get("/subirPrivilegios", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("plantillas/subir-privilegios.ftl");

            List<Persona> amigos = new ArrayList<>();

            for (Persona persona : usuario.getAmigos()) {
                amigos.add((Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(persona.getUsuario().getUsuario()));
            }

            usuario.setNotificaciones(ServicioNotificacion.getInstancia().buscarNotificacionesNoLeidas(usuario.getUsuario()));

            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            atributos.put("usuarios", ServicioUsuario.getInstancia().listar());
            atributos.put("usuario", usuario);
            atributos.put("amigos", amigos);
            atributos.put("flashMessage", captureFlashMessage(req));

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

        post("/reaccionarAlbum", (req, res) -> {
            Long id = Long.parseLong(req.queryParams("id"));
            String tipo = req.queryParams("tipo");

            Album album = ServicioAlbum.getInstancia().encontrar(id);

            Reaccion reaccion = (Reaccion) ServicioReaccion.getInstancia().encontrarReaccionUsuarioAlbum(id, usuario.getId());

            if (reaccion == null) {
                ServicioReaccion.getInstancia().crear(
                        new Reaccion(
                                tipo,
                                usuario,
                                album
                        )
                );
            } else {
                reaccion.setTipoReaccionElegida(tipo);
                ServicioReaccion.getInstancia().editar(reaccion);
            }


            ServicioAlbum.getInstancia().editar(album);

            int[] cantidades = new int[5];
            cantidades[0] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionAlbum(id, "me-gusta"));
            cantidades[1] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionAlbum(id, "me-encanta"));
            cantidades[2] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionAlbum(id, "meh"));
            cantidades[3] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionAlbum(id, "me-disgusta"));
            cantidades[4] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionAlbum(id, "me-indigna"));

            String stringCantidades = "";

            for (int i = 0; i < 5; i++) {
                stringCantidades += cantidades[i] + ",";
            }

            return stringCantidades;
        });

        post("/reaccionarComentario", (req, res) -> {
            Long id = Long.parseLong(req.queryParams("id"));
            String tipo = req.queryParams("tipo");

            Comentario comentario = ServicioComentario.getInstancia().encontrar(id);

            Reaccion reaccion = (Reaccion) ServicioReaccion.getInstancia().encontrarReaccionUsuarioComentario(id, usuario.getId());

            if (reaccion == null) {
                ServicioReaccion.getInstancia().crear(
                        new Reaccion(
                                tipo,
                                usuario,
                                comentario
                        )
                );
            } else {
                reaccion.setTipoReaccionElegida(tipo);
                ServicioReaccion.getInstancia().editar(reaccion);
            }


            ServicioComentario.getInstancia().editar(comentario);

            int[] cantidades = new int[5];
            cantidades[0] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionComentario(id, "me-gusta"));
            cantidades[1] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionComentario(id, "me-encanta"));
            cantidades[2] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionComentario(id, "meh"));
            cantidades[3] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionComentario(id, "me-disgusta"));
            cantidades[4] = (ServicioReaccion.getInstancia().conseguirCantidadReaccionComentario(id, "me-indigna"));

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

            Persona persona = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(usuario.getUsuario());

            List<Persona> usuariosSugeridos =
                    ServicioUsuario.getInstancia().sugerirUsuario(persona.getNacionalidad(), persona.getEstudio(), persona.getTrabajo(), persona.getCreencia(), persona.getSitioWeb());

            for (Persona per : usuario.getAmigos()) {
                for (int i = 0; i < usuariosSugeridos.size(); i++) {
                    if (per.getUsuario().getUsuario().equals(usuariosSugeridos.get(i).getUsuario().getUsuario())) {
                        usuariosSugeridos.remove(usuariosSugeridos.get(i));
                    }
                }
            }

            List<Persona> amigos = new ArrayList<>();

            for (Persona per : usuario.getAmigos()) {
                amigos.add((Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(per.getUsuario().getUsuario()));
            }


            usuario.setNotificaciones(ServicioNotificacion.getInstancia().buscarNotificacionesNoLeidas(usuario.getUsuario()));

            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            atributos.put("usuariosSugeridos", usuariosSugeridos);
            atributos.put("usuario", usuario);
            atributos.put("amigos", amigos);
            atributos.put("flashMessage", captureFlashMessage(req));

            template.process(atributos, writer);

            return writer;
        });

        post("/agregarAmigo/:usuario", (req, res) -> {
            String username = req.params("usuario");

            Persona persona = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(username);

            Persona personaUsuario = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(usuario.getUsuario());

            Notificacion notificacion = new Notificacion(
                    personaUsuario.getNombre() + " " + personaUsuario.getApellido() + " quiere agregarte como amigo.",
                    usuario, persona.getUsuario(), new java.util.Date(System.currentTimeMillis()),
                    "Solicitud amistad", false);

            ServicioNotificacion.getInstancia().crear(notificacion);

            Usuario user = persona.getUsuario();

            user.getNotificaciones().add(notificacion);
            ServicioUsuario.getInstancia().editar(user);

            res.redirect("/amigos");

            return null;
        });

        post("/verPerfil/:notificacion", (req, res) -> {

            Long notificacion = Long.parseLong(req.params("notificacion"));

            Notificacion notificacionAux = ServicioNotificacion.getInstancia().encontrar(notificacion);
            notificacionAux.setLeido(true);

            ServicioNotificacion.getInstancia().editar(notificacionAux);

            res.redirect("/perfil/" + usuario.getUsuario());

            return null;
        });

        post("/aceptarAmigo/:usuario/:notificacion", (req, res) -> {
            String username = req.params("usuario");

            Long notificacion = Long.parseLong(req.params("notificacion"));

            Usuario usuAmigo = (Usuario) ServicioUsuario.getInstancia().encontrarUsuarioPorUsername(username);

            Persona persona = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(username);
            Persona personaUsuario = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(usuario.getUsuario());


            usuario.getAmigos().add(persona);
            usuAmigo.getAmigos().add(personaUsuario);

            ServicioUsuario.getInstancia().editar(usuario);
            ServicioUsuario.getInstancia().editar(usuAmigo);

            Notificacion notificacionAux = ServicioNotificacion.getInstancia().encontrar(notificacion);
            notificacionAux.setLeido(true);
            ServicioNotificacion.getInstancia().editar(notificacionAux);

            res.redirect("/");

            return null;
        });

        get("/perfil/:usuario", (req, res) -> {
            StringWriter writer = new StringWriter();
            nombreUsuario = req.params("usuario");
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("plantillas/perfil.ftl");

            Persona persona = (Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(usuario.getUsuario());

            List<Post> posts = ServicioPost.getInstancia().buscarPosts();
            List<Post> listaPostPropios = new ArrayList<>();

            List<Album> albumes = ServicioAlbum.getInstancia().buscarAlbumes();
            List<Album> listaAlbumesPropios = new ArrayList<>();

            for (Post post : posts) {
                if (post.getUsuario().getUsuario() == usuario.getUsuario()) {
                    listaPostPropios.add(post);
                } else {
                    if (post.getPersonaEtiquetada() != null) {
                        if (post.getPersonaEtiquetada().getUsuario().getUsuario() == usuario.getUsuario()) {
                            listaPostPropios.add(post);
                        }
                    }
                }
            }

            for (Album album : albumes) {
                if (album.getUsuario().getUsuario() == usuario.getUsuario()) {
                    listaAlbumesPropios.add(album);
                } else {
                    if (album.getImagen1() != null && album.getImagen1().getPersonaEtiquetada() != null && album.getImagen1().getPersonaEtiquetada().getUsuario().getUsuario() == usuario.getUsuario()) {
                        listaAlbumesPropios.add(album);
                    } else if (album.getImagen2() != null && album.getImagen2().getPersonaEtiquetada() != null && album.getImagen2().getPersonaEtiquetada().getUsuario().getUsuario() == usuario.getUsuario()) {
                        listaAlbumesPropios.add(album);
                    } else if (album.getImagen3() != null && album.getImagen3().getPersonaEtiquetada() != null && album.getImagen3().getPersonaEtiquetada().getUsuario().getUsuario() == usuario.getUsuario()) {
                        listaAlbumesPropios.add(album);
                    }
                }
            }

            for (Post post : listaPostPropios) {
                post.setMeGusta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-gusta"));
                post.setMeEncanta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-encanta"));
                post.setMeh(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "meh"));
                post.setMeDisgusta(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-disgusta"));
                post.setMeIndigna(ServicioReaccion.getInstancia().encontrarReaccionPorPost(post.getId(), "me-indigna"));

                for (Comentario comentario : post.getComentarios()) {
                    comentario.setMeGusta(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "me-gusta"));
                    comentario.setMeEncanta(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "me-encanta"));
                    comentario.setMeh(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "meh"));
                    comentario.setMeDisgusta(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "me-disgusta"));
                    comentario.setMeIndigna(ServicioReaccion.getInstancia().encontrarReaccionPorComentario(comentario.getId(), "me-indigna"));
                }
            }

            for (Album album : listaAlbumesPropios) {
                album.setMeGusta(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "me-gusta"));
                album.setMeEncanta(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "me-encanta"));
                album.setMeh(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "meh"));
                album.setMeDisgusta(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "me-disgusta"));
                album.setMeIndigna(ServicioReaccion.getInstancia().encontrarReaccionPorAlbum(album.getId(), "me-indigna"));
            }

            List<Persona> amigos = new ArrayList<>();

            for (Persona per : usuario.getAmigos()) {
                amigos.add((Persona) ServicioUsuario.getInstancia().encontrarPersonaUsuario(per.getUsuario().getUsuario()));
            }

            usuario.setNotificaciones(ServicioNotificacion.getInstancia().buscarNotificacionesNoLeidas(usuario.getUsuario()));

            atributos.put("estaLogueado", req.session().attribute("sesionUsuario") != null);
            atributos.put("usuarios", ServicioUsuario.getInstancia().listar());
            atributos.put("usuario", usuario);
            atributos.put("nombreUsuario", nombreUsuario);
            atributos.put("persona", persona);
            atributos.put("listaPost", listaPostPropios);
            atributos.put("listaAlbum", listaAlbumesPropios);
            atributos.put("amigos", amigos);
            atributos.put("flashMessage", captureFlashMessage(req));

            template.process(atributos, writer);

            return writer;
        });

        post("/comentar", (req, res) -> {
            String comentario = req.queryParams("comentario");
            Long post = Long.parseLong(req.queryParams("post"));

            Post postAux = ServicioPost.getInstancia().encontrar(post);

            Comentario com = new Comentario(comentario, postAux, usuario, null, new Date(System.currentTimeMillis()));
            postAux.getComentarios().add(com);

            ServicioComentario.getInstancia().crear(com);
            ServicioPost.getInstancia().editar(postAux);

            return com.getId() + "," + com.getFecha().toString() + "," + postAux.getComentarios().size();
        });

        post("/comentarImagen", (req, res) -> {
            String comentario = req.queryParams("comentario");
            Long imagen = Long.parseLong(req.queryParams("imagen"));

            Imagen imagenAux = ServicioImagen.getInstancia().encontrar(imagen);

            Comentario com = new Comentario(comentario, imagenAux, usuario, new Date(System.currentTimeMillis()));
            imagenAux.getComentarios().add(com);

            ServicioComentario.getInstancia().crear(com);
            ServicioImagen.getInstancia().editar(imagenAux);

            return com.getId() + "," + com.getFecha().toString() + "," + imagenAux.getComentarios().size();
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

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private static void setFlashMessage(Request req, String message) {
        req.session().attribute(FLASH_MESSAGE_KEY, message);
    }

    private static String getFlashMessage(Request req) {
        if (req.session(false) == null) {
            return null;
        }

        if (!req.session().attributes().contains(FLASH_MESSAGE_KEY)) {
            return null;
        }

        return (String) req.session().attribute(FLASH_MESSAGE_KEY);
    }

    private static String captureFlashMessage(Request req) {
        String message = getFlashMessage(req);
        if (message != null) {
            req.session().removeAttribute(FLASH_MESSAGE_KEY);
        }
        return message;
    }
}
