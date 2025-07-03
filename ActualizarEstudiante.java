import java.util.Scanner;

public class ActualizarEstudiante {
    private EstudianteService estudianteService;
    private Scanner scanner;

    public ActualizarEstudiante(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("\n=== ACTUALIZAR ESTUDIANTE ===");
        System.out.print("Ingrese el ID del estudiante a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("\nInformación actual del estudiante:");
        System.out.println("1. Nombre: " + estudiante.getNombre());
        System.out.println("2. Carrera: " + estudiante.getCarrera());
        System.out.println("3. Semestre: " + estudiante.getSemestre());

        System.out.print("\nSeleccione qué desea actualizar (ej: 1,3): ");
        String seleccion = scanner.nextLine();

        String[] opciones = seleccion.split(",");

        for (String opcionStr : opciones) {
            try {
                int opcion = Integer.parseInt(opcionStr.trim());

                switch (opcion) {
                    case 1:
                        System.out.print("Nuevo Nombre: ");
                        estudiante.setNombre(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Nueva Carrera: ");
                        estudiante.setCarrera(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Nuevo Semestre: ");
                        estudiante.setSemestre(scanner.nextLine());
                        break;
                    default:
                        System.out.println("Opción " + opcion + " no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: " + opcionStr);
            }
        }

        estudianteService.actualizarEstudiante(estudiante);
    }
}
