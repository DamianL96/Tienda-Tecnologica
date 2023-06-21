package ModelsExcepcion;

import java.time.LocalDateTime;
import java.util.Date;

public class MiExcepcionProducto extends Exception
{
    private String fechaError;

    public MiExcepcionProducto(String mensaje)
    {
        super(mensaje);
        fechaError = LocalDateTime.now().toString();

    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n" + fechaError;
    }
}
