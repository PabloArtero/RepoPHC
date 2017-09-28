package formularios;

import phc.VariablesDelSistema;

/**
 * Vantana principal que da acceso a todas las funciones del sistema.
 * @author Artero, Pablo Federico. Leg: 26345
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public FrmPrincipal() {
        initComponents();//inicialización de componenetes por el editor de ventanas de NetBeans       
        this.setLocationRelativeTo(null);//Para centrarlo en el escritorio
        this.setVisible(true);//Hacemos visible
        if(VariablesDelSistema.usuarioEsAdministrador){/*si el usuario actual es administrador habilito el acceso
            a las funciones del sistema que son solo para el administrador*/
            this.mnItemAdministrarUsuarios.setEnabled(true);//puede administrar usuarios
            this.mnItemCrearNuevoUsuario.setEnabled(true);//puede crear nuevos usuarios
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalirDelSistema = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnUsuarios = new javax.swing.JMenu();
        mnItemCrearNuevoUsuario = new javax.swing.JMenuItem();
        mnItemAdministrarUsuarios = new javax.swing.JMenuItem();
        mnItemAdministrarMiCuenta = new javax.swing.JMenuItem();
        mnAyuda = new javax.swing.JMenu();
        mnItemManualDeUsuario = new javax.swing.JMenuItem();
        mnItemAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestión de RRHH");

        btnSalirDelSistema.setText("Salir del sistema");
        btnSalirDelSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirDelSistemaActionPerformed(evt);
            }
        });

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("jMenuItem2");
        jMenu1.add(jMenuItem2);

        barraMenu.add(jMenu1);

        mnUsuarios.setText("Usuarios");

        mnItemCrearNuevoUsuario.setText("Nuevo");
        mnItemCrearNuevoUsuario.setEnabled(false);
        mnItemCrearNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItemCrearNuevoUsuarioActionPerformed(evt);
            }
        });
        mnUsuarios.add(mnItemCrearNuevoUsuario);

        mnItemAdministrarUsuarios.setText("Administrar");
        mnItemAdministrarUsuarios.setEnabled(false);
        mnItemAdministrarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItemAdministrarUsuariosActionPerformed(evt);
            }
        });
        mnUsuarios.add(mnItemAdministrarUsuarios);

        mnItemAdministrarMiCuenta.setText("Mi cuenta");
        mnItemAdministrarMiCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItemAdministrarMiCuentaActionPerformed(evt);
            }
        });
        mnUsuarios.add(mnItemAdministrarMiCuenta);

        barraMenu.add(mnUsuarios);

        mnAyuda.setText("Ayuda");

        mnItemManualDeUsuario.setText("Manual de usuario");
        mnAyuda.add(mnItemManualDeUsuario);

        mnItemAcercaDe.setText("Acerca de");
        mnAyuda.add(mnItemAcercaDe);

        barraMenu.add(mnAyuda);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(407, Short.MAX_VALUE)
                .addComponent(btnSalirDelSistema)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalirDelSistema)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnItemAdministrarMiCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItemAdministrarMiCuentaActionPerformed
        formularios.seguridad.FrmAdministrarMiCuenta ac = new formularios.seguridad.FrmAdministrarMiCuenta(this);
    }//GEN-LAST:event_mnItemAdministrarMiCuentaActionPerformed

    private void btnSalirDelSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirDelSistemaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirDelSistemaActionPerformed

    private void mnItemAdministrarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItemAdministrarUsuariosActionPerformed
        formularios.seguridad.FrmAdministrarUsuarios au = new formularios.seguridad.FrmAdministrarUsuarios(this);
    }//GEN-LAST:event_mnItemAdministrarUsuariosActionPerformed

    private void mnItemCrearNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItemCrearNuevoUsuarioActionPerformed
        formularios.seguridad.FrmCrearNuevoUsuario cu = new formularios.seguridad.FrmCrearNuevoUsuario(this);
    }//GEN-LAST:event_mnItemCrearNuevoUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton btnSalirDelSistema;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu mnAyuda;
    private javax.swing.JMenuItem mnItemAcercaDe;
    private javax.swing.JMenuItem mnItemAdministrarMiCuenta;
    private javax.swing.JMenuItem mnItemAdministrarUsuarios;
    private javax.swing.JMenuItem mnItemCrearNuevoUsuario;
    private javax.swing.JMenuItem mnItemManualDeUsuario;
    private javax.swing.JMenu mnUsuarios;
    // End of variables declaration//GEN-END:variables
}
