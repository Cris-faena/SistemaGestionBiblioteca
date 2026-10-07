package DAO;

import modelo.Categoria;
import modelo.Estudiante;

import java.util.List;

public interface EstudianteDAO
{
    // ===================== AGREGAR ESTUDIANTE =====================

    /**
     * Método que permitirá agregar una estudiante a la base de datos
     * @param estudiante objeto tipo estudiante para agregar a la BD
     */
    public boolean insertar(Estudiante estudiante);

    // ===================== EDITAR ESTUDIANTE =====================

    /**
     * Método que permitirá actualizar un estudiante en la base de datos.
     * @param estudiante objeto categoría que se require actualizar
     */
    public boolean actualizar(Estudiante estudiante);

    // ===================== ELIMINAR ESTUDIANTE =====================
    /**
     * Método que permitirá eliminar un estudiante de la base de datos.
     *
     * @param idEstudiante "ID" del estudiante que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idEstudiante);

    // ===================== BUSCAR POR ID O NOMBRE ESTUDIANTE =====================
    /**
     * Método que permitirá buscar un estudiante en la base de datos.
     * @param idEstudiante "ID" del estudiante que se requiere buscar.
     * @return un Objeto tipo estudiante que coincide con el "ID" ingresado
     */
    public Estudiante buscarPorId(int idEstudiante);

    /**
     * Método que permitirá buscar un estudiante en la base de datos.
     * @param nombreEstudiante nombre del estudiante que se requiere buscar.
     * @return un Objeto tipo estudiante que coincide con el string ingresado
     */
    public Estudiante buscarPorNombre(String nombreEstudiante);

    // ===================== LISTAR TODOS LOS ESTUDIANTES =====================
    /**
     * Método que permitirá devolver una lista de estudiantes de la base de datos.
     * @return una lista de objetos "estudiante" previamente almacenada.
     */
    public List<Estudiante> listarTodos();
}
