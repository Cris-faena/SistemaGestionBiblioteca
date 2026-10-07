package modelo;

/**
 * Clase que representa la categoría de los libros existentes en la biblioteca.
 */
public class Categoria
{
    private int id;                     // Atributo para asignar el identificador único de la categoría.
    private CategoriaLibros nombre;  // Atributo para asignar la categoría específica de un libro.

    // Constructor sin parámetros.
    public Categoria() {}

    // Constructor con parámetros.
    public Categoria(int id, CategoriaLibros nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    // Se implementan los GETTERS:

    /**
     * Método que retorna el valor de la variable "ID"
     * @return "ID" del objeto creado.
     */
    public int getId() {return id;}

    /**
     * Método que retorna el valor de la variable "categoría".
     * @return "categoría" del objeto creado.
     */
    public CategoriaLibros getNombre() {return nombre;}

    // Se implementan los SETTERS

    /**
     * Método de ajusta el valor de la variable "ID".
     * @param id nuevo "ID" que se requiere asignar al objeto.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Método de ajusta el valor de la variable "categoría".
     * @param nombre nueva "categoría" que se requiere asignar al objeto.
     */
    public void setNombre(CategoriaLibros nombre) {this.nombre = nombre;}

    // Se implementa un método toString

    /**
     * Método que retorna una cadena de texto con información del objeto.
     * @return "ID" + "categoría".
     */
    public String toString()
    {
        return id + " - " + nombre;
    }
}
