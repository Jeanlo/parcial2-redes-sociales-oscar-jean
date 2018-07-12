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
                            <a href="https://manga-anime-empire.me">manga-anime-empire.me</a>
                            &nbsp;|&nbsp;
                            <i class="fas fa-calendar"></i> Se unió el ${persona.fechaRegistro}
                        </p>
                    </div>
                </div>
                 <#list listaPost as post>
                     <#if post.usuario.usuario == usuario.usuario>
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
                                      <span class="badge badge-secondary" id="badge-me-gusta-${post.id}">${post.cantidadMeGusta}</span>
                                  </button>
                                  <button class="btn btn-reaccion" data-tipo="me-encanta" data-id="${post.id}">
                                      <i class="far fa-grin-beam fa-2x" style="color: rgb(255, 94, 180)"
                                         title="Me encanta"></i>
                                      <span class="badge badge-secondary" id="badge-me-encanta-${post.id}">${post.cantidadMeEncanta}</span>
                                  </button>
                                  <button class="btn btn-reaccion" data-tipo="meh" data-id="${post.id}">
                                      <i class="far fa-meh fa-2x" style="color: rgb(255, 158, 41)" title="Meh"></i>
                                      <span class="badge badge-secondary" id="badge-meh-${post.id}">${post.cantidadMeh}</span>
                                  </button>
                                  <button class="btn btn-reaccion" data-tipo="me-disgusta" data-id="${post.id}">
                                      <i class="far fa-frown fa-2x" style="color: rgb(255, 75, 75)" title="Me disgusta"></i>
                                      <span class="badge badge-secondary" id="badge-me-disgusta-${post.id}">${post.cantidadMeDisgusta}</span>
                                  </button>
                                  <button class="btn btn-reaccion" data-tipo="me-indigna" data-id="${post.id}">
                                      <i class="fas fa-poo fa-2x" style="color: rgb(214, 135, 79)" title="Me indigna"></i>
                                      <span class="badge badge-secondary" id="badge-me-indigna-${post.id}">${post.cantidadMeIndigna}</span>
                                  </button>
                              </div>
                          </div>
                     </#if>
                 </#list>
            </div>
        </div>
    </div>
    <#include "modal-bacanear.ftl">
</@base.pagina>