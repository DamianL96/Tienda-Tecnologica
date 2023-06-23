
package ModelsGestoras;

import ModelsControladoraArchivos.ControladoraArchivo;
import ModelsExcepcion.MiExcepcionContraseniaIncorrecta;
import ModelsExcepcion.MiExcepcionNombreDeUsuario;
import ModelsProducto.Producto;
import ModelsUsuario.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * clase utilizada para almacenar todos los datos de tipo usuario
 * utiliza un hashSet ya que deseamos que nuestros usuarios no se repitan
 * se comparan por nombre de usuario
 */
public class GestoraDeUsuarios {
    private HashSet<Usuario> usuarios;

    public GestoraDeUsuarios() {
        usuarios = new HashSet<Usuario>();
    }


    /**
     * Metodo que verifica la existencia del usuario
     * en caso de que no, lanza una excepcion para notificar que no existe - te invita a registrarte
     *
     * @param nombreUsuario
     * @return Usuario
     */
    public Usuario verificarNombreDeUsuario(String nombreUsuario) {

        Iterator<Usuario> it = usuarios.iterator();
        Usuario encontrado = null;
        int result = 1;

        while (it.hasNext() && result == 1) {
            Usuario aux = it.next();
            if (aux.getNombreDeUsuario().equals(nombreUsuario)) {
                encontrado = aux;
                result = 0;
            }
        }
        return encontrado;
    }


    /**
     * Metodo que verifica si esta bien la contraseña
     * en caso de que no, lanza una excepcion para ingresarla de nuevo
     * @param contrasenia
     * @param
     * @return Boolean
     */
    public boolean verificarContrasenia(String contrasenia, Usuario usuario) throws MiExcepcionContraseniaIncorrecta {
        boolean flag = false;

        int result = CharSequence.compare(usuario.getContrasenia(), contrasenia);

        if (result == 0) {
            flag = true;
        } else {
            throw new MiExcepcionContraseniaIncorrecta("ERROR - Contraseña incorrecta");
        }
        return flag;
    }

    /**
     * Verifica si el codigo ingresado por el administrador es correcto
     * @param usuario
     * @param codigoDeAdmin
     * @return Boolean
     */
    public boolean verificarCodigoDeAdmin(Usuario usuario, String codigoDeAdmin) throws MiExcepcionContraseniaIncorrecta {
        boolean flag = false;

        if (codigoDeAdmin.equals(usuario.getCodigoAdmin())) {
            flag = true;
        } else {
            throw new MiExcepcionContraseniaIncorrecta("ERROR - Codigo incorrecto.");
        }

        return flag;
    }

    /**
     * Metodo que añade un usuario al set
     * no puede repetirse el nombre de usuario
     * @param usuario
     * @return Boolean
     */
    public boolean agregarUsuario(Usuario usuario) {
        boolean flag = false;
        Usuario aux = verificarNombreDeUsuario(usuario.getNombreDeUsuario());

        if (aux == null) {
            usuario.setId(usuario.ultimoId + 1);
            usuario.ultimoId = usuario.getId();
            flag = usuarios.add(usuario);
        }

        return flag;
    }

    /**
     * Metodo que busca el usuario solo por el nombre del user ---no se si es necesario ??????? /seria para el admin
     * @param nombreDeUsuario
     * @return Usuario
     */
    public Usuario buscarUsuario(String nombreDeUsuario) {
        Iterator<Usuario> it = usuarios.iterator();

        Usuario encontrado = new Usuario();

        while (it.hasNext()) {
            Usuario aux = it.next();

            if (aux.getNombreDeUsuario().equals(nombreDeUsuario)) {
                encontrado = aux;
            }
        }
        return encontrado;
    }

    /**
     * Metodo que elimina un usuario, ya sea que el admin lo elimine o el usuario desea borrar su cuenta
     * @param usuario
     * @return Boolean
     */
    public boolean eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        return true;
    }

    /**
     * funcion que invoca a una clase generica que desde un array list nos alamcena
     * todos los datos en un archivo binario de usuarios.
     */
    public void guardarArchivo() {
        ArrayList<Usuario> aGuardar = new ArrayList<>();
        Iterator<Usuario> it = usuarios.iterator();

        while (it.hasNext()) {
            Usuario aux = it.next();
            aGuardar.add(aux);
        }

        ControladoraArchivo.guardar(aGuardar, "Usuarios.dat");
    }


    /**
     * lee un archivo y almacena sus datos en el hashSet de usuarios
     */
    public void leeArchivo()
    {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ControladoraArchivo.leer(usuarios, "Usuarios.dat");

        for(Usuario us: usuarios)
        {
            agregarUsuario(us);
        }
    }

    /**
     * funcion que retorna en un String la lista de usuarios
     * @return String
     */
    public String infoUsuarios()
    {
        Iterator<Usuario> it= usuarios.iterator();
        String info= "";

        while(it.hasNext())
        {
            Usuario aux=it.next();
            info += "\n" + aux.toString();
        }
        return info;
    }

}
