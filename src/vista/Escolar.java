package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Escolar extends JFrame
{
    private JPanel PanelEscolar;
    // Componentes del formulario
    private JTextField txtNombre;
    private JTextField txtRut;
    private JTextField txtCurso;
    private JTextField txtCorreo;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    // Tabla de libros prestados
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;

    public Escolar() {
        initComponents();
        configurarVentana();
    }

    private void initComponents() {
        PanelEscolar = new JPanel(new BorderLayout(15, 15));
        PanelEscolar.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        PanelEscolar.setBackground(new Color(245, 247, 250));

        // ==================== TÍTULO ====================
        JLabel lblTitulo = new JLabel("Bienvenidos estudiantes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        PanelEscolar.add(lblTitulo, BorderLayout.NORTH);

        // ==================== PANEL CENTRAL ====================
        JPanel panelCentral = new JPanel(new BorderLayout(15, 15));
        panelCentral.setOpaque(false);

        // ---------- FORMULARIO (izquierda) ----------
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(189, 195, 199)),
                        "  Registro de Estudiante  ",
                        0, 0,
                        new Font("Segoe UI", Font.BOLD, 14),
                        new Color(52, 73, 94)
                ),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(crearLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtNombre = crearTextField();
        panelFormulario.add(txtNombre, gbc);

        // RUT
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelFormulario.add(crearLabel("RUT:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtRut = crearTextField();
        panelFormulario.add(txtRut, gbc);

        // Curso
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelFormulario.add(crearLabel("Curso:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtCurso = crearTextField();
        panelFormulario.add(txtCurso, gbc);

        // Correo
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelFormulario.add(crearLabel("Correo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtCorreo = crearTextField();
        panelFormulario.add(txtCorreo, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setOpaque(false);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setPreferredSize(new Dimension(120, 35));
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnLimpiar.setBackground(new Color(149, 165, 166));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setPreferredSize(new Dimension(120, 35));
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 8, 8, 8);
        panelFormulario.add(panelBotones, gbc);

        // ---------- TABLA DE LIBROS PRESTADOS (derecha) ----------
        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(189, 195, 199)),
                        "  Libros Prestados  ",
                        0, 0,
                        new Font("Segoe UI", Font.BOLD, 14),
                        new Color(52, 73, 94)
                ),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        String[] columnas = {"ID", "Título", "Autor", "Fecha Préstamo", "Fecha Devolución"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Solo lectura
            }
        };

        tablaLibros = new JTable(modeloTabla);
        tablaLibros.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaLibros.setRowHeight(28);
        tablaLibros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaLibros.getTableHeader().setBackground(new Color(52, 73, 94));
        tablaLibros.getTableHeader().setForeground(Color.WHITE);
        tablaLibros.setSelectionBackground(new Color(52, 152, 219));
        tablaLibros.setGridColor(new Color(220, 220, 220));
        tablaLibros.setShowGrid(true);

        // Ancho de columnas
        tablaLibros.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaLibros.getColumnModel().getColumn(1).setPreferredWidth(180);
        tablaLibros.getColumnModel().getColumn(2).setPreferredWidth(140);
        tablaLibros.getColumnModel().getColumn(3).setPreferredWidth(110);
        tablaLibros.getColumnModel().getColumn(4).setPreferredWidth(110);

        JScrollPane scrollTabla = new JScrollPane(tablaLibros);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder());
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // Agregar formulario y tabla al panel central
        panelCentral.add(panelFormulario, BorderLayout.WEST);
        panelCentral.add(panelTabla, BorderLayout.CENTER);

        PanelEscolar.add(panelCentral, BorderLayout.CENTER);

        // ==================== EVENTOS ====================
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        btnRegistrar.addActionListener(e -> {
            // Aquí conectarás con tu controlador / DAO
            JOptionPane.showMessageDialog(this,
                    "Estudiante registrado correctamente (conectar con DAO)",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        });
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setPreferredSize(new Dimension(70, 25));
        return label;
    }

    private JTextField crearTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setPreferredSize(new Dimension(200, 28));
        return txt;
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtRut.setText("");
        txtCurso.setText("");
        txtCorreo.setText("");
        txtNombre.requestFocus();
    }

    /** Método para cargar los libros prestados del estudiante logueado */
    public void cargarLibrosPrestados(Object[][] datos) {
        modeloTabla.setRowCount(0); // Limpia la tabla
        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }

    private void configurarVentana() {
        setTitle("Gestión de Biblioteca - Escolar");
        setContentPane(PanelEscolar);
        setSize(1050, 600);
        setMinimumSize(new Dimension(900, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ========== MAIN DE PRUEBA ==========
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Escolar ventana = new Escolar();

            // Datos de ejemplo para la tabla (borrar cuando conectes la BD)
            Object[][] ejemplo = {
                    {1, "Cien años de soledad", "G. García Márquez", "2025-09-10", "2025-09-24"},
                    {2, "El Principito", "Antoine de Saint-Exupéry", "2025-09-15", "2025-09-29"},
                    {3, "Don Quijote de la Mancha", "Miguel de Cervantes", "2025-09-20", "2025-10-04"}
            };
            ventana.cargarLibrosPrestados(ejemplo);
        });
    }
}