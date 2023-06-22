package ModelsGestoras;

import ModelsControladoraArchivos.ControladoraArchivo;
import ModelsFactura.Factura;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import Interfaces.iArchivos;
public class GestoraDeFacturas implements iArchivos {
    private TreeSet<Factura> facturas;

    public GestoraDeFacturas() {
        facturas = new TreeSet<>();
    }

    public boolean agregarFactura(Factura factura) {
        boolean flag = false;
        flag = facturas.add(factura);
        return flag;
    }

    public String listarFacturas() {
        String listado = "";

        Iterator<Factura> it = facturas.iterator();
        while (it.hasNext()) {

            listado += it.next().toString();
        }
        return listado;
    }

    public String listarFacturasPorFecha(String fecha) {
        String listado = "";
        Iterator<Factura> it = facturas.iterator();

        while (it.hasNext()) {
            if (it.next().getFecha().compareTo(fecha) <= 0)//lista las fechas mas recientes a la fecha recibida
            {
                listado += it.next().toString();
            }
        }
        return listado;
    }
    @Override
    public void guardarArchivo(String nombreArchivo){
        ArrayList<Factura> aGuardar= new ArrayList<>();
        Iterator<Factura> it= facturas.iterator();
        while (it.hasNext())
        {
            Factura aux= it.next();
            aGuardar.add(aux);
        }
        ControladoraArchivo.guardar(aGuardar,nombreArchivo);
    }
    @Override
    public void leerArchivo(String nombreArchivo){

        ArrayList<Factura>facturas= new ArrayList<>();
        ControladoraArchivo.leer(facturas,nombreArchivo);

        for (Factura fa:facturas)
        {
            agregarFactura(fa);
        }
    }

}
