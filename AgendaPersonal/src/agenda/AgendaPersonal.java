import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Aplicación de Agenda Personal con interfaz gráfica Swing
 * Permite agregar, ver y eliminar eventos con fecha, hora y descripción
 */
public class AgendaPersonal extends JFrame {
    
    // Componentes de la interfaz
    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JSpinner spinnerFecha;
    private JSpinner spinnerHora;
    private JTextField txtDescripcion;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnSalir;
    
    // Formateadores para fecha y hora
    private SimpleDateFormat formatoFecha;
    private SimpleDateFormat formatoHora;
    
    /**
     * Constructor principal - Inicializa la aplicación
     */
    public AgendaPersonal() {
        // Inicializar formateadores
        formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoHora = new SimpleDateFormat("HH:mm");
        
        // Configurar la ventana principal
        setTitle("Agenda Personal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar ventana
        
        // Inicializar componentes
        initComponents();
        setupSpinners();
        setupEventListeners();
        
        // Hacer visible la ventana
        setVisible(true);
    }
    
    /**
     * Inicializa todos los componentes de la interfaz gráfica
     */
    private void initComponents() {
        // Configurar layout principal
        setLayout(new BorderLayout());
        
        // Panel superior para entrada de datos
        JPanel panelEntrada = createPanelEntrada();
        add(panelEntrada, BorderLayout.NORTH);
        
        // Panel central para la tabla
        JPanel panelTabla = createPanelTabla();
        add(panelTabla, BorderLayout.CENTER);
        
        // Panel inferior para botones de acción
        JPanel panelBotones = createPanelBotones();
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel de entrada de datos
     */
    private JPanel createPanelEntrada() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Agregar Nuevo Evento"));
        panel.setPreferredSize(new Dimension(0, 120));
        
        // Etiquetas y campos
        panel.add(new JLabel("Fecha (dd/MM/yyyy):"));
        spinnerFecha = new JSpinner();
        panel.add(spinnerFecha);
        
        panel.add(new JLabel("Hora (HH:mm):"));
        spinnerHora = new JSpinner();
        panel.add(spinnerHora);
        
        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panel.add(txtDescripcion);
        
        return panel;
    }
    
    /**
     * Crea el panel que contiene la tabla de eventos
     */
    private JPanel createPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Eventos"));
        
        // Crear modelo de tabla con columnas específicas
        String[] columnas = {"Fecha", "Hora", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        // Crear tabla con el modelo
        tablaEventos = new JTable(modeloTabla);
        tablaEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEventos.getTableHeader().setReorderingAllowed(false);
        
        // Configurar ancho de columnas
        tablaEventos.getColumnModel().getColumn(0).setPreferredWidth(100); // Fecha
        tablaEventos.getColumnModel().getColumn(1).setPreferredWidth(80);  // Hora
        tablaEventos.getColumnModel().getColumn(2).setPreferredWidth(300); // Descripción
        
        // Agregar scroll pane
        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crea el panel de botones de acción
     */
    private JPanel createPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(0, 60));
        
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar Seleccionado");
        btnSalir = new JButton("Salir");
        
        // Configurar tamaño preferido para botones
        Dimension btnSize = new Dimension(150, 30);
        btnAgregar.setPreferredSize(btnSize);
        btnEliminar.setPreferredSize(btnSize);
        btnSalir.setPreferredSize(btnSize);
        
        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnSalir);
        
        return panel;
    }
    
    /**
     * Configura los JSpinner para fecha y hora con formatos específicos
     */
    private void setupSpinners() {
        // Configurar spinner de fecha
        Date fechaActual = new Date();
        SpinnerDateModel modeloFecha = new SpinnerDateModel(fechaActual, null, null, java.util.Calendar.DAY_OF_MONTH);
        spinnerFecha.setModel(modeloFecha);
        
        JSpinner.DateEditor editorFecha = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
        spinnerFecha.setEditor(editorFecha);
        
        // Configurar spinner de hora
        SpinnerDateModel modeloHora = new SpinnerDateModel(fechaActual, null, null, java.util.Calendar.MINUTE);
        spinnerHora.setModel(modeloHora);
        
        JSpinner.DateEditor editorHora = new JSpinner.DateEditor(spinnerHora, "HH:mm");
        spinnerHora.setEditor(editorHora);
    }
    
    /**
     * Configura los listeners para los eventos de botones
     */
    private void setupEventListeners() {
        // Listener para botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEvento();
            }
        });
        
        // Listener para botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEvento();
            }
        });
        
        // Listener para botón Salir
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirAplicacion();
            }
        });
        
        // Listener para Enter en campo descripción
        txtDescripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEvento();
            }
        });
    }
    
    /**
     * Agrega un nuevo evento a la tabla
     * Incluye validaciones requeridas
     */
    private void agregarEvento() {
        // Obtener y validar descripción
        String descripcion = txtDescripcion.getText().trim();
        
        // Validación: descripción no puede estar vacía
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "La descripción no puede estar vacía",
                "Validación",
                JOptionPane.WARNING_MESSAGE
            );
            txtDescripcion.requestFocus();
            return;
        }
        
        // Obtener fecha y hora de los spinners
        Date fecha = (Date) spinnerFecha.getValue();
        Date hora = (Date) spinnerHora.getValue();
        
        // Formatear fecha y hora según los requisitos
        String fechaFormateada = formatoFecha.format(fecha);
        String horaFormateada = formatoHora.format(hora);
        
        // Agregar nueva fila a la tabla
        Object[] fila = {fechaFormateada, horaFormateada, descripcion};
        modeloTabla.addRow(fila);
        
        // Limpiar campo descripción y dar foco
        txtDescripcion.setText("");
        txtDescripcion.requestFocus();
        
        // Mensaje opcional de confirmación (no requerido pero útil)
        // JOptionPane.showMessageDialog(this, "Evento agregado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Elimina el evento seleccionado de la tabla
     * Incluye validación de selección y confirmación del usuario
     */
    private void eliminarEvento() {
        int filaSeleccionada = tablaEventos.getSelectedRow();
        
        // Validación: debe haber una fila seleccionada
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Selecciona un evento primero",
                "Validación",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Pedir confirmación al usuario
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Eliminar el evento seleccionado?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        // Eliminar solo si el usuario confirmó
        if (respuesta == JOptionPane.YES_OPTION) {
            modeloTabla.removeRow(filaSeleccionada);
        }
    }
    
    /**
     * Cierra la aplicación de forma segura
     */
    private void salirAplicacion() {
        dispose(); // Cierra la ventana principal
        System.exit(0); // Termina la aplicación
    }
    
    /**
     * Método principal - Punto de entrada de la aplicación
     */
    public static void main(String[] args) {
        // Ejecutar en el EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgendaPersonal();
            }
        });
    }
}