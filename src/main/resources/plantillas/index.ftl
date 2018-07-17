<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado usuario=usuario>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0">
                <br>
                <#list listaPost as post>
                    <#include "post.ftl">
                </#list>
                <#list listaAlbum as album>
                    <#include "album.ftl">
                </#list>

            </div>
        </div>
    </div>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <form action="/reaccionar" method="POST" id="form-reaccionar"></form>
    <form action="/reaccionarComentario" method="POST" id="form-reaccionar-comentario"></form>
    <script src="/js/reaccionar.js"></script>
    <script src="/js/comentar.js"></script>
    <#include "modal-notificaciones.ftl">
    <#include "modal-bacanear.ftl">
    <#include "modal-album.ftl">
</@base.pagina>