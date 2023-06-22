package ModelsFactura;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Factura implements Comparable, Serializable {
    private String fecha;
    private String producto; //recibe producto-marca-modelo concatenado en un string
    private String marca;
    private String modelo;
    private double precio;
    private String apellido;
    private String nombre;
    private String email;
    private String tipoDePago;



    public Factura(String producto,String marca,String modelo,double precio,String apellido,String nombre, String email, String tipoDePago){
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.fecha= dtf.format(LocalDateTime.now());
        this.producto= producto;
        this.marca= marca;
        this.modelo= modelo;
        this.precio= precio;
        this.apellido= apellido;
        this.nombre= nombre;
        this.email= email;
        this.tipoDePago= tipoDePago;
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
        return "Factura{" +
                "Fecha='" + fecha + '\'' +
                ", Producto='" + producto + "-" + marca +"-"+ modelo + '\'' +
                ", Precio=$" + precio +
                ", Cliente='" + apellido +"-"+ nombre + "(" + email + ")" + '\'' +
                ", Tipo De Pago='" + tipoDePago + '\'' +
                '}'+ "\n" ;
    }




    public String getFecha() {
            return fecha;
        }

    public String getProducto() {
            return producto;
        }
    public void setProducto(String producto) {
            this.producto = producto;
        }
    public double getPrecio() {
            return precio;
        }
    public void setPrecio(double precio) {
            this.precio = precio;
        }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
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
    public int compareTo(Object o) {
        int rta=0;
        if (o != null){
            if(o instanceof Factura){
                Factura aux= (Factura) o;
                rta= fecha.compareTo(aux.getFecha());
            }
        }
        return rta;
    }




}

