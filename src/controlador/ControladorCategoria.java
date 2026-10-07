package controlador;

import DAO.CategoriaDAO;
import DAO.impl.CategoriaDAOImpl;
import modelo.Categoria;

import java.sql.SQLException;
import java.util.List;

public class ControladorCategoria
{
    // Se implementa una nueva instancia de CategoriaDao
    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

    // ===================== AGREGAR CATEGORÍA =====================
    public boolean crearCategoria(Categoria categoria)
    {
        return categoriaDAO.insertar(categoria);
    }

    // ===================== EDITAR CATEGORÍA =====================
    public boolean editarCategoria(Categoria categoria) {return categoriaDAO.actualizar(categoria);}

    // ===================== ELIMINAR CATEGORÍA =====================
    public boolean eliminarCategoria(int idCategoria) {return categoriaDAO.eliminar(idCategoria);}

    // ===================== LISTAR TODAS LAS CATEGORÍAS =====================
    public List<Categoria> obtenerCategorias() {return categoriaDAO.listarTodos();}

    // ===================== FILTRAR CATEGORÍAS =====================

    public Categoria buscarPorId(int idCategoria) {return categoriaDAO.buscarPorId(idCategoria);}
    public Categoria buscarPorCategoria(String categoria) {return categoriaDAO.buscarPorCategoria(categoria);}
}
