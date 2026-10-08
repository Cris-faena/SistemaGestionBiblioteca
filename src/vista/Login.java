package vista;

import controlador.AuthController;
import modelo.TipoUsuario;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Login extends JFrame
{
    private JPanel PanelPrincipal;
    private JPanel PanelBotonesLogin;
    private JLabel lblTituloUsuario;
    private JTextField txtNombreUsuario;
    private JButton btnIngresarLogin;
    private JButton btnCancelarLogin;
    private JPasswordField txtPasswordFieldLogin;
    private JLabel lblContraseñaLogin;
    private JLabel lblCorreoLogin;
    private JPanel PanelTituloLogin;
    private JPanel PanelUsuariosLogin;
    private JLabel lblRegistroLabel;

    // Se crea una instancia del controlador "AuthController"
    private final AuthController authController = new AuthController();

    public Login(AuthController authController)
    {
        setTitle("Gestion de Biblioteca Login");
        setContentPane(PanelPrincipal);
        setSize(430, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PanelPrincipal.setBackground(new Color(245, 222, 179));
        PanelBotonesLogin.setBackground(new Color(245, 222, 179));

        btnIngresarLogin.addActionListener(event -> {ingresar();});
        btnCancelarLogin.addActionListener(event -> {cancelar();});

        // Se añade funcionalidad al texto que permite registrarse en el formulario:
        lblRegistroLabel.setForeground(Color.BLUE);                 // Se asigna el color azul al texto
        lblRegistroLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // transforma el curso en una mano, cuando se posiciona en el texto.
        // Aplica el efecto al texto
        lblRegistroLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                lblRegistroLabel.setText("<html><u>¿No tienes cuenta? Regístrate aquí</u></html>");
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
                lblRegistroLabel.setText("¿No tienes cuenta? Regístrate aquí");
            }
        });
        // Le da funcionalidad al texto tras presionar con el botón del mouse.
        // Esto despliega el formulario de registro:
        lblRegistroLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Abre la pantalla de registro:
                Registro registro = new Registro();
                registro.setVisible(true);
            }
        });
    }

    /**
     * Método para autenticar a un usuario que pretende acceder al sistema.
     * Al presionar el botón Ingresar, llama a la lógica de autenticación
     */
    private void ingresar()
    {
        // Se capturan los campos ingresados por el usuario
        String correo = txtNombreUsuario.getText();                // captura el usuario
        String contraseñaPlana = new String (txtPasswordFieldLogin.getPassword()).trim();   // captura la contraseña

        // Se implementa un manejo de excepciones en caso de que los campos se encuentren vacíos
        if (correo == null || correo.isBlank())
        {
            JOptionPane.showMessageDialog(null, "Ingrese un correo para ingresar");
            return;
        }

        if (contraseñaPlana == null || contraseñaPlana.isBlank())
        {
            JOptionPane.showMessageDialog(this, "Debe ingresar una contraseña para ingresar");
            return;
        }
        // Si se cumplen los requisitos, ejecuta este bloque TRY_CATCH
        try
        {
            // crea un usuario u y llama al método "autenticar" para comprobar que sea válido
            Usuario u = authController.autenticar(correo, contraseñaPlana);
            // Si el usuario es null, lanza este cuadro emergente
            if (u == null)
            {
                JOptionPane.showMessageDialog(this, "Credenciales inválidas o usuario inactivo");
                return;
            }
            String rol = u.getRol().name();
            if ("ESTUDIANTE".equals(rol))
            {
                Escolar escolar = new Escolar();
                escolar.setVisible(true);
                this.dispose();

            }
            else if ("BIBLIOTECARIO".equals(rol))
            {
                Bibliotecario bibliotecario = new Bibliotecario();
                bibliotecario.setVisible(true);
                this.dispose();

            }
            else
            {
                JOptionPane.showMessageDialog(this, "Rol no reconocido: " + rol);
            }

        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que cierra la aplicación si el usuario presiona el botón cancelar
     */
    private  void cancelar()
    {
        System.exit(0);
    }
}
