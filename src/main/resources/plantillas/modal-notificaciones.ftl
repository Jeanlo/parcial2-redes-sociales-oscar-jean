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
                        <#if notificacion.tipoNotificacion?contains("Etiquetacion")>
                        <div class="alert alert-dark rounded-0 m-0">
                            <small>
                                <i class="fas fa-calendar-alt"></i> ${notificacion.fecha}<br>
                                ${notificacion.texto}
                                <form action="/verPerfil/${notificacion.id?string['0']}" method="POST">
                                    <button class="btn btn-outline-dark" type="submit">Ver perfil</button>
                                </form>
                            </small>
                        </div>
                        <#else>
                        <div class="alert alert-dark rounded-0 m-0">
                            <small>
                                <i class="fas fa-calendar-alt"></i> ${notificacion.fecha}<br>
                                ${notificacion.texto}
                                <form action="/aceptarAmigo/${notificacion.desde.usuario}/${notificacion.id?string['0']}"
                                      method="POST">
                                    <button class="btn btn-outline-dark" type="submit">Aceptar amigo</button>
                                </form>
                            </small>
                        </div>
                        </#if>
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
