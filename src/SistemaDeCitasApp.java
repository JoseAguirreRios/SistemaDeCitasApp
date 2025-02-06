import java.util.ArrayList;
import java.util.Scanner;

public class SistemaDeCitasApp {
    static ArrayList<Paciente> pacientes = new ArrayList<>();
    static ArrayList<String> doctores = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Dar de alta paciente");
            System.out.println("2. Ver pacientes");
            System.out.println("3. Crear cita");
            System.out.println("4. Ver historial de citas");
            System.out.println("5. Agregar doctor");
            System.out.println("6. Ver doctores");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    darDeAltaPaciente(scanner);
                    break;
                case 2:
                    verPacientes();
                    break;
                case 3:
                    crearCita(scanner);
                    break;
                case 4:
                    verHistorialCitas(scanner);
                    break;
                case 5:
                    agregarDoctor(scanner);
                    break;
                case 6:
                    verDoctores();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void darDeAltaPaciente(Scanner scanner) {
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        Paciente paciente = new Paciente(nombre);

        System.out.print("Ingrese la dirección del paciente: ");
        paciente.setDireccion(scanner.nextLine());
        System.out.print("Ingrese el teléfono del paciente: ");
        paciente.setTelefono(scanner.nextLine());
        System.out.print("¿Padece de alguna enfermedad? ");
        paciente.setEnfermedad(scanner.nextLine());
        System.out.print("Ingrese el tipo de sangre del paciente: ");
        paciente.setTipoSangre(scanner.nextLine());
        System.out.print("Ingrese el sexo del paciente: ");
        paciente.setSexo(scanner.nextLine());
        System.out.print("Ingrese el nombre de un familiar del paciente: ");
        paciente.setNombreFamiliar(scanner.nextLine());
        System.out.print("Ingrese el teléfono del familiar: ");
        paciente.setTelefonoFamiliar(scanner.nextLine());

        pacientes.add(paciente);
        System.out.println("Paciente registrado: " + nombre);
    }

    public static void verPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Lista de pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println("Nombre: " + paciente.getNombre());
                System.out.println("Dirección: " + paciente.getDireccion());
                System.out.println("Teléfono: " + paciente.getTelefono());
                System.out.println("Enfermedad: " + paciente.getEnfermedad());
                System.out.println("Tipo de Sangre: " + paciente.getTipoSangre());
                System.out.println("Sexo: " + paciente.getSexo());
                System.out.println("Nombre Familiar: " + paciente.getNombreFamiliar());
                System.out.println("Teléfono Familiar: " + paciente.getTelefonoFamiliar());
                System.out.println("-----------------------------");
            }
        }
    }

    public static void crearCita(Scanner scanner) {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        System.out.println("Seleccione un paciente para crear una cita:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i).getNombre());
        }
        System.out.print("Seleccione un paciente (número): ");
        int indicePaciente = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpiar el buffer

        if (indicePaciente >= 0 && indicePaciente < pacientes.size()) {
            Paciente paciente = pacientes.get(indicePaciente);
            System.out.print("Ingrese la fecha y hora de la cita: ");
            String fechaHora = scanner.nextLine();
            System.out.print("Ingrese el motivo de la cita: ");
            String motivo = scanner.nextLine();

            String cita = "Cita: " + fechaHora + " - " + motivo;
            paciente.agregarCita(cita);
            System.out.println("Cita creada para " + paciente.getNombre());
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public static void verHistorialCitas(Scanner scanner) {
        System.out.println("Seleccione un paciente para ver su historial de citas:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i).getNombre());
        }
        System.out.print("Seleccione un paciente (número): ");
        int indicePaciente = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpiar el buffer

        if (indicePaciente >= 0 && indicePaciente < pacientes.size()) {
            Paciente paciente = pacientes.get(indicePaciente);
            paciente.mostrarHistorialCitas();
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public static void agregarDoctor(Scanner scanner) {
        System.out.print("Ingrese el nombre del doctor: ");
        String nombre = scanner.nextLine();
        doctores.add(nombre);
        System.out.println("Doctor registrado: " + nombre);
    }

    public static void verDoctores() {
        if (doctores.isEmpty()) {
            System.out.println("No hay doctores registrados.");
        } else {
            System.out.println("Lista de doctores:");
            for (String doctor : doctores) {
                System.out.println(doctor);
            }
        }
    }
}