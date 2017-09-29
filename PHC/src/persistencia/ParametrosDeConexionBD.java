package persistencia;

/**
 * Parámetros de conexión a la base de datos.
 * 
 * @author Usuario
 */
public class ParametrosDeConexionBD {
    /**
     * Driver JDBC.
     * Por ejemplo, el driver JDBC-ODBC es "sun.jdbc.odbc.JdbcOdbcDriver"
     * y el driver JDBC-MySQL es "com.mysql.jdbc.Driver"
     * El driver debe estar agregado a las bibliotecas del proyecto para que se pueda cargar.
     */
    public static final String DRIVER_JDBC = "com.mysql.jdbc.Driver";   
    /**
     * URL del servidor.
     * Tiene la forma: "jdbc:subprotocolo//servidor:puerto/nombreBaseDeDatos"
     * Si no se especifica el nombre de la base de datos la conexión se establece con el servidor.
     * Es como loguearse a MySQL. Pero si incluimos el nombre de la base de datos es como hacer un 
     * USE nombreBaseDeDatos luego de loguearse al servidor.
     */
    public static final String URL = "jdbc:mysql://localhost:3306/phc";
    
    public static final String USUARIO = "root";
    
    public static final String CONTRASEÑA = "1234";
}
