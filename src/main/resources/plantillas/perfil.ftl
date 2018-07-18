<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado usuario=usuario persona=persona>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0">
                <div class="jumbotron jumbotron-fluid"
                     style="background: url('/img/cover_admin.png') no-repeat; background-size: cover;">
                    <div class="container">
                        <h1 class="display-4 text-white text-center">${persona.nombre} ${persona.apellido} </h1>
                        <img src="https://i.imgur.com/hpta335.jpg" class="rounded-circle" alt="">
                    </div>
                </div>
                <div class="card bg-dark text-white rounded-0 col-12 p-0 mb-5">
                    <h5 class="card-header text-center">
                        Información personal
                    </h5>
                    <div class="card-body">
                        <p class="card-text text-center">
                            <i class="fas fa-user"></i> ${persona.nombre} ${persona.apellido} &nbsp;|&nbsp;
                            <i class="fas fa-user-circle"></i> ${persona.usuario.usuario} &nbsp;|&nbsp;
                            <i class="fas fa-map-marker-alt"></i> ${persona.nacionalidad} &nbsp;|&nbsp;
                            <i class="fas fa-link"></i>
                            <a href="https://${persona.sitioWeb}" target="_blank">${persona.sitioWeb}</a>
                            &nbsp;|&nbsp;
                            <i class="fas fa-calendar"></i> Se unió el ${persona.fechaRegistro?date}
                        </p>
                    </div>
                </div>
                <#if listaPost?size gt 0>
                    <h4 class="text-white text-center">Posts</h4>
                </#if>
                 <#list listaPost as post>
                     <#include "post.ftl">
                 </#list>
                <br>
                <#if listaAlbum?size gt 0>
                    <h4 class="text-white text-center">Albumes</h4>
                </#if>
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