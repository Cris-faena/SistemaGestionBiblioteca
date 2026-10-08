package controlador;

import DAO.PrestamoDAO;
import DAO.impl.PrestamoDAOImpl;
import modelo.Prestamos;

import java.util.List;

public class ControladorPrestamo
{
    private final PrestamoDAO prestamoDAO = new PrestamoDAOImpl();

    // ===================== AGREGAR PRÉSTAMO =====================
    public boolean insertarPrestamo(Prestamos prestamo) {return prestamoDAO.insertar(prestamo);}

    // ===================== EDITAR PRÉSTAMO =====================
    public boolean editarPrestamo(Prestamos prestamo) {return prestamoDAO.actualizar(prestamo);}

    // ===================== ELIMINAR PRÉSTAMO =====================
    public boolean eliminarPrestamo(int idPrestamo) {return prestamoDAO.eliminar(idPrestamo);}

    // ===================== BUSCAR PRÉSTAMO POR ID O BOOLEAN =====================
    public Prestamos buscarPrestamoPorId(int idPrestamo) {return prestamoDAO.buscarPorId(idPrestamo);}
    public Prestamos buscarPrestamoPorBoolean(boolean devuelto) {return prestamoDAO.buscarPorDevuelto(devuelto);}

    // ===================== LISTAR TODOS LOS PRÉSTAMOS =====================
    public List<Prestamos> obtenerTodosLosPrestamos() {return prestamoDAO.listarTodos();}
}
