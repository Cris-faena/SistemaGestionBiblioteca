package modelo;

import java.sql.Date;


/**
 * Clase que representa una lista con los préstamos de los libros de la biblioteca.
 */
public class Prestamos
{
    private int id;                        // Atributo que representa el identificador único del préstamo.
    private int id_estudiante;              // Atributo que representa el id_estudiante que solicitó el préstamo.
    private int id_libro;                   // Atributo que representa el id_libro que se entregó como préstamo.
    private Date fecha_prestamo;       // Atributo que representa la fecha de préstamo del libro.
    private Date fecha_devolucion;     // Atributo que representa la fecha de devolución del libro en calidad de préstamo.
    private boolean devuelto;               // Atributo que representa si el libro fue devuelto o no.

    // Constructor sin parámetros
    public Prestamos() {}

    // Constructor con parámetros
    public Prestamos(int id, int id_estudiante, int id_libro, Date fecha_prestamo, Date fecha_devolucion, boolean devuelto)
    {
        this.id = id;
        this.id_estudiante = id_estudiante;
        this.id_libro = id_libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.devuelto = devuelto;
    }

    // Se implementan los GETTERS

    /**
     * Método que retorna el valor de la variable "".
     * @return "id" del préstamo.
     */
    public int getId() {return id;}

    /**
     * Método que retorna el valor de la variable "id_estudiante".
     * @return "id_estudiante" del préstamo.
     */
    public int getId_estudiante() {return id_estudiante;}

    /**
     * Método que retorna el valor de la variable "id_libro".
     * @return "id_libro" del préstamo.
     */
    public int getId_libro() {return id_libro;}

    /**
     * Método que retorna el valor de la variable "fecha_prestamo".
     * @return "fecha_préstamo" del préstamo.
     */
    public Date getFecha_prestamo() {return fecha_prestamo;}

    /**
     * Método que retorna el valor de la variable "fecha_devolución".
     * @return "fecha_devolución" del préstamo.
     */
    public Date getFecha_devolucion() {return fecha_devolucion;}

    /**
     * Método que retorna el valor de la variable "devuelto".
     * @return condición o estado en el que se encuentra el préstamo. Puede ser "true" o "false".
     */
    public boolean isDevuelto() {return devuelto;}

    // Se implementan los SETTERS:

    /**
     * Método que modifica el valor de la variable "id".
     * @param id nuevo id que se requiere asignar al objeto.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Método que modifica el valor de la variable "id_estudiante".
     * @param id_estudiante nuevo id_estudiante que se require asignar al objeto.
     */
    public void setId_estudiante(int id_estudiante) {this.id_estudiante = id_estudiante;}

    /**
     * Método que modifica el valor de la variable "id_libro".
     * @param id_libro nuevo id_libro que se requiere asignar al objeto.
     */
    public void setId_libro(int id_libro) {this.id_libro = id_libro;}

    /**
     * Método que modifica el valor de la variable "fecha_prestamo".
     * @param fecha_prestamo nueva fecha_préstamo que se requiere asignar al objeto
     */
    public void setFecha_prestamo(Date fecha_prestamo) {this.fecha_prestamo = fecha_prestamo;}

    /**
     * Método que modifica el valor de la variable "fecha_devolucion".
     * @param fecha_devolucion nueva fecha_devolución que se requiere asignar al objeto.
     */
    public void setFecha_devolucion(Date fecha_devolucion) {this.fecha_devolucion = fecha_devolucion;}

    /**
     * Método que modifica el valor de la variable "devuelto".
     * @param devuelto nuevo valor booleano que se requiere asignar al objeto.
     */
    public void setDevuelto(boolean devuelto) {this.devuelto = devuelto;}

    // Se implementa un método toString

    /**
     * Método que devuelve una cadena de texto con la información del objeto.
     * @return "ID" + "id_estudiante" + "id_libro" + "fecha_préstamo" + "fecha_devolución" + "devuelto"
     */
    public String toString()
    {
        return id + " - " + id_estudiante + " - " + id_libro + " - " + fecha_prestamo + " - " + fecha_devolucion + " - " + devuelto;
    }
}
