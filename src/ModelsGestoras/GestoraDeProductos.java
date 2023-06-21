package ModelsGestoras;

import ModelsControladoraArchivos.ControladoraArchivo;
import ModelsEnum.TipoProducto;
import ModelsExcepcion.MiExcepcionProducto;
import ModelsProducto.Comentario;
import ModelsProducto.Producto;

import java.io.Serializable;
import java.util.*;

public class GestoraDeProductos implements Serializable{

    //private ArrayList<Producto> productosTipo
    private HashMap<TipoProducto, ArrayList<Producto>> productos;


    public GestoraDeProductos()
    {
        productos = new HashMap<>();
    }


    /*Seccion listar productos-------------------------------------------------------------------------------------*/
    /**
     * Metodo que recorre el hasMap con su respectivo arrayList
     * Devuelve en forma de string toda la informacion de todos los producos
     */
    public String infoProductos()
    {
        String info = "";
        Iterator it = productos.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry mapa = (Map.Entry) it.next();
            ArrayList<Producto> aux = (ArrayList<Producto>) mapa.getValue();
            for(Producto p : aux)
            {
                info += "\n" + p.toString();
            }
        }
        return info;
    }

    /**
     * Muestra todos los productos de un mismo tipo que recibe por parametro ej. todos los CELULARES
     * @return String
     */
    public String infoProductosDeCiertoTipo(TipoProducto t)
    {
        String info = "";
        for(Producto p : productos.get(t)){
            info += "\n" + p.toString();
        }
        return info;
    }

    /**
     * Metodo que busca un producto en especifido si lo encuentra lo retorna y si no devuelve un null
     * sirve para verificar si esta o no esta un producto.
     * @param marca
     * @param modelo
     * @return
     */
    public Producto buscaProductoColeccion(String marca, String modelo)
    {
        Producto encontrado = null;
        Iterator it = productos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry mapa = (Map.Entry) it.next();
            ArrayList<Producto> aux = (ArrayList<Producto>) mapa.getValue();
            for (Producto p : aux) {
                if (p.buscaProducto(modelo, marca)) {
                  encontrado = p;
                }
            }
        }
        return encontrado;
    }



    /*Seccion Admin-----------------------------------------------------------------------------------------**/

    /**
     * Metodo que agrega un producto al HasMap de productos
     * si el tipo de producto ya existe (ejemplo televisor) directamente lo añede al su arrayList
     * si el tipo de producto no existe crea una nueva instancia del map con su arrayList y luego lo añede
     * @param clave
     * @param producto
     */
    public void agregar(TipoProducto clave, Producto producto)
    {
        try
        {
            if (buscaProductoColeccion(producto.getMarca(), producto.getModelo()) == null) {
                if (!productos.containsKey(clave))
                {
                    productos.put(clave, new ArrayList<>());
                }
                producto.setId(producto.ultimoId + 1);
                producto.ultimoId = producto.getId();
                productos.get(clave).add(producto);
            } else{
                throw new MiExcepcionProducto("ERROR - El producto que desea ingresar ya se encuentra en el catalogo.");
            }
        }
        catch (MiExcepcionProducto ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *Metodo que incrementa el stock
     * recibe la marca y el modelo, recorre la coleccion y si lo encuentra lo agrega al stock ya existente
     * si el producto no se enceuntra en la coleccion este lanza un mensaje para avisar que no existe
     * @param marca
     * @param modelo
     * @param stockAAgregar
     */
    public void incrementaStock(String marca, String modelo, int stockAAgregar)
    {
        try {
            Producto encontrado = buscaProductoColeccion(marca, modelo);
            if(encontrado != null)
            {
                encontrado.setStock(stockAAgregar);
            }
            else
            {
                throw new Exception("ERROR PRODUCTO NO EXISTENTE");
            }
        }
        catch (MiExcepcionProducto ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metodo para actualizar el precio de un producto
     * @param marca
     * @param modelo
     * @param precioNuevo
     */

    public void cambioPrecio(String marca, String modelo, double precioNuevo)
    {
        Producto p = buscaProductoColeccion(marca, modelo);
        if(p != null)
        {
            p.setPrecio(precioNuevo);
        }
    }

    /**
     * Metodo que recibe los datos principales de un producto, lo busca y si se encuentra lo retorna.
     * contiene una excepcion del tipo MiEexcepcionProducto para el caso de que no se encuentre.
     * @param marca
     * @param modelo
     */
    public void eliminaProducto(String marca, String modelo)
    {
        Producto pAElimnar = buscaProductoColeccion(marca, modelo); ///almacena el producto que se desea eliminar si se enceuntra
        try {
            if(pAElimnar != null)
            {
                ArrayList<Producto> aux = productos.get(pAElimnar.getTipoProducto());
                aux.remove(pAElimnar);
            }
            else
            {
                throw new MiExcepcionProducto("ERROR - El producto que desea eliminar no se encuentra en el catalogo");
            }
        }
        catch (MiExcepcionProducto ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    /*Seccion archivos--------------------------------------------------------------------------------------------*/

    /**
     * Metodo que guarda todos los prouctos en un archivo
     * */

    public void guardarArchivo()
    {
        ArrayList<Producto> aGuardar = new ArrayList<>();
        Iterator it = productos.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry map = (Map.Entry) it.next();
            aGuardar.addAll((Collection<? extends Producto>) map.getValue());
        }
        ControladoraArchivo.guardar(aGuardar, "Productos.dat");
    }


    /**
     * Metodo que carga los productos de un archivo al hashmap
     * */
    public void leeArchivo()
    {
        ArrayList<Producto> productos = new ArrayList<>();
        ControladoraArchivo.leer(productos, "Productos.dat");

        for(Producto p: productos)
        {
            agregar(p.getTipoProducto(), p);
        }
    }

    //----------------------------------------------------------------------------------------

    /**
     * Metodo utilizado para cargar el array list de comentarios
     * recibe el comentario y los datos del usuario necesarios para generar el comentario
     */
    public void agregaComentario(Producto producto,String nombre, String email, String comentario, int puntiacion)
    {
        Comentario comentario1 = new Comentario(nombre, email, puntiacion, comentario);
        producto.setComentariosProducto(comentario1);
    }


}
