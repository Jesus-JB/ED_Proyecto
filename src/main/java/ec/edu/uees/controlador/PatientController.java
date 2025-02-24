package ec.edu.uees.controlador;

import ec.edu.uees.modelo.PriorityQueueManager;
import ec.edu.uees.vista.MainView;
import javax.swing.*;

public class PatientController {
    private final PriorityQueueManager modelo;
    private final MainView vista;
    private final VideoController videoController;

    public PatientController(PriorityQueueManager modelo, MainView vista, VideoController videoController) {
        this.modelo = modelo;
        this.vista = vista;
        this.videoController = videoController;
        configurarListeners();
    }

    private void configurarListeners() {
        vista.getBtnRegistrar().addActionListener(e -> registrarPaciente());
        vista.getBtnAtencion().addActionListener(e -> atenderPaciente());
    }

    private void registrarPaciente() {
        String nombre = vista.getCampoNombre().getText();
        String dolencia = (String) vista.getComboDolencias().getSelectedItem();

        if (!nombre.isEmpty() && dolencia != null) {
            modelo.agregarPaciente(nombre, dolencia);
            vista.getCampoNombre().setText("");
            JOptionPane.showMessageDialog(vista, "Paciente registrado!");
        } else {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
        }
    }

    private void atenderPaciente() {
        var paciente = modelo.atenderPaciente();
        if (paciente != null) {
            JOptionPane.showMessageDialog(vista,
                    "Atendiendo a: " + paciente.getNombre() +
                            "\nDolencia: " + paciente.getDolencia());
            videoController.iniciarReproduccion(); // Inicia la reproducción del video
        } else {
            JOptionPane.showMessageDialog(vista, "No hay pacientes en espera");
        }
    }
}