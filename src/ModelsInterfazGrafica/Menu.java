package ModelsInterfazGrafica;

import ModelsEnum.TipoOpciones;
import ModelsUsuario.Usuario;

public class Menu
{
    public static void muestraMenuPrincipal()
    {
        System.out.println("MENU -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.INICIARSESION.getNombre());
        System.out.println("2- "+TipoOpciones.REGISTRARSE.getNombre());
        System.out.println("3- "+TipoOpciones.CATALOGO.getNombre());
    }
    public static void muestraOpcionesUsuario()
    {
        System.out.println("USUARIO -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.CATALOGO.getNombre());
        System.out.println("2- "+TipoOpciones.CARRITO.getNombre());
        System.out.println("3- "+TipoOpciones.MISDATOS.getNombre());
        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());
    }

    public static void muestraOpcionesAdmin()
    {
        System.out.println("ADMIN -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.GESTIONARINVENTARIO.getNombre());
        System.out.println("2- "+TipoOpciones.VERFACTURAS.getNombre());
        System.out.println("3- "+TipoOpciones.MISDATOS.getNombre());
        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());
    }

    public static void muestraGestionarInventario()
    {
        System.out.println("GESTIONAR INVENTARIO -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.AGREGARPRODUCTO.getNombre());
        System.out.println("2- "+TipoOpciones.CAMBIARSTOCK.getNombre());
        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());
    }

    public static void muestraCatalogo()
    {
        System.out.println("CATALOGO -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.CATALGOCOMPLETO.getNombre());
        System.out.println("2- "+TipoOpciones.CELULARES.getNombre());
        System.out.println("3- "+TipoOpciones.TV.getNombre());
        System.out.println("4- "+TipoOpciones.COMPUTADORAS.getNombre());
        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());
    }
    public static void muestraCarrito()
    {
        System.out.println("CARRITO -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.COMPRAR.getNombre());
        System.out.println("2- "+TipoOpciones.LIMPIARCARRITO.getNombre());
        System.out.println("3- "+TipoOpciones.VOLVER.getNombre());
    }

    public static void opcionesCatalogo()
    {
        System.out.println("VER PRODUCTO -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.VERPRODUCTO.getNombre());
        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());
    }
    public static void opcionesCompra()
    {
        System.out.println("COMPRAR -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.MEDIOPAGO.getNombre());
        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());
    }


    public static void verProductoMarcaModelo(){
        System.out.println("OPCIONES -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.AGREGARALCARRITO.getNombre());
        System.out.println("2- "+TipoOpciones.COMPRAR.getNombre());
        System.out.println("3- "+TipoOpciones.DEJARCOMENTARIO.getNombre());

        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());
    }

    public static void verMisDatos(){
        System.out.println("MIS DATOS -- Ingrese el numero de la opcion que desee");
        System.out.println("1- "+TipoOpciones.MISDATOS.getNombre());

        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());

    }
    public static void menuComentar(){
        System.out.println("COMENTARIO DEJADO");
        System.out.println("9- "+TipoOpciones.VOLVER.getNombre());
    }


}
