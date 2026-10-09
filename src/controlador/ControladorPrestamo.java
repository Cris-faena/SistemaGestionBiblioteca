package controlador;

import DAO.PrestamoDAO;
import DAO.impl.PrestamoDAOImpl;
import modelo.Prestamos;

import javax.swing.table.DefaultTableModel;
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
    public List<Prestamos> buscarPrestamoPorBoolean(boolean devuelto) {return prestamoDAO.buscarPorDevuelto(devuelto);}
    public boolean consultarEstudianteConPrestamo(DefaultTableModel modelo) {return prestamoDAO.consultarEstudiantePrestamo(modelo);}
    public boolean consultarPrestamoEspecificoPorEstudiante(DefaultTableModel modelo, int idEstudiante) {return prestamoDAO.consultarPrestamoEspecifico(modelo, idEstudiante);}

    // ===================== LISTAR TODOS LOS PRÉSTAMOS =====================
    public List<Prestamos> obtenerTodosLosPrestamos() {return prestamoDAO.listarTodos();}
}
