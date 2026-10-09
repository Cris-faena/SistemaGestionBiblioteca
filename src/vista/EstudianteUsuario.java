package vista;

import controlador.ControladorEstudiante;
import controlador.ControladorLibros;

import controlador.ControladorPrestamo;
import modelo.Estudiante;
import modelo.Libros;
import modelo.Prestamos;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class EstudianteUsuario extends JFrame
{
    private Usuario usuarioActual;
    private JPanel PanelEstudianteUsuario;
    private JTabbedPane jtpEstudiante;
    private JLabel lblNombreEstudiante;
    private JPanel PanelFormularioEstudiante;
    private JTextField txtNombreEstudiante;
    private JLabel lblRutEstudiante;
    private JTextField txtRutEstudiante;
    private JLabel lblCursoEstudiante;
    private JTextField txtCursoEstudiante;
    private JTextField txtCorreoEstudiante;
    private JLabel lblCorreoEstudiante;
    private JLabel lblLibrosDisponibles;
    private JComboBox jcbLibrosDisponibles;
    private JButton btnSolicitarLibro;
    private JButton btnCancelarLibro;
    private JPanel PanelTablaEstudiante;
    private JScrollPane jspTablaestudiante;
    private JPanel PanelTablasFiltradas;
    private JTable tblLibrosDelEstudiante;
    private JTable tblLibros;
    private JScrollPane jspDelEstudianteFiltrado;
    private JButton btnAgregarCurso;
    private JTextField txtFechaInicio;
    private JTextField txtFechaDevolucion;
    private JLabel lblTituloSolicitud;
    private JLabel lblFechaInicio;
    private JLabel lblFechaTermino;
    private JRadioButton rbFiltrarYo;

    private DefaultTableModel modeloTablaLibros;
    private DefaultTableModel modeloTablaEstudiante;
    private DefaultTableModel modeloTablaEstudianteFiltrado;
    private DefaultTableModel modeloTablaPrestamos;

    private final ControladorLibros controladorLibros = new ControladorLibros();
    private final ControladorEstudiante controladorEstudiante = new ControladorEstudiante();
    private final ControladorPrestamo controladorPrestamo = new ControladorPrestamo();

    private int idLibroSeleccionado = -1;
    private int idEstudianteSeleccionado = -1;
    private int idUsuarioSeleccionado = -1;
    private int idPrestamoSeleccionado = -1;

    public EstudianteUsuario(Usuario usuario)
    {
        setTitle("EstudianteUsuario");
        setContentPane(PanelEstudianteUsuario);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        PanelEstudianteUsuario.setBackground(new Color(245, 222, 179));
        jtpEstudiante.setBackground(new Color(245, 222, 179));

        PanelFormularioEstudiante.setBackground(new Color(250, 240, 230));
        PanelTablaEstudiante.setBackground(new Color(250, 240, 230));
        PanelTablasFiltradas.setBackground(new Color(250, 240, 230));

        tblLibros.setBackground(new Color(255, 245, 230));
        tblLibros.getTableHeader().setBackground(new Color(240, 220, 200));
        tblLibros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblLibros.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblLibros.setRowHeight(22);

        btnAgregarCurso.addActionListener(event->{agregarEstudianteALista();});
        btnSolicitarLibro.addActionListener(event->{agregarSolicitudLibro();});
        btnCancelarLibro.addActionListener(event->{cancelarSolicitudLibro();});
        rbFiltrarYo.addActionListener(event->{
            if (rbFiltrarYo.isSelected())
                filtrarSoloUsuario();;});

        inicializarTablaLibros();
        inicializarTablaPrestamos();
        cargarTablaLibros();
        cargarJcbLibrosDisponibles();
        controladorPrestamo.consultarEstudianteConPrestamo(modeloTablaPrestamos);

        this.usuarioActual = usuario;
        rellenarCampos();
    }
    // Acá termina el constructor

    private void cargarJcbLibrosDisponibles()
    {
        List<Libros> librosDisponibles = new ArrayList<Libros>();
        librosDisponibles = controladorLibros.obtenerTodosLibros();
        for (Libros libro : librosDisponibles)
        {
            if (libro.getStock() > 0)
            {
                jcbLibrosDisponibles.addItem(libro.getTitulo());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "El libro no está disponible");
                return;
            }
        }
    }

    private void inicializarTablaLibros()
    {
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

    private void inicializarTablaPrestamos()
    {
        String[] columnas = {"Estudiante", "Libro", "Fecha Devolución", "Condición"};
        modeloTablaPrestamos = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla no editable
            }
        };
        tblLibrosDelEstudiante.setModel(modeloTablaPrestamos);
        tblLibrosDelEstudiante.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblLibrosDelEstudiante.getColumnModel().getColumn(0).setPreferredWidth(200); // estudiante
        tblLibrosDelEstudiante.getColumnModel().getColumn(1).setPreferredWidth(250); // libro
        tblLibrosDelEstudiante.getColumnModel().getColumn(2).setPreferredWidth(150); // fecha
        tblLibrosDelEstudiante.getColumnModel().getColumn(3).setPreferredWidth(120); // condición
        tblLibrosDelEstudiante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void cargarTablaPrestamos()
    {
        modeloTablaPrestamos.setRowCount(0);
        controladorPrestamo.consultarEstudianteConPrestamo(modeloTablaPrestamos);
    }

    private void rellenarCampos() {
        txtNombreEstudiante.setText(usuarioActual.getNombre());
        txtNombreEstudiante.setEditable(false);
        txtRutEstudiante.setText(usuarioActual.getRut());
        txtRutEstudiante.setEditable(false);
        txtCorreoEstudiante.setText(usuarioActual.getCorreo());
        txtCorreoEstudiante.setEditable(false);
    }

    private void agregarEstudianteALista()
    {
        String nombre = txtNombreEstudiante.getText().toLowerCase().trim();
        String rut = txtRutEstudiante.getText().toLowerCase().trim();
        String curso = txtCursoEstudiante.getText().toLowerCase().trim();
        String correo = txtCorreoEstudiante.getText().toLowerCase().trim();

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

    /**
     * Método para crear una nueva solicitud de préstamo.
     * Con los campos del usuario se busca su ID de estudiante.
     * Con el campo seleccionado para libros se obtiene un id de libro.
     * Estos sólo muestras libros que se encuentran devueltos.
     * Con las fechas ingresadas, más los datos anteriores se crea un nuevo préstamo.
     * Cuando la solicitud es aprobada por el bibliotecario, se le entrega el libro al estudiante y se actualiza la BD.
     */
    private void agregarSolicitudLibro()
    {

        String nombreEstudiante  = txtNombreEstudiante.getText().toLowerCase().trim();
        if (nombreEstudiante.isEmpty() || nombreEstudiante.isBlank())
        {
            JOptionPane.showMessageDialog(this, "Debe llenar el nombre del estudiante");
            return;
        }

        String librosSeleccionado = jcbLibrosDisponibles.getSelectedItem().toString();
        if (librosSeleccionado.isEmpty() || librosSeleccionado.isBlank())
        {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un libro de la pestaña");
            return;
        }

        Libros libro = controladorLibros.buscarLibroPorTitulo(librosSeleccionado);
        int idLibroSeleccionado = libro.getId();

        String fechaInicio = txtFechaInicio.getText().toLowerCase().trim();
        {
            if(fechaInicio.isEmpty() || fechaInicio.isBlank())
            {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un fecha de inicio");
                return;
            }
        }
        String fechaDevolucion  = txtFechaDevolucion.getText().toLowerCase().trim();
        {
            if(fechaDevolucion.isEmpty() || fechaDevolucion.isBlank())
            {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un fecha de devolucion");
                return;
            }
        }

        Estudiante estudianteNuevo = controladorEstudiante.buscarEstudiantePorNombre(nombreEstudiante);
        int idEstudiante = estudianteNuevo.getId();

        java.sql.Date fechaInicioSolicitada;
        try
        {
            fechaInicioSolicitada = java.sql.Date.valueOf(fechaInicio);
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

        Date fechaDevolucionSolicitada;
        try
        {
            fechaDevolucionSolicitada = java.sql.Date.valueOf(fechaDevolucion);
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
        boolean devuelto = true;

        Prestamos p = new Prestamos();
        p.setId_estudiante(idEstudiante);
        p.setId_libro(idLibroSeleccionado);
        p.setFecha_prestamo(fechaInicioSolicitada);
        p.setFecha_devolucion(fechaDevolucionSolicitada);
        p.setDevuelto(devuelto);

        if (controladorPrestamo.insertarPrestamo(p))
        {
            JOptionPane.showMessageDialog(this, "Préstamo insertado correctamente");
            return;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Préstamo insertado correctamente");
        }
    }

    private void cancelarSolicitudLibro()
    {
        txtFechaInicio.setText("");
        txtFechaDevolucion.setText("");
    }

    private void filtrarSoloUsuario()
    {
        String nombreEstudiante  = txtNombreEstudiante.getText().toLowerCase().trim();
        if (nombreEstudiante.isEmpty() || nombreEstudiante.isBlank())
        {
            JOptionPane.showMessageDialog(this, "Debe llenar el nombre del estudiante");
            return;
        }

        Estudiante estudianteFiltrado = controladorEstudiante.buscarEstudiantePorNombre(nombreEstudiante);

        if (estudianteFiltrado == null)
        {
            JOptionPane.showMessageDialog(this, "Estudiante no existe");
            return;
        }
        int idEstudiante = estudianteFiltrado.getId();

        if (!controladorPrestamo.consultarPrestamoEspecificoPorEstudiante(modeloTablaPrestamos, idEstudiante))
        {
            JOptionPane.showMessageDialog(this, "No hay un estudiante asociado a ese préstamo");
            return;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Estudiante filtrado con éxito");
            controladorPrestamo.consultarPrestamoEspecificoPorEstudiante(modeloTablaPrestamos, idEstudiante);
        }

    }
}
