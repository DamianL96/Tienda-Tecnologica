package ModelsInterfazGrafica;

import ModelsEnum.TipoUsuario;
import ModelsExcepcion.MiExcepcionContraseniaIncorrecta;
import ModelsExcepcion.MiExcepcionNombreDeUsuario;
import ModelsExcepcion.MiExcepcionUsuarioRepetido;
import ModelsGestoras.GestoraDeProductos;
import ModelsGestoras.GestoraDeUsuarios;
import ModelsUsuario.Usuario;

import java.util.Scanner;

public class Sistema
{
    private GestoraDeUsuarios gestoraDeUsuarios;
    private GestoraDeProductos gestoraDeProductos;
    private Scanner teclado;
    private Usuario usuario;
    private int opcion;

    public Sistema()
    {
        this.teclado = new Scanner(System.in);
        this.gestoraDeUsuarios= new GestoraDeUsuarios();
        this.gestoraDeProductos= new GestoraDeProductos();
        this.usuario = new Usuario();
        this.opcion = -1;
    }

/**
    public void cicloPrograma()
    {

        do{
            opcion = cicloMenuPrincipal();
        }while (opcion!=0);

    }
*/

    public void cargaSistema()
    {
        gestoraDeUsuarios.leeArchivo();
        gestoraDeProductos.leeArchivo();
    }

    public void guardaSistema()
    {
        gestoraDeUsuarios.guardarArchivo();
        gestoraDeProductos.guardarArchivo();
    }


    public int cicloMenuPrincipal() {  //menu donde se dara las 3 opciones principales crear usuario - iniciar seccion - ver catalogo
        cargaSistema();

        do{
            Menu.muestraMenuPrincipal();
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    //inicio de sesion
                    try {
                        usuario = iniciarSesion();
                        boolean admin = verificaTipoUsuario(usuario);
                        if(admin == true)
                        {
                            //cicloOpcionesAdmin();
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

                    break;

                case 2:

                    usuario = registrarUsuario();
                    cicloOpcionesUsuario();
                    break;

                case 3:
                    //Catalogo
                    opcion= cicloMuestraCatalogo();
                    break;
            }
        }while (opcion != 9);
        guardaSistema();

        return opcion;
    }



    public int cicloMuestraCatalogo(){
        Menu.muestraCatalogo();
        do{
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    //catalogo completo
                    System.out.println("Catalogo completo");
                    opcion= cicloOpcionesDeCatalogo();
                    break;

                case 2:
                    System.out.println("Catalogo Celulares");
                    opcion= cicloOpcionesDeCatalogo();
                    //celulares
                    break;

                case 3:
                    //Smart TV's
                    System.out.println("Catalogo Smart tvs");
                    opcion= cicloOpcionesDeCatalogo();
                    break;

                case 4:
                    //Computadoras
                    System.out.println("Catalogo computadoras");
                    opcion= cicloOpcionesDeCatalogo();
                    break;
            }
        }while (opcion != 9);

        return opcion;
    }



    public int cicloVerProducto (){
        Menu.verProductoMarcaModelo();
        do {
            opcion= teclado.nextInt();

            switch (opcion) {
                case 1://agregar al carrito
                    System.out.println("producto agregado al carrito");
                    break;

                case 2://compra
                    cicloComprar();
                    break;
                case 3://comentario
                    cicloComentario();
                case 9:
                    //Volver
                    break;
            }
        }while (opcion != 9);

        return opcion;
    }

    private int cicloComentario() {
        Menu.menuComentar();

        do{
            opcion=teclado.nextInt();

        }while (opcion != 9);

        return opcion;
    }

    public int cicloOpcionesDeCatalogo(){
        Menu.opcionesCatalogo();
        do{
            opcion = teclado.nextInt();


            switch (opcion) {
                case 1://ver producto
                    cicloVerProducto();
                    break;

                case 9:
                    //Volver
                    break;
            }
        }while (opcion != 9);
        return opcion;
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
                    cicloVerCarrito();
                    break;

                case 3:
                    //Mis Datos
                    cicloVerMisDatos();
                    break;

                case 9:
                    //Volver
                    break;

            }
        }while (opcion != 9);

        //return opcion;
    }


    public int cicloVerCarrito(){
        Menu.muestraCarrito();

        do {
            opcion= teclado.nextInt();

            switch (opcion){
                case 1:
                    cicloComprar();
                    break;

                case 2:
                    System.out.println("Carrito limpio");
                    break;

                case 9:
                    break;

            }
        }while (opcion != 9);

        return opcion;
    }


    public int cicloComprar(){
        Menu.opcionesCompra();
        do {
            opcion= teclado.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Efectivo-Tarjeta-Transfencia");
                    break;
                case 9:
                    break;
            }
        }while (opcion != 9);

        return opcion;
    }

    public int cicloVerMisDatos(){
        Menu.verMisDatos();

        do {
            opcion= teclado.nextInt();
        }while (opcion != 9);

        return opcion;
    }






    /**
    public void cicloPrograma()
    {
        //menu donde se dara las 3 opciones principales
        //crear usuario - iniciar seccion - ver catalogo
        int opcion = 0;
        GestoraDeUsuarios usuarios = new GestoraDeUsuarios();
        do{
            opcion = cicloMenuPrincipal(opcion);
        }while (opcion!=0);

    }


    public int cicloMenuPrincipal(int opcion){
            Menu.muestraMenuPrincipal();
            opcion = teclado.nextInt();
            Usuario usuario = null;

            switch (opcion) {
                case 1:
                    //inicio de sesion
                    try {
                        usuario = iniciarSesion();
                    }
                    catch (MiExcepcionNombreDeUsuario ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    boolean admin = verificaTipoUsuario(usuario);
                    if(admin == true)
                    {
                        //menu de adminstrador
                    }
                    else
                    {
                        //menu de usuario normal
                    }
                    break;
                case 2:
                    //registrarse
                    /*boolean exitoRegisto=false;
                    int opc=1;
                    do {
                        try
                        {
                            exitoRegisto=registrarUsuario();
                        }catch (MiExcepcionNombreDeUsuario e)
                        {
                            System.out.println(e.getMessage());
                        }
                        if(exitoRegisto==false)
                        {
                            System.out.println("\n" + "1- Internar registrarse nuevamente. " + "\n" + "2- Iniciar sesion. "  + "\n" + "3- Volver al menu. ");
                            op=teclado.nextInt();
                        }
                    }while(exitoRegisto==false && opc==1);
                    break;
                case 3:
                    //Menu
                    opcion= cicloMuestraCatalogo(opcion);
                    break;
            }
            return opcion;
    }

    public int cicloMuestraCatalogo(int opcion){
        Menu.muestraCatalogo();

        opcion = teclado.nextInt();


        switch (opcion) {
            case 1:
                //catalogo completo

                break;

            case 2:
                //celulares
                break;

            case 3:
                //Smart TV's

                break;

            case 4:
                //Computadoras
                break;

            case 5:
                //Volver
                break;
        }
        return opcion;
    }

    */


    /** Metodo que verifica los datos para el inicio de sesion
     * utiliza verificar contrasenia y verificar nombre de usuario
     * @param
     * @return
     */
    public Usuario iniciarSesion() throws MiExcepcionNombreDeUsuario//de inicio de sesion
    {
        boolean flag=false;

        System.out.println("Ingresar nombre de usuario: ");
        String nombreDeUsuario=teclado.next();
        Usuario usuario=gestoraDeUsuarios.verificarNombreDeUsuario(nombreDeUsuario);

        if(usuario!=null)
        {
            do {
                try {
                    System.out.println("Ingresar contraseña: ");
                    String contrasenia = teclado.next();

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
            String codigoAdmin=teclado.next();
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
        System.out.println("Nombre: ");
        String nombre=teclado.next();

        System.out.println("Apellido: ");
        String apellido=teclado.next();

        System.out.println("Nombre de usuario: ");
        String nombreDeUsuario=teclado.next();

        System.out.println("Email: ");
        String email=teclado.next();

        System.out.println("Contraseña: ");
        String contrasenia=teclado.next();

        System.out.println("Codigo postal: ");
        String codigoPostal=teclado.next();

        Usuario usuario=new Usuario(nombre, apellido, codigoPostal, nombreDeUsuario, email, contrasenia, TipoUsuario.CLIENTE);

        return usuario;
    }






}
