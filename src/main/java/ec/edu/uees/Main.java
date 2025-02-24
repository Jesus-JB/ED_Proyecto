package ec.edu.uees;

import ec.edu.uees.controlador.PatientController;
import ec.edu.uees.controlador.VideoController;
import ec.edu.uees.modelo.PriorityQueueManager;
import ec.edu.uees.modelo.VideoPlaylist;
import ec.edu.uees.util.VideoUtil;
import ec.edu.uees.vista.MainView;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView vista = new MainView();
            PriorityQueueManager modeloPacientes = new PriorityQueueManager();
            VideoPlaylist modeloVideos = new VideoPlaylist();

            // Configurar combobox de dolencias
            modeloPacientes.getDolencias().keySet().forEach(dolencia ->
                    vista.getComboDolencias().addItem(dolencia)
            );

            // Copiar videos a una carpeta temporal
            try {
                Path carpetaVideos = Path.of("videos"); // Carpeta temporal para los videos
                Files.createDirectories(carpetaVideos); // Crea la carpeta si no existe

                // Copia cada video a la carpeta temporal
                VideoUtil.copiarVideo("videoMessi.mp4", carpetaVideos.resolve("videoMessi.mp4"));
                VideoUtil.copiarVideo("videoNescafe.mp4", carpetaVideos.resolve("videoNescafe.mp4"));
                VideoUtil.copiarVideo("videoPSN.mp4", carpetaVideos.resolve("videoPSN.mp4"));

                System.out.println("Videos copiados a: " + carpetaVideos.toAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error copiando videos: " + e.getMessage());
                System.exit(1); // Sale de la aplicación si no se pueden copiar los videos
            }

            VideoController videoController = new VideoController(modeloVideos, vista);
            new PatientController(modeloPacientes, vista, videoController);

            vista.setVisible(true);
        });
    }
}