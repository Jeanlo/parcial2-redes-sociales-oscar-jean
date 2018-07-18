<div class="card bg-dark text-white rounded-0 col-8 mx-auto p-0 mb-2">
    <div class="card-header">
        <small class="card-title">
            <i class="fas fa-user"></i> ${album.usuario.usuario}
            &nbsp;|&nbsp;
            <i class="fas fa-calendar-alt"></i> ${album.fecha?string.iso}
            <#if album.imagen1.personaEtiquetada?? || album.imagen2.personaEtiquetada?? || album.imagen3.personaEtiquetada??>
                &nbsp;|&nbsp;
                <i class="fas fa-tag"></i>
                <strong>Foto #1: </strong>
                ${album.imagen1.personaEtiquetada.nombre} ${album.imagen1.personaEtiquetada.apellido}
                &nbsp;|&nbsp;
                <strong>Foto #2: </strong>
                ${album.imagen2.personaEtiquetada.nombre} ${album.imagen2.personaEtiquetada.apellido}
                &nbsp;|&nbsp;
                <strong>Foto #3: </strong>
                ${album.imagen3.personaEtiquetada.nombre} ${album.imagen3.personaEtiquetada.apellido}
            </#if>
        </small>
    </div>
    <div class="card-body">
        <p class="card-text">
            <span class="alert alert-warning rounded-0 m-0">
                <strong><em>Album:</em></strong> ${album.descripcion}
            </span>
            <br>
            <#if album.imagen1??>
                <img src="/${album.imagen1.url}" class="imagen mb-2" width="860" title="${album.imagen1.descripcion}"
                     alt="${album.imagen1.descripcion}">
            <span class="alert alert-secondary rounded-0 m-0">
                <strong><em>Descripción:</em></strong> ${album.imagen1.descripcion}
            </span>
            <br>
            <br>
            </#if>
            <#if album.imagen2??>
            <img src="/${album.imagen2.url}" class="imagen mb-2" width="860" title="${album.imagen2.descripcion}"
                 alt="${album.imagen2.descripcion}">
            <span class="alert alert-secondary rounded-0 m-0">
                <strong><em>Descripción:</em></strong> ${album.imagen2.descripcion}
            </span>
            <br>
            <br>
            </#if>
            <#if album.imagen3??>
            <img src="/${album.imagen3.url}" class="imagen mb-2" width="860" title="${album.imagen3.descripcion}"
                 alt="${album.imagen3.descripcion}">
            <span class="alert alert-secondary rounded-0 m-0">
                <strong><em>Descripción:</em></strong> ${album.imagen3.descripcion}
            </span>
            <br>
            <br>
            </#if>
        </p>
    </div>
    <div class="card-footer">

    </div>
    <form action="/comentar" method="POST" id="agregaComentario"></form>
</div>

<script src="/js/jquery-3.2.1.min.js"></script>
<script>
    $(document).ready(function () {
        $(".comentario-box").hide();
        $(".comentarios").hide();

        $(".btn-mostrar-comentarios").unbind().click(function () {
            $("#comentario-box-" + $(this).data("id")).toggle();
            $("#listaComentarios-" + $(this).data("id")).toggle();
        });
    });
</script>