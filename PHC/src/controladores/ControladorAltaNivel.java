package controladores;

import dominio.Nivel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import persistencia.MYSQL.MysqlNivelDAO;
import vistas.*;

/**
 *
 * @author Pablo
 */
public class ControladorAltaNivel implements ActionListener {
    private MysqlNivelDAO nivelDAO;
    private VentanaAltaNivel ventanaAltaNivel;
    private VentanaNivel ventanaNivel;

    public ControladorAltaNivel(VentanaNivel ventanaNivel) {
        this.nivelDAO = new MysqlNivelDAO();
        this.ventanaAltaNivel = new VentanaAltaNivel();
        this.ventanaNivel = ventanaNivel;
        
        this.ventanaAltaNivel.btn_accept.addActionListener(this);
        this.ventanaAltaNivel.btn_cancel.addActionListener(this);
        
        inicializarVista();
    }
    
    private void inicializarVista(){
        ventanaNivel.setEnabled(false);
        ventanaAltaNivel.setTitle("Alta de Nivel");
        ventanaAltaNivel.setLocationRelativeTo(null);
        ventanaAltaNivel.setVisible(true);
        ventanaAltaNivel.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    private void aceptar(){
        if (validarNombre()) {
            Nivel nivel = new Nivel(ventanaAltaNivel.txt_nombrenivel.getText());
            nivelDAO.insertar(nivel);
        }else{
            System.out.println("Nombre incorrecto");
        }
    }
    private void cancelar(){
        ventanaAltaNivel.dispose();
        ventanaNivel.setEnabled(true);
    }
    
    private boolean validarNombre(){//Aquí habría que validar los datos traidos de la vista
        /*Como mínimo habría que validar que no sea una cadena vacía
        y que tampoco sea solo espacios en blanco*/
        if (ventanaAltaNivel.txt_nombrenivel.getText().isEmpty()) {
            return false;
        }else{
            return true;
        }
        /*Recordar: me faltó validar que no sea solo espacios en blanco*/
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaAltaNivel.btn_accept) aceptar();
        if (e.getSource() == ventanaAltaNivel.btn_cancel) cancelar();
    }
    
    
    
}
