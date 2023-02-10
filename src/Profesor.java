import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Profesor {
    private String nombre, apellidos, vehiculo;

    private List<String> especialidad;
    private boolean esTitular, esAsociado;

    public Profesor(String nombre, String apellidos, String vehiculo, boolean esTitular, boolean esAsociado,String[] especialidades) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.vehiculo = vehiculo;
        this.esTitular = esTitular;
        this.esAsociado = esAsociado;
        especialidad = new ArrayList<>();
        this.especialidad = Arrays.stream(especialidades).toList();
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", vehiculo='" + vehiculo + '\'' +
                ", especialidad=" + especialidad +
                ", esTitular=" + esTitular +
                ", esAsociado=" + esAsociado +
                '}';
    }

    public Profesor(String nombre, String apellidos, List<String> especialidad, boolean esTitular, boolean esAsociado, String vehiculo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        especialidad = new ArrayList<>();
        this.especialidad = especialidad;
        this.esTitular = esTitular;
        this.esAsociado = esAsociado;
        this.vehiculo = vehiculo;
    }

    public Profesor() {
        this.especialidad = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<String> getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(ArrayList<String> especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isEsTitular() {
        return esTitular;
    }

    public void setEsTitular(boolean esTitular) {
        this.esTitular = esTitular;
    }

    public boolean isEsAsociado() {
        return esAsociado;
    }

    public void setEsAsociado(boolean esAsociado) {
        this.esAsociado = esAsociado;
    }
}
