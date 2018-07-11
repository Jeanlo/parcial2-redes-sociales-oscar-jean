<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado usuario=usuario>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0">
                <br>
                <#list usuario.amigos as usu>
                    <div class="bg-dark text-white rounded-0 col-3 mx-auto p-0 mb-2">
                        <div class="card-header"><strong>Usuario amigo: </strong>
                            <span class="text-warning">${usu.usuario}</span>
                        </div>
                    </div>
                </#list>
                <#list usuariosNoAmigos as usua>
                    <#if usua.usuario != usuario.usuario>
                         <div class="bg-dark text-white rounded-0 col-3 mx-auto p-0 mb-2">
                             <div class="card-header"><strong>Usuario: </strong>
                                 <span class="text-warning">${usua.usuario}</span>
                             </div>
                             <div class="card-body">
                                 <form action="/agregarAmigo/${usua.usuario}" method="POST">
                                     <button type="submit" class="btn btn-outline-warning">
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
</@base.pagina>