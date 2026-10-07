package modelo;

/**
 * Clase que representa los libros disponibles en la biblioteca.
 */
public class Libros
{
    private int id;             // Atributo que representa un identificador único para el libro.
    private String titulo;      // Atributo que representa el título del libro.
    private String autor;       // Atributo que representa el autor del libro.
    private String isbn;        // Atributo que representa el ISBN del libro
    private String editorial;   // Atributo que representa la editorial del libro.
    private int stock;          // Atributo que representa el stock de libros.
    private int id_categoria;   // Atributo que representa el "ID" de la categoría del libro.

    // Constructor sin parámetros
    public Libros() {}

    // Constructor con parámetros
    public Libros (int id, String titulo, String autor, String isbn, String editorial, int stock, int id_categoria)
    {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.stock = stock;
        this.id_categoria = id_categoria;
    }

    // Se implementan los GETTERS:

    /**
     * Método que retorna el valor de la variable "ID".
     * @return "ID" del libro.
     */
    public int getId() {return id;}

    /**
     * Método que retorna el valor de la variable "título".
     * @return "título" del libro.
     */
    public String getTitulo() {return titulo;}

    /**
     * Método que retorna el valor de la variable "autor".
     * @return "autor" del libro.
     */
    public String getAutor() {return autor;}

    /**
     * Método que retorna el valor de la variable "ISBN".
     * @return "ISBN" del libro.
     */
    public String getIsbn() {return isbn;}

    /**
     * Método que retorna el valor de la variable "editorial".
     * @return "editorial" del libro.
     */
    public String getEditorial() {return editorial;}

    /**
     * Método que retorna el valor de la variable "stock".
     * @return "stock" del libro.
     */
    public int getStock() {return stock;}

    /**
     * Método que retorna el valor de la variable "id_categoria".
     * @return "id_categoria" del libro.
     */
    public int getId_categoria() {return id_categoria;}

    // Se implementan los SETTERS:

    /**
     * Método para ajustar el valor de la variable "ID".
     * @param id nuevo "ID" que se requiere asignar al objeto.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Método para ajustar el valor de la variable "título".
     * @param titulo nuevo "título" que se requiere asignar al objeto.
     */
    public void setTitulo(String titulo) {this.titulo = titulo;}

    /**
     * Método para ajustar el valor de la variable "autor".
     * @param autor nuevo "autor" que se requiere asignar al objeto.
     */
    public void setAutor(String autor) {this.autor = autor;}

    /**
     * Método para ajustar el valor de la variable "ISBN".
     * @param isbn nuevo "ISBN" que se requiere asignar al objeto.
     */
    public void setIsbn(String isbn) {this.isbn = isbn;}

    /**
     * Método para ajustar el valor de la variable "editorial".
     * @param editorial nueva "editorial" que se require asignar al objeto.
     */
    public void setEditorial(String editorial) {this.editorial = editorial;}

    /**
     * Método para ajustar el valor de la variable "stock".
     * @param stock nuevo "stock" que se require asignar al objeto.
     */
    public void setStock(int stock) {this.stock = stock;}

    /**
     * Método para ajustar el valor de la variable "id_categoria".
     * @param id_categoria nuevo "stock" que se require asignar al objeto.
     */
    public void setId_categoria(int id_categoria) {this.id_categoria = id_categoria;}

    // Se implementa un método toString

    /**
     * Método que devuelve una cadena de texto con la inromación del objeto
     * @return "ID" + "título" + "autor" + "ISBN" + "editorial" + "stock" + "id_categoria"
     */
    public String toString()
    {
        return id + " - " + titulo + " - " + autor + " - " + isbn +  " - " + editorial + " - " + stock + " - " + id_categoria;
    }

}
