import java.util.Scanner;

public class LeerEstudiante {
    private EstudianteService EstudianteService;
    private Scanner scanner;

    public LeerEstudiante(EstudianteService estudianteService) {
        this.EstudianteService = estudianteService;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("\n=== CONSULTAR ESTUDIANTE ===");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por carrera");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Estudiante estudiante = null;

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el ID del estudiante: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                estudiante = EstudianteService.obtenerEstudiantePorId(id);
                break;
            case 2:
                System.out.print("Ingrese la carrera del estudiante: ");
                String carrera = scanner.nextLine();
                estudiante = EstudianteService.obtenerEstudiantePorCarrera(carrera);
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (estudiante != null) {
            System.out.println("\n=== INFORMACIÓN DEL ESTUDIANTE ===");
            System.out.println("ID: " + estudiante.getId());
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Carrera: " + estudiante.getCarrera());
            System.out.println("Semestre: " + estudiante.getSemestre());
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}