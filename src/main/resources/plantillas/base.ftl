<#macro pagina>
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
    <link rel="stylesheet" href="/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
          crossorigin="anonymous">
    <script src="/js/bootstrap.min.js"
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
                        <a class="navbar-brand" href="index.html">bacano</a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item active">
                                    <a class="nav-link" href="perfil.html">
                                        <i class="fas fa-user"></i> Perfil
                                        <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">
                                        <i class="fas fa-users"></i> Amigos</a>
                                </li>
                            </ul>
                            <button class="btn btn-outline-warning my-2 my-sm-0" data-toggle="modal"
                                    data-target="#exampleModal" type="button">
                                <i class="fas fa-feather-alt"></i> Bacanear
                            </button>
                            &nbsp;&nbsp;
                            <button class="btn btn-outline-danger text-white my-2 my-sm-0" type="submit">
                                <i class="fas fa-sign-out-alt"></i> Salir
                            </button>
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