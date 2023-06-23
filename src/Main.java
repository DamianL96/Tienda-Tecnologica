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

        Sistema sistema = new Sistema();
        sistema.cicloPrograma();
        
    }
}