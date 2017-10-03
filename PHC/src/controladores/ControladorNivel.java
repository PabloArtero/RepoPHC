package controladores;

import dominio.Nivel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import persistencia.MYSQL.MysqlNivelDAO;
import vistas.VentanaNivel;
import vistas.VentanaPrincipal;

/**
 *
 * @author josdan
 */
public class ControladorNivel  implements ActionListener/*, FocusListener*/{
    private Nivel nivel;
    private MysqlNivelDAO nivelDAO;
    private VentanaNivel ventanaNivel;
    private VentanaPrincipal ventanaPrincipal;
    private DefaultTableModel dtmTabla;
    private ArrayList<Nivel> listaNiveles;

    public ControladorNivel(VentanaPrincipal ventanaPrincipal) {
        this.nivel = new Nivel("");
        this.nivelDAO = new MysqlNivelDAO();
        this.ventanaNivel = new VentanaNivel();
        this.ventanaPrincipal = ventanaPrincipal;
        this.dtmTabla = new DefaultTableModel();
        this.listaNiveles = new ArrayList<>();
        
        this.ventanaNivel.mnAgregar.addActionListener(this);
        this.ventanaNivel.mnModificar.addActionListener(this);
        this.ventanaNivel.mnVolver.addActionListener(this);
        //this.ventanaNivel.addFocusListener(this);
        
        inicializarVista();
    }
    
    private void inicializarVista(){
        ventanaNivel.setTitle("Gestionar Nivel");
        ventanaNivel.setLocationRelativeTo(null);
        ventanaPrincipal.setEnabled(false);
        ventanaNivel.setVisible(true);
        ventanaNivel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Inicializo la tabla
        dtmTabla = (DefaultTableModel) ventanaNivel.tabla.getModel();//obtengo el modelo de la tabla para manipularlo
        //establezco los anchos predeterminados para las columnas:
        ventanaNivel.tabla.getColumnModel().getColumn(0).setPreferredWidth(1);//la de índex=0 (ID)
        ventanaNivel.tabla.getColumnModel().getColumn(1).setPreferredWidth(300);//la de índex=1 (Nombre)
        actualizarTabla();
    }
    
    private void agregar(){
        ControladorAltaNivel controladorAltaNivel = new ControladorAltaNivel(ventanaNivel);
    }
    private void modificar(){
        
    }
    private void volver(){
        
    }
    
    private void actualizarTabla(){
        while(dtmTabla.getRowCount()>0)dtmTabla.removeRow(0);//limpio la tabla
        listaNiveles = nivelDAO.obtenerTodos();
        Iterator<Nivel> iterador = listaNiveles.iterator();
        while(iterador.hasNext()){
            Nivel nivel = iterador.next();
            Integer id = nivel.getIdNivel();
            String nombre = nivel.getNombre();
            dtmTabla.addRow(new Object[]{id, nombre});
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaNivel.mnAgregar) agregar();
        if (e.getSource() == ventanaNivel.mnModificar) modificar();
        if (e.getSource() == ventanaNivel.mnVolver) volver();
    }
/*
    @Override
    public void focusGained(FocusEvent e) {
        //if (e.getSource() == ventanaNivel) actualizarTabla();
    }

    @Override//No implementado
    public void focusLost(FocusEvent e) {
        
    }
    
   */ 
    
}
