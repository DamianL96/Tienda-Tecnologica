package ModelsUsuario;


import ModelsEnum.TipoUsuario;

import ModelsProducto.Producto;

import java.util.ArrayList;
import java.util.Objects;


public class Usuario extends Persona{

    private String nombreDeUsuario;
    private String email;
    private String contrasenia;
    private String codigoAdmin;
    private int id;
    private TipoUsuario tipo;
    private ArrayList <Producto> miCarrito;
    private ArrayList <Producto> historialDeCompra;
    public static int ultimoId;

    public Usuario()
    {
        super();
        this.nombreDeUsuario = "";
        this.email = "";
        this.contrasenia = "";
        this.codigoAdmin = "";
        this.id = 0;
        this.tipo = tipo;

        miCarrito= new ArrayList<>();
        historialDeCompra=new ArrayList<>();

    }

    public Usuario(String nombre, String apellido, String codigoPostal, String nombreDeUsuario, String email, String contraseña, TipoUsuario tipo)
    {
        super(nombre, apellido, codigoPostal);
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.contrasenia = contraseña;
        this.tipo = tipo;
        this.id = 0;
        miCarrito= new ArrayList<>();
        historialDeCompra=new ArrayList<>();
    }

    public ArrayList<Producto> getMiCarrito() {
        return miCarrito;
    }

    /**Metodo que devuelve un String con el contenido del carrito del usuario
     * @return String
     */
    public String listarCarrito(){
        String lista= "";
        for (int i = 0; i < miCarrito.size(); i++) {
            lista += "\n" +  miCarrito.get(i).toString();
        }
        return lista;
    }

    public ArrayList<Producto> getHistorialDeCompra() {
        return historialDeCompra;
    }

    /**Metodo que devuelve un String con el contenido del historial del usuario
     * @return String
     */
    public String listarHistorial() {
        String lista = "";
        for (int i = 0; i < historialDeCompra.size(); i++) {
            lista += historialDeCompra.get(i).toString();
        }
        return lista;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCodigoAdmin() {
        return codigoAdmin;
    }

    public void setCodigoAdmin(String codigoAdmin) {
        this.codigoAdmin = codigoAdmin;
    }

    public int getId()
    {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }


    /**Metodo que agrega un producto al carrito de compras
     *
     * @param producto
     * @return boolean
     */
    public boolean agregarProductoAlCarrito(Producto producto)
    {
        miCarrito.add(producto);
        return true;
    }


    /**Metodo que agrega al historial de productos un producto ya comprado
     *
     * @param producto
     * @return boolean
     */
    public boolean agregarAlHistorial(Producto producto)
    {
        historialDeCompra.add(producto);
        return true;
    }


    /** Metodo que elimina un producto del carrito
     *   se utiliza en comprar producto
     * @param producto
     * @return boolean
     */

    public boolean eliminarProductoDelCarrito(Producto producto)
    {
        for(int i=0; i<miCarrito.size(); i++)
        {
            if(producto.equals(miCarrito.get(i)))
            {
                miCarrito.remove(i);
            }
        }
        return true;
    }

    /** Metodo que elimina todos los productos cargados en el carrito
     *
     */
    public void limpiarCarrito(){
        miCarrito.clear();;
    }
    @Override
    public boolean equals(Object obj)
    {
        boolean rta = false;
        if(obj != null)
        {
            if(obj instanceof Usuario)
            {
                Usuario aux = (Usuario) obj;
                if(nombreDeUsuario.equals(aux.getNombreDeUsuario()))
                {
                    rta = true;
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
        return "\nUsuario: " + super.toString()+
                "\nNombre de usuario: " + nombreDeUsuario +
                "\nEmail: " + email +
                "\nContraseña: " + contrasenia;
    }
}
