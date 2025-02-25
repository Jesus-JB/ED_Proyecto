package ec.edu.uees;

import ec.edu.uees.controlador.PatientController;
import ec.edu.uees.controlador.VideoController;
import ec.edu.uees.modelo.PriorityQueueManager;
import ec.edu.uees.modelo.VideoPlaylist;
import ec.edu.uees.vista.MainView;

import javax.swing.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Inicializar modelos
            PriorityQueueManager queueManager = new PriorityQueueManager();
            VideoPlaylist videoPlaylist = new VideoPlaylist();

            // Inicializar vista
            MainView vista = new MainView();

            // Cargar las dolencias en el ComboBox
            JComboBox<String> comboDolencias = vista.getComboDolencias();
            for (Map.Entry<String, Integer> entry : queueManager.getDolencias().entrySet()) {
                comboDolencias.addItem(entry.getKey());
            }

            // Inicializar controladores
            VideoController videoController = new VideoController(videoPlaylist, vista);
            PatientController patientController = new PatientController(queueManager, vista, videoController);

            // Configurar el listener para finalizar atención
            vista.setFinalizarAtencionListener(e -> {
                // Cuando se finaliza la atención, se detiene el video y se limpia el paciente actual
                videoController.detenerReproduccion(); // Este método debe ser añadido a VideoController
                patientController.finalizarAtencion(); // Este método debe ser añadido a PatientController
            });

            // Mostrar la ventana
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    }
}