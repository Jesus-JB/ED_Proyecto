package ec.edu.uees.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class VideoUtil {

    /**
     * Copia un video desde el classpath (dentro del JAR) a una ruta externa.
     *
     * @param nombreVideo Nombre del video en el classpath (debe estar en src/main/resources).
     * @param destino     Ruta donde se copiará el video.
     * @throws IOException Si el video no se encuentra o no se puede copiar.
     */
    public static void copiarVideo(String nombreVideo, Path destino) throws IOException {
        // Obtiene el recurso (video) desde el classpath
        try (InputStream inputStream = VideoUtil.class.getResourceAsStream("/" + nombreVideo)) {
            if (inputStream == null) {
                throw new IOException("Video no encontrado: " + nombreVideo);
            }
            // Copia el video a la ruta especificada
            Files.copy(inputStream, destino, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}