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
public class Sistema {
    private GestoraDeUsuarios gestoraDeUsuarios;
    private GestoraDeProductos gestoraDeProductos;
    private GestoraDeFacturas gestoraDeFacturas;
    private Scanner teclado;
    private Usuario usuario;
    private String opcion;

    /**
     * Constructor de Sistema, inicializa el teclado, las gestoras de ususario, producto y facturas,
     * el usuario que luego iniciara sesion y la variable de opciones
     */
    public Sistema() {
        this.teclado = new Scanner(System.in);
        this.gestoraDeUsuarios = new GestoraDeUsuarios();
        this.gestoraDeProductos = new GestoraDeProductos();
        this.gestoraDeFacturas = new GestoraDeFacturas();
        this.usuario = new Usuario();
        this.opcion = " ";
    }

    /**
     * Lee los datos de ususario, producto y facturas de los archivos y json
     */
    public void cargaSistema() {
        gestoraDeUsuarios.leeArchivo();
        gestoraDeProductos.leeArchivo();
    }

    /**
     * Guarda todos los datos en los archivos correspondientes
     */
    public void guardaSistema() {
        gestoraDeUsuarios.guardarArchivo();
        gestoraDeProductos.guardarArchivo();
        //gestoraDeFacturas.guardarArchivo("Facturas");
    }

    /**
     * Carga los datos necesarios para el funcionamiento del sistema e inicia
     * el ciclo principal del programa
     */
    public void cicloPrograma() {
        cargaSistema();
        do {
            cicloMenuPrincipal();
        } while (opcion.compareTo("10") != 0);

    }

    /**
     * Muestra el menu principal inicio sesion - registrarse - catalogo
     * y permite elegir una opcion
     */
    public void cicloMenuPrincipal() {  //menu donde se dara las 3 opciones principales crear usuario - iniciar seccion - ver catalogo
        Menu.muestraMenuPrincipal();
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "1":
                    //inicio de sesion
                    cicloIniciarSesion();
                    break;

                case "2":
                    usuario = registrarUsuario();
                    gestoraDeUsuarios.guardarArchivo();
                    cicloOpcionesUsuario();
                    break;

                case "3":
                    //Catalogo
                    cicloMuestraCatalogo();
                    break;
            }
        } while (opcion.compareTo("10") != 0);
        guardaSistema();
    }

    /**
     * Verifica si quien inicia sesion es administrador o usuario para mostrar
     * las opciones correspondientes a cada uno
     */
    public void cicloIniciarSesion() {
        try {
            usuario = iniciarSesion();
            boolean admin = verificaTipoUsuario(usuario);
            if (admin)// si admin == true
            {
                cicloOpcionesAdministrador();
            } else
            {
                cicloOpcionesUsuario();
            }
        } catch (MiExcepcionNombreDeUsuario ex) {
            System.out.println(ex.getMessage());
            cicloMenuPrincipal();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Muestra el menu de catalogos posibles, y permite elegir entre catalogo completo o filtrar por tipo
     */
    public void cicloMuestraCatalogo() {
        Menu.muestraCatalogo();
        do {
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    //catalogo completo
                    System.out.println(gestoraDeProductos.infoProductos());
                    cicloOpcionesDeCatalogo(1);
                    break;

                case "2":
                    System.out.println(gestoraDeProductos.infoProductosDeCiertoTipo(TipoProducto.CELULAR));
                    cicloOpcionesDeCatalogo(1);
                    //celulares
                    break;

                case "3":
                    //Smart TV's
                    System.out.println(gestoraDeProductos.infoProductosDeCiertoTipo(TipoProducto.TELEVISOR));
                    cicloOpcionesDeCatalogo(1);
                    break;

                case "4":
                    //Computadoras
                    System.out.println(gestoraDeProductos.infoProductosDeCiertoTipo(TipoProducto.COMPUTADORA));
                    cicloOpcionesDeCatalogo(1);
                    break;

                case "9":
                    cicloMenuPrincipal();
                    break;
            }
        } while (opcion.compareTo("10") != 0);
    }

    /**
     * Pide ingresar la marca y el modelo del producto, lo busca en la coleccion,
     * si no lo encuentra retorna null
     *
     * @return Producto
     */
    public Producto cicloBuscarProducto() {
        teclado.nextLine();
        System.out.println("\n Ingresar MARCA del producto:");
        String marca = teclado.nextLine();

        System.out.println("\n Ingresar MODELO del producto:");
        String modelo = teclado.nextLine();
        teclado.nextLine();

        return gestoraDeProductos.buscaProductoColeccion(marca, modelo);
    }

    /**
     * Muestra el producto con las opciones que puede hacer el usuario, agregar al carrito o comprar.
     */
    public void cicloVerProducto() {
        String factura = "";
        Producto producto = cicloBuscarProducto();
        if (producto == null)
        {
            System.out.println("Producto no encontrado.");
            cicloOpcionesDeCatalogo(2);
        } else
        {
            System.out.println(producto.toString());//Muestro el producto
            Menu.verProductoMarcaModelo();
            do
            {
                opcion = teclado.nextLine();


                switch (opcion)
                {
                    case "1"://agregar al carrito
                        if (usuario == null)
                        {
                            cicloIniciarSesion();
                        }
                        usuario.agregarProductoAlCarrito(producto);
                        cicloVerCarrito(2);
                        break;

                    case "2"://compra
                        if (usuario == null)
                        {
                            cicloIniciarSesion();
                        }
                        usuario.agregarProductoAlCarrito(producto);
                        cicloComprar(usuario.getMiCarrito(), 1);
                        System.out.println(factura);
                        cicloOpcionesUsuario();
                        break;

                    case "9":
                        cicloMuestraCatalogo();
                        break;
                }
            } while (opcion.compareTo("10") != 0);
        }
    }


    /**
     * Permite entrar al menu de ver producto dentro de cualquier catalogo,
     * recibe un entero llamado "superior" para identificar quien lo invoca
     *
     * @param superior
     */
    public void cicloOpcionesDeCatalogo(int superior)
    {
        Menu.opcionesCatalogo();
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "1"://ver producto
                    cicloVerProducto();
                    break;

                case "9":
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
        } while (opcion.compareTo("10") != 0);
    }

    /**
     * Muestra las opciones del administrador
     */
    public void cicloOpcionesAdministrador()
    {
        teclado.nextLine();
        do
        {
            Menu.muestraOpcionesAdmin();
            opcion = teclado.nextLine();

            switch (opcion)
            {
                case "1":
                    cicloAgregarProducto();
                    //teclado.next();
                    break;

                case "2":
                    //gestionar inventario
                    cicloGestionarInventario();
                    break;
                case "3":
                    //ver facturas
                    cicloVerFacturas();
                    break;
                case "4":
                    //mis datos
                    cicloVerMisDatosAdmin();
                    break;

                case "9":
                    cicloMenuPrincipal();
                    break;
            }
            guardaSistema();
        } while (opcion.compareTo("10") != 0);
    }

    /**
     * Permite que el administrador agrege o reste stock y modifique los precios
     * de un producto especifico buscado por marca y modelo.
     */
    public void cicloGestionarInventario()
    {
        Producto producto = cicloBuscarProducto();
        if (producto == null)
        {
            System.out.println("Producto no encontrado.");
            cicloOpcionesAdministrador();
        }
        else
        {
            do
            {
                System.out.println(producto.toStringAdmin());
                Menu.menuStock();
                opcion = teclado.nextLine();
                switch (opcion)
                {
                    case "1"://agregar Stock
                        producto.agregarStock(cicloAgregarStock());
                        System.out.println(producto.toStringAdmin());
                        cicloOpcionesAdministrador();
                        break;

                    case "2"://quitar Stock
                        producto.quitarStock(cicloQuitarStock());
                        System.out.println(producto.toStringAdmin());
                        cicloOpcionesAdministrador();
                        break;

                    case "3"://moificar precio
                        producto.setPrecio(cicloModificarPrecio());
                        System.out.println(producto.toStringAdmin());
                        cicloOpcionesAdministrador();
                        break;

                    case "9":
                        cicloOpcionesAdministrador();
                        break;
                }
            } while (opcion.compareTo("10") != 0);
        }
    }

    /**
     * Pide al administrador ingresar la cantidad de stock que se quiere AGREGAR
     * y devuelve esa cantidad
     *
     * @return int
     */
    public int cicloAgregarStock() {
        System.out.println("Cantidad a agregar:");
        return teclado.nextInt();
    }

    /**
     * Pide al administrador ingresar la cantidad de stock que se quiere RESTAR
     * y devuelve esa cantidad
     *
     * @return int
     */
    public int cicloQuitarStock() {
        System.out.println("Cantidad a quitar:");
        return teclado.nextInt();
    }

    /**
     * Pide al administrador ingresar el nuevo precio del producto elegido
     * y devuelve el valor
     *
     * @return double
     */
    public double cicloModificarPrecio() {
        System.out.println("Nuevo Precio:");
        return teclado.nextDouble();
    }

    /**
     * Muestra las opciones del usuario
     */
    public void cicloOpcionesUsuario()
    {
        Menu.muestraOpcionesUsuario();
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "1":
                    //Catalogo
                    cicloMuestraCatalogo();
                    break;

                case "2":
                    //Carrito
                    if (usuario.getMiCarrito().size() > 0)
                    {
                        cicloVerCarrito(1);
                    }
                    else
                    {
                        System.out.println("Carrito vacio");
                        cicloOpcionesUsuario();
                    }
                    break;

                case "3":
                    //Mis Datos
                    cicloVerMisDatosUsuario();
                    break;

                case "9":
                    cicloMenuPrincipal();
                    break;
            }
        } while (opcion.compareTo("10") != 0);
    }

    /**
     * Muestra los productos del carrito, y permite comprarlos o limpiar el carrito,
     * recibe un entero "superior" para saber que metodo lo invoca
     *
     * @param superior
     */
    public void cicloVerCarrito(int superior)
    {
        System.out.println(usuario.getMiCarrito());
        Menu.muestraCarrito();
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "1":
                    cicloComprar(usuario.getMiCarrito(), 1);
                    cicloOpcionesUsuario();
                    break;

                case "2":
                    cicloLimpiaCarrito();
                    break;

                case "9":
                    switch (superior) //Para volver a su respectivo metodo que lo invoca
                    {
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
        } while (opcion.compareTo("10") != 0);
    }

    /**
     * Se encarga de la gestion al momento de realizar una compra y muestra la factura.
     * Recibe un listado de los productos que hay en el carrito
     * Recibe un entero "superior" para saber que metodo lo invoca
     *
     * @param productosAComprar
     * @param superior
     */
    public void cicloComprar(ArrayList<Producto> productosAComprar, int superior)
    {
        Menu.opcionesCompra();
        Factura factura = null;
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "1":
                    teclado.nextLine();
                    System.out.println("Efectivo-Tarjeta-Transfencia");
                    String metodoDePago = teclado.nextLine();

                    factura = generarFactura(productosAComprar, metodoDePago);
                    System.out.println(factura.toString());
                    cicloOpcionesUsuario();
                    break;

                case "9":
                    cicloOpcionesUsuario();
                    break;
            }
        } while (opcion.compareTo("10") != 0);
    }


    /**
     * Muestra los todos los datos del administrador
     */
    public void cicloVerMisDatosAdmin()
    {
        System.out.println("\n MIS DATOS");
        System.out.println(usuario.toString());
        Menu.volver();
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "9":
                    cicloOpcionesAdministrador();
                    break;
            }
        } while (opcion.compareTo("10") != 0);
    }

    /**
     * Elimina todos los productos del carrito
     */
    public void cicloLimpiaCarrito() //BORRA TODO EL CARRITO
    {
        usuario.limpiarCarrito();
        cicloVerCarrito(3);
    }

    /**
     * Muestra todos los datos del ususario, junto a su historial de compras y su carrito actual
     */
    public void cicloVerMisDatosUsuario()
    {
        System.out.println("\nMIS DATOS:");
        System.out.println(usuario.toString());
        Menu.volver();
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "9":
                    cicloOpcionesUsuario();
                    break;
            }
        } while (opcion.compareTo("10") != 0);
    }

    /**
     * Permite al administrador agregar un nuevo producto al sistema
     */
    public void cicloAgregarProducto()
    {
        System.out.println("\n AGREGAR PRODUCTO");
        agregarProducto();
        Menu.volver();
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "9":
                    cicloOpcionesAdministrador();
                    break;
            }
        } while (opcion.compareTo("10") != 0);
    }

    /**
    * Permite al administrador ver todas las facturas generadas por el sistema
    */
    public void cicloVerFacturas()
    {
        gestoraDeFacturas.leerArchivo("Facturas");
        System.out.println("VER FACTURAS");
        System.out.println(gestoraDeFacturas.listarFacturas());
        Menu.volver();
        do
        {
            opcion = teclado.nextLine();
            switch (opcion)
            {
                case "9":
                    cicloOpcionesAdministrador();
                    break;
            }
        } while (opcion.compareTo("10") != 0);
    }


    /** Metodo que verifica los datos para el inicio de sesion
     * utiliza verificar contrasenia y verificar nombre de usuario
     * @throws MiExcepcionNombreDeUsuario -ModelsExcepcion
     * @return Usuario
     */
    public Usuario iniciarSesion () throws MiExcepcionNombreDeUsuario//de inicio de sesion
    {
            boolean flag = false;
            teclado.nextLine();
            System.out.println("Ingresar nombre de usuario: ");
            String nombreDeUsuario = teclado.nextLine();
            Usuario usuario = gestoraDeUsuarios.verificarNombreDeUsuario(nombreDeUsuario);

            if (usuario != null)
            {
                do
                {
                    try
                    {
                        System.out.println("Ingresar contraseña: ");
                        String contrasenia = teclado.nextLine();
                        flag = gestoraDeUsuarios.verificarContrasenia(contrasenia, usuario);
                    }
                    catch (MiExcepcionContraseniaIncorrecta ex) //excepcion de contrasenia - puse eso para compilar marcos
                    {
                        System.out.println(ex.getMessage());
                    }
                } while (flag == false);
            }
            else
            {
                throw new MiExcepcionNombreDeUsuario("ERROR - no existe el usuario ingresado - registrese");
            }
            return usuario;
        }

    /**
    * * Verifica el nombre de usuario y devuelve true si es admin o false si es usuario comun
    * @param  usuario
    * @return boolean
    */
    public boolean verificaTipoUsuario (Usuario usuario)
    {
        boolean flag = false;
        if (usuario.getTipo() == TipoUsuario.ADMINISTRADOR)
        {
            System.out.println("Ingresar codigo de administrador: ");
            String codigoAdmin = teclado.nextLine();
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

    /**
    * Registra un nuevo usuario, y muestra error si el usuario se repite.
    * @return Usuario
    */
    public Usuario registrarUsuario ()
    {
        Usuario usuario = cargaUnUsuario();
        try
        {
            if (!gestoraDeUsuarios.agregarUsuario(usuario))
            {
                throw new MiExcepcionUsuarioRepetido("ERROR - usuario ya existente.");
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
    /**
    * Pide al usuario ingresar los datos para registrarse
    * @return Usuario
    */
    public Usuario cargaUnUsuario ()
    {
        boolean flag = true;
        teclado.nextLine();
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.println("Apellido: ");
        String apellido = teclado.nextLine();
        String nombreDeUsuario = "";
        do
        {
            System.out.println("Nombre de usuario: ");
            nombreDeUsuario = teclado.nextLine();

            if (gestoraDeUsuarios.verificarNombreDeUsuario(nombreDeUsuario) != null)
            {
                System.out.println("Nombre de usuario ya utilizado - eliga otro");
                flag = false;
            }
        } while (flag == false);

        System.out.println("Email: ");
        String email = teclado.nextLine();

        System.out.println("Contraseña: ");
        String contrasenia = teclado.nextLine();

        System.out.println("Codigo postal: ");
        String codigoPostal = teclado.nextLine();

        Usuario usuario = new Usuario(nombre, apellido, codigoPostal, nombreDeUsuario, email, contrasenia, TipoUsuario.CLIENTE);
        return usuario;
    }

    /**
    *Pide al administrador el tipod de producto para agregar un producto al sistema
    */
    public void agregarProducto() {
        System.out.println("Ingrese que tipo de producto desea cargar:");
        System.out.println("1) Televisor - 2)Celular - 3)Computadora");
        int opcion = teclado.nextInt();
        teclado.nextLine();
        TipoProducto tipoP = null;
        switch (opcion) {
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

    /**
    * Pide al Administrador los datos para completar el cargado de un nuevo producto, y lo retorna
    * @param tipo
    * @return Producto
    */
    public Producto cargaUnProducto (TipoProducto tipo)
    {
        Producto productoNuevo = null;
        System.out.println("Ingrese la marca:");
        String marca = teclado.nextLine();

        System.out.println("Ingrese el modelo:");
        String modelo = teclado.nextLine();

        System.out.println("Ingrese el precio (solo en numeros):");
        double precio = teclado.nextDouble();

        System.out.println("Ingrese el stock (solo en numeros):");
        int stock = teclado.nextInt();

        teclado.nextLine();

        System.out.println("Ingrese la descripcion:");
        String descripcion = teclado.nextLine();

        System.out.println("Ingrese el tamaño de la pantalla (solo en numeros):");
        double tamaneoPantalla = teclado.nextDouble();

        teclado.nextLine();

        System.out.println("Ingrese la resolucion:");
        String resolucion = teclado.nextLine();

        System.out.println("Ingrese los accesorios:");
        String accesorios = teclado.nextLine();

        System.out.println("Ingrese el procesador:");
        String procesador = teclado.nextLine();

        System.out.println("Ingrese la ram (solo en numeros) :");
        int ram = teclado.nextInt();

        teclado.nextLine();

        System.out.println("Ingrese el sistema operativo:");
        String sistemaOperativo = teclado.nextLine();

        System.out.println("Ingrese el almacenamiento (solo en numeros):");
        int almacenamiento = teclado.nextInt();
        teclado.nextLine();

        if (tipo == TipoProducto.CELULAR)
        {
            productoNuevo= ingresarCelular(tipo, modelo, marca, precio, stock, descripcion,tamaneoPantalla, resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento);
        }
        else if (tipo == TipoProducto.TELEVISOR)
        {
            productoNuevo = ingresarTelevisor(tipo, modelo, marca, precio, stock, descripcion,tamaneoPantalla, resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento);
        }
        else if (tipo == TipoProducto.COMPUTADORA)
        {
            productoNuevo = ingresarComputadora(tipo, modelo, marca, precio, stock, descripcion, tamaneoPantalla, resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento);
        }
        return productoNuevo;
    }

    public Producto ingresarCelular(TipoProducto tipo,String modelo,String marca,double precio,int stock,String descripcion,double tamaneoPantalla,String resolucion,String accesorios,String procesador,int ram,String sistemaOperativo,int almacenamiento){

        teclado.nextLine();
        System.out.println("Ingrese detalles de la/s camaras traseras:");
        String camaraTrasera = teclado.nextLine();

        System.out.println("Ingrese la cantidad de camaras (solo en numeros):");
        int cantCamaras = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Ingrese detalle de la camara frontal:");
        String camaraFrontal = teclado.nextLine();
        return new Celular(tipo, modelo, marca, precio, stock, descripcion, 0, 0, tamaneoPantalla, resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento, camaraTrasera, cantCamaras, camaraFrontal);
    }

    public Producto ingresarTelevisor(TipoProducto tipo,String modelo,String marca,double precio,int stock,String descripcion,double tamaneoPantalla,String resolucion,String accesorios,String procesador,int ram,String sistemaOperativo,int almacenamiento){

        System.out.println("Es samart (Ingrese SI o NO):");
        String op = teclado.nextLine();

        boolean smart = false;
        if (op.equalsIgnoreCase("si")) {
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
        return new Televisor(tipo, modelo, marca, precio, stock, descripcion, 0, 0, tamaneoPantalla, resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento,smart,tipoPantalla);
    }

    public Producto ingresarComputadora(TipoProducto tipo,String modelo,String marca,double precio,int stock,String descripcion,double tamaneoPantalla,String resolucion,String accesorios,String procesador,int ram,String sistemaOperativo,int almacenamiento)
    {
        System.out.println("Ingrese detalle de la webCam:");
        String webCam = teclado.nextLine();

        System.out.println("Ingrese detalle de la bateria:");
        String bateria = teclado.nextLine();

        System.out.println("Tiene lector(Ingrese SI o NO):");
        String op = teclado.nextLine();

        boolean lector = false;
        if (op.equalsIgnoreCase("si")) {
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
        return new Computadora(tipo,modelo,marca, precio, stock, descripcion,0,0, tamaneoPantalla,resolucion, accesorios, procesador, ram, sistemaOperativo, almacenamiento,webCam,bateria,lector,tipoPc);
    }

    /**
     *Recibe el metodo de pago, los productos del carrito, suma el precio y con los datos
     * del ususario genera la factura y la guarda en el sistema.
     * @param productos
     * @param metodoDePago
     * @return Factura
     */
    public Factura generarFactura(ArrayList<Producto> productos, String metodoDePago) {
        double precio = 0;
        for (Producto p : productos)
        {
            usuario.agregarAlHistorial(p);
            p.setVendidos();
            p.disminuirStock();
            precio = precio + p.getPrecio();
        }
        Factura factura = new Factura(productos, usuario.getApellido(), usuario.getNombre(), usuario.getEmail(), metodoDePago, precio);
        gestoraDeFacturas.agregarFactura(factura);
        gestoraDeFacturas.guardarArchivo("Facturas");
        usuario.limpiarCarrito();
        return factura;
    }

    /**
    * Permite elegir los productos del carrito que queremos comprar, devuelve los productos elegidos
    * @return ArrayList<Producto>
    */
    private ArrayList<Producto> comprarProducto ()
    {
        int seguirComprando = 0;
        ArrayList<Producto> productosAComprar = new ArrayList<>();
        do
        {
            System.out.println("Ingrese el producto que desee comprar: ");
            int opcionProducto = teclado.nextInt();
            System.out.println("Ingrese la cantidad de productos: ");
            int cant = teclado.nextInt();

            Producto producto = usuario.getMiCarrito().get(opcionProducto - 1); //-1 porque el carrito va a mostrar desde la posicion 1, pero el array empieza en la 0
            for (int i = 0; i < cant; i++)
            {
                productosAComprar.add(producto);
            }
            System.out.println("Desea seguir comprando? 1-Si 2-No ");
            seguirComprando = teclado.nextInt();

        } while (seguirComprando == 1);
        teclado.nextLine();
        return productosAComprar;
    }


    /**
    * Permite agregar comentarios a los productos
    * -NO LO LLEGAMOS A IMPLEMENTAR-
    */
    private void cicloComentario() {
        System.out.println("\n COMENTARIO");
        Menu.volver();
        do
        {
            opcion = teclado.nextLine();
        } while (opcion.compareTo("10") != 0);
    }




}
