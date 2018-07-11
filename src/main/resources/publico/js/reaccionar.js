$(document).ready(function () {
    $(".btn-reaccion").unbind().click(function () {
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
            }
        });
    });
});