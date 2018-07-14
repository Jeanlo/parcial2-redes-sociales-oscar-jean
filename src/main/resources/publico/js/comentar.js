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
                $('#listaComentarios-' + id).append('Usuario:' + usuario + 'comentario:' + datos);
            }
        });
    });
});