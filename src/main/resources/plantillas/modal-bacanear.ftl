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
                <div class="form-group">
                    <label for="contenido">Contenido</label>
                    <textarea name="" class="form-control rounded-0"></textarea>
                </div>
                <div class="form-group">
                    <label for="imagen">Agregar foto</label>
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name="imagen" required>
                        <label class="custom-file-label" for="validatedCustomFile">Subir foto...</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="etiquetar">Etiquetar</label>
                    <select multiple class="form-control rounded-0">
                        <option>Oscar Núñez</option>
                        <option>Carlos Camacho</option>
                        <option>Carlos Pérez</option>
                        <option>Nelson Duran</option>
                        <option>César Peña</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">
                    <i class="fas fa-ban"></i> Cancelar
                </button>
                <button class="btn btn-warning my-2 my-sm-0" type="submit">
                    <i class="fas fa-feather-alt"></i> Bacanear
                </button>
            </div>
        </div>
    </div>
</div>