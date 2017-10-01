/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;

/**
 *
 * @author josdan
 * @param <T>
 */
public interface baseDAO<T> {
    
     void insertar(T nivel);
    
    void modificar(T nivel);
    
    void eliminar(T nivel);
    
    List<T> obtenerTodos();
    
    T obtener(int id);
    
}
