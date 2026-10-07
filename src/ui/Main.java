package ui;

import util.ConexionBD;

import java.sql.SQLException;
import controlador.AuthController;
import vista.Bibliotecario;
import vista.Login;

public class Main
{
    private static final AuthController auth = new AuthController();
    public static void main(String[] args) throws SQLException
    {
        ConexionBD.getInstancia().obtenerConexion();

        javax.swing.SwingUtilities.invokeLater(() -> {
            Bibliotecario login = new Bibliotecario();
            login.setVisible(true);
        });
    }
}
