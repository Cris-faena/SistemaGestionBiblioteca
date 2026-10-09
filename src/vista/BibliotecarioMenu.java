package vista;

import controlador.*;
import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BibliotecarioMenu extends JFrame {
    private JPanel PanelPrincipalBiblio;
    private JTextField txtTituloLibros;
    private JTextField txtCategoriaSub;
    private JLabel lblCategoriaSub;
    private JButton btnAgregarCategoria;
    private JTextField txtAutor;
    private JButton btnEditarCategoria;
    private JButton btnEliminarCategoria;
    private JButton btnLimpiarCategoria;
    private JTextField txtIsbn;
    private JTextField txtEditorial;
    private JTextField txtStock;
    private JLabel lblFiltrarcategoria;
    private JComboBox jcbCategorias;
    private JTextField txtFiltrarCat;
    private JButton btnFiltrar;
    private JButton btnListarCategorias;
    private JButton btnAgregarLibros;
    private JButton btnEditarLibros;
    private JButton btnEliminarLibros;
    private JButton btnLimpiarLibros;
    private JPanel PanelSubLibros;
    private JScrollPane jspTablaCategoria;
    private JTable tblCategoria;
    private JComboBox jcbLibros;
    private JTextField txtFiltrarLibros;
    private JButton btnFiltrarLibro;
    private JButton btnListarLibros;
    private JScrollPane jspSubLibros;
    private JTable tblLibros;
    private JPanel PanelSubCategorias;
    private JTextField txtIdCategoriaLibro;
    private JButton btnMostrarCategorias;
    private JButton btnFiltrarCategoria;
    private JLabel lblTituloSubLibros;
    private JLabel lblAutor;
    private JLabel lblISBN;
    private JLabel lblEditorial;
    private JLabel lblStock;
    private JLabel lblFiltrarLibro;
    private JLabel lblIDCategoriaLibro;
    private JTabbedPane JTabbedCategorias;
    private JPanel PanelBotonesLibros;
    private JPanel PanelFiltrosLibro;
    private JPanel PanelTextosLibros;
    private JPanel PanelTextosCategoria;
    private JPanel PanelFiltrosCategoria;
    private JPanel PanelBotonMostrarCategoria;
    private JPanel PanelEstudianteTextos;
    private JPanel lblNombreEstudiante;
    private JTextField txtNombreEstudiante;
    private JLabel lblRutEstudiante;
    private JTextField txtRutEstudiante;
    private JLabel lblCursoEstudiante;
    private JTextField txtCursoEstudiante;
    private JLabel lblCorreoEstudiante;
    private JPanel PanelBotonesEstudiante;
    private JButton btnAgregarEstudiante;
    private JButton btnEditarEstudiante;
    private JButton btnEliminarEstudiante;
    private JButton btnLimpiarEstudiante;
    private JPanel PanelFiltrosEstudiante;
    private JComboBox jcbEstudiante;
    private JTextField txtEstudiantejcb;
    private JButton btnFiltrarEstudiante;
    private JButton btnListarEstudiante;
    private JLabel lblEstudiante;
    private JTextField txtCorreo;
    private JTable tblEstudiante;
    private JScrollPane jspEstudiante;
    private JPanel PaneltxtEstudiantes;
    private JLabel lblId_EstudiantePrestamo;
    private JTextField txtIdEstudiantePrestamo;
    private JLabel lblIdLibroPrestamo;
    private JTextField txtIdLibroPrestamo;
    private JLabel lblFechaPrestamo;
    private JTextField txtFechaPrestamo;
    private JLabel lblDevuelvoPrestamo;
    private JPanel PanelTextosPrestamos;
    private JComboBox jcbDevuelto;
    private JPanel PanelBotonesPrestamos;
    private JButton btnAgregarPrestamos;
    private JButton btnEditarPrestamos;
    private JButton btnEliminarPrestamo;
    private JButton btnLimpiarPrestamos;
    private JPanel PanelFiltrosPrestamos;
    private JLabel lblFiltrarPrestamos;
    private JComboBox jcbFiltrarPrestamos;
    private JTextField txtFiltroPrestamos;
    private JButton btnFiltrarPrestamos;
    private JButton btnListarPrestamos;
    private JPanel PanelTramitarPrestamos;
    private JTextField txtTramitarPorId;
    private JLabel lblTramitarPrestamos;
    private JButton btnEjecutarPrestamo;
    private JScrollPane jspTablaPrestamos;
    private JScrollPane jspParaArea;
    private JTable tblPrestamos;
    private JTextArea jtaAreaPrestamos;
    private JLabel lblFechaDevolucionPrestamo;
    private JTextField txtFechaDevolucionPrestamo;
    private JButton btnLimpiarAREA;
    private JPanel PanelReportesPrincipal;
    private JScrollPane jspTablaFiltrosReportes;
    private JTextArea jtaResumen;
    private JButton btnMostrar1;
    private JButton btnMostar2;
    private JButton btnMostrar3;
    private JTextArea jtaMostrarInformacion;
    private JButton btnQuitar1;
    private JButton btnQuitar2;
    private JButton btnQuitar3;
    private JPanel PanelJAREA;
    private JPanel PanelMitarIzquierda;
    private JPanel PanelMitarDerecha;

    private int idCategoriaSeleccionado = -1;
    private int idLibroSeleccionado = -1;
    private int idEstudianteSeleccionado = -1;
    private int idPrestamoSeleccionado = -1;

    private DefaultTableModel modeloTablaCategoria;
    private DefaultTableModel modeloTablaLibros;
    private DefaultTableModel modeloTablaEstudiante;
    private DefaultTableModel modeloTablaPrestamos;

    private final ControladorCategoria controladorCategoria = new ControladorCategoria();
    private final ControladorLibros controladorLibros = new ControladorLibros();
    private final ControladorEstudiante controladorEstudiante = new ControladorEstudiante();
    private final ControladorPrestamo controladorPrestamo = new ControladorPrestamo();
    private final ControladorBibliotecario controladorBibliotecario = new ControladorBibliotecario();
    private final ControladorUsuario controladorUsuario = new ControladorUsuario();

    public BibliotecarioMenu() {
        setTitle("BibliotecarioMenu");
        setContentPane(PanelPrincipalBiblio);
        setSize(430, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        PanelPrincipalBiblio.setBackground(new Color(245, 222, 179));

        PanelSubCategorias.setBackground(new Color(250, 240, 230));
        PanelSubLibros.setBackground(new Color(250, 240, 230));

        PanelBotonesLibros.setBackground(new Color(250, 240, 230));
        PanelTextosLibros.setBackground(new Color(250, 240, 230));
        PanelFiltrosLibro.setBackground(new Color(173, 216, 230));

        PanelTextosCategoria.setBackground(new Color(250, 240, 230));
        PanelBotonMostrarCategoria.setBackground(new Color(250, 240, 230));
        PanelFiltrosCategoria.setBackground(new Color(173, 216, 230));

        PaneltxtEstudiantes.setBackground(new Color(250, 240, 230));
        PanelBotonesEstudiante.setBackground(new Color(250, 240, 230));
        PanelFiltrosEstudiante.setBackground(new Color(173, 216, 230));

        PanelTextosPrestamos.setBackground(new Color(250, 240, 230));
        PanelBotonesPrestamos.setBackground(new Color(250, 240, 230));
        PanelFiltrosPrestamos.setBackground(new Color(173, 216, 230));
        PanelTramitarPrestamos.setBackground(new Color(255, 220, 220));

        PanelJAREA.setBackground(new Color(250, 240, 230));
        PanelMitarIzquierda.setBackground(new Color(250, 240, 230));
        PanelMitarDerecha.setBackground(new Color(250, 240, 230));
        PanelReportesPrincipal.setBackground(new Color(245, 222, 179));

        // Se añaden funcionalidades a la JTextArea
        jtaAreaPrestamos.setEditable(false);
        jtaAreaPrestamos.setWrapStyleWord(true);
        jtaAreaPrestamos.setLineWrap(true);
        jtaAreaPrestamos.setBackground(new Color(255, 255, 230)); // Se añade color.
        jtaAreaPrestamos.setFont(new Font("Arial", Font.BOLD, 18));

        jtaResumen.setEditable(false);
        jtaResumen.setWrapStyleWord(true);
        jtaResumen.setLineWrap(true);
        jtaResumen.setBackground(new Color(255, 255, 230));
        jtaResumen.setFont(new Font("Arial", Font.PLAIN, 14));

        jspTablaCategoria.setViewportView(tblCategoria);
        jspTablaCategoria.setPreferredSize(new Dimension(200, 300));
        jspTablaCategoria.setMaximumSize(new Dimension(200, 300));
        jspTablaCategoria.setMinimumSize(new Dimension(200, 300));

        jspSubLibros.setViewportView(tblLibros);
        jspSubLibros.setPreferredSize(new Dimension(760, 300));
        jspSubLibros.setMaximumSize(new Dimension(770, 300));
        jspSubLibros.setMinimumSize(new Dimension(760, 300));

        jspTablaPrestamos.setViewportView(tblPrestamos);

        txtTituloLibros.setMaximumSize(new Dimension(120, 30));
        txtTituloLibros.setMinimumSize(new Dimension(120, 30));
        txtTituloLibros.setPreferredSize(new Dimension(120, 30));

        txtAutor.setMaximumSize(new Dimension(120, 30));
        txtAutor.setMinimumSize(new Dimension(120, 30));
        txtAutor.setPreferredSize(new Dimension(120, 30));

        txtIsbn.setMaximumSize(new Dimension(120, 30));
        txtIsbn.setMinimumSize(new Dimension(120, 30));
        txtIsbn.setPreferredSize(new Dimension(120, 30));

        txtEditorial.setMaximumSize(new Dimension(120, 30));
        txtEditorial.setMinimumSize(new Dimension(120, 30));
        txtEditorial.setPreferredSize(new Dimension(120, 30));

        txtStock.setMaximumSize(new Dimension(50, 30));
        txtStock.setMinimumSize(new Dimension(50, 30));
        txtStock.setPreferredSize(new Dimension(50, 30));

        txtIdCategoriaLibro.setMaximumSize(new Dimension(50, 30));
        txtIdCategoriaLibro.setMinimumSize(new Dimension(50, 30));
        txtIdCategoriaLibro.setPreferredSize(new Dimension(50, 30));

        txtIdEstudiantePrestamo.setMaximumSize(new Dimension(50, 30));
        txtIdEstudiantePrestamo.setMinimumSize(new Dimension(50, 30));
        txtIdEstudiantePrestamo.setPreferredSize(new Dimension(50, 30));

        txtIdLibroPrestamo.setMinimumSize(new Dimension(50, 30));
        txtIdLibroPrestamo.setMaximumSize(new Dimension(50, 30));
        txtIdLibroPrestamo.setPreferredSize(new Dimension(50, 30));

        txtFechaPrestamo.setMaximumSize(new Dimension(100, 30));
        txtFechaPrestamo.setMinimumSize(new Dimension(100, 30));
        txtFechaPrestamo.setPreferredSize(new Dimension(100, 30));

        txtFechaDevolucionPrestamo.setMaximumSize(new Dimension(100, 30));
        txtFechaDevolucionPrestamo.setMinimumSize(new Dimension(100, 30));
        txtFechaDevolucionPrestamo.setPreferredSize(new Dimension(100, 30));

        txtFiltrarLibros.setMaximumSize(new Dimension(170, 30));
        txtFiltrarLibros.setMinimumSize(new Dimension(170, 30));
        txtFiltrarLibros.setPreferredSize(new Dimension(170, 30));

        txtCategoriaSub.setMaximumSize(new Dimension(170, 30));
        txtCategoriaSub.setMinimumSize(new Dimension(170, 30));
        txtCategoriaSub.setPreferredSize(new Dimension(170, 30));

        txtFiltrarCat.setMaximumSize(new Dimension(170, 30));
        txtFiltrarCat.setMinimumSize(new Dimension(170, 30));
        txtFiltrarCat.setPreferredSize(new Dimension(170, 30));

        txtNombreEstudiante.setMaximumSize(new Dimension(170, 30));
        txtNombreEstudiante.setMinimumSize(new Dimension(170, 30));
        txtNombreEstudiante.setPreferredSize(new Dimension(170, 30));

        txtRutEstudiante.setMaximumSize(new Dimension(170, 30));
        txtRutEstudiante.setMinimumSize(new Dimension(170, 30));
        txtRutEstudiante.setPreferredSize(new Dimension(170, 30));

        txtCursoEstudiante.setMaximumSize(new Dimension(100, 30));
        txtCursoEstudiante.setMinimumSize(new Dimension(100, 30));
        txtCursoEstudiante.setPreferredSize(new Dimension(100, 30));

        txtCorreo.setMaximumSize(new Dimension(170, 30));
        txtCorreo.setMinimumSize(new Dimension(170, 30));
        txtCorreo.setPreferredSize(new Dimension(170, 30));

        txtEstudiantejcb.setMaximumSize(new Dimension(170, 30));
        txtEstudiantejcb.setMinimumSize(new Dimension(170, 30));
        txtEstudiantejcb.setPreferredSize(new Dimension(170, 30));

        txtFiltroPrestamos.setMaximumSize(new Dimension(170, 30));
        txtFiltroPrestamos.setMinimumSize(new Dimension(170, 30));
        txtFiltroPrestamos.setPreferredSize(new Dimension(170, 30));
        txtTramitarPorId.setMaximumSize(new Dimension(170, 30));
        txtTramitarPorId.setMinimumSize(new Dimension(170, 30));
        txtTramitarPorId.setPreferredSize(new Dimension(170, 30));

        // Se edita el tamaño de los botones
        btnAgregarLibros.setMaximumSize(new Dimension(100, 30));
        btnAgregarLibros.setMinimumSize(new Dimension(100, 30));
        btnAgregarLibros.setPreferredSize(new Dimension(100, 30));
        btnEditarLibros.setMaximumSize(new Dimension(100, 30));
        btnEditarLibros.setMinimumSize(new Dimension(100, 30));
        btnEditarLibros.setPreferredSize(new Dimension(100, 30));
        btnEliminarLibros.setMaximumSize(new Dimension(100, 30));
        btnEliminarLibros.setMinimumSize(new Dimension(100, 30));
        btnEliminarLibros.setPreferredSize(new Dimension(100, 30));
        btnLimpiarLibros.setMaximumSize(new Dimension(100, 30));
        btnLimpiarLibros.setMinimumSize(new Dimension(100, 30));
        btnLimpiarLibros.setPreferredSize(new Dimension(100, 30));

        btnAgregarEstudiante.setMaximumSize(new Dimension(100, 30));
        btnAgregarEstudiante.setMinimumSize(new Dimension(100, 30));
        btnAgregarEstudiante.setPreferredSize(new Dimension(100, 30));
        btnEditarEstudiante.setMaximumSize(new Dimension(100, 30));
        btnEditarEstudiante.setMinimumSize(new Dimension(100, 30));
        btnEditarEstudiante.setPreferredSize(new Dimension(100, 30));
        btnEliminarEstudiante.setMaximumSize(new Dimension(100, 30));
        btnEliminarEstudiante.setPreferredSize(new Dimension(100, 30));
        btnEliminarEstudiante.setMinimumSize(new Dimension(100, 30));
        btnLimpiarEstudiante.setMaximumSize(new Dimension(100, 30));
        btnLimpiarEstudiante.setPreferredSize(new Dimension(100, 30));
        btnLimpiarEstudiante.setMinimumSize(new Dimension(100, 30));
        btnFiltrarEstudiante.setMaximumSize(new Dimension(100, 30));
        btnFiltrarEstudiante.setMinimumSize(new Dimension(100, 30));
        btnFiltrarEstudiante.setPreferredSize(new Dimension(100, 30));
        btnListarEstudiante.setMaximumSize(new Dimension(100, 30));
        btnListarEstudiante.setMinimumSize(new Dimension(100, 30));
        btnListarEstudiante.setPreferredSize(new Dimension(100, 30));

        btnAgregarPrestamos.setMaximumSize(new Dimension(100, 30));
        btnAgregarPrestamos.setMinimumSize(new Dimension(100, 30));
        btnAgregarPrestamos.setPreferredSize(new Dimension(100, 30));
        btnEditarPrestamos.setMaximumSize(new Dimension(100, 30));
        btnEditarPrestamos.setMinimumSize(new Dimension(100, 30));
        btnEditarPrestamos.setPreferredSize(new Dimension(100, 30));
        btnEliminarPrestamo.setMaximumSize(new Dimension(100, 30));
        btnEliminarPrestamo.setMinimumSize(new Dimension(100, 30));
        btnEliminarPrestamo.setPreferredSize(new Dimension(100, 30));
        btnLimpiarPrestamos.setMaximumSize(new Dimension(100, 30));
        btnLimpiarPrestamos.setMinimumSize(new Dimension(100, 30));
        btnLimpiarPrestamos.setPreferredSize(new Dimension(100, 30));
        btnFiltrarPrestamos.setMaximumSize(new Dimension(100, 30));
        btnFiltrarPrestamos.setMinimumSize(new Dimension(100, 30));
        btnFiltrarPrestamos.setPreferredSize(new Dimension(100, 30));
        btnListarPrestamos.setMaximumSize(new Dimension(100, 30));
        btnListarPrestamos.setMinimumSize(new Dimension(100, 30));
        btnListarPrestamos.setPreferredSize(new Dimension(100, 30));
        btnEjecutarPrestamo.setMaximumSize(new Dimension(100, 30));
        btnEjecutarPrestamo.setMinimumSize(new Dimension(100, 30));
        btnEjecutarPrestamo.setPreferredSize(new Dimension(100, 30));


        // Se edita el tamaño de los JComboBox
        jcbLibros.setMaximumSize(new Dimension(100, 30));
        jcbLibros.setMinimumSize(new Dimension(100, 30));
        jcbLibros.setPreferredSize(new Dimension(100, 30));

        jcbCategorias.setMaximumSize(new Dimension(100, 30));
        jcbCategorias.setMinimumSize(new Dimension(100, 30));
        jcbCategorias.setPreferredSize(new Dimension(100, 30));

        jcbEstudiante.setMaximumSize(new Dimension(100, 30));
        jcbEstudiante.setMinimumSize(new Dimension(100, 30));
        jcbEstudiante.setPreferredSize(new Dimension(100, 30));

        jcbDevuelto.setMaximumSize(new Dimension(100, 30));
        jcbDevuelto.setMinimumSize(new Dimension(100, 30));
        jcbDevuelto.setPreferredSize(new Dimension(100, 30));

        jcbFiltrarPrestamos.setMaximumSize(new Dimension(100, 30));
        jcbFiltrarPrestamos.setMinimumSize(new Dimension(100, 30));
        jcbFiltrarPrestamos.setPreferredSize(new Dimension(100, 30));

        // Se agrega funcionalidades a los botones del panel de ingreso de categorías
        btnAgregarCategoria.addActionListener(event -> {
            agregarCategoria();
        });
        btnEditarCategoria.addActionListener(event -> {
            editarCategoria();
        });
        btnEliminarCategoria.addActionListener(event -> {
            eliminarCategoria();
        });
        btnLimpiarCategoria.addActionListener(event -> {
            limpiarCategoria();
        });
        btnListarCategorias.addActionListener(event -> {
            listarCategoria();
        });
        btnFiltrarCategoria.addActionListener(event -> {
            filtrarCategoria();
        });
        btnMostrarCategorias.addActionListener(event -> {
            String lista = String.join("\n",
                    Arrays.stream(CategoriaLibros.values())
                            .map(Enum::name)
                            .toArray(String[]::new)
            );

            // Mostrar cuadro emergente con la lista
            JOptionPane.showMessageDialog(
                    null,
                    "Valores disponibles:\n\n" + lista,
                    "ENUM disponibles",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // Se agregan funcionalidades a los botones del panel de registro de libros
        btnAgregarLibros.addActionListener(event -> {
            agregarLibro();
        });
        btnEditarLibros.addActionListener(event -> {
            editarLibro();
        });
        btnEliminarLibros.addActionListener(event -> {
            eliminarLibro();
        });
        btnLimpiarLibros.addActionListener(event -> {
            limpiarLibro();
        });
        btnListarLibros.addActionListener(event -> {
            listarLibro();
        });
        btnFiltrarLibro.addActionListener(event -> {
            filtrarLibros();
        });

        btnAgregarEstudiante.addActionListener(event -> {
            agregarEstudiante();
        });

        btnEditarEstudiante.addActionListener(event -> {
            editarEstudiante();
        });
        btnEliminarEstudiante.addActionListener(event -> {
            eliminarEstudiante();
        });
        btnLimpiarEstudiante.addActionListener(event -> {
            limpiarEstudiante();
        });

        btnListarEstudiante.addActionListener(event -> {
            listarEstudiante();
        });

        btnFiltrarEstudiante.addActionListener(event -> {
            filtrarEstudiante();
        });

        btnAgregarPrestamos.addActionListener(event -> {
            agregarPrestamo();
        });

        btnEditarPrestamos.addActionListener(event -> {editarPrestamo();});
        btnEliminarPrestamo.addActionListener(event -> {eliminarPrestamo();});
        btnLimpiarPrestamos.addActionListener(event -> {limpiarPrestamo();});
        btnListarPrestamos.addActionListener(event -> {listarPrestamos();});
        btnFiltrarPrestamos.addActionListener(event -> {filtrarPrestamo();});
        btnEjecutarPrestamo.addActionListener(event -> {ejecutarLosHilos();});
        btnLimpiarAREA.addActionListener(event ->{limpiarAreaTexto();});

        btnMostrar1.addActionListener(event ->{mostrarNombresFiltrados();});
        btnMostar2.addActionListener(event ->{mostrarLibrosFiltrados();});
        btnMostrar3.addActionListener(event ->{mostrarUsuariosFiltrados();});
        btnQuitar1.addActionListener(event -> {quitarFiltros();});
        btnQuitar2.addActionListener(event -> {quitarFiltros();});
        btnQuitar3.addActionListener(event -> {quitarFiltros();});


        // Se añade color a las JTables
        tblLibros.setBackground(new Color(255, 245, 230));
        tblLibros.getTableHeader().setBackground(new Color(240, 220, 200));
        tblLibros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblLibros.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblLibros.setRowHeight(22);

        tblCategoria.setBackground(new Color(255, 245, 230));
        tblCategoria.getTableHeader().setBackground(new Color(240, 220, 200));
        tblCategoria.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblCategoria.setRowHeight(22);

        tblEstudiante.setBackground(new Color(255, 245, 230));
        tblEstudiante.getTableHeader().setBackground(new Color(240, 220, 200));
        tblEstudiante.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblEstudiante.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblEstudiante.setRowHeight(22);

        // Se cargan los JComboBox del panel
        cargarCategoriasJComboBoxCategoria();
        cargarCategoriasJComboBoxLibros();
        cargarCategoriasJComboBoxEstudiante();
        cargarCategoriasJComboBoxPrestamosDevueltos();
        cargarCategoriasJComboBoxPrestamos();
        // Se inicializan las tablas del panel
        inicializarTablaCategorias();
        inicializarTablaLibros();
        inicializarTablaEstudiantes();
        inicializarTablaPrestamos();
        // Se cargan las tablas de la BD.
        cargarTablaCategoria();
        cargarTablaLibros();
        cargarTablaEstudiantes();
        cargarTablaPrestamos();

    }
    // Acá termina el constructor


    /**
     * Método para limpiar la JTextArea
     */
    public void limpiarJTextArea() {jtaAreaPrestamos.setText("");}

    /**
     * Método que se utiliza para agregar mensajes a la JtextArea
     * @param mensaje cadena de texto que se quiere mostrar en la JTextArea.
     */
    private void agregarMensaje(String mensaje)
    {
        SwingUtilities.invokeLater(() -> {
            jtaAreaPrestamos.append(mensaje + "\n");
            // Scroll automático al final
            jtaAreaPrestamos.setCaretPosition(jtaAreaPrestamos.getDocument().getLength());
        });
    }

    /**
     * Método para crear una Tabla "categoría" con sus respectivos campos
     */
    private void inicializarTablaCategorias() {
        String[] columnas = {"ID_categoría", "Nombre_categoria"};
        modeloTablaCategoria = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCategoria.setModel(modeloTablaCategoria);
        tblCategoria.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        tblCategoria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblCategoria.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tblCategoria.getSelectedRow();
                if (fila >= 0) {
                    idCategoriaSeleccionado = Integer.parseInt(modeloTablaCategoria.getValueAt(fila, 0).toString());
                    txtCategoriaSub.setText(modeloTablaCategoria.getValueAt(fila, 1).toString());
                }
            }
        });
    }

    /**
     * Método que carga la tabla categoría de la base de datos.
     */
    private void cargarTablaCategoria() {
        modeloTablaCategoria.setRowCount(0); // limpia la tabla

        for (Categoria c : controladorCategoria.obtenerCategorias()) {
            modeloTablaCategoria.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),

            });
        }
    }

    /**
     * Método que carga una tabla filtrada por loas especificaciones del usuario
     *
     * @param categoria objeto Categoría que se pasa como parámetro para filtrar la búsqueda.
     */
    private void cargarTablaCategoriaFiltrada(Categoria categoria) {
        modeloTablaCategoria.setRowCount(0); // limpia la tabla

        List<Categoria> listaFiltradaID = new ArrayList<Categoria>();
        listaFiltradaID.add(categoria);

        for (Categoria c : listaFiltradaID) {
            modeloTablaCategoria.addRow(new Object[]{
                    c.getId(),
                    c.getNombre()
            });
        }
    }

    private void inicializarTablaLibros() {
        String[] columnas = {"ID_libro", "Título_libro", "Autor_libro", "ISBN_libro", "Editorial_libro", "Stock_libro", "ID_categoria"};
        modeloTablaLibros = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblLibros.setModel(modeloTablaLibros);
        tblLibros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tblLibros.getColumnModel().getColumn(0).setPreferredWidth(70);   // ID_Libro
        tblLibros.getColumnModel().getColumn(0).setMinWidth(70);
        tblLibros.getColumnModel().getColumn(1).setPreferredWidth(305); // Titulo
        tblLibros.getColumnModel().getColumn(1).setMinWidth(305);
        tblLibros.getColumnModel().getColumn(2).setPreferredWidth(145);  // Autor
        tblLibros.getColumnModel().getColumn(2).setMinWidth(145);
        tblLibros.getColumnModel().getColumn(3).setPreferredWidth(120);  // ISBN
        tblLibros.getColumnModel().getColumn(4).setPreferredWidth(120);   // Editorial
        tblLibros.getColumnModel().getColumn(5).setPreferredWidth(90);  // Stock
        tblLibros.getColumnModel().getColumn(5).setMinWidth(90);
        tblLibros.getColumnModel().getColumn(6).setPreferredWidth(95);  // Id_categoría
        tblLibros.getColumnModel().getColumn(6).setMinWidth(95);

        tblLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblLibros.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tblLibros.getSelectedRow();
                if (fila >= 0) {

                    idLibroSeleccionado = Integer.parseInt(modeloTablaLibros.getValueAt(fila, 0).toString());

                    txtTituloLibros.setText(modeloTablaLibros.getValueAt(fila, 1).toString());
                    txtAutor.setText(modeloTablaLibros.getValueAt(fila, 2).toString());
                    txtIsbn.setText(modeloTablaLibros.getValueAt(fila, 3).toString());
                    txtEditorial.setText(modeloTablaLibros.getValueAt(fila, 4).toString());
                    txtStock.setText(modeloTablaLibros.getValueAt(fila, 5).toString());
                    txtIdCategoriaLibro.setText(modeloTablaLibros.getValueAt(fila, 6).toString());
                }
            }
        });
    }

    private void cargarTablaLibros() {
        modeloTablaLibros.setRowCount(0); // limpia la tabla

        for (Libros l : controladorLibros.obtenerTodosLibros()) {
            modeloTablaLibros.addRow(new Object[]{
                    l.getId(),
                    l.getTitulo(),
                    l.getAutor(),
                    l.getIsbn(),
                    l.getEditorial(),
                    l.getStock(),
                    l.getId_categoria()
            });
        }
    }

    private void cargarTablaLibrosFiltrada(Libros libros) {
        modeloTablaLibros.setRowCount(0); // limpia la tabla

        List<Libros> listaFiltradaID = new ArrayList<Libros>();
        listaFiltradaID.add(libros);

        for (Libros l : listaFiltradaID) {
            modeloTablaLibros.addRow(new Object[]{
                    l.getId(),
                    l.getTitulo(),
                    l.getAutor(),
                    l.getIsbn(),
                    l.getEditorial(),
                    l.getStock(),
                    l.getId_categoria()
            });
        }
    }

    private void inicializarTablaEstudiantes() {
        String[] columnas = {"ID_estudiante", "Nombre_estudiante", "Rut_estudiante", "Curso_estudiante", "Correo_estudiante"};
        modeloTablaEstudiante = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblEstudiante.setModel(modeloTablaEstudiante);
        tblEstudiante.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tblEstudiante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblEstudiante.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tblEstudiante.getSelectedRow();
                if (fila >= 0) {
                    idEstudianteSeleccionado = Integer.parseInt(modeloTablaEstudiante.getValueAt(fila, 0).toString());
                    txtNombreEstudiante.setText(modeloTablaEstudiante.getValueAt(fila, 1).toString());
                    txtRutEstudiante.setText(modeloTablaEstudiante.getValueAt(fila, 2).toString());
                    txtCursoEstudiante.setText(modeloTablaEstudiante.getValueAt(fila, 3).toString());
                    txtCorreo.setText(modeloTablaEstudiante.getValueAt(fila, 4).toString());
                }
            }
        });
    }

    private void cargarTablaEstudiantes() {
        modeloTablaEstudiante.setRowCount(0); // limpia la tabla

        for (Estudiante e : controladorEstudiante.obtenerTodosEstudiantes()) {
            modeloTablaEstudiante.addRow(new Object[]{
                    e.getId(),
                    e.getNombre(),
                    e.getRut(),
                    e.getCurso(),
                    e.getCorreo(),
            });
        }
    }

    private void cargarTablaEstudiantesFiltrada(Estudiante estudiante)
    {
        modeloTablaEstudiante.setRowCount(0); // limpia la tabla

        List<Estudiante> listaFiltradaID = new ArrayList<Estudiante>();
        listaFiltradaID.add(estudiante);

        for (Estudiante e : listaFiltradaID) {
            modeloTablaEstudiante.addRow(new Object[]{
                    e.getId(),
                    e.getNombre(),
                    e.getRut(),
                    e.getCurso(),
                    e.getCorreo()
            });
        }
    }

    private void inicializarTablaPrestamos() {
        String[] columnas = {"id", "id_estudiante", "id_libro", "fecha_préstamo", "fecha_devolución", "devuelto"};
        modeloTablaPrestamos = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPrestamos.setModel(modeloTablaPrestamos);
        tblPrestamos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tblPrestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblPrestamos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tblPrestamos.getSelectedRow();
                if (fila >= 0) {
                    idPrestamoSeleccionado = Integer.parseInt(modeloTablaPrestamos.getValueAt(fila, 0).toString());
                    txtIdEstudiantePrestamo.setText(modeloTablaPrestamos.getValueAt(fila, 1).toString());
                    txtIdLibroPrestamo.setText(modeloTablaPrestamos.getValueAt(fila, 2).toString());
                    txtFechaPrestamo.setText(modeloTablaPrestamos.getValueAt(fila, 3).toString());
                    txtFechaDevolucionPrestamo.setText(modeloTablaPrestamos.getValueAt(fila, 4).toString());
                    jcbDevuelto.setSelectedItem(modeloTablaPrestamos.getValueAt(fila, 5).toString());
                }
            }
        });
    }

    private void cargarTablaPrestamos()
    {
        modeloTablaPrestamos.setRowCount(0); // limpia la tabla

        for (Prestamos p : controladorPrestamo.obtenerTodosLosPrestamos())
        {
            modeloTablaPrestamos.addRow(new Object[]{
                    p.getId(),
                    p.getId_estudiante(),
                    p.getId_libro(),
                    p.getFecha_prestamo(),
                    p.getFecha_devolucion(),
                    p.isDevuelto()
            });
        }
    }

    /**
     * Método que carga una tabla filtrada por las especificaciones del usuario
     *
     * @param prestamos objeto préstamos que se pasa como parámetro para filtrar la búsqueda.
     */
    private void cargarTablaPrestamosFiltrada(Prestamos prestamos)
    {
        modeloTablaPrestamos.setRowCount(0); // limpia la tabla

        List<Prestamos> listaFiltradaID = new ArrayList<Prestamos>();
        listaFiltradaID.add(prestamos);

        for (Prestamos p : listaFiltradaID) {
            modeloTablaPrestamos.addRow(new Object[]{
                    p.getId(),
                    p.getId_estudiante(),
                    p.getId_libro(),
                    p.getFecha_prestamo(),
                    p.getFecha_devolucion(),
                    p.isDevuelto()
            });
        }
    }

    private void cargarTablaPrestamosFiltradaLista(List<Prestamos> lista)
    {
        modeloTablaPrestamos.setRowCount(0); // limpia la tabla

        for (Prestamos p : lista)
        {
            modeloTablaPrestamos.addRow(new Object[]{
                    p.getId(),
                    p.getId_estudiante(),
                    p.getId_libro(),
                    p.getFecha_prestamo(),
                    p.getFecha_devolucion(),
                    p.isDevuelto()
            });
        }
    }

    /**
     * Método que carga los valores iniciales del JComboBOx del Panel "categorías"
     */
    private void cargarCategoriasJComboBoxCategoria() {
        jcbCategorias.removeAllItems();
        jcbCategorias.addItem("ID");
        jcbCategorias.addItem("Nombre");
    }

    /**
     * Método que carga los valores iniciales del JComboBOx del Panel "libros"
     */
    private void cargarCategoriasJComboBoxLibros() {
        jcbLibros.removeAllItems();
        jcbLibros.addItem("ID");
        jcbLibros.addItem("Autor");
    }

    /**
     * Método que carga los valores iniciales del JComboBOx del Panel "Estudiantes"
     */
    private void cargarCategoriasJComboBoxEstudiante() {
        jcbEstudiante.removeAllItems();
        jcbEstudiante.addItem("ID");
        jcbEstudiante.addItem("Nombre");
    }

    /**
     * Método que carga los valores iniciales del JComboBOx del Panel "Prestamos" (devueltos).
     */
    private void cargarCategoriasJComboBoxPrestamosDevueltos()
    {
        jcbDevuelto.removeAllItems();
        jcbDevuelto.addItem("true");
        jcbDevuelto.addItem("false");
    }

    /**
     * Método que carga los valores iniciales del JComboBOx del Panel "Prestamos"
     */
    private void cargarCategoriasJComboBoxPrestamos()
    {
        jcbFiltrarPrestamos.removeAllItems();
        jcbFiltrarPrestamos.addItem("ID");
        jcbFiltrarPrestamos.addItem("Devuelto");
    }



    // ===================== AGREGAR CATEGORÍA =====================

    /**
     * Método que agrega una nueva categoría a la BD
     */
    private void agregarCategoria() {
        String categoriaStr = txtCategoriaSub.getText().toUpperCase();

        if (categoriaStr.isEmpty() || categoriaStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una categoria");
            return;
        }

        CategoriaLibros categoria;

        try {
            categoria = CategoriaLibros.valueOf(categoriaStr);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Esa categoría no existe. Intente nuevamente");
            return;
        }
        Categoria c = new Categoria();  // crea una nueva instancia de "categoria", llamada "c".
        // asigna los valores para "c"
        c.setNombre(categoria);

        // Llama al método que crea o inserta un usuario nuevo en la base de datos.
        if (controladorCategoria.crearCategoria(c)) {
            cargarTablaCategoria();
            JOptionPane.showMessageDialog(this, "Categoría agregada correctamente");
            cargarTablaCategoria();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar la categoría");
        }
    }
    // ===================== EDITAR CATEGORÍA =====================

    /**
     * Método que permite editar una categoría.
     * Por motivos que utiliza valores inmutables, no se permitirá editarlos
     * Se implementó sólo por fines académicos.
     */
    private void editarCategoria() {
        if (idCategoriaSeleccionado <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Esta categoría es INMUTABLE. Pruebe agregar o eliminar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idCategoriaSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoria para editar");
            return;
        }

        String nombreTxt = txtCategoriaSub.getText().toUpperCase();

        if (nombreTxt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la categoría no puede estar vacío");
        }

        CategoriaLibros categoria;
        try {
            categoria = CategoriaLibros.valueOf(nombreTxt);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Esa categoría no existe. Intente nuevamente");
            return;
        }

        Categoria c = new Categoria();
        c.setId(idCategoriaSeleccionado);
        c.setNombre(categoria);

        if (controladorCategoria.editarCategoria(c)) {
            JOptionPane.showMessageDialog(this, "Categoría editada correctamente");
            cargarTablaCategoria();
        } else {
            JOptionPane.showMessageDialog(this, "Error al editar la categoría");
        }
    }

    // ===================== ELIMINAR CATEGORÍA =====================

    /**
     * Método que permite eliminar la categoría seleccionada.
     */
    private void eliminarCategoria() {
        // Si no se ha seleccionado nada, lanza este mensaje emergente
        if (idCategoriaSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría para eliminar");
            return;
        }

        // Intenta esto:
        try {
            // Lanza cuadro emergente con opción si/no seleccionable para confirmar eliminación de categoría.
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar esta categoría?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            // Si la opción es NO, regresa a la pantalla principal
            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }
            // Si la opción es SI, llama al controlador de categorías y elimina la fila marcada
            boolean eliminado = controladorCategoria.eliminarCategoria(idCategoriaSeleccionado);

            // Si se confirma eliminación, lanza este cuadro emergente:
            if (eliminado) {
                JOptionPane.showMessageDialog(null,
                        "Categoría eliminada correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            // En caso contrario, lanza el cuadro emergente con la mala noticia.
            else {
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar la categoría",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        // Lanza este "CATCH" si no se puedo eliminar por restricción de integridad:
        catch (RuntimeException ex) {
            if (ex.getMessage().equals("FK_ERROR")) {
                JOptionPane.showMessageDialog(null,
                        "No se puede eliminar esta categoría porque tiene libros asociados.\n" +
                                "Debe eliminar o reasignar los libros primero.",
                        "Restricción de integridad",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    // ===================== LIMPIAR CATEGORÍA =====================

    /**
     * Método que permite limpiar todos los campos de la tabla categorías.
     */
    private void limpiarCategoria() {
        txtCategoriaSub.setText("");
        tblCategoria.clearSelection();
        idCategoriaSeleccionado = -1;
        modeloTablaCategoria.setRowCount(0);
    }

    // ===================== FILTRAR CATEGORÍA =====================

    /**
     * Método que filtra la tabla categoría por ID o Nombre
     */
    private void filtrarCategoria() {
        String categoriaSeleccionada = jcbCategorias.getSelectedItem().toString();
        if (categoriaSeleccionada.isEmpty() || categoriaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor en la pestaña para filtrar");
            return;
        }
        // Dependiendo de la selección del usuario, se filtra la tabla por id o categoría

        if (categoriaSeleccionada.equals("ID")) {
            try {
                Categoria c = new Categoria();
                int idCategoria = Integer.parseInt(txtFiltrarCat.getText());
                c = controladorCategoria.buscarPorId(idCategoria);
                cargarTablaCategoriaFiltrada(c);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El valor ingresado no es válido. Intente ingresar un número entero");
                return;
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "El valor ingresado no existe en la BD. Intente con otro");
                return;
            }
        }

        if (categoriaSeleccionada.equals("Nombre")) {
            try {
                Categoria c = new Categoria();
                String nombre = txtFiltrarCat.getText().toUpperCase();
                c = controladorCategoria.buscarPorCategoria(nombre);
                cargarTablaCategoriaFiltrada(c);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Los valores ingresados no son válidos. Inténtelo nuevamente");
                return;
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "El nombre ingresado no existe. Presione 'categorias' para obtener valores aceptados");
                return;
            }
        }
    }

    // ===================== LISTAR CATEGORÍA =====================

    /**
     * Método que carga una tabla actualizada con todas las categorías
     */
    private void listarCategoria() {
        List<Categoria> todasLasCategorias = controladorCategoria.obtenerCategorias();
        if (todasLasCategorias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron categorías almacenadas en la BD");
            return;
        }
        cargarTablaCategoria();
    }

    // Se agrega la lógica a los botones del panel para agregar libros

    // ===================== AGREGAR LIBRO =====================

    /**
     * Método que permite agregar un libro a la BD.
     * Recuerda: el ISBN es de tipo "UNIQUE" no se puede repetir o lanza error.
     */
    private void agregarLibro() {
        // Se capturan los valores
        String titulo = txtTituloLibros.getText().toLowerCase().trim();
        String autor = txtAutor.getText().toLowerCase().trim();
        String isbn = txtIsbn.getText().toLowerCase().trim();
        String editorial = txtEditorial.getText().toLowerCase().trim();
        int stock;
        int idCategoria;

        // Se agregan los manejos de excepciones correspondientes
        if (titulo.isEmpty() || titulo == null) {
            JOptionPane.showMessageDialog(this, "Debe agregar un titulo para poder agregar");
            return;
        }

        if (autor.isEmpty() || autor == null) {
            JOptionPane.showMessageDialog(this, "Debe agregar un autor para poder agregar");
            return;
        }

        if (isbn.isEmpty() || isbn == null) {
            JOptionPane.showMessageDialog(this, "Debe agregar un ISBN para agregar");
            return;
        }

        if (controladorLibros.comprobarISBNLibro(isbn)) {
            JOptionPane.showMessageDialog(this,
                    "El ISBN '" + isbn + "' ya existe.\nPor favor ingresa otro ISBN.",
                    "ISBN duplicado",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (editorial.isEmpty() || editorial == null) {
            JOptionPane.showMessageDialog(this, "Debe agregar una editorial para agregar");
            return;
        }

        try {
            stock = Integer.parseInt(txtStock.getText().trim());
            if (stock < 0) {
                JOptionPane.showMessageDialog(this, "El stock ingresado no puede ser negativo. Ingrese un valor '0' o más alto");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe agregar un número entero para agregar", "número entero desconocido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            idCategoria = Integer.parseInt(txtIdCategoriaLibro.getText().trim());
            if (!(idCategoria > 0)) {
                JOptionPane.showMessageDialog(this, "El id ingresado no puede ser negativo. Ingrese un valor entero, superior a '0' para agregar");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe agregar un número entero para agregar", "número entero desconocido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Libros l = new Libros();
        l.setTitulo(titulo);
        l.setAutor(autor);
        l.setIsbn(isbn);
        l.setEditorial(editorial);
        l.setStock(stock);
        l.setId_categoria(idCategoria);

        // Llama al método que crea o inserta un libro nuevo en la base de datos.

        if (controladorLibros.insertarLibro(l)) {
            cargarTablaLibros();
            JOptionPane.showMessageDialog(this, "Libro agregado correctamente");
            cargarTablaCategoria();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el libro");
        }
    }

    // ===================== EDITAR LIBRO =====================

    /**
     * Método que permite editar un libro que ya fue ingresado a la BD
     */
    private void editarLibro() {
        if (idLibroSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para editar");
            return;
        }

        String titulo = txtTituloLibros.getText().toLowerCase().trim();
        String autor = txtAutor.getText().toLowerCase().trim();
        String isbn = txtIsbn.getText().toLowerCase().trim();
        String editorial = txtEditorial.getText().toLowerCase().trim();
        int stock;
        int idCategoria;

        if (titulo.isEmpty() || titulo == null) {
            JOptionPane.showMessageDialog(this, "El nombre del libro no puede estar vacío");
        }

        if (autor.isEmpty() || autor == null) {
            JOptionPane.showMessageDialog(this, "Debe agregar un autor para poder editar");
            return;
        }

        if (isbn.isEmpty() || isbn == null) {
            JOptionPane.showMessageDialog(this, "Debe agregar un ISBN para editar");
            return;
        }

        if (editorial.isEmpty() || editorial == null) {
            JOptionPane.showMessageDialog(this, "Debe agregar una editorial para agregar");
            return;
        }

        try {
            stock = Integer.parseInt(txtStock.getText().trim());
            if (stock < 0) {
                JOptionPane.showMessageDialog(this, "El stock ingresado no puede ser negativo. Ingrese un valor '0' o más alto");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe agregar un número entero para agregar", "número entero desconocido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            idCategoria = Integer.parseInt(txtIdCategoriaLibro.getText().trim());
            if (!(idCategoria > 0)) {
                JOptionPane.showMessageDialog(this, "El id ingresado no puede ser negativo. Ingrese un valor entero, superior a '0' para agregar");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe agregar un número entero para agregar", "número entero desconocido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Libros l = new Libros();
        l.setId(idLibroSeleccionado);
        l.setTitulo(titulo);
        l.setAutor(autor);
        l.setIsbn(isbn);
        l.setEditorial(editorial);
        l.setStock(stock);
        l.setId_categoria(idCategoria);

        if (controladorLibros.editarLibro(l)) {
            JOptionPane.showMessageDialog(this, "libro editado correctamente");
            cargarTablaLibros();
        } else {
            JOptionPane.showMessageDialog(this, "Error al editar el libro");
        }
    }

    // ===================== ELIMINAR LIBRO =====================

    /**
     * Método para eliminar un libro de la base de datos.
     * Debe seleccionar una fila de la tabla para eliminarla.
     */
    private void eliminarLibro() {
        // Si no se ha seleccionado nada, lanza este mensaje emergente
        if (idLibroSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para eliminar");
            return;
        }

        // Intenta esto:
        try {
            // Lanza cuadro emergente con opción si/no seleccionable para confirmar eliminación del libro.
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar este libro?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            // Si la opción es NO, regresa a la pantalla principal
            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }
            // Si la opción es SI, llama al controlador de categorías y elimina la fila marcada
            boolean eliminado = controladorLibros.eliminarLibro(idLibroSeleccionado);

            // Si se confirma eliminación, lanza este cuadro emergente:
            if (eliminado) {
                JOptionPane.showMessageDialog(null,
                        "libro eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarTablaLibros();
            }
            // En caso contrario, lanza el cuadro emergente con la mala noticia.
            else {
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar el libro",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        // Lanza este "CATCH" si no se puedo eliminar por restricción de integridad:
        catch (RuntimeException ex) {
            if (ex.getMessage().equals("FK_ERROR")) {
                JOptionPane.showMessageDialog(null,
                        "No se puede eliminar esta libro porque tiene relaciones de integridad asociadas.",
                        "Restricción de integridad",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ===================== LIMPIAR TABLA LIBRO =====================

    /**
     * Método que limpia los campos de texto y esconde la tabla de libros.
     * No elimina la tabla, sólo la oculta.
     */
    private void limpiarLibro() {
        txtTituloLibros.setText("");
        txtAutor.setText("");
        txtIsbn.setText("");
        txtEditorial.setText("");
        txtStock.setText("");
        txtIdCategoriaLibro.setText("");

        tblLibros.clearSelection();
        idLibroSeleccionado = -1;
        modeloTablaLibros.setRowCount(0);
    }

    // ===================== LISTAR TODOS LOS LIBROS =====================

    /**
     * Método que carga la tabla de libros con todos los registros de la BD
     */
    private void listarLibro() {
        List<Libros> todosLosLibros = controladorLibros.obtenerTodosLibros();
        if (todosLosLibros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron libros almacenadas en la BD");
            return;
        }
        cargarTablaLibros();
    }

    // ===================== FILTRAR LIBROS POR ID O AUTOR =====================

    private void filtrarLibros() {
        String filtroSeleccionado = jcbLibros.getSelectedItem().toString();
        if (filtroSeleccionado.isEmpty() || filtroSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor en la pestaña para filtrar");
            return;
        }

        // Dependiendo de la selección del usuario, se filtra la tabla por "id" o autor.
        if (filtroSeleccionado.equals("ID")) {
            try {
                Libros l = new Libros();
                int idLibro = Integer.parseInt(txtFiltrarLibros.getText());
                l = controladorLibros.buscarLibroPorId(idLibro);
                cargarTablaLibrosFiltrada(l);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El valor ingresado no es válido. Intente ingresar un número entero");
                return;
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "El valor ingresado no existe en la BD. Intente con otro");
                return;
            }
        }

        if (filtroSeleccionado.equals("Autor")) {
            try {
                Libros l = new Libros();
                String autor = txtFiltrarLibros.getText().toLowerCase().trim();
                l = controladorLibros.buscarLibroPorAutor(autor);
                cargarTablaLibrosFiltrada(l);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Los valores ingresados no son válidos. Inténtelo nuevamente");
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "El autor ingresado no existe. Intente por ID o con otro nombre de autor");
            }
        }
    }

    // ===================== AGREGAR ESTUDIANTE =====================
    private void agregarEstudiante() {
        String nombre = txtNombreEstudiante.getText().toLowerCase().trim();
        String rut = txtRutEstudiante.getText().toLowerCase().trim();
        String curso = txtCursoEstudiante.getText().toLowerCase().trim();
        String correo = txtCorreo.getText().toLowerCase().trim();

        if (nombre.isEmpty() || rut.isEmpty() || curso.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos para ingresar un estudiante");
            return;
        }

        Estudiante e = new Estudiante();
        e.setNombre(nombre);
        e.setRut(rut);
        e.setCurso(curso);
        e.setCorreo(correo);

        if (controladorEstudiante.insertarEstudiante(e)) {
            JOptionPane.showMessageDialog(this, "Estudiante insertado correctamente");
            cargarTablaEstudiantes();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "no se pudo agregar el estudiante");
        }
    }

    // ===================== EDITAR ESTUDIANTE =====================
    private void editarEstudiante() {
        if (idEstudianteSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para editar");
            return;
        }
        String nombre = txtNombreEstudiante.getText().toLowerCase().trim();
        String rut = txtRutEstudiante.getText().toLowerCase().trim();
        String curso = txtCursoEstudiante.getText().toLowerCase().trim();
        String correo = txtCorreo.getText().toLowerCase().trim();

        if (nombre.isEmpty() || rut.isEmpty() || curso.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos para ingresar un estudiante");
            return;
        }

        Estudiante e = new Estudiante();
        e.setId(idEstudianteSeleccionado);
        e.setNombre(nombre);
        e.setRut(rut);
        e.setCurso(curso);
        e.setCorreo(correo);

        if (controladorEstudiante.editarEstudiante(e)) {
            JOptionPane.showMessageDialog(this, "Estudiante editado correctamente");
            cargarTablaEstudiantes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al editar el Estudiante");
        }
    }

    // ===================== ELIMINAR ESTUDIANTE =====================
    private void eliminarEstudiante() {
        // Si no se ha seleccionado nada, lanza este mensaje emergente
        if (idEstudianteSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una estudiante para eliminar");
            return;
        }

        // Intenta esto:
        try {
            // Lanza cuadro emergente con opción si/no seleccionable para confirmar eliminación del estudiante.
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar este estudiante?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            // Si la opción es NO, regresa a la pantalla principal
            if (opcion != JOptionPane.YES_OPTION)
            {
                return;
            }
            // Si la opción es SI, llama al controlador de estudiantes y elimina la fila marcada
            boolean eliminado = controladorEstudiante.eliminarEstudiante(idEstudianteSeleccionado);

            // Si se confirma eliminación, lanza este cuadro emergente:
            if (eliminado)
            {
                JOptionPane.showMessageDialog(null,
                        "Estudiante eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarTablaEstudiantes();
            }
            // En caso contrario, lanza el cuadro emergente con la mala noticia.
            else
            {
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar el estudiante",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        // Lanza este "CATCH" si no se puedo eliminar por restricción de integridad:
        catch (RuntimeException ex)
        {
            if (ex.getMessage().equals("FK_ERROR"))
            {
                JOptionPane.showMessageDialog(null,
                        "No se puede eliminar esta categoría porque tiene libros asociados.\n" +
                                "Debe eliminar o reasignar los libros primero.",
                        "Restricción de integridad",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ===================== LIMPIAR ESTUDIANTE =====================
    private void limpiarEstudiante()
    {
        txtNombreEstudiante.setText("");
        txtRutEstudiante.setText("");
        txtCursoEstudiante.setText("");
        txtCorreo.setText("");

        tblEstudiante.clearSelection();
        idEstudianteSeleccionado = -1;
        modeloTablaEstudiante.setRowCount(0);
    }

    // ===================== LISTAR TODOS LOS ESTUDIANTE =====================
    private void listarEstudiante()
    {
        List<Estudiante> todosLosEstudiantes = controladorEstudiante.obtenerTodosEstudiantes();
        if (todosLosEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron estudiantes almacenadas en la BD");
            return;
        }
        cargarTablaEstudiantes();
    }

    // ===================== FILTRAR ESTUDIANTE POR NOMBRE O ID =====================
    private void filtrarEstudiante()
    {
        String filtroSeleccionado = jcbEstudiante.getSelectedItem().toString();

        if (filtroSeleccionado.isEmpty() || filtroSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor en la pestaña para filtrar");
            return;
        }

        // Dependiendo de la selección del usuario, se filtra la tabla por "id" o nombre.
        if (filtroSeleccionado.equals("ID"))
        {
            try {
                Estudiante e = new Estudiante();
                int idEstudiante = Integer.parseInt(txtEstudiantejcb.getText());
                e = controladorEstudiante.buscarEstudiantePorId(idEstudiante);
                cargarTablaEstudiantesFiltrada(e);
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(this, "El valor ingresado no es válido. Intente ingresar un número entero");
                return;
            }
            catch (NullPointerException e)
            {
                JOptionPane.showMessageDialog(this, "El valor ingresado no existe en la BD. Intente con otro");
                return;
            }
        }

        if (filtroSeleccionado.equals("Nombre"))
        {
            try
            {
                Estudiante e = new Estudiante();
                String nombre = txtEstudiantejcb.getText().toLowerCase().trim();
                e = controladorEstudiante.buscarEstudiantePorNombre(nombre);
                cargarTablaEstudiantesFiltrada(e);
            }
            catch (IllegalArgumentException e)
            {
                JOptionPane.showMessageDialog(this, "Los valores ingresados no son válidos. Inténtelo nuevamente");
            }
            catch (NullPointerException e)
            {
                JOptionPane.showMessageDialog(this, "El estudiante ingresado no existe. Intente por ID o con otro nombre de autor");
            }
        }
    }

    // ===================== AGREGAR PRÉSTAMO =====================
    private void agregarPrestamo() {

        String idEstudianteTxt = txtIdEstudiantePrestamo.getText().toLowerCase().trim();
        String idLibroTxt = txtIdLibroPrestamo.getText().toLowerCase().trim();
        String fechaPrestamoTxt = txtFechaPrestamo.getText().toLowerCase().trim();
        String fechaDevolucionTxt = txtFechaDevolucionPrestamo.getText().toLowerCase().trim();
        String devueltoTxt = jcbDevuelto.getSelectedItem().toString();

        if (idEstudianteTxt.isEmpty() || idLibroTxt.isEmpty() || fechaPrestamoTxt.isEmpty() || fechaDevolucionTxt.isEmpty() || devueltoTxt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos para ingresar un préstamo");
            return;
        }

        int idEstudiante;
        try
        {
            idEstudiante = Integer.parseInt(idEstudianteTxt);
            if (idEstudiante < 1)
            {
                    JOptionPane.showMessageDialog(this, "Error al asignar el ID. Ingrese un valor '1' o más alto");
                    return;
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Debe agregar un número entero para agregar", "número entero desconocido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idLibro;
        try
        {
            idLibro = Integer.parseInt(idLibroTxt);
            if (idLibro < 1)
            {
                JOptionPane.showMessageDialog(this, "Error al asignar el ID. Ingrese un valor '1' o más alto");
                return;
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Debe agregar un número entero para agregar", "número entero desconocido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Date fechaPrestamo;
        try
        {
            fechaPrestamo = java.sql.Date.valueOf(fechaPrestamoTxt);
        }
        catch (DateTimeParseException e)
        {
            JOptionPane.showMessageDialog(this, "La fecha no se puedo convertir como se esperaba. Intente de nuevo");
            return;
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(this, "El formato de fecha es incorrecto. Debe ser: YYYY-MM-DD");
            return;
        }

        Date fechaDevolucion;
        try
        {
            fechaDevolucion = java.sql.Date.valueOf(fechaDevolucionTxt);
        }
        catch (DateTimeParseException e)
        {
            JOptionPane.showMessageDialog(this, "La fecha no se puedo convertir como se esperaba. Intente de nuevo");
            return;
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(this, "El formato de fecha es incorrecto. Debe ser: YYYY-MM-DD");
            return;
        }
        boolean devuelto;
        try
        {
            devuelto = Boolean.parseBoolean(devueltoTxt);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(this, "No se puedo realizar la conversión booleana. Revise el valor ingresado");
            return;
        }

        Prestamos p = new Prestamos();
        p.setId_estudiante(idEstudiante);
        p.setId_libro(idLibro);
        p.setFecha_prestamo((java.sql.Date) fechaPrestamo);
        p.setFecha_devolucion((java.sql.Date) fechaDevolucion);
        p.setDevuelto(devuelto);

        if (controladorPrestamo.insertarPrestamo(p))
        {
            JOptionPane.showMessageDialog(this, "Préstamo agregado correctamente");
            cargarTablaPrestamos();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al agregar el préstamo");
        }
    }

    // ===================== EDITAR PRÉSTAMO =====================
    private void editarPrestamo()
    {
        if (idPrestamoSeleccionado == -1)
        {
            JOptionPane.showMessageDialog(this, "Seleccione un préstamo para editar");
            return;
        }
        String idEstudianteTxt = txtIdEstudiantePrestamo.getText().toLowerCase().trim();
        String idLibroTxt = txtIdLibroPrestamo.getText().toLowerCase().trim();
        String fechaPrestamoTxt = txtFechaPrestamo.getText().toLowerCase().trim();
        String fechaDevolucionTxt = txtFechaDevolucionPrestamo.getText().toLowerCase().trim();
        String devueltoTxt = jcbDevuelto.getSelectedItem().toString();

        if (idEstudianteTxt.isEmpty() || idLibroTxt.isEmpty() || fechaPrestamoTxt.isEmpty() || fechaDevolucionTxt.isEmpty() || devueltoTxt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos para ingresar un préstamo");
            return;
        }

        int idEstudiante;
        try
        {
            idEstudiante = Integer.parseInt(idEstudianteTxt);
            if (idEstudiante < 1)
            {
                JOptionPane.showMessageDialog(this, "Error al asignar el ID. Ingrese un valor '1' o más alto");
                return;
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Debe agregar un número entero para agregar", "número entero desconocido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idLibro;
        try
        {
            idLibro = Integer.parseInt(idLibroTxt);
            if (idLibro < 1)
            {
                JOptionPane.showMessageDialog(this, "Error al asignar el ID. Ingrese un valor '1' o más alto");
                return;
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Debe agregar un número entero para agregar", "número entero desconocido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Date fechaPrestamo;
        try
        {
            fechaPrestamo = java.sql.Date.valueOf(fechaPrestamoTxt);
        }
        catch (DateTimeParseException e)
        {
            JOptionPane.showMessageDialog(this, "La fecha no se puedo convertir como se esperaba. Intente de nuevo");
            return;
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(this, "El formato de fecha es incorrecto. Debe ser: YYYY-MM-DD");
            return;
        }

        Date fechaDevolucion;
        try
        {
            fechaDevolucion = java.sql.Date.valueOf(fechaDevolucionTxt);
        }
        catch (DateTimeParseException e)
        {
            JOptionPane.showMessageDialog(this, "La fecha no se puedo convertir como se esperaba. Intente de nuevo");
            return;
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(this, "El formato de fecha es incorrecto. Debe ser: YYYY-MM-DD");
            return;
        }

        boolean devuelto;
        try
        {
            devuelto = Boolean.parseBoolean(devueltoTxt);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(this, "No se puedo realizar la conversión booleana. Revise el valor ingresado");
            return;
        }

        Prestamos p = new Prestamos();
        p.setId_estudiante(idEstudiante);
        p.setId_libro(idLibro);
        p.setFecha_prestamo((java.sql.Date) fechaPrestamo);
        p.setFecha_devolucion((java.sql.Date) fechaDevolucion);
        p.setDevuelto(devuelto);

        if (controladorPrestamo.editarPrestamo(p))
        {
            JOptionPane.showMessageDialog(this, "Préstamo editado correctamente");
            cargarTablaPrestamos();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al editar el préstamo");
        }
    }

    // ===================== ELIMINAR PRÉSTAMO =====================
    private void eliminarPrestamo()
    {
        // Si no se ha seleccionado nada, lanza este mensaje emergente
        if (idPrestamoSeleccionado == -1)
        {
            JOptionPane.showMessageDialog(this, "Seleccione un préstamo para eliminar");
            return;
        }

        // Intenta esto:
        try {
            // Lanza cuadro emergente con opción si/no seleccionable para confirmar eliminación del préstamo.
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar este préstamo?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            // Si la opción es NO, regresa a la pantalla principal
            if (opcion != JOptionPane.YES_OPTION)
            {
                return;
            }
            // Si la opción es SI, llama al controlador de préstamos y elimina la fila marcada
            boolean eliminado = controladorPrestamo.eliminarPrestamo(idPrestamoSeleccionado);
            cargarTablaPrestamos();

            // Si se confirma eliminación, lanza este cuadro emergente:
            if (eliminado)
            {
                JOptionPane.showMessageDialog(null,
                        "Préstamo eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarTablaEstudiantes();
            }
            // En caso contrario, lanza el cuadro emergente con la mala noticia.
            else
            {
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar el préstamo",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        // Lanza este "CATCH" si no se puedo eliminar por restricción de integridad:
        catch (RuntimeException ex)
        {
            if (ex.getMessage().equals("FK_ERROR"))
            {
                JOptionPane.showMessageDialog(null,
                        "No se puede eliminar este préstamo porque tiene restricciones de integridad asociadas",
                        "Restricción de integridad",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    // ===================== LIMPIAR PRÉSTAMO =====================
    private void limpiarPrestamo()
    {
        txtIdEstudiantePrestamo.setText("");
        txtIdLibroPrestamo.setText("");
        txtFechaPrestamo.setText("");
        txtFechaDevolucionPrestamo.setText("");
        jcbDevuelto.setSelectedIndex(-1);

        tblPrestamos.clearSelection();
        idPrestamoSeleccionado = -1;
        modeloTablaPrestamos.setRowCount(0);
    }

    // ===================== LISTAR TODOS LOS PRESTAMOS =====================
    private void listarPrestamos()
    {
        List<Prestamos> todosLosPrestamos = controladorPrestamo.obtenerTodosLosPrestamos();
        if (todosLosPrestamos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron préstamos almacenadas en la BD");
            return;
        }
        cargarTablaPrestamos();
    }

    // ===================== FILTRAR PRÉSTAMO POR ID O CONDICIÓN (DEVUELTO) =====================
    private void filtrarPrestamo()
    {
        String filtroSeleccionado = jcbFiltrarPrestamos.getSelectedItem().toString();

        if (filtroSeleccionado.isEmpty() || filtroSeleccionado == null)
        {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor en la pestaña para filtrar");
            return;
        }

        String textoFiltro = txtFiltroPrestamos.getText().trim();

        if (textoFiltro.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor para filtrar");
            return;
        }
        // Dependiendo de la selección del usuario, se filtra la tabla por "id" o condición de devolución.
        if (filtroSeleccionado.equals("ID"))
        {
            try {
                Prestamos p = new Prestamos();
                int idPrestamo = Integer.parseInt(textoFiltro);
                p = controladorPrestamo.buscarPrestamoPorId(idPrestamo);
                if (p == null)
                {
                    JOptionPane.showMessageDialog(this, "No existe un préstamo bajo esos términos");
                    return;
                }
                cargarTablaPrestamosFiltrada(p);
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(this, "El valor ingresado no es un número entero. Ingrese un número mayor a '1'");
                return;
            }
            catch (NullPointerException e)
            {
                JOptionPane.showMessageDialog(this, "El valor ingresado no existe en la BD. Intente con otro");
                return;
            }
        }

        if (filtroSeleccionado.equals("Devuelto"))
        {
            boolean devuelto;
            if (textoFiltro.equalsIgnoreCase("true") || textoFiltro.equalsIgnoreCase("sí") || textoFiltro.equalsIgnoreCase("si"))
            {
                devuelto = true;
            }
            else if (textoFiltro.equalsIgnoreCase("false") || textoFiltro.equalsIgnoreCase("no"))
            {
                devuelto = false;
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Ingrese 'true', 'false', 'sí' o 'no'");
                return;
            }

                List<Prestamos> lista = controladorPrestamo.buscarPrestamoPorBoolean(devuelto);

                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se encontraron préstamos con ese estado");
                    return;
                }

                cargarTablaPrestamosFiltradaLista(lista);
            }
    }

    private void ejecutarLosHilos()
    {
        // Nombre de los trabajadores que se desempeñan como bibliotecarios
        String nombreBibliotecario1 = "Cristian";
        String nombreBibliotecario2 = "Enrique";

        String idHilosTxt = txtTramitarPorId.getText().trim();
        if (idHilosTxt.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor en el campo de texto");
        }

        int idPrestamo;
        try
        {
            idPrestamo = Integer.parseInt(idHilosTxt);
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el ID. Inténtelo de nuevo");
            return;
        }
        int idEstudianteDevuelto;
        int idLibroDevuelto;
        try
        {

            Prestamos p = controladorPrestamo.buscarPrestamoPorId(idPrestamo);
            if (p == null)
            {
                JOptionPane.showMessageDialog(this, "El préstamo no existe");
                return;
            }
            idEstudianteDevuelto = p.getId_estudiante();
            idLibroDevuelto = p.getId_libro();
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "El ID ingresado no fue encontrado en la BD");
            return;
        }

        Bibliotecario bibliotecario = controladorBibliotecario.crearBibliotecario(nombreBibliotecario1, idEstudianteDevuelto, idLibroDevuelto );
        bibliotecario.setLogger(this::agregarMensaje);
        int tamañoHilo = 2; // Se establece 2, simulando que hay máximo 2 bibliotecarios

        ExecutorService executor;
        executor = Executors.newFixedThreadPool(tamañoHilo);
        executor.execute(bibliotecario);

        executor.shutdown();

        try {
            // Espera hasta que terminen todas
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                JOptionPane.showMessageDialog(this, "Algunas tareas no terminaron a tiempo. Forzando cierre...");
                executor.shutdownNow();   // Interrumpe las que aún estén corriendo
            } else {
                JOptionPane.showMessageDialog(this, "Todas las tareas terminaron.");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    private void limpiarAreaTexto()
    {
        jtaAreaPrestamos.setText("");
    }

    private void mostrarNombresFiltrados()
    {

        if (controladorEstudiante.cargarEstudiantesJAREA(jtaResumen))
        {
            JOptionPane.showMessageDialog(this, "Estudiantes agregados correctamente");
            return;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Estudiantes no agregados");
        }
    }

    private void mostrarLibrosFiltrados()
    {
        if (controladorLibros.consultarLibros(jtaResumen))
        {
            JOptionPane.showMessageDialog(this, "Libros agregados correctamente");
            return;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No se puedo agregar los libros al área de visualización");
        }
    }

    private void mostrarUsuariosFiltrados()
    {
        if (controladorUsuario.consultarUsuarios(jtaResumen))
        {
            JOptionPane.showMessageDialog(this, "Usuarios agregados correctamente");
            return;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los usuarios");
        }
    }

    private void quitarFiltros()
    {
        jtaResumen.setText("");
    }

}

