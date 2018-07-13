<div class="bg-dark text-white rounded-0 col-6 mx-auto p-0 mb-2">
    <div class="card-header">
        <small class="card-title">
            ${post.usuario.usuario}&nbsp;|&nbsp;
            <i class="fas fa-calendar-alt"></i>
            ${post.fecha}
            <#if post.personasEtiquetadas?size gt 0>
                &nbsp;|&nbsp;
                <i class="fas fa-tag"></i>
                <#list post.personasEtiquetadas as persona>
                    ${persona.nombre} ${persona.apellido}<#if !persona?is_last>, </#if>
                </#list>
            </#if>
        </small>
    </div>
    <div class="card-body">
        <p class="card-text">
        ${post.texto}
        </p>
    </div>
    <div class="card-footer">
        <button class="btn btn-reaccion" data-tipo="me-gusta" data-id="${post.id?string['0']}">
            <i class="far fa-thumbs-up fa-lg" style="color: rgb(55, 175, 255)" title="Me gusta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-gusta-${post.id?string['0']}">${post.cantidadMeGusta}</span>
        </button>
        <button class="btn btn-reaccion" data-tipo="me-encanta" data-id="${post.id?string['0']}">
            <i class="far fa-grin-beam fa-lg" style="color: rgb(255, 94, 180)"
               title="Me encanta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-encanta-${post.id?string['0']}">${post.cantidadMeEncanta}</span>
        </button>
        <button class="btn btn-reaccion" data-tipo="meh" data-id="${post.id?string['0']}">
            <i class="far fa-meh fa-lg" style="color: rgb(255, 158, 41)" title="Meh"></i>
            <span class="badge badge-secondary"
                  id="badge-meh-${post.id?string['0']}">${post.cantidadMeh}</span>
        </button>
        <button class="btn btn-reaccion" data-tipo="me-disgusta" data-id="${post.id?string['0']}">
            <i class="far fa-frown fa-lg" style="color: rgb(255, 75, 75)" title="Me disgusta"></i>
            <span class="badge badge-secondary"
                  id="badge-me-disgusta-${post.id?string['0']}">${post.cantidadMeDisgusta}</span>
        </button>
        <button class="btn btn-reaccion" data-tipo="me-indigna" data-id="${post.id?string['0']}">
            <i class="fas fa-poo fa-lg" style="color: rgb(214, 135, 79)" title="Me indigna"></i>
            <span class="badge badge-secondary"
                  id="badge-me-indigna-${post.id?string['0']}">${post.cantidadMeIndigna}</span>
        </button>
    </div>
</div>