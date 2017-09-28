package phc;

/**
 * Alberga las variables globales del sistema.
 * 
 * @author Usuario
 */
public class VariablesDelSistema {
    
    /**
     * Identifica al usuario que inició sesión.
     * Es su id de empleado y de usuario en la base de datos. La clave principal en la tabla usuario es idEmpleado.
     * Inicialmente vale 0 indicando que ningún usuario inició sesión aún.
     */
    public static Integer idUsuarioActual = 0;

    /**
     * Indica si el usuario actual del sistema es un administrador. Sirve principalmente para que el constructor de la
     * pantalla principal sepa si debe activar aquellos ítems de menú reservados para los administradores del sistema.
     */
    public static Boolean usuarioEsAdministrador = false;

    /**
     * indica si se restauró la contraseña del usuario actual, en cuyo caso
     * deberá cambiarla por otra obligatoriamente. También será false si
     * es la primera vez que el usuario inicia sesión en el sistema.
     */
    public static Boolean contraseñaRestaurada = false;
    
}
