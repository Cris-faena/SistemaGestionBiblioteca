package DAO;

import modelo.Categoria;
import modelo.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface CategoriaDAO
{
    // ===================== AGREGAR CATEGORÍA =====================

    /**
     * Método que permitirá agregar una Categoría a la base de datos
     * @param categoria objeto tipo categoria para agregar a la BD
     */
    public boolean insertar(Categoria categoria);

    // ===================== EDITAR CATEGORÍA =====================

    /**
     * Método que permitirá actualizar un registro en la base de datos.
     * @param categoria objeto categoría que se require actualizar
     */
    public boolean actualizar(Categoria categoria);

    // ===================== ELIMINAR CATEGORÍA =====================
    /**
     * Método que permitirá eliminar una categoría de la base de datos.
     *
     * @param idCategoria "ID" de la categoría que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idCategoria);

    // ===================== BUSCAR POR CATEGORÍA =====================
    /**
     * Método que permitirá buscar una categoría en la base de datos.
     * @param idCategoria "ID" de la categoría que se requiere buscar.
     * @return un Objeto tipo categoría que coincide con el "ID" ingresado
     */
    public Categoria buscarPorId(int idCategoria);

    /**
     * Método que permitirá buscar una categoría en la base de datos.
     * @param categoria categoría que se requiere buscar.
     * @return un Objeto tipo categoría que coincide con el string ingresado
     */
    public Categoria buscarPorCategoria(String categoria);

    // ===================== LISTAR TODOS =====================
    /**
     * Método que permitirá devolver una lista de categorías de la base de datos.
     * @return una lista de objetos "categoría" previamente almacenada.
     */
    public List<Categoria> listarTodos();
}
