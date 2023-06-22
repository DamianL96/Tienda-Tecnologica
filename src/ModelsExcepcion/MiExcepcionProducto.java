package ModelsExcepcion;

import Interfaces.IformatFecha;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MiExcepcionProducto extends Exception implements IformatFecha
{
    private String fechaError;

    public MiExcepcionProducto(String mensaje)
    {
        super(mensaje);
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
        return super.getMessage() + "\n" + fechaError;
    }
}
