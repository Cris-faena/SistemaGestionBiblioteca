package DAO;

import modelo.Categoria;
import modelo.Libros;

import javax.swing.*;
import java.util.List;

public interface LibrosDAO
{
    // ===================== AGREGAR LIBRO =====================
    /**
     * Método que permitirá agregar un libro a la base de datos
     * @param libro objeto tipo "Libro" para agregar a la BD
     */
    public boolean insertar(Libros libro);

    // ===================== EDITAR LIBRO =====================
    /**
     * Método que permitirá actualizar un registro en la base de datos.
     * @param libro objeto tipo "Libros" que se require actualizar.
     */
    public boolean actualizar(Libros libro);

    // ===================== ELIMINAR LIBRO =====================
    /**
     * Método que permitirá eliminar un libro de la base de datos.
     *
     * @param idLibro "ID" del libro que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idLibro);

    // ===================== BUSCAR POR LIBRO =====================
    /**
     * Método que permitirá buscar un libro en la base de datos.
     * @param idLibro "ID" del libro que se requiere buscar.
     * @return un Objeto tipo "Libro" que coincide con el "ID" ingresado
     */
    public Libros buscarPorId(int idLibro);

    /**
     * Método que permitirá buscar una categoría en la base de datos.
     * @param autorLibro parámetro que se requiere buscar.
     * @return un Objeto tipo "Libro" que coincide con el string ingresado
     */
    public Libros buscarPorAutor(String autorLibro);

    /**
     * Método que permitirá buscar un libro en la base de datos.
     * @param tituloLibro parámetro que se requiere buscar.
     * @return un Objeto tipo "Libro" que coincide con el string ingresado
     */
    public Libros buscarPorTitulo(String tituloLibro);

    // ===================== LISTAR TODOS =====================
    /**
     * Método que permitirá devolver una lista de libros de la base de datos.
     * @return una lista de objetos "Libros" previamente almacenada.
     */
    public List<Libros> listarTodos();

    // ===================== COMPROBAR ISBN LIBRO =====================
    /**
     * Método que verifica si un ISBN ya existe
     * @return "true" si ya existe, "false" si no existe
     */
    public boolean existeISBN(String isbn);

    /**
     * Método que permite disminuir el stock de un libro en específico
     * @param idLibro "id del libro que se quiere modificar el stock
     */
    public boolean retirarLibro(int idLibro);


    public boolean cargarConsultaLibros(JTextArea textArea);
}
