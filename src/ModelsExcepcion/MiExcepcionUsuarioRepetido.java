package ModelsExcepcion;

import java.time.LocalDateTime;

public class MiExcepcionUsuarioRepetido extends Exception
{
    private String fechaError;


    public MiExcepcionUsuarioRepetido (String mensaje)
    {
        super(mensaje);
        fechaError = LocalDateTime.now().toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
