package ec.edu.uees.vista;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JTextField campoNombre;
    private JComboBox<String> comboDolencias;
    private JButton btnRegistrar;
    private JButton btnAtencion;
    private JLabel etiquetaVideo;
    private JButton btnAnterior, btnSiguiente, btnPausa;
    private JPanel panelVideo, panelReproductor; // Panel separado para el reproductor

    public MainView() {
        configurarUI();
    }

    private void configurarUI() {
        setTitle("Sistema de Salud");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de registro
        JPanel panelRegistro = new JPanel(new GridLayout(3, 2, 10, 10));
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
        JPanel panelAtencion = new JPanel();
        btnAtencion = new JButton("Atender Siguiente Paciente");
        panelAtencion.add(btnAtencion);

        // Panel de videos (estructura mejorada)
        panelVideo = new JPanel(new BorderLayout());
        panelReproductor = new JPanel(new BorderLayout()); // Panel dedicado al reproductor

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
        panelVideo.add(panelReproductor, BorderLayout.CENTER); // El reproductor ocupa el centro
        panelVideo.add(controles, BorderLayout.SOUTH);

        // Agrega los paneles principales a la ventana
        add(panelRegistro, BorderLayout.NORTH);
        add(panelAtencion, BorderLayout.WEST); // Cambiado a WEST para dejar espacio al video
        add(panelVideo, BorderLayout.CENTER); // El panel de video ahora es el elemento principal
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
    public JPanel getPanelReproductor() { return panelReproductor; } // Nuevo getter
}