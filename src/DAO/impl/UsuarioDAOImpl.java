package DAO.impl;

import DAO.UsuarioDAO;
import modelo.TipoUsuario;
import modelo.Usuario;
import util.ConexionBD;
import util.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO
{
    /**
     * Método que devuelve un "Usuario" autenticado, encontrado en la BD.
     * @param correo correo del usuario que se desea autenticar.
     * @param contraseñaPlana contraseña plana ingresada en el Login
     * @return objeto tipo Usuario retornado desde la base de datos.

     */
    public Usuario autenticar(String correo, String contraseñaPlana)
    {
        String sql = """
                SELECT id, nombre, rut, correo, contraseña, rol
                FROM usuarios
                WHERE correo = ?
                """;

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, correo);

            ResultSet rs = ps.executeQuery();

            // 1. Si no existe el correo → no hay usuario
            if (!rs.next())
            {
                return null;
            }

            // 2. Obtener el hash almacenado
            String hashAlmacenado = rs.getString("contraseña");

            // 3. Se "hashea" la contraseña plana
            String hashIngresado = HashUtil.sha256(contraseñaPlana);

            // ========== DEBUG ==========
            System.out.println("Correo buscado     : [" + correo + "]");
            System.out.println("Contraseña plana   : [" + contraseñaPlana + "]");
            System.out.println("Hash almacenado    : [" + hashAlmacenado + "]");
            System.out.println("Hash ingresado     : [" + hashIngresado + "]");
            System.out.println("¿Son iguales?      : " + hashAlmacenado.equals(hashIngresado));
            // ===========================

            // 4. Comparar hashes
            if (!hashAlmacenado.equals(hashIngresado))
            {
                return null; // contraseña incorrecta
            }

            // 4. Construir el objeto Usuario
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setNombre(rs.getString("nombre"));
            u.setRut(rs.getString("rut"));
            u.setCorreo(rs.getString("correo"));
            u.setContraseña(hashAlmacenado);
            u.setRol(TipoUsuario.valueOf(rs.getString("rol").toUpperCase()));

            return u;
        }
        catch (SQLException ex)
        {
            System.out.println("Error al autenticar: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Método que permitirá agregar un Usuario a la base de datos
     * @param usuario objeto tipo Usuario para agregar a la BD
     * @throws SQLException excepción que se lanza en caso de fallas.
     */
    public boolean insertar(Usuario usuario)
    {
        String sql = """
                INSERT INTO usuarios (nombre,rut,correo,contraseña,rol)
                VALUES (?,?,?,?,?)
        """;
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getRut());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4,usuario.getContraseña());
            ps.setString(5,usuario.getRol().name());

            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método que permitirá actualizar un usuario en la base de datos.
     * @param usuario objeto usuario que se require actualizar
     * @throws SQLException excepción que se lanza en caso de fallas.
     */
    public boolean actualizar(Usuario usuario)
    {
        String sql = "UPDATE usuarios SET nombre = ? WHERE id= ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getId());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Error al editar al usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método que permitirá eliminar un usuario de la base de datos.
     *
     * @param idUsuario "ID" del usuario que se requiere eliminar.
     * @return
     * @throws SQLException excepción que se lanza en caso de fallas.
     */
    public boolean eliminar(int idUsuario)
    {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            // Detectar error de integridad referencial (FK)
            if (e.getMessage().contains("foreign key constraint fails"))
            {
                throw new RuntimeException("FK_ERROR");
            }
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método que permitirá buscar un usuario en la base de datos.
     * @param idUsuario "ID" del usuario que se requiere buscar.
     * @return un Objeto tipo usuario que coincide con el "ID" ingresado
     * @throws SQLException excepción que se lanza en caso de fallas.
     */
    public Usuario buscarPorId(int idUsuario)
    {
        // Almacena esta consulta SQL en un String, en donde se busquen todos los usuarios de la tabla usuarios, en donde el "id" sea el parámetro ingresado
        String sql = "SELECT * FROM usuarios WHERE id= ?";
        // Intenta conectar a la base de datos y almacena la consulta SQL en un objeto "ps" pre-compilado
        try (Connection conn = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idUsuario);
            // Ejecuta la consulta SQL en la base de datos
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        TipoUsuario.valueOf(rs.getString("rol").toUpperCase())
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al buscar el usuario: " + e.getMessage());
        }
        return null;
    }

    /**
     * Método que permitirá devolver una lista de usuarios de la base de datos.
     * @return una lista de objetos "Usuario" previamente almacenada.
     */
    public List<Usuario> listarTodos()
    {
        return null;
    }
}
