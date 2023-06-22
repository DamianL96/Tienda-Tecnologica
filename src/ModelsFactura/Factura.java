package ModelsFactura;

import Interfaces.IformatFecha;
import ModelsProducto.Computadora;
import ModelsProducto.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Factura implements Serializable, IformatFecha {
    private String fecha;
    private ArrayList<Producto> productos; //recibe producto-marca-modelo concatenado en un string
    private double precio;
    private String apellido;
    private String nombre;
    private String email;
    private String tipoDePago;

    public Factura()
    {
        this.fecha= fechaFormateada();
        this.productos = new ArrayList<>();
        this.apellido= "";
        this.nombre= "";
        this.email= "";
        this.tipoDePago= "";
        this.precio = 0;
    }

    public Factura(ArrayList<Producto> productos, String apellido, String nombre, String email, String tipoDePago, double precio){

        this.fecha= fechaFormateada();
        this.productos = productos;
        this.apellido= apellido;
        this.nombre= nombre;
        this.email= email;
        this.tipoDePago= tipoDePago;
        this.precio=precio;
    }

    public String getFecha() {
        return fecha;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipoDePago() {
        return tipoDePago;
    }
    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o)
    {
        boolean rta= false;
        if (o != null){
            if(o instanceof Factura){
                Factura aux = (Factura) o;
                if(aux.getFecha().equals(String.valueOf(fecha))){
                    rta= true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
            return 1;
        }

    @Override
    public String toString() {
        return "\nFactura:" +
                "\nFecha: " + fecha +
                "\nProductos: " + listarProductos() +
                "\nPrecio total: $" + precio +
                "\nCliente: " + apellido +"-"+ nombre + "-" + email + "-" +
                "\nTipo De Pago:" + tipoDePago;
    }

    public String listarProductos()
    {
        String lista = "";

        for (Producto p: productos)
        {
            lista += "\n" + listarProducto(p);
        }
        return lista;
    }
    public String listarProducto (Producto p)
    {
        return  p.getTipoProducto() + " - " + p.getMarca() + " - " +  p.getModelo() + " - " + " Precio= $" + p.getPrecio();
    }

    @Override
    public String fechaFormateada()
    {
        LocalDateTime fechaLocal = LocalDateTime.now();
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaLocal.format(fechaFormateada);
    }

    public JSONObject toJson()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fecha", fecha);

            JSONArray jsonArrayProducto = new JSONArray();
            for (Producto p : productos) {
                jsonArrayProducto.put(p.toJson());
            }
            jsonObject.put("producto", jsonArrayProducto);
            jsonObject.put("precioFinal", precio);
            jsonObject.put("apellido", apellido);
            jsonObject.put("nombre", nombre);
            jsonObject.put("email", email);
            jsonObject.put("tipoDePago", tipoDePago);
        }
        catch (JSONException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return jsonObject;
    }

    public void fromJson(JSONObject jsonObject)
    {
        try {
            tipoDePago = jsonObject.getString("tipoDePago");
            fecha = jsonObject.getString("fecha");
            apellido = jsonObject.getString("apellido");
            precio = jsonObject.getDouble("precioFinal");

            JSONArray jsonArrayProducto = jsonObject.getJSONArray("producto");
            for (int i = 0; i<jsonArrayProducto.length(); i++)
            {
                Producto p = new Computadora();
                p.fromJson(jsonArrayProducto.getJSONObject(i));
                productos.add(p);
            }
            nombre = jsonObject.getString("nombre");
            email = jsonObject.getString("email");
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

