package formularios.seguridad;

import phc.VariablesDelSistema;
import phc.ParámetrosDeConexiónBD;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Le permite a los usarios del sitema iniciar sesión.
 * 
 * @author Usuario
 */
public class FrmIniciarSesion extends javax.swing.JFrame {
/////////////////ATRIBUTOS

///////////////////METODOS
    /**
     * Creates new form IniciarSesion
     * @param bd
     */
    public FrmIniciarSesion() {
        this.initComponents();//inicialización de componenetes por el editor de formularios de NetBeans       
        this.setLocationRelativeTo(null);//Para centrarlo en el escritorio
        this.setVisible(true);//Hacemos visible
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar sesión");

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(txtNombreUsuario))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        /*Declaro los Connection, los Statement y los ResusltSet que necesitaré y creo una conexión
        que cierro al final del método luego de trabajar con la base de datos*/
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(ParámetrosDeConexiónBD.DRIVER_JDBC); //Iniciamos el driver encargado de conectar con el sevidor.
            con = DriverManager.getConnection(ParámetrosDeConexiónBD.URL,
                    ParámetrosDeConexiónBD.USUARIO, ParámetrosDeConexiónBD.CONTRASEÑA);
            //Devuelve una conexión abierta a la base de datos. Es como loguearse a MySQL y luego ejecutar USE nombreBD;
            //Se pueden establecer tantas conexiones como se quiera pero lo habitual es crear una sola, hacer algo con la
            //BD y luego cerrarla para liberar recursos.
            System.out.println("Se estableció la conexión con el servidor de datos");
            String sql = "SELECT * FROM usuario WHERE nombreUsuario=? AND contraseña=MD5(?)";
            ps = con.prepareStatement(sql);//Creo un PreparedStatement para ejecutar sentencias en el DBMS.
            /*Puedo instanciar de esta manera todos los Stantemt o PreparedStatement que necesite.
            En general se crea uno por cada Resultset que vaya a necesitar de manera simultanea*/
            ps.setString(1, txtNombreUsuario.getText());//Le paso los parámetros al ps
            ps.setString(2, txtContraseña.getText());
            rs = ps.executeQuery();//Ejecuto la sentencia SQL preparada (pre compilada en la base de datos).
            //Importante: No ejecutar ps.executeQuery(sql) ni ps.executeUpdate(sql) ya que ejecutará la cadena sql original
            //sin los parámetros y el DBMS datá un error de sintaxis cerca de '?' en la sentencia ejecutada.
            //ps.executeQuery() nos permite realizar consultas (SELECT o sea DQL) devolviendo los resultados en un ResultSet.
            //ps.executeUpdate nos permite realizar modificaciones (INSERT, UPDATE, DELETE, o sea DML) devolviendo el número de registros afectados.
            //si ejecutamos otro sql con el mismo PreparedStatement, perderemos la referencia al ResultSet instanciado por 
            //ps.execute***(). Por eso es que si queremos trabajar con más de un resultset a la vez, hay que instanciar
            //un Statement por cada Resultset. Se podría tener entonces un stUsuario para instanciar un rsUsuario,
            //un stEmpleado para instanciar un rsEmpleado, etc (igual con los ps).
            if(rs.next() == true && rs.getBoolean("habilitado")){//si identificamos al usuario...
                //y además está habilitado para usar el sistema... Nota: no permitir que haya dos usuarios con el mismo nómbre de usuario. Una solucion podría ser concatenar la palabra 'usuario' con su idEmpleado.
                VariablesDelSistema.idUsuarioActual = rs.getInt("idEmpleado");
                System.out.println("Usuario identificado con el id: "+ rs.getInt("idEmpleado"));
                if(rs.getBoolean("esAdministrador")){//si el usario es administrador...
                    VariablesDelSistema.usuarioEsAdministrador = true;
                    System.out.println("El usuario es Administrador");
                }
                formularios.FrmPrincipal frmPrincipal = new formularios.FrmPrincipal();//abro la ventana principal.
                if(rs.getBoolean("contraseñaRestaurada")){//si su contraseña fué restaurada...
                    VariablesDelSistema.contraseñaRestaurada = true;
                    FrmAdministrarMiCuenta ac = new FrmAdministrarMiCuenta(frmPrincipal);//abro también la ventana para administrar mi cuenta.
                    //le paso por parámetro el padre para simular una ventana modal. En java los JFrame no pueden ser 
                    //modales, solo los cuadros de diálogo implementados con JOptionPane.
                }
                this.dispose();//Cierra el jFrame liberando sus recursos.
            } else {//si no se identificó al usuario o no está habilitado para usar el sistema.
                rs.beforeFirst();
                if(rs.next() && !rs.getBoolean("habilitado")){//Si lo identificamos pero no está habilitado...
                    JOptionPane.showMessageDialog(
                        this, //el JFrame en el cuál se mostrará el mensaje
                        "Su cuenta de usuario fué deshabilitada. Consulte con el administrador",//mensaje 
                        "Cuenta deshabilitada", //título del Dialog
                        JOptionPane.ERROR_MESSAGE //tipo de mensaje.
                    );
                } else {//Si no lo identificamos...
                    JOptionPane.showMessageDialog(
                        this, //el JFrame en el cuál se mostrará el mensaje
                        "Ingrese un nombre de usuario y contraseña válidos",//mensaje 
                        "Usuario no identificado", //título del Dialog
                        JOptionPane.ERROR_MESSAGE //tipo de mensaje.
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {//Si Class.forName() no encontró el driver
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se encontró el driver "+ ParámetrosDeConexiónBD.DRIVER_JDBC);
        } finally{//libero recursos
            try{
                if(rs != null){rs.close(); System.out.println("ResultSets cerrados");}//liberamos los ResultSet
                if(ps != null){ps.close(); System.out.println("Statements cerrados");}//liberamos los PreparedStatement
                if(con != null){con.close(); System.out.println("Connections cerrados");}//liberamos las conexiónes
            } catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Algunos recursos de la conexión con el DBMS no pudieron ser liberados");
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmIniciarSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
