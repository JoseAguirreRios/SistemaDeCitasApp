import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class SistemaDeCitasGUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private DefaultListModel<String> pacienteListModel;
    private DefaultListModel<String> doctorListModel;
    private DefaultListModel<String> citaListModel;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Doctor> doctores;
    private ArrayList<Cita> citas;

    public SistemaDeCitasGUI() {
        frame = new JFrame("Sistema de Citas");
        tabbedPane = new JTabbedPane();
        pacienteListModel = new DefaultListModel<>();
        doctorListModel = new DefaultListModel<>();
        citaListModel = new DefaultListModel<>();
        pacientes = new ArrayList<>();
        doctores = new ArrayList<>();
        citas = new ArrayList<>();

        initUI();
    }

    private void initUI() {
        // Apartado de pacientes
        JPanel pacientePanel = new JPanel(new BorderLayout());
        JList<String> pacienteList = new JList<>(pacienteListModel);
        pacienteList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = pacienteList.locationToIndex(evt.getPoint());
                    editarPaciente(index);
                }
            }
        });
        pacientePanel.add(new JScrollPane(pacienteList), BorderLayout.CENTER);

        JPanel pacienteInputPanel = new JPanel(new GridLayout(2, 1));
        JTextField pacienteNombreField = new JTextField();
        JButton agregarPacienteButton = new JButton("Agregar Paciente");
        agregarPacienteButton.addActionListener(e -> {
            String nombre = pacienteNombreField.getText();
            if (!nombre.isEmpty()) {
                pacienteListModel.addElement(nombre);
                pacientes.add(new Paciente(nombre));
                pacienteNombreField.setText("");
            }
        });
        pacienteInputPanel.add(new JLabel("Nombre:"));
        pacienteInputPanel.add(pacienteNombreField);
        pacienteInputPanel.add(agregarPacienteButton);

        pacientePanel.add(pacienteInputPanel, BorderLayout.SOUTH);

        // Apartado de doctores
        JPanel doctorPanel = new JPanel(new BorderLayout());
        JList<String> doctorList = new JList<>(doctorListModel);
        doctorList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = doctorList.locationToIndex(evt.getPoint());
                    editarDoctor(index);
                }
            }
        });
        doctorPanel.add(new JScrollPane(doctorList), BorderLayout.CENTER);

        JPanel doctorInputPanel = new JPanel(new GridLayout(2, 1));
        JTextField doctorNombreField = new JTextField();
        JButton agregarDoctorButton = new JButton("Agregar Doctor");
        agregarDoctorButton.addActionListener(e -> {
            String nombre = doctorNombreField.getText();
            if (!nombre.isEmpty()) {
                doctorListModel.addElement(nombre);
                doctores.add(new Doctor(nombre));
                doctorNombreField.setText("");
            }
        });
        doctorInputPanel.add(new JLabel("Nombre:"));
        doctorInputPanel.add(doctorNombreField);
        doctorInputPanel.add(agregarDoctorButton);

        doctorPanel.add(doctorInputPanel, BorderLayout.SOUTH);

        // Citas
        JPanel citaPanel = new JPanel(new BorderLayout());
        JList<String> citaList = new JList<>(citaListModel);
        citaList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = citaList.locationToIndex(evt.getPoint());
                    editarCita(index);
                }
            }
        });
        citaPanel.add(new JScrollPane(citaList), BorderLayout.CENTER);

        JPanel citaInputPanel = new JPanel(new GridLayout(2, 1));
        JButton agregarCitaButton = new JButton("Crear Cita");
        agregarCitaButton.addActionListener(e -> crearCita());
        citaInputPanel.add(agregarCitaButton);

        citaPanel.add(citaInputPanel, BorderLayout.SOUTH);

        // Muestra las pestañas del menu del programa
        tabbedPane.addTab("Pacientes", pacientePanel);
        tabbedPane.addTab("Doctores", doctorPanel);
        tabbedPane.addTab("Citas", citaPanel);

        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void editarPaciente(int index) {
        Paciente paciente = pacientes.get(index);
        JTextField direccionField = new JTextField(paciente.getDireccion());
        JTextField telefonoField = new JTextField(paciente.getTelefono());
        JTextField enfermedadField = new JTextField(paciente.getEnfermedad());
        JTextField tipoSangreField = new JTextField(paciente.getTipoSangre());
        JTextField sexoField = new JTextField(paciente.getSexo());
        JTextField nombreFamiliarField = new JTextField(paciente.getNombreFamiliar());
        JTextField telefonoFamiliarField = new JTextField(paciente.getTelefonoFamiliar());

        Object[] message = {
                "Dirección:", direccionField,
                "Teléfono:", telefonoField,
                "Enfermedad:", enfermedadField,
                "Tipo de Sangre:", tipoSangreField,
                "Sexo:", sexoField,
                "Nombre Familiar:", nombreFamiliarField,
                "Teléfono Familiar:", telefonoFamiliarField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Editar Paciente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            paciente.setDireccion(direccionField.getText());
            paciente.setTelefono(telefonoField.getText());
            paciente.setEnfermedad(enfermedadField.getText());
            paciente.setTipoSangre(tipoSangreField.getText());
            paciente.setSexo(sexoField.getText());
            paciente.setNombreFamiliar(nombreFamiliarField.getText());
            paciente.setTelefonoFamiliar(telefonoFamiliarField.getText());
        }
    }

    private void editarDoctor(int index) {
        Doctor doctor = doctores.get(index);
        JTextField direccionField = new JTextField(doctor.getDireccion());
        JTextField telefonoField = new JTextField(doctor.getTelefono());
        JTextField sexoField = new JTextField(doctor.getSexo());
        JTextField areaEspecializadaField = new JTextField(doctor.getAreaEspecializada());
        JTextField horarioField = new JTextField(doctor.getHorario());
        JTextField fechasDisponiblesField = new JTextField(doctor.getFechasDisponibles());

        Object[] message = {
                "Dirección:", direccionField,
                "Teléfono:", telefonoField,
                "Sexo:", sexoField,
                "Área Especializada:", areaEspecializadaField,
                "Horario:", horarioField,
                "Fechas Disponibles:", fechasDisponiblesField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Editar Doctor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            doctor.setDireccion(direccionField.getText());
            doctor.setTelefono(telefonoField.getText());
            doctor.setSexo(sexoField.getText());
            doctor.setAreaEspecializada(areaEspecializadaField.getText());
            doctor.setHorario(horarioField.getText());
            doctor.setFechasDisponibles(fechasDisponiblesField.getText());

            doctorListModel.set(index, doctor.getNombre());
        }
    }

    private void crearCita() {
        if (pacientes.isEmpty() || doctores.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Debe registrar al menos un paciente y un doctor.");
            return;
        }

        Paciente paciente = seleccionarPaciente();
        Doctor doctor = seleccionarDoctor();
        if (paciente == null || doctor == null) {
            return;
        }

        JTextField tipoConsultaField = new JTextField();
        JTextField fechaField = new JTextField();
        JTextField horaField = new JTextField();

        Object[] message = {
                "Tipo de Consulta:", tipoConsultaField,
                "Fecha (dd/MM/yyyy):", fechaField,
                "Hora (HH:mm):", horaField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Crear Cita", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String tipoConsulta = tipoConsultaField.getText();
            String fecha = fechaField.getText();
            String hora = horaField.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fecha + " " + hora, formatter);

            Cita nuevaCita = new Cita(paciente, doctor, tipoConsulta, fechaHora);
            citas.add(nuevaCita);
            Collections.sort(citas);

            actualizarListaCitas();
        }
    }

    private Paciente seleccionarPaciente() {
        String[] nombresPacientes = pacientes.stream().map(Paciente::getNombre).toArray(String[]::new);
        String nombrePaciente = (String) JOptionPane.showInputDialog(frame, "Seleccione un paciente", "Pacientes", JOptionPane.PLAIN_MESSAGE, null, nombresPacientes, nombresPacientes[0]);

        for (Paciente paciente : pacientes) {
            if (paciente.getNombre().equals(nombrePaciente)) {
                return paciente;
            }
        }
        return null;
    }

    private Doctor seleccionarDoctor() {
        String[] nombresDoctores = doctores.stream().map(Doctor::getNombre).toArray(String[]::new);
        String nombreDoctor = (String) JOptionPane.showInputDialog(frame, "Seleccione un doctor", "Doctores", JOptionPane.PLAIN_MESSAGE, null, nombresDoctores, nombresDoctores[0]);

        for (Doctor doctor : doctores) {
            if (doctor.getNombre().equals(nombreDoctor)) {
                return doctor;
            }
        }
        return null;
    }

    private void actualizarListaCitas() {
        citaListModel.clear();
        for (Cita cita : citas) {
            citaListModel.addElement(cita.toString());
        }
    }

    private void editarCita(int index) {
        Cita cita = citas.get(index);

        Paciente paciente = cita.getPaciente();
        Doctor doctor = cita.getDoctor();
        JTextField tipoConsultaField = new JTextField(cita.getTipoConsulta());
        JTextField fechaField = new JTextField(cita.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        JTextField horaField = new JTextField(cita.getFechaHora().format(DateTimeFormatter.ofPattern("HH:mm")));

        Object[] message = {
                "Paciente:", new JLabel(paciente.getNombre()),
                "Doctor:", new JLabel(doctor.getNombre()),
                "Tipo de Consulta:", tipoConsultaField,
                "Fecha (dd/MM/yyyy):", fechaField,
                "Hora (HH:mm):", horaField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Editar Cita", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String tipoConsulta = tipoConsultaField.getText();
            String fecha = fechaField.getText();
            String hora = horaField.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fecha + " " + hora, formatter);

            cita.setTipoConsulta(tipoConsulta);
            cita.setFechaHora(fechaHora);

            Collections.sort(citas);
            actualizarListaCitas();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaDeCitasGUI::new);
    }
}
