/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author josdan
 */
public class Requerimiento {
    private int idRequerimiento;
    private String descripcion;
    private ArrayList<Puesto> puestos;

    public Requerimiento(int idRequerimiento, String descripcion, ArrayList<Puesto> puestos) {
        this.idRequerimiento = idRequerimiento;
        this.descripcion = descripcion;
        this.puestos = puestos;
    }

    
    public int getIdRequerimiento() {
        return idRequerimiento;
    }

    public void setIdRequerimiento(int idRequerimiento) {
        this.idRequerimiento = idRequerimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Puesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(ArrayList<Puesto> puestos) {
        this.puestos = puestos;
    }
    
}
