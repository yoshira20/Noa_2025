package gestiondatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GestionDatos extends JFrame {
    
    // Componentes de la interfaz
    private JTextField campoTexto;
    private JButton botonAgregar;
    private JButton botonLimpiar;
    private JButton botonEliminar;
    private JList listaDatos;
    private DefaultListModel modeloLista;
    private JLabel etiquetaInstruccion;
    private JLabel etiquetaSeleccion;
    private JLabel etiquetaContador;
    
    // Constructor
    public GestionDatos() {
        inicializarComponentes();
        configurarVentana();
        agregarEventos();
    }
    
    private void inicializarComponentes() {
        // Inicializar componentes
        campoTexto = new JTextField(20);
        botonAgregar = new JButton("Agregar");
        botonLimpiar = new JButton("Limpiar Todo");
        botonEliminar = new JButton("Eliminar Seleccionado");
        
        // Modelo para la lista (sin generics para máxima compatibilidad)
        modeloLista = new DefaultListModel();
        listaDatos = new JList(modeloLista);
        listaDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Etiquetas
        etiquetaInstruccion = new JLabel("Ingrese un dato para agregar:");
        etiquetaSeleccion = new JLabel("Elemento seleccionado: Ninguno");
        etiquetaContador = new JLabel("Total de elementos: 0");
        
        // Configurar estilo de componentes
        configurarEstilos();
    }
    
    private void configurarEstilos() {
        // Configurar fuentes
        Font fuentePrincipal = new Font("Arial", Font.PLAIN, 12);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 14);
        
        etiquetaInstruccion.setFont(fuenteTitulo);
        etiquetaSeleccion.setFont(fuentePrincipal);
        etiquetaContador.setFont(fuentePrincipal);
        
        // Colores de botones
        botonAgregar.setBackground(new Color(76, 175, 80));
        botonAgregar.setForeground(Color.WHITE);
        botonLimpiar.setBackground(new Color(255, 152, 0));
        botonLimpiar.setForeground(Color.WHITE);
        botonEliminar.setBackground(new Color(244, 67, 54));
        botonEliminar.setForeground(Color.WHITE);
        
        // Configurar lista
        listaDatos.setFont(fuentePrincipal);
    }
    
    private void configurarVentana() {
        // Configurar ventana principal
        setTitle("Aplicacion de Gestion de Datos - Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior - Entrada de datos
        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.add(etiquetaInstruccion);
        panelSuperior.add(campoTexto);
        panelSuperior.add(botonAgregar);
        
        // Panel central - Lista de datos
        JPanel panelCentral = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(listaDatos);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior - Controles y información
        JPanel panelInferior = new JPanel(new GridLayout(3, 1, 5, 5));
        
        // Sub-panel para botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonEliminar);
        panelBotones.add(botonLimpiar);
        
        panelInferior.add(etiquetaSeleccion);
        panelInferior.add(etiquetaContador);
        panelInferior.add(panelBotones);
        
        // Agregar paneles a la ventana principal
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        
        // Configurar tamaño y centrar
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(true);
    }
    
    private void agregarEventos() {
        // Evento para botón Agregar
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
        });
        
        // Evento para botón Limpiar
        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarTodo();
            }
        });
        
        // Evento para botón Eliminar
        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarSeleccionado();
            }
        });
        
        // Evento para Enter en el campo de texto
        campoTexto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
        });
        
        // Evento para selección en la lista
        listaDatos.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarSeleccion();
                }
            }
        });
        
        // Inicializar estado
        botonEliminar.setEnabled(false);
    }
    
    private void agregarDato() {
        String texto = campoTexto.getText().trim();
        
        // Validar entrada vacía
        if (texto.equals("") || texto.length() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese un dato valido.", 
                "Entrada Vacia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Verificar si el dato ya existe
        boolean existe = false;
        for (int i = 0; i < modeloLista.getSize(); i++) {
            if (modeloLista.getElementAt(i).toString().equals(texto)) {
                existe = true;
                break;
            }
        }
        
        if (existe) {
            JOptionPane.showMessageDialog(this, 
                "El dato '" + texto + "' ya existe en la lista.", 
                "Dato Duplicado", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Agregar dato a la lista
        modeloLista.addElement(texto);
        campoTexto.setText("");
        campoTexto.requestFocus();
        actualizarContador();
        
        // Mensaje de confirmación
        JOptionPane.showMessageDialog(this, 
            "Dato agregado exitosamente: " + texto, 
            "Exito", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void limpiarTodo() {
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "Esta seguro de que desea limpiar todos los datos?", 
            "Confirmar Limpieza", 
            JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            modeloLista.clear();
            campoTexto.setText("");
            actualizarContador();
            actualizarSeleccion();
            JOptionPane.showMessageDialog(this, 
                "Todos los datos han sido eliminados.", 
                "Limpieza Completa", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void eliminarSeleccionado() {
        int indiceSeleccionado = listaDatos.getSelectedIndex();
        
        if (indiceSeleccionado != -1) {
            String elementoSeleccionado = modeloLista.getElementAt(indiceSeleccionado).toString();
            int respuesta = JOptionPane.showConfirmDialog(this, 
                "Desea eliminar '" + elementoSeleccionado + "'?", 
                "Confirmar Eliminacion", 
                JOptionPane.YES_NO_OPTION);
            
            if (respuesta == JOptionPane.YES_OPTION) {
                modeloLista.remove(indiceSeleccionado);
                actualizarContador();
                actualizarSeleccion();
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un elemento para eliminar.", 
                "Sin Seleccion", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void actualizarSeleccion() {
        Object elementoSeleccionado = listaDatos.getSelectedValue();
        if (elementoSeleccionado != null) {
            etiquetaSeleccion.setText("Elemento seleccionado: " + elementoSeleccionado.toString());
            botonEliminar.setEnabled(true);
        } else {
            etiquetaSeleccion.setText("Elemento seleccionado: Ninguno");
            botonEliminar.setEnabled(false);
        }
    }
    
    private void actualizarContador() {
        etiquetaContador.setText("Total de elementos: " + modeloLista.getSize());
    }
    
    // Método principal
    public static void main(String[] args) {
        // Crear y mostrar la aplicación
        GestionDatos app = new GestionDatos();
        app.setVisible(true);
    }
}