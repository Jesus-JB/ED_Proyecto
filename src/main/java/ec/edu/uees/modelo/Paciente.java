package ec.edu.uees.modelo;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String dolencia;
    private int prioridad;

    public Paciente(String nombre, String dolencia, int prioridad) {
        this.nombre = nombre;
        this.dolencia = dolencia;
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Paciente otro) {
        return Integer.compare(this.prioridad, otro.prioridad);
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDolencia() { return dolencia; }
    public int getPrioridad() { return prioridad; }
}