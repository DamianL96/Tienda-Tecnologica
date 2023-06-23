package Interfaces;

/**
 * Interface creada para generar 2 metodos que se utilizan en las gestoras para guardar
 * los datos en los archivos
 */
public interface IArchivos {
    public void guardarArchivo(String nombreArchivo);

    public void leerArchivo(String nombreArchivo);
}
