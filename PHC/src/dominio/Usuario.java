/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author josdan
 */
public class Usuario {
    
    private int idEmpleado;
    private String nombreUsuario;
    private String contraseña;
    private boolean esAdministrador;
    private boolean habilitado;
    private boolean contraseñaRestaurada;

    public Usuario(int idEmpleado, String nombreUsuario, String contraseña, boolean esAdministrador, boolean habilitado, boolean contraseñaRestaurada) {
        this.idEmpleado = idEmpleado;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.esAdministrador = esAdministrador;
        this.habilitado = habilitado;
        this.contraseñaRestaurada = contraseñaRestaurada;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isContraseñaRestaurada() {
        return contraseñaRestaurada;
    }

    public void setContraseñaRestaurada(boolean contraseñaRestaurada) {
        this.contraseñaRestaurada = contraseñaRestaurada;
    }
}
