<div class="card bg-dark text-white rounded-0 col-8 mx-auto p-0 mb-2">
    <div class="card-header">
        <small class="card-title">
            <i class="fas fa-user"></i> ${album.usuario.usuario}
            &nbsp;|&nbsp;
            <i class="fas fa-calendar-alt"></i> ${album.fecha?string.iso}
            <#if album.imagen1?? && album.imagen1.personaEtiquetada??>
                &nbsp;|&nbsp;
                <i class="fas fa-tag"></i>
                <strong>Foto #1: </strong>
                ${album.imagen1.personaEtiquetada.nombre} ${album.imagen1.personaEtiquetada.apellido}
            </#if>
            <#if album.imagen2?? && album.imagen2.personaEtiquetada??>
                &nbsp;|&nbsp;
                <strong>Foto #2: </strong>
                ${album.imagen2.personaEtiquetada.nombre} ${album.imagen2.personaEtiquetada.apellido}
            </#if>
            <#if album.imagen3?? && album.imagen3.personaEtiquetada??>
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
            <#if album.imagen1.descripcion?length gt 0>
                <span class="alert alert-secondary rounded-0 m-0">
                    <strong><em>Descripción:</em></strong> ${album.imagen1.descripcion}
                </span>
            </#if>
            <button type="button" class="btn btn-sm btn-outline-light float-right btn-mostrar-comentarios-imagen"
                    data-id="${album.imagen1.id?string['0']}">
                Comentarios <span class="badge badge-secondary"
                                  id="badge-cantidad-comentarios-imagen-${album.imagen1.id?string['0']}">${album.imagen1.comentarios?size}</span>
            </button>
            <div class="alert alert-dark rounded-0 m-0 comentario-box" id="comentario-box-imagen-${album.imagen1.id?string['0']}">
                <textarea placeholder="Comenta tu opinión" class="comentario form-control rounded-0"
                  id="texto-imagen-${album.imagen1.id?string['0']}"></textarea>
                <button type="submit" class="btn btn-outline-dark rounded-0 btn-comentar-imagen mt-3" data-id="${album.imagen1.id?string['0']}"
                        data-usuario="${usuario.usuario}">
                    <i class="fas fa-paper-plane"></i> Comentar
                </button>
            </div>
            <div class="comentarios" id="listaComentariosImagen-${album.imagen1.id?string['0']}">
                <#list album.imagen1.comentarios as comentario>
                    <div class="alert alert-secondary rounded-0 m-0">
                        <small>
                            <strong>
                                <i class="fas fa-user-circle"></i> ${comentario.usuario.usuario}
                                &nbsp;|&nbsp;
                                <i class="fas fa-calendar-alt"></i> ${comentario.fecha?string.iso}
                            </strong>
                        </small>
                        <p class="my-2">${comentario.texto}</p>
                    </div>
                </#list>
            </div>
            <br>
            <br>
        </#if>
        <#if album.imagen2??>
            <img src="/${album.imagen2.url}" class="imagen mb-2" width="860" title="${album.imagen2.descripcion}"
                 alt="${album.imagen2.descripcion}">
            <#if album.imagen2.descripcion?length gt 0>
                <span class="alert alert-secondary rounded-0 m-0">
                    <strong><em>Descripción:</em></strong> ${album.imagen2.descripcion}
                </span>
            </#if>
            <button type="button" class="btn btn-sm btn-outline-light float-right btn-mostrar-comentarios-imagen"
                    data-id="${album.imagen2.id?string['0']}">
                Comentarios <span class="badge badge-secondary"
                                  id="badge-cantidad-comentarios-imagen-${album.imagen2.id?string['0']}">${album.imagen2.comentarios?size}</span>
            </button>
            <div class="alert alert-dark rounded-0 m-0 comentario-box" id="comentario-box-imagen-${album.imagen2.id?string['0']}">
                <textarea placeholder="Comenta tu opinión" class="comentario form-control rounded-0"
                          id="texto-imagen-${album.imagen2.id?string['0']}"></textarea>
                <button type="submit" class="btn btn-outline-dark rounded-0 btn-comentar-imagen mt-3" data-id="${album.imagen2.id?string['0']}"
                        data-usuario="${usuario.usuario}">
                    <i class="fas fa-paper-plane"></i> Comentar
                </button>
            </div>
            <div class="comentarios" id="listaComentariosImagen-${album.imagen2.id?string['0']}">
                <#list album.imagen2.comentarios as comentario>
                    <div class="alert alert-secondary rounded-0 m-0">
                        <small>
                            <strong>
                                <i class="fas fa-user-circle"></i> ${comentario.usuario.usuario}
                                &nbsp;|&nbsp;
                                <i class="fas fa-calendar-alt"></i> ${comentario.fecha?string.iso}
                            </strong>
                        </small>
                        <p class="my-2">${comentario.texto}</p>
                    </div>
                </#list>
            </div>
            <br>
            <br>
        </#if>
        <#if album.imagen3??>
            <img src="/${album.imagen3.url}" class="imagen mb-2" width="860" title="${album.imagen3.descripcion}"
                 alt="${album.imagen3.descripcion}">
            <#if album.imagen3.descripcion?length gt 0>
                <span class="alert alert-secondary rounded-0 m-0">
                    <strong><em>Descripción:</em></strong> ${album.imagen3.descripcion}
                </span>
            </#if>
            <button type="button" class="btn btn-sm btn-outline-light float-right btn-mostrar-comentarios-imagen"
                    data-id="${album.imagen3.id?string['0']}">
                Comentarios <span class="badge badge-secondary"
                                  id="badge-cantidad-comentarios-imagen-${album.imagen3.id?string['0']}">${album.imagen3.comentarios?size}</span>
            </button>
            <div class="alert alert-dark rounded-0 m-0 comentario-box" id="comentario-box-imagen-${album.imagen3.id?string['0']}">
                <textarea placeholder="Comenta tu opinión" class="comentario form-control rounded-0"
                          id="texto-imagen-${album.imagen3.id?string['0']}"></textarea>
                <button type="submit" class="btn btn-outline-dark rounded-0 btn-comentar-imagen mt-3" data-id="${album.imagen3.id?string['0']}"
                        data-usuario="${usuario.usuario}">
                    <i class="fas fa-paper-plane"></i> Comentar
                </button>
            </div>
            <div class="comentarios" id="listaComentariosImagen-${album.imagen3.id?string['0']}">
                <#list album.imagen3.comentarios as comentario>
                    <div class="alert alert-secondary rounded-0 m-0">
                        <small>
                            <strong>
                                <i class="fas fa-user-circle"></i> ${comentario.usuario.usuario}
                                &nbsp;|&nbsp;
                                <i class="fas fa-calendar-alt"></i> ${comentario.fecha?string.iso}
                            </strong>
                        </small>
                        <p class="my-2">${comentario.texto}</p>
                    </div>
                </#list>
            </div>
            <br>
            <br>
        </#if>
        </p>
    </div>

    <div class="card-footer">
        <button class="btn btn-reaccion-album" data-tipo="me-gusta" data-id="${album.id?string['0']}">
            <i class="far fa-thumbs-up fa-lg" style="color: rgb(55, 175, 255)" title="Me gusta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-gusta-album-${album.id?string['0']}">${album.cantidadMeGusta}</span>
        </button>
        <button class="btn btn-reaccion-album" data-tipo="me-encanta" data-id="${album.id?string['0']}">
            <i class="far fa-grin-beam fa-lg" style="color: rgb(255, 94, 180)"
               title="Me encanta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-encanta-album-${album.id?string['0']}">${album.cantidadMeEncanta}</span>
        </button>
        <button class="btn btn-reaccion-album" data-tipo="meh" data-id="${album.id?string['0']}">
            <i class="far fa-meh fa-lg" style="color: rgb(255, 158, 41)" title="Meh"></i>
            <span class="badge badge-secondary"
                  id="badge-meh-album-${album.id?string['0']}">${album.cantidadMeh}</span>
        </button>
        <button class="btn btn-reaccion-album" data-tipo="me-disgusta" data-id="${album.id?string['0']}">
            <i class="far fa-frown fa-lg" style="color: rgb(255, 75, 75)" title="Me disgusta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-disgusta-album-${album.id?string['0']}">${album.cantidadMeDisgusta}</span>
        </button>
        <button class="btn btn-reaccion-album" data-tipo="me-indigna" data-id="${album.id?string['0']}">
            <i class="fas fa-poo fa-lg" style="color: rgb(214, 135, 79)" title="Me indigna"></i>
            <span class="badge badge-secondary"
                  id="badge-me-indigna-album-${album.id?string['0']}">${album.cantidadMeIndigna}</span>
        </button>
    </div>
    <form action="/comentarImagen" method="POST" id="agregaComentarioImagen"></form>
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

        $(".btn-mostrar-comentarios-imagen").unbind().click(function () {
            $("#comentario-box-imagen-" + $(this).data("id")).toggle();
            $("#listaComentariosImagen-" + $(this).data("id")).toggle();
        });
    });
</script>