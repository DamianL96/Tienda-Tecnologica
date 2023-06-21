package ModelsProducto;

import ModelsEnum.TipoPantalla;
import ModelsEnum.TipoProducto;

import java.io.Serializable;
import java.util.ArrayList;

public class Televisor extends ProductoInformatico{

    private boolean smart;
    private TipoPantalla tipoPantalla;

    public Televisor()
    {
        super();
        this.smart = false;
        this.tipoPantalla = null;
    }

    public Televisor(TipoProducto tipoProducto, String modelo, String marca, double precio, int stock, String descripcion, int vendidos, int id, double tamañoDePantalla, String resolucion, String accesorios, String procesador, int ram, String sistemOperativo, int almaecenamiento, boolean smart, TipoPantalla tipoPantalla)
    {
        super(tipoProducto, modelo, marca, precio, stock, descripcion, vendidos, id, tamañoDePantalla, resolucion, accesorios, procesador, ram, sistemOperativo, almaecenamiento);
        this.smart = smart;
        this.tipoPantalla = tipoPantalla;
    }

    public boolean isSmart() {
        return smart;
    }

    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    public TipoPantalla getTipoPantalla() {
        return tipoPantalla;
    }

    public void setTipoPantalla(TipoPantalla tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nSmart: " + smart +
                "\nTipo de pantalla: " + tipoPantalla;
    }
}
