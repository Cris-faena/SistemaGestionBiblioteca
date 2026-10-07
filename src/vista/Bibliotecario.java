package vista;

import controlador.ControladorCategoria;
import controlador.ControladorLibros;
import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bibliotecario extends JFrame
{
    private JPanel PanelPrincipalBiblio;
    private JLabel lblCategorias;
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

    private int idCategoriaSeleccionado = -1;
    private int idLibroSeleccionado = -1;

    private DefaultTableModel modeloTablaCategoria;
    private DefaultTableModel modeloTablaLibros;

    private final ControladorCategoria controladorCategoria = new ControladorCategoria();
    private final ControladorLibros controladorLibros = new ControladorLibros();

    public Bibliotecario()
    {
        setTitle("Bibliotecario");
        setContentPane(PanelPrincipalBiblio);
        setSize(430, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        PanelPrincipalBiblio.setBackground(new Color(245, 222, 179));
        PanelSubLibros.setBackground(new Color(250, 240, 230));
        PanelSubCategorias.setBackground(new Color(250, 240, 230));

        jspTablaCategoria.setViewportView(tblCategoria);
        //jspTablaCategoria.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jspTablaCategoria.setPreferredSize(new Dimension(200, 300));
        jspTablaCategoria.setMaximumSize(new Dimension(200, 300));
        jspTablaCategoria.setMinimumSize(new Dimension(200, 300));

        jspSubLibros.setViewportView(tblLibros);
        //jspSubLibros.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jspSubLibros.setPreferredSize(new Dimension(760, 300));
        jspSubLibros.setMaximumSize(new Dimension(770, 300));
        jspSubLibros.setMinimumSize(new Dimension(760, 300));



        // Se agrega funcionalidades a los botones del panel de ingreso de categorías
        btnAgregarCategoria.addActionListener(event -> {agregarCategoria();});
        btnEditarCategoria.addActionListener(event -> {editarCategoria();});
        btnEliminarCategoria.addActionListener(event -> {eliminarCategoria();});
        btnLimpiarCategoria.addActionListener(event -> {limpiarCategoria();});
        btnListarCategorias.addActionListener(event -> {listarCategoria();});
        btnFiltrarCategoria.addActionListener(event -> {filtrarCategoria();});
        btnMostrarCategorias.addActionListener(event -> {String lista = String.join("\n",
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
        btnAgregarLibros.addActionListener(event -> {agregarLibro();});
        btnEditarLibros.addActionListener(event -> {editarLibro();});
        btnEliminarLibros.addActionListener(event -> {eliminarLibro();});
        btnLimpiarLibros.addActionListener(event -> {limpiarLibro();});
        btnListarLibros.addActionListener(event -> {listarLibro();});
        btnFiltrarLibro.addActionListener(event -> {filtrarLibros();});

        // Se cargan los JComboBox del panel
        cargarCategoriasJComboBoxCategoria();
        cargarCategoriasJComboBoxLibros();
        // Se inicializan las tablas del panel
        inicializarTablaCategorias();
        inicializarTablaLibros();
        // Se cargan las tablas de la BD.
        cargarTablaCategoria();
        cargarTablaLibros();

        /*PestañaPanelAdmin.add(PanelSubCategorias);
        PestañaPanelAdmin.add(PanelSubLibros);
        PestañaPanelAdmin.add(jspTablaCategoria);
        PestañaPanelAdmin.add(jspSubLibros);*/

    }
    // Acá termina el constructor

    /**
     * Método para crear una Tabla "categoría" con sus respectivos campos
     */
    private void inicializarTablaCategorias()
    {
        String[] columnas = {"ID_categoría", "Nombre_categoria"};
        modeloTablaCategoria = new DefaultTableModel(columnas, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        tblCategoria.setModel(modeloTablaCategoria);
        tblCategoria.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        tblCategoria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblCategoria.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
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
    private void cargarTablaCategoria()
    {
        modeloTablaCategoria.setRowCount(0); // limpia la tabla

        for (Categoria c : controladorCategoria.obtenerCategorias())
        {
            modeloTablaCategoria.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),

            });
        }
    }

    /**
     * Método que carga una tabla filtrada por loas especificaciones del usuario
     * @param categoria objeto Categoría que se pasa como parámetro para filtrar la búsqueda.
     */
    private void cargarTablaCategoriaFiltrada(Categoria categoria)
    {
        modeloTablaCategoria.setRowCount(0); // limpia la tabla

        List<Categoria> listaFiltradaID = new ArrayList<Categoria>();
        listaFiltradaID.add(categoria);

        for (Categoria c : listaFiltradaID)
        {
            modeloTablaCategoria.addRow(new Object[]{
                    c.getId(),
                    c.getNombre()
            });
        }
    }

    private void inicializarTablaLibros()
    {
        String[] columnas = {"ID_libro", "Título_libro", "Autor_libro", "ISBN_libro", "Editorial_libro", "Stock_libro", "ID_categoria"};
        modeloTablaLibros = new DefaultTableModel(columnas, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        tblLibros.setModel(modeloTablaLibros);
        tblLibros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tblLibros.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID_Libro
        tblLibros.getColumnModel().getColumn(0).setMinWidth(50);
        tblLibros.getColumnModel().getColumn(1).setPreferredWidth(205); // Titulo
        tblLibros.getColumnModel().getColumn(1).setMinWidth(205);
        tblLibros.getColumnModel().getColumn(2).setPreferredWidth(137);  // Autor
        tblLibros.getColumnModel().getColumn(2).setMinWidth(137);
        tblLibros.getColumnModel().getColumn(3).setPreferredWidth(100);  // ISBN
        tblLibros.getColumnModel().getColumn(4).setPreferredWidth(100);   // Editorial
        tblLibros.getColumnModel().getColumn(5).setPreferredWidth(80);  // Stock
        tblLibros.getColumnModel().getColumn(5).setMinWidth(80);
        tblLibros.getColumnModel().getColumn(6).setPreferredWidth(80);  // Id_categoría
        tblLibros.getColumnModel().getColumn(6).setMinWidth(80);

        tblLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblLibros.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
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

    private void cargarTablaLibros()
    {
        modeloTablaLibros.setRowCount(0); // limpia la tabla

        for (Libros l : controladorLibros.obtenerTodosLibros())
        {
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

    private void cargarTablaLibrosFiltrada(Libros libros)
    {
        modeloTablaLibros.setRowCount(0); // limpia la tabla

        List<Libros> listaFiltradaID = new ArrayList<Libros>();
        listaFiltradaID.add(libros);

        for (Libros l : listaFiltradaID)
        {
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

    /**
     * Método que carga los valores iniciales del JComboBOx del Panel "categorías"
     */
    private void cargarCategoriasJComboBoxCategoria()
    {
        jcbCategorias.removeAllItems();
        jcbCategorias.addItem("ID");
        jcbCategorias.addItem("Nombre");
    }

    /**
     * Método que carga los valores iniciales del JComboBOx del Panel "libros"
     */
    private void cargarCategoriasJComboBoxLibros()
    {
        jcbLibros.removeAllItems();
        jcbLibros.addItem("ID");
        jcbLibros.addItem("Autor");
    }

    // ===================== AGREGAR CATEGORÍA =====================
    /**
     * Método que agrega una nueva categoría a la BD
     */
    private void agregarCategoria()
    {
        String categoriaStr =  txtCategoriaSub.getText().toUpperCase();

        if (categoriaStr.isEmpty() || categoriaStr.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Debe ingresar una categoria");
            return;
        }

        CategoriaLibros categoria;

        try
        {
            categoria = CategoriaLibros.valueOf(categoriaStr);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, "Esa categoría no existe. Intente nuevamente");
            return;
        }
        Categoria c = new Categoria();  // crea una nueva instancia de "categoria", llamada "c".
        // asigna los valores para "c"
        c.setNombre(categoria);

        // Llama al método que crea o inserta un usuario nuevo en la base de datos.
        if (controladorCategoria.crearCategoria(c))
        {
            cargarTablaCategoria();
            JOptionPane.showMessageDialog(this, "Categoría agregada correctamente");
            cargarTablaCategoria();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al agregar la categoría");
        }
    }
    // ===================== EDITAR CATEGORÍA =====================
    /**
     * Método que permite editar una categoría.
     * Por motivos que utiliza valores inmutables, no se permitirá editarlos
     * Se implementó sólo por fines académicos.
     */
    private void editarCategoria()
    {
        if (idCategoriaSeleccionado <= 0)
        {
            JOptionPane.showMessageDialog(null,
                    "Esta categoría es INMUTABLE. Pruebe agregar o eliminar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idCategoriaSeleccionado == -1)
        {
            JOptionPane.showMessageDialog(this, "Seleccione una categoria para editar");
            return;
        }

        String nombreTxt = txtCategoriaSub.getText().toUpperCase();

        if (nombreTxt.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "El nombre de la categoría no puede estar vacío");
        }

        CategoriaLibros categoria;
        try
        {
            categoria = CategoriaLibros.valueOf(nombreTxt);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, "Esa categoría no existe. Intente nuevamente");
            return;
        }

        Categoria c = new Categoria();
        c.setId(idCategoriaSeleccionado);
        c.setNombre(categoria);

        if (controladorCategoria.editarCategoria(c))
        {
            JOptionPane.showMessageDialog(this, "Categoría editada correctamente");
            cargarTablaCategoria();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al editar la categoría");
        }
    }

    // ===================== ELIMINAR CATEGORÍA =====================

    /**
     * Método que permite eliminar la categoría seleccionada.
     */
    private void eliminarCategoria()
    {
        // Si no se ha seleccionado nada, lanza este mensaje emergente
        if (idCategoriaSeleccionado == -1)
        {
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
            if (eliminado)
            {
                JOptionPane.showMessageDialog(null,
                        "Categoría eliminada correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            // En caso contrario, lanza el cuadro emergente con la mala noticia.
            else
            {
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar la categoría",
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
    // ===================== LIMPIAR CATEGORÍA =====================

    /**
     * Método que permite limpiar todos los campos de la tabla categorías.
     */
    private void limpiarCategoria()
    {
        txtCategoriaSub.setText("");
        tblCategoria.clearSelection();
        idCategoriaSeleccionado = -1;
        modeloTablaCategoria.setRowCount(0);
    }

    // ===================== FILTRAR CATEGORÍA =====================

    /**
     * Método que filtra la tabla categoría por ID o Nombre
     */
    private void filtrarCategoria()
    {
        String categoriaSeleccionada = jcbCategorias.getSelectedItem().toString();
        if (categoriaSeleccionada.isEmpty() || categoriaSeleccionada == null)
        {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor en la pestaña para filtrar");
            return;
        }
        // Dependiendo de la selección del usuario, se filtra la tabla por id o categoría

            if (categoriaSeleccionada.equals("ID"))
            {
                try
                {
                    Categoria c = new Categoria();
                    int idCategoria = Integer.parseInt(txtFiltrarCat.getText());
                    c =  controladorCategoria.buscarPorId(idCategoria);
                    cargarTablaCategoriaFiltrada(c);
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

            if (categoriaSeleccionada.equals("Nombre"))
            {
                try
                {
                    Categoria c = new Categoria();
                    String nombre = txtFiltrarCat.getText().toUpperCase();
                    c = controladorCategoria.buscarPorCategoria(nombre);
                    cargarTablaCategoriaFiltrada(c);
                }
                catch (IllegalArgumentException e)
                {
                    JOptionPane.showMessageDialog(this, "Los valores ingresados no son válidos. Inténtelo nuevamente");
                    return;
                }
                catch (NullPointerException e)
                {
                    JOptionPane.showMessageDialog(this, "El nombre ingresado no existe. Presione 'categorias' para obtener valores aceptados");
                    return;
                }
            }
    }

    // ===================== LISTAR CATEGORÍA =====================

    /**
     * Método que carga una tabla actualizada con todas las categorías
     */
    private void listarCategoria()
    {
        List<Categoria> todasLasCategorias = controladorCategoria.obtenerCategorias();
        if (todasLasCategorias.isEmpty())
        {
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
    private void agregarLibro()
    {
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

        if (controladorLibros.comprobarISBNLibro(isbn))
        {
            JOptionPane.showMessageDialog(this,
                    "El ISBN '" + isbn + "' ya existe.\nPor favor ingresa otro ISBN.",
                    "ISBN duplicado",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (editorial.isEmpty() || editorial == null)
        {
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

        if (controladorLibros.insertarLibro(l))
        {
            cargarTablaLibros();
            JOptionPane.showMessageDialog(this, "Libro agregado correctamente");
            cargarTablaCategoria();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al agregar el libro");
        }
    }

    // ===================== EDITAR LIBRO =====================

    /**
     * Método que permite editar un libro que ya fue ingresado a la BD
     */
    private void editarLibro()
    {
        if (idLibroSeleccionado == -1)
        {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para editar");
            return;
        }

        String titulo = txtTituloLibros.getText().toLowerCase().trim();
        String autor = txtAutor.getText().toLowerCase().trim();
        String isbn = txtIsbn.getText().toLowerCase().trim();
        String editorial = txtEditorial.getText().toLowerCase().trim();
        int stock;
        int idCategoria;

        if (titulo.isEmpty() || titulo == null)
        {
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

        if (editorial.isEmpty() || editorial == null)
        {
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

        if (controladorLibros.editarLibro(l))
        {
            JOptionPane.showMessageDialog(this, "libro editado correctamente");
            cargarTablaLibros();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al editar el libro");
        }
    }

    // ===================== ELIMINAR LIBRO =====================

    /**
     * Método para eliminar un libro de la base de datos.
     * Debe seleccionar una fila de la tabla para eliminarla.
     */
    private void eliminarLibro()
    {
        // Si no se ha seleccionado nada, lanza este mensaje emergente
        if (idLibroSeleccionado == -1)
        {
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
            if (eliminado)
            {
                JOptionPane.showMessageDialog(null,
                        "libro eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarTablaLibros();
            }
            // En caso contrario, lanza el cuadro emergente con la mala noticia.
            else
            {
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar el libro",
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
    private void limpiarLibro()
    {
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
    private void listarLibro()
    {
        List<Libros> todosLosLibros = controladorLibros.obtenerTodosLibros();
        if (todosLosLibros.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "No se encontraron libros almacenadas en la BD");
            return;
        }
        cargarTablaLibros();
    }

    // ===================== FILTRAR LIBROS POR ID O AUTOR =====================

    private void filtrarLibros()
    {
        String filtroSeleccionado = jcbLibros.getSelectedItem().toString();
        if (filtroSeleccionado.isEmpty() || filtroSeleccionado == null)
        {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor en la pestaña para filtrar");
            return;
        }

        // Dependiendo de la selección del usuario, se filtra la tabla por "id" o autor.
        if (filtroSeleccionado.equals("ID"))
        {
            try
            {
                Libros l = new Libros();
                int idLibro = Integer.parseInt(txtFiltrarLibros.getText());
                l =  controladorLibros.buscarLibroPorId(idLibro);
                cargarTablaLibrosFiltrada(l);
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

        if (filtroSeleccionado.equals("Autor"))
        {
            try
            {
                Libros l = new Libros();
                String autor = txtFiltrarLibros.getText().toLowerCase().trim();
                l = controladorLibros.buscarLibroPorAutor(autor);
                cargarTablaLibrosFiltrada(l);
            }
            catch (IllegalArgumentException e)
            {
                JOptionPane.showMessageDialog(this, "Los valores ingresados no son válidos. Inténtelo nuevamente");
            }
            catch (NullPointerException e)
            {
                JOptionPane.showMessageDialog(this, "El autor ingresado no existe. Intente por ID o con otro nombre de autor");
            }
        }
    }
}

