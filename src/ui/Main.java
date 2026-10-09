package ui;

import modelo.Bibliotecario;
import modelo.Usuario;
import util.ConexionBD;

import java.sql.SQLException;
import controlador.AuthController;
import vista.BibliotecarioMenu;
import vista.EstudianteUsuario;
import vista.Login;

public class Main
{
    private static final AuthController auth = new AuthController();
    public static void main(String[] args) throws SQLException
    {
        ConexionBD.getInstancia().obtenerConexion();

        javax.swing.SwingUtilities.invokeLater(() -> {
            BibliotecarioMenu login = new BibliotecarioMenu();
            login.setVisible(true);
        });
    }
}
