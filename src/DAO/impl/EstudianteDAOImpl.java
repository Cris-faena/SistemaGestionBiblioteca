package DAO.impl;

import DAO.EstudianteDAO;
import modelo.Estudiante;
import util.ConexionBD;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO
{
    // ===================== AGREGAR ESTUDIANTE =====================
    /**
     * Método que permitirá agregar una estudiante a la base de datos
     * @param estudiante objeto tipo estudiante para agregar a la BD
     */
    public boolean insertar(Estudiante estudiante)
    {
        String sql = """
                INSERT INTO estudiantes (nombre, rut, curso, correo)
                VALUES (?,?,?,?)
        """;
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getRut());
            ps.setString(3, estudiante.getCurso());
            ps.setString(4, estudiante.getCorreo());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Error al insertar el estudiante: " + e.getMessage());
            return false;
        }
    }

    // ===================== EDITAR ESTUDIANTE =====================

    /**
     * Método que permitirá actualizar un estudiante en la base de datos.
     * @param estudiante objeto estudiante que se require actualizar
     */
    public boolean actualizar(Estudiante estudiante)
    {
        String sql = "UPDATE estudiantes SET nombre = ? WHERE id= ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            // Se cambió el valor de los índices
            ps.setString(1, estudiante.getNombre());
            ps.setInt(2, estudiante.getId());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Error al editar estudiante: " + e.getMessage());
            return false;
        }
    }

    // ===================== ELIMINAR ESTUDIANTE =====================
    /**
     * Método que permitirá eliminar un estudiante de la base de datos.
     *
     * @param idEstudiante "ID" del estudiante que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idEstudiante)
    {
        String sql = "DELETE FROM estudiantes WHERE id = ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idEstudiante);
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            // Detectar error de integridad referencial (FK)
            if (e.getMessage().contains("foreign key constraint fails"))
            {
                throw new RuntimeException("FK_ERROR");
            }
            System.out.println("Error al eliminar el estudiante: " + e.getMessage());
            return false;
        }
    }

    // ===================== BUSCAR ESTUDIANTE POR ID O NOMBRE =====================
    /**
     * Método que permitirá buscar un estudiante en la base de datos.
     * @param idEstudiante "ID" del estudiante que se requiere buscar.
     * @return un Objeto tipo estudiante que coincide con el "ID" ingresado
     */
    public Estudiante buscarPorId(int idEstudiante)
    {
        // Almacena esta consulta SQL en un String, en donde se busquen todos los estudiantes de la tabla estudiante, en donde el "id" sea el parámetro ingresado
        String sql = "SELECT * FROM estudiantes WHERE id= ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idEstudiante);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("curso"),
                        rs.getString("correo")
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar el estudiante: " + e.getMessage());
        }
        return null;
    }

    /**
     * Método que permitirá buscar un estudiante en la base de datos.
     * @param nombreEstudiante nombre del estudiante que se requiere buscar.
     * @return un Objeto tipo estudiante que coincide con el string ingresado
     */
    public Estudiante buscarPorNombre(String nombreEstudiante)
    {
        // Almacena esta consulta SQL en un String, en donde se busquen todos los estudiantes de la tabla estudiantes, en donde el nombre sea el parámetro ingresado
        String sql = "SELECT * FROM estudiantes WHERE nombre= ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, nombreEstudiante);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("curso"),
                        rs.getString("correo")
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar al estudiante: " + e.getMessage());
        }
        return null;
    }


    // ===================== LISTAR TODOS LOS ESTUDIANTES =====================
    /**
     * Método que permitirá devolver una lista de estudiantes de la base de datos.
     * @return una lista de objetos "estudiante" previamente almacenada.
     */
    public List<Estudiante> listarTodos()
    {
        // Se crea una lista para almacenar los estudiantes de la base de datos
        List<Estudiante> lista = new ArrayList<>();
        // Almacena esta consulta SQL en un String, en donde se seleccione todas las columnas de la tabla "categorías", ordenada por "id".
        String sql = "SELECT * FROM estudiantes ORDER BY id";

        // Intenta conectar a la base de datos, y crea una consulta estática SQL. Además crea un objeto "rs" que almacene los resultados de la consulta en memoria
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // mientras no haya otra línea, crea un objeto categoría con los elementos existentes en la base de datos
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("curso"),
                        rs.getString("correo")
                );
                // añade estos elementos a la lista "categoría"
                lista.add(estudiante);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los estudiantes: " + e.getMessage());
            return null;
        }
        return lista;
    }

    public boolean cargarEstudiantes(JTextArea textArea)
    {
        String sql = "SELECT id,nombre,rut,curso,correo FROM estudiantes ORDER BY id";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            StringBuilder sb  = new StringBuilder();
            while (rs.next())
            {
                sb.append("\n---------------- ESTUDIANTE ---------------------\n");
                sb.append("ID: ").append(rs.getInt("id")).append("\n");
                sb.append("Nombre: ").append(rs.getString("nombre")).append("\n");
                sb.append("Rut: ").append(rs.getString("rut")).append("\n");
                sb.append("Curso: ").append(rs.getString("curso")).append("\n");
                sb.append("Correo: ").append(rs.getString("correo")).append("\n");
                sb.append("-------------------------------------------------------------\n");
            }
            textArea.setText(sb.toString());
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("Error al cargar estudiantes: " + e.getMessage());
            textArea.setText("Error al cargar datos: " + e.getMessage());
        }
        return false;
    }
}
