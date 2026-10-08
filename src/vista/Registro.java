package vista;

import controlador.ControladorUsuario;
import modelo.TipoUsuario;
import modelo.Usuario;
import util.HashUtil;
import controlador.AuthController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Registro extends JFrame
{
    private JPanel PanelPrincipalRegistro;
    private JLabel lblTituloRegistro;
    private JPanel PanelRutRegistro;
    private JLabel lblRutRegistro;
    private JTextField txtRutRegistro;
    private JPanel PanelCorreoRegistro;
    private JLabel lblCorreoRegistro;
    private JTextField txtCorreoRegistro;
    private JPanel PanelContraseñaRegistro;
    private JLabel lblContraseñaRegistro;
    private JTextField txtContraseñaRegistro;
    private JPanel PanelRolRegistro;
    private JLabel lblRolRegistro;
    private JTextField txtRolRegistro;
    private JPanel PAnelBotonesRegistro;
    private JButton btnRegistrarse;
    private JButton btnVolverRegistro;
    private JPanel PanelNombreRegistro;
    private JLabel lblNombreRegistro;
    private JTextField txtNombreRegistro;
    private JPanel PanelRepitaContraseña;
    private JTextField txtRepitaRegistro;
    private JLabel lblRepitaCRegistro;
    private JTextField txtExplicaContraseña;

    private final ControladorUsuario controladorUsuario = new ControladorUsuario();
    private final AuthController authController = new AuthController();

    public Registro()
    {
        setTitle("Gestion de Biblioteca Registro");
        setContentPane(PanelPrincipalRegistro);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Se establecen los colores de fondo para el Panel Principal de Registro.
        PanelPrincipalRegistro.setBackground(new Color(245, 222, 179));
        PanelPrincipalRegistro.setBackground(new Color(245, 222, 179));

        // Se establece el tamaño de las etiquetas.
        lblNombreRegistro.setPreferredSize(new Dimension(100, 30));
        lblRutRegistro.setPreferredSize(new Dimension(100, 30));
        lblCorreoRegistro.setPreferredSize(new Dimension(100, 30));
        lblContraseñaRegistro.setPreferredSize(new Dimension(100, 30));
        lblRepitaCRegistro.setPreferredSize(new Dimension(100, 30));
        lblRolRegistro.setPreferredSize(new Dimension(100, 30));

        // Se establece el tamaño del panel con la explicación de la segunda contraseña
        txtExplicaContraseña.setPreferredSize(new Dimension(100, 30));
        txtExplicaContraseña.setBackground(new Color(245, 222, 179));

        // Se implementan las funcionalidades
        btnRegistrarse.addActionListener(event -> {registrase();});
        btnVolverRegistro.addActionListener(event -> {volver();});
    }

    private void registrase()
    {
        // Se capturan los campos de texto en las variables tipo String correspondiente
        String nombre = txtNombreRegistro.getText().toLowerCase().trim();
        String rut = txtRutRegistro.getText().toLowerCase().trim();
        String correo = txtCorreoRegistro.getText().toLowerCase().trim();
        String contraseña = txtContraseñaRegistro.getText().toLowerCase().trim();
        String repitaContra = txtRepitaRegistro.getText().toLowerCase().trim();
        String rolTxt = txtRolRegistro.getText().trim();

        // Se implementa un manejo de excepciones para cada campo.
        if (nombre.isEmpty() || nombre == null)
        {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (rut.isEmpty() || rut == null)
        {
            JOptionPane.showMessageDialog(this, "El RUT es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (correo.isEmpty() || correo == null)
        {
            JOptionPane.showMessageDialog(this, "El correo es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (contraseña.isEmpty() || contraseña == null)
        {
            JOptionPane.showMessageDialog(this, "La contraseña es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (repitaContra.isEmpty() || repitaContra == null)
        {
            JOptionPane.showMessageDialog(this, "ingresar nuevamente la contraseña es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (rolTxt.isEmpty() || rolTxt == null)
        {
            JOptionPane.showMessageDialog(this, "El rolTxt es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Esto compara ambas contraseñas, si no son iguales, no se puede continuar con el registro
        if (!contraseña.equals(repitaContra))
        {
            JOptionPane.showMessageDialog(this, "Las contraseñas ingresadas no son iguales", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Se implementa un bloque TRY-CATCH para intentar convertir String rolTxt a un ENUM
        TipoUsuario rol;
        try
        {
            rol = TipoUsuario.valueOf(rolTxt.toUpperCase());
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(this, "El rol sólo puede ser: bibliotecario o estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String contraseñaHash = HashUtil.sha256(contraseña);
        // Si se pasó por todos los filtros, intenta esto:
        try
        {
            Usuario u = new Usuario();  // crea una nueva instancia de "Usuario", llamada "u".
            // asigna los valores para "u"
            u.setNombre(nombre);
            u.setRut(rut);
            u.setCorreo(correo);
            u.setContraseña(contraseñaHash);        // inserta la contraseña "hasheada".
            u.setRol(rol);

            // Llama al método que crea o inserta un usuario nuevo en la base de datos.
            controladorUsuario.crearUsuario(u);
            // Abre la pantalla de login:
            Login login = new Login(authController);
            login.setVisible(true);

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que permite cerrar la ventana actual.
     */
    private void volver()
    {
        this.dispose();
    }
}
