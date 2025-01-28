import java.util.ArrayList;
import java.util.Scanner;

public class SistemaDeCitasApp {

    static ArrayList<String> doctores = new ArrayList<>();
    static ArrayList<String> pacientes = new ArrayList<>();
    static ArrayList<String> citas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Sistema de Citas Simple ---");
            System.out.println("1. Dar de alta doctor");
            System.out.println("2. Dar de alta paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Ver doctores");
            System.out.println("5. Ver pacientes");
            System.out.println("6. Ver citas");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    darDeAltaDoctor(scanner);
                    break;
                case 2:
                    darDeAltaPaciente(scanner);
                    break;
                case 3:
                    crearCita(scanner);
                    break;
                case 4:
                    verDoctores();
                    break;
                case 5:
                    verPacientes();
                    break;
                case 6:
                    verCitas();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void darDeAltaDoctor(Scanner scanner) {
        System.out.print("Ingrese el nombre del doctor: ");
        String doctor = scanner.nextLine();
        doctores.add(doctor);
        System.out.println("Doctor registrado: " + doctor);
    }

    public static void darDeAltaPaciente(Scanner scanner) {
        System.out.print("Ingrese el nombre del paciente: ");
        String paciente = scanner.nextLine();
        pacientes.add(paciente);
        System.out.println("Paciente registrado: " + paciente);
    }

    public static void crearCita(Scanner scanner) {
        if (doctores.isEmpty() || pacientes.isEmpty()) {
            System.out.println("Debe registrar al menos un doctor y un paciente.");
            return;
        }

        System.out.println("Doctores disponibles:");
        for (int i = 0; i < doctores.size(); i++) {
            System.out.println((i + 1) + ". " + doctores.get(i));
        }
        System.out.print("Seleccione un doctor (número): ");
        int indiceDoctor = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpiar el buffer

        System.out.println("Pacientes disponibles:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i));
        }
        System.out.print("Seleccione un paciente (número): ");
        int indicePaciente = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese la fecha y hora de la cita: ");
        String fechaHora = scanner.nextLine();
        System.out.print("Ingrese el motivo de la cita: ");
        String motivo = scanner.nextLine();

        String cita = "Cita: " + fechaHora + " - " + motivo + "\nDoctor: " + doctores.get(indiceDoctor) + "\nPaciente: " + pacientes.get(indicePaciente);
        citas.add(cita);
        System.out.println("Cita creada exitosamente.");
    }

    public static void verDoctores() {
        System.out.println("\n--- Doctores Registrados ---");
        for (String doctor : doctores) {
            System.out.println(doctor);
        }
    }

    public static void verPacientes() {
        System.out.println("\n--- Pacientes Registrados ---");
        for (String paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    public static void verCitas() {
        System.out.println("\n--- Citas Registradas ---");
        for (String cita : citas) {
            System.out.println(cita);
        }
    }
}
