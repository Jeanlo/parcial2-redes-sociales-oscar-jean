<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado usuario=usuario>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0">
                <br>
                <#list listaPost as post>
                    <div class="bg-dark text-white rounded-0 col-6 mx-auto p-0 mb-2">
                        <div class="card-header">
                            ${post.usuario.usuario} |
                            <i class="fas fa-calendar-alt"></i> ${post.fecha}
                        </div>
                        <div class="card-body">
                            <p class="card-text">
                                ${post.texto}
                            </p>
                        </div>
                        <div class="card-footer">
                            <button class="btn btn-reaccion" data-tipo="me-gusta" data-id="${post.id}">
                                <i class="far fa-thumbs-up fa-2x" style="color: rgb(55, 175, 255)" title="Me gusta"></i>
                                <span class="badge badge-secondary" id="badge-me-gusta-${post.id}">0</span>
                            </button>
                            <button class="btn btn-reaccion" data-tipo="me-encanta" data-id="${post.id}">
                                <i class="far fa-grin-beam fa-2x" style="color: rgb(255, 94, 180)"
                                   title="Me encanta"></i>
                                <span class="badge badge-secondary" id="badge-me-encanta-${post.id}">0</span>
                            </button>
                            <button class="btn btn-reaccion" data-tipo="meh" data-id="${post.id}">
                                <i class="far fa-meh fa-2x" style="color: rgb(255, 158, 41)" title="Meh"></i>
                                <span class="badge badge-secondary" id="badge-meh-${post.id}">0</span>
                            </button>
                            <button class="btn btn-reaccion" data-tipo="me-disgusta" data-id="${post.id}">
                                <i class="far fa-frown fa-2x" style="color: rgb(255, 75, 75)" title="Me disgusta"></i>
                                <span class="badge badge-secondary" id="badge-me-disgusta-${post.id}">0</span>
                            </button>
                            <button class="btn btn-reaccion" data-tipo="me-indigna" data-id="${post.id}">
                                <i class="fas fa-poo fa-2x" style="color: rgb(214, 135, 79)" title="Me indigna"></i>
                                <span class="badge badge-secondary" id="badge-me-indigna-${post.id}">0</span>
                            </button>
                        </div>
                    </div>
                </#list>

            </div>
        </div>
    </div>
    <#include "modal-bacanear.ftl">

    <form action="/reaccionar" method="POST" id="form-reaccionar"></form>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/reaccionar.js"></script>
</@base.pagina>