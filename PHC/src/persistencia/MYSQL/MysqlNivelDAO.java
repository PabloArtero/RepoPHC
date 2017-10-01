/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.MYSQL;

import dominio.Nivel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.NivelDAO;

/**
 *
 * @author josdan
 */
public class MysqlNivelDAO implements NivelDAO{
    
    final String INSERT = "INSERT INTO nivel(idNivel, nombre) VALUES (?,?)";
    final String UPDATE = "";
    final String DELETE = "";
    final String GETALL = "";
    final String GETONE = "SELECT * FROM nivel WHERE idNivel=?";
    
    private Connection conexion;

    public MysqlNivelDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Nivel convertir(ResultSet resultSet) throws SQLException {
        int idNivel = resultSet.getInt("idNivel");
        String nombre = resultSet.getString("nombre");
        
        Nivel nivel = new Nivel(nombre);
        
        return nivel;
    }
    
    
    @Override
    public Nivel obtener(int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Nivel nivel = null;
        
        try{
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
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return nivel;
    }

    @Override
    public List<Nivel> obtenerTodos() {
        return NivelDAO.super.obtenerTodos(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Nivel nivel) {
        NivelDAO.super.eliminar(nivel); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Nivel nivel) {
        NivelDAO.super.modificar(nivel); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(Nivel nivel) {

        PreparedStatement statement = null;
        
        try {
            
            statement = conexion.prepareStatement(INSERT);
            statement.setInt(1, nivel.getIdNivel());
            statement.setString(2, nivel.getNombre());
            
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null)
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        
    }
    
    
    
}
