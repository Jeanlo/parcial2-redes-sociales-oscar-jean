<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado usuario=usuario>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0">
                <br>
                <#list usuarios as usuario>
                    <#if !usuario.administrator>
                      <div class="bg-dark text-white rounded-0 col-3 mx-auto p-0 mb-2">
                          <div class="card-header"><strong>Usuario: </strong>
                              <span class="text-warning">${usuario.usuario}</span>
                          </div>
                          <div class="card-body">
                              <form action="/hacerAdmin/${usuario.usuario}" method="POST">
                                  <button type="submit" class="btn btn-outline-warning">
                                      <i class="fas fa-unlock-alt"></i> Subir priviligeos
                                  </button>
                              </form>
                          </div>
                      </div>
                    <#else>
                          <div class="bg-dark text-white rounded-0 col-3 mx-auto p-0 mb-2">
                              <div class="card-header"><strong>Usuario administrador: </strong>
                                  <span class="text-warning">${usuario.usuario}</span>
                              </div>
                          </div>
                    </#if>
                </#list>
            </div>
        </div>
    </div>
</@base.pagina>