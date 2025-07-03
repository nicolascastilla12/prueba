import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class EstudianteService {
    private static final String SUPABASE_URL = "https://cinxxdbxhpadocjzkvso.supabase.co/rest/v1/estudiante";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNpbnh4ZGJ4aHBhZG9janprdnNvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTA3OTM3MDMsImV4cCI6MjA2NjM2OTcwM30.60kn-EpiZfSKnK8q5IDGf8KjPCNJd9v2SkVqPxSmopg";

    private Scanner scanner = new Scanner(System.in);

    public void crearEstudiante(Estudiante estudiante) {
        try {
            JSONObject estudianteJson = new JSONObject();
            estudianteJson.put("nombre", estudiante.getNombre());
            estudianteJson.put("carrera", estudiante.getCarrera());
            estudianteJson.put("semestre", estudiante.getSemestre());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=representation")
                    .POST(HttpRequest.BodyPublishers.ofString(estudianteJson.toString()))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("Estudiante creado exitosamente!");
            } else {
                System.out.println("Error al crear estudiante: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        try {
            String url = SUPABASE_URL + "?id=eq." + id;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 && !response.body().equals("[]")) {
                JSONArray jsonArray = new JSONArray(response.body());
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                return new Estudiante(
                        jsonObject.getInt("id"),
                        jsonObject.getString("nombre"),
                        jsonObject.getString("carrera"),
                        jsonObject.getString("semestre")
                );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        try {
            JSONObject estudianteJson = new JSONObject();
            estudianteJson.put("nombre", estudiante.getNombre());
            estudianteJson.put("carrera", estudiante.getCarrera());
            estudianteJson.put("semestre", estudiante.getSemestre());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + "?id=eq." + estudiante.getId()))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(estudianteJson.toString()))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 204) {
                System.out.println("Estudiante actualizado exitosamente!");
            } else {
                System.out.println("Error al actualizar estudiante: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void borrarEstudiante(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + "?id=eq." + id))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .DELETE()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 204) {
                System.out.println("Estudiante borrado exitosamente!");
            } else {
                System.out.println("Error al borrar estudiante: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listarTodosEstudiantes() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                System.out.println("\n=== LISTA DE ESTUDIANTES ===");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println("ID: " + jsonObject.getInt("id"));
                    System.out.println("Nombre: " + jsonObject.getString("nombre"));
                    System.out.println("Carrera: " + jsonObject.getString("carrera"));
                    System.out.println("-----------------------------");
                }
            } else {
                System.out.println("Error al obtener estudiantes: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Estudiante obtenerEstudiantePorCarrera(String carrera) {
        try {
            String url = SUPABASE_URL + "?carrera=eq." + carrera;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 && !response.body().equals("[]")) {
                JSONArray jsonArray = new JSONArray(response.body());
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                return new Estudiante(
                        jsonObject.getInt("id"),
                        jsonObject.getString("nombre"),
                        jsonObject.getString("carrera"),
                        jsonObject.getString("semestre")
                );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}