package formularios.seguridad;

import formularios.*;
import phc.ParametrosDeConexionBD;
import phc.VariablesDelSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * JFrame que le permite a un administrador del sistema administrar los usuarios.
 * El administrador podrá decidir, por cada usuario (exepto él mismo) si está habilitado para
 * usar el sistema, si es otro administrador o si se restaurará su contraseña.
 * 
 * @author Usuario
 */
public class FrmAdministrarUsuarios extends javax.swing.JFrame {
///////////////////ATRIBUTOS
    private JFrame padre; //para simular una ventana modal
///////////////////METODOS

    /**
     * Creates new form AdministrarUsuarios
     */
    public FrmAdministrarUsuarios(JFrame padre) {
        initComponents();//inicialización de componenetes por el editor de formularios de NetBeans       
        this.setLocationRelativeTo(null);//Para centrarlo en el escritorio
        this.setVisible(true);
        
        padre.setEnabled(false);//para simular una ventana modal el padre (la ventana principal) se deshabilitará
        this.padre = padre;//lo rehabilito en el evento WindowClosed.
        
        /*Declaro los Connection, los Statement y los ResusltSet que necesitaré y creo una conexión
        que cierro al final del método luego de trabajar con la base de datos*/
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName(ParametrosDeConexionBD.DRIVER_JDBC); //Iniciamos el driver encargado de conectar con el sevidor.
            con = DriverManager.getConnection(ParametrosDeConexionBD.URL,
                    ParametrosDeConexionBD.USUARIO, ParametrosDeConexionBD.CONTRASEÑA);
            //Devuelve una conexión abierta a la base de datos. Es como loguearse a MySQL y luego ejecutar USE nombreBD;
            //Se pueden establecer tantas conexiones como se quiera pero lo habitual es crear una sola, hacer algo con la
            //BD y luego cerrarla para liberar recursos.
            System.out.println("Se estableció la conexión con el servidor de datos en FrmAdministrarUsuarios(JFrame padre)");
            String sql = "SELECT * FROM usuario, empleado WHERE usuario.idEmpleado=empleado.idEmpleado and usuario.idEmpleado<>"+ VariablesDelSistema.idUsuarioActual;
            /*quiero una lista de todos los empleados usuarios, exepto el actual (un administrador del sistema) de esta forma evito
            que el propio usuario administrador del sistema se inhabilite o se quite la propiedad de administrador*/
            st = con.createStatement();//Creo un Statement para ejecutar sentencias en el DBMS.
            rs = st.executeQuery(sql);

            while(rs.next()){//cargo el JComboBox con los usuarios que se podrán administrar
                ItemDeCombo item = new ItemDeCombo();
                item.setId(rs.getInt("idEmpleado"));//cargo el id
                item.setCadena(rs.getString("idEmpleado") +" - "+ rs.getString("apellido") +", "+ rs.getString("nombre"));
                //cargo en la cadena el id + el apellido, nombre
                cmbUsuario.addItem(item);//agrego el ítem al JComboBox                
            }
            if(cmbUsuario.getSelectedIndex()== -1){//si el combo está vacío
                JOptionPane.showMessageDialog(//Si no lo identificamos...
                        this, //el JFrame padre de este Dialog modal
                        "Usted es el único usuario del sistema",//mensaje 
                        "No hay usuarios para administrar", //título del Dialog
                        JOptionPane.ERROR_MESSAGE //tipo de mensaje.
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {//Si Class.forName() no encontró el driver
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se encontró el driver "+ ParametrosDeConexionBD.DRIVER_JDBC);
        } finally{//libero recursos
            try{
                if(rs != null){rs.close(); System.out.println("ResultSets cerrados en FrmAdministrarUsuarios(JFrame padre)");}//liberamos los ResultSet
                if(st != null){st.close(); System.out.println("Statements cerrados en FrmAdministrarUsuarios(JFrame padre)");}//liberamos los Statement
                if(con != null){con.close(); System.out.println("Connections cerrados en FrmAdministrarUsuarios(JFrame padre)");}//liberamos las conexiónes
            } catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Algunos recursos de la conexión con el DBMS no pudieron ser liberados");
            }
        }
    }
    public FrmAdministrarUsuarios() {
        initComponents();//inicialización de componenetes por el editor de formularios de NetBeans       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox();
        chkHabilitado = new javax.swing.JCheckBox();
        chkAdministrador = new javax.swing.JCheckBox();
        chkContraseñaRestaurada = new javax.swing.JCheckBox();
        btnGuardarCambios = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnRestaurarContraseña = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar usuarios");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Usuario");

        cmbUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuarioActionPerformed(evt);
            }
        });

        chkHabilitado.setText("Habilitado");

        chkAdministrador.setText("Administrador");

        chkContraseñaRestaurada.setText("Contraseña restaurada");
        chkContraseñaRestaurada.setEnabled(false);

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

        btnRestaurarContraseña.setText("Restaurar contraseña");
        btnRestaurarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarContraseñaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUsuario, 0, 223, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkHabilitado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestaurarContraseña))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkContraseñaRestaurada)
                        .addGap(26, 26, 26)
                        .addComponent(btnVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkAdministrador)
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
                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkHabilitado)
                    .addComponent(btnRestaurarContraseña))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAdministrador)
                    .addComponent(btnGuardarCambios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkContraseñaRestaurada)
                    .addComponent(btnVolver))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        padre.setEnabled(true);
    }//GEN-LAST:event_formWindowClosed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void cmbUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuarioActionPerformed
        /*Importante: Además de dispararse cuando el usuario selecciona un ítem del combo, este evento también 
        se dispara cuando se crea o se muestra por primera vez el objeto, ya que, por defecto, tiene un ítem seleccionado*/
        
        /*Declaro los Connection, los Statement y los ResusltSet que necesitaré y creo una conexión
        que cierro al final del método luego de trabajar con la base de datos*/
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName(ParametrosDeConexionBD.DRIVER_JDBC); //Iniciamos el driver encargado de conectar con el servidor.
            con = DriverManager.getConnection(ParametrosDeConexionBD.URL,
                    ParametrosDeConexionBD.USUARIO, ParametrosDeConexionBD.CONTRASEÑA);
            //Devuelve una conexión abierta a la base de datos. Es como loguearse a MySQL y luego ejecutar USE nombreBD;
            System.out.println("Se estableció la conexión con el servidor de datos en cmbUsuarioActionPerformed(java.awt.event.ActionEvent evt)");

            String sql = "SELECT * FROM usuario WHERE idEmpleado="+ ((ItemDeCombo)cmbUsuario.getSelectedItem()).getId();
            //quiero solo los datos del usuario seleccionado (obtendré un rs de una fila)
            st = con.createStatement();//Creo un Statement para ejecutar sentencias en el DBMS.
            rs = st.executeQuery(sql);
            rs.next();//posiciono el cursor en el primer y único registro
            //Cargo los Chek con los datos del usuario seleccionado:
            chkHabilitado.setSelected(rs.getBoolean("habilitado"));
            chkAdministrador.setSelected(rs.getBoolean("esAdministrador"));
            chkContraseñaRestaurada.setSelected(rs.getBoolean("contraseñaRestaurada"));
        } catch (SQLException ex) {
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {//Si Class.forName() no encontró el driver
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se encontró el driver "+ ParametrosDeConexionBD.DRIVER_JDBC);
        } finally{//libero recursos
            try{
                if(rs != null){rs.close(); System.out.println("ResultSets cerrados");}//liberamos los ResultSet
                if(st != null){st.close(); System.out.println("Statements cerrados");}//liberamos los Statement
                if(con != null){con.close(); System.out.println("Connections cerrados");}//liberamos las conexiónes
            } catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Algunos recursos de la conexión con el DBMS no pudieron ser liberados");
            }
        }
    }//GEN-LAST:event_cmbUsuarioActionPerformed

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        /*Declaro los Connection, los Statement y los ResusltSet que necesitaré y creo una conexión
        que cierro al final del método luego de trabajar con la base de datos*/
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(ParametrosDeConexionBD.DRIVER_JDBC); //Iniciamos el driver encargado de conectar con el servidor.
            con = DriverManager.getConnection(ParametrosDeConexionBD.URL,
                    ParametrosDeConexionBD.USUARIO, ParametrosDeConexionBD.CONTRASEÑA);
            //Devuelve una conexión abierta a la base de datos. Es como loguearse a MySQL y luego ejecutar USE nombreBD;
            System.out.println("Se estableció la conexión con el servidor de datos");
            
            if(cmbUsuario.getSelectedIndex()> -1){//si el combo tiene al menos un ítem...
                String sql = "UPDATE usuario SET esAdministrador=?, habilitado=? WHERE  idEmpleado=?";
                //quiero guardar los cambios para el usuario seleccionado.
                ps = con.prepareStatement(sql);//Creo un PreparedStatement para ejecutar sentencias en el DBMS.
                ps.setBoolean(1, chkAdministrador.isSelected());
                ps.setBoolean(2, chkHabilitado.isSelected());
                ps.setInt(3, ((ItemDeCombo)cmbUsuario.getSelectedItem()).getId());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(
                        this, //el JFrame padre de este Dialog modal
                        "Se guardaron los atributos del usuario "+ cmbUsuario.getSelectedItem(),//mensaje 
                        "Atributos del usuario guardados", //título del Dialog
                        JOptionPane.INFORMATION_MESSAGE //tipo de mensaje.
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {//Si Class.forName() no encontró el driver
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se encontró el driver "+ ParametrosDeConexionBD.DRIVER_JDBC);
        } finally{//libero recursos
            try{
                if(rs != null){rs.close(); System.out.println("ResultSets cerrados");}//liberamos los ResultSet
                if(ps != null){ps.close(); System.out.println("Statements cerrados");}//liberamos los Statement
                if(con != null){con.close(); System.out.println("Connections cerrados");}//liberamos las conexiónes
            } catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Algunos recursos de la conexión con el DBMS no pudieron ser liberados");
            }
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnRestaurarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarContraseñaActionPerformed
        /*Declaro los Connection, los Statement y los ResusltSet que necesitaré y creo una conexión
        que cierro al final del método luego de trabajar con la base de datos*/
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(ParametrosDeConexionBD.DRIVER_JDBC); //Iniciamos el driver encargado de conectar con el servidor.
            con = DriverManager.getConnection(ParametrosDeConexionBD.URL,
                    ParametrosDeConexionBD.USUARIO, ParametrosDeConexionBD.CONTRASEÑA);
            //Devuelve una conexión abierta a la base de datos. Es como loguearse a MySQL y luego ejecutar USE nombreBD;
            System.out.println("Se estableció la conexión con el servidor de datos");
            
            if(cmbUsuario.getSelectedIndex()> -1){//si el combo tiene al menos un ítem...
                String sql = "SELECT * FROM usuario, empleado WHERE usuario.idEmpleado=empleado.idEmpleado AND usuario.idEmpleado="+ ((ItemDeCombo)cmbUsuario.getSelectedItem()).getId();
                //quiero los datos del empleado (que también es usuario) seleccionado.
                st = con.createStatement();
                rs = st.executeQuery(sql);
                rs.next();
                sql = "UPDATE usuario SET nombreUsuario=?, contraseña=MD5(?), contraseñaRestaurada=b'1' WHERE  idEmpleado=?";
                //quiero guardar los cambios para el usuario seleccionado.
                ps = con.prepareStatement(sql);//Creo un PreparedStatement para ejecutar sentencias en el DBMS.
                ps.setString(1, "usuario"+ rs.getInt("idEmpleado"));//usuario2, donde el 2 es su id.
                ps.setString(2, rs.getString("dni"));//su nueva contraseña será su dni
                ps.setInt(3, ((ItemDeCombo)cmbUsuario.getSelectedItem()).getId());//los cambios se aplicarán al usuario seleccionado en el combo
                ps.executeUpdate();
                this.cmbUsuarioActionPerformed(evt);//comvoco al evento para que se actualicen los checkBox.
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {//Si Class.forName() no encontró el driver
            Logger.getLogger(FrmIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se encontró el driver "+ ParametrosDeConexionBD.DRIVER_JDBC);
        } finally{//libero recursos
            try{
                if(rs != null){rs.close(); System.out.println("ResultSets cerrados");}//liberamos los ResultSet
                if(st != null){st.close(); System.out.println("Statements cerrados");}//liberamos los Statement
                if(ps != null){ps.close(); System.out.println("PreparedStatements cerrados");}//liberamos los PreparedStatement
                if(con != null){con.close(); System.out.println("Connections cerrados");}//liberamos las conexiónes
            } catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Algunos recursos de la conexión con el DBMS no pudieron ser liberados");
            }
        }
    }//GEN-LAST:event_btnRestaurarContraseñaActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdministrarUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnRestaurarContraseña;
    private javax.swing.JButton btnVolver;
    private javax.swing.JCheckBox chkAdministrador;
    private javax.swing.JCheckBox chkContraseñaRestaurada;
    private javax.swing.JCheckBox chkHabilitado;
    private javax.swing.JComboBox cmbUsuario;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
