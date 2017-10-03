package controladores;

import dominio.Nivel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import persistencia.MYSQL.MysqlNivelDAO;
import vistas.*;

/**
 *
 * @author Pablo
 */
public class ControladorModificaNivel implements ActionListener {
    private Nivel nivel;
    private MysqlNivelDAO nivelDAO;
    private VentanaModificaNivel ventanaModificaNivel;
    private VentanaNivel ventanaNivel;

    public ControladorModificaNivel(Nivel nivel, MysqlNivelDAO nivelDAO, VentanaModificaNivel ventanaModificaNivel,VentanaNivel ventanaNivel) {
        this.nivel = nivel;
        this.nivelDAO = nivelDAO;
        this.ventanaModificaNivel = ventanaModificaNivel;
        this.ventanaNivel = ventanaNivel;
        
        this.ventanaModificaNivel.btn_accept.addActionListener(this);
        this.ventanaModificaNivel.btn_cancel.addActionListener(this);
    }
    
    public void inicializar(){
        ventanaModificaNivel.setTitle("Modificación de Nivel");
        ventanaModificaNivel.setLocationRelativeTo(null);
        ventanaModificaNivel.lbl_idnivel.setText(String.valueOf(nivel.getIdNivel()));
    }
    
    private void aceptar(){
        if (validarNombre()) {
            nivel.setNombre(ventanaModificaNivel.txt_nombrenivel.getText());
            nivelDAO.modificar(nivel);
        }else{
            System.out.println("Nombre incorrecto");
        }
    }
    private void cancelar(){
        ventanaModificaNivel.dispose();
        ventanaNivel.setVisible(true);
    }
    
    private boolean validarNombre(){//Aquí habría que validar los datos traidos de la vista
        /*Como mínimo habría que validar que no sea una cadena vacía
        y que tampoco sea solo espacios en blanco*/
        if (ventanaModificaNivel.txt_nombrenivel.getText().isEmpty()) {
            return false;
        }else{
            return true;
        }
        /*Recordar: me faltó validar que no sea solo espacios en blanco*/
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaModificaNivel.btn_accept) aceptar();
        if (e.getSource() == ventanaModificaNivel.btn_cancel) cancelar();
    }
    
    
    
}

