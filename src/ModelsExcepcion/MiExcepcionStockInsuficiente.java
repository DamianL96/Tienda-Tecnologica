package ModelsExcepcion;

import Interfaces.IformatFecha;
import ModelsProducto.Producto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**Excepcion que notifica que el stock de x producto no es suficiente para realizar la compra
 * */
public class MiExcepcionStockInsuficiente extends Exception implements IformatFecha {
    private Producto producto;
    private String fechaError;

    public MiExcepcionStockInsuficiente (String mensaje, Producto producto)
    {
        super(mensaje);
        this.producto=producto;
        fechaError = fechaFormateada();
    }

    @Override
    public String fechaFormateada()
    {
        LocalDateTime fechaLocal = LocalDateTime.now();
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaLocal.format(fechaFormateada);
    }
    @Override
    public String getMessage() {
        return producto + super.getMessage() ;
    }
}
