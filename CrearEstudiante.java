import java.util.Scanner;

public class CrearEstudiante {
    private EstudianteService EstudianteService;
    private Scanner scanner;

    public CrearEstudiante(EstudianteService estudianteService) {
        this.EstudianteService = estudianteService;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("\n=== CREAR NUEVO ESTUDIANTE ===");

        Estudiante nuevoEstudiante = new Estudiante();

        System.out.print("Nombre: ");
        nuevoEstudiante.setNombre(scanner.nextLine());

        System.out.print("Carrera: ");
        nuevoEstudiante.setCarrera(scanner.nextLine());

        System.out.print("Semestre: ");
        nuevoEstudiante.setSemestre(scanner.nextLine());

        EstudianteService.crearEstudiante(nuevoEstudiante);
    }
}
