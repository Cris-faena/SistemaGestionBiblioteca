package controlador;

import DAO.impl.UsuarioDAOImpl;
import modelo.Usuario;
import util.HashUtil;
import DAO.UsuarioDAO;

import javax.swing.*;
import java.sql.SQLException;

public class ControladorUsuario
{
    private final UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();

    public Usuario login(String correo, String contraseñaPlana)
    {
        return usuarioDAO.autenticar(correo, contraseñaPlana);
    }

    // CREAR
    public boolean crearUsuario(Usuario usuario) throws SQLException
    {
        return usuarioDAO.insertar(usuario);
    }

    public boolean consultarUsuarios(JTextArea area) {return usuarioDAO.cargarConsultaUsuarios(area);}

}
