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
public class Tarea {
    private int idTarea;
    private String descripcion;
    private ArrayList<Puesto> puestos;

    public Tarea(int idTarea, String descripcion, ArrayList<Puesto> puestos) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
        this.puestos = puestos;
    }
    
    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
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
