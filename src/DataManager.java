import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import java.time.LocalDateTime;

public class DataManager {

    // Método para guardar los datos en archivos .txt
    public static void guardarDatos(ArrayList<Paciente> pacientes, ArrayList<Doctor> doctores, ArrayList<Cita> citas) {
        guardarPacientes(pacientes);
        guardarDoctores(doctores);
        guardarCitas(citas);
    }

    // Guardar pacientes en pacientes.txt
    private static void guardarPacientes(ArrayList<Paciente> pacientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pacientes.txt"))) {
            for (Paciente paciente : pacientes) {
                writer.write(paciente.getNombre() + "," + paciente.getDireccion() + "," + paciente.getTelefono() + "," +
                        paciente.getEnfermedad() + "," + paciente.getTipoSangre() + "," + paciente.getSexo() + "," +
                        paciente.getNombreFamiliar() + "," + paciente.getTelefonoFamiliar());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar doctores en doctores.txt
    private static void guardarDoctores(ArrayList<Doctor> doctores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("doctores.txt"))) {
            for (Doctor doctor : doctores) {
                writer.write(doctor.getNombre() + "," + doctor.getDireccion() + "," + doctor.getTelefono() + "," +
                        doctor.getSexo() + "," + doctor.getAreaEspecializada() + "," + doctor.getHorario() + "," +
                        doctor.getFechasDisponibles());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar citas en citas.txt
    private static void guardarCitas(ArrayList<Cita> citas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("citas.txt"))) {
            for (Cita cita : citas) {
                writer.write(cita.getPaciente().getNombre() + "," + cita.getDoctor().getNombre() + "," +
                        cita.getTipoConsulta() + "," + cita.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar los datos desde archivos .txt
    public static void cargarDatos(ArrayList<Paciente> pacientes, ArrayList<Doctor> doctores, ArrayList<Cita> citas,
                                   DefaultListModel<String> pacienteListModel, DefaultListModel<String> doctorListModel, DefaultListModel<String> citaListModel) {
        cargarPacientes(pacientes, pacienteListModel);
        cargarDoctores(doctores, doctorListModel);
        cargarCitas(citas, citaListModel, pacientes, doctores);
    }

    // Cargar pacientes desde pacientes.txt
    private static void cargarPacientes(ArrayList<Paciente> pacientes, DefaultListModel<String> pacienteListModel) {
        try (BufferedReader reader = new BufferedReader(new FileReader("pacientes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                Paciente paciente = new Paciente(datos[0]);
                paciente.setDireccion(datos[1]);
                paciente.setTelefono(datos[2]);
                paciente.setEnfermedad(datos[3]);
                paciente.setTipoSangre(datos[4]);
                paciente.setSexo(datos[5]);
                paciente.setNombreFamiliar(datos[6]);
                paciente.setTelefonoFamiliar(datos[7]);
                pacientes.add(paciente);
                pacienteListModel.addElement(paciente.getNombre());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar doctores desde doctores.txt
    private static void cargarDoctores(ArrayList<Doctor> doctores, DefaultListModel<String> doctorListModel) {
        try (BufferedReader reader = new BufferedReader(new FileReader("doctores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                Doctor doctor = new Doctor(datos[0]);
                doctor.setDireccion(datos[1]);
                doctor.setTelefono(datos[2]);
                doctor.setSexo(datos[3]);
                doctor.setAreaEspecializada(datos[4]);
                doctor.setHorario(datos[5]);
                doctor.setFechasDisponibles(datos[6]);
                doctores.add(doctor);
                doctorListModel.addElement(doctor.getNombre());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar citas desde citas.txt
    private static void cargarCitas(ArrayList<Cita> citas, DefaultListModel<String> citaListModel, ArrayList<Paciente> pacientes, ArrayList<Doctor> doctores) {
        try (BufferedReader reader = new BufferedReader(new FileReader("citas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                Paciente paciente = buscarPacientePorNombre(pacientes, datos[0]);
                Doctor doctor = buscarDoctorPorNombre(doctores, datos[1]);
                if (paciente != null && doctor != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime fechaHora = LocalDateTime.parse(datos[3], formatter);
                    Cita cita = new Cita(paciente, doctor, datos[2], fechaHora);
                    citas.add(cita);
                    citaListModel.addElement(cita.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para buscar un paciente por nombre
    private static Paciente buscarPacientePorNombre(ArrayList<Paciente> pacientes, String nombre) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNombre().equals(nombre)) {
                return paciente;
            }
        }
        return null;
    }

    // Método auxiliar para buscar un doctor por nombre
    private static Doctor buscarDoctorPorNombre(ArrayList<Doctor> doctores, String nombre) {
        for (Doctor doctor : doctores) {
            if (doctor.getNombre().equals(nombre)) {
                return doctor;
            }
        }
        return null;
    }
}