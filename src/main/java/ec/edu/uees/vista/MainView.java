package ec.edu.uees.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JTextField campoNombre;
    private JComboBox<String> comboDolencias;
    private JButton btnRegistrar;
    private JButton btnAtencion;
    private JLabel etiquetaVideo;
    private JButton btnAnterior, btnSiguiente, btnPausa;
    private JPanel panelVideo, panelReproductor;

    // Componentes para mostrar paciente actual
    private JPanel panelPacienteActual;
    private JLabel lblPacienteNombre;
    private JLabel lblPacienteDolencia;
    private JLabel lblPacientePrioridad;
    private JButton btnFinalizarAtencion;

    public MainView() {
        configurarUI();
    }

    private void configurarUI() {
        setTitle("Sistema de Salud");
        setSize(1000, 700); // Aumentado para dar más espacio
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de registro
        JPanel panelRegistro = new JPanel(new GridLayout(3, 2, 10, 10));
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registro de Pacientes"));
        campoNombre = new JTextField();
        comboDolencias = new JComboBox<>();
        btnRegistrar = new JButton("Registrar Paciente");
        panelRegistro.add(new JLabel("Nombre:"));
        panelRegistro.add(campoNombre);
        panelRegistro.add(new JLabel("Dolencia:"));
        panelRegistro.add(comboDolencias);
        panelRegistro.add(new JLabel());
        panelRegistro.add(btnRegistrar);

        // Panel de atención
        JPanel panelAtencion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelAtencion.setBorder(BorderFactory.createTitledBorder("Cola de Espera"));
        btnAtencion = new JButton("Atender Siguiente Paciente");
        panelAtencion.add(btnAtencion);

        // Panel para mostrar el paciente actual
        panelPacienteActual = new JPanel(new GridLayout(4, 2, 5, 5));
        panelPacienteActual.setBorder(BorderFactory.createTitledBorder("Paciente en Atención"));

        lblPacienteNombre = new JLabel("No hay paciente en atención");
        lblPacienteDolencia = new JLabel("");
        lblPacientePrioridad = new JLabel("");
        btnFinalizarAtencion = new JButton("Finalizar Atención");
        btnFinalizarAtencion.setEnabled(false); // Deshabilitado inicialmente

        panelPacienteActual.add(new JLabel("Nombre:"));
        panelPacienteActual.add(lblPacienteNombre);
        panelPacienteActual.add(new JLabel("Dolencia:"));
        panelPacienteActual.add(lblPacienteDolencia);
        panelPacienteActual.add(new JLabel("Prioridad:"));
        panelPacienteActual.add(lblPacientePrioridad);
        panelPacienteActual.add(new JLabel());
        panelPacienteActual.add(btnFinalizarAtencion);

        // Panel lateral izquierdo que contiene registro, atención y paciente actual
        JPanel panelIzquierdo = new JPanel(new GridLayout(3, 1, 10, 10));
        panelIzquierdo.add(panelRegistro);
        panelIzquierdo.add(panelAtencion);
        panelIzquierdo.add(panelPacienteActual);

        // Panel de videos (estructura mejorada)
        panelVideo = new JPanel(new BorderLayout());
        panelVideo.setBorder(BorderFactory.createTitledBorder("Información de Salud"));
        panelReproductor = new JPanel(new BorderLayout());

        etiquetaVideo = new JLabel("Seleccione un video", SwingConstants.CENTER);
        etiquetaVideo.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel controles = new JPanel();
        btnAnterior = new JButton("<< Anterior");
        btnSiguiente = new JButton("Siguiente >>");
        btnPausa = new JButton("Pausar");
        controles.add(btnAnterior);
        controles.add(btnPausa);
        controles.add(btnSiguiente);

        // Organización jerárquica clara
        panelVideo.add(etiquetaVideo, BorderLayout.NORTH);
        panelVideo.add(panelReproductor, BorderLayout.CENTER);
        panelVideo.add(controles, BorderLayout.SOUTH);

        // Agrega los paneles principales a la ventana
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelVideo, BorderLayout.CENTER);
    }

    // Método para actualizar la información del paciente actual en la interfaz
    public void actualizarPacienteActual(String nombre, String dolencia, int prioridad) {
        lblPacienteNombre.setText(nombre);
        lblPacienteDolencia.setText(dolencia);

        // Solo muestra prioridad si hay un paciente
        if (prioridad > 0) {
            lblPacientePrioridad.setText(String.valueOf(prioridad));
            btnFinalizarAtencion.setEnabled(true);
        } else {
            lblPacientePrioridad.setText("");
            btnFinalizarAtencion.setEnabled(false);
        }

        // Actualiza la interfaz inmediatamente
        panelPacienteActual.revalidate();
        panelPacienteActual.repaint();
    }

    // Método para asignar un ActionListener al botón de finalizar atención
    public void setFinalizarAtencionListener(ActionListener listener) {
        btnFinalizarAtencion.addActionListener(listener);
    }

    // Getters
    public JTextField getCampoNombre() { return campoNombre; }
    public JComboBox<String> getComboDolencias() { return comboDolencias; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnAtencion() { return btnAtencion; }
    public JLabel getEtiquetaVideo() { return etiquetaVideo; }
    public JButton getBtnAnterior() { return btnAnterior; }
    public JButton getBtnSiguiente() { return btnSiguiente; }
    public JButton getBtnPausa() { return btnPausa; }
    public JPanel getPanelVideo() { return panelVideo; }
    public JPanel getPanelReproductor() { return panelReproductor; }
    public JButton getBtnFinalizarAtencion() { return btnFinalizarAtencion; }
}