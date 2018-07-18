<div class="center-text" style="text-align:center;">
    <input type="text" name="usuario" id="usuario-affectiva" class="form-control" placeholder="usuario">
    <br>
    <div class="btn-group btn-group-lg" role="group" aria-label="Basic example">
        <button id="start" type="button" class="btn btn-light btn-sm" onclick="onStart()">Capturar emoción</button>
        <button id="stop" type="button" class="btn btn-outline-light btm-sm" onclick="onStop()">Loguearse</button>
    </div>
</div>
<br>
<div id="affdex_elements" class="mx-auto text-center" style="width: 835px; height: 620px"></div>
<div class="alert alert-secondary mt-0 float-right alert-flotante">
    <div id="results"></div>
</div>


<form action="/login-affectiva" method="POST" id="form-loguear-affectiva"></form>
<form action="/" method="GET" id="form-autentificar"></form>

<script src="https://download.affectiva.com/js/3.2/affdex.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="/js/affectiva.js"></script>