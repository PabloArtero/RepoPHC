
package persistencia.MYSQL;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private final String DRIVER_JDBC = "com.mysql.jdbc.Driver";//Driver JDBC
    private final String BASE = "phc";//Nombre de la base de datos
    private final String USER = "root";//Nombre de usuario
    private final String PASSWORD = "1234";//Contraseña
    private final String URL = "jdbc:mysql://localhost:3306/" + BASE;//DBMS al que se conecta el driver JDBC, dirección, puerto y nombre de la Base de Datos
    
    private Connection con = null;//Conexión con la base de datos
    
    public Connection getConexion()
    {       
        try{
            Class.forName(DRIVER_JDBC);
            con = (Connection) DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
            
        } catch(SQLException e)
        {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
      return con;  
    }
}
