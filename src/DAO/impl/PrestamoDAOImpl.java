package DAO.impl;

import DAO.PrestamoDAO;
import modelo.Prestamos;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO
{
    // ===================== AGREGAR PRÉSTAMO =====================

    /**
     * Método que permitirá agregar una Préstamo a la base de datos
     * @param prestamo objeto tipo Préstamo para agregar a la BD
     */
    public boolean insertar(Prestamos prestamo)
    {
        String sql = """
                INSERT INTO prestamos (id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto)
                VALUES (?,?,?,?,?)
        """;
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, prestamo.getId_estudiante());
            ps.setInt(2, prestamo.getId_libro());
            ps.setDate(3, prestamo.getFecha_prestamo());
            ps.setDate(4, prestamo.getFecha_devolucion());
            ps.setBoolean(5, prestamo.isDevuelto());

            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Error al insertar el préstamo: " + e.getMessage());
            return false;
        }
    }

    // ===================== EDITAR PRÉSTAMO =====================

    /**
     * Método que permitirá actualizar un Préstamo en la base de datos.
     * @param prestamo objeto Préstamo que se require actualizar
     */
    public boolean actualizar(Prestamos prestamo)
    {
        String sql = "UPDATE prestamos SET id_categoria = ?, id_libro = ?, fecha_prestamo = ?, fecha_devolucion = ?, devuelto = ?  WHERE id = ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, prestamo.getId_estudiante());
            ps.setInt(2, prestamo.getId_libro());
            ps.setDate(3, prestamo.getFecha_prestamo());
            ps.setDate(4, prestamo.getFecha_devolucion());
            ps.setBoolean(5, prestamo.isDevuelto());
            ps.setInt(6, prestamo.getId());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Error al editar préstamo: " + e.getMessage());
            return false;
        }
    }

    // ===================== ELIMINAR PRÉSTAMO =====================
    /**
     * Método que permitirá eliminar un Préstamo de la base de datos.
     *
     * @param idPrestamo "ID" del Préstamo que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idPrestamo)
    {
        String sql = "DELETE FROM prestamos WHERE id = ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idPrestamo);
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            // Detectar error de integridad referencial (FK)
            if (e.getMessage().contains("foreign key constraint fails"))
            {
                throw new RuntimeException("FK_ERROR");
            }
            System.out.println("Error al eliminar la categoría: " + e.getMessage());
            return false;
        }
    }

    // ===================== BUSCAR POR PRÉSTAMO =====================
    /**
     * Método que permitirá buscar un Préstamo en la base de datos.
     * @param idPrestamo "ID" del Préstamo que se requiere buscar.
     * @return un Objeto tipo Préstamo que coincide con el "ID" ingresado
     */
    public Prestamos buscarPorId(int idPrestamo)
    {
        // Almacena esta consulta SQL en un String, en donde se busquen todas los Préstamos de la tabla préstamos, en donde el "id" sea el parámetro ingresado
        String sql = "SELECT * FROM prestamos WHERE id= ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idPrestamo);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return new Prestamos(
                        rs.getInt("id"),
                        rs.getInt("id_estudiante"),
                        rs.getInt("id_libro"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getBoolean("devuelto")
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar préstamo: " + e.getMessage());
        }
        return null;
    }

    /**
     * Método que permitirá buscar un Préstamo en la base de datos.
     * @param devuelto categoría que se requiere buscar.
     * @return un Objeto tipo Préstamo que coincide con el boolean ingresado
     */
    public List<Prestamos> buscarPorDevuelto(boolean devuelto)
    {
        List<Prestamos> lista = new ArrayList<>();
        // Almacena esta consulta SQL en un String, en donde se busquen todos las categorías de la tabla categorías, en donde el nombre sea el parámetro ingresado
        String sql = "SELECT * FROM prestamos WHERE devuelto= ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setBoolean(1, devuelto);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                lista.add(new Prestamos(
                        rs.getInt("id"),
                        rs.getInt("id_estudiante"),
                        rs.getInt("id_libro"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getBoolean("devuelto")
                ));
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar el préstamo por estado de devolución: " + e.getMessage());
        }
        return lista;
    }

    // ===================== LISTAR TODOS LOS PRÉSTAMOS =====================
    /**
     * Método que permitirá devolver una lista de Préstamo de la base de datos.
     * @return una lista de objetos Préstamo previamente almacenada.
     */
    public List<Prestamos> listarTodos()
    {
        // Se crea una lista para almacenar los préstamos de la base de datos
        List<Prestamos> lista = new ArrayList<>();
        // Almacena esta consulta SQL en un String, en donde se seleccione todas las columnas de la tabla "préstamos", ordenada por "id".
        String sql = "SELECT * FROM prestamos ORDER BY id";

        // Intenta conectar a la base de datos, y crea una consulta estática SQL. Además crea un objeto "rs" que almacene los resultados de la consulta en memoria
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // mientras no haya otra línea, crea un objeto categoría con los elementos existentes en la base de datos
            while (rs.next()) {
                Prestamos prestamos = new Prestamos(
                        rs.getInt("id"),
                        rs.getInt("id_estudiante"),
                        rs.getInt("id_libro"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getBoolean("devuelto")
                );
                // añade estos elementos a la lista "préstamos"
                lista.add(prestamos);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar préstamos: " + e.getMessage());
            return null;
        }
        return lista;
    }
}
