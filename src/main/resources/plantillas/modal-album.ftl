<div class="modal fade" id="modal-album" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content bg-dark text-white">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Crea tu album</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" class="text-white">
              <i class="fas fa-times-circle"></i>
            </span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action="/crear-album" id="form-album" enctype='multipart/form-data'>
                    <div class="form-group">
                        <label for="descripcion">Descripción album</label>
                        <textarea name="descripcion" class="form-control rounded-0"></textarea>
                    </div>
                    <fieldset class="alert alert-secondary rounded-0 m-0">
                        <div class="form-group">
                            <label for="imagen">Foto #1</label>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" name="imagen1">
                                <label class="custom-file-label" for="validatedCustomFile">Subir foto #1...</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descripcion1">Descripción imagen #1</label>
                            <textarea name="descripcion1" class="form-control rounded-0"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="etiquetar1">Etiquetar imagen #1</label><br>
                            <select class="select2 form-control rounded-0" name="etiquetado1">
                                <option selected value="">Busca un amigo</option>
                            <#list amigos as amigo>
                                <option value="${amigo.usuario.usuario}">${amigo.nombre} ${amigo.apellido}</option>
                            </#list>
                            </select>
                        </div>
                    </fieldset>
                    <fieldset class="alert alert-dark rounded-0 m-0">
                        <div class="form-group">
                            <label for="imagen2">Foto #2</label>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" name="imagen2">
                                <label class="custom-file-label" for="validatedCustomFile">Subir foto #2...</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descripcion2">Descripción imagen #2</label>
                            <textarea name="descripcion2" class="form-control rounded-0"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="etiquetar2">Etiquetar imagen #2</label><br>
                            <select class="select2 form-control rounded-0" name="etiquetado2">
                                <option selected value="">Busca un amigo</option>
                            <#list amigos as amigo>
                                <option value="${amigo.usuario.usuario}">${amigo.nombre} ${amigo.apellido}</option>
                            </#list>
                            </select>
                        </div>
                    </fieldset>
                    <fieldset class="alert alert-secondary rounded-0 m-0">
                        <div class="form-group">
                            <label for="imagen3">Foto #3</label>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" name="imagen3">
                                <label class="custom-file-label" for="validatedCustomFile">Subir foto #3...</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descripcion3">Descripción imagen #3</label>
                            <textarea name="descripcion3" class="form-control rounded-0"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="etiquetar3">Etiquetar imagen #3</label><br>
                            <select class="select2 form-control rounded-0" name="etiquetado3">
                                <option selected value="">Busca un amigo</option>
                            <#list amigos as amigo>
                                <option value="${amigo.usuario.usuario}">${amigo.nombre} ${amigo.apellido}</option>
                            </#list>
                            </select>
                        </div>
                    </fieldset>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">
                            <i class="fas fa-ban"></i> Cancelar
                        </button>
                        <button class="btn btn-warning my-2 my-sm-0" type="submit">
                            <i class="fas fa-feather-alt"></i> Crear album
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $(".select2").select2();
    });
</script>