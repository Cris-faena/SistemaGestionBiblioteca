package DAO.impl;

import DAO.CategoriaDAO;
import modelo.Categoria;
import modelo.CategoriaLibros;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO
{
    // ===================== AGREGAR CATEGORÍA =====================
    /**
     * Método que permitirá agregar una Categoría a la base de datos
     * @param categoria objeto tipo categoria para agregar a la BD
     */
    public boolean insertar(Categoria categoria)
    {
        String sql = """
                INSERT INTO categorias (nombre)
                VALUES (?)
        """;
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, categoria.getNombre().name());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
            return false;
        }
    }

    // ===================== EDITAR CATEGORÍA =====================
    
    /**
     * Método que permitirá actualizar un registro en la base de datos.
     * @param categoria objeto categoría que se require actualizar
     */
    public boolean actualizar(Categoria categoria)
    {
        String sql = "UPDATE categorias SET nombre = ? WHERE id= ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            // Se cambió el valor de los índices
            ps.setString(1, categoria.getNombre().name());
            ps.setInt(2, categoria.getId());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Error al editar categoría: " + e.getMessage());
            return false;
        }
    }

    // ===================== ELIMINAR CATEGORÍA =====================
    /**
     * Método que permitirá eliminar una categoría de la base de datos.
     *
     * @param idCategoria "ID" de la categoría que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idCategoria)
    {
        String sql = "DELETE FROM categorias WHERE id = ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idCategoria);
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

    // ===================== LISTAR TODOS =====================

    public List<Categoria> listarTodos()
    {
        // Se crea una lista para almacenar las categorías de la base de datos
        List<Categoria> lista = new ArrayList<>();
        // Almacena esta consulta SQL en un String, en donde se seleccione todas las columnas de la tabla "categorías", ordenada por "id".
        String sql = "SELECT * FROM categorias ORDER BY id";

        // Intenta conectar a la base de datos, y crea una consulta estática SQL. Además crea un objeto "rs" que almacene los resultados de la consulta en memoria
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // mientras no haya otra línea, crea un objeto categoría con los elementos existentes en la base de datos
            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("id"),
                        CategoriaLibros.valueOf(rs.getString("nombre"))
                );
                // añade estos elementos a la lista "categoría"
                lista.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
            return null;
        }
        return lista;
    }

    // ===================== BUSCAR POR ID O CATEGORÍA =====================
    /**
     * Método que permitirá buscar una categoría en la base de datos.
     * @param idCategoria "ID" de la categoría que se requiere buscar.
     * @return un Objeto tipo categoría que coincide con el "ID" ingresado
     */
    public Categoria buscarPorId(int idCategoria)
    {
        // Almacena esta consulta SQL en un String, en donde se busquen todas las categorías de la tabla categoria, en donde el "id" sea el parámetro ingresado
        String sql = "SELECT * FROM categorias WHERE id= ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idCategoria);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return new Categoria(
                        rs.getInt("id"),
                        CategoriaLibros.valueOf(rs.getString("nombre"))
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar pedido: " + e.getMessage());
        }
        return null;
    }

    /**
     * Método que permitirá buscar una categoría en la base de datos.
     * @param categoria categoría que se requiere buscar.
     * @return un Objeto tipo categoría que coincide con el string ingresado
     */
    public Categoria buscarPorCategoria(String categoria)
    {
        // Almacena esta consulta SQL en un String, en donde se busquen todos las categorías de la tabla categorías, en donde el nombre sea el parámetro ingresado
        String sql = "SELECT * FROM categorias WHERE nombre= ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, categoria);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return new Categoria(
                        rs.getInt("id"),
                        CategoriaLibros.valueOf(rs.getString("nombre"))
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar la categoría: " + e.getMessage());
        }
        return null;
    }
}
