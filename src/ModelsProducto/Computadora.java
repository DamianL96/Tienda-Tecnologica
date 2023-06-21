package ModelsProducto;

import ModelsEnum.TipoPc;
import ModelsEnum.TipoProducto;

import java.io.Serializable;
import java.util.ArrayList;

public class Computadora extends ProductoInformatico{

    private String webCam;
    private String duracionDeBateria;
    private boolean lectorCd;

    private TipoPc tipoPc;

    public Computadora()
    {
        super();
        this.webCam = "";
        this.duracionDeBateria = "";
        this.lectorCd = false;
        this.tipoPc = null;
    }

    public Computadora(TipoProducto tipoProducto, String modelo, String marca, double precio, int stock, String descripcion, int vendidos, int id, double tamañoDePantalla, String resolucion, String accesorios, String procesador, int ram, String sistemOperativo, int almaecenamiento, String webCam, String duracionDeBateria, boolean lectorCd, TipoPc tipoPc)
    {
        super(tipoProducto, modelo, marca, precio, stock, descripcion, vendidos, id, tamañoDePantalla, resolucion, accesorios, procesador, ram, sistemOperativo, almaecenamiento);
        this.webCam = webCam;
        this.duracionDeBateria = duracionDeBateria;
        this.lectorCd = lectorCd;
        this.tipoPc = tipoPc;
    }

    public String getWebCam() {
        return webCam;
    }

    public void setWebCam(String webCam) {
        this.webCam = webCam;
    }

    public String getDuracionDeBateria() {
        return duracionDeBateria;
    }

    public void setDuracionDeBateria(String duracionDeBateria) {
        this.duracionDeBateria = duracionDeBateria;
    }

    public boolean isLectorCd() {
        return lectorCd;
    }

    public void setLectorCd(boolean lectorCd) {
        this.lectorCd = lectorCd;
    }

    public TipoPc getTipoPc() {
        return tipoPc;
    }

    public void setTipoPc(TipoPc tipoPc) {
        this.tipoPc = tipoPc;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nWeb cam: " + webCam +
                "\nDuracion de bateria: " + duracionDeBateria +
                "\nLector de CD: " + lectorCd +
                "\nTipo de computadora: " + tipoPc;
    }
}
