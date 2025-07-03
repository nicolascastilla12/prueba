public class Estudiante {
        int ID;
        private String nombre;
        private String carrera;
        private String semestre;

        // Constructor vacío
        public Estudiante() {
        }

        // Constructor completo
        public Estudiante(int ID_Universidad, String nombre, String departamento, String direccion) {
            this.ID = ID;
            this.nombre = nombre;
            this.carrera = carrera;
            this.semestre = semestre;


        }

        // Getters y Setters
        public int getId() {
            return ID;
        }

        public void setId(int id) {
            this.ID = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCarrera() {
            return carrera;
        }

        public void setCarrera(String carrera) {this.carrera = carrera;}

        public String getSemestre() { return  semestre;}

        public void setSemestre(String semestre) {
            this.semestre = semestre;
        }

        @Override
        public String toString() {
            return "Estudiante{" +
                    "id=" + ID +
                    ",nombre='" + nombre + '\'' +
                    ",carrera='" + carrera + '\''  +
                    ", semestre='" + semestre + '\'';

        }
    }

