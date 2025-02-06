import java.io.Serializable;
import java.util.ArrayList;

public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String direccion;
    private String telefono;
    private String sexo;
    private String areaEspecializada;
    private String horario;
    private String fechasDisponibles;

    public Doctor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public String getAreaEspecializada() {
        return areaEspecializada;
    }

    public String getHorario() {
        return horario;
    }

    public String getFechasDisponibles() {
        return fechasDisponibles;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAreaEspecializada(String areaEspecializada) {
        this.areaEspecializada = areaEspecializada;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setFechasDisponibles(String fechasDisponibles) {
        this.fechasDisponibles = fechasDisponibles;
    }
}
