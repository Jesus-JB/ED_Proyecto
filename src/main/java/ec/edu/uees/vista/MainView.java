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
    private JPanel panelVideo; // Panel para el reproductor de video

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

        // Panel de videos
        panelVideo = new JPanel(new BorderLayout()); // Inicializa el panel de video
        etiquetaVideo = new JLabel(" ", SwingConstants.CENTER);
        etiquetaVideo.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel controles = new JPanel();
        btnAnterior = new JButton("<< Anterior");
        btnSiguiente = new JButton("Siguiente >>");
        btnPausa = new JButton("Pausar");
        controles.add(btnAnterior);
        controles.add(btnPausa);
        controles.add(btnSiguiente);

        panelVideo.add(etiquetaVideo, BorderLayout.NORTH); // Añade la etiqueta al panel de video
        panelVideo.add(controles, BorderLayout.SOUTH); // Añade los controles al panel de video

        add(panelRegistro, BorderLayout.NORTH);
        add(panelAtencion, BorderLayout.CENTER);
        add(panelVideo, BorderLayout.SOUTH); // Añade el panel de video a la ventana principal
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
    public JPanel getPanelVideo() { return panelVideo; } // Método para obtener el panel de video
}