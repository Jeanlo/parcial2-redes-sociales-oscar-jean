$(document).ready(function () {
    $(".btn-reaccion").unbind().on("click", function () {
        var ruta = $("#form-reaccionar").attr("action");
        var id = $(this).data("id");
        var tipo = $(this).data("tipo");

        $.ajax({
            url: ruta,
            type: "POST",
            data: {
                id: id,
                tipo: tipo
            },
            success: function (datos) {
                var cantidades = datos.split(",");

                $("#badge-me-gusta-" + id).html(parseInt(cantidades[0]));
                $("#badge-me-encanta-" + id).html(parseInt(cantidades[1]));
                $("#badge-meh-" + id).html(parseInt(cantidades[2]));
                $("#badge-me-disgusta-" + id).html(parseInt(cantidades[3]));
                $("#badge-me-indigna-" + id).html(parseInt(cantidades[4]));

                notifyMe("REACCIONASTE", "¡Le has dado a " + tipo.replace("-", " ") + " a este post!");
            }
        });
    });

    $(".btn-reaccion-album").unbind().on("click", function () {
        var ruta = $("#form-reaccionar-album").attr("action");
        var id = $(this).data("id");
        var tipo = $(this).data("tipo");

        $.ajax({
            url: ruta,
            type: "POST",
            data: {
                id: id,
                tipo: tipo
            },
            success: function (datos) {
                var cantidades = datos.split(",");

                $("#badge-me-gusta-album-" + id).html(parseInt(cantidades[0]));
                $("#badge-me-encanta-album-" + id).html(parseInt(cantidades[1]));
                $("#badge-meh-album-" + id).html(parseInt(cantidades[2]));
                $("#badge-me-disgusta-album-" + id).html(parseInt(cantidades[3]));
                $("#badge-me-indigna-album-" + id).html(parseInt(cantidades[4]));

                notifyMe("REACCIONASTE", "¡Le has dado a " + tipo.replace("-", " ") + " a este album!");
            }
        });
    });

    $(document).unbind().on("click", ".btn-reaccion-comentario", function () {
        var ruta = $("#form-reaccionar-comentario").attr("action");
        var id = $(this).data("id");
        var tipo = $(this).data("tipo");

        $.ajax({
            url: ruta,
            type: "POST",
            data: {
                id: id,
                tipo: tipo
            },
            success: function (datos) {
                var cantidades = datos.split(",");

                $("#badge-me-gusta-comentario-" + id).html(parseInt(cantidades[0]));
                $("#badge-me-encanta-comentario-" + id).html(parseInt(cantidades[1]));
                $("#badge-meh-comentario-" + id).html(parseInt(cantidades[2]));
                $("#badge-me-disgusta-comentario-" + id).html(parseInt(cantidades[3]));
                $("#badge-me-indigna-comentario-" + id).html(parseInt(cantidades[4]));

                notifyMe("REACCIONASTE", "¡Le has dado a " + tipo.replace("-", " ") + " a este comentario!");
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