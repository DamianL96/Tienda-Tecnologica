package ModelsExcepcion;

import ModelsProducto.Producto;

import java.time.LocalDateTime;

public class MiExcepcionStockInsuficiente extends Exception {
    private Producto producto;
    private String fechaError;

    public MiExcepcionStockInsuficiente (String mensaje, Producto producto)
    {
        super(mensaje);
        this.producto=producto;
        fechaError = LocalDateTime.now().toString();
    }

    @Override
    public String getMessage() {
        return producto + super.getMessage() ;
    }
}
