package ec.edu.uees.modelo;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class VideoPlaylist {
    private NodoVideo actual;

    private static class NodoVideo {
        String video;
        NodoVideo siguiente;
        NodoVideo anterior;

        public NodoVideo(String video) {
            this.video = video;
        }
    }

    public VideoPlaylist() {
        try (InputStream is = getClass().getResourceAsStream("/videos.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            List<String> videos = br.lines().collect(Collectors.toList());

            if (videos.isEmpty()) {
                throw new IOException("Archivo vacío");
            }

            for (String video : videos) {
                agregarVideo(video);
            }
            hacerCircular();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Cargando videos");
            agregarVideo("video_default.mp4");
            hacerCircular();
        }
    }

    private void agregarVideo(String video) {
        NodoVideo nuevo = new NodoVideo(video);
        if (actual == null) {
            actual = nuevo;
        } else {
            NodoVideo ultimo = actual;
            while (ultimo.siguiente != null) {
                ultimo = ultimo.siguiente;
            }
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
        }
    }

    private void hacerCircular() {
        if (actual != null) {
            NodoVideo primero = actual;
            NodoVideo ultimo = actual;
            while (ultimo.siguiente != null) {
                ultimo = ultimo.siguiente;
            }
            ultimo.siguiente = primero;
            primero.anterior = ultimo;
        }
    }

    public void siguiente() {
        actual = actual.siguiente;
    }

    public void anterior() {
        actual = actual.anterior;
    }

    public String getActual() {
        return actual != null ? actual.video : "Sin videos";
    }
}