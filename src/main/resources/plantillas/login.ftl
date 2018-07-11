<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0">
                <div class="bg-dark text-white rounded-0 col-6 p-0 mb-5 mt-3 mr-3 float-right">
                    <h5 class="card-header text-center">
                        Registrarse
                    </h5>
                    <div class="card-body">
                        <form action="/registrar" method="POST">
                            <div class="form-row">
                                <div class="form-group col-6">
                                    <label for="nombre">Nombre</label>
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">
                                                <i class="fas fa-id-card"></i>
                                            </div>
                                        </div>
                                        <input type="text" class="form-control" name="nombre" placeholder="Nombre">
                                    </div>
                                </div>
                                <div class="form-group col-6">
                                    <label for="apellido">Apellido</label>
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">
                                                <i class="fas fa-id-card"></i>
                                            </div>
                                        </div>
                                        <input type="text" class="form-control" name="apellido" placeholder="Apellido">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nacionalidad">Nacionalidad</label>
                                <div class="input-group mb-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">
                                            <i class="fas fa-globe-americas"></i>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" name="nacionalidad"
                                           placeholder="Nacionalidad">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="sexo">Sexo</label>
                                <br>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sexo" value="M">
                                    <label class="form-check-label" for="sexo">Masculino</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sexo" value="F">
                                    <label class="form-check-label" for="sexo">Femenino</label>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-6">
                                    <label for="usuario">Usuario</label>
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">
                                                <i class="fas fa-user"></i>
                                            </div>
                                        </div>
                                        <input type="text" class="form-control" name="usuario" placeholder="Usuario">
                                    </div>
                                </div>
                                <div class="form-group col-6">
                                    <label for="contrasena">Contraseña</label>
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">
                                                <i class="fas fa-lock"></i>
                                            </div>
                                        </div>
                                        <input type="password" class="form-control" name="contrasena"
                                               placeholder="Contraseña">
                                    </div>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-outline-warning">
                                <i class="fas fa-angle-right"></i> Registrarse
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@base.pagina>