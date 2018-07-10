package Modelos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reaccion implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TipoReaccion TipoReaccionElegida;

    public Reaccion(TipoReaccion tipoReaccionElegida) {
        TipoReaccionElegida = tipoReaccionElegida;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoReaccion getTipoReaccionElegida() {
        return TipoReaccionElegida;
    }

    public void setTipoReaccionElegida(TipoReaccion tipoReaccionElegida) {
        TipoReaccionElegida = tipoReaccionElegida;
    }
}
