package ec.edu.uees.modelo;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class PriorityQueueManager {
    private PriorityQueue<Paciente> cola;
    private Map<String, Integer> dolencias;

    public PriorityQueueManager() {
        cola = new PriorityQueue<>();
        dolencias = new HashMap<>();
        cargarDolencias();
    }

    private void cargarDolencias() {
        try (InputStream is = getClass().getResourceAsStream("/complaints.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            br.lines().forEach(linea -> {
                String[] partes = linea.split(",");
                if(partes.length == 2) {
                    dolencias.put(partes[0].trim(), Integer.parseInt(partes[1].trim()));
                }
            });

        } catch (IOException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Cargando dolencias por defecto");
            dolencias.put("Emergencia", 1);
            dolencias.put("Fractura", 2);
            dolencias.put("Consulta General", 3);
        }
    }

    public Map<String, Integer> getDolencias() {
        return dolencias;
    }

    public void agregarPaciente(String nombre, String dolencia) {
        int prioridad = dolencias.getOrDefault(dolencia, 3);
        cola.add(new Paciente(nombre, dolencia, prioridad));
    }

    public Paciente atenderPaciente() {
        return cola.poll();
    }
}