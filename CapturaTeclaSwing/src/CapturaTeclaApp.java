import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Aplicación que demuestra la captura de la tecla 'C' en Java Seing
 * Permite marcar/desmarcar tareas presionando la tecla C
*/
public class CapturaTeclaApp extends JFrame{
    
    //Componentes de la interfaz
    private JList<String> listaTareas;
    private DefaultListModel<String> modeloLista;
    private JButton btnAgregar;
    private JLabel lblInstrucciones;
    
    /**
     * Contructor: Inicializa la ventana y sus componentes
     */
    public CapturaTeclaApp(){
        configurarVentana();
        inicializarComponentes();
        configurarCapturaTecla();
        setVisible(true);
    }
    
    /**
     * Configurar las propiedades básicas de la ventana
     */
    private void configurarVentana(){
        setTitle("Capturar de Tecla 'C' - Demo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// Centra la ventana
        setLayout(new BorderLayout(10, 10));
        
    }
    
    /**
     * Crea e inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes(){
        //Panel principal con margenes
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        //===PANEL SUPERIOR: Instrucciones ===
        lblInstrucciones = new JLabel(
        "<html><b>Instrucciones:</b> Selecciona na tarea y presiona la tecla <b>'C'<b/b>" +
                "Para marcarla como completada(✓) o pendientes</html>"
        );
        lblInstrucciones.setForeground(new Color(50, 50, 150));
        panelPrincipal.add(lblInstrucciones, BorderLayout.NORTH);
        
        //===PANEL CENTRAL: Lista de tareas ===
        modeloLista = new DefaultListModel<>();
        //Agragamos tareas de ejemplo
        modeloLista.addElement("Estudiar Java Swing");
        modeloLista.addElement("Practicar KeyListeners");
        modeloLista.addElement("Completar el ejercicio");
        modeloLista.addElement("Revisar documentación");
        
        listaTareas = new JList<>(modeloLista);
        listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTareas.setFont(new Font("Arial", Font.PLAIN, 14));
        listaTareas.setSelectedIndex(0); // Selecciona el primer elemento
        
        //ScrollPane para lista(permite scroll si hay muchos elementos)
        JScrollPane scrollPane = new JScrollPane(listaTareas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Tareas"));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        //===PANEL INFERIOR: Botón===
        JPanel panelBoton= new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAgregar= new JButton("Agregar Nueva Tarea");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 12));
        
        //Acción del botón: agregar nueva tarea
        btnAgregar.addActionListener(e -> agregarNuevaTarea());
        
        panelBoton.add(btnAgregar);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);
        
        //Agregar panel principal a la ventaba
        add(panelPrincipal);    
    }
    
    /**
     * Configurar la captura de la tecla 'C' usando Key Bindings
     * Este es el método RECOMENDADO en Swing(mejor que KeyListener)
     */
    private void configurarCapturaTecla(){
        //InputMap: Mapea una combinación de teclas a un nombre de acción
        InputMap inputMap = listaTareas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        //ActionMap: Asocia el nobre de acción con el código a ejecutar
        ActionMap actionMap = listaTareas.getActionMap();
        
        //Definamos la tecla a capturar: 'C'
         KeyStroke teclaC = KeyStroke.getKeyStroke(KeyEvent.VK_C, 0);
        
        //Nombre identificador de la acción
        String accionMarcar = "marcarTarea";
        
        //Asociamos la tecla 'C' en el nombre de acción
        inputMap.put(teclaC, accionMarcar);
        
        //Definamos que hacer cuando se presiona 'C'
        actionMap.put(accionMarcar, new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                marcarTareaSeleecionada();
            }
        });
        
        //Alternativa: Tambien podriamos usar KeyListener
        //ListaTareas.addKeyListener(new KeyAdapter(){
        //@Override
        //public void keyPressed(KeyEvent e){
        //if (e. getKeyCode()===KeyEvent.VK_C){
        // marcarTareasSeleccionada();
        //     }
        //   }
        // });
    }
    
    /**
     * Método que se ejecuta cuando se presiona la tecla 'C'
     * Marcar o desmarcar la tarea seleccionada con un símbolo
     */
    private void marcarTareaSeleecionada(){
        int indiceSeleccionado = listaTareas.getSelectedIndex();
        
        //Verificar que hay un elemento seleccionado
        if (indiceSeleccionado == -1){
         System.out.println("⚠️ No hay ninguna tarea seleccionada");
         JOptionPane.showMessageDialog(
         this,
           "Por favor, selecciona una tarea primero",
           "Aviso",
           JOptionPane.WARNING_MESSAGE
         );
        return;
    }
    //Obtener el texto actual de la tarea
    String tareaActual =modeloLista.getElementAt(indiceSeleccionado );
    String nuevaTarea;
    
   //Alternar estado: si tiene lo quitamos, si no lo agregamos
    if (tareaActual.startsWith("✓")){
        //Ya está marcada, la desmarcamos
        nuevaTarea = tareaActual.substring(2);//Quitamos "✓ "
        System.out.println("x Tarea desmarcada: " + nuevaTarea);
    } else {
    //No está marcada, lo marcamos
    nuevaTarea =  "✓ " + tareaActual;
    System.out.println("✓ Tarea marcada: " + tareaActual);
    }
    
    //Actualizar el elemento en la lista
    modeloLista.set(indiceSeleccionado, nuevaTarea);
    
    //Mantener la selección en el mismo elemento
    listaTareas.setSelectedIndex(indiceSeleccionado);       
}
/**
 * Agregar una nueva tarea a la lista mediante un diálogo
 */

private void agregarNuevaTarea(){
    String nuevaTarea = JOptionPane.showInputDialog(
        this,
        "Escribe el nombre de la nueva tarea:",
        "Agregar Tarea",
        JOptionPane.PLAIN_MESSAGE
    );

    //Verificar que el ususuario escribió algo y no calceló
    if(nuevaTarea !=null && !nuevaTarea.trim(). isEmpty()){
        modeloLista.addElement(nuevaTarea.trim());
        System.out.println("➕ Nueva tarea agregada: " + nuevaTarea);

        //Seleccionar la tarea recién agregada
        listaTareas.setSelectedIndex(modeloLista.getSize() -1);

    }
}
/**
 * Método main: Punto de entrada de la aplicación
 */
 public static void main(String[] args) {
        // Ejecutar la interfaz en el Event Dispatch Thread (EDT)
        // Esto es OBLIGATORIO en Swing para evitar problemas de concurrencia
        SwingUtilities.invokeLater(() -> {
            try {
                // Intentar usar el Look and Feel del sistema operativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Si falla, usar el Look and Feel por defecto de Swing
                System.out.println("No se pudo cargar el Look and Feel del sistema");
            }
            
            // Crear y mostrar la ventana
            new CapturaTeclaApp();
        });
    }
}