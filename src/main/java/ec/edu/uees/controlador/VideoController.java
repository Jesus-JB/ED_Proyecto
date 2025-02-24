package ec.edu.uees.controlador;

import ec.edu.uees.modelo.VideoPlaylist;
import ec.edu.uees.vista.MainView;
import ec.edu.uees.util.VideoUtil;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoController {
    private final VideoPlaylist modelo;
    private final MainView vista;
    private boolean reproduciendo = false;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public VideoController(VideoPlaylist modelo, MainView vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Asegúrate que la carpeta de videos existe
        crearDirectorioVideos();

        // Inicializa el reproductor
        this.mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        configurarUI();
        configurarListeners();
        actualizarEtiqueta();
    }

    private void crearDirectorioVideos() {
        try {
            Path directorioVideos = Paths.get("videos");
            if (!Files.exists(directorioVideos)) {
                Files.createDirectories(directorioVideos);

                // Opcional: extraer videos de recursos si es necesario
                // Esto debería hacerse para cada video en la lista
                String videoActual = modelo.getActual();
                if (videoActual != null && !videoActual.equals("Sin videos")) {
                    Path destinoVideo = directorioVideos.resolve(videoActual);
                    VideoUtil.copiarVideo(videoActual, destinoVideo);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Error al crear directorio de videos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarUI() {
        // Usa el panel específico para el reproductor
        JPanel panelReproductor = vista.getPanelReproductor();
        panelReproductor.add(mediaPlayerComponent, BorderLayout.CENTER);
        panelReproductor.setPreferredSize(new Dimension(640, 360)); // Tamaño sugerido para el video
        vista.pack(); // Ajusta el tamaño de la ventana
        vista.setLocationRelativeTo(null); // Centra la ventana
    }

    private void configurarListeners() {
        vista.getBtnSiguiente().addActionListener(e -> {
            modelo.siguiente();
            reproducirVideoActual();
            actualizarEtiqueta();
        });

        vista.getBtnAnterior().addActionListener(e -> {
            modelo.anterior();
            reproducirVideoActual();
            actualizarEtiqueta();
        });

        vista.getBtnPausa().addActionListener(e -> {
            if (reproduciendo) {
                mediaPlayerComponent.mediaPlayer().controls().pause();
                vista.getBtnPausa().setText("Reanudar");
            } else {
                mediaPlayerComponent.mediaPlayer().controls().play();
                vista.getBtnPausa().setText("Pausar");
            }
            reproduciendo = !reproduciendo;
        });
    }

    private void reproducirVideoActual() {
        String videoActual = modelo.getActual();
        if (videoActual != null && !videoActual.equals("Sin videos")) {
            // Verificar que el archivo existe
            Path rutaVideo = Path.of("videos", videoActual);
            File archivoVideo = rutaVideo.toFile();

            if (!archivoVideo.exists()) {
                JOptionPane.showMessageDialog(vista,
                        "Video no encontrado: " + rutaVideo,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Detiene la reproducción actual antes de iniciar una nueva
            mediaPlayerComponent.mediaPlayer().controls().stop();

            // Reproduce el video
            boolean resultado = mediaPlayerComponent.mediaPlayer().media().play(rutaVideo.toString());
            reproduciendo = resultado;

            if (resultado) {
                vista.getBtnPausa().setText("Pausar");
                System.out.println("Reproduciendo: " + rutaVideo);
            } else {
                System.err.println("Error al reproducir: " + rutaVideo);
            }
        }
    }

    private void actualizarEtiqueta() {
        vista.getEtiquetaVideo().setText("Reproduciendo: " + modelo.getActual());
    }

    public void iniciarReproduccion() {
        reproducirVideoActual();
    }
}