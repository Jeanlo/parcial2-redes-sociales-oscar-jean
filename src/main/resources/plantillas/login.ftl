<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado>
    <div class="col p-0">
        <div class="row">
            <div class="col-12 p-0 mt-3">
                <div class="row">
                    <div class="card bg-secondary text-white rounded-0 col-6 p-0">
                        <div class="card-body">
                        <#include "affectiva.ftl">
                        </div>
                    </div>
                    <div class="card bg-dark text-white rounded-0 col-6 p-0">
                        <h5 class="card-header text-center">
                            Registrarse
                        </h5>
                        <div class="card-body">
                            <form action="/registrar" method="POST" id="form-registrar">
                                <div class="form-row">
                                    <div class="form-group col-6">
                                        <label for="nombre">Nombre</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="fas fa-id-card"></i>
                                                </div>
                                            </div>
                                            <input type="text" class="form-control" name="nombre" placeholder="Nombre"
                                                   maxlength="30" required>
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
                                            <input type="text" class="form-control" name="apellido" placeholder="Apellido"
                                                   maxlength="30" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-6">
                                        <label for="fecha-nacimiento">Fecha de nacimiento</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="fas fa-calendar-alt"></i>
                                                </div>
                                            </div>
                                            <input type="date" class="form-control" name="fecha-nacimiento" required>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="nacionalidad">Nacionalidad</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="fas fa-globe-americas"></i>
                                                </div>
                                            </div>
                                            <input type="text" class="form-control" name="nacionalidad"
                                                   placeholder="Nacionalidad" maxlength="30" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group col-12">
                                    <label for="sexo">Sexo</label>
                                    <br>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="sexo" value="M" required>
                                        <label class="form-check-label" for="sexo">Masculino</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="sexo" value="F" required>
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
                                            <input type="text" class="form-control" name="usuario" placeholder="Usuario"
                                                   maxlength="16" required>
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
                                                   placeholder="Contraseña" maxlength="16" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-6">

                                    </div>
                                    <div class="col-6">
                                        <button type="button" class="btn btn-sm btn-outline-light float-right"
                                                id="btn-mostrar-mas">
                                            Mostrar más
                                        </button>
                                    </div>
                                </div>
                                <div class="form-row form-mas">
                                    <div class="form-group col-6">
                                        <label for="estudio">Estudio en...</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="fas fa-user-graduate"></i>
                                                </div>
                                            </div>
                                            <input type="text" class="form-control" name="estudio"
                                                   placeholder="Estudio en..."
                                            >
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="trabajo">Trabaja en...</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="fas fa-briefcase"></i>
                                                </div>
                                            </div>
                                            <input type="text" class="form-control" name="trabajo"
                                                   placeholder="Trabaja en..."
                                            >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row form-mas">
                                    <div class="form-group col-6">
                                        <label for="creencia">Creo en...</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="fas fa-dove"></i>
                                                </div>
                                            </div>
                                            <input type="text" class="form-control" name="creencia"
                                                   placeholder="Creo en..."
                                            >
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="sitio-web">Mi sitio web es...</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="fas fa-globe"></i>
                                                </div>
                                            </div>
                                            <input type="text" class="form-control" name="sitio-web"
                                                   placeholder="Mi sitio web es..."
                                            >
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
    </div>
    <script src="/js/jquery-validation/dist/jquery.validate.min.js"></script>
    <script>
        $(document).ready(function () {
            $(".form-mas").hide();

            $("#btn-mostrar-mas").unbind().click(function () {
                $(".form-mas").toggle();
            });

            $("#form-registrar").validate({
                rules: {
                    nombre: "required",
                    apellido: "required",
                    nacionalidad: "required",
                    "fecha-nacimiento": "required",
                    sexo: "required",
                    usuario: "required",
                    contrasena: "required"
                },
                messages: {
                    nombre: "Campo requerido",
                    apellido: "Campo requerido",
                    nacionalidad: "Campo requerido",
                    "fecha-nacimiento": "Campo requerido",
                    sexo: "Campo requerido",
                    usuario: "Campo requerido",
                    contrasena: "Campo requerido"
                }
            });
        });
    </script>
</@base.pagina>