package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que representa una conexión hacia la base de datos.
 * Requiere designar URL, USUARIO y CONTRASEÑA para poder conectar.
 * El nombre de usuario y la contraseña se almacenan como variables de entorno.
 */
public final class ConexionBD
{
    // Atributo de la clase Conexión BD que representa la instancia actual de conexión
    private static final ConexionBD INSTANCIA = new ConexionBD();
    // String que contiene la URL para conectarse a la BD "biblioteca".
    private static final String URL =
            "jdbc:mysql://localhost:3306/biblioteca"
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=UTC"
                    + "&characterEncoding=UTF-8";

    // Constructor sin parámetros de la clase Conexión:
    private ConexionBD() {}

    // Método que devuelve la instancia de la Clase conexión que se está utilizando.
    public static ConexionBD getInstancia() {return INSTANCIA;}

    /**
     * Método que devuelve una conexión JBDC hacia la base de datos.
     * @return una conexión con la base de datos.
     * @throws SQLException excepción que se lanza en caso de no poder conectar a la BD.
     */
    public Connection obtenerConexion() throws SQLException
    {
        String user = System.getenv().getOrDefault("MYSQL_USER", "root");
        String password = System.getenv("MYSQL_PASSWORD");
        if (password == null)
        {
            throw new SQLException("No se encontró MYSQL_PASSWORD.");
        }
        System.out.println("Se ha conectado con éxito a la base de datos...");
        return DriverManager.getConnection(URL, user, password);
    }
}
