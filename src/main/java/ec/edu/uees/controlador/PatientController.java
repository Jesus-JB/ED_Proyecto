package ec.edu.uees.controlador;

import ec.edu.uees.modelo.Paciente;
import ec.edu.uees.modelo.PriorityQueueManager;
import ec.edu.uees.vista.MainView;
import javax.swing.*;

public class PatientController {
    private final PriorityQueueManager modelo;
    private final MainView vista;
    private final VideoController videoController;
    private Paciente pacienteActual; // Añadido para rastrear el paciente actual

    public PatientController(PriorityQueueManager modelo, MainView vista, VideoController videoController) {
        this.modelo = modelo;
        this.vista = vista;
        this.videoController = videoController;
        this.pacienteActual = null; // Inicializado como null
        configurarListeners();
        actualizarEstadoPaciente(); // Actualiza la vista inicial
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

    public void finalizarAtencion() {
        if (pacienteActual != null) {
            JOptionPane.showMessageDialog(vista,
                    "Atención finalizada para: " + pacienteActual.getNombre(),
                    "Atención Finalizada", JOptionPane.INFORMATION_MESSAGE);
            pacienteActual = null;
            actualizarEstadoPaciente();
        }
    }

    private void atenderPaciente() {
        pacienteActual = modelo.atenderPaciente();
        if (pacienteActual != null) {
            JOptionPane.showMessageDialog(vista,
                    "Atendiendo a: " + pacienteActual.getNombre() +
                            "\nDolencia: " + pacienteActual.getDolencia());

            actualizarEstadoPaciente(); // Actualiza la información en la vista
            videoController.iniciarReproduccion(); // Inicia la reproducción del video
        } else {
            JOptionPane.showMessageDialog(vista, "No hay pacientes en espera");
            // También actualizamos para mostrar que no hay paciente actual
            pacienteActual = null;
            actualizarEstadoPaciente();
        }
    }

    // Método para actualizar la información del paciente en la interfaz
    private void actualizarEstadoPaciente() {
        if (pacienteActual != null) {
            vista.actualizarPacienteActual(
                    pacienteActual.getNombre(),
                    pacienteActual.getDolencia(),
                    pacienteActual.getPrioridad()
            );
        } else {
            vista.actualizarPacienteActual("No hay paciente en atención", "", 0);
        }
    }

    // Getter para el paciente actual (podría ser útil para otras clases)
    public Paciente getPacienteActual() {
        return pacienteActual;
    }


}