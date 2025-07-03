import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    private static EstudianteService estudianteService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        estudianteService = new EstudianteService(); // ¡Instanciado correctamente!

        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE ESTUDIANTES ===");
            System.out.println("1. Crear nuevo estudiante");
            System.out.println("2. Leer información de estudiante");
            System.out.println("3. Actualizar estudiante");
            System.out.println("4. Borrar estudiante");
            System.out.println("5. Listar todos los estudiantes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    new CrearEstudiante(estudianteService).ejecutar();
                    break;
                case 2:
                    new LeerEstudiante(estudianteService).ejecutar();
                    break;
                case 3:
                    new ActualizarEstudiante(estudianteService).ejecutar();
                    break;
                case 4:
                    new BorrarEstudiante(estudianteService).ejecutar();
                    break;
                case 5:
                    estudianteService.listarTodosEstudiantes();
                    break;
                case 6:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}
