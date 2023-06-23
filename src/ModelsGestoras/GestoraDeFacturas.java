package ModelsGestoras;

import ModelsFactura.Factura;

import java.util.ArrayList;

import Interfaces.IArchivos;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Clase utilizada para la manipulacion de un array list
 * contenedor de objetos del tipo factura, una vez que la facutra se termina de crear
 * el contenido de cada factura es guardada en un archivo de tipo json
 */
public class GestoraDeFacturas implements IArchivos {
    private ArrayList<Factura> facturas;
    public GestoraDeFacturas() {
        facturas = new ArrayList<>();
    }

    public boolean agregarFactura(Factura factura)
    {
        return facturas.add(factura);
    }

    public String listarFacturas() {

        String listado = "";

        for (Factura f : facturas)
        {
            listado += "\n" + f.toString();
        }
        return listado;
    }


    /**
     * funcion que guarda en el archivo de tipo json todos los datos del array con las facturas
     * la firma del metodo se encuentra en una interfaz
     * @param nombreArchivo
     */
    @Override
    public void guardarArchivo(String nombreArchivo){

        JSONArray jsonArray = new JSONArray();
        for (Factura f : facturas)
        {
            jsonArray.put(f.toJson());
        }
        JsonUtiles.grabar(jsonArray, nombreArchivo);
    }

    /**
     * lee un archivo de tipo json y lo va agrgando al arraylist de facturas
     * la firma del metodo se encuentra en una interfaz
     * @param nombreArchivo
     */
    @Override
    public void leerArchivo(String nombreArchivo){

        try {
            JSONArray jsonArray = new JSONArray(JsonUtiles.leer(nombreArchivo));
            for (int i = 0; i<jsonArray.length(); i++)
            {
                Factura f = new Factura();
                f.fromJson(jsonArray.getJSONObject(i));
                facturas.add(f);
            }
        }
        catch (JSONException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }





}
