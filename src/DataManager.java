import java.io.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel; // Importación necesaria

public class DataManager {
    public static void guardarDatos(ArrayList<Paciente> pacientes, ArrayList<Doctor> doctores, ArrayList<Cita> citas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datos.dat"))) {
            oos.writeObject(pacientes);
            oos.writeObject(doctores);
            oos.writeObject(citas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos(ArrayList<Paciente> pacientes, ArrayList<Doctor> doctores, ArrayList<Cita> citas,
                                   DefaultListModel<String> pacienteListModel, DefaultListModel<String> doctorListModel, DefaultListModel<String> citaListModel) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("datos.dat"))) {
            pacientes.clear();
            doctores.clear();
            citas.clear();
            pacientes.addAll((ArrayList<Paciente>) ois.readObject());
            doctores.addAll((ArrayList<Doctor>) ois.readObject());
            citas.addAll((ArrayList<Cita>) ois.readObject());

            for (Paciente paciente : pacientes) {
                pacienteListModel.addElement(paciente.getNombre());
            }

            for (Doctor doctor : doctores) {
                doctorListModel.addElement(doctor.getNombre());
            }

            for (Cita cita : citas) {
                citaListModel.addElement(cita.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
