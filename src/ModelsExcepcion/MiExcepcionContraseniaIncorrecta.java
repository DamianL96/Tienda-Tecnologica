package ModelsExcepcion;

import java.time.LocalDateTime;

public class MiExcepcionContraseniaIncorrecta extends Exception{
    private String fechaError;

    public MiExcepcionContraseniaIncorrecta(String mensaje)
    {
        super(mensaje);
        fechaError = LocalDateTime.now().toString();

    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n" + fechaError;
    }
}
