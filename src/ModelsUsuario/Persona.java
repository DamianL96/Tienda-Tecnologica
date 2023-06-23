package ModelsUsuario;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Clase con los datos que comparten todas las personas
 */
public abstract class Persona implements Serializable
{
    private String nombre;
    private String apellido;
    private String codigoPostal;

    public static int ultimoId;

    public Persona()
    {
        this.nombre = "";
        this.apellido = "";
        this.codigoPostal = "";
    }

    public Persona(String nombre, String apellido, String codigoPostal)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public boolean equals(Object obj) {

        boolean rta = false;
        if(obj != null)
        {
            if(obj instanceof Persona)
            {
                Persona aux = (Persona) obj;
                if(nombre.equals(aux.nombre) && apellido.equals(aux.apellido) && codigoPostal.equals(aux.codigoPostal))
                {
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apllido='" + apellido + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                '}';
    }
}
