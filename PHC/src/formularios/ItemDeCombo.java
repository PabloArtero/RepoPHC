
package formularios;

/**
 * Este clase permite guardar en un combo una clave (id) asociada al String mostrado por
 * el JComboBox.
 * 
 * @author Usuario
 */
public class ItemDeCombo {
////////ATRIBUTOS
    private Integer id;
    private String cadena;
////////CONSTRUCTORES

    /**
     * Costrucutor sin parámetros
     */
    public ItemDeCombo(){
        this.id = 0;
        this.cadena = "NO SE CARGÓ LA CADENA DEL ÍTEM!!!!";
    }

    /**
     * Constructor con parámetros
     * 
     * @param id Clave asociada a la cadena del ítem
     * @param cadena Cadena de caracteres del ítem
     */
    public ItemDeCombo(Integer id, String cadena){
        this.id = id;
        this.cadena = cadena;
    }
////////toString() devuelve lo que mostrará por pantalla el JComboBox
    @Override
    public String toString(){
        return this.cadena;
    }

////////SETERS

    /**
     *
     * @param id
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     *
     * @param cadena
     */
    public void setCadena(String cadena){
        this.cadena = cadena;
    }

/////////GETERS

    /**
     *
     * @return
     */
    public Integer getId(){
        return this.id;
    }

    /**
     *
     * @return
     */
    public String getCadena(){
        return this.cadena;
    }
}
