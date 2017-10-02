package persistencia.MYSQL;

import dominio.Nivel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.NivelDAO;

/**
 *
 * @author josdan
 */
public class MysqlNivelDAO extends Conexion implements NivelDAO{
    
    //private Connection conexion = null;
    
    final String INSERT = "INSERT INTO nivel(nombre) VALUES (?)";
    final String UPDATE = "UPDATE nivel SET nombre = ? WHERE idNivel = ?";
    final String DELETE = "DELETE FROM nivel WHERE idNivel = ?";
    final String GETALL = "SELECT * FROM nivel";
    final String GETONE = "SELECT * FROM nivel WHERE idNivel=?";

    public MysqlNivelDAO() {
        
        ///PRUEBAS
        //ejecutarPruebas();
    }
    
    private Nivel convertir(ResultSet resultSet) throws SQLException {
        
        int idNivel = resultSet.getInt("idNivel");
        String nombre = resultSet.getString("nombre");
        
        Nivel nivel = new Nivel(nombre);
 
        nivel.setIdNivel(idNivel);
        
        return nivel;
    }
    
    
    @Override
    public Nivel obtener(int id) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Nivel nivel = null;
        
        try{
            conexion = getConexion();
            statement = conexion.prepareStatement(GETONE);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nivel = convertir(resultSet);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
                resultSet.close();
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return nivel;
    }

    @Override
    public ArrayList<Nivel> obtenerTodos() {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        ArrayList<Nivel> niveles = new ArrayList<>();
       
        try{
            conexion = getConexion();
            statement = conexion.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                niveles.add(convertir(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
                resultSet.close();
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return niveles;
    }

    @Override
    public void eliminar(Nivel nivel) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try{
            conexion = getConexion();
            statement = conexion.prepareStatement(DELETE);
            statement.setInt(1, nivel.getIdNivel());
            if (statement.executeUpdate() == 0 ) {
                System.out.append("el registro no fué borrado porque no existe");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void modificar(Nivel nivel) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try{
            conexion = getConexion();
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, nivel.getNombre());
            statement.setInt(2, nivel.getIdNivel());
            if (statement.executeUpdate() == 0 ) {
                System.out.append("el registro no fué modificado");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void insertar(Nivel nivel) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try {
            conexion = getConexion();
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, nivel.getNombre());
            statement.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null)
            try {
                statement.close();
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

///PRUEBAS
    private void ejecutarPruebas() {
        System.out.println("Pruebas de MysqlNivelDAO");
        //pruebaInsertar();
        //pruebaObtener();
        //pruebaObtenerTodos();
        //pruebaEliminar();
        //pruebaModificar();
    }
   
    private void pruebaInsertar(){
        System.out.println("Prueba de Insertar Nivel");
        Nivel n = new Nivel("Nivel 1");
        this.insertar(n);
    }
    private void pruebaObtener(){
        System.out.println("Prueba de Obtener Nivel");
        Nivel n = this.obtener(1);
        System.out.println("    "+n);
    }
    private void pruebaObtenerTodos(){
        System.out.println("Prueba de Obtener Todos los niveles");
        ArrayList<Nivel> niveles = this.obtenerTodos();
        Iterator i = niveles.iterator();
        while(i.hasNext()){
            System.out.println("  "+i.next());
        }
    }
    private void pruebaEliminar(){
        System.out.println("Prueba de Eliminar nivel");
        Nivel n = this.obtener(4);
        this.eliminar(n);
        this.eliminar(n);
    }
    private void pruebaModificar(){
        System.out.println("Prueba de Modificar nivel");
        Nivel n = this.obtener(1);
        n.setNombre(n.getNombre()+" MODIFICADO");
        this.modificar(n);
        System.out.println(n);
    }
}
