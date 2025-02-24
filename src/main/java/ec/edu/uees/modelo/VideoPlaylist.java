package ec.edu.uees.modelo;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class VideoPlaylist {
    private VideoNode actual;

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
            JOptionPane.showMessageDialog(null, "Error Cargando videos: " + e.getMessage());
            agregarVideo("video_default.mp4");
            hacerCircular();
        }
    }

    private void agregarVideo(String video) {
        VideoNode nuevo = new VideoNode(video);
        if (actual == null) {
            actual = nuevo;
        } else {
            VideoNode ultimo = actual;
            while (ultimo.next != null) {
                ultimo = ultimo.next;
            }
            ultimo.next = nuevo;
            nuevo.prev = ultimo;
        }
    }

    private void hacerCircular() {
        if (actual != null) {
            VideoNode primero = actual;
            VideoNode ultimo = actual;
            while (ultimo.next != null) {
                ultimo = ultimo.next;
            }
            ultimo.next = primero;
            primero.prev = ultimo;
        }
    }

    public void siguiente() {
        if (actual != null && actual.next != null) {
            actual = actual.next;
        }
    }

    public void anterior() {
        if (actual != null && actual.prev != null) {
            actual = actual.prev;
        }
    }

    public String getActual() {
        return actual != null ? actual.videoName : "Sin videos";
    }
}