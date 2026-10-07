package controlador;

import DAO.LibrosDAO;
import DAO.impl.LibrosDAOImpl;
import modelo.Libros;

import java.util.List;

public class ControladorLibros
{
    private final LibrosDAO libroDAO = new LibrosDAOImpl();

    // ===================== AGREGAR LIBRO =====================
    public boolean insertarLibro(Libros libro) {return libroDAO.insertar(libro);}

    // ===================== EDITAR LIBRO =====================
    public boolean editarLibro(Libros libro) {return libroDAO.actualizar(libro);}

    // ===================== ELIMINAR LIBRO =====================
    public boolean eliminarLibro(int idLibro) {return libroDAO.eliminar(idLibro);}

    // ===================== BUSCAR LIBRO POR ID O AUTOR =====================
    public Libros buscarLibroPorId(int idLibro) {return libroDAO.buscarPorId(idLibro);}
    public Libros buscarLibroPorAutor(String autorLibro) {return libroDAO.buscarPorAutor(autorLibro);}

    // ===================== LISTAR TODOS LOS LIBROS =====================
    public List<Libros> obtenerTodosLibros() {return libroDAO.listarTodos();}

    // ===================== COMPROBAR ISBN DE UN LIBRO =====================
    public boolean comprobarISBNLibro(String isbn) {return libroDAO.existeISBN(isbn);}
}
