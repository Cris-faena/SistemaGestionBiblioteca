package controlador;

import DAO.EstudianteDAO;
import DAO.impl.EstudianteDAOImpl;
import modelo.Estudiante;
import modelo.Libros;

import javax.swing.*;
import java.util.List;

public class ControladorEstudiante {
    private final EstudianteDAO estudianteDAO = new EstudianteDAOImpl();

    // ===================== AGREGAR ESTUDIANTE =====================
    public boolean insertarEstudiante(Estudiante estudiante) {
        return estudianteDAO.insertar(estudiante);
    }

    // ===================== EDITAR ESTUDIANTE =====================
    public boolean editarEstudiante(Estudiante estudiante) {
        return estudianteDAO.actualizar(estudiante);
    }

    // ===================== ELIMINAR ESTUDIANTE =====================
    public boolean eliminarEstudiante(int idEstudiante) {
        return estudianteDAO.eliminar(idEstudiante);
    }

    // ===================== BUSCAR ESTUDIANTE POR ID O NOMBRE =====================
    public Estudiante buscarEstudiantePorId(int idEstudiante) {
        return estudianteDAO.buscarPorId(idEstudiante);
    }

    public Estudiante buscarEstudiantePorNombre(String nombreEstudiante) {
        return estudianteDAO.buscarPorNombre(nombreEstudiante);
    }

    // ===================== LISTAR TODOS LOS ESTUDIANTES =====================
    public List<Estudiante> obtenerTodosEstudiantes() {
        return estudianteDAO.listarTodos();
    }

    // ===================== MOSTRAR ESTUDIANTES JTEXTAREA =====================
    public boolean cargarEstudiantesJAREA(JTextArea area) {return estudianteDAO.cargarEstudiantes(area);}
}
