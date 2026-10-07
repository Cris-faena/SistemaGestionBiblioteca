package controlador;

import DAO.impl.UsuarioDAOImpl;
import DAO.UsuarioDAO;
import modelo.Usuario;
import vista.Login;

import java.sql.SQLException;

public class AuthController
{
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public Usuario autenticar(String correo, String contraseñaPlana) throws SQLException
    {
        if (correo == null || correo.isBlank())
        {
            throw new IllegalArgumentException("El correo no puede estar vacía");
        }

        if (contraseñaPlana == null || contraseñaPlana.isBlank())
        {
            throw new IllegalArgumentException("La contraseña no puede quedar vacía");
        }
        return usuarioDAO.autenticar(correo, contraseñaPlana);
    }
}
