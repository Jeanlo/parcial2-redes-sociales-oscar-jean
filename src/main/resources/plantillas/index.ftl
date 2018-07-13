<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado usuario=usuario>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0">
                <br>
                <#list listaPost as post>
                    <#include "post.ftl">
                </#list>
            </div>
        </div>
    </div>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <form action="/reaccionar" method="POST" id="form-reaccionar"></form>
    <script src="/js/reaccionar.js"></script>
    <#include "modal-bacanear.ftl">
</@base.pagina>