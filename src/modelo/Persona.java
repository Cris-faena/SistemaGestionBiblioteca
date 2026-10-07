package modelo;

/**
 * Clase abstracta que representa a una entidad persona.
 * Se utilizará como base para crear las otras entidades del modelo.
 */
public abstract class Persona
{
    private int id;         // Atributo para asignar un identificador único.
    private String nombre;  // Atributo para asignar un nombre.
    private String rut;     // Atributo para asignar un RUT.
    private String correo;  // Atributo para asignar un correo.

    // Constructor sin parámetros
    public  Persona() {}

    // Constructor con parámetros
    public Persona(int id, String nombre, String rut, String correo)
    {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
    }

    // Se implementan los GETTERS:

    /**
     * Método que devuelve el valor de la variable id.
     * @return "ID" del objeto.
     */
    public int getId() {return id;}

    /**
     * Método que devuelve el valor de la variable nombre.
     * @return "nombre" del objeto.
     */
    public String getNombre() {return nombre;}

    /**
     * Método que devuelve el valor de la variable rut.
     * @return "RUT" del objeto.
     */
    public String getRut() {return rut;}

    /**
     * Método que devuelve el valor de la variable correo.
     * @return "correo" del objeto.
     */
    public String getCorreo() {return correo;}

    // Se implementan los SETTERS:

    /**
     * Método que permite ajustar el valor de la variable "ID".
     * @param id nuevo "ID" que se requiere asignar.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Método que permite ajustar el valor de la variable "nombre".
     * @param nombre nuevo "nombre" que se requiere asignar.
     */
    public void setNombre(String nombre) {this.nombre = nombre;}

    /**
     * Método que permite ajustar el valor de la variable "rut".
     * @param rut nuevo "RUT" que se requiere asignar.
     */
    public void setRut(String rut) {this.rut = rut;}

    /**
     * Método que permite ajustar el valor de la variable "correo".
     * @param correo nuevo "correo" que se requiere asignar.
     */
    public void setCorreo(String correo) {this.correo = correo;}

    // Se implementa un método toString

    /**
     * Método que devuelve una cadena de texto con información del objeto
     * @return "ID" + "nombre" + "RUT" + "correo"
     */
    public String toString()
    {
        return id + " - " + nombre + " - " + rut +  " - " + correo;
    }
}
