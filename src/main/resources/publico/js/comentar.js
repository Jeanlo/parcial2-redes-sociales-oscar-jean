$(document).ready(function () {
    $(".btn-comentar").unbind().click(function () {
        var ruta = $("#agregaComentario").attr("action");
        var id = $(this).data("id");
        var texto = $('#texto-' + id).val();
        var usuario = $(this).data("usuario");

        $.ajax({
            url: ruta,
            type: "POST",
            data: {
                post: id,
                comentario: texto,
                usuario: usuario
            },
            success: function (datos) {
                $('#texto-' + id).val("");

                var valores = datos.split(",");

                $('#listaComentarios-' + id).append("<div class=\"alert alert-secondary rounded-0 m-0\">\n" +
                    "\t<small>\n" +
                    "\t\t<strong>\n" +
                    "\t\t\t<i class=\"fas fa-user-circle\"></i> " + usuario + "\n" +
                    "\t\t\t&nbsp;|&nbsp;\n" +
                    "\t\t\t<i class=\"fas fa-calendar-alt\"></i> " + valores[1] + "\n" +
                    "\t\t</strong>\n" +
                    "\t</small>\n" +
                    "\t<p class=\"my-2\">" + texto + "</p>\n" +
                    "\t<div class=\"alert alert-dark rounded-0 m-0\">\n" +
                    "\t\t<button class=\"btn btn-reaccion-comentario\" data-tipo=\"me-gusta\"\n" +
                    "\t\t\t\tdata-id='" + valores[0] + "'>\n" +
                    "\t\t\t<i class=\"far fa-thumbs-up fa-lg\" style=\"color: rgb(55, 175, 255)\" title=\"Me gusta\"></i>\n" +
                    "\t\t\t<span class=\"badge badge-secondary\"\n" +
                    "\t\t\t\t  id=\"badge-me-gusta-comentario-" + valores[0] + "\">0</span>\n" +
                    "\t\t</button>\n" +
                    "\t\t<button class=\"btn btn-reaccion-comentario\" data-tipo=\"me-encanta\"\n" +
                    "\t\t\t\tdata-id='" + valores[0] + "'>\n" +
                    "\t\t\t<i class=\"far fa-grin-beam fa-lg\" style=\"color: rgb(255, 94, 180)\"\n" +
                    "\t\t\t   title=\"Me encanta\"></i>\n" +
                    "\t\t\t<span class=\"badge badge-secondary\"\n" +
                    "\t\t\t\t  id=\"badge-me-encanta-comentario-" + valores[0] + "\">0</span>\n" +
                    "\t\t</button>\n" +
                    "\t\t<button class=\"btn btn-reaccion-comentario\" data-tipo=\"meh\" data-id='" + valores[0] + "'>\n" +
                    "\t\t\t<i class=\"far fa-meh fa-lg\" style=\"color: rgb(255, 158, 41)\" title=\"Meh\"></i>\n" +
                    "\t\t\t<span class=\"badge badge-secondary\"\n" +
                    "\t\t\t\t  id=\"badge-meh-comentario-" + valores[0] + "\">0</span>\n" +
                    "\t\t</button>\n" +
                    "\t\t<button class=\"btn btn-reaccion-comentario\" data-tipo=\"me-disgusta\"\n" +
                    "\t\t\t\tdata-id='" + valores[0] + "'>\n" +
                    "\t\t\t<i class=\"far fa-frown fa-lg\" style=\"color: rgb(255, 75, 75)\" title=\"Me disgusta\"></i>\n" +
                    "\t\t\t<span class=\"badge badge-secondary\"\n" +
                    "\t\t\t\t  id=\"badge-me-disgusta-comentario-" + valores[0] + "\">0</span>\n" +
                    "\t\t</button>\n" +
                    "\t\t<button class=\"btn btn-reaccion-comentario\" data-tipo=\"me-indigna\"\n" +
                    "\t\t\t\tdata-id='" + valores[0] + "'>\n" +
                    "\t\t\t<i class=\"fas fa-poo fa-lg\" style=\"color: rgb(214, 135, 79)\" title=\"Me indigna\"></i>\n" +
                    "\t\t\t<span class=\"badge badge-secondary\"\n" +
                    "\t\t\t\t  id=\"badge-me-indigna-comentario-" + valores[0] + "\">0</span>\n" +
                    "\t\t</button>\n" +
                    "\t</div>\n" +
                    "</div>");

                $("#badge-cantidad-comentarios-" + id).html(valores[2]);

                notifyMe("COMENTASTE", "¡Has comentado a este post!");
            }
        });
    });

    $(".btn-comentar-imagen").unbind().click(function () {
        var ruta = $("#agregaComentarioImagen").attr("action");
        var id = $(this).data("id");
        var texto = $('#texto-imagen-' + id).val();
        var usuario = $(this).data("usuario");

        $.ajax({
            url: ruta,
            type: "POST",
            data: {
                imagen: id,
                comentario: texto,
                usuario: usuario
            },
            success: function (datos) {
                $('#texto-imagen-' + id).val("");

                var valores = datos.split(",");

                $('#listaComentariosImagen-' + id).append("<div class=\"alert alert-secondary rounded-0 m-0\">\n" +
                    "\t<small>\n" +
                    "\t\t<strong>\n" +
                    "\t\t\t<i class=\"fas fa-user-circle\"></i> " + usuario + "\n" +
                    "\t\t\t&nbsp;|&nbsp;\n" +
                    "\t\t\t<i class=\"fas fa-calendar-alt\"></i> " + valores[1] + "\n" +
                    "\t\t</strong>\n" +
                    "\t</small>\n" +
                    "\t<p class=\"my-2\">" + texto + "</p>\n" +
                    "</div>");

                $("#badge-cantidad-comentarios-imagen-" + id).html(valores[2]);

                notifyMe("COMENTASTE", "¡Has comentado a esta imagen!");
            }
        });
    });
});

function notifyMe(titulo, cuerpo) {
    if (Notification.permission !== "granted")
        Notification.requestPermission();
    else {
        var notification = new Notification(titulo, {
            icon: 'img/bacano.png',
            body: cuerpo
        });

        setTimeout(function(){
            notification.close();
        }, 2500);
    }
}