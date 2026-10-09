package DAO;

import modelo.Usuario;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface de UsuarioDAO que permitirá implementar las operaciones CRUD y AUTENTICAR
 */
public interface UsuarioDAO
{
    // ===================== AGREGAR USUARIO =====================
    /**
     * Método que permitirá agregar un Usuario a la base de datos
     * @param usuario objeto tipo Usuario para agregar a la BD
     */
    public boolean insertar(Usuario usuario);

    // ===================== EDITAR USUARIO =====================
    /**
     * Método que permitirá actualizar un registro en la base de datos.
     * @param usuario objeto usuario que se require actualizar
     */
    public boolean actualizar(Usuario usuario);

    // ===================== ELIMINAR USUARIO =====================
    /**
     * Método que permitirá eliminar un usuario de la base de datos.
     *
     * @param idUsuario "ID" del usuario que se requiere eliminar.
     * @return "true" si se logró eliminar, "false" si no lo hizo.
     */
    public boolean eliminar(int idUsuario);

    // ===================== BUSCAR USUARIO POR ID =====================
    /**
     * Método que permitirá buscar un usuario en la base de datos.
     * @param idUsuario "ID" del usuario que se requiere buscar.
     * @return un Objeto tipo usuario que coincide con el "ID" ingresado
     */
    public Usuario buscarPorId(int idUsuario);

    // ===================== LISTAR TODOS LOS USUARIO =====================
    /**
     * Método que permitirá devolver una lista de usuarios de la base de datos.
     * @return una lista de objetos "Usuario" previamente almacenada.
     */
    public List<Usuario> listarTodos();

    // ===================== AUTENTICAR USUARIO =====================
    /**
     * Método que permite autenticar a un usuario que desea acceder a la base de datos
     * @param correo correo electrónico del Usuario que se requiere autenticar.
     * @param contraseñaPlana String que almacena la contraseña ingresada.
     * @return un Usuario previamente autenticado
     */
    public Usuario autenticar(String correo, String contraseñaPlana);

    public boolean cargarConsultaUsuarios(JTextArea textArea);
}
