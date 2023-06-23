package ModelsControladoraArchivos;

import java.io.*;
import java.util.ArrayList;

/**
 * clase generada para proveer dos funciones genericas
 */
public class ControladoraArchivo
{
    /**
     * funcion utilizada para guardar datos (cualquier tipo porque es generica) en un archivo
     * @param datos
     * @param nombreArch
     * @param <V>
     */
    public static <V> void guardar(ArrayList<V> datos, String nombreArch)
    {
        FileOutputStream file = null;
        ObjectOutputStream objEntrada = null;

        try
        {
            file = new FileOutputStream(nombreArch);
            objEntrada = new ObjectOutputStream(file);

            for(V dato :  datos)
            {
                objEntrada.writeObject(dato);
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            try {
                if (file != null)
                {
                    file.close();
                }
                if (objEntrada != null)
                {
                    file.close();
                }
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * funcion que lee un archivo de datos y almacena en un arraylist lo que va guardando
     * @param datos
     * @param nombreArch
     * @return ArrayList
     * @param <V>
     */
    public static<V> ArrayList<V> leer(ArrayList<V> datos, String nombreArch)
    {
        FileInputStream file = null;
        ObjectInputStream objSalida = null;

        try
        {
            file = new FileInputStream(nombreArch);
            objSalida = new ObjectInputStream(file);

            while (true)
            {
                datos.add((V) objSalida.readObject());
            }
        }
        catch (EOFException ex)
        {
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            try {

                if (file != null)
                {
                    file.close();
                }
                if (objSalida != null)
                {
                    file.close();
                }
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }

        return datos;
    }
}
