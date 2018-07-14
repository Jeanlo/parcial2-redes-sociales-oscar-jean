<div class="modal fade" id="notificaciones" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content bg-dark text-white">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Notificaciones</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" class="text-white">
              <i class="fas fa-times-circle"></i>
            </span>
                </button>
            </div>
            <div class="modal-body p-0">
                <#if usuario.notificaciones?size gt 0>
                    <#list usuario.notificaciones as notificacion>
                        <div class="alert alert-dark rounded-0 m-0">
                            <small>
                                <i class="fas fa-calendar-alt"></i> ${notificacion.fecha}<br>
                                <a href="/perfil/${usuario.usuario}" class="alert-link">
                                    ${notificacion.texto}
                                </a>
                            </small>
                        </div>
                    </#list>
                <#else>
                    <div class="alert alert-dark rounded-0 m-0">
                        No tienes ninguna notificación.
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>
