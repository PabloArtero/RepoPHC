/*
 * Sistema de Gestión del Capital Humano.
 */
package phc;

import controladores.ControladorAltaNivel;
import controladores.ControladorNivel;
import loginYGestionDeUsuarios.VariablesDelSistema;
import javax.swing.JFrame;
import persistencia.MYSQL.MysqlNivelDAO;
import vistas.VentanaNivel;
import vistas.VentanaPrincipal;

/**
 * Clase principal del sistema. En el método main de esta clase comienza la ejecución del sistema.
 * 
 * @author Usuario
 */
public class PHC {
/////////////////ATRIBUTOS

///////////////////METODOS
    
    /**
     * Método principal.
     * @param args los argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        //loginYGestionDeUsuarios.FrmIniciarSesion frmIniciarSesion = new loginYGestionDeUsuarios.FrmIniciarSesion();
        
        //pruebaFrmPrincipal();
        //pruebaAdministrarUsuarios();
        //pruebaCrearNuevoUsuario();
        
        //persistencia.MYSQL.MysqlNivelDAO mysqlNivelDAO = new MysqlNivelDAO();
        pruebaControladorNivel();
    }
  
///////////////////METODOS DE PRUEBA
    
    private static void pruebaControladorNivel(){
        VentanaPrincipal v = new VentanaPrincipal();
        v.setLocationRelativeTo(null);
        v.setVisible(true);
        ControladorNivel c = new ControladorNivel(v);
    }
    
    private static void pruebaFrmPrincipal(){
        VariablesDelSistema.idUsuarioActual = 1;
        VariablesDelSistema.usuarioEsAdministrador = true;
        loginYGestionDeUsuarios.FrmPrincipal frmPrincipal = new loginYGestionDeUsuarios.FrmPrincipal();
    }
    private static void pruebaAdministrarUsuarios(){
        VariablesDelSistema.idUsuarioActual = 1;
        VariablesDelSistema.usuarioEsAdministrador = true;
        loginYGestionDeUsuarios.FrmPrincipal frmPrincipal = new loginYGestionDeUsuarios.FrmPrincipal();
        loginYGestionDeUsuarios.FrmAdministrarUsuarios au = new loginYGestionDeUsuarios.FrmAdministrarUsuarios(frmPrincipal);
        au.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private static void pruebaCrearNuevoUsuario(){
        VariablesDelSistema.idUsuarioActual = 1;
        VariablesDelSistema.usuarioEsAdministrador = true;
        loginYGestionDeUsuarios.FrmPrincipal frmPrincipal = new loginYGestionDeUsuarios.FrmPrincipal();
        loginYGestionDeUsuarios.FrmCrearNuevoUsuario cu = new loginYGestionDeUsuarios.FrmCrearNuevoUsuario(frmPrincipal);
        cu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
