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
                $("#badge-" + tipo + "-" + id).html(datos);
            }
        });
    });
});