package ModelsInterfazGrafica;

import ModelsEnum.TipoPantalla;
import ModelsEnum.TipoPc;
import ModelsEnum.TipoProducto;
import ModelsEnum.TipoUsuario;
import ModelsExcepcion.*;
import ModelsFactura.Factura;
import ModelsGestoras.GestoraDeFacturas;
import ModelsGestoras.GestoraDeProductos;
import ModelsGestoras.GestoraDeUsuarios;
import ModelsProducto.Celular;
import ModelsProducto.Computadora;
import ModelsProducto.Producto;
import ModelsProducto.Televisor;
import ModelsUsuario.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase dedicada a manejar los menus y las funcionalidades del programa
 */
public class Sistema
{
    private GestoraDeUsuarios gestoraDeUsuarios;
    private GestoraDeProductos gestoraDeProductos;
    private GestoraDeFacturas gestoraDeFacturas;
    private Scanner teclado;
    private Usuario usuario;
    private int opcion;

    public Sistema()
    {
        this.teclado = new Scanner(System.in);
        this.gestoraDeUsuarios= new GestoraDeUsuarios();
        this.gestoraDeProductos= new GestoraDeProductos();
        this.gestoraDeFacturas= new GestoraDeFacturas();
        this.usuario = new Usuario();
        this.opcion = -1;
    }

    /**
     * recupera los datos de los archivos
     */
    public void cargaSistema()
    {
        gestoraDeUsuarios.leeArchivo();
        gestoraDeProductos.leeArchivo();
    }
/**
 *guarda todos los datos en los archivos correspondientes
 */
    public void guardaSistema()
    {
        gestoraDeUsuarios.guardarArchivo();
        gestoraDeProductos.guardarArchivo();
        //gestoraDeFacturas.guardarArchivo("Facturas");
    }

    /**
     * carga los datos necesarios para el funcionamiento del sistema,
     * si no hay archivos se debe crear el admin
     */
    public void cicloPrograma()
    {
        cargaSistema();
        do{

            cicloMenuPrincipal();
        }while (opcion!=0);

    }

    public void cicloMenuPrincipal() {  //menu donde se dara las 3 opciones principales crear usuario - iniciar seccion - ver catalogo

        do{
            Menu.muestraMenuPrincipal();
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    //inicio de sesion
                    cicloIniciarSesion();
                    break;

                case 2:
                    usuario = registrarUsuario();
                    gestoraDeUsuarios.guardarArchivo();
                    cicloOpcionesUsuario();
                    break;

                case 3:
                    //Catalogo
                    cicloMuestraCatalogo();
                    break;
            }
        }while (opcion != 0);
        guardaSistema();
    }

    public void cicloIniciarSesion(){
        try {
            usuario = iniciarSesion();
            boolean admin = verificaTipoUsuario(usuario);
            if(admin == true)
            {
                cicloOpcionesAdministrador();
            }
            else
            {
                cicloOpcionesUsuario();
            }
        }
        catch (MiExcepcionNombreDeUsuario ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    public void cicloMuestraCatalogo(){
        Menu.muestraCatalogo();
        do{
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    //catalogo completo
                    System.out.println(gestoraDeProductos.infoProductos());
                    cicloOpcionesDeCatalogo(1);
                    break;

                case 2:
                    System.out.println(gestoraDeProductos.infoProductosDeCiertoTipo(TipoProducto.CELULAR));
                    cicloOpcionesDeCatalogo(1);
                    //celulares
                    break;

                case 3:
                    //Smart TV's
                    System.out.println(gestoraDeProductos.infoProductosDeCiertoTipo(TipoProducto.TELEVISOR));
                    cicloOpcionesDeCatalogo(1);
                    break;

                case 4:
                    //Computadoras
                    System.out.println(gestoraDeProductos.infoProductosDeCiertoTipo(TipoProducto.COMPUTADORA));
                    cicloOpcionesDeCatalogo(1);
                    break;

                case 9:
                    cicloMenuPrincipal();
                    break;
            }
        }while (opcion != 10);
    }

    public Producto cicloBuscarProducto()
    {
        teclado.nextLine();
        System.out.println("\n Ingresar MARCA del producto:");
        String marca= teclado.nextLine();

        System.out.println("\n Ingresar MODELO del producto:");
        String modelo= teclado.nextLine();
        teclado.nextLine();

        return gestoraDeProductos.buscaProductoColeccion(marca,modelo);
    }

    public void cicloVerProducto ()
    {
        String factura=  "";
        Producto producto=cicloBuscarProducto();
        if(producto == null)
        {
            System.out.println("Producto no encontrado.");
            cicloOpcionesDeCatalogo(2);
        }
        else
        {
            System.out.println(producto.toString());//Muestro el producto
            Menu.verProductoMarcaModelo();
            do
            {
                opcion= teclado.nextInt();


                switch (opcion)
                {
                    case 1://agregar al carrito
                        if(usuario ==null){
                            cicloIniciarSesion();
                        }
                        usuario.agregarProductoAlCarrito(producto);
                        cicloVerCarrito(2);
                        break;

                    case 2://compra
                        if(usuario ==null){
                            cicloIniciarSesion();
                        }
                        usuario.agregarProductoAlCarrito(producto);
                        cicloComprar(usuario.getMiCarrito(),1);
                        System.out.println(factura);
                        cicloOpcionesUsuario();
                        break;
                    case 9:
                        cicloMuestraCatalogo();
                        break;
                }
            }while (opcion != 9);
        }
    }



    public void cicloOpcionesDeCatalogo(int superior)
    {
        Menu.opcionesCatalogo();
        do
        {
            opcion = teclado.nextInt();

            switch (opcion)
            {
                case 1://ver producto
                    cicloVerProducto();
                    break;

                case 9:
                    switch (superior)
                    {
                        case 1:
                            cicloMuestraCatalogo();
                            break;

                        case 2:
                            cicloVerProducto();
                            break;
                    }
                    break;
            }
        }while (opcion != 9);

    }

    public void cicloOpcionesAdministrador ()
    {
       teclado.nextLine();
       do
       {
            Menu.muestraOpcionesAdmin();
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    cicloAgregarProducto();
                    //teclado.next();
                    break;

                case 2:
                    //gestionar inventario
                    cicloGestionarInventario();
                    break;
                case 3:
                    //ver facturas
                    cicloVerFacturas();
                    break;
                case 4:
                    //mis datos
                    cicloVerMisDatosAdmin();
                    break;

                case 9:
                    cicloMenuPrincipal();
                    break;

            }
            guardaSistema();
        }while (opcion != 9);

    }

    public void cicloGestionarInventario(){

        Producto producto= cicloBuscarProducto();
        if(producto == null)
        {
            System.out.println("Producto no encontrado.");
            cicloOpcionesAdministrador();
        }else
        {

            do
            {
                System.out.println(producto.toStringAdmin());
                Menu.menuStock();
                opcion = teclado.nextInt();
                switch (opcion)
                {
                    case 1://agregar Stock
                        producto.agregarStock(cicloAgregarStock());
                        System.out.println(producto.toStringAdmin());
                        cicloOpcionesAdministrador();
                        break;

                    case 2://quitar Stock
                        producto.quitarStock(cicloQuitarStock());
                        System.out.println(producto.toStringAdmin());
                        cicloOpcionesAdministrador();
                        break;

                    case 3://moificar precio
                        producto.setPrecio(cicloModificarPrecio());
                        System.out.println(producto.toStringAdmin());
                        cicloOpcionesAdministrador();
                        break;

                    case 9:
                        cicloOpcionesAdministrador();
                        break;
                }
            }while (opcion != 0);
        }

    }
    public int cicloAgregarStock(){
        System.out.println("Cantidad a agregar:");
        return teclado.nextInt();
    }
    public int cicloQuitarStock(){
        System.out.println("Cantidad a quitar:");
        return teclado.nextInt();
    }
    public double cicloModificarPrecio(){
        System.out.println("Nuevo Precio:");
        return teclado.nextDouble();
    }

    public void cicloOpcionesUsuario () {
        Menu.muestraOpcionesUsuario();

        do{
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    //Catalogo
                    cicloMuestraCatalogo();
                    break;

                case 2:
                    //Carrito
                    if(usuario.getMiCarrito().size() > 0)
                    {
                        cicloVerCarrito(1);
                    }
                    else
                    {
                        System.out.println("Carrito vacio");
                        cicloOpcionesUsuario();
                    }
                    break;
                case 3:
                    //Mis Datos
                    cicloVerMisDatosUsuario();
                    break;

                case 9:
                    cicloMenuPrincipal();
                    break;

            }
        }while (opcion != 9);
    }

    public void cicloVerCarrito(int superior){
        String factura= " ";
        System.out.println(usuario.getMiCarrito());
        Menu.muestraCarrito();
        do {
            opcion= teclado.nextInt();

            switch (opcion){
                case 1:

                    cicloComprar(usuario.getMiCarrito(),1);
                    System.out.println(factura);
                    cicloOpcionesUsuario();

                    ArrayList<Producto> productosAComprar = new ArrayList<>(comprarProducto());
                    cicloComprar(productosAComprar,1);
                    System.out.println(factura);

                    break;

                case 2:
                    cicloLimpiaCarrito();
                    break;

                case 9:
                    switch (superior){ //Para volver a su respectivo metodo que lo invoca
                        case 1: //Lo llama cicloOpcionesUsuario
                            cicloOpcionesUsuario();
                            break;

                        case 2://Lo llama cicloVerProducto
                            cicloOpcionesUsuario();
                            break;

                        case 3://Lo llama cicloLimpiarCarrito
                            cicloOpcionesUsuario();
                            break;
                        case 4:
                            cicloMenuPrincipal();
                            break;
                    }
                    break;
            }
        }while (opcion != 9);
    }

    public void cicloComprar(ArrayList<Producto> productosAComprar,int superior){
        Menu.opcionesCompra();
        Factura factura= null;
        do {
            opcion= teclado.nextInt();
            switch (opcion){
                case 1:
                    teclado.nextLine();
                    System.out.println("Efectivo-Tarjeta-Transfencia");
                    String metodoDePago=teclado.nextLine();

                    factura=generarFactura(productosAComprar, metodoDePago);
                    System.out.println(factura.toString());
                    cicloOpcionesUsuario();
                    break;

                case 9:
                    cicloOpcionesUsuario ();
                    break;
            }
        }while (opcion != 10);
    }

    public void cicloVerMisDatosAdmin(){
        System.out.println("\n MIS DATOS");
        System.out.println(usuario.toString());
        Menu.volver();
        do{
            opcion= teclado.nextInt();
            switch (opcion){
                case 9:
                    cicloOpcionesAdministrador();
                    break;
            }
        }while (opcion != 9);
    }

    public void cicloLimpiaCarrito(){//BORRA TODO EL CARRITO
        usuario.limpiarCarrito();
        cicloVerCarrito(3);
    }

    public void cicloVerMisDatosUsuario(){
        System.out.println("\nMIS DATOS:");
        System.out.println(usuario.toString());
        Menu.volver();
        do{
            opcion= teclado.nextInt();
            switch (opcion){
                case 9:
                    cicloOpcionesUsuario();
                    break;
            }
        }while (opcion != 9);
    }

    public void cicloAgregarProducto(){
        System.out.println("\n AGREGAR PRODUCTO");
        agregarProducto();

        Menu.volver();
        do{
            opcion= teclado.nextInt();
            switch (opcion){
                case 9:
                    cicloOpcionesAdministrador();
                    break;
            }
        }while (opcion != 9);
    }

    public void cicloVerFacturas()
    {
        gestoraDeFacturas.leerArchivo("Facturas");
        System.out.println("\n VER FACTURAS");
        System.out.println(gestoraDeFacturas.listarFacturas());
        Menu.volver();
        do{
            opcion= teclado.nextInt();
            switch (opcion){
                case 9:
                    cicloOpcionesAdministrador();
                    break;
            }
        }while (opcion != 9);

    }


    /** Metodo que verifica los datos para el inicio de sesion
     * utiliza verificar contrasenia y verificar nombre de usuario
     * @param
     * @return
     */
    public Usuario iniciarSesion() throws MiExcepcionNombreDeUsuario//de inicio de sesion
    {
        boolean flag=false;
        teclado.nextLine();
        System.out.println("Ingresar nombre de usuario: ");
        String nombreDeUsuario= teclado.nextLine();
        Usuario usuario=gestoraDeUsuarios.verificarNombreDeUsuario(nombreDeUsuario);

        if(usuario!=null)
        {
            do {
                try {
                    System.out.println("Ingresar contraseña: ");
                    String contrasenia = teclado.nextLine();

                    flag = gestoraDeUsuarios.verificarContrasenia(contrasenia, usuario);

                } catch (MiExcepcionContraseniaIncorrecta ex) //excepcion de contrasenia - puse eso para compilar marcos
                {
                    System.out.println(ex.getMessage());
                }

            }while (flag == false);

        }else
        {
            throw new MiExcepcionNombreDeUsuario("ERROR - no existe el usuario ingresado - registrese");
        }

        return usuario;
    }

    public boolean verificaTipoUsuario (Usuario usuario)
    {
        boolean flag=false;
        if(usuario.getTipo() == TipoUsuario.ADMINISTRADOR)
        {
            System.out.println("Ingresar codigo de administrador: ");
            String codigoAdmin=teclado.nextLine();
            try
            {
                flag = gestoraDeUsuarios.verificarCodigoDeAdmin(usuario, codigoAdmin);
            }
            catch (MiExcepcionContraseniaIncorrecta ex) //excepcion de codigo incorrecto
            {
                System.out.println(ex.getMessage());

            }
        }
        return flag;
    }

    public Usuario registrarUsuario()
    {
        Usuario usuario = cargaUnUsuario();
        try {
            if(!gestoraDeUsuarios.agregarUsuario(usuario))
            {
                throw new MiExcepcionUsuarioRepetido("ERROS - usuario ya existente.");
            }
        }
        catch (MiExcepcionUsuarioRepetido ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return usuario;
    }

    public Usuario cargaUnUsuario()
    {
        boolean flag = true;
        teclado.nextLine();
        System.out.println("Nombre: ");
        String nombre=teclado.nextLine();

        System.out.println("Apellido: ");
        String apellido=teclado.nextLine();

        String nombreDeUsuario = "";
        do {
            System.out.println("Nombre de usuario: ");
            nombreDeUsuario=teclado.nextLine();
            if(gestoraDeUsuarios.verificarNombreDeUsuario(nombreDeUsuario) != null)
            {
                System.out.println("Nombre de usuario ya utilizado - eliga otro");
                flag = false;
            }
        }while (flag == false);

        System.out.println("Email: ");
        String email=teclado.nextLine();

        System.out.println("Contraseña: ");
        String contrasenia=teclado.nextLine();

        System.out.println("Codigo postal: ");
        String codigoPostal=teclado.nextLine();

        Usuario usuario=new Usuario(nombre, apellido, codigoPostal, nombreDeUsuario, email, contrasenia, TipoUsuario.CLIENTE);

        return usuario;
    }

    public void agregarProducto()
    {
        System.out.println("Ingrese que tipo de producto desea cargar:");
        System.out.println("1) Televisor - 2)Celular - 3)Computadora");
        int opcion = teclado.nextInt();
        teclado.nextLine();
        TipoProducto tipoP = null;
        switch (opcion)
        {
            case 1:
                tipoP = TipoProducto.TELEVISOR;
                break;
            case 2:
                tipoP = TipoProducto.CELULAR;
                break;
            case 3:
                tipoP = TipoProducto.COMPUTADORA;
                break;
        }
        Producto producto = cargaUnProducto(tipoP);
        gestoraDeProductos.agregar(producto.getTipoProducto(), producto);
    }

    public Producto cargaUnProducto(TipoProducto tipo)
    {
        Producto productoNuevo = null;

        System.out.println("Ingrese la marca:");
        String marca = teclado.nextLine();

        System.out.println("Ingrese el modelo:");
        String modelo = teclado.nextLine();

        System.out.println("Ingrese el precio:");
        double precio= teclado.nextDouble();

        System.out.println("Ingrese el stock:");
        int stock= teclado.nextInt();

        teclado.nextLine();

        System.out.println("Ingrese la descripcion:");
        String descripcion = teclado.nextLine();

        // teclado.nextLine();

        System.out.println("Ingrese el tamaño de la pantalla:");
        double tamaneoPantalla= teclado.nextDouble();

        teclado.nextLine();

        System.out.println("Ingrese la resolucion:");
        String resolucion = teclado.nextLine();

        System.out.println("Ingrese los accesorios:");
        String accesorios = teclado.nextLine();

        System.out.println("Ingrese el procesador:");
        String procesador = teclado.nextLine();

        //teclado.nextLine();

        System.out.println("Ingrese la ram:");
        int ram= teclado.nextInt();

        teclado.nextLine();

        System.out.println("Ingrese el sistema operativo:");
        String sistemaOperativo = teclado.nextLine();

        //teclado.nextLine();

        System.out.println("Ingrese el almacenamiento:");
        int almacenamiento= teclado.nextInt();
        teclado.nextLine();

        if(tipo == TipoProducto.CELULAR)
        {
            teclado.nextLine();
            System.out.println("Ingrese detalles de la/s camaras traseras:");
            String camaraTrasera = teclado.nextLine();

            //teclado.nextLine();

            System.out.println("Ingrese la cantidad de camaras:");
            int cantCamaras= teclado.nextInt();
            teclado.nextLine();
            System.out.println("Ingrese detalle de la camara frontal:");
            String camaraFrontal = teclado.nextLine();

            productoNuevo = new Celular(tipo, modelo, marca, precio, stock, descripcion, 0, 0, tamaneoPantalla, resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento, camaraTrasera, cantCamaras, camaraFrontal);

        } else if (tipo == TipoProducto.TELEVISOR)
        {
            System.out.println("Es samart (Ingrese SI o NO):");
            String op = teclado.nextLine();
            boolean smart = false;
            if(op.equalsIgnoreCase("si"))
            {
                smart = true;
            }
            teclado.nextLine();
            System.out.println("Ingrese el numero de la opcion:\n");
            System.out.println("1) LED - 2)LCD - 3)AMOLED - 4)OLED");
            int opcion = teclado.nextInt();
            TipoPantalla tipoPantalla = null;
            switch (opcion)
            {

                case 1:
                    tipoPantalla = TipoPantalla.LED;
                    break;
                case 2:
                    tipoPantalla = TipoPantalla.LCD;
                    break;
                case 3:
                    tipoPantalla = TipoPantalla.AMOLED;
                    break;
                case 4:
                    tipoPantalla = TipoPantalla.OLED;
                    break;
            }
            //teclado.nextLine();
            productoNuevo = new Televisor(tipo, modelo, marca, precio, stock, descripcion, 0, 0, tamaneoPantalla, resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento, smart, tipoPantalla);

        } else if (tipo == TipoProducto.COMPUTADORA)
        {

            System.out.println("Ingrese detalle de la webCam:");
            String webCam = teclado.nextLine();

            System.out.println("Ingrese detalle de la bateria:");
            String bateria = teclado.nextLine();

            System.out.println("Tiene lector(Ingrese SI o NO):");
            String op = teclado.nextLine();
            boolean lector = false;
            if(op.equalsIgnoreCase("si"))
            {
                lector = true;
            }
            teclado.nextLine();
            System.out.println("Ingrese el numero de la opcion:\n");
            System.out.println("1) PCDEESCRITORIO - 2)NOTEBOOK - 3)NETBOOK");
            int opcion = teclado.nextInt();
            TipoPc tipoPc = null;
            switch (opcion)
            {

                case 1:
                    tipoPc = TipoPc.PCDEESCRITORIO;
                    break;
                case 2:
                    tipoPc = TipoPc.NOTEBOOK;
                    break;
                case 3:
                    tipoPc = TipoPc.NETBOOK;
                    break;
            }
            teclado.nextLine();
            productoNuevo = new Computadora(tipo, modelo, marca, precio, stock, descripcion, 0, 0, tamaneoPantalla, resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento, webCam, bateria, lector, tipoPc);
        }
        //teclado.nextLine();
        return productoNuevo;
    }

    public Factura generarFactura(ArrayList <Producto> productos, String metodoDePago)
    {
        double precio=0;
        for (Producto p : productos)
        {
            usuario.agregarAlHistorial(p);

            p.setVendidos();
            p.disminuirStock();
            precio=precio+p.getPrecio();

        }

        Factura factura = new Factura(productos, usuario.getApellido(), usuario.getNombre(), usuario.getEmail(), metodoDePago, precio);

        gestoraDeFacturas.agregarFactura(factura);
        gestoraDeFacturas.guardarArchivo("Facturas");
        usuario.limpiarCarrito();
        return factura;
    }

    private ArrayList<Producto> comprarProducto ()
    {
        int seguirComprando=0;
        ArrayList<Producto> productosAComprar= new ArrayList<>();

        do {
            System.out.println("Ingrese el producto que desee comprar: ");
            int opcionProducto= teclado.nextInt();

            System.out.println("Ingrese la cantidad de productos: ");
            int cant=teclado.nextInt();

            Producto producto= usuario.getMiCarrito().get(opcionProducto-1); //-1 porque el carrito va a mostrar desde la posicion 1, pero el array empieza en la 0

            for(int i=0; i<cant; i++)
            {
                productosAComprar.add(producto);
            }

            System.out.println("Desea seguir comprando? 1-Si 2-No ");
            seguirComprando = teclado.nextInt();

        } while (seguirComprando==1);

        teclado.nextLine();

        return productosAComprar;
    }

    private int cicloComentario() {
        System.out.println("\n COMENTARIO");
        Menu.volver();

        do{
            opcion=teclado.nextInt();

        }while (opcion != 9);

        return opcion;
    }



}
