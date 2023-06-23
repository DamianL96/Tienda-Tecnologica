import ModelsEnum.TipoPantalla;
import ModelsEnum.TipoPc;
import ModelsEnum.TipoProducto;
import ModelsEnum.TipoUsuario;
import ModelsFactura.Factura;
import ModelsGestoras.GestoraDeFacturas;
import ModelsGestoras.GestoraDeProductos;
import ModelsGestoras.GestoraDeUsuarios;
import ModelsGestoras.JsonUtiles;
import ModelsInterfazGrafica.Sistema;
import ModelsProducto.Celular;
import ModelsProducto.Computadora;
import ModelsProducto.Producto;
import ModelsProducto.Televisor;
import ModelsUsuario.Usuario;
import org.json.JSONArray;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
         /*Usuario usuario = new Usuario("admin", "administrador", "7600", "elAdmin123", "administrador@gmail.com", "admin123", TipoUsuario.ADMINISTRADOR);
        usuario.setCodigoAdmin("holajava");

        Usuario usuario2 = new Usuario("damian", "lambrecht", "7600", "dami", "dami@gmail.com", "dami123", TipoUsuario.CLIENTE);
        GestoraDeUsuarios gestora = new GestoraDeUsuarios();
        gestora.agregarUsuario(usuario);
        gestora.agregarUsuario(usuario2);
        gestora.guardarArchivo();

        Producto celular = new Celular(TipoProducto.CELULAR, "11", "Iphone", 150000, 5, null, 0, 0, 6.1, "FULL HD", "Cable de carga", "m1", 2, "IOS 15", 128, "Camara principal de 12mpx y un gran angulas de 8 mpx", 2, "1 sensor de 8 mpx");
        Producto compu = new Computadora(TipoProducto.COMPUTADORA, "ThinkPad", "Lenovo", 450000, 2, null, 2, 0, 15.6, "FULL HD", "Cargador", "Intel I5", 8, "Windows 10", 256, "720p", "5 horas carga maxima", true, TipoPc.NETBOOK);
        Producto tele = new Televisor(TipoProducto.TELEVISOR, "50AU7000", "Samsung", 147000, 80, null, 1, 0, 52, "4k 120fps", "Control remoto", "no se", 2, "samsun smart", 2, true, TipoPantalla.LED);
        Producto celular1 = new Celular(TipoProducto.CELULAR, "s23", "samsung", 350000, 1, null, 0, 0, 7.0, "FULL HD- 4k", "Cable de carga - auriculares", "snap dragon 880", 12, "andriod 15", 256, "Camara principal de 108 mpx con gran angular de 25 mpx y ultra", 4, "1 sensor de 58 mpx");
        Producto compu1 = new Computadora(TipoProducto.COMPUTADORA, "IdeaPad", "Lenovo", 300000, 8, null, 0, 0, 15.6, "720", "Cargador", "Intel I3", 8, "Windows 11", 512, "1080", "4 horas carga maxima", false, TipoPc.NOTEBOOK);
        Producto tele1 = new Televisor(TipoProducto.TELEVISOR, "TCL sport", "TCL", 120000, 25, null, 1, 0, 32, "1080", "Control remoto", "no se", 2, "TCL", 2, false, TipoPantalla.LCD);

        GestoraDeProductos gestoraP = new GestoraDeProductos();
        gestoraP.agregar(celular.getTipoProducto(), celular);
        gestoraP.agregar(compu.getTipoProducto(), compu);
        gestoraP.agregar(tele.getTipoProducto(), tele);
        gestoraP.agregar(celular1.getTipoProducto(), celular1);
        gestoraP.agregar(compu1.getTipoProducto(), compu1);
        gestoraP.agregar(tele1.getTipoProducto(), tele1);
        gestoraP.guardarArchivo();*/
        //System.out.println(JsonUtiles.leer("Facturas"));




        Sistema sistema = new Sistema();
        sistema.cicloPrograma();
    }
}