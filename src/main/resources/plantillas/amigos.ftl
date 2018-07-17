<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado usuario=usuario>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0">
                <br>
                <#if usuario.amigos?size gt 0>
                    <h5 class="text-white text-center">Amigos</h5>
                </#if>
                <#list usuario.amigos as usu>
                     <div class="alert alert-secondary rounded-0 m-0 col-4 mx-auto">
                         <div class="alert alert-dark rounded-0 m-0">
                             <small>
                                 <strong>
                                     <i class="fas fa-user-circle"></i> ${usu.nombre} ${usu.apellido}
                                 </strong>
                             </small>
                         </div>
                     </div>
                </#list>
                <br>
                <#if usuariosSugeridos?size gt 1>
                    <h5 class="text-white text-center">Agregar amigos</h5>
                </#if>
                <#list usuariosSugeridos as usua>
                    <#if usua.usuario.usuario != usuario.usuario>
                         <div class="alert alert-secondary rounded-0 m-0 col-4 mx-auto">
                             <div class="alert alert-dark rounded-0 m-0">
                                 <small>
                                     <strong>
                                         <i class="fas fa-user-circle"></i> ${usua.nombre} ${usua.apellido}
                                     </strong>
                                 </small>
                                 <form class="mt-2" action="/agregarAmigo/${usua.usuario.usuario}" method="POST">
                                     <button type="submit" class="btn btn-outline-dark btn-sm">
                                         <i class="fas fa-user-friends"></i> Agregar a amigo
                                     </button>
                                 </form>
                             </div>
                         </div>
                    </#if>
                </#list>
            </div>
        </div>
    </div>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/comentar.js"></script>
    <#include "modal-notificaciones.ftl">
    <#include "modal-bacanear.ftl">
    <#include "modal-album.ftl">
</@base.pagina>