import ModelsEnum.TipoPantalla;
import ModelsEnum.TipoPc;
import ModelsEnum.TipoProducto;
import ModelsEnum.TipoUsuario;
import ModelsGestoras.GestoraDeFacturas;
import ModelsGestoras.GestoraDeProductos;
import ModelsGestoras.GestoraDeUsuarios;
import ModelsInterfazGrafica.Sistema;
import ModelsProducto.Celular;
import ModelsProducto.Computadora;
import ModelsProducto.Producto;
import ModelsProducto.Televisor;
import ModelsUsuario.Usuario;

public class Main {
    public static void main(String[] args)
    {

        GestoraDeFacturas facturas= new GestoraDeFacturas();
        

       /* Producto celular = new Celular(TipoProducto.CELULAR, "11", "Iphone", 150000, 5, null, 0, 0, 6.1, "FULL HD", "Cable de carga", "m1", 2, "IOS 15", 128, "Camara principal de 12mpx y un gran angulas de 8 mpx", 2, "1 sensor de 8 mpx");
        Producto compu = new Computadora(TipoProducto.COMPUTADORA, "ThinkPad", "Lenovo", 450000, 2, null, 2, 0, 15.6, "FULL HD", "Cargador", "Intel I5", 8, "Windows 10", 256, "720p", "5 horas carga maxima", true, TipoPc.Notebook);
        Producto tele = new Televisor(TipoProducto.TELEVISOR, "50AU7000", "Samsung", 147000, 80, null, 1, 0, 52, "4k 120fps", "Control remoto", "no se", 2, "samsun smart", 2, true, TipoPantalla.LED);
        Producto celular1 = new Celular(TipoProducto.CELULAR, "s23", "samsung", 350000, 1, null, 0, 0, 7.0, "FULL HD- 4k", "Cable de carga - auriculares", "snap dragon 880", 12, "andriod 15", 256, "Camara principal de 108 mpx con gran angular de 25 mpx y ultra", 4, "1 sensor de 58 mpx");
        Producto compu1 = new Computadora(TipoProducto.COMPUTADORA, "IdeaPad", "Lenovo", 300000, 8, null, 0, 0, 15.6, "720", "Cargador", "Intel I3", 8, "Windows 11", 512, "1080", "4 horas carga maxima", false, TipoPc.Notebook);
        Producto tele1 = new Televisor(TipoProducto.TELEVISOR, "TCL sport", "TCL", 120000, 25, null, 1, 0, 32, "1080", "Control remoto", "no se", 2, "TCL", 2, false, TipoPantalla.LCD);

        GestoraDeProductos gestoraDeProductos = new GestoraDeProductos();
        gestoraDeProductos.agregar(celular.getTipoProducto(), celular);
        gestoraDeProductos.agregar(compu.getTipoProducto(), compu);
        gestoraDeProductos.agregar(tele.getTipoProducto(), tele);
        gestoraDeProductos.agregar(celular.getTipoProducto(), celular1);
        gestoraDeProductos.agregar(compu.getTipoProducto(), compu1);
        gestoraDeProductos.agregar(tele.getTipoProducto(), tele1);
        gestoraDeProductos.guardarArchivo();*/
        //Sistema sistema = new Sistema();
        //sistema.cicloMenuPrincipal();
        /*Usuario usuario = new Usuario("admin", "administrador", "7600", "elAdmin123", "administrador@gmail.com", "admin123", TipoUsuario.ADMINISTRADOR);
        usuario.setCodigoAdmin("holajava");
        GestoraDeUsuarios gestora = new GestoraDeUsuarios();
        gestora.agregarUsuario(usuario);
        gestora.guardarArchivo();
        System.out.println(usuario.toString());*/



        /**GestoraDeUsuarios gestora = new GestoraDeUsuarios();
        gestora.leeArchivo();
        System.out.println(gestora.infoUsuarios());*/

    }

    //MARCOS: estuve probando cargar productos con esto, lo dejo de prueba
    /*
     //System.out.println(gestoraDeProductos.infoProductosDeCiertoTipo(TipoProducto.CELULAR));


        GestoraDeProductos gestoraDeProducto = new GestoraDeProductos();



        //System.out.println(gestoraDeProducto.infoProductos());Sistema sistema = new Sistema();


        //sistema.cicloPrograma();
        //System.out.println(gestoraDeProducto.infoProductosDeCiertoTipo(TipoProducto.CELULAR));



        Producto celular = new Celular(TipoProducto.CELULAR, "11", "Iphone", 150000, 5, null, 0, 0, 6.1, "FULL HD", "Cable de carga", "m1", 2, "IOS 15", 128, "Camara principal de 12mpx y un gran angulas de 8 mpx", 2, "1 sensor de 8 mpx");
        Producto compu = new Computadora(TipoProducto.COMPUTADORA, "ThinkPad", "Lenovo", 450000, 2, null, 2, 0, 15.6, "FULL HD", "Cargador", "Intel I5", 8, "Windows 10", 256, "720p", "5 horas carga maxima", true, TipoPc.Notebook);
        Producto tele = new Televisor(TipoProducto.TELEVISOR, "50AU7000", "Samsung", 147000, 80, null, 1, 0, 52, "4k 120fps", "Control remoto", "no se", 2, "samsun smart", 2, true, TipoPantalla.LED);
        Producto celular1 = new Celular(TipoProducto.CELULAR, "s23", "samsung", 350000, 1, null, 0, 0, 7.0, "FULL HD- 4k", "Cable de carga - auriculares", "snap dragon 880", 12, "andriod 15", 256, "Camara principal de 108 mpx con gran angular de 25 mpx y ultra", 4, "1 sensor de 58 mpx");
        Producto compu1 = new Computadora(TipoProducto.COMPUTADORA, "IdeaPad", "Lenovo", 300000, 8, null, 0, 0, 15.6, "720", "Cargador", "Intel I3", 8, "Windows 11", 512, "1080", "4 horas carga maxima", false, TipoPc.Notebook);
        Producto tele1 = new Televisor(TipoProducto.TELEVISOR, "TCL sport", "TCL", 120000, 25, null, 1, 0, 32, "1080", "Control remoto", "no se", 2, "TCL", 2, false, TipoPantalla.LCD);

        GestoraDeProducto gestoraDeProducto = new GestoraDeProducto();
        gestoraDeProducto.agregar(celular.getTipoProducto(), celular);
        gestoraDeProducto.agregar(compu.getTipoProducto(), compu);
        gestoraDeProducto.agregar(tele.getTipoProducto(), tele);
        gestoraDeProducto.agregar(celular.getTipoProducto(), celular1);
        gestoraDeProducto.agregar(compu.getTipoProducto(), compu1);
        gestoraDeProducto.agregar(tele.getTipoProducto(), tele1);

        //gestoraDeProducto.eliminaProducto("Lenovo", "ideaPad");

        System.out.println(gestoraDeProducto.infoProductos());

          /*
        Producto p = null;
        p = new Celular(TipoProducto.CELULAR, "Redmi note 11", "Xiaomi", 108999, 15, "Fotografía profesional en tu bolsillo\n Descubrí infinitas posibilidades para tus fotos con las 4 cámaras principales de tu equipo. Poné a prueba tu creatividad y jugá con la iluminación, diferentes planos y efectos para obtener grandes resultados.",0 ,null, 6.43,  "1080 px x 2400 px", "", "Snapdragon 680", 4, "Android 11", 128, "Tiene 4 camaras: 50 Mpx/8 Mpx/2 Mpx/2 Mpx", 4, "13 Mpx");
        gestoraDeProducto.agregar(p.getTipoProducto(), p);
        p = new Celular(TipoProducto.CELULAR, "Moto E22", "Motorola", 57000, 5, "",0 ,null, 6.43,  "1080 px x 2400 px", "", "Snapdragon 680", 4, "Android 11", 128, "Tiene 4 camaras: 50 Mpx/8 Mpx/2 Mpx/2 Mpx", 4, "13 Mpx");
        */

}