package ec.edu.uees.controlador;

import ec.edu.uees.modelo.VideoPlaylist;
import ec.edu.uees.vista.MainView;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class VideoController {
    private final VideoPlaylist modelo;
    private final MainView vista;
    private boolean reproduciendo = true;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public VideoController(VideoPlaylist modelo, MainView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        configurarUI();
        configurarListeners();
        actualizarEtiqueta();
    }

    private void configurarUI() {
        // Obtiene el panel de video de la vista y añade el reproductor
        JPanel panelVideo = vista.getPanelVideo();
        panelVideo.add(mediaPlayerComponent, BorderLayout.CENTER);
        vista.pack(); // Ajusta el tamaño de la ventana
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
            // Ruta completa del video en la carpeta temporal
            Path rutaVideo = Path.of("videos", videoActual);
            System.out.println("Intentando reproducir: " + rutaVideo.toAbsolutePath()); // Mensaje de depuración
            mediaPlayerComponent.mediaPlayer().media().play(rutaVideo.toString());
        }
    }

    private void actualizarEtiqueta() {
        vista.getEtiquetaVideo().setText("Reproduciendo: " + modelo.getActual());
    }

    public void iniciarReproduccion() {
        reproducirVideoActual();
    }


}