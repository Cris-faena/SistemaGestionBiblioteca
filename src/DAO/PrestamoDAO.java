package DAO;

import modelo.Prestamos;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface PrestamoDAO
{
    // ===================== AGREGAR PRÉSTAMO =====================

    /**
     * Método que permitirá agregar una Préstamo a la base de datos
     * @param prestamo objeto tipo Préstamo para agregar a la BD
     */
    public boolean insertar(Prestamos prestamo);

    // ===================== EDITAR PRÉSTAMO =====================

    /**
     * Método que permitirá actualizar un Préstamo en la base de datos.
     * @param prestamo objeto Préstamo que se require actualizar
     */
    public boolean actualizar(Prestamos prestamo);

    // ===================== ELIMINAR PRÉSTAMO =====================
    /**
     * Método que permitirá eliminar un Préstamo de la base de datos.
     *
     * @param idPrestamo "ID" del Préstamo que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idPrestamo);

    // ===================== BUSCAR POR PRÉSTAMO =====================
    /**
     * Método que permitirá buscar un Préstamo en la base de datos.
     * @param idPrestamo "ID" del Préstamo que se requiere buscar.
     * @return un Objeto tipo Préstamo que coincide con el "ID" ingresado
     */
    public Prestamos buscarPorId(int idPrestamo);

    /**
     * Método que permitirá buscar un Préstamo en la base de datos.
     * @param devuelto categoría que se requiere buscar.
     * @return un Objeto tipo Préstamo que coincide con el boolean ingresado
     */
    public List<Prestamos> buscarPorDevuelto(boolean devuelto);

    // ===================== LISTAR TODOS LOS PRÉSTAMOS =====================
    /**
     * Método que permitirá devolver una lista de Préstamo de la base de datos.
     * @return una lista de objetos Préstamo previamente almacenada.
     */
    public List<Prestamos> listarTodos();

    public boolean consultarEstudiantePrestamo(DefaultTableModel modelo);

    public boolean consultarPrestamoEspecifico(DefaultTableModel modelo, int idEstudiante);


}
