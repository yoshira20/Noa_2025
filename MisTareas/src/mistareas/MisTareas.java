package mistareas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Clase para representar una tarea
class Tarea {
    private String descripcion;
    private boolean completada;
    
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public boolean isCompletada() {
        return completada;
    }
    
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
    
    @Override
    public String toString() {
        return completada ? "✓ " + descripcion : "○ " + descripcion;
    }
}

// Renderer personalizado para la lista
class TareaListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, 
            int index, boolean isSelected, boolean cellHasFocus) {
        
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        if (value instanceof Tarea) {
            Tarea tarea = (Tarea) value;
            setText(tarea.toString());
            
            if (tarea.isCompletada()) {
                setFont(getFont().deriveFont(Font.ITALIC));
                if (!isSelected) {
                    setForeground(Color.GRAY);
                }
            } else {
                setFont(getFont().deriveFont(Font.PLAIN));
                if (!isSelected) {
                    setForeground(Color.BLACK);
                }
            }
        }
        
        return this;
    }
}

// Clase principal de la aplicación
public class MisTareas extends JFrame {
    
    private DefaultListModel<Tarea> modeloLista;
    private JList<Tarea> listaTareas;
    private JTextField campoNuevaTarea;
    private JButton botonAnadir, botonCompletar, botonEliminar;
    
    public MisTareas() {
        inicializarComponentes();
        configurarEventos();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        // Modelo de la lista
        modeloLista = new DefaultListModel<Tarea>();
        
        // Lista de tareas
        listaTareas = new JList<Tarea>(modeloLista);
        listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTareas.setCellRenderer(new TareaListCellRenderer());
        listaTareas.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Campo de texto
        campoNuevaTarea = new JTextField(20);
        campoNuevaTarea.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Botones
        botonAnadir = new JButton("Añadir Tarea");
        botonCompletar = new JButton("Marcar Completada");
        botonEliminar = new JButton("Eliminar Tarea");
        
        // Estilo de botones
        configurarBoton(botonAnadir, new Color(46, 125, 50));
        configurarBoton(botonCompletar, new Color(251, 140, 0));
        configurarBoton(botonEliminar, new Color(211, 47, 47));
    }
    
    private void configurarBoton(JButton boton, Color color) {
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
    }
    
    private void configurarEventos() {
        // Añadir tarea con botón
        botonAnadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anadirTarea();
            }
        });
        
        // Añadir tarea con Enter
        campoNuevaTarea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anadirTarea();
            }
        });
        
        // Marcar como completada
        botonCompletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marcarComoCompletada();
            }
        });
        
        // Eliminar tarea
        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarTarea();
            }
        });
        
        // Doble clic para completar
        listaTareas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    marcarComoCompletada();
                }
            }
        });
        
        // Tecla Delete para eliminar
        listaTareas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    eliminarTarea();
                }
            }
        });
    }
    
    private void configurarVentana() {
        setTitle("Mis Tareas - Gestor Personal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Nueva Tarea"));
        panelSuperior.add(campoNuevaTarea, BorderLayout.CENTER);
        panelSuperior.add(botonAnadir, BorderLayout.EAST);
        
        // Panel central
        JScrollPane scrollPane = new JScrollPane(listaTareas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Tareas"));
        scrollPane.setPreferredSize(new Dimension(400, 300));
        
        // Panel inferior
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonCompletar);
        panelBotones.add(botonEliminar);
        
        // Agregar a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        
        // Configurar ventana
        pack();
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Foco en el campo de texto
        campoNuevaTarea.requestFocus();
    }
    
    private void anadirTarea() {
        String descripcion = campoNuevaTarea.getText().trim();
        
        if (!descripcion.isEmpty()) {
            Tarea nuevaTarea = new Tarea(descripcion);
            modeloLista.addElement(nuevaTarea);
            campoNuevaTarea.setText("");
            campoNuevaTarea.requestFocus();
            
            JOptionPane.showMessageDialog(this, 
                "Tarea añadida: " + descripcion, 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese una descripción para la tarea.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void marcarComoCompletada() {
        int indiceSeleccionado = listaTareas.getSelectedIndex();
        
        if (indiceSeleccionado != -1) {
            Tarea tareaSeleccionada = modeloLista.getElementAt(indiceSeleccionado);
            tareaSeleccionada.setCompletada(!tareaSeleccionada.isCompletada());
            
            listaTareas.repaint();
            
            String estado = tareaSeleccionada.isCompletada() ? "completada" : "pendiente";
            JOptionPane.showMessageDialog(this, 
                "Tarea marcada como " + estado, 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione una tarea para marcar.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void eliminarTarea() {
        int indiceSeleccionado = listaTareas.getSelectedIndex();
        
        if (indiceSeleccionado != -1) {
            Tarea tareaSeleccionada = modeloLista.getElementAt(indiceSeleccionado);
            
            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea eliminar la tarea:\n'" + 
                tareaSeleccionada.getDescripcion() + "'?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (respuesta == JOptionPane.YES_OPTION) {
                modeloLista.removeElementAt(indiceSeleccionado);
                JOptionPane.showMessageDialog(this, 
                    "Tarea eliminada correctamente", 
                    "Información", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione una tarea para eliminar.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MisTareas().setVisible(true);
            }
        });
    }
}