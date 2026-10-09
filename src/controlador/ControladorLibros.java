package controlador;

import DAO.LibrosDAO;
import DAO.impl.LibrosDAOImpl;
import modelo.Libros;

import javax.swing.*;
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
    public Libros buscarLibroPorTitulo(String tituloLibro) {return libroDAO.buscarPorTitulo(tituloLibro);}

    // ===================== LISTAR TODOS LOS LIBROS =====================
    public List<Libros> obtenerTodosLibros() {return libroDAO.listarTodos();}

    // ===================== COMPROBAR ISBN DE UN LIBRO =====================
    public boolean comprobarISBNLibro(String isbn) {return libroDAO.existeISBN(isbn);}

    // ===================== DISMINUIR STOCK DE UN LIBRO =====================
    public synchronized boolean retirarLibro(int idLibro) {return libroDAO.retirarLibro(idLibro);}

    // ===================== CONSULTAR TODOS LOS LIBRO =====================
    public boolean consultarLibros(JTextArea area) {return libroDAO.cargarConsultaLibros(area);}

}
