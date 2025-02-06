import java.util.ArrayList;

public class Paciente {
    private String nombre;
    private String direccion;
    private String telefono;
    private String enfermedad;
    private String tipoSangre;
    private String sexo;
    private String nombreFamiliar;
    private String telefonoFamiliar;
    private ArrayList<String> citas = new ArrayList<>();

    public Paciente(String nombre) {
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

    public String getEnfermedad() {
        return enfermedad;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getSexo() {
        return sexo;
    }

    public String getNombreFamiliar() {
        return nombreFamiliar;
    }

    public String getTelefonoFamiliar() {
        return telefonoFamiliar;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setNombreFamiliar(String nombreFamiliar) {
        this.nombreFamiliar = nombreFamiliar;
    }

    public void setTelefonoFamiliar(String telefonoFamiliar) {
        this.telefonoFamiliar = telefonoFamiliar;
    }

    public void agregarCita(String cita) {
        citas.add(cita);
    }

    public void mostrarHistorialCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas para " + nombre);
        } else {
            System.out.println("Historial de citas para " + nombre + ":");
            for (String cita : citas) {
                System.out.println(cita);
            }
        }
    }
}
