package DAO.impl;

import DAO.LibrosDAO;
import modelo.Libros;
import util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibrosDAOImpl implements LibrosDAO
{
    // ===================== AGREGAR LIBRO =====================
    /**
     * Método que permitirá agregar un libro a la base de datos
     * @param libro objeto tipo "Libro" para agregar a la BD
     */
    public boolean insertar(Libros libro)
    {
        String sql = """
                INSERT INTO libros (titulo, autor, isbn, editorial, stock, id_categoria)
                VALUES (?,?,?,?,?,?)
        """;

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getId_categoria());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            if (e.getErrorCode() == 1062)
            {
                System.out.println("Error al insertar el libro, isbn duplicado: " + e.getMessage());
            }
            System.out.println("Error al insertar el libro: " + e.getMessage());
            return false;
        }
    }

    // ===================== EDITAR LIBRO =====================
    /**
     * Método que permitirá actualizar un registro en la base de datos.
     * @param libro objeto tipo "Libros" que se require actualizar.
     */
    public boolean actualizar(Libros libro)
    {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, isbn = ?, editorial = ?, stock = ?, id_categoria = ? WHERE id= ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getId_categoria());
            ps.setInt(7, libro.getId());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            if (e.getErrorCode() == 1062)
            {
                System.out.println("Error al insertar el libro, isbn duplicado: " + e.getMessage());
            }
            System.out.println("Error al editar el libro: " + e.getMessage());
            return false;
        }
    }

    // ===================== ELIMINAR LIBRO =====================
    /**
     * Método que permitirá eliminar un libro de la base de datos.
     *
     * @param idLibro "ID" del libro que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idLibro)
    {
        String sql = "DELETE FROM libros WHERE id = ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idLibro);
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            // Detectar error de integridad referencial (FK)
            if (e.getMessage().contains("foreign key constraint fails"))
            {
                throw new RuntimeException("FK_ERROR");
            }
            System.out.println("Error al eliminar el libro: " + e.getMessage());
            return false;
        }
    }

    // ===================== BUSCAR LIBRO POR ID O TITULO =====================
    /**
     * Método que permitirá buscar un libro en la base de datos.
     * @param idLibro "ID" del libro que se requiere buscar.
     * @return un Objeto tipo "Libro" que coincide con el "ID" ingresado
     */
    public Libros buscarPorId(int idLibro)
    {
        // Almacena esta consulta SQL en un String, en donde se busquen todos los libros de la tabla libros, en donde el "id" sea el parámetro ingresado
        String sql = "SELECT * FROM libros WHERE id= ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idLibro);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return new Libros(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getString("editorial"),
                        rs.getInt("stock"),
                        rs.getInt("id_categoria")
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
        return null;
    }

    /**
     * Método que permitirá buscar una categoría en la base de datos.
     * @param autorLibro parámetro que se requiere buscar.
     * @return un Objeto tipo "Libro" que coincide con el string ingresado
     */
    public Libros buscarPorAutor(String autorLibro)
    {
        // Almacena esta consulta SQL en un String, en donde se busquen todos los autores de la tabla libros, en donde el autor sea el parámetro ingresado
        String sql = "SELECT * FROM libros WHERE autor = ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, autorLibro);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return new Libros(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getString("editorial"),
                        rs.getInt("stock"),
                        rs.getInt("id_categoria")
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
        return null;
    }

    // ===================== LISTAR TODOS =====================
    /**
     * Método que permitirá devolver una lista de libros de la base de datos.
     * @return una lista de objetos "Libros" previamente almacenada.
     */
    public List<Libros> listarTodos()
    {
        // Se crea una lista para almacenar los libros de la base de datos
        List<Libros> lista = new ArrayList<>();
        // Almacena esta consulta SQL en un String, en donde se seleccione todas las columnas de la tabla "libros", ordenada por "id".
        String sql = "SELECT * FROM libros ORDER BY id";

        // Intenta conectar a la base de datos, y crea una consulta estática SQL. Además crea un objeto "rs" que almacene los resultados de la consulta en memoria
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // mientras no haya otra línea, crea un objeto categoría con los elementos existentes en la base de datos
            while (rs.next()) {
                Libros libros = new Libros(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getString("editorial"),
                        rs.getInt("stock"),
                        rs.getInt("id_categoria")
                );
                // añade estos elementos a la lista "libros"
                lista.add(libros);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    // ===================== COMPROBAR ISBN LIBRO =====================
    /**
     * Método que verifica si un ISBN ya existe
     * @return "true" si ya existe, "false" si no existe
     */
    public boolean existeISBN(String isbn) {
        String sql = "SELECT COUNT(*) FROM libros WHERE isbn = ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return rs.getInt(1) > 0;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al verificar el libro: " + e.getMessage());
        }
        return false;
    }
}
