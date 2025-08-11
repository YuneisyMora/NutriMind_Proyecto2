
package ac.cr.uned.nutrimind.modelos;

public class Paciente {
    //private int id;
    private String identificacion;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento; // sería mejor LocalDate
    private String sexo;
    private String fechaRegistro; // sería mejor LocalDate

    // Constructor, getters y setters
    public Paciente() {}
    public Paciente(String identificacion, String nombre, String apellidos,
                    String fechaNacimiento, String sexo, String fechaRegistro) {
        //this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.fechaRegistro = fechaRegistro;
    }
    //public int getId() { return id; }
    //public void setId(int id) { this.id = id; }
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}

