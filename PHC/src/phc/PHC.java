/*
 * Sistema de Gestión del Capital Humano.
 */
/*modificación en la clase principal*/
package phc;

import javax.swing.JFrame;

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
        formularios.seguridad.FrmIniciarSesion frmIniciarSesion = new formularios.seguridad.FrmIniciarSesion();
        
        //pruebaFrmPrincipal();
        //pruebaAdministrarUsuarios();
        //pruebaCrearNuevoUsuario();
    }

///////////////////METODOS DE PRUEBA
    
    private static void pruebaFrmPrincipal(){
        VariablesDelSistema.idUsuarioActual = 1;
        VariablesDelSistema.usuarioEsAdministrador = true;
        formularios.FrmPrincipal frmPrincipal = new formularios.FrmPrincipal();
    }
    private static void pruebaAdministrarUsuarios(){
        VariablesDelSistema.idUsuarioActual = 1;
        VariablesDelSistema.usuarioEsAdministrador = true;
        formularios.FrmPrincipal frmPrincipal = new formularios.FrmPrincipal();
        formularios.seguridad.FrmAdministrarUsuarios au = new formularios.seguridad.FrmAdministrarUsuarios(frmPrincipal);
        au.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private static void pruebaCrearNuevoUsuario(){
        VariablesDelSistema.idUsuarioActual = 1;
        VariablesDelSistema.usuarioEsAdministrador = true;
        formularios.FrmPrincipal frmPrincipal = new formularios.FrmPrincipal();
        formularios.seguridad.FrmCrearNuevoUsuario cu = new formularios.seguridad.FrmCrearNuevoUsuario(frmPrincipal);
        cu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
