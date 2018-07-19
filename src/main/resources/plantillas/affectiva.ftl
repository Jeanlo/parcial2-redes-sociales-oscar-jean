<div class="form-row">
    <div class="form-group col-4">
        <input type="text" name="usuario" id="usuario-affectiva" class="form-control" placeholder="usuario">
    </div>
    <div class="form-group col-8">
        <div class="btn-group" role="group" aria-label="Basic example">
            <button id="start" type="button" class="btn btn-light btn-sm" onclick="onStart()">
                <i class="far fa-grin-wink"></i> Capturar emoción
            </button>
            <button id="stop" type="button" class="btn btn-outline-light btm-sm" onclick="onStop()">
                <i class="fas fa-sign-in-alt"></i> Loguearse
            </button>
        </div>
    </div>
</div>

<div id="affdex_elements" style="width: 580px; height: 400px"></div>
<div class="alert rounded m-0 float-right alert-flotante">
    <div id="results" class="text-secondary"></div>
</div>

<form action="/login-affectiva" method="POST" id="form-loguear-affectiva"></form>
<form action="/" method="GET" id="form-autentificar"></form>

<script src="https://download.affectiva.com/js/3.2/affdex.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="/js/affectiva.js"></script>