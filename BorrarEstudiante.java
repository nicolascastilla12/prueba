import java.util.Scanner;

public class BorrarEstudiante {
    private EstudianteService estudianteService;
    private Scanner scanner;

    public BorrarEstudiante(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("\n=== BORRAR ESTUDIANTE ===");
        System.out.println("1. Borrar por ID");
        System.out.println("2. Borrar por carrera");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el ID del estudiante: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("¿Está seguro de borrar el estudiante? (s/n): ");
                String confirmacion1 = scanner.nextLine();

                if (confirmacion1.equalsIgnoreCase("s")) {
                    estudianteService.borrarEstudiante(id);
                } else {
                    System.out.println("Operación cancelada.");
                }
                break;

            case 2:
                System.out.print("Ingrese la carrera del estudiante: ");
                String carrera = scanner.nextLine();

                Estudiante estudiante = estudianteService.obtenerEstudiantePorCarrera(carrera);

                if (estudiante != null) {
                    System.out.println("Estudiante encontrado:");
                    System.out.println("ID: " + estudiante.getId());
                    System.out.println("Nombre: " + estudiante.getNombre());

                    System.out.print("¿Está seguro de borrarlo? (s/n): ");
                    String confirmacion2 = scanner.nextLine();

                    if (confirmacion2.equalsIgnoreCase("s")) {
                        estudianteService.borrarEstudiante(estudiante.getId());
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                } else {
                    System.out.println("Estudiante no encontrado.");
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }
}