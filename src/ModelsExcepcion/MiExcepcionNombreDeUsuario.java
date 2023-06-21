package ModelsExcepcion;

import java.time.LocalDateTime;

public class MiExcepcionNombreDeUsuario extends Exception{
    private String fechaError;


    public MiExcepcionNombreDeUsuario (String mensaje)
    {
        super(mensaje);
        fechaError = LocalDateTime.now().toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
