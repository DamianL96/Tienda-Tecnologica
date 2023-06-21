package ModelsProducto;

import ModelsEnum.TipoProducto;

import java.util.ArrayList;

public abstract class ProductoInformatico extends Producto
{

    private double tamaneoDePantalla; //en pulgadas
    private String resolucion;
    private String accesorios;
    private String procesador;
    private int ram;
    private String sistemOperativo;
    private int almaecenamiento;

    public ProductoInformatico()
    {
        super();
        this.tamaneoDePantalla = 0;
        this.resolucion = "";
        this.accesorios = "";
        this.procesador = "";
        this.ram = 0;
        this.sistemOperativo = "";
        this.almaecenamiento = 0;
    }

    public ProductoInformatico(TipoProducto tipoProducto, String modelo, String marca, double precio, int stock, String descripcion, int vendidos, int id, double tamaneoDePantalla, String resolucion, String accesorios, String procesador, int ram, String sistemOperativo, int almaecenamiento)
    {
        super(tipoProducto, modelo, marca, precio, stock, descripcion, vendidos, id);
        this.tamaneoDePantalla = tamaneoDePantalla;
        this.resolucion = resolucion;
        this.accesorios = accesorios;
        this.procesador = procesador;
        this.ram = ram;
        this.sistemOperativo = sistemOperativo;
        this.almaecenamiento = almaecenamiento;
    }

    public double getTamaneoDePantalla() {
        return tamaneoDePantalla;
    }

    public void setTamaneoDePantalla(double tamaneoDePantalla) {
        this.tamaneoDePantalla = tamaneoDePantalla;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getSistemOperativo() {
        return sistemOperativo;
    }

    public void setSistemOperativo(String sistemOperativo) {
        this.sistemOperativo = sistemOperativo;
    }

    public int getAlmaecenamiento() {
        return almaecenamiento;
    }

    public void setAlmaecenamiento(int almaecenamiento) {
        this.almaecenamiento = almaecenamiento;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTamaño de pantalla: " + tamaneoDePantalla +
                "\nResolucion " + resolucion +
                "\nAccesorios: mostrar array" +
                "\nProcesador: " + procesador +
                "\nMemoria RAM: " + ram +
                "\nAlmacenamiento interno: " + almaecenamiento +
                "\nSistema operativo " + sistemOperativo;

    }
}
