/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.MYSQL;

import dominio.Nivel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.NivelDAO;
import persistencia.ParametrosDeConexionBD;

/**
 *
 * @author josdan
 */
public class MysqlNivelDAO implements NivelDAO{
    
    final String INSERT = "INSERT INTO nivel(nombre) VALUES (?)";
    final String UPDATE = "UPDATE nivel SET nombre = ? WHERE idNivel = ?";
    final String DELETE = "DELETE FROM nivel WHERE idNivel = ?";
    final String GETALL = "SELECT * FROM nivel";
    final String GETONE = "SELECT * FROM nivel WHERE idNivel=?";
    
    private Connection conexion;

    public MysqlNivelDAO(Connection conexion) {
        this.conexion = conexion;
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
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Nivel> niveles = new ArrayList<>();
       
        try{
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
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return niveles;
    }

    @Override
    public void eliminar(Nivel nivel) {
        PreparedStatement statement = null;
        
        try{
            statement = conexion.prepareStatement(DELETE);
            statement.setInt(1, nivel.getIdNivel());
            if (statement.executeUpdate() == 0 ) {
                System.out.append("el alumno no a sido borrado porque no existe");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void modificar(Nivel nivel) {
        PreparedStatement statement = null;
        
        try{
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, nivel.getNombre());
            statement.setInt(2, nivel.getIdNivel());
            if (statement.executeUpdate() == 0 ) {
                System.out.append("el alumno no a sido modificado");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlNivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void insertar(Nivel nivel) {

        PreparedStatement statement = null;
        ResultSet res = null;
        
        try {
            
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, nivel.getNombre());
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
  
    
    public static void main(String args[]) throws SQLException {
        Connection con = DriverManager.getConnection(ParametrosDeConexionBD.URL, ParametrosDeConexionBD.USUARIO, ParametrosDeConexionBD.CONTRASEÑA);
        
        NivelDAO dao = new MysqlNivelDAO(con);
        
        Nivel nivel = new Nivel("nivel 1");
        nivel.setIdNivel(11);
        
        dao.modificar(nivel);
    }
    
}
