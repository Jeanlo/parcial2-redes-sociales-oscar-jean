<div class="card bg-dark text-white rounded-0 col-6 mx-auto p-0 mb-2">
    <div class="card-header">
        <small class="card-title">
            <i class="fas fa-user"></i> ${post.usuario.usuario}
            &nbsp;|&nbsp;
            <i class="fas fa-calendar-alt"></i> ${post.fecha?string.iso}
            <#if post.personaEtiquetada??>
                &nbsp;|&nbsp;
                <i class="fas fa-tag"></i>
                ${post.personaEtiquetada.nombre} ${post.personaEtiquetada.apellido}
            </#if>
        </small>
    </div>
    <div class="card-body">
        <p class="card-text">
        <#if post.imagen??>
            <img src="/${post.imagen.url}" class="imagen mb-2" height="360" title="${post.imagen.descripcion}" alt="${post.imagen.descripcion}">
        <br>
        </#if>
        ${post.texto}
        </p>
    </div>
    <div class="card-footer">
        <button class="btn btn-reaccion" data-tipo="me-gusta" data-id="${post.id?string['0']}">
            <i class="far fa-thumbs-up fa-lg" style="color: rgb(55, 175, 255)" title="Me gusta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-gusta-${post.id?string['0']}">${post.cantidadMeGusta}</span>
        </button>
        <button class="btn btn-reaccion" data-tipo="me-encanta" data-id="${post.id?string['0']}">
            <i class="far fa-grin-beam fa-lg" style="color: rgb(255, 94, 180)"
               title="Me encanta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-encanta-${post.id?string['0']}">${post.cantidadMeEncanta}</span>
        </button>
        <button class="btn btn-reaccion" data-tipo="meh" data-id="${post.id?string['0']}">
            <i class="far fa-meh fa-lg" style="color: rgb(255, 158, 41)" title="Meh"></i>
            <span class="badge badge-secondary"
                  id="badge-meh-${post.id?string['0']}">${post.cantidadMeh}</span>
        </button>
        <button class="btn btn-reaccion" data-tipo="me-disgusta" data-id="${post.id?string['0']}">
            <i class="far fa-frown fa-lg" style="color: rgb(255, 75, 75)" title="Me disgusta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-disgusta-${post.id?string['0']}">${post.cantidadMeDisgusta}</span>
        </button>
        <button class="btn btn-reaccion" data-tipo="me-indigna" data-id="${post.id?string['0']}">
            <i class="fas fa-poo fa-lg" style="color: rgb(214, 135, 79)" title="Me indigna"></i>
            <span class="badge badge-secondary"
                  id="badge-me-indigna-${post.id?string['0']}">${post.cantidadMeIndigna}</span>
        </button>
        <button type="button" class="btn btn-sm btn-outline-light float-right btn-mostrar-comentarios"
                data-id="${post.id?string['0']}">
            Comentarios <span class="badge badge-secondary"
                              id="badge-cantidad-comentarios-${post.id?string['0']}">${post.comentarios?size}</span>
        </button>
    </div>

    <div class="alert alert-dark rounded-0 m-0 comentario-box" id="comentario-box-${post.id?string['0']}">
        <textarea placeholder="Comenta tu opinión" class="comentario form-control rounded-0"
                  id="texto-${post.id?string['0']}"></textarea>
        <button type="submit" class="btn btn-outline-dark rounded-0 btn-comentar mt-3" data-id="${post.id?string['0']}"
                data-usuario="${usuario.usuario}">
            <i class="fas fa-paper-plane"></i> Comentar
        </button>
    </div>

    <div class="comentarios" id="listaComentarios-${post.id?string['0']}">
        <#list post.comentarios as comentario>
            <div class="alert alert-secondary rounded-0 m-0">
                <small>
                    <strong>
                        <i class="fas fa-user-circle"></i> ${comentario.usuario.usuario}
                        &nbsp;|&nbsp;
                        <i class="fas fa-calendar-alt"></i> ${comentario.fecha?string.iso}
                    </strong>
                </small>
                <p class="my-2">${comentario.texto}</p>
                <div class="alert alert-dark rounded-0 m-0">
                    <button class="btn btn-reaccion-comentario" data-tipo="me-gusta"
                            data-id="${comentario.id?string['0']}">
                        <i class="far fa-thumbs-up fa-lg" style="color: rgb(55, 175, 255)" title="Me gusta"></i>
                        <span class="badge badge-secondary"
                              id="badge-me-gusta-comentario-${comentario.id?string['0']}">${comentario.cantidadMeGusta}</span>
                    </button>
                    <button class="btn btn-reaccion-comentario" data-tipo="me-encanta"
                            data-id="${comentario.id?string['0']}">
                        <i class="far fa-grin-beam fa-lg" style="color: rgb(255, 94, 180)"
                           title="Me encanta"></i>
                        <span class="badge badge-secondary"
                              id="badge-me-encanta-comentario-${comentario.id?string['0']}">${comentario.cantidadMeEncanta}</span>
                    </button>
                    <button class="btn btn-reaccion-comentario" data-tipo="meh" data-id="${comentario.id?string['0']}">
                        <i class="far fa-meh fa-lg" style="color: rgb(255, 158, 41)" title="Meh"></i>
                        <span class="badge badge-secondary"
                              id="badge-meh-comentario-${comentario.id?string['0']}">${comentario.cantidadMeh}</span>
                    </button>
                    <button class="btn btn-reaccion-comentario" data-tipo="me-disgusta"
                            data-id="${comentario.id?string['0']}">
                        <i class="far fa-frown fa-lg" style="color: rgb(255, 75, 75)" title="Me disgusta"></i>
                        <span class="badge badge-secondary"
                              id="badge-me-disgusta-comentario-${comentario.id?string['0']}">${comentario.cantidadMeDisgusta}</span>
                    </button>
                    <button class="btn btn-reaccion-comentario" data-tipo="me-indigna"
                            data-id="${comentario.id?string['0']}">
                        <i class="fas fa-poo fa-lg" style="color: rgb(214, 135, 79)" title="Me indigna"></i>
                        <span class="badge badge-secondary"
                              id="badge-me-indigna-comentario-${comentario.id?string['0']}">${comentario.cantidadMeIndigna}</span>
                    </button>
                </div>
            </div>
        </#list>
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