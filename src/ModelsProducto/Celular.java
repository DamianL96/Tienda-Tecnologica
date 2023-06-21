package ModelsProducto;

import ModelsEnum.TipoProducto;

public class Celular extends ProductoInformatico{

    private String camaraTrasera;
    private int cantCamaras;
    private String camaraDelantera;

    public Celular()
    {
        super();
        this.camaraTrasera = "";
        this.cantCamaras = 0;
        this.camaraDelantera = "";
    }

    public Celular(TipoProducto tipoProducto, String modelo, String marca, double precio, int stock, String descripcion, int vendidos, int id, double tamañoDePantalla, String resolucion, String accesorios, String procesador, int ram, String sistemOperativo, int almaecenamiento, String camaraTrasera, int cantCamaras, String camaraDelantera) {
        super(tipoProducto, modelo, marca, precio, stock, descripcion, vendidos, id, tamañoDePantalla, resolucion, accesorios, procesador, ram, sistemOperativo, almaecenamiento);
        this.camaraTrasera = camaraTrasera;
        this.cantCamaras = cantCamaras;
        this.camaraDelantera = camaraDelantera;
    }

    public String getCamaraTrasera() {
        return camaraTrasera;
    }

    public void setCamaraTrasera(String camaraTrasera) {
        this.camaraTrasera = camaraTrasera;
    }

    public int getCantCamaras() {
        return cantCamaras;
    }

    public void setCantCamaras(int cantCamaras) {
        this.cantCamaras = cantCamaras;
    }

    public String getCamaraDelantera() {
        return camaraDelantera;
    }

    public void setCamaraDelantera(String camaraDelantera) {
        this.camaraDelantera = camaraDelantera;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCamara trasera: " + camaraTrasera +
                "\nCantidad de camaras: " + cantCamaras +
                "\nCamara Delantera: " + camaraDelantera;
    }
    public String toStringCatalogo() {
        return super.toStringCatalogo();
    }
}
