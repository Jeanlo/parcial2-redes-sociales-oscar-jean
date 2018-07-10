<#macro pagina logueado=false>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>bacano</title>

    <!-- jQuery -->
    <script src="/js/jquery-3.2.1.min.js"></script>

    <!-- Bootstrap 4.1 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>

    <!-- Font Awesome 5.1 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt"
          crossorigin="anonymous">

    <!-- Estilos propios -->
    <link rel="stylesheet" href="/css/estilo.css">
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <div class="row">
                <div class="col-12 p-0">
                    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                        <a class="navbar-brand" href="/">bacano</a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <#if logueado>
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item active">
                                    <a class="nav-link" href="/perfil">
                                        <i class="fas fa-user"></i> Perfil
                                        <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/amigos">
                                        <i class="fas fa-users"></i> Amigos</a>
                                </li>
                            </ul>
                            <button class="btn btn-outline-warning my-2 my-sm-0" data-toggle="modal"
                                    data-target="#exampleModal" type="button">
                                <i class="fas fa-feather-alt"></i> Bacanear
                            </button>
                            &nbsp;&nbsp;
                            <a href="/salir" class="btn btn-outline-danger text-white my-2 my-sm-0" type="submit">
                                <i class="fas fa-sign-out-alt"></i> Salir
                            </a>
                        <#else>
                            <ul class="navbar-nav mr-auto">
                            </ul>
                            <form action="/login" method="POST" class="form-inline">
                                <div class="input-group mr-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">
                                            <i class="fas fa-user"></i>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" name="usuario" placeholder="Usuario">
                                </div>
                                <div class="input-group mr-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">
                                            <i class="fas fa-lock"></i>
                                        </div>
                                    </div>
                                    <input type="password" class="form-control" name="contrasena" placeholder="Contraseña">
                                </div>
                                <input class="form-check-input" type="radio" name="guardarSesion">
                                <label class="form-check-label text-white mr-2" for="guardarSesion">
                                    <strong>Guardar sesión</strong>
                                </label>
                                <button type="submit" class="btn btn-outline-warning">
                                    <i class="fas fa-sign-in-alt"></i> Acceder
                                </button>
                            </form>
                        </#if>
                        </div>
                    </nav>
                </div>
            </div>
            <#nested>
        </div>
    </div>
</div>
</body>

</html>
</#macro>