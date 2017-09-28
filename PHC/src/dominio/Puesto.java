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
public class Puesto {
    private int idPuesto;
    private String nombre;
    private String descripcion;
    private Departamento departamento;
    private Nivel nivel;
    private ArrayList<Requerimiento> requerimientos;
    private ArrayList<Tarea> tareas;

    public Puesto(int idPuesto, String nombre, String descripcion, Departamento departamento, Nivel nivel, ArrayList<Requerimiento> requerimientos, ArrayList<Tarea> tareas) {
        this.idPuesto = idPuesto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.nivel = nivel;
        this.requerimientos = requerimientos;
        this.tareas = tareas;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Requerimiento> getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(ArrayList<Requerimiento> requerimientos) {
        this.requerimientos = requerimientos;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }
    
    
}
