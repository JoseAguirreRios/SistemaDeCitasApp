import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita implements Serializable, Comparable<Cita> {
    private static final long serialVersionUID = 1L;

    private Paciente paciente;
    private Doctor doctor;
    private String tipoConsulta;
    private LocalDateTime fechaHora;

    public Cita(Paciente paciente, Doctor doctor, String tipoConsulta, LocalDateTime fechaHora) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.tipoConsulta = tipoConsulta;
        this.fechaHora = fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public int compareTo(Cita o) {
        return this.fechaHora.compareTo(o.getFechaHora());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Paciente: " + paciente.getNombre() + " / Tipo de Consulta: " + tipoConsulta + " / Fecha: " + fechaHora.format(formatter) + " / Doctor: " + doctor.getNombre();
    }
}
