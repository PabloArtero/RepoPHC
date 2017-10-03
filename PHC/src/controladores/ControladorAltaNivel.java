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
public class ControladorAltaNivel implements ActionListener {
    private Nivel nivel;
    private MysqlNivelDAO nivelDAO;
    private VentanaAltaNivel ventanaAltaNivel;
    private VentanaNivel ventanaNivel;

    public ControladorAltaNivel(Nivel nivel, MysqlNivelDAO nivelDAO, VentanaAltaNivel ventanaAltaNivel,VentanaNivel ventanaNivel) {
        this.nivel = nivel;
        this.nivelDAO = nivelDAO;
        this.ventanaAltaNivel = ventanaAltaNivel;
        this.ventanaNivel = ventanaNivel;
        
        this.ventanaAltaNivel.btn_accept.addActionListener(this);
        this.ventanaAltaNivel.btn_cancel.addActionListener(this);
    }
    
    private void aceptar(){
        if (validarNombre()) {
            nivel.setNombre(ventanaAltaNivel.txt_nombrenivel.getText());
            nivelDAO.insertar(nivel);
        }else{
            System.out.println("Nombre incorrecto");
        }
    }
    private void cancelar(){
        ventanaAltaNivel.dispose();
        ventanaNivel.setVisible(true);
    }
    
    private boolean validarNombre(){//Aquí habría que validar los datos traidos de la vista
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaAltaNivel.btn_accept) aceptar();
        if (e.getSource() == ventanaAltaNivel.btn_cancel) cancelar();
    }
    
    
    
}
