package ModelsGestoras;

import ModelsFactura.Factura;

import java.util.ArrayList;

import Interfaces.IArchivos;
import org.json.JSONArray;
import org.json.JSONException;

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


    @Override
    public void guardarArchivo(String nombreArchivo){

        JSONArray jsonArray = new JSONArray();
        for (Factura f : facturas)
        {
            jsonArray.put(f.toJson());
        }
        JsonUtiles.grabar(jsonArray, nombreArchivo);
    }
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
