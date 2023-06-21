package ModelsProducto;

import ModelsUsuario.Usuario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comentario implements Serializable
{
    private String nombreUsuario;
    private String email;
    private String fecha;
    private int puntuacion;
    private String comentario;

    public Comentario()
    {
        this.nombreUsuario = "";
        this.email = "";
        this.fecha = fechaFormateada();
        this.puntuacion = 0;
        this.comentario = "";
    }

    public Comentario(String nombreUsuario, String email, int puntuacion, String comentario) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.fecha = fechaFormateada();
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public String fechaFormateada()
    {
        LocalDateTime fechaLocal = LocalDateTime.now();
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaLocal.format(fechaFormateada);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "\nUsuario: " + nombreUsuario +
                "\nEmail: " + email +
                "\nFecha: " + fecha +
                "\nPuntuacion: "+ puntuacion +
                "\nComentario: " + comentario;
    }
}
