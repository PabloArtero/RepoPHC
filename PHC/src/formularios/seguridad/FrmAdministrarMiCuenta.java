package formularios.seguridad;

import phc.ParámetrosDeConexiónBD;
import phc.VariablesDelSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * JFrame para que los usuarios puedan administrar su cuenta.
 * Padrían cambiar su nombre de usuario (siempre y cuando no exista otro usuario con el mismo nombre de usuario)
 * y cambiar su contraseña.
 * @author Usuario
 */
public class FrmAdministrarMiCuenta extends javax.swing.JFrame {
/////////////////ATRIBUTOS
    private JFrame padre;
///////////////////METODOS
    /**
     * Creates new form AdministrarMiCuenta
     * @param padre es el JFrame habierto que hace de padre de éste JFrame, que sería deshabilitado y rehabilitado al cerrar esta ventana para simular que es modal.
     */
    public FrmAdministrarMiCuenta(JFrame padre) {
        initComponents();//inicialización de componenetes por el editor de formularios de NetBeans       
        this.setLocationRelativeTo(null);//Para centrarlo en el escritorio
        this.setVisible(true);//Hacemos visible
        
        padre.setEnabled(false);//para simular una ventana modal el padre (la ventana principal) se deshabilitará
        this.padre = padre;//lo rehabilito en el evento WindowClosed.
        
        if(VariablesDelSistema.contraseñaRestaurada){//si su contraseña fué restaurada...
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*si quiere cerrar la ventana con el aspa (x)
            saldrá de la aplicación, ya que está obligado a cambiar sus credenciales para continuar*/
        } else {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);/*al cerrarse la ventana con el aspa (x)
            no cerrará la aplicación ya que la ventana principal permanece abierta*/
        }
        ///////////CARGO EL NOMBRE DE USUARIO ACTUAL EN EL JTextBox///////////
        
        /*Declaro los Connection, los Statement y los ResusltSet que necesitaré y creo una conexión
        que cierro al final del método luego de trabajar con la base de datos*/
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(ParámetrosDeConexiónBD.DRIVER_JDBC); //Iniciamos el driver encargado de conectar con el servidor.
            con = DriverManager.getConnection(ParámetrosDeConexiónBD.URL,
                    ParámetrosDeConexiónBD.USUARIO, ParámetrosDeConexiónBD.CONTRASEÑA);
            //Devuelve una conexión abierta a la base de datos. Es como loguearse a MySQL y luego ejecutar USE nombreBD;
            //Se pueden establecer tantas conexiones como se quiera pero lo habitual es crear una sola, hacer algo con la
            //BD y luego cerrarla para liberar recursos.
            System.out.println("Se estableció la conexión con el servidor de datos");
            String sql = "SELECT * FROM usuario WHERE idEmpleado=?";
            ps = con.prepareStatement(sql);//Creo un PrepareStatement para ejecutar sentencias en el DBMS.
            /*Puedo instanciar de esta manera todos los Stantemt o PreparedStatement que necesite.
            En general se crea uno por cada Resultset que vaya a necesitar de manera simultanea*/
            ps.setInt(1, VariablesDelSistema.idUsuarioActual);//Le paso el parámetro al ps
            rs = ps.executeQuery();//Ejecuto la sentencia SQL preparada (pre compilada en la base de datos).
            //ps.executeQuery(sql) nos permite realizar consultas (SELECT o sea DQL) devolviendo los resultados en un ResultSet.
            //ps.executeUpdate nos permite realizar modificaciones (INSERT, UPDATE, DELETE, o sea DML) devolviendo el número de registros afectados.
            //si ejecutamos otro sql con el mismo PreparedStatement, perderemos la referencia al ResultSet instanciado por 
            //ps.execute***(sql). Por eso es que si queremos trabajar con más de un resultset a la vez, hay que instanciar
            //un Statement por cada Resultset. Se podría tener entonces un stUsuario para instanciar un rsUsuario,
            //un stEmpleado para instanciar un rsEmpleado, etc (igual con los ps).
            rs.next();//pone el cursor en el registro del usuario.
            txtNombreDeUsuario.setText(rs.getString("nombreUsuario"));//carga el txt con el nombre del usuario actual
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
    }
    public FrmAdministrarMiCuenta(){
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreDeUsuario = new javax.swing.JTextField();
        txtContraseñaActual = new javax.swing.JPasswordField();
        txtNuevaContraseña = new javax.swing.JPasswordField();
        txtConfirmarContraseña = new javax.swing.JPasswordField();
        btnGuardarCambios = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrar mi cuenta");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Nombre de usuario");

        jLabel2.setText("Contraseña actual");

        jLabel3.setText("Nueva contraseña");

        jLabel4.setText("Confirmar contraseña");

        btnGuardarCambios.setText("Guardar cambios");
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtConfirmarContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addGap(90, 90, 90)
                        .addComponent(btnVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtContraseñaActual, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreDeUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNuevaContraseña, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardarCambios)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreDeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarCambios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtContraseñaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNuevaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnVolver)
                    .addComponent(txtConfirmarContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        /* Guardo los nuevos nombre de usuario y contraseña introducidos por el usuario*/
        
        /*Declaro los Connection, los Statement y los ResusltSet que necesitaré y creo una conexión
        que cierro al final del método luego de trabajar con la base de datos*/
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        ResultSet rs3 = null;
        try {
            Class.forName(ParámetrosDeConexiónBD.DRIVER_JDBC); //Iniciamos el driver encargado de conectar con el sevidor.
            con = DriverManager.getConnection(ParámetrosDeConexiónBD.URL,
                    ParámetrosDeConexiónBD.USUARIO, ParámetrosDeConexiónBD.CONTRASEÑA);
            //Devuelve una conexión abierta a la base de datos. Es como loguearse a MySQL y luego ejecutar USE nombreBD;
            //Se pueden establecer tantas conexiones como se quiera pero lo habitual es crear una sola, hacer algo con la
            //BD y luego cerrarla para liberar recursos.
            System.out.println("Se estableció la conexión con el servidor de datos");
            String sql = "SELECT * FROM usuario WHERE idEmpleado=?";
            ps = con.prepareStatement(sql);//Creo un PreparedStatement para ejecutar sentencias en el DBMS.
            /*Puedo instanciar de esta manera todos los Stantemt o PreparedStatement que necesite.
            En general se crea uno por cada Resultset que vaya a necesitar de manera simultanea*/
            ps.setInt(1, VariablesDelSistema.idUsuarioActual);//Le paso el parámetro al ps
            rs = ps.executeQuery();//Ejecuto la sentencia SQL preparada (pre compilada en la base de datos).
            //ps.executeQuery() nos permite realizar consultas (SELECT o sea DQL) devolviendo los resultados en un ResultSet.
            //ps.executeUpdate() nos permite realizar modificaciones (INSERT, UPDATE, DELETE, o sea DML) devolviendo el número de registros afectados.
            //si ejecutamos otro sql con el mismo PreparedStatement, perderemos la referencia al ResultSet instanciado por 
            //ps.execute***(). Por eso es que si queremos trabajar con más de un resultset a la vez, hay que instanciar
            //un Statement por cada Resultset. Se podría tener entonces un stUsuario para instanciar un rsUsuario,
            //un stEmpleado para instanciar un rsEmpleado, etc (igual con los ps).
            rs.next();//pone el cursor en el registro del usuario.
            /*No se podrán guardar los cambios realizados por el usuario a sus credenciales en minguno de 
            estos casos:
            1- La contraseña actual ingresada no coicide con la contraseña en la tabla usuario
            2- Su contraseña fué restaurada y la nueva contraseña es igual a la actual.
            3- El campo para confirmar contraseña no coincide con el de nueva contraseña
            4- La nueva contraseña tiene una longitud menor a 6 caracteres*/
            Boolean seDióUnCaso = false;
            
            sql = "select * from usuario WHERE idEmpleado=? AND contraseña=MD5(?)";
            //quiero saber si la contraseña ingresada coincide con la actual del usuario (rs.next()==true)
            ps3 = con.prepareStatement(sql);
            ps3.setInt(1, VariablesDelSistema.idUsuarioActual);
            ps3.setString(2, txtContraseñaActual.getText());
            rs3 = ps3.executeQuery();
            if(!rs3.next()){
                //caso 1: La contraseña actual ingresada no coicide con la contraseña en la tabla usuario
                seDióUnCaso = true;
                JOptionPane.showMessageDialog(
                        this, //el JFrame en el cuál se mostrará el mensaje
                        "La contraseña actual no es correcta",//mensaje 
                        "Error al cambiar credenciales", //título del Dialog
                        JOptionPane.ERROR_MESSAGE //tipo de mensaje.
                );
            }
            //Ahora quiero saber, con el mismo PreparedStatement si su nueva contraseña es igual a la anterior (rs.next()==true)
            ps3.setInt(1, VariablesDelSistema.idUsuarioActual);
            ps3.setString(2, txtNuevaContraseña.getText());
            rs3 = ps3.executeQuery();
            if(rs.getBoolean("contraseñaRestaurada") && rs3.next()){
                //caso 2: Su contraseña fué restaurada y no la cambió por otra
                seDióUnCaso = true;
                JOptionPane.showMessageDialog(
                        this, //el JFrame en el cuál se mostrará el mensaje
                        "Debe cambiar su contraseña",//mensaje 
                        "Error al cambiar credenciales", //título del Dialog
                        JOptionPane.ERROR_MESSAGE //tipo de mensaje.
                );
            }
            if(!txtConfirmarContraseña.getText().equals(txtNuevaContraseña.getText())){
                //caso 3: El campo para confirmar contraseña no coincide con el de nueva contraseña
                seDióUnCaso = true;
                JOptionPane.showMessageDialog(
                        this, //el JFrame en el cuál se mostrará el mensaje
                        "El campo para confirmar contraseña no coincide con el de nueva contraseña",//mensaje 
                        "Error al cambiar credenciales", //título del Dialog
                        JOptionPane.ERROR_MESSAGE //tipo de mensaje.
                );
            }
            if(txtNuevaContraseña.getText().length() < 6){
                //caso 4: La nueva contraseña tiene una longitud menor a 6 caracteres
                seDióUnCaso = true;
                JOptionPane.showMessageDialog(
                        this, //el JFrame en el cuál se mostrará el mensaje
                        "La nueva contraseña no puede tener una longitud menor a 6 caracteres",//mensaje 
                        "Error al cambiar credenciales", //título del Dialog
                        JOptionPane.ERROR_MESSAGE //tipo de mensaje.
                );
            }
            if(!seDióUnCaso){//si no se dió ninguno de los casos anteriores se pueden guardar los cambios
                VariablesDelSistema.contraseñaRestaurada = false;//por si su contraseña había sido restaurada...
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//y permito que se cierre la ventana...
                sql = "UPDATE usuario SET nombreUsuario=?, contraseña=MD5(?), contraseñaRestaurada=b'0' WHERE  idEmpleado=?";
                //pongo a 0 el bit de contraseñaRestaurada
                
                ps2 = con.prepareStatement(sql);
                ps2.setString(1, txtNombreDeUsuario.getText());//le paso los parámetros al PreparedStatement
                ps2.setString(2, txtNuevaContraseña.getText());
                ps2.setInt(3, VariablesDelSistema.idUsuarioActual);
                ps2.executeUpdate();
                this.dispose();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {//Si Class.forName() no encontró el driver
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se encontró el driver "+ ParámetrosDeConexiónBD.DRIVER_JDBC);
        } finally{//libero recursos
            try{
                if(rs != null){rs.close(); System.out.println("ResultSet cerrado");}//liberamos los ResultSet
                if(rs3 != null){rs3.close(); System.out.println("ResultSet cerrado");}//liberamos los ResultSet
                if(ps != null){ps.close(); System.out.println("PrepareStatement cerrado");}//liberamos los PrepareStatement
                if(ps2 != null){ps2.close(); System.out.println("PrepareStatement cerrado");}//liberamos los PrepareStatement
                if(ps3 != null){ps3.close(); System.out.println("PrepareStatement cerrado");}//liberamos los PrepareStatement
                if(con != null){con.close(); System.out.println("Connection cerrado");}//liberamos las conexiónes
            } catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Algunos recursos de la conexión con el DBMS no pudieron ser liberados");
            }
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        padre.setEnabled(true);
    }//GEN-LAST:event_formWindowClosed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        if(VariablesDelSistema.contraseñaRestaurada){//si su contraseña fué restaurada está obligado a cambiarla.
            JOptionPane.showMessageDialog(
                        this, //el JFrame en el cuál se mostrará el mensaje
                        "Como su contraseña fué restaurada por el administrador, debe cambiarla obligatoriamente",//mensaje 
                        "No cambió su contraseña", //título del Dialog
                        JOptionPane.ERROR_MESSAGE //tipo de mensaje.
                );
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdministrarMiCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarMiCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarMiCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarMiCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdministrarMiCuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtConfirmarContraseña;
    private javax.swing.JPasswordField txtContraseñaActual;
    private javax.swing.JTextField txtNombreDeUsuario;
    private javax.swing.JPasswordField txtNuevaContraseña;
    // End of variables declaration//GEN-END:variables
}
