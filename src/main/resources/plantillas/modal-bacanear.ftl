<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content bg-dark text-white">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bacaneando</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" class="text-white">
              <i class="fas fa-times-circle"></i>
            </span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action="/bacanear" id="form-bacanear" enctype='multipart/form-data'>
                    <div class="form-group">
                        <label for="contenido">Contenido</label>
                        <textarea name="texto" class="form-control rounded-0"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="imagen">Agregar foto</label>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" name="imagen">
                            <label class="custom-file-label" for="validatedCustomFile">Subir foto...</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="etiquetar">Etiquetar</label><br>
                        <select class="select2 form-control rounded-0" name="etiquetado">
                            <option selected value="">Busca un amigo</option>
                            <#list amigos as amigo>
                                <option value="${amigo.usuario.usuario}">${amigo.nombre} ${amigo.apellido}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">
                            <i class="fas fa-ban"></i> Cancelar
                        </button>
                        <button class="btn btn-warning my-2 my-sm-0" type="submit">
                            <i class="fas fa-feather-alt"></i> Bacanear
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