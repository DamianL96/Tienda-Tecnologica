package ModelsExcepcion;

import Interfaces.IformatFecha;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/** Excepcion que se utiliza a la hora de agregar y buscar productos
 * */
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
